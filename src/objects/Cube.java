package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;

public class Cube extends Solid {

    public Cube() {
        vB.add(new Vertex(new Point3D(1, 1, 1), new Col(255, 0, 0), 0.0, 0.0));   // 0 -red
        vB.add(new Vertex(new Point3D(2, 1, 1), new Col(0, 255, 0), 1.0, 0.0));    // 1 - green
        vB.add(new Vertex(new Point3D(2, 2, 1), new Col(0, 0, 255), 1.0, 1.0));     // 2 -blue
        vB.add(new Vertex(new Point3D(1, 2, 1), new Col(255, 255, 0), 0.0, 1.0));  // 3 - yellow

        vB.add(new Vertex(new Point3D(1, 1, 2), new Col(255, 0, 255), 0.0, 0.0));  // 4 - magenta
        vB.add(new Vertex(new Point3D(2, 1, 2), new Col(0, 255, 255), 1.0, 0.0));   // 5  cyan
        vB.add(new Vertex(new Point3D(2, 2, 2), new Col(128, 128, 128), 1.0, 1.0));  // 6 - gray
        vB.add(new Vertex(new Point3D(1, 2, 2), new Col(255, 128, 0), 0.0, 1.0));   // 7 - orange


        iB.add(0); iB.add(1); iB.add(2);
        iB.add(0); iB.add(2); iB.add(3);

        iB.add(4); iB.add(5); iB.add(6);
        iB.add(4); iB.add(6); iB.add(7);

        iB.add(0); iB.add(1); iB.add(5);
        iB.add(0); iB.add(4); iB.add(5);

        iB.add(2); iB.add(3); iB.add(7);
        iB.add(2); iB.add(6); iB.add(7);

        iB.add(0); iB.add(3); iB.add(7);
        iB.add(0); iB.add(4); iB.add(7);

        iB.add(1); iB.add(2); iB.add(6);
        iB.add(1); iB.add(5); iB.add(6);

        pB.add(new Part(TopologyType.TRIANGLES, 0, 36));
    }
}