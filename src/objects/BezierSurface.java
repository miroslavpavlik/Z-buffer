package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;
import transforms.Cubic;

public class BezierSurface extends Solid {

    private final Point3D[][] controlPoints; // Mřížka 3x3 kontrolních bodů
    private final int resolution; // Rozlišení plochy (počet bodů podél každého parametru)

    public BezierSurface(Point3D[][] controlPoints, int resolution, Col color) {
        this.controlPoints = controlPoints;
        this.resolution = resolution;
        generateSurface(color);
    }

    private void generateSurface(Col color) {
        // Výpočet bodů na ploše
        for (int i = 0; i < resolution; i++) {
            double u = (double) i / (resolution - 1); // Parametr u

            for (int j = 0; j < resolution; j++) {
                double v = (double) j / (resolution - 1); // Parametr v

                // Výpočet bodu na ploše
                Point3D point = computePointOnSurface(u, v);
                getvB().add(new Vertex(point, color));
            }
        }

        // Triangulace plochy
        for (int i = 0; i < resolution - 1; i++) {
            for (int j = 0; j < resolution - 1; j++) {
                int topLeft = i * resolution + j;
                int topRight = topLeft + 1;
                int bottomLeft = (i + 1) * resolution + j;
                int bottomRight = bottomLeft + 1;

                // První trojúhelník
                getiB().add(topLeft);
                getiB().add(topRight);
                getiB().add(bottomLeft);

                // Druhý trojúhelník
                getiB().add(bottomLeft);
                getiB().add(topRight);
                getiB().add(bottomRight);
            }
        }

        // Nastavení částí (parts)
        getpB().add(new Part(TopologyType.TRIANGLES, 0, getiB().size()));
    }

    private Point3D computePointOnSurface(double u, double v) {
        Point3D[] rowPoints = new Point3D[3];

        // Výpočet bodů podél řádků (pro dané u)
        for (int i = 0; i < 3; i++) {
            Cubic cubic = new Cubic(Cubic.BEZIER, controlPoints[i][0], controlPoints[i][1], controlPoints[i][2], controlPoints[i][2]);
            rowPoints[i] = cubic.compute(u);
        }

        // Výpočet bodu podél sloupce (pro dané v)
        Cubic cubic = new Cubic(Cubic.BEZIER, rowPoints[0], rowPoints[1], rowPoints[2], rowPoints[2]);
        return cubic.compute(v);
    }
}