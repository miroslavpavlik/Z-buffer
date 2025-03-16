package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;

public class Cube extends Solid {

    public Cube() {
        getvB().add(new Vertex(new Point3D(-1, -1, -1), new Col(255, 0, 0)));   // 0 - red
        getvB().add(new Vertex(new Point3D(1, -1, -1), new Col(0, 255, 0)));    // 1 - green
        getvB().add(new Vertex(new Point3D(1, 1, -1), new Col(0, 0, 255)));     // 2 - blue
        getvB().add(new Vertex(new Point3D(-1, 1, -1), new Col(255, 255, 0)));  // 3 - yellow
        getvB().add(new Vertex(new Point3D(-1, -1, 1), new Col(255, 0, 255)));  // 4 - magenta
        getvB().add(new Vertex(new Point3D(1, -1, 1), new Col(0, 255, 255)));   // 5 - cyan
        getvB().add(new Vertex(new Point3D(1, 1, 1), new Col(128, 128, 128)));  // 6 - gray
        getvB().add(new Vertex(new Point3D(-1, 1, 1), new Col(255, 128, 0)));   // 7 - orange



        getiB().add(0);
        getiB().add(1);
        getiB().add(2);
        getiB().add(0);
        getiB().add(2);
        getiB().add(3);

        getiB().add(4);
        getiB().add(5);
        getiB().add(6);
        getiB().add(4);
        getiB().add(6);
        getiB().add(7);

        getiB().add(0);
        getiB().add(3);
        getiB().add(7);
        getiB().add(0);
        getiB().add(7);
        getiB().add(4);

        getiB().add(1);
        getiB().add(2);
        getiB().add(6);
        getiB().add(1);
        getiB().add(6);
        getiB().add(5);

        getiB().add(2);
        getiB().add(3);
        getiB().add(7);
        getiB().add(2);
        getiB().add(7);
        getiB().add(6);

        getiB().add(0);
        getiB().add(1);
        getiB().add(5);
        getiB().add(0);
        getiB().add(5);
        getiB().add(4);


        getpB().add(new Part(TopologyType.TRIANGLES, 0, 36));

    }
}