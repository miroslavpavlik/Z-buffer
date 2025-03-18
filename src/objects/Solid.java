package objects;

import model.Vertex;
import raster.Part;
import transforms.*;

import java.util.ArrayList;
import java.util.List;


public class Solid {
    // Part buffer
    List<Part> pB = new ArrayList<Part>();
    // Vertex buffer = GEOMETRIE
   final List<Vertex> vB = new ArrayList<>();
    // Index buffer = TOPOLOGIE
    final List<Integer> iB = new ArrayList<>();
    private boolean axis;

    private Mat4 model = new Mat4Identity();

    public List<Part> getpB() {
        return pB;
    }

    public List<Vertex> getvB() {
        return vB;
    }

    public List<Integer> getiB() {
        return iB;
    }

    public Mat4 getModel() {
        return model;
    }
    public void setModel(Mat4 modelMatrix) {
        this.model = modelMatrix;
    }
    public void rotate(double angleX, double angleY, double angleZ) {
        model = model.mul(new Mat4RotX(angleX).mul(new Mat4RotY(angleY)).mul(new Mat4RotZ(angleZ)));
    }
    public void scale(double scaleX, double scaleY, double scaleZ) {
        model = model.mul(new Mat4Scale(scaleX, scaleY, scaleZ));
    }
    public void translate(Vec3D translation) {
        model = model.mul(new Mat4Transl(translation));
    }
}
