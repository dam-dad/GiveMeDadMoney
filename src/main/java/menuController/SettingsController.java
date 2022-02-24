package menuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

/**
 * The type Settings controller. Controllador de los ajustes del juego
 */
public class SettingsController implements Initializable {

	@FXML
    private Button backButton;

    @FXML
    private Button generarButton;

    @FXML
    private Button playMusicButton;

    @FXML
    private BorderPane settingsView;

    @FXML
    private Button stopMusicButton;

    @FXML
    private Slider volumenSlider;


    @FXML
    void onGenerarAction(ActionEvent event) {

    }


	/**
	 * Instantiates a new Settings controller.
	 *
	 * @throws IOException the io exception
	 */
	public SettingsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/SettingsView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		volumenSlider.valueProperty().addListener((o,ov,nv) -> {
            BaseController.getInstance().musica.fondoReproductor.volumeProperty().bind(volumenSlider.valueProperty());
        });
	}


	/**
	 * On music stop acction. Para la musica de fondo
	 *
	 * @param event the event
	 */
	@FXML
	void onMusicStopAcction(ActionEvent event) {
		try {
			BaseController.getInstance().musica.pause();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * On music play acction. Reproduce la musica de fondo
	 *
	 * @param event the event
	 */
	@FXML
	void onMusicPlayAcction(ActionEvent event) {
		try {
			BaseController.getInstance().musica.play();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * On back acction. Vuelve al menu
	 *
	 * @param event the event
	 */
	@FXML
	void onBackAcction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}


	/**
	 * Gets view.
	 *
	 * @return the view
	 */
	public BorderPane getView() {
		return settingsView;
	}

}
