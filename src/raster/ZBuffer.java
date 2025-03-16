package raster;

import transforms.Col;

import java.awt.image.BufferedImage;

public class ZBuffer {
    private final ImageBuffer iBuffer;
    private final DepthBuffer dBuffer;

    public ZBuffer(ImageBuffer iBuffer) {
        this.iBuffer = iBuffer;
        this.dBuffer = new DepthBuffer(iBuffer.getWidth(), iBuffer.getHeight());
    }

    public void drawPixelZTest(int x, int y, double z, Col color) {
        if(x < 0 || x >= iBuffer.getWidth() || y < 0 || y >= iBuffer.getHeight()) {
            return;
        }
        double currentZ = dBuffer.getPixel(x, y);
        if(z < currentZ) {
            iBuffer.setPixel(x, y, color);
            dBuffer.setPixel(x, y, z);
        }
        // 1. Overim, zda souradnice (x, y) lezi uvnitr platne oblasti obrazovky
        // 2. Ziskame hodnotu Z-Bufferu na danych souradnicich
        // 3. testujeme, zda je novy prvek blize ke kamere nez aktualni hodnotav Z-Bufferu
        // 4. aktualizujeme barvu pixelu = setPixel v iBufferu
        // 5. ulozime novu hodnotu z-Bufferu = setPixel v dBufferu
    }

    public BufferedImage getImageBuffer() {
        return iBuffer.getImg();
    }

    public DepthBuffer getDepthBuffer() {
        return dBuffer;
    }

    public void clear() {
        iBuffer.clear();
        dBuffer.clear();
    }
}
