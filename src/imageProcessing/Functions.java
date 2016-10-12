package imageProcessing;

import models.Image;
import operations.*;

import static models.Operations.*;

public class Functions {

    public static void doAND(Image firstImage, Image secondImage) {
        new Image.Builder(firstImage)
                .add(new AND(secondImage))
                .build()
                .save(firstImage.getFilename() + "_" + AND + "_" + secondImage.getFilename());
    }

    public static void doOR(Image firstImage, Image secondImage) {
        new Image.Builder(firstImage)
                .add(new OR(secondImage))
                .build()
                .save(firstImage.getFilename() + "_" + OR + "_" + secondImage.getFilename());
    }

    public static void doXOR(Image firstImage, Image secondImage) {
        new Image.Builder(firstImage)
                .add(new XOR(secondImage))
                .build()
                .save(firstImage.getFilename() + "_" + XOR + "_" + secondImage.getFilename());
    }

    public static void doNOT(Image image) {
        new Image.Builder(image).add(new NOT()).build().save(NOT);
    }

    public static void doConstAddition(Image image, Double a) {
        new Image.Builder(image)
                .add(new ConstAddition(a))
                .build()
                .save(image.getFilename() + "_" + ADD + "_const_" + a.toString());
    }

    public static void doConstMultiplication(Image image, Double a) {
        new Image.Builder(image)
                .add(new ConstMultiplication(a))
                .build()
                .save(image.getFilename() + "_" + MUL+ "_const_" + a.toString());
    }

    public static void doConstSubtraction(Image image, Double a) {
        new Image.Builder(image)
                .add(new ConstSubtraction(a))
                .build()
                .save(image.getFilename() + "_" + SUB + "_const_" + a.toString());
    }

    public static void doImagesAddition(Image firstImage, Image secondImage) {
        new Image.Builder(firstImage)
                .add(new ImagesAddition(secondImage))
                .build()
                .save(firstImage.getFilename() + "_" + ADD + "_" + secondImage.getFilename());
    }

    public static void doImagesSubtraction(Image firstImage, Image secondImage) {
        new Image.Builder(firstImage)
                .add(new ImagesSubtraction(secondImage))
                .build()
                .save(firstImage.getFilename() + "_" + SUB + "_" + secondImage.getFilename());
    }

    public static void doMasking(Image firstImage, Image secondImage) {
        new Image.Builder(firstImage)
                .add(new Masking(new Image.Builder(secondImage)
                        .add(new Binarization(200))
                        .build()))
                .build()
                .save(firstImage.getFilename() + "_" + MASK + "_" + secondImage.getFilename());
    }

    public static void doHistogramEqualization(Image image) {
        new Image.Builder(image)
                .add(new HistogramEqualization())
                .build()
                .save(HIST_EQU);
    }

    public static void doSawtoothCut(Image image, Integer sawtooth) {
        new Image.Builder(image)
                .add(new SawToothCut(sawtooth))
                .build()
                .save(image.getFilename() + "_" + CUT + "_" + sawtooth);
    }
}
