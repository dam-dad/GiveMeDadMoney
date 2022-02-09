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

public class cubeTowerController extends AnimationTimer implements Initializable {

	private long time = 0L, last;
	private int posX = 0, posY = 4, tamanoConColor = 0;
	private boolean choque = false;
	private boolean inicio = true, termino;

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
		tamanoConColor = 2;
		inicio = true;
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
		if (inicio == false) {
			for (int i = 0; i < 2; i++) {
				setTransparent(posX + i, posY);
			}
		}
		if (inicio == true) {
			for (int i = 0; i < tamanoConColor; i++) {
				setTransparent(posX + i, posY);
			}
		}
		// update
		if (time > 0.5 * 1e9 && termino == false) {
			bloqueDeMovimiento2();
			time -= 0.5 * 1e9;
		}
		if (inicio == false) {
			for (int i = 0; i < 2; i++) {
				setColor(posX + i, posY);
			}
		}
		if (inicio == true) {
			for (int i = 0; i < tamanoConColor; i++) {
				setColor(posX + i, posY);
			}
		}

	}

	private void bloqueDeMovimiento2() {
		if (posX < 5 && choque == false) {
			posX++;
			if (posX == pixeles.getColumnCount() - 1) {
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
		tamanoConColor = 0;
		stop();
		if (posY != pixeles.getRowCount() - 1) {
			for (int i = 0; i < pixeles.getColumnCount(); i++) {
				if (rectangulos[i][posY].getStyle() != "-fx-background-color:transparent") {
					if (rectangulos[i][posY].getStyle() != rectangulos[i][posY + 1].getStyle()) {
						rectangulos[i][posY].setStyle("-fx-background-color:transparent");
					} else {
						tamanoConColor++;
					}
				}
			}
		}
		if (posY == 0) {
			stop();
		} else {
			System.out.println(tamanoConColor);
			System.out.println(posX + " " + posY);
			posY--;
			posX = 0;
			start();
		}
		if (tamanoConColor == 0) {
			inicio = false;
		}

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
