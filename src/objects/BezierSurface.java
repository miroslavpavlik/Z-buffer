package objects;

import model.Vertex;
import raster.Part;
import transforms.*;

public class BezierSurface extends Solid {

    public BezierSurface() {
        double offsetX = 2.0;
        double offsetZ = 1.5;

        Point3D[] controlPoints = new Point3D[]{
                new Point3D(0 + offsetX, 0, 0 + offsetZ),
                new Point3D(1 + offsetX, 0, 0 + offsetZ),
                new Point3D(2 + offsetX, 0, 0 + offsetZ),
                new Point3D(3 + offsetX, 0, 0 + offsetZ),

                new Point3D(0 + offsetX, 1, 0.5 + offsetZ),
                new Point3D(1 + offsetX, 1, 1 + offsetZ),
                new Point3D(2 + offsetX, 1, 1 + offsetZ),
                new Point3D(3 + offsetX, 1, 0.5 + offsetZ),

                new Point3D(0 + offsetX, 2, 0.5 + offsetZ),
                new Point3D(1 + offsetX, 2, 1 + offsetZ),
                new Point3D(2 + offsetX, 2, 1 + offsetZ),
                new Point3D(3 + offsetX, 2, 0.5 + offsetZ),

                new Point3D(0 + offsetX, 3, 0 + offsetZ),
                new Point3D(1 + offsetX, 3, 0 + offsetZ),
                new Point3D(2 + offsetX, 3, 0 + offsetZ),
                new Point3D(3 + offsetX, 3, 0 + offsetZ)
        };

        Bicubic bicubic = new Bicubic(Cubic.BEZIER, controlPoints);

        int resolution = 10;
        for (int i = 0; i <= resolution; i++) {
            double u = (double) i / resolution;
            for (int j = 0; j <= resolution; j++) {
                double v = (double) j / resolution;
                Point3D point = bicubic.compute(u, v);

                int red = (int) Math.min(255, 50 + point.getY() * 50);
                int green = (int) Math.min(255, 100 + point.getY() * 30);
                int blue = (int) Math.min(255, 200 - point.getY() * 40);

                Col color = new Col(red, green, blue);
                vB.add(new Vertex(point, color));
            }
        }
        for (int i = 0; i < resolution; i++) {
            for (int j = 0; j < resolution; j++) {
                int topLeft = i * (resolution + 1) + j;
                int topRight = topLeft + 1;
                int bottomLeft = (i + 1) * (resolution + 1) + j;
                int bottomRight = bottomLeft + 1;


                iB.add(topLeft);
                iB.add(topRight);
                iB.add(bottomLeft);

                iB.add(topRight);
                iB.add(bottomRight);
                iB.add(bottomLeft);
            }
        }

        pB.add(new Part(TopologyType.TRIANGLES, 0, resolution * resolution * 6));
    }
}
