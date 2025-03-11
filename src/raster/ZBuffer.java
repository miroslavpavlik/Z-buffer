package raster;

import transforms.Col;

import java.awt.image.BufferedImage;

/**
 * @author KŠ
 * 11.02.2025 9:04
 */

public class ZBuffer {
    private final ImageBuffer iBuffer;
    private final DepthBuffer dBuffer;

    public ZBuffer(ImageBuffer iBuffer) {
        this.iBuffer = iBuffer;
        this.dBuffer = new DepthBuffer(iBuffer.getWidth(), iBuffer.getHeight());
    }

    /**
     * @param x souradnice x
     * @param y souradnice y
     * @param z hloubka
     * @param color barva pixelu
     */
    public void drawPixelZTest(int x, int y, double z, Col color) {
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
        // TODO: doimplementovat
    }
}
