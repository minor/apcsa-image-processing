import images.APImage;

public class Main {
    public static void main(String[] args) {
        APImage image = new APImage("swan.jpg");
        image.draw();

        ImageProcessor ip = new ImageProcessor(image);
        ip.toGrayScale();
        image.draw();

        ip.toBW();
        image.draw();
    }
}