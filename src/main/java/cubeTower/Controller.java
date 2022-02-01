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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller extends AnimationTimer implements Initializable {

	private long last;

	private int pos = 0;

	private Pane[][] rectangulos;

	@FXML
	private GridPane pixeles;

	@FXML
	private BorderPane view;

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/cubeToweFxml.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pixeles.setGridLinesVisible(false);
		rectangulos = new Pane[pixeles.getColumnCount()+1][pixeles.getRowCount()+1];
		view.getStylesheets().add("css/CubeTower/cubeTower.css");
//		for (int x = 0; x < view.getColumnCount(); x++) {
//			for (int y = 0; y < view.getRowCount(); y++) {
//				Pane rectangulo = new Pane();
//				rectangulo.setMaxWidth(Double.MAX_VALUE);
//				rectangulo.setMaxHeight(Double.MAX_VALUE);
//				rectangulo.setStyle("-fx-background-color: red");
//				view.add(rectangulo, x, y);
//				rectangulos[x][y] = rectangulo;
//			}
//		}
		for (int i = 0; i < 3; i++) {
			Pane rectangulo = new Pane();
			rectangulo.setMaxWidth(Double.MAX_VALUE);
			rectangulo.setMaxHeight(Double.MAX_VALUE);
			rectangulo.setStyle("-fx-background-color: #2F302F");
			pixeles.add(rectangulo, i, 4);
			rectangulos[i][4] = rectangulo;
		}
		System.out.println(pixeles.getColumnCount());
		start();
	}

	public BorderPane getView() {
		return view;
	}

	public void setColorNull(int x, int y) {
		rectangulos[x][y].setStyle("-fx-background-color:#" + "6A6A6A" + ";");
	}

	public void setColor(int x, int y) {
		rectangulos[x][y].setStyle("-fx-background-color:#" + "2F302F" + ";");
	}

	@Override
	public void handle(long now) {
		int i=0;
		if(i==0) {
			doHandleDer();
		}
		i=1;
		stop();

		// doHandleIz();
//		long diff = now - last;
//		System.out.println(diff);
	}

	private void doHandleDer() {
		int pos_cubo_añadir1 = 3;
		int pos_cubo_eliminar1 = 0;
		int pos_cubos = 2;
		int pos_cubo_añadir2=1;
		int pos_cubo_eliminar2=4;
		int pos_cubos2=5;
		while (pos_cubos < pixeles.getColumnCount()-1) {
			System.out.println("Cambio Izquierda");
			setColorNull(pos_cubo_eliminar1, 4);
			Pane rectangulo = new Pane();
			rectangulo.setMaxWidth(Double.MAX_VALUE);
			rectangulo.setMaxHeight(Double.MAX_VALUE);
			rectangulo.setStyle("-fx-background-color: #2F302F");
			pixeles.add(rectangulo, pos_cubo_añadir1, pixeles.getColumnCount()-1);
			rectangulos[pos_cubo_añadir1][4] = rectangulo;
			pos_cubos++;
			pos_cubo_añadir1++;
			pos_cubo_eliminar1++;
		}
		
		while (pos_cubos2 > pixeles.getColumnCount()) {
			System.out.println("Cambio Derecha");
			setColorNull(pos_cubo_eliminar2, 4);
			Pane rectangulo = new Pane();
			rectangulo.setMaxWidth(Double.MAX_VALUE);
			rectangulo.setMaxHeight(Double.MAX_VALUE);
			rectangulo.setStyle("-fx-background-color: #2F302F");
			pixeles.add(rectangulo, pos_cubo_añadir2, pixeles.getColumnCount()-1);
			rectangulos[pos_cubo_añadir2][4] = rectangulo;
			pos_cubos2--;
			pos_cubo_añadir2--;
			pos_cubo_eliminar2--;
		}
	}

	private void doHandleIz() {
		for (int i = 1; i > 0; i--) {
			System.out.println("Cambio Izquierda");
			setColor(i, 4);
		}
	}

	@FXML
	void onStartAction(ActionEvent event) {
		start();
	}

}
