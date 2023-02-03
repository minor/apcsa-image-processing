import images.*;

public class ImageProcessor {
    private APImage image;

    public ImageProcessor(APImage image){
        this.image = image;
        //this.image = new APImage(image.getName());
    }

    public void toBW(){
        // row
        for(int i = 0; i < image.getHeight(); i++){
            // column
            for(int j = 0; j < image.getWidth(); j++){
                //                    col, row
                Pixel p = image.getPixel(j, i);

                int red = p.getRed();
                int green = p.getGreen();
                int blue = p.getBlue();

                int avg = (red + green + blue) / 3;

                if(avg < 128){
                    p.setRed(0);
                    p.setGreen(0);
                    p.setBlue(0);
                }
                else {
                    p.setRed(255);
                    p.setGreen(255);
                    p.setBlue(255);
                }
            }
        }
    }

    public void toGrayScale(){
        // row
        for(int i = 0; i < image.getHeight(); i++){
            // column
            for(int j = 0; j < image.getWidth(); j++){
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
}
