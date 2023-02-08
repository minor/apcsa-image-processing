import images.*;

public class ImageProcessor {
    private APImage image;

    public ImageProcessor(APImage image) {
        this.image = image;
        //this.image = new APImage(image.getName());
    }

    public void toBW() {
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();

                int avg = (red + green + blue) / 3;

                if (avg < 128) {
                    p.setRed(0);
                    p.setGreen(0);
                    p.setBlue(0);
                } else {
                    p.setRed(255);
                    p.setGreen(255);
                    p.setBlue(255);
                }
            }
        }
    }

    public void toGrayScale() {
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();

                int avg = (red + green + blue) / 3;
                p.setRed(avg);
                p.setGreen(avg);
                p.setBlue(avg);
            }
        }
    }

    public void toLuminanceGrayScale() {
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();

                int avg = (int)(((red * 0.299) + (green * .587) + (blue * .114)) / 3);
                p.setRed(avg);
                p.setGreen(avg);
                p.setBlue(avg);
            }
        }
    }

    public void rotateImage(int angle) {
        // - 90 = left, 90 = right
        int rows = image.getHeight();
        int cols = image.getWidth();
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (angle == -90) {
                    result[j][rows - i - 1] = image[i][j];
                } else if (angle == 90) {
                    result[cols - j - 1][i] = image[i][j];
                } else {
                    result[rows - i - 1][cols - j - 1] = image[i][j];
                }
            }
        }
    }

    public void toOldFashioned() {
        toGrayScale();
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();
                if (red < 63) {
                    red = (int)(red * 1.1);
                    blue = (int)(blue * 0.9);
                } else if (red < 192) {
                    red = (int)(red * 1.15);
                    blue = (int)(blue * 0.85);
                } else {
                    red = Math.min(int(red * 1.08), 255);
                    blue = (int)(blue * 0.93);
                }
                p.setRed(red);
                p.setGreen(blue);
            }
        }
    }
    public void toDarkOrBright(String val) {
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();

                if (val == "darken") {
                    p.setRed(red - 50);
                    p.setGreen(green - 50);
                    p.setBlue(blue - 50);
                } else if (val == "brighten") {
                    p.setRed(red + 50);
                    p.setGreen(green + 50);
                    p.setBlue(blue + 50);
                }

            }
        }
    }
}
