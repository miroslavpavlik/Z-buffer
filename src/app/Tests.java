package app;

import raster.ImageBuffer;
import raster.ZBuffer;
import transforms.Col;

import java.util.Random;

public class Tests {
    public static void testVisibility(ImageBuffer img) {
        ZBuffer zBuffer = new ZBuffer(img);
        zBuffer.clear();

        int x = new Random(123).nextInt(img.getWidth());
        int y = new Random(456).nextInt(img.getHeight());

        System.out.println("Size of ImageBuffer: " + zBuffer.getImageBuffer().getWidth() + "x" +
                zBuffer.getImageBuffer().getHeight());
        System.out.println("Size of DepthBuffer: " + zBuffer.getDepthBuffer().getWidth() + "x" +
                zBuffer.getImageBuffer().getHeight());
        System.out.println("Read default value from ImageBuffer [" + x + "," + y + "] :"
                + zBuffer.getImageBuffer().getRGB(x, y));
        System.out.println("Read default value from DepthBuffer [" + x + "," + y + "] :"
                + zBuffer.getDepthBuffer().getPixel(x, y));

        double depth_value = 0.5;
        Col color = new Col(255, 0, 0);
        System.out.println("\nSet pixel depth value " + depth_value + " with color " + color);
        zBuffer.drawPixelZTest(x, y, depth_value, color);
        System.out.println("Read default value from ImageBuffer [" + x + "," + y + "] :"
                + zBuffer.getImageBuffer().getRGB(x, y));
        System.out.println("Read default value from DepthBuffer [" + x + "," + y + "] :"
                + zBuffer.getDepthBuffer().getPixel(x, y));

        depth_value = 0.8;
        color = new Col(0, 255, 0);
        System.out.println("\nSet pixel depth value " + depth_value + " with color " + color);
        zBuffer.drawPixelZTest(x, y, depth_value, color);
        System.out.println("Read default value from ImageBuffer [" + x + "," + y + "] :"
                + zBuffer.getImageBuffer().getRGB(x, y));
        System.out.println("Read default value from DepthBuffer [" + x + "," + y + "] :"
                + zBuffer.getDepthBuffer().getPixel(x, y));

        depth_value = 0.3;
        color = new Col(0, 0, 255);
        System.out.println("\nSet pixel depth value " + depth_value + " with color " + color);
        zBuffer.drawPixelZTest(x, y, depth_value, color);
        System.out.println("Read default value from ImageBuffer [" + x + "," + y + "] :"
                + zBuffer.getImageBuffer().getRGB(x, y));
        System.out.println("Read default value from DepthBuffer [" + x + "," + y + "] :"
                + zBuffer.getDepthBuffer().getPixel(x, y));
    }

    public static void main(String[] args) {
        ImageBuffer img = new ImageBuffer(800, 600);
        img.setClearColor(new Col(0, 0, 0)); // Nastavení barvy pozadí
        testVisibility(img);
    }
}
