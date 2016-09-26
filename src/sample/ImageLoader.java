package sample;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage load(String path) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(path + ".jpg"));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer[][] getGrayValues(BufferedImage image) {
        Integer[][] src = new Integer[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                src[x][y] = (int)(0.3 * color.getRed() + 0.59 * color.getGreen() + 0.11 * color.getBlue());
            }
        return src;
    }

    public static Integer[][] getRedValues(BufferedImage image) {
        Integer[][] src = new Integer[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                src[x][y] = color.getRed();
            }
        return src;
    }

    public static Integer[][] getGreenValues(BufferedImage image) {
        Integer[][] src = new Integer[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                src[x][y] = color.getGreen();
            }
        return src;
    }

    public static Integer[][] getBlueValues(BufferedImage image) {
        Integer[][] src = new Integer[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                src[x][y] = color.getBlue();
            }
        return src;
    }

    public static void save(Integer[][] res, String fileName, BufferedImage image) {
        save(res,res,res,fileName, image);
    }

    public static void save(Integer[][] red, Integer[][] green, Integer[][] blue, String fileName, BufferedImage image) {
        BufferedImage resImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                resImage.setRGB(i, j, new Color(red[i][j], green[i][j], blue[i][j]).getRGB());
            }
        }

        File outputFile = new File(fileName + ".jpg");
        try {
            ImageIO.write(resImage, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
