package forFlat;

public class Coordinates {
    private long x; //Максимальное значение поля: 38
    private float y;

    public Coordinates(long x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}