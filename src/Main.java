import images.APImage;

public class Main {
    public static void main(String[] args) {

        APImage image = new APImage("smokey.jpg");
        image.draw();

        ImageProcessor ip = new ImageProcessor(image);
        //ip.toGrayScale();

        //ip.posterize(37, 86, 48, 10, 180, 14);
        //ip.toPhotoNeg();
        //ip.colorFilter(80, 0, 0);
        //ip.sharpen(255, 10);
        ip.toOldFashioned();
        image.draw();
    }
}