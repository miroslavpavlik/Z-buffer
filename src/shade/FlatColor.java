package shade;

import model.Vertex;
import transforms.Col;

public class FlatColor implements FragmentShader {

    private Col color;

    public FlatColor() {
        this.color = new Col(1., 1., 1.);
    }

    public FlatColor(Col color) {
        this.color = color;
    }

    @Override
    public Col getColor(Vertex v) {
        return color;
    }
}

