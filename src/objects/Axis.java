package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;

public class Axis extends Solid {

    public Axis() {
        getvB().add(new Vertex(new Point3D(0, 0, 0), new Col(255, 0, 0))); // 0
        getvB().add(new Vertex(new Point3D(10, 0, 0), new Col(255, 0, 0))); // 1

        getvB().add(new Vertex(new Point3D(0, 0, 0), new Col(0, 255, 0))); // 2
        getvB().add(new Vertex(new Point3D(0, 10, 0), new Col(0, 255, 0))); // 3

        getvB().add(new Vertex(new Point3D(0, 0, 0), new Col(0, 0, 255))); // 4
        getvB().add(new Vertex(new Point3D(0, 0, 10), new Col(0, 0, 255))); // 5

        getvB().add(new Vertex(new Point3D(9.7, 0.2, 0), new Col(255, 0, 0))); // 6
        getvB().add(new Vertex(new Point3D(9.7, -0.2, 0), new Col(255, 0, 0))); // 7
        getvB().add(new Vertex(new Point3D(10.2, 0, 0), new Col(255, 0, 0))); // 8

        getvB().add(new Vertex(new Point3D(-0.2, 9.7, 0), new Col(0, 255, 0))); // 9
        getvB().add(new Vertex(new Point3D(0.2, 9.7, 0), new Col(0, 255, 0))); // 10
        getvB().add(new Vertex(new Point3D(0, 10.2, 0), new Col(0, 255, 0))); // 11

        getvB().add(new Vertex(new Point3D(0.2, 0, 9.7), new Col(0, 0, 255))); // 12
        getvB().add(new Vertex(new Point3D(-0.2, 0, 9.7), new Col(0, 0, 255))); // 13
        getvB().add(new Vertex(new Point3D(0, 0, 10.2), new Col(0, 0, 255))); // 14

        getiB().add(0); getiB().add(1);
        getiB().add(2); getiB().add(3);
        getiB().add(4); getiB().add(5);

        getiB().add(6); getiB().add(7); getiB().add(8);
        getiB().add(9); getiB().add(10); getiB().add(11);
        getiB().add(12); getiB().add(13); getiB().add(14);

        getpB().add(new Part(TopologyType.LINES, 0, 2));
        getpB().add(new Part(TopologyType.LINES, 2, 2));
        getpB().add(new Part(TopologyType.LINES, 4, 2));


        getpB().add(new Part(TopologyType.TRIANGLES, 6, 3));
        getpB().add(new Part(TopologyType.TRIANGLES, 9, 3));
        getpB().add(new Part(TopologyType.TRIANGLES, 12, 3));
    }
}
