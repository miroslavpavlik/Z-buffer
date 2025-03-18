package shade;

import model.Vertex;
import transforms.Col;

public interface FragmentShader {
    Col getColor(Vertex v);
}

