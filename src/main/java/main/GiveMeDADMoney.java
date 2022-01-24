package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import menuController.MenuRootController;

public class GiveMeDADMoney extends Application {

	private MenuRootController menu;
	
	private static Stage primaryStage; 

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		menu = new MenuRootController();

		Scene scene = new Scene(menu.getView());

		primaryStage.setTitle("GiveMeDADMoney");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}


}
