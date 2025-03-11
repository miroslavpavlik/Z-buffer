package raster;

import java.nio.Buffer;

/**
 * @author KŠ
 * 11.02.2025 8:59
 */

public class DepthBuffer implements Raster<Double> {
    private final double[][] buffer;
    private final int width, height;
    private double defaultValue;

    public DepthBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = new double[width][height];
        this.defaultValue = 1.0d;
    }

    @Override
    public void clear() {
        // TODO: doimplementovat
    }

    @Override
    public void setClearColor(Double element) {
        // TODO: doimplementovat
    }

    @Override
    public int getWidth() {
        // TODO: doimplementovat
        return 0;
    }

    @Override
    public int getHeight() {
        // TODO: doimplementovat
        return 0;
    }

    @Override
    public Double getPixel(int x, int y) {
        // TODO: doimplementovat
        return 0.0;
    }

    @Override
    public void setPixel(int x, int y, Double element) {
        // TODO: doimplementovat
    }
}
