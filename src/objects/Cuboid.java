package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;

public class Cuboid extends Solid {
    public Cuboid() {
        getvB().add(new Vertex(new Point3D(3, 2, 1), new Col(255, 0, 0)));   // 0  red
        getvB().add(new Vertex(new Point3D(2, 3, 1), new Col(0, 0, 255)));   // 1  blue
        getvB().add(new Vertex(new Point3D(3, 3, 1), new Col(0, 255, 0)));   // 2 -green
        getvB().add(new Vertex(new Point3D(2, 2, 1), new Col(0, 0, 0)));     // 3 -  black
        getvB().add(new Vertex(new Point3D(3, 2, 2), new Col(255, 255, 255))); // 4 - white
        getvB().add(new Vertex(new Point3D(3, 3, 2), new Col(255, 255, 0))); // 5 -  yellow
        getvB().add(new Vertex(new Point3D(2, 3, 2), new Col(255, 0, 255))); // 6 -magenta
        getvB().add(new Vertex(new Point3D(2, 2, 2), new Col(0, 255, 255))); // 7 -cyan
        getvB().add(new Vertex(new Point3D(3, 2, 3), new Col(255, 255, 255))); // 8 - white
        getvB().add(new Vertex(new Point3D(2, 2, 3), new Col(0, 255, 255))); // 9 - cyan
        getvB().add(new Vertex(new Point3D(3, 3, 3), new Col(255, 255, 0))); // 10 - ellow
        getvB().add(new Vertex(new Point3D(2, 3, 3), new Col(255, 0, 255))); // 11 - magenta

        getiB().add(0); getiB().add(1); getiB().add(2);
        getiB().add(0); getiB().add(1); getiB().add(3);
        getiB().add(0); getiB().add(2); getiB().add(4);
        getiB().add(2); getiB().add(4); getiB().add(5);
        getiB().add(1); getiB().add(2); getiB().add(5);
        getiB().add(1); getiB().add(6); getiB().add(5);
        getiB().add(1); getiB().add(6); getiB().add(3);
        getiB().add(7); getiB().add(6); getiB().add(3);
        getiB().add(7); getiB().add(0); getiB().add(3);
        getiB().add(7); getiB().add(0); getiB().add(4);
        getiB().add(4); getiB().add(7); getiB().add(8);
        getiB().add(5); getiB().add(8); getiB().add(4);
        getiB().add(9); getiB().add(7); getiB().add(8);
        getiB().add(10); getiB().add(8); getiB().add(5);
        getiB().add(10); getiB().add(6); getiB().add(5);
        getiB().add(9); getiB().add(7); getiB().add(6);
        getiB().add(9); getiB().add(11); getiB().add(6);
        getiB().add(6); getiB().add(11); getiB().add(10);
        getiB().add(9); getiB().add(11); getiB().add(10);
        getiB().add(9); getiB().add(10); getiB().add(8);

        // Define parts (triangles)
        getpB().add(new Part(TopologyType.TRIANGLES, 0, 20 * 3));
    }
}