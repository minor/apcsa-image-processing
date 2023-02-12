import images.*;


public class ImageProcessor {
    private APImage image;

    /**
     * ImageProcessor class constructor.
     *
     * @param image The APImage object that the class will process.
     */
    public ImageProcessor(APImage image) {
        this.image = image.clone();
    }

    /**
     * Converts the image to black and white.
     */
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

    /**
     * Converts the image to grayscale.
     */
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

    /**
     * Converts the image to grayscale using the luminance method.
     */
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

    /**
     * turns the image counterclockwise by a specified angle
     * @param angle How the far the image is turned (counterclockwise)
     * precondition: angle is 90 (left), 180 (flip), -90 (right)
     */
    public void rotateImage(int angle) {
        // - 90 = left, 90 = right, 180 = flip
        int turns;

        if(angle == 90) turns = 1;
        else if(angle == 180) turns = 2;
        else turns = 3;

        for(int t = 0; t < turns; t++){

            int h = image.getHeight();
            int w = image.getWidth();

            APImage turned = new APImage(h, w);

            for (int i = 0; i < w; i++) {

                for (int j = 1; j < h; j++) {
                    Pixel res = turned.getPixel(j, w-1-i);
                    Pixel og = image.getPixel(i, j);

                    res.setRed(og.getRed());
                    res.setGreen(og.getGreen());
                    res.setBlue(og.getBlue());
                }
            }
            image = turned;
        }

    }

    /**
     * Converts the image to an "old-fashioned" look by converting pixels
     * to grayscale and transforming them to get a sepia effect
=======
    /**
     * Converts the image to an "old-fashioned" look.
>>>>>>> 2a171547954305da845dea1638c1f90dd3d43981
     */
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
                p.setBlue(blue);
            }
        }
    }

    /**
     * Adjusts the brightness of the image.
     * @param val Specifies if the image should be darkened or brightened
     * @param magnitude Specifies the amount of adjustment to be made to the brightness
     */
    public void toDarkOrBright(String val, int magnitude) {
        // Loop through each row of the image
        for (int i = 0; i < image.getHeight(); i++) {
            // Loop through each column of the image
            for (int j = 0; j < image.getWidth(); j++) {
                // Get the Pixel at the current row and column
                Pixel p = image.getPixel(j, i);

                // Get the red, green, and blue values of the Pixel
                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();

                // If the value is "darken", darken the Pixel
                if (val.equals("darken")) {
                    p.setRed(Math.max(0, red - magnitude));
                    p.setGreen(Math.max(0, green - magnitude));
                    p.setBlue(Math.max(0, blue - magnitude));
                }
                // If the value is "brighten", brighten the Pixel
                else if (val.equals("brighten")) {
                    p.setRed(Math.min(255, red + magnitude));
                    p.setGreen(Math.min(255, green + magnitude));
                    p.setBlue(Math.min(255, blue + magnitude));
                }
            }
        }
    }

    /**
     * Applies a color filter to the image
     * @param r The amount to adjust the red value of each Pixel
     * @param g The amount to adjust the green value of each Pixel
     * @param b The amount to adjust the blue value of each Pixel
     */
    public void colorFilter(int r, int g, int b){
        // Loop through each row of the image
        for (int i = 0; i < image.getHeight(); i++) {
            // Loop through each column of the image
            for (int j = 0; j < image.getWidth(); j++) {

                // gets Pixel at row and column
                // Get the Pixel at the current row and column
                Pixel p = image.getPixel(j, i);

                // Get the red, green, and blue values of the Pixel
                int red = Math.min(p.getRed() + r, 255);
                int green = Math.min(p.getGreen() + g, 255);
                int blue = Math.min(p.getBlue() + b, 255);

                // Set the red, green, and blue values of the Pixel
                p.setRed(red);
                p.setGreen(green);
                p.setBlue(blue);
            }
        }
    }

    /**
     * This method takes in two sets of RGB values and performs a posterization effect on an image.
     * The average RGB values of the two sets of RGB values are calculated, and for each pixel in the image,
     * its average RGB value is compared to the average RGB values of the two sets of RGB values.
     * If the pixel's average RGB value is closer to the first set of RGB values, the pixel is set to that value,
     * otherwise it is set to the second set of RGB values.
     *
     * @param r1 Red value of the first set of RGB values
     * @param g1 Green value of the first set of RGB values
     * @param b1 Blue value of the first set of RGB values
     * @param r2 Red value of the second set of RGB values
     * @param g2 Green value of the second set of RGB values
     * @param b2 Blue value of the second set of RGB values
     */
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

    /**
     * This method converts the image to its photo negative.
     * The method first calls the toGrayScale method, which converts the image to grayscale,
     * then it inverts the RGB values of each pixel, by subtracting it from 255.
     */
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

    /**
     * This method applies a sharpening effect to the image by analyzing the difference between a pixel and its neighbors.
<<<<<<< HEAD
     * This method first creates a blank image to write on, before iterating through pixels of the original image.
     * If the difference between the current (original) pixel and its neighbors is less than or equal to the specified threshold,
     * then the color values of the current (blank) pixel are set to the same balue as the current (original) pixel. If
     * the threshold is exceeding, a sharpening effect is added to the current (original) pixel and copied onto the blank one.
=======
     * If the difference between the current pixel and its neighbors is less than or equal to the specified threshold,
     * then the color values of the current pixel are set to the maximum color value (255).
>>>>>>> 2a171547954305da845dea1638c1f90dd3d43981
     *
     * @param sharpness The level of sharpness to be applied to the image.
     * @param threshold The difference threshold used to determine if a pixel should be sharpened.
     */
    public void sharpen(int sharpness, int threshold){

        int w = image.getWidth();
        int h = image.getHeight();

        APImage result = new APImage(w, h);
        // row
        for (int i = 0; i < h-1; i++) {
            // column
            for (int j = 1; j < w; j++) {
                //                    col, row
                Pixel old = image.getPixel(j, i);
                Pixel left = image.getPixel(j-1, i);
                Pixel bottom = image.getPixel(j, i+1);

                int oldAvg = (old.getRed() + old.getBlue() + old.getGreen()) / 3;
                int leftAvg = (left.getRed() + left.getGreen() + left.getBlue())/ 3;
                int bottomAvg = (bottom.getRed() + bottom.getGreen() + bottom.getBlue()) / 3;

                Pixel newPixel = result.getPixel(j, i);
                if(Math.abs(oldAvg - leftAvg) <= threshold || Math.abs(oldAvg - bottomAvg) <= threshold){
                    newPixel.setRed(old.getRed());
                    newPixel.setGreen(old.getGreen());
                    newPixel.setBlue(old.getBlue());
                }
                else {
                    newPixel.setRed(Math.max(0, old.getRed() - sharpness));
                    newPixel.setBlue(Math.max(0, old.getBlue() - sharpness));
                    newPixel.setGreen(Math.max(0, old.getGreen() - sharpness));
                }

            }
        }
        image = result;
    }

    /**
     * This method applies a blur effect to the image by taking the average of a pixel and its neighbors and setting
     * the color values of the current pixel to that average.
<<<<<<< HEAD
=======
     *
>>>>>>> 2a171547954305da845dea1638c1f90dd3d43981
     */
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
                p.setGreen(greenAvg);
                p.setBlue(blueAvg);
            }
        }
    }

    /**
     * Shrinks an APImage object by a given factor.
     *
     * @param factor - the factor by which the image should be shrunken.
     */
    public void shrink(int factor) {
        // call method once to get height and width
        int height = image.getHeight();
        int width = image.getWidth();

        APImage shrunkenImage = new APImage(width/factor, height/factor);

        int counterY = 0;
        int counterX = 0;
        for (int i = 0; i < height-factor; i+= factor) {
            counterX = 0;
            for (int j = 1; j < width-factor; j+= factor) {
                Pixel currentPixel = image.getPixel(j , i);
                shrunkenImage.setPixel(counterX, counterY, currentPixel);
                counterX++;
            }
            counterY++;
        }
        this.image = shrunkenImage;
    }

    /**
     * Enlarges an APImage object by a given factor.
     *
     * @param factor - the factor by which the image should be enlarged.
     */
    public void enlarge(int factor) {
        // call method once to get height and width
        int height = image.getHeight();
        int width = image.getWidth();

        APImage enlargedImage = new APImage(width * factor, height * factor);

        int counterY = 0;
        int counterX = 0;
        for (int i = 0; counterY < height; i+= factor) {
            for (int j = 0; counterX < width; j++) {
                Pixel currentPixel = image.getPixel(counterX , counterY);
                enlargedImage.setPixel(j, i, currentPixel);
                if (j % factor == 0) {
                    counterX++;
                }
            }
            if (i % factor == 0) {
                counterY++;
            }
            counterX = 0;
        }
        this.image = enlargedImage;
    }

    /**
     * accesses image instance variable
     * @return image object
     */
    public APImage getImage(){return this.image;}
}
