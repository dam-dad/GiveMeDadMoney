package menuController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import cubeTower.CubeTowerController;
import cubeTower.Niveles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.GiveMeDADMoney;
import main.Music;
import mayorOMenor.MayorOMenorController;
import tragaPerras.Controller;

public class BaseController implements Initializable {

	@FXML
	private BorderPane root;

	// CONTROLLER MENU
	private SettingsController settingController = new SettingsController();
	private MenuController menuController = new MenuController();

	private static BaseController instance;

	// CONTROLLER JUEGO
	private Controller tragaPerrasController = new Controller();
	private MayorOMenorController mayorOMenor = new MayorOMenorController();
	private CubeTowerController cubeTower = new CubeTowerController();;
	private Niveles cubeLevel = new Niveles();

	public Music musica;

	private BaseController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/BaseView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getView().getStylesheets().add("/css/Menu/menu.css");
		

		try {
			musica = new Music("/media/sound2.mp3");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		showMenu();
	}

	public void showSetting() {
		root.setCenter(settingController.getView());

	}

	public void showTragaPerras() {
		root.setCenter(tragaPerrasController.getView());
		tragaPerrasController.load_score();
	}
	
	public void showCubeTower() {
		root.setCenter(cubeTower.getView());
	}
	public CubeTowerController getCubeTower() {
		return cubeTower;
	}
	public void setCubeTower(CubeTowerController cube) {
		this.cubeTower = cube;
	}
	
	
	public void showLevelTower() {
		cubeLevel.setCubeController(cubeTower);
		root.setCenter(cubeLevel.getView());
	}


	public void showMayorOMenor() {
		root.setCenter(mayorOMenor.getView());
		mayorOMenor.load_score();
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
