package render;

import raster.ZBuffer;
import model.Vertex;

public class LineRasterizer {

    private final ZBuffer buffer;

    public LineRasterizer(ZBuffer buffer) {
        this.buffer = buffer;
    }

    // Algoritmus pro rasterizaci čáry podle Bresenhama
    public void rasterize(Vertex start, Vertex end) {
        int x1 = (int) start.getPosition().getX();
        int y1 = (int) start.getPosition().getY();
        int x2 = (int) end.getPosition().getX();
        int y2 = (int) end.getPosition().getY();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            buffer.drawPixelZTest(x1, y1, start.getPosition().getZ(), start.getCol());

            if (x1 == x2 && y1 == y2) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}
