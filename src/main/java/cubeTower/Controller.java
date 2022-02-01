package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller extends AnimationTimer implements Initializable {
	
	private long last;
	
	private Pane[][] rectangulos;

	@FXML
	private GridPane view;

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/cubeToweFxml.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		view.setGridLinesVisible(true);
		
		rectangulos = new Pane[view.getColumnCount()][view.getRowCount()];
		for (int x = 0; x < view.getColumnCount(); x++) {
			for (int y = 0; y < view.getRowCount(); y++) {
				Pane rectangulo = new Pane();
				rectangulo.setMaxWidth(Double.MAX_VALUE);
				rectangulo.setMaxHeight(Double.MAX_VALUE);
				rectangulo.setStyle("-fx-background-color: red");
				view.add(rectangulo, x, y);
				rectangulos[x][y] = rectangulo;
			}
		}
		
		last = System.nanoTime();
		start();

	}

	public GridPane getView() {
		return view;
	}
	
	public void setColor(int x, int y, Color color) {
		rectangulos[x][y].setStyle("-fx-background-color: " + color.toString());
	}

	@Override
	public void handle(long now) {
		long diff = now - last;
		System.out.println(1 / (diff * 1e-9));
		last = now;
	}

}
