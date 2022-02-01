package menuController;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import main.Sounds;
import mayorOMenor.MayorOMenorController;
import tragaPerras.Controller;


public class BaseController implements Initializable {

	@FXML
	private BorderPane root;

	//CONTROLLER
	private SettingsController settingController = new SettingsController();
	private MenuController menuController = new MenuController();

	private static BaseController instance;


	
	//CONTROLLER JUEGO
	private Controller tragaPerrasController = new Controller();
	private MayorOMenorController mayorOMenor = new MayorOMenorController();
	
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
		tragaPerrasController.load_score();
		
	}
	public void showMayorOMenor() {
		root.setCenter(mayorOMenor.getView());

	}

	public void showMenu() {
		root.setCenter(menuController.getView());
		menuController.load_score();
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
