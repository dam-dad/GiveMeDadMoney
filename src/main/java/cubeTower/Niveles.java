package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.GiveMeDADMoney;
import menuController.BaseController;

public class Niveles implements Initializable {

	private CubeTowerController cube;
	private Tower tower;

	@FXML
	private BorderPane view;

	public Niveles() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/dificultad.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("niveles");
	}
	
	public void setCubeController(CubeTowerController cube) {
		this.cube = cube;
	}

	@FXML
	void onFacilAction(ActionEvent event) {
		cube.setSpeed(1.5 * 1e9);
		cube.setSize(4);
		tower.clear();
		BaseController.getInstance().setCubeTower(cube);
		BaseController.getInstance().showCubeTower();
	}

	@FXML
	void onMedioAction(ActionEvent event) {
		cube.setSpeed(0.5 * 1e9);
		cube.setSize(3);
		tower.clear();
		BaseController.getInstance().setCubeTower(cube);
		BaseController.getInstance().showCubeTower();
	}

	@FXML
	void onDificilAction(ActionEvent event) {
		cube.setSpeed(0.3 * 1e9);
		cube.setSize(2);
		tower.clear();
		BaseController.getInstance().setCubeTower(cube);
		BaseController.getInstance().showCubeTower();
	}

	public BorderPane getView() {
		return view;
	}

	

}
