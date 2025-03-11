package model;

import transforms.Col;
import transforms.Point3D;

public class Vertex implements IVectorizable<Vertex> {
    private final Point3D position;
    private final Col col;
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

        return new Vertex(position.add(item.position), col.add(item.col).saturate()
                // UV
                // normal
                // one
        );
    }

    @Override
    public Vertex mul(double k) {

        return new Vertex(position.mul(k), col.mul(k).saturate()
                // UV
                // normal
                // one
        );
    }
}
