package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;

public class Axis extends Solid {

    public Axis(){
        getvB().add(new Vertex(new Point3D(0, 0, 0), new Col(255, 0, 0)));
        getvB().add(new Vertex(new Point3D(1, 0, 0), new Col(255, 0, 0)));

        // Os Y (zelená)
        getvB().add(new Vertex(new Point3D(0, 0, 0), new Col(0, 255, 0)));
        getvB().add(new Vertex(new Point3D(0, 1, 0), new Col(0, 255, 0)));

        // Os Z (modrá)
        getvB().add(new Vertex(new Point3D(0, 0, 0), new Col(0, 0, 255)));
        getvB().add(new Vertex(new Point3D(0, 0, 1), new Col(0, 0, 255)));

        getiB().add(0);
        getiB().add(1);
        getiB().add(2);
        getiB().add(3);
        getiB().add(4);
        getiB().add(5);

        getpB().add(new Part(TopologyType.LINES, 0, 2));  // Os X
        getpB().add(new Part(TopologyType.LINES, 2, 2));  // Os Y
        getpB().add(new Part(TopologyType.LINES, 4, 2));  // Os Z
    }
}
