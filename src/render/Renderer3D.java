package render;

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

    public void renderSolids(List<Solid> solids, Mat4 model, Mat4 view, Mat4 projection) {
        for (Solid solid : solids) {
            render(solid, model, view, projection);
        }
    }

    public void render(Solid solid, Mat4 model, Mat4 view, Mat4 projection) {
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

                        if (clipTriangle(a, b, c)) {
                            rasterizeTriangle(viewportTransform(dehomogenize(a)), viewportTransform(dehomogenize(b)), viewportTransform(dehomogenize(c)));
                        }
                    }
                    break;
            }
        }
    }

    private Vertex transformVertex(Vertex vertex, Mat4 model, Mat4 view, Mat4 projection) {
        Point3D transformedPos = vertex.getPosition().mul(model).mul(view).mul(projection);
        return new Vertex(transformedPos, vertex.getCol());
    }

    private boolean clipTriangle(Vertex a, Vertex b, Vertex c) {
        return (a.getPosition().getW() > 0 && b.getPosition().getW() > 0 && c.getPosition().getW() > 0);
    }

    private boolean clipLine(Vertex a, Vertex b) {
        return (a.getPosition().getW() > 0 && b.getPosition().getW() > 0);
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