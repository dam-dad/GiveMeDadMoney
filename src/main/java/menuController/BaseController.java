package menuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import cubeTower.CubeTowerController;
import cubeTower.Niveles;
import estasdisticas.Estasdisticas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import main.Music;
import mayorOMenor.MayorOMenorController;
import score.Score;
import store.StoreController;
import tragaPerras.Controller;
import tragaPerras.Pagos;

/**
 * The type Base controller. La base de toda la aplicacion, donde incia todos los controladores
 */
public class BaseController implements Initializable {

	@FXML
	private BorderPane root;

	// CONTROLLER MENU
	private SettingsController settingController = new SettingsController();
	private MenuController menuController = new MenuController();

	private static BaseController instance;

	// CONTROLLER JUEGO
	private Controller tragaPerrasController = new Controller();
	private Pagos TablaPagos = new Pagos();
	private MayorOMenorController mayorOMenor = new MayorOMenorController();
	private CubeTowerController cubeTower = new CubeTowerController();;
	private Niveles cubeLevel = new Niveles();
	
	private Estasdisticas estadiscticas;
	
	//Tienda
	private StoreController store = new StoreController();

	/**
	 * The Musica.
	 */
	public Music musica;

	private BaseController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/BaseView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getView().getStylesheets().add("/css/style.css");
		
		try {
			musica = new Music("/media/Broken_Heart.mp3");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		showMenu();
		
		estadiscticas=new Estasdisticas();
		estadiscticas.setPuntosDespues(Score.getInstance().getTotalScore());
		estadiscticas.setPuntosAntes(Score.getInstance().getTotalScore());
	}

	/**
	 * Show setting.
	 */
	public void showSetting() {
		root.setCenter(settingController.getView());

	}

	/**
	 * Show traga perras.
	 */
	public void showTragaPerras() {
		root.setCenter(tragaPerrasController.getView());
		tragaPerrasController.load_score();
	}
	
	public void showTablaPagos() {
		root.setCenter(TablaPagos.getView());
		
	}
	
	public void play_sound() {
		musica.sonido_tragaperras();
	}

	/**
	 * Show cube tower.
	 */
	public void showCubeTower() {
		root.setCenter(cubeTower.getView());
	}

	/**
	 * Gets cube tower.
	 *
	 * @return the cube tower
	 */
	public CubeTowerController getCubeTower() {
		return cubeTower;
	}

	/**
	 * Sets cube tower.
	 *
	 * @param cube the cube
	 */
	public void setCubeTower(CubeTowerController cube) {
		this.cubeTower = cube;
	}


	/**
	 * Show level tower.
	 */
	public void showLevelTower() {
		cubeLevel.setCubeController(cubeTower);
		root.setCenter(cubeLevel.getView());
	}


	/**
	 * Show mayor o menor.
	 */
	public void showMayorOMenor() {
		root.setCenter(mayorOMenor.getView());
		mayorOMenor.load_score();
	}

	/**
	 * Show menu.
	 */
	public void showMenu() {
		root.setCenter(menuController.getView());
		menuController.load_score();
	}

	/**
	 * Show store.
	 */
	public void showStore() {
		root.setCenter(store.getView());
	}

	/**
	 * Gets view.
	 *
	 * @return the view
	 */
	public BorderPane getView() {
		return root;
	}

	/**
	 * Gets instance. 
	 *
	 * @return the instance
	 */
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

	/**
	 * Gets estadisticas.
	 *
	 * @return the estadisticas
	 */
	public Estasdisticas getEstadisticas() {
		return estadiscticas;
	}

}
