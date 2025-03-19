package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;

public class Pyramid extends Solid {

    public Pyramid() {
        vB.add(new Vertex(new Point3D(3, 3, 1), new Col(255, 0, 0)));   // 0
        vB.add(new Vertex(new Point3D(4, 3, 1), new Col(0, 255, 0)));   // 1
        vB.add(new Vertex(new Point3D(4, 4, 1), new Col(0, 0, 255)));   // 2
        vB.add(new Vertex(new Point3D(3, 4, 1), new Col(255, 255, 0))); // 3
        vB.add(new Vertex(new Point3D(3.5, 3.5, 2), new Col(255, 128, 0))); // 4

        iB.add(0); iB.add(1); iB.add(2);
        iB.add(0); iB.add(2); iB.add(3);

        iB.add(0); iB.add(1); iB.add(4);
        iB.add(1); iB.add(2); iB.add(4);
        iB.add(2); iB.add(3); iB.add(4);
        iB.add(3); iB.add(0); iB.add(4);

        pB.add(new Part(TopologyType.TRIANGLES, 0, 18));
    }
}
