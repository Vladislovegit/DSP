package imageProcessing.processors;

import models.Image;

import java.awt.image.BufferedImage;

public abstract class OneImageProcessor implements Processor {

    protected abstract int operation(int rgb);

    @Override
    public void process(Image image) {
        BufferedImage bufferedImage = image.getBufferedImage();
        for(int i = 0; i < bufferedImage.getWidth(); i++)
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                bufferedImage.setRGB(i, j, operation(bufferedImage.getRGB(i, j)));
            }
    }
}
