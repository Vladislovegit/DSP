package operations;

import imageProcessing.processors.TwoImagesProcessor;
import models.Image;

public class XOR extends TwoImagesProcessor {
    public XOR(Image image) {
        super(image);
    }

    @Override
    protected int operation(int destination, int source) {
        return destination ^ source;
    }
}
