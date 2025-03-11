package objects;

import model.Vertex;
import raster.Part;
import transforms.Point3D;

/**
 * @author KŠ
 * 04.03.2025 8:58
 */

public class Arrow extends Solid {

    public Arrow() {
        // CTRL + D = duplikujem radek
        // TODO: vhodne doplnit
        getvB().add(new Vertex(new Point3D(0, 0, 0)));
        getvB().add(new Vertex(new Point3D(0, 0, 0)));
        getvB().add(new Vertex(new Point3D(0, 0, 0)));
        getvB().add(new Vertex(new Point3D(0, 0, 0)));
        getvB().add(new Vertex(new Point3D(0, 0, 0)));

        // LINE
        getiB().add(0);
        getiB().add(1);

        getiB().add(2);
        getiB().add(3);
        getiB().add(4);

        getpB().add(new Part(TopologyType.LINES, 0, 1));
        getpB().add(new Part(TopologyType.TRIANGLES, 2, 1));

    }
}
