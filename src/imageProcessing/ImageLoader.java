package imageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage load(String path) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File("images/src/" + path + ".jpg"));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage load(File file) {
        try {
            BufferedImage image;
            image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public static void save(String fileName, BufferedImage image) {
        File outputFile = new File("images/res/" + fileName + ".jpg");
        try {
            ImageIO.write(image, "jpg", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
