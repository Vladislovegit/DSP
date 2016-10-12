package models;

import imageProcessing.ImageLoader;
import imageProcessing.processors.Processor;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;

public class Image {

    private String filename;
    private BufferedImage image;

    public Image(BufferedImage bufferedImage, String filename) {
        this.image = bufferedImage;
        this.filename = filename;
    }

    public Image(String path) {
        filename = path;
        image = ImageLoader.load(path);
    }

    public Image(File file) {
        filename = file.getName();
        image = ImageLoader.load(file);
    }

    public BufferedImage getBufferedImage() {
        return image;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public Image clone() {
        String fileName = this.filename;
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new Image(new BufferedImage(cm, raster, isAlphaPremultiplied, null), fileName);
    }

    public void save() {
        ImageLoader.save(filename, image);
    }

    public void save(String filename) {
        ImageLoader.save(filename, image);
    }

    public void save(Operations op) {
        ImageLoader.save(filename + "_" + op, image);
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static class Builder {
        private ArrayList<Processor> processors = new ArrayList<>();
        private Image image;

        public Builder (Image image) {
            this.image = image.clone();
        }

        public Builder add(Processor processor) {
            processors.add(processor);
            return this;
        }

        public Image build() {
            for (Processor processor: processors) {
                processor.process(image);
            }
            return image;
        }
    }
}
