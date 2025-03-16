package raster;

public interface Raster<E> {

    void clear();

    void setClearColor(E element);

    int getWidth();

    int getHeight();

    E getPixel(int x, int y);

    void setPixel(int x, int y, E element);

    default boolean checkBorders(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

}
