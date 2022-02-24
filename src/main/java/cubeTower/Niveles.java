package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import menuController.BaseController;

/**
 * The type Niveles.
 */
public class Niveles implements Initializable {

	private CubeTowerController cube;

	@FXML
	private BorderPane view;

	/**
	 * Instantiates a new Niveles.
	 *
	 * @throws IOException the io exception
	 */
	public Niveles() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/dificultad.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	/**
	 * Sets cube controller.
	 *
	 * @param cube the cube
	 */
	public void setCubeController(CubeTowerController cube) {
		this.cube = cube;
	}

	/**
	 * On facil action.
	 *
	 * @param event the event
	 * Settea la velocidad y el numero de cubo inicial
	 */
	@FXML
	void onFacilAction(ActionEvent event) {
		cube.setSpeed(1.5 * 1e9);
		cube.setSize(4);
		cube.setBonificacion(2);
		cube.getTower().clear();
		cube.stop();
		cube.playCubo();
		BaseController.getInstance().setCubeTower(cube);
		BaseController.getInstance().showCubeTower();
	}

	/**
	 * On medio action.
	 *
	 * @param event the event
	 * Settea la velocidad y el numero de cubo inicial
	 */
	@FXML
	void onMedioAction(ActionEvent event) {
		cube.setSpeed(0.5 * 1e9);
		cube.setSize(3);
		cube.setBonificacion(4);
		cube.getTower().clear();
		cube.stop();
		cube.playCubo();
		BaseController.getInstance().setCubeTower(cube);
		BaseController.getInstance().showCubeTower();
	}

	/**
	 * On dificil action.
	 *
	 * @param event the event
	 * Settea la velocidad y el numero de cubo inicial
	 */
	@FXML
	void onDificilAction(ActionEvent event) {
		cube.setSpeed(0.3 * 1e9);
		cube.setSize(2);
		cube.setBonificacion(8);
		cube.getPixeles().getColumnConstraints();
		cube.stop();
		cube.playCubo();
		BaseController.getInstance().setCubeTower(cube);
		BaseController.getInstance().showCubeTower();
	}

	/**
	 * On atras action.
	 *
	 * @param event the event
	 */
	@FXML
    void onAtrasAction(ActionEvent event) {
		BaseController.getInstance().showCubeTower();
    }

	/**
	 * Gets view.
	 *
	 * @return the view
	 */
	public BorderPane getView() {
		return view;
	}
	

}
