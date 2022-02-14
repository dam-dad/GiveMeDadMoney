package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import menuController.BaseController;

public class CubeTowerController extends AnimationTimer implements Initializable {

	private long last;
	private Cube cube;
	private Tower tower;
	private boolean inicio = true;
	private boolean play = true;
	private double speed;
	private int size;
	

	@FXML
	private GridPane pixeles;

	@FXML
	private BorderPane view;

	public CubeTowerController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//pixeles.setGridLinesVisible(true);

		tower = new Tower(pixeles.getColumnCount(), pixeles.getRowCount());
		for (int i = 0; i < tower.getCols(); i++) {
			for (int j = 0; j < tower.getRows(); j++) {
				pixeles.add(tower.getRectangle(i, j), i, j);
			}
		}

		view.requestFocus();
		view.setFocusTraversable(true);
		view.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
			if (event.getCode() == KeyCode.SPACE) {
				if (!play) {
					stopCubo();
				} else {
					playCubo();
				}

			}
			event.consume();
		});
		setSpeed(1e9);

	}

	public BorderPane getView() {
		return view;
	}

	@Override
	public void handle(long now) {
		long diff = now - last;
		last = now;
		loop(diff / getSpeed()); // pasamos el tiempo transacurrido a segundos, getSpeed();
	}

	private void loop(double time) {
		// clear cube from tower
		cube.setColor("transparent");
		cube.render(tower);

		// move cube
		cube.update(time, tower);

		// draw cube in tower
		cube.setColor("#FF7B00");
		cube.render(tower);

	}

	public int getNivel() {
		return tower.getRows() - cube.getY() - 1;
	}

	@FXML
	void onAtrasButton(ActionEvent event) {
		BaseController.getInstance().showMenu();
		tower.clear();
		stop();
		
	}

	private void stopCubo() {
		if (!inicio) {
			cube.reduce(tower);
			if (cube.getSize() == 0) {
				stop();
				play = true;
				Alert alertaTope = new Alert(AlertType.INFORMATION);
				alertaTope.setHeaderText("¡¡¡Has perdido!!!");
				alertaTope.setContentText("Nivel: " + getNivel());
				alertaTope.showAndWait();
			} else {
				cube.moveUp();
				if (cube.getY() < 0) {
					play = true;
					stop();
					Alert alertaTope = new Alert(AlertType.INFORMATION);
					alertaTope.setHeaderText("¡¡¡Has ganado!!!");
					alertaTope.showAndWait();
				}
			}
		} else {
			// la primera vez sólo subimos el cubo un nivel
			cube.moveUp();
			inicio = false;
		}
	}

	@FXML
	void onStopAction(ActionEvent event) {
		stopCubo();
	}

	@FXML
	void onPlayAction(ActionEvent event) {
		playCubo();
	}

	private void playCubo() {
		inicio = true;
		play = false;
		cube = new Cube(0, tower.getRows() - 1, 4);// setsize();
		tower.clear();
		last = System.nanoTime();
		start();
	}
	
	@FXML
    void onNivelesAction(ActionEvent event) throws IOException {
		BaseController.getInstance().showLevelTower();
	
    }

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
