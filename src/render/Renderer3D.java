package render;

import model.Lerp;
import model.Vertex;
import objects.Solid;
import raster.Part;
import raster.ZBuffer;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec3D;

import java.util.List;
import java.util.Optional;

public class Renderer3D {
    private final ZBuffer buffer;

    public Renderer3D(ZBuffer buffer) {
        this.buffer = buffer;
    }

    public void renderSolids(List<Solid> solids, Mat4 view, Mat4 projection, Boolean wiredModel) {
        for (Solid solid : solids) {
            render(solid, solid.getModel(), view, projection, wiredModel);
        }
    }

    public void render(Solid solid, Mat4 model, Mat4 view, Mat4 projection, Boolean wiredModel) {
        for (Part part : solid.getpB()) {
            int startIndex = part.getStartIndex();

            switch (part.getTopologyType()) {
                case LINES:
                    for (int i = 0; i < part.getCount(); i += 2) {
                        Vertex a = transformVertex(solid.getvB().get(solid.getiB().get(startIndex)), model, view, projection);
                        Vertex b = transformVertex(solid.getvB().get(solid.getiB().get(startIndex + 1)), model, view, projection);
                        startIndex += 2;

                        if (clipLine(a, b)) {
                            rasterizeLine(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(b)));
                        }
                    }
                    break;

                case TRIANGLES:
                    for (int i = 0; i < part.getCount(); i += 3) {
                        Vertex a = transformVertex(solid.getvB().get(solid.getiB().get(startIndex)), model, view, projection);
                        Vertex b = transformVertex(solid.getvB().get(solid.getiB().get(startIndex + 1)), model, view, projection);
                        Vertex c = transformVertex(solid.getvB().get(solid.getiB().get(startIndex + 2)), model, view, projection);
                        startIndex += 3;

                        clipTriangle(a, b, c, wiredModel);
                    }
                    break;
            }
        }
    }


    private Vertex transformVertex(Vertex vertex, Mat4 model, Mat4 view, Mat4 projection) {
        Point3D transformedPos = vertex.getPosition().mul(model).mul(view).mul(projection);
        return new Vertex(transformedPos, vertex.getCol());
    }

    private void clipTriangle(Vertex a, Vertex b, Vertex c, Boolean wiredModel) {
        if ((a.getPosition().getX() > a.getPosition().getW() && b.getPosition().getX() > b.getPosition().getW() && c.getPosition().getX() > c.getPosition().getW()) ||
                (a.getPosition().getX() < -a.getPosition().getW() && b.getPosition().getX() < -b.getPosition().getW() && c.getPosition().getX() < -c.getPosition().getW()) ||
                (a.getPosition().getY() > a.getPosition().getW() && b.getPosition().getY() > b.getPosition().getW() && c.getPosition().getY() > c.getPosition().getW()) ||
                (a.getPosition().getY() < -a.getPosition().getW() && b.getPosition().getY() < -b.getPosition().getW() && c.getPosition().getY() < -c.getPosition().getW()) ||
                (a.getPosition().getZ() > a.getPosition().getW() && b.getPosition().getZ() > b.getPosition().getW() && c.getPosition().getZ() > c.getPosition().getW()) ||
                (a.getPosition().getZ() < 0 && b.getPosition().getZ() < 0 && c.getPosition().getZ() < 0)) {
            return;
        }
        if (a.getPosition().getZ() < b.getPosition().getZ()) {
            Vertex temp = a;
            a = b;
            b = temp;
        }
        if (a.getPosition().getZ() < c.getPosition().getZ()) {
            Vertex temp = a;
            a = c;
            c = temp;
        }
        if (b.getPosition().getZ() < c.getPosition().getZ()) {
            Vertex temp = b;
            b = c;
            c = temp;
        }
        double zMin = 0.0;
        if (a.getPosition().getZ() < zMin) {
            return;
        }

        if (b.getPosition().getZ() < zMin) {
            double t1 = (zMin - a.getPosition().getZ()) / (b.getPosition().getZ() - a.getPosition().getZ());
            Vertex v1 = new Lerp<Vertex>().lerp(a, b, t1);

            double t2 = (zMin - a.getPosition().getZ()) / (c.getPosition().getZ() - a.getPosition().getZ());
            Vertex v2 = new Lerp<Vertex>().lerp(a, c, t2);
            if (wiredModel) {
                rasterizeLine(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(v1)));
                rasterizeLine(viewportTransform(dehomogenize(v1)), viewportTransform(dehomogenize(v2)));
                rasterizeLine(viewportTransform(dehomogenize(v2)), viewportTransform(dehomogenize(a)));
            } else {
                rasterizeTriangle(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(v1)), viewportTransform(dehomogenize(v2)));
            }
            return;
        }

        if (c.getPosition().getZ() < zMin) {
            double t1 = (zMin - a.getPosition().getZ()) / (c.getPosition().getZ() - a.getPosition().getZ());
            Vertex v1 = new Lerp<Vertex>().lerp(a, c, t1);

            double t2 = (zMin - b.getPosition().getZ()) / (c.getPosition().getZ() - b.getPosition().getZ());
            Vertex v2 = new Lerp<Vertex>().lerp(b, c, t2);

            if(wiredModel){
                rasterizeLine(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(b)));
                rasterizeLine(viewportTransform(dehomogenize(b)), viewportTransform(dehomogenize(v1)));
                rasterizeLine(viewportTransform(dehomogenize(v1)), viewportTransform(dehomogenize(a)));

                rasterizeLine(viewportTransform(dehomogenize(b)), viewportTransform(dehomogenize(v1)));
                rasterizeLine(viewportTransform(dehomogenize(v1)), viewportTransform(dehomogenize(v2)));
                rasterizeLine(viewportTransform(dehomogenize(v2)), viewportTransform(dehomogenize(b)));
            } else {
                rasterizeTriangle(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(b)), viewportTransform(dehomogenize(v1)));
                rasterizeTriangle(viewportTransform(dehomogenize(b)), viewportTransform(dehomogenize(v1)), viewportTransform(dehomogenize(v2)));
            }
            return;
        }
        if(wiredModel){
            rasterizeLine(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(b)));
            rasterizeLine(viewportTransform(dehomogenize(b)), viewportTransform(dehomogenize(c)));
            rasterizeLine(viewportTransform(dehomogenize(c)), viewportTransform(dehomogenize(a)));
        } else {
            rasterizeTriangle(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(b)), viewportTransform(dehomogenize(c)));
        }
    }

    private boolean clipLine(Vertex a, Vertex b) {
        if ((a.getPosition().getX() > a.getPosition().getW() && b.getPosition().getX() > b.getPosition().getW()) ||
                (a.getPosition().getX() < -a.getPosition().getW() && b.getPosition().getX() < -b.getPosition().getW()) ||
                (a.getPosition().getY() > a.getPosition().getW() && b.getPosition().getY() > b.getPosition().getW()) ||
                (a.getPosition().getY() < -a.getPosition().getW() && b.getPosition().getY() < -b.getPosition().getW()) ||
                (a.getPosition().getZ() > a.getPosition().getW() && b.getPosition().getZ() > b.getPosition().getW()) ||
                (a.getPosition().getZ() < 0 && b.getPosition().getZ() < 0)) {
            return false;
        }
        return true;
    }

    private Vertex dehomogenize(Vertex vertex) {
        Point3D pos = vertex.getPosition();
        Optional<Vec3D> dehomogenized = pos.dehomog();

        if (dehomogenized.isPresent()) {
            Vec3D dehomogVec = dehomogenized.get();
            return new Vertex(new Point3D(dehomogVec.getX(), dehomogVec.getY(), dehomogVec.getZ()), vertex.getCol());
        } else {
            return new Vertex(new Point3D(0, 0, 0), vertex.getCol());
        }
    }


    private Vertex viewportTransform(Vertex vertex) {
        double width = buffer.getImageBuffer().getWidth();
        double height = buffer.getImageBuffer().getHeight();

        double x = (vertex.getPosition().getX() + 1) * (width / 2);
        double y = (1 - vertex.getPosition().getY()) * (height / 2);

        return new Vertex(new Point3D(x, y, vertex.getPosition().getZ()), vertex.getCol());
    }


    private void rasterizeTriangle(Vertex a, Vertex b, Vertex c) {
        new TriangleRasterizer(buffer).rasterize(a, b, c);
    }

    private void rasterizeLine(Vertex a, Vertex b) {
        new LineRasterizer(buffer).rasterize(a, b);
    }
}