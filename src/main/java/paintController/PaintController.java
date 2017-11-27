package paintController;

import drawables.*;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mateusz on 2017-02-12.
 */
public class PaintController {
    private Coordinates coordinatesStart;
    private List<Drawable> listOfDrawings = FXCollections.observableArrayList();
    private List<Drawable> tempList = FXCollections.observableArrayList();
    @FXML
    private Button buttonPrintWelcome;
    @FXML
    private Canvas canvas;
    GraphicsContext gc;
    @FXML
    private RadioButton lineCheck;
    @FXML
    private RadioButton circleCheck;
    @FXML
    private RadioButton rectangleCheck;
    @FXML
    private Button undo;
    @FXML
    private Button redo;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ColorPicker colorPickerFill;
    @FXML
    private CheckBox figureFilled;
    @FXML
    private Button fill;


    @FXML
    void initialize() {
        gc = canvas.getGraphicsContext2D();
        cleanCanvas();
    }

    public void clearCanvas(ActionEvent actionEvent) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        listOfDrawings.removeAll(listOfDrawings);
    }

    private void cleanCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void getCoordinates(MouseEvent mouseEvent) {
        coordinatesStart = new Coordinates(mouseEvent.getX(), mouseEvent.getY());
    }

    public void paintSomething(MouseEvent mouseEvent) {
        if (lineCheck.isSelected()) {
            Coordinates coordinatesEnd = new Coordinates(mouseEvent.getX(), mouseEvent.getY());
            Line line = new Line(coordinatesStart, coordinatesEnd, colorPicker.getValue());
            line.Draw(gc);
            listOfDrawings.add(line);
        }
        if (rectangleCheck.isSelected()) {
            Coordinates coordinatesEnd = new Coordinates(mouseEvent.getX(), mouseEvent.getY());
            Rectangle rectangle = new Rectangle(coordinatesStart, coordinatesEnd, colorPicker.getValue(), colorPickerFill.getValue());
            rectangle.Draw(gc);
            listOfDrawings.add(rectangle);
        }
        if (circleCheck.isSelected()) {
            Coordinates coordinatesEnd = new Coordinates(mouseEvent.getX(), mouseEvent.getY());
            Circle circle = new Circle(coordinatesStart, coordinatesEnd, colorPicker.getValue(), colorPickerFill.getValue());
            circle.Draw(gc);
            listOfDrawings.add(circle);
        }
    }

    public void undoAction(ActionEvent actionEvent) {
        if (!listOfDrawings.isEmpty()) {
            cleanCanvas();
            tempList.add(listOfDrawings.get(listOfDrawings.size() - 1));
            listOfDrawings.remove(listOfDrawings.size() - 1);
            listOfDrawings.forEach(p -> p.Draw(gc));
        }
    }

    public void redoAction(ActionEvent actionEvent) {
        if (!tempList.isEmpty()) {
            cleanCanvas();
            listOfDrawings.add(tempList.get(tempList.size() - 1));
            tempList.remove(tempList.size() - 1);
            listOfDrawings.forEach(p -> p.Draw(gc));
        }
    }


    public void exitProgram(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void saveFileChooser(Event event) throws IOException {
        File file = openSaveDialogToSaveAFile();
        WritableImage writableImage = canvas.snapshot(null, null);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ImageIO.write(bufferedImage, "png", file);

    }

    private File openSaveDialogToSaveAFile() {
        FileChooser save = new FileChooser();
        save.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png"));
        save.setInitialDirectory(new File(System.getProperty("user.home")));
        save.setTitle("Save as...");
        save.setInitialFileName("defaultFileName");
        Scene scene2 = rectangleCheck.getScene();
        File file = save.showSaveDialog(scene2.getWindow());
        return file;
    }

    public void visiblePaintOnMouseMoved(MouseEvent mouseEvent) {
        Coordinates coordinatesEnd = new Coordinates(mouseEvent.getX(), mouseEvent.getY());
        if (!(mouseEvent.getX() == 0) && !(mouseEvent.getY() == 0)) {
            Drawable drawable = null;
            cleanCanvas();
            listOfDrawings.forEach(p -> p.Draw(gc));
            if (lineCheck.isSelected()) {
                drawable = new Line(coordinatesStart, coordinatesEnd, colorPicker.getValue());
                drawable.Draw(gc);
            }
            if (rectangleCheck.isSelected()) {
                drawable = new Rectangle(coordinatesStart, coordinatesEnd, colorPicker.getValue(), colorPickerFill.getValue());
                drawable.Draw(gc);
            }
            if (circleCheck.isSelected()) {
                drawable = new Circle(coordinatesStart, coordinatesEnd, colorPicker.getValue(), colorPickerFill.getValue());
                drawable.Draw(gc);
            }
        }
    }
}
