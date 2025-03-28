package raster;

import transforms.Col;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBuffer implements Raster<Col> {

    private final BufferedImage img;
    private Col color;

    public BufferedImage getImg() {
        return img;
    }

    public ImageBuffer(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void repaint(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public void draw(ImageBuffer raster) {
        Graphics graphics = getGraphics();
        graphics.setColor(new Color(color.getRGB()));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(raster.img, 0, 0, null);
    }

    public Graphics getGraphics(){
        return img.getGraphics();
    }

    @Override
    public Col getPixel(int x, int y) {
        return new Col(img.getRGB(x, y));
    }

    @Override
    public void setPixel(int x, int y, Col element) {
        img.setRGB(x, y, element.getRGB());
    }

    @Override
    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(new Color(color.getRGB()));
        g.clearRect(0, 0, img.getWidth(), img.getHeight());
    }

    @Override
    public void setClearColor(Col color) {
        this.color = color;
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

}

