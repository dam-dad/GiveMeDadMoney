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
	private int posX = 0, posY = 0;

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
		
		long diff = now - last;
		time += diff;
		last = now;

		//
		setTransparent(posX, posY);
		
		// update
		if (time > 0.5 * 1e9) {
			posX++;
			if (posX >= 5) {
				posX = 0;
				posY++;
			}
			if (posY >= 5) {
				posY = 0;
			}
			time -= 0.5 * 1e9;
		}

		// render
		setColor(posX, posY);
		// cambioFilas();
//		try {
//			TimeUnit.MILLISECONDS.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// quita();
		// stop();
	}

	private void cambioFilas() {
		int rectanguloColor = 3;
//		if (pos < pixeles.getColumnCount()) {
//			for (int i = 0; i < rectangulos.length - rectanguloColor; i++) {
//				setColorNull(i, 4);
//				setColor(i + rectanguloColor, 4);
//			}
//			pos = 5;
//		} else {
//			for (int i = rectangulos.length-1; i > 0; i--) {
//				setColorNull(i, 4);
//				setColor(i - rectanguloColor, 4);
//			}
//			pos = 1;
//		}
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				for (int i = 0; i <= rectangulos.length - rectanguloColor - 1; i++) {
					System.out.println("i  " + i);
					System.out.println("lrn  " + rectangulos.length);
					//setColorNull(i, 4);
					setColor(i + rectanguloColor, 4);
				}
				return null;
			}
		};

		new Thread(task);
		stop();

	}

	private void quita() {
//		for (int i = 4; i >= 3; i--) {
//			System.out.println("dERECAHA");
//			System.out.println("i  " + i);
//			System.out.println("length  " + rectangulos.length);
//			setColorNull(i, 4);
//			setColor(i - 3, 4);
//		}
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				//setColorNull(4, 4);
				System.out.println("Mueve");
				setColor(1, 4);
				System.out.println("Mueve");
				//setColorNull(3, 4);
				System.out.println("Mueve");
				setColor(0, 4);
				return null;
			}
		};
		new Thread(task);
	}

	@FXML
	void onAtrasButton(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onStopAction(ActionEvent event) {
		stop();
//		pos=0;
//		setColor(0, 3);
//		setColor(1, 3);
//		if(pos<pixeles.getColumnCount()) {
//			setColorNull(0, 3);
//			setColor(2, 3);
//			setColorNull(1, 3);
//			setColor(3, 3);
//			setColorNull(2, 3);
//			setColor(4, 3);
//		}
	}

	@FXML
	void onPlayAction(ActionEvent event) {
//		cambioFilas();
//		try {
//			TimeUnit.MILLISECONDS.sleep(1250);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		quita();
		last = System.nanoTime();
		start();
	}

}
