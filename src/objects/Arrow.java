package objects;

import model.Vertex;
import raster.Part;
import transforms.Point3D;

public class Arrow extends Solid {

    public Arrow() {
        // Vrcholy pro tělo šipky (čára)
        getvB().add(new Vertex(new Point3D(0, 0, 0))); // Vrchol 0: začátek šipky
        getvB().add(new Vertex(new Point3D(0, 1, 0))); // Vrchol 1: konec šipky

        // Vrcholy pro špičku šipky (trojúhelník)
        getvB().add(new Vertex(new Point3D(-0.1, 0.9, 0))); // Vrchol 2: levý bod špičky
        getvB().add(new Vertex(new Point3D(0.1, 0.9, 0)));  // Vrchol 3: pravý bod špičky
        getvB().add(new Vertex(new Point3D(0, 1.1, 0)));    // Vrchol 4: vrchol špičky

        // Indexy pro čáru (tělo šipky)
        getiB().add(0); // Začátek čáry
        getiB().add(1); // Konec čáry

        // Indexy pro trojúhelník (špička šipky)
        getiB().add(2); // Levý bod špičky
        getiB().add(3); // Pravý bod špičky
        getiB().add(4); // Vrchol špičky

        // Part pro čáru (tělo šipky)
        getpB().add(new Part(TopologyType.LINES, 0, 2)); // 2 vrcholy (0 a 1)

        // Part pro trojúhelník (špička šipky)
        getpB().add(new Part(TopologyType.TRIANGLES, 2, 3)); // 3 vrcholy (2, 3, 4)
    }
}