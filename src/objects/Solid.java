package objects;

import model.Vertex;
import raster.Part;
import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.ArrayList;
import java.util.List;


public class Solid {
    // Part buffer
    List<Part> pB = new ArrayList<Part>();
    // Vertex buffer = GEOMETRIE
    List<Vertex> vB = new ArrayList<>();
    // Index buffer = TOPOLOGIE
    List<Integer> iB = new ArrayList<>();

    private Mat4 modelMatrix = new Mat4Identity();

    public List<Part> getpB() {
        return pB;
    }

    public List<Vertex> getvB() {
        return vB;
    }

    public List<Integer> getiB() {
        return iB;
    }

    public Mat4 getModelMatrix() {
        return modelMatrix;
    }
    public void setModelMatrix(Mat4 modelMatrix) {
        this.modelMatrix = modelMatrix;
    }
}
