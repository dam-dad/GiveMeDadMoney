package tragaPerras;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
	
	private Figura figura;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Button roll = new Button("Roll");
		roll.setOnAction(this::onRoll);
		
		figura = new Figura();

		VBox root = new VBox(5, figura, roll);
		root.setAlignment(Pos.CENTER);
		
		primaryStage.setTitle("Prueba Tragaperras");
		primaryStage.setScene(new Scene(root, 320, 200));
		primaryStage.show();
		
	}
	
	public void onRoll(ActionEvent e) {
		figura.roll();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
