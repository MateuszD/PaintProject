package drawables;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Mateusz on 2017-03-29.
 */
public class Rectangle implements Drawable {
    Coordinates start, end;
    Color color;
    Color colorFill;
    double height, width;

    public Rectangle(Coordinates start, Coordinates end, Color color, Color colorFill) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.colorFill = colorFill;
    }


    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(colorFill);
        gc.setStroke(color);
        double startingPointX = Math.min(start.getCoordinateX(), end.getCoordinateX());
        double startingPointY = Math.min(start.getCoordinateY(), end.getCoordinateY());
        double endingPointX = Math.max(start.getCoordinateX(), end.getCoordinateX());
        double endingPointY = Math.max(start.getCoordinateY(), end.getCoordinateY());
        width = endingPointX - startingPointX;
        height = endingPointY - startingPointY;
        gc.strokeRect(startingPointX, startingPointY, width, height);
        gc.fillRect(startingPointX, startingPointY, width, height);
    }
}
