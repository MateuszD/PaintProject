package drawables;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Mateusz on 2017-03-29.
 */
public class Line implements Drawable{

    Coordinates start;
    Coordinates end;
    Color color;

    public Line(Coordinates start, Coordinates end, Color color){
        this.start = start;
        this.end = end;
        this.color = color;
    }
    @Override
    public void Draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeLine(start.getCoordinateX(), start.getCoordinateY(), end.getCoordinateX(), end.getCoordinateY());
    }
}
