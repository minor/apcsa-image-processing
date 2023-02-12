import images.APImage;

public class Main {
    public static void main(String[] args) {

        APImage image = new APImage("smokey.jpg");

        ImageProcessor ip = new ImageProcessor(image);
        //ip.toGrayScale();

        //ip.posterize(37, 86, 48, 10, 180, 14);
        //ip.toPhotoNeg();
        //ip.colorFilter(20, 0, 0);
        ip.toBW();
        ip.blur();
        ip.blur();
        ip.getImage().draw();
        image.draw();

        System.out.println(ip.getImage().equals(image));
    }
}