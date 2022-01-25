package menuController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import score.Score;


public class BaseController implements Initializable {

	@FXML
	private BorderPane root;

	private SettingsController setting = new SettingsController();
	private MenuController menu = new MenuController();
	
	private static BaseController instance;
	
	public static ObjectProperty<Score> score_file = new SimpleObjectProperty<>();


	private BaseController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/BaseView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showMenu();
		score_file.bind(menu.score_fileProperty());
		
	}
	
	
	public void showSetting(Score file) {
		root.setCenter(setting.getView());
		setting.setScore_file(getScore());
		setting.load();

	}
	
	public void showMenu() {
		root.setCenter(menu.getView());
	}

	public BorderPane getView() {
		return root;
	}
	
	
	public static BaseController getInstance(){
		if (instance == null) {
			try {
				instance = new BaseController();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
	
	public static Score getScore(){
		return score_file.get();
	}

	
	
}
