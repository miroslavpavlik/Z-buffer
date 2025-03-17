package render;
import model.Vertex;
import raster.ZBuffer;
import transforms.Vec2D;

public class TriangleRasterizer {
    private final ZBuffer buffer;

    public TriangleRasterizer(ZBuffer buffer) {
        this.buffer = buffer;
    }

    public void rasterize(Vertex a, Vertex b, Vertex c) {
        // Seřazení vrcholů podle y hodnoty
        Vec2D pa = new Vec2D(a.getPosition().getX(), a.getPosition().getY());
        Vec2D pb = new Vec2D(b.getPosition().getX(), b.getPosition().getY());
        Vec2D pc = new Vec2D(c.getPosition().getX(), c.getPosition().getY());

        if (pa.getY() > pb.getY()) {
            Vec2D tmp = pa; pa = pb; pb = tmp;
            Vertex tempV = a; a = b; b = tempV;
        }
        if (pa.getY() > pc.getY()) {
            Vec2D temp = pa; pa = pc; pc = temp;
            Vertex tempV = a; a = c; c = tempV;
        }
        if (pb.getY() > pc.getY()) {
            Vec2D temp = pb; pb = pc; pc = temp;
            Vertex tempV = b; b = c; c = tempV;
        }

        // První polovina trojúhelníku (A -> B)
        for (int y = (int) pa.getY(); y <= (int) pb.getY(); y++) {
            double t1 = (y - pa.getY()) / (pb.getY() - pa.getY());
            double t2 = (y - pa.getY()) / (pc.getY() - pa.getY());

            double x1 = (1 - t1) * pa.getX() + t1 * pb.getX();
            double x2 = (1 - t2) * pa.getX() + t2 * pc.getX();

            // Interpolace pro barvy a z-souřadnice
            Vertex ab = a.mul(1 - t1).add(b.mul(t1));
            Vertex ac = a.mul(1 - t2).add(c.mul(t2));

            if (x1 > x2) {
                double tempX = x1; x1 = x2; x2 = tempX;
                Vertex tempV = ab; ab = ac; ac = tempV;
            }

            for (int x = (int) x1; x <= (int) x2; x++) {
                double t3 = (x - x1) / (x2 - x1);
                Vertex abc = ab.mul(1 - t3).add(ac.mul(t3));
                buffer.drawPixelZTest(x, y, abc.getPosition().getZ(), abc.getCol());
            }
        }

        // Druhá polovina trojúhelníku (B -> C)
        for (int y = (int) pb.getY(); y <= (int) pc.getY(); y++) {
            double t1 = (y - pb.getY()) / (pc.getY() - pb.getY());
            double t2 = (y - pa.getY()) / (pc.getY() - pa.getY());

            double x1 = (1 - t1) * pb.getX() + t1 * pc.getX();
            double x2 = (1 - t2) * pa.getX() + t2 * pc.getX();

            // Interpolace pro barvy a z-souřadnice
            Vertex bc = b.mul(1 - t1).add(c.mul(t1));
            Vertex ac = a.mul(1 - t2).add(c.mul(t2));

            if (x1 > x2) {
                double tempX = x1; x1 = x2; x2 = tempX;
                Vertex tempV = bc; bc = ac; ac = tempV;
            }

            for (int x = (int) x1; x <= (int) x2; x++) {
                double t3 = (x - x1) / (x2 - x1);
                Vertex abc = bc.mul(1 - t3).add(ac.mul(t3));
                buffer.drawPixelZTest(x, y, abc.getPosition().getZ(), abc.getCol());
            }
        }
    }
}
