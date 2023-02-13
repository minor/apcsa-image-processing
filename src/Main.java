import images.APImage;
import java.util.Scanner;

public class Main {

    /**
     * uses ImageProcessor and UserInputProcessor classes to allow users to choose
     * and modify images using multiple methods
     * @param args terminal input
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        UserInputProcessor uiProcessor = new UserInputProcessor(input);

        String file = uiProcessor.promptFile();

        // creates image from filename
        APImage image = new APImage(file);
        // creates new ImageProcessor object from image
        ImageProcessor ip = new ImageProcessor(image);

        // allows uiProcessorObject to modify image
        uiProcessor.setImageProcessor(ip);

        int userEntry;

        // user will have an option to modify until they exit by
        // entering 0
        do {
            // stores user input to terminal prompt
            userEntry = uiProcessor.chooseImageModificaton();

            // use switch statement for selection of image modification method based
            // on the user's input integer. prints verification of modification to terminal
            switch (userEntry){
                // converts image to black and white
                case 1:
                    uiProcessor.promptBlackandWhite();
                    break;
                // converts image to grayscale
                case 2:
                    uiProcessor.promptGrayscale();
                    break;
                // converts image to grayscale
                case 3:
                    uiProcessor.promptLuminanceGrayscale();
                    break;
                // rotates image based on user input
                case 4:
                    uiProcessor.promptRotation();
                    break;
                // converts image to old-fashioned
                case 5:
                    uiProcessor.promptOldFashioned();
                    break;
                // converts image to darken or brighten based on user input
                case 6:
                    uiProcessor.promptDarkenorBrighten();
                    break;
                // converts image based on color filter from user input
                case 7:
                    uiProcessor.promptColorFilter();
                    break;
                // posterizes image based on rgb colors from user input
                case 8:
                    uiProcessor.promptPosterize();
                    break;
                // converts image to photo negative
                case 9:
                    uiProcessor.promptPhotoNegative();
                    break;
                // sharpens image based on user input
                case 10:
                    uiProcessor.promptSharpen();
                    break;
               // blurs image
                case 11:
                    uiProcessor.promptBlur();
                    break;
                // shrinks image based on user-inputted factor
                case 12:
                    uiProcessor.promptShrink();
                    break;
                // enlarged image based on user-inputted factor
                case 13:
                    uiProcessor.promptEnlarge();
                    break;
                // changes image to be modified
                case 14:
                    uiProcessor.promptImageChange();
                    break;
            }

        } while(userEntry != -1);

        // prints resulting and original image
        image.draw();
        ip.getImage().draw();
    }


}