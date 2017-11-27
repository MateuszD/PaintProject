package drawables;

/**
 * Created by Mateusz on 2017-03-29.
 */
public class Coordinates {
    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    private double coordinateX;
    private double coordinateY;

    public Coordinates(double x, double y){
        this.coordinateX = x;
        this.coordinateY = y;
    }
}
