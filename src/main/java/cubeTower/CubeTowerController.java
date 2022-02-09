package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import menuController.BaseController;

public class CubeTowerController extends AnimationTimer implements Initializable {

	private long last;
	private Cube cube;
	private Tower tower;
	private boolean inicio = true, termino;

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
		
		pixeles.setGridLinesVisible(true);

		tower = new Tower(pixeles.getColumnCount(), pixeles.getRowCount());
		for (int i = 0; i < tower.getCols(); i++) {
			for (int j = 0; j < tower.getRows(); j++) {
				pixeles.add(tower.getRectangle(i, j), i, j);
			}
		}
		
		cube = new Cube(0, tower.getRows() - 1, 3);
		
		inicio = true;
	}

	public BorderPane getView() {
		return view;
	}

	@Override
	public void handle(long now) {
		long diff = now - last;
		last = now;
		loop(diff / 1e9); // pasamos el tiempo transacurrido a segundos
	}
	
	private void loop(double time) {
		//
		
		// clear cube from tower
		cube.setColor("transparent");
		cube.render(tower);
		
		// move cube 
		cube.update(time, tower);
		
		// draw cube in tower
		cube.setColor("red");
		cube.render(tower);
		
	}

	@FXML
	void onAtrasButton(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onStopAction(ActionEvent event) {

		System.out.println("move up");
		
		if (!inicio) {

			System.out.println("reduciendo cubo. no inicio");
			cube.reduce(tower);
	
		} else {
			
			// la primera vez sólo subimos el cubo un nivel
			System.out.println("inicio");
			inicio = false;
			
		}

		cube.moveUp();

//		tamanoConColor = 0;
//		stop();
//		if (posY != pixeles.getRowCount() - 1) {
//			for (int i = 0; i < pixeles.getColumnCount(); i++) {
//				if (rectangulos[i][posY].getStyle() != "-fx-background-color:transparent") {
//					if (rectangulos[i][posY].getStyle() != rectangulos[i][posY + 1].getStyle()) {
//						rectangulos[i][posY].setStyle("-fx-background-color:transparent");
//					} else {
//						tamanoConColor++;
//					}
//				}
//			}
//		}
//		if (posY == 0) {
//			stop();
//		} else {
//			System.out.println(tamanoConColor);
//			System.out.println(posX + " " + posY);
//			posY--;
//			posX = 0;
//			start();
//		}
//		if (tamanoConColor == 0) {
//			inicio = false;
//		}

//		if (posY != pixeles.getRowCount() - 1) {
//			System.out.println("hola");
//			if (rectangulos[posX][posY].getStyle() != rectangulos[posX][posY + 1].getStyle()
//					&& rectangulos[posX + 1][posY].getStyle() != rectangulos[posX + 1][posY + 1].getStyle()) {
//				stop();
//				setTransparent(posX, posY);
//				setTransparent(posX + 1, posY);
//				Alert alertaTope = new Alert(AlertType.INFORMATION);
//				alertaTope.setHeaderText("Has perdido");
//				alertaTope.setContentText("¿Reiniciar?");
//				alertaTope.showAndWait();
//				termino = true;
//			} else {
//				if (rectangulos[posX][posY].getStyle() != rectangulos[posX][posY + 1].getStyle() && choque == false) {
//					System.out.println("pasa derecha");
//					rectangulos[posX][posY].setStyle("-fx-background-color:transparent");
//				}
//				if (rectangulos[posX + 1][posY].getStyle() != rectangulos[posX + 1][posY + 1].getStyle()
//						&& choque == false) {
//					System.out.println("pasa derecha 2");
//					rectangulos[posX + 1][posY].setStyle("-fx-background-color:transparent");
//				}
//				System.out.println(posX + " " + posY);
//				if (rectangulos[posX][posY].getStyle() != rectangulos[posX][posY + 1].getStyle() && choque == true) {
//					System.out.println("pasa derecha");
//					rectangulos[posX][posY].setStyle("-fx-background-color:transparent");
//				}
//				if (rectangulos[posX + 1][posY].getStyle() != rectangulos[posX + 1][posY + 1].getStyle()
//						&& choque == true) {
//					System.out.println("pasa derecha 2");
//					rectangulos[posX + 1][posY].setStyle("-fx-background-color:transparent");
//				}
//			}
//			posX = 1;
//			posY--;
//		} else {
//			posY--;
//		}
//		if (posY < 0) {
//			stop();
//			Alert alertaTope = new Alert(AlertType.INFORMATION);
//			alertaTope.setHeaderText("Completado");
//			alertaTope.setContentText("Has completado el nivel");
//			alertaTope.showAndWait();
//		} else {
//			start();
//		}
	}

	@FXML
	void onPlayAction(ActionEvent event) {

		last = System.nanoTime();
		start();
	}

}
