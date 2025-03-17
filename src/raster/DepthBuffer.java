package raster;

public class DepthBuffer implements Raster<Double> {
    private final double[][] buffer;
    private final int width, height;
    private double defaultValue;

    public DepthBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = new double[width][height];
        this.defaultValue = 1.0d;
        clear();
    }

    @Override
    public void clear() {
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                buffer[x][y] = defaultValue;
            }
        }
    }

    @Override
    public void setClearColor(Double element) {
        this.defaultValue = element;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Double getPixel(int x, int y) {
        if(checkBorders(x, y)){
            return buffer[x][y];
        }
        return null;
    }

    @Override
    public void setPixel(int x, int y, Double element) {
       if(checkBorders(x, y)){
           buffer[x][y] = element;
       }
    }
}
