package shade;

import model.Vertex;
import transforms.Col;

public class SmoothColor implements FragmentShader {
    @Override
    public Col getColor(Vertex v) {
        return v.getCol();
    }
}

