package model;

public interface IVectorizable<T> {
    public T add(T item);

    public T mul(double k);
}
