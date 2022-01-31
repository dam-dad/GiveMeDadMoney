package menuController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.print.attribute.standard.Media;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import main.Sounds;
import score.Score;
import tragaPerras.Controller;
import javafx.application.Application;
import javafx.scene.Group;



public class BaseController implements Initializable {

	@FXML
	private BorderPane root;

	//CONTROLLER
	private SettingsController settingController = new SettingsController();
	private MenuController menuController = new MenuController();

	private static BaseController instance;


	
	//CONTROLLER JUEGO
	private Controller tragaPerrasController = new Controller();
	
	public Sounds musica;

	private BaseController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/BaseView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getView().getStylesheets().add("/css/Menu/menu.css");
		showMenu();
		
		
		try {
			musica = new Sounds("sound/sound2.wav");
		} catch (Exception e) {

		}
		
		musica.play();
		musica.volumen(-25.0F);
	}

	public void showSetting() {
		root.setCenter(settingController.getView());

	}
	
	public void showTragaPerras() {
		root.setCenter(tragaPerrasController.getView());
		
	}

	public void showMenu() {
		root.setCenter(menuController.getView());
	}

	public BorderPane getView() {
		return root;
	}

	public static BaseController getInstance() {
		if (instance == null) {
			try {
				instance = new BaseController();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}



}
