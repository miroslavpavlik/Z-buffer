package objects;

import model.Vertex;
import raster.Part;

import java.util.ArrayList;
import java.util.List;


public class Solid {
    // Part buffer
    List<Part> pB = new ArrayList<Part>();
    // Vertex buffer = GEOMETRIE
    List<Vertex> vB = new ArrayList<>();
    // Index buffer = TOPOLOGIE
    List<Integer> iB = new ArrayList<>();

    public List<Part> getpB() {
        return pB;
    }

    public List<Vertex> getvB() {
        return vB;
    }

    public List<Integer> getiB() {
        return iB;
    }
}
