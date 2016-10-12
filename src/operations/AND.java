package operations;

import imageProcessing.processors.TwoImagesProcessor;
import models.Image;

public class AND extends TwoImagesProcessor {
    public AND(Image image) {
        super(image);
    }

    @Override
    protected int operation(int destination, int source) {
        return destination & source;
    }
}
