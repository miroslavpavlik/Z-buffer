package render;

import model.Vertex;
import objects.Solid;
import raster.Part;

import java.util.List;

/**
 * @author KŠ
 * 04.03.2025 9:05
 */

public class Renderer3D {

    public void renderSolids(List<Solid> solids) {
        for (Solid solid : solids) {
            render(solid);
        }
    }

    public void render(Solid solid
                       // , Mat4 ...
    ) {
        for(Part part : solid.getpB()) {
            switch (part.getTopologyType()) {
                case LINES:
                    // TODO: dokoncit doma
                    break;
                case TRIANGLES:
                    int startIndex = part.getStartIndex();
                    for(int i = 0; i < part.getCount(); i++) {
                        int indexA = solid.getiB().get(startIndex);
                        int indexB = solid.getiB().get(startIndex + 1);
                        int indexC = solid.getiB().get(startIndex + 2);
                        startIndex += 3;

                        Vertex a = solid.getvB().get(indexA);
                        Vertex b = solid.getvB().get(indexB);
                        Vertex c = solid.getvB().get(indexC);

                        // TODO: MVP transformace

                        // TODO: ořezání
                        clipTriangle(a, b, c);
                        // TODO: dehomog.

                        // TODO: transformace do okna

                    }
                    break;

                // TODO: doplnit cases pro další topologie
            }
        }
    }

    private void clipTriangle(Vertex a, Vertex b, Vertex c) {
        // Prezentace 01, Slide 106

        // ořezání podle Z

        // rasterizace trojuhelniku
    }
}
