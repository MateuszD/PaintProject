package drawables;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Mateusz on 2017-03-29.
 */
public class Circle implements Drawable {
Coordinates start, end;
Color color;
Color colorfill;

public Circle(Coordinates start, Coordinates end, Color color, Color colorfill){
    this.color = color;
    this.start = start;
    this.end = end;
    this.colorfill = colorfill;
}
    @Override
    public void Draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.setFill(colorfill);
        double startingPointX = Math.min(start.getCoordinateX(), end.getCoordinateX());
        double startingPointY = Math.min(start.getCoordinateY(), end.getCoordinateY());
        double endingPointX = Math.max(start.getCoordinateX(), end.getCoordinateX());
        double endingPointY = Math.max(start.getCoordinateY(), end.getCoordinateY());
        double width = (endingPointX - startingPointX);
        double height = (endingPointY - startingPointY);
        gc.strokeOval(startingPointX, startingPointY, width, height);
        gc.fillOval(startingPointX, startingPointY, width, height);
    }
}
