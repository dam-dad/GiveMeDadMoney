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

	private int pos;

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
		rectangulos = new Pane[pixeles.getColumnCount() + 1][pixeles.getRowCount() + 1];
		double width = 0;
		view.getStylesheets().add("css/CubeTower/cubeTower.css");
		for (int i = 0; i < 3; i++) {

			Pane rectangulo = new Pane();
			rectangulo.setMaxWidth(Double.MAX_VALUE);
			rectangulo.setMaxHeight(Double.MAX_VALUE);
			rectangulo.setStyle("-fx-background-color: #2F302F");
			pixeles.add(rectangulo, i, 4);
			rectangulos[i][4] = rectangulo;
		}
		pos=3;

	}

	public BorderPane getView() {
		return view;
	}

	public void setColorNull(int x, int y) {
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rectangulos[x][y].setStyle("-fx-background-color:transparent");
		System.out.println("Eliminado");
	}

	public void setColor(int x, int y) {
		Pane rectangulo = new Pane();
		rectangulo.setMaxHeight(Double.MAX_VALUE);
		rectangulo.setMaxWidth(Double.MAX_VALUE);
		rectangulo.setStyle("-fx-background-color: #2F302F");
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pixeles.add(rectangulo, x, y);
		rectangulos[x][y] = rectangulo;
		System.out.println("Añadido");
	}

	@Override
	public void handle(long now) {
		if (pos < pixeles.getColumnCount()) {
			setColorNull(0, 4);
			setColor(3, 4);
			pos=4;
			setColorNull(1, 4);
			setColor(4, 4);
			pos=5;
		}else {
			setColorNull(4, 4);
			setColor(1, 4);
			setColorNull(3, 4);
			setColor(0, 4);
			pos=1;
		}
		stop();
	}

	@FXML
	void onAtrasButton(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onStopAction(ActionEvent event) {
		stop();
	}

	@FXML
	void onPlayAction(ActionEvent event) {
		start();
	}

}
