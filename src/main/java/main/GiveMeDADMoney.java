package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import menuController.BaseController;
import score.Score;

/**
 * The type Give me dad money.
 */
public class GiveMeDADMoney extends Application {

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		GiveMeDADMoney.primaryStage = primaryStage;

		Scene scene = new Scene(BaseController.getInstance().getView());
		primaryStage.getIcons().add(new Image("/images/Menu/icono.png"));

		primaryStage.setTitle("GiveMeDADMoney"); 
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@SuppressWarnings("static-access")
	@Override
	public void stop() throws Exception {
		Score.getInstance().save();
		super.stop();
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Gets primary stage.
	 *
	 * @return the primary stage
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

}