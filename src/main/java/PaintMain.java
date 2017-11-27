
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Cursor;



/**
 * Created by Mateusz on 2017-02-07.
 */
public class PaintMain extends Application {
    private Scene scene;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/PaintFXML/PaintMain.fxml"));
        AnchorPane anchorPane = loader.load();

        scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Paint");
        primaryStage.show();
    }

    public void cursorChange(Cursor cursor) {
        scene.setCursor(cursor);
    }
}
