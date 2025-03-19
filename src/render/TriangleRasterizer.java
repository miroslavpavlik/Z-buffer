package render;

import model.Lerp;
import model.Vertex;
import raster.ZBuffer;
import shade.FragmentShader;
import transforms.Col;
import transforms.Vec2D;

public class TriangleRasterizer {
    private final ZBuffer buffer;
    private final FragmentShader shader;
    private final Lerp<Vertex> lerp = new Lerp<>();

    public TriangleRasterizer(ZBuffer buffer, FragmentShader shader) {
        this.buffer = buffer;
        this.shader = shader;
    }

    public void rasterize(Vertex a, Vertex b, Vertex c) {
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

        for (double y = pa.getY(); y <= pb.getY(); y++) {
            double t1 = (y - pa.getY()) / (pb.getY() - pa.getY());
            double t2 = (y - pa.getY()) / (pc.getY() - pa.getY());

            double x1 = (1 - t1) * pa.getX() + t1 * pb.getX();
            double x2 = (1 - t2) * pa.getX() + t2 * pc.getX();

            Vertex ab = lerp.lerp(a, b, t1);
            Vertex ac = lerp.lerp(a, c, t2);

            if (x1 > x2) {
                double tempX = x1; x1 = x2; x2 = tempX;
                Vertex tempV = ab; ab = ac; ac = tempV;
            }

            for (int x = (int) x1; x <= (int) x2; x++) {
                double t3 = (x - x1) / (x2 - x1);
                Vertex abc = lerp.lerp(ab, ac, t3);
                Col color = shader.getColor(abc);
                buffer.drawPixelZTest(x, (int)y, abc.getPosition().getZ(), color);
            }
        }

        for (double y = pb.getY(); y <= pc.getY(); y++) {
            double t1 = (y - pb.getY()) / (pc.getY() - pb.getY());
            double t2 = (y - pa.getY()) / (pc.getY() - pa.getY());

            double x1 = (1 - t1) * pb.getX() + t1 * pc.getX();
            double x2 = (1 - t2) * pa.getX() + t2 * pc.getX();

            Vertex bc = lerp.lerp(b, c, t1);
            Vertex ac = lerp.lerp(a, c, t2);

            if (x1 > x2) {
                double tempX = x1; x1 = x2; x2 = tempX;
                Vertex tempV = bc; bc = ac; ac = tempV;
            }

            for (int x = (int) x1; x <= (int) x2; x++) {
                double t3 = (x - x1) / (x2 - x1);
                Vertex abc = lerp.lerp(bc, ac, t3);
                Col color = shader.getColor(abc);
                buffer.drawPixelZTest(x, (int)y, abc.getPosition().getZ(), color);
            }
        }
    }
}
