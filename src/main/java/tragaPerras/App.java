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

        VBox root = new VBox(5, pagos);
        root.setAlignment(Pos.CENTER);

        primaryStage.setTitle("Prueba Pagos");
        primaryStage.setScene(new Scene(root, 320, 200));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}