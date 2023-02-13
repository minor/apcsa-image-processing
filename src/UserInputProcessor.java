import images.APImage;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputProcessor {

    private Scanner input;
    private ImageProcessor ip;
    /**
     * Creates a new UserInputProcessor
     * @param in a Scanner for user input
     */
    public UserInputProcessor(Scanner in){
        input = in;
    }

    /**
     * Sets the ImageProcessor to use for modification
     * @param ip an ImageProcessor for image modification
     */
    public void setImageProcessor(ImageProcessor ip){
        this.ip = ip;
    }

    /**
     * Prompts the user for the name of the image file to modify
     * @return a string containing the name of the image file to modify
     */
    public String promptFile(){
        System.out.print("Enter the filename of an image or none to use a default image: ");
        String file = input.next();

        // checks if the filename exists in directory
        // if the file doesn't exist, use default "smokey.jpg"
        File f = new File(file);
        if(!f.exists()){
            file = "smokey.jpg";
        }
        return file;
    }

    /**
     * Prompts the user to choose a modification to apply to the image
     * @return an integer corresponding to the selected modification
     */
    public int chooseImageModificaton(){
        int userEntry;
        System.out.println("Enter the number corresponding to the modification " +
                "you want to make to the image: ");

        System.out.println("1: to black and white");
        System.out.println("2: to grayscale");
        System.out.println("3: to luminance grayscale");
        System.out.println("4: image rotation");
        System.out.println("5: to old fashioned");
        System.out.println("6: to darken or brighten");
        System.out.println("7: color filter");
        System.out.println("8: posterize");
        System.out.println("9: to photographic negative");
        System.out.println("10: sharpen");
        System.out.println("11: blur");
        System.out.println("12: shrink");
        System.out.println("13: enlarge");
        System.out.println("14: change image");
        System.out.println("-1: exit and view resulting image");

        // if user input isn't valid, gives option for the user to try again
        try {
            userEntry = input.nextInt();
            if(userEntry > 14) throw new InputMismatchException();
        } catch (InputMismatchException i){
            System.out.println("Sorry, that input is invalid, try again.");
            userEntry = 15;
        }

        return userEntry;
    }

    /**
     * Converts the image to black and white
     */
    public void promptBlackandWhite(){
        ip.toBW();

        System.out.println("The image has been converted to black and white.");
        System.out.println();
    }

    /**
     * Converts the image to grayscale
     */
    public void promptGrayscale(){
        ip.toGrayScale();

        System.out.println();
        System.out.println("This image has been converted to grayscale.");
        System.out.println();
    }

    /**
     * Converts the image to luminance grayscale
     */
    public void promptLuminanceGrayscale(){
        ip.toLuminanceGrayScale();

        System.out.println();
        System.out.println("This image has now been converted to luminance grayscale.");
        System.out.println();
    }

    /**
     * Prompts the user to choose a rotation and rotates the image accordingly
     */
    public void promptRotation(){

        System.out.println();
        System.out.print("How should the image be rotated? (1) left ; (2) flip ; (3) right  ");
        int turns = input.nextInt();
        while(turns < 1 || turns > 3){
            System.out.print("Retry. How should the image be rotated? (1) left ; (2) flip ; (3) right  ");
            turns = input.nextInt();
        }

        ip.rotateImage(turns);
        System.out.println("This image has now been rotated.");
        System.out.println();
    }

    /**
     * Converts the image to an old-fashioned style.
     */
    public void promptOldFashioned(){
        ip.toOldFashioned();

        System.out.println();
        System.out.println("This image has now been converted to old-fashioned style.");
        System.out.println();
    }

    /**
     * Prompts the user to darken or brighten the image, and apply the specified magnitude
     * of darkness or brightness.
     */
    public void promptDarkenorBrighten(){
        System.out.println();
        System.out.println("Enter \"0\"  to darken or \"1\" to brighten  ");
        int userInput = input.nextInt();
        while(userInput != 0 && userInput != 1){
            System.out.println("Retry. Enter \"0\"  to darken or \"1\" to brighten  ");
            userInput = input.nextInt();
        }

        String mode = userInput == 0 ? "darken" : "brighten";

        System.out.println("Enter the magnitude of brightness/darkness: ");
        int magnitude = input.nextInt();

        ip.toDarkOrBright(mode, magnitude);

        System.out.println();
    }

    /**
     * Prompts the user to enter RGB values for a color filter, applies the filter to the image, and
     * prints a confirmation message.
     */
    public void promptColorFilter(){
        System.out.println();

        System.out.println("Enter each RGB value for the filter. Between 0 and 255  ");
        System.out.print("R: ");
        int r = input.nextInt();

        while(r < 0 || r > 255){
            System.out.print("Between 0 and 255.  ");
            r = input.nextInt();
        }

        System.out.print("G: ");
        int g = input.nextInt();

        while(g < 0 || g > 255){
            System.out.print("Between 0 and 255.  ");
            g = input.nextInt();
        }

        System.out.print("B: ");
        int b = input.nextInt();

        while(b < 0 || b > 255){
            System.out.print("Between 0 and 255.  ");
            b = input.nextInt();
        }

        ip.colorFilter(r, g, b);

        System.out.println("This color filter has been applied.");

        System.out.println();
    }

    /**
     * Prompts the user to enter RGB values for two different colors, posterizes the image with these colors,
     * and prints a confirmation message.
     */
    public void promptPosterize(){
        System.out.println();
        System.out.println("Enter the first RGB values");

        System.out.print("R: ");
        int r1 = input.nextInt();

        while(r1 < 0 || r1 > 255){
            System.out.print("Between 0 and 255.  ");
            r1 = input.nextInt();
        }

        System.out.print("G: ");
        int g1 = input.nextInt();

        while(g1 < 0 || g1 > 255){
            System.out.print("Between 0 and 255.  ");
            g1 = input.nextInt();
        }

        System.out.print("B: ");
        int b1 = input.nextInt();

        while(b1 < 0 || b1 > 255){
            System.out.print("Between 0 and 255.  ");
            b1 = input.nextInt();
        }

        System.out.println("Enter the second RGB values");
        System.out.print("R: ");
        int r2 = input.nextInt();

        while(r2 < 0 || r2 > 255){
            System.out.print("Between 0 and 255.  ");
            r2 = input.nextInt();
        }

        System.out.print("G: ");
        int g2 = input.nextInt();

        while(g2 < 0 || g2 > 255){
            System.out.print("Between 0 and 255.  ");
            g2 = input.nextInt();
        }

        System.out.print("B: ");
        int b2 = input.nextInt();

        while(b2 < 0 || b2 > 255){
            System.out.print("Between 0 and 255.  ");
            b2 = input.nextInt();
        }

        ip.posterize(r1, g1, b1, r2, g2, b2);
        System.out.println("This image has been posterized.");
        System.out.println();
    }

    /**
     * Converts the image to a photo negative and prints a confirmation message.
     */
    public void promptPhotoNegative(){
        ip.toPhotoNeg();

        System.out.println();
        System.out.println("The image has been converted to photo negative.");
        System.out.println();
    }

    /**
     * Prompts the user to enter the level of sharpness and the threshold to use for edge
     * detection, sharpens the image, and prints a confirmation message.
     */
    public void promptSharpen(){

        System.out.println();
        System.out.print("What level of sharpness should be applied? ");
        int sharpness = input.nextInt();

        System.out.println("What threshold should be used to detect edges? ");
        int threshold = input.nextInt();

        ip.sharpen(sharpness, threshold);
        System.out.println("This image has now been sharpened.");
        System.out.println();
    }

    /**
     * Blurs the image and prints a confirmation message.
     */
    public void promptBlur(){
        ip.blur();

        System.out.println();
        System.out.println("This image has now been blurred");
        System.out.println();
    }

    /**
     * Prompts the user to enter an integer factor, shrinks the image by that factor,
     * and prints a confirmation message.
     */
    public void promptShrink(){

        System.out.println();
        System.out.println("What (integer) factor should the image be shrunk by? ");
        int factor = input.nextInt();
        ip.shrink(factor);
        System.out.println("The image has been shrunk.");
        System.out.println();
    }

    /**
     * Prompts the user to enter an integer factor, enlarges the image by that factor, and
     * prints a confirmation message.
     */
    public void promptEnlarge(){

        System.out.println();
        System.out.println("What (integer) factor should the image be enlarged by? ");
        int factor = input.nextInt();
        ip.enlarge(factor);
        System.out.println("The image has been enlarged.");
        System.out.println();
    }

    /**
     * Prompts the user to select a new image file, replaces the current image with the new one,
     * and prints a confirmation message.
     */
    public void promptImageChange(){

        System.out.println();
        String imgFile = promptFile();
        ip = new ImageProcessor(new APImage(imgFile));
        System.out.println("The image to modify has been changed.");
        System.out.println();

    }

}
