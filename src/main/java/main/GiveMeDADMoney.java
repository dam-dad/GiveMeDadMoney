package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
	
	public static void error(String header, String content) {
		Alert error = new Alert(AlertType.ERROR);
		error.initOwner(getPrimaryStage());
		error.setTitle("Error");
		error.setHeaderText(header);
		error.setContentText(content);
		error.showAndWait();
	}
	
	public static boolean confirm(String title, String header, String content) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.initOwner(getPrimaryStage());
		confirm.setTitle(title);
		confirm.setHeaderText(header);
		confirm.setContentText(content);
		return confirm.showAndWait().get().equals(ButtonType.OK);
	}
	
	public static void info(String header, String content) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.initOwner(getPrimaryStage());
		info.setTitle(getPrimaryStage().getTitle());
		info.setHeaderText(header);
		info.setContentText(content);
		info.showAndWait();
	}
	@Override
	public void stop() throws Exception {
		menu.getScore_file().save();
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}


}
