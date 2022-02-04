package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.sun.javafx.perf.PerformanceTracker;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import menuController.BaseController;

public class cubeTowerController extends AnimationTimer implements Initializable {

	private long time = 0L, last;
	private int posX = 0, posY = 4;
	private boolean choque = false;
	private boolean inicio=true;

	private Pane[][] rectangulos;

	@FXML
	private GridPane pixeles;

	@FXML
	private BorderPane view;

	public cubeTowerController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/cubeToweFxml.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pixeles.setGridLinesVisible(true);
		rectangulos = new Pane[pixeles.getColumnCount()][pixeles.getRowCount()];
		view.getStylesheets().add("css/CubeTower/cubeTower.css");
		for (int i = 0; i < pixeles.getColumnCount(); i++) {
			for (int j = 0; j < pixeles.getRowCount(); j++) {
				Pane rectangulo = new Pane();
				rectangulo.setMaxWidth(Double.MAX_VALUE);
				rectangulo.setMaxHeight(Double.MAX_VALUE);
				rectangulo.setStyle("-fx-background-color: transparent");
				pixeles.add(rectangulo, i, j);
				rectangulos[i][j] = rectangulo;
			}
		}
		setColor(posX, posY);
		setColor(posX + 1, posY);
		setColor(posX + 2, posY);
	}

	public BorderPane getView() {
		return view;
	}

	public void setTransparent(int x, int y) {
		rectangulos[x][y].setStyle("-fx-background-color:transparent");
	}

	public void setColor(int x, int y) {
		rectangulos[x][y].setStyle("-fx-background-color:red");
	}

	@Override
	public void handle(long now) {

		// render
		long diff = now - last;
		time += diff;
		last = now;

		//
		if(inicio==false) {
			setTransparent(posX, posY);
			setTransparent(posX + 1, posY);
			setTransparent(posX + 2, posY);
		}
		
		
		// update
		if (time > 0.5 * 1e9) {
			bloqueDeMovimiento3();
			time -= 0.5 * 1e9;
		}
		
	
		
		setColor(posX, posY);
		setColor(posX + 1, posY);
		setColor(posX + 2, posY);
		inicio=false;
		

	}

	private void bloqueDeMovimiento3() {
		if (posX < 5 && choque == false) {
			posX++;
			if (posX == 3) {
				choque = true;
				posX--;
			}
		}
		if (posX >= 0 && choque == true) {
			posX--;
			if (posX == 0) {
				choque = false;
			}
		}
		
	}
	
	@FXML
	void onAtrasButton(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onStopAction(ActionEvent event) {
		stop();
		posX = 1;
		System.out.println("Antes" + posY);
		posY--;
		System.out.println("Despues" + posY);
		if (posY < 0) {
			stop();
			System.out.println("Tope");
		} else {
			start();
		}
	}

	@FXML
	void onPlayAction(ActionEvent event) {
		
		last = System.nanoTime();
		start();
	}

}
