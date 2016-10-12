package imageProcessing;

import javafx.scene.chart.XYChart;

import java.util.Arrays;

public class ImageProcessing {
    public static Integer[][] dissection(Integer[][] pixels, int lowerLimit, int upperLimit) {
        Integer[][] res = new Integer[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            System.arraycopy(pixels[i], 0, res[i], 0, pixels[i].length);
        }

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                res[i][j] = (res[i][j] > lowerLimit && res[i][j] < upperLimit) ? 255 : 0;
            }
        }
        return res;
    }

    public static Integer[][] dissection(Integer[][] pixels, int lowerLimit) {
        Integer[][] res = new Integer[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            System.arraycopy(pixels[i], 0, res[i], 0, pixels[i].length);
        }

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                res[i][j] = (res[i][j] > lowerLimit) ? 255 : 0;
            }
        }
        return res;
    }

    public static XYChart.Series getHistogramSeries(Integer[][] pixels) {
        Integer[] brightneses = new Integer[256];
        Arrays.fill(brightneses, 0);

        XYChart.Series series = new XYChart.Series();
        series.setName("BarChart");

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                brightneses[ pixels[i][j] ]++;
            }
        }

        for (int i = 0; i < brightneses.length ; i++) {
            series.getData().add(new XYChart.Data(Integer.toString(i), brightneses[i]));
        }


        return series;
    }

    public static Integer[][] SobelFilter(Integer[][] pixels) {
        Integer width = pixels.length;
        Integer height = pixels[0].length;
        Integer[][] h1 = {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
        Integer[][] h2 = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        Integer[][] resh1 = convolution(pixels, h1);
        Integer[][] resh2 = convolution(pixels, h2);

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                resh1[i][j] = (int)(Math.sqrt(resh1[i][j] * resh1[i][j] + resh2[i][j] * resh2[i][j]));
                if(resh1[i][j] > 255)
                    resh1[i][j] = 255;
                else if(resh1[i][j] < 0)
                    resh1[i][j] = 0;
            }
        }
        return resh1;
    }

    public static Integer[][] degradation(Integer[][] pixels) {
        Integer width = pixels.length;
        Integer height = pixels[0].length;
        Integer[][] h = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        Integer[][] res = convolution(pixels, h);

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                res[i][j] /= 16;
                if(res[i][j] > 255)
                    res[i][j] = 255;
                else if(res[i][j] < 0)
                    res[i][j] = 0;
            }
        }
        return res;
    }

    private static Integer[][] convolution(Integer[][] pixels, Integer[][] matrix) {
        Integer width = pixels.length;
        Integer height = pixels[0].length;

        Integer[][] resh1 = new Integer[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            System.arraycopy(pixels[i], 0, resh1[i], 0, pixels[i].length);
        }

        Integer pixel;
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                pixel = 0;
                for (int k = 0; k < matrix.length; k++) {
                    for (int l = 0; l < matrix[k].length; l++) {
                        pixel += pixels[i - 1 + k][j - 1 + l] * matrix[l][k];
                    }
                }
                resh1[i][j] = pixel;
            }
        }
        return resh1;
    }
}
