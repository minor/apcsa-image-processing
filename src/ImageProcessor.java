import images.*;

public class ImageProcessor {
    private APImage image;

    public ImageProcessor(APImage image) {
        this.image = image;
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

//    public void rotateImage(int angle) {
//        // - 90 = left, 90 = right
//        int rows = image.getHeight();
//        int cols = image.getWidth();
//        int[][] result = new int[cols][rows];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (angle == -90) {
//                    result[j][rows - i - 1] = image[i][j];
//                } else if (angle == 90) {
//                    result[cols - j - 1][i] = image[i][j];
//                } else {
//                    result[rows - i - 1][cols - j - 1] = image[i][j];
//                }
//            }
//        }
//    }

    public void toOldFashioned() {
        toGrayScale();
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int blue = p.getBlue();

                if (red < 63) {
                    red = (int)(red * 1.1);
                    blue = (int)(blue * 0.9);
                } else if (red < 192) {
                    red = (int)(red * 1.15);
                    blue = (int)(blue * 0.85);
                } else {
                    red = Math.min((int)(red * 1.08), 255);
                    blue = (int)(blue * 0.93);
                }
                p.setRed(red);
                p.setGreen(blue);
            }
        }
    }

    public void toDarkOrBright(String val, int magnitude) {
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();

                if (val.equals("darken")) {
                    p.setRed(Math.max(0, red - magnitude));
                    p.setGreen(Math.max(0, green - magnitude));
                    p.setBlue(Math.max(0, blue - magnitude));
                } else if (val.equals("brighten")) {
                    p.setRed(Math.min(255, red + magnitude));
                    p.setGreen(Math.min(255, green + magnitude));
                    p.setBlue(Math.min(255, blue + magnitude));
                }

            }
        }
    }

    public void colorFilter(int r, int g, int b){
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = Math.min(p.getRed() + r, 255);
                int green = Math.min(p.getGreen() + g, 255);
                int blue = Math.min(p.getBlue() + b, 255);

                p.setRed(red);
                p.setGreen(green);
                p.setBlue(blue);
            }
        }
    }

    public void posterize(int r1, int g1, int b1, int r2, int g2, int b2){
        int color1Avg = (r1 + g1 + b1) / 3;
        int color2Avg = (r2 + g2 + b2) / 3;

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

                // if avg closer to color1avg -> set to color1
                // else set to color2

                if (Math.abs(color1Avg-avg) < Math.abs(color2Avg-avg)) {
                    p.setRed(r1);
                    p.setGreen(g1);
                    p.setBlue(b1);
                } else {
                    p.setRed(r2);
                    p.setGreen(g2);
                    p.setBlue(b2);
                }
            }
        }
    }

    public void toPhotoNeg(){
        toGrayScale();
        // row
        for (int i = 0; i < image.getHeight(); i++) {
            // column
            for (int j = 0; j < image.getWidth(); j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int r = p.getRed();
                int g = p.getGreen();
                int b = p.getBlue();

                p.setRed(255-r);
                p.setGreen(255-g);
                p.setBlue(255-b);
            }
        }
    }

    public void sharpen(int sharpness, int threshold){

        APImage copy = new APImage(image.getName());
        // row
        for (int i = 0; i < image.getHeight()-1; i++) {
            // column
            for (int j = 1; j < image.getWidth(); j++) {
                //                    col, row
                Pixel old = copy.getPixel(j, i);
                Pixel left = copy.getPixel(j-1, i);
                Pixel bottom = copy.getPixel(j, i+1);

                int oldAvg = (old.getRed() + old.getBlue() + old.getGreen()) / 3;
                int leftAvg = (left.getRed() + left.getGreen() + left.getBlue())/ 3;
                int bottomAvg = (bottom.getRed() + bottom.getGreen() + bottom.getBlue()) / 3;

                Pixel newPixel = image.getPixel(j, i);
                if(Math.abs(oldAvg - leftAvg) <= threshold || Math.abs(oldAvg - bottomAvg) <= threshold){
                    newPixel.setRed(Math.min(255, 255));
                    newPixel.setBlue(Math.min(255, 255));
                    newPixel.setGreen(Math.min(255, 255));
                }
            }
        }
    }

    public void blur(){
        // row
        for (int i = 1; i < image.getHeight()-1; i++) {
            // column
            for (int j = 1; j < image.getWidth()-1; j++) {
                //                    col, row
                Pixel p = image.getPixel(j, i);

                Pixel above = image.getPixel(j, i-1);
                Pixel below = image.getPixel(j, i+1);
                Pixel left = image.getPixel(j-1, i);
                Pixel right = image.getPixel(j+1, i);

                int redAvg = (above.getRed() + below.getRed() + left.getRed() + right.getRed()) / 4;
                int greenAvg = (above.getGreen() + below.getGreen() + left.getGreen() + right.getGreen()) / 4;
                int blueAvg = (above.getBlue() + below.getBlue() + left.getBlue() + right.getBlue())/4;

                p.setRed(redAvg);
                p.setBlue(greenAvg);
                p.setGreen(blueAvg);
            }
        }
    }
}
