package imageProcessing.processors;

import models.Image;

import java.awt.image.BufferedImage;

public abstract class TwoImagesProcessor implements Processor {

    private Image sourceImage;

    protected abstract int operation(int destinationRGB, int sourceRGB);

    public TwoImagesProcessor(Image image) {
        this.sourceImage = image;
    }

    @Override
    public void process(Image destinationImage) {
        BufferedImage src = sourceImage.getBufferedImage();
        BufferedImage dest = destinationImage.getBufferedImage();
        for(int i = 0; i < dest.getWidth(); i++)
            for (int j = 0; j < dest.getHeight(); j++) {
                dest.setRGB(i, j, operation(dest.getRGB(i,j), src.getRGB(i,j)));
            }
    }
}
