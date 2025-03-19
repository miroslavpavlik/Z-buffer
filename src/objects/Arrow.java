package objects;

import model.Vertex;
import raster.Part;
import transforms.Point3D;

public class Arrow extends Solid {

    public Arrow() {
        getvB().add(new Vertex(new Point3D(0, 0, 0)));
        getvB().add(new Vertex(new Point3D(0, 1, 0)));

        getvB().add(new Vertex(new Point3D(-0.1, 0.9, 0)));
        getvB().add(new Vertex(new Point3D(0.1, 0.9, 0)));
        getvB().add(new Vertex(new Point3D(0, 1.1, 0)));

        getiB().add(0);
        getiB().add(1);

        getiB().add(2);
        getiB().add(3);
        getiB().add(4);

        getpB().add(new Part(TopologyType.LINES, 0, 2));

        getpB().add(new Part(TopologyType.TRIANGLES, 2, 3));
    }
}