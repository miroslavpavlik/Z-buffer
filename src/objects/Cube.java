package objects;

import model.Vertex;
import raster.Part;
import transforms.Col;
import transforms.Point3D;

public class Cube extends Solid {

    public Cube() {
        // Define vertices of the cube
        vB.add(new Vertex(new Point3D(0, 0, 0), new Col(255, 0, 0)));   // 0 - red
        vB.add(new Vertex(new Point3D(1, 0, 0), new Col(0, 255, 0)));    // 1 - green
        vB.add(new Vertex(new Point3D(1, 1, 0), new Col(0, 0, 255)));     // 2 - blue
        vB.add(new Vertex(new Point3D(0, 1, 0), new Col(255, 255, 0)));  // 3 - yellow
        vB.add(new Vertex(new Point3D(0, 0, 1), new Col(255, 0, 255)));  // 4 - magenta
        vB.add(new Vertex(new Point3D(1, 0, 1), new Col(0, 255, 255)));   // 5 - cyan
        vB.add(new Vertex(new Point3D(1, 1, 1), new Col(128, 128, 128)));  // 6 - gray
        vB.add(new Vertex(new Point3D(0, 1, 1), new Col(255, 128, 0)));   // 7 - orange

        // Define indices for the cube's triangles (6 faces, 2 triangles per face)
        // Front face
        iB.add(0); iB.add(1); iB.add(2); // Triangle 1
        iB.add(0); iB.add(2); iB.add(3); // Triangle 2

        // Back face
        iB.add(4); iB.add(5); iB.add(6); // Triangle 1
        iB.add(4); iB.add(7); iB.add(6); // Triangle 2

        // Left face
        iB.add(0); iB.add(1); iB.add(5); // Triangle 1
        iB.add(0); iB.add(4); iB.add(5); // Triangle 2

        // Right face
        iB.add(0); iB.add(3); iB.add(7); // Triangle 1
        iB.add(0); iB.add(4); iB.add(7); // Triangle 2

        // Top face
        iB.add(2); iB.add(3); iB.add(7); // Triangle 1
        iB.add(2); iB.add(6); iB.add(7); // Triangle 2

        // Bottom face
        iB.add(1); iB.add(2); iB.add(6); // Triangle 1
        iB.add(1); iB.add(5); iB.add(6); // Triangle 2

        iB.add(0);
        iB.add(1);
        iB.add(1);
        iB.add(2);
        iB.add(2);
        iB.add(3);
        iB.add(3);
        iB.add(0);
        iB.add(4);
        iB.add(5);
        iB.add(5);
        iB.add(6);
        iB.add(6);
        iB.add(7);
        iB.add(7);
        iB.add(4);
        iB.add(0);
        iB.add(4);
        iB.add(1);
        iB.add(5);
        iB.add(2);
        iB.add(6);
        iB.add(3);
        iB.add(7);

        // Define parts (12 triangles in total)
        pB.add(new Part(TopologyType.TRIANGLES, 0, 36));
        pB.add(new Part(TopologyType.LINES, 36,24));
    }
}