package tragaPerras;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Pagos pagos;

    @Override
    public void start(Stage primaryStage) throws Exception {

        pagos = new Pagos();

        Scene scene = new Scene(pagos.getView());
        primaryStage.setTitle("Prueba Pagos");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}