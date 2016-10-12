package operations;

import imageProcessing.processors.TwoImagesProcessor;
import models.Image;

public class OR extends TwoImagesProcessor {
    public OR(Image image) {
        super(image);
    }

    @Override
    protected int operation(int destination, int source) {
        return destination | source;
    }
}
