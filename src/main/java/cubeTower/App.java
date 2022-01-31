package cubeTower;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();

        Scene escena = new Scene(controller.getView());

        primaryStage.setTitle("a");
        primaryStage.setScene(escena);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

}
