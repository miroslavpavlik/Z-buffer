package render;

import model.Vertex;
import raster.ZBuffer;
import transforms.Vec2D;

public class TriangleRasterizer {

    public TriangleRasterizer(ZBuffer buffer) {
        this.buffer = buffer;
    }

    private final ZBuffer buffer;

    public void rasterize(Vertex a, Vertex b, Vertex c) {
        Vec2D pa = viewport(a);
        Vec2D pb = viewport(b);
        Vec2D pc = viewport(c);

        // Sort vertices by y-coordinate
        if (pa.getY() > pb.getY()) { Vec2D temp = pa; pa = pb; pb = temp; Vertex vtemp = a; a = b; b = vtemp; }
        if (pb.getY() > pc.getY()) { Vec2D temp = pb; pb = pc; pc = temp; Vertex vtemp = b; b = c; c = vtemp; }
        if (pa.getY() > pb.getY()) { Vec2D temp = pa; pa = pb; pb = temp; Vertex vtemp = a; a = b; b = vtemp; }

        // First half of the triangle
        for (int y = Math.max((int) pa.getY(), 0); y < Math.min(pb.getY(), buffer.getImageBuffer().getHeight() - 1); y++) {
            double t1 = (y - pa.getY()) / (pb.getY() - pa.getY());
            double t2 = (y - pa.getY()) / (pc.getY() - pa.getY());
            double x1 = (1 - t1) * pa.getX() + t1 * pb.getX();
            double x2 = (1 - t2) * pa.getX() + t2 * pc.getX();
            Vertex ab = a.mul(1 - t1).add(b.mul(t1));
            Vertex ac = a.mul(1 - t2).add(c.mul(t2));
            if (x1 > x2) { double tmp = x1; x1 = x2; x2 = tmp; Vertex vtmp = ab; ab = ac; ac = vtmp; }
            for (int x = Math.max((int) x1, 0); x < Math.min(x2, buffer.getImageBuffer().getWidth() - 1); x++) {
                double t3 = (x - x1) / (x2 - x1);
                Vertex abc = ab.mul(1 - t3).add(ac.mul(t3));
                buffer.drawPixelZTest(x, y, abc.getPosition().getZ(), abc.getCol());
            }
        }

        // Second half of the triangle
        for (int y = Math.max((int) pb.getY(), 0); y < Math.min(pc.getY(), buffer.getImageBuffer().getHeight() - 1); y++) {
            double t1 = (y - pb.getY()) / (pc.getY() - pb.getY());
            double t2 = (y - pa.getY()) / (pc.getY() - pa.getY());
            double x1 = (1 - t1) * pb.getX() + t1 * pc.getX();
            double x2 = (1 - t2) * pa.getX() + t2 * pc.getX();
            Vertex bc = b.mul(1 - t1).add(c.mul(t1));
            Vertex ac = a.mul(1 - t2).add(c.mul(t2));
            if (x1 > x2) { double tmp = x1; x1 = x2; x2 = tmp; Vertex vtmp = bc; bc = ac; ac = vtmp; }
            for (int x = Math.max((int) x1, 0); x < Math.min(x2, buffer.getImageBuffer().getWidth() - 1); x++) {
                double t3 = (x - x1) / (x2 - x1);
                Vertex abc = bc.mul(1 - t3).add(ac.mul(t3));
                buffer.drawPixelZTest(x, y, abc.getPosition().getZ(), abc.getCol());
            }
        }
    }

    public Vec2D viewport(Vertex vertex) {
        Vec2D xy = new Vec2D(vertex.getPosition().getX(), vertex.getPosition().getY());
        return xy.mul(new Vec2D(1, -1)).add(new Vec2D(1, 1))
                .mul(new Vec2D((buffer.getImageBuffer().getWidth() - 1) / 2.,
                        (buffer.getImageBuffer().getHeight() - 1) / 2.));
    }
}