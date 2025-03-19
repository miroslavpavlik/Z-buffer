package model;

import transforms.Col;
import transforms.Point3D;

public class Vertex implements IVectorizable<Vertex> {
    private final Point3D position;
    private final Col col;
    private double texU;
    private double texV;
    // Prezentace 01, Slide 168
    // UV
    // normal
    // one

    public Vertex(Point3D position) {
        this(position, new Col(255, 255, 255));
    }

    public Vertex(Point3D position, Col col) {
        this.position = position;
        this.col = col;
    }

    public Vertex(Point3D position, Col col, double texU, double texV) {
        this.position = position;
        this.col = col;
        this.texU = texU;
        this.texV = texV;
    }

    public Col getCol() {
        return col;
    }

    public Point3D getPosition() {
        return position;
    }

    public Vertex withPosition(Point3D position) {
        return new Vertex(position, this.col);
    }

    public Vertex withCol(Col col) {
        return new Vertex(this.position, col);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                ", col=" + col +
                '}';
    }

    @Override
    public Vertex add(Vertex item) {
        return new Vertex(
                position.add(item.position),
                col.add(item.col).saturate(),
                texU + item.texU,
                texV + item.texV
        );
    }

    @Override
    public Vertex mul(double k) {
        return new Vertex(
                position.mul(k),
                col.mul(k).saturate(),
                texU * k,
                texV * k
        );
    }
}
