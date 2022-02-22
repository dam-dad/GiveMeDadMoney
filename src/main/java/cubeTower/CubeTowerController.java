package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import menuController.BaseController;
import score.Score;

public class CubeTowerController extends AnimationTimer implements Initializable {

	private long last;
	private Cube cube;
	private Tower tower;
	private boolean inicio = true;
	private boolean play = true;
	private double speed;
	private int size;
	private int bonificacion;
	private String nivelPuntos;
	private int partidas = 1;
	private int puntos;

	private IntegerProperty score = new SimpleIntegerProperty();
	private StringProperty info= new SimpleStringProperty();

	@FXML
	private GridPane pixeles;

	@FXML
	private Label puntosLabel;
	
	@FXML
    private Label alertaInfo;

    @FXML
    private VBox alertaVbox;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button continuarButton;

	@FXML
	private BorderPane view;

	public CubeTowerController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		puntosLabel.textProperty().bind(score.asString());
		alertaInfo.textProperty().bind(info);

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
		setSize(4);
		setBonificacion(2);
		load_score();
	}

	public BorderPane getView() {
		return view;
	}

	public void load_score() {
		score.set(Score.getInstance().getTotalScore());
	}

	private void you_win(String nivelPuntos) {
		score.set(score.get() + Integer.parseInt(nivelPuntos));
		int antesPuntos=BaseController.getInstance().getEstadisticas().getPuntosAntes();
		Score.getInstance().setTotalScore(score.intValue());
		BaseController.getInstance().getEstadisticas().setPuntosDespues(antesPuntos+score.intValue());
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
				if (getNivel() == 1) {
					nivelPuntos = 0 + "";
				} else {
					nivelPuntos = getNivel() * getBonificacion() + "";

				}
				alertaVbox.setVisible(true);
				info.set("HAS PERDIDO\nNIVEL: " + getNivel() + "\nPUNTOS GANADOS: " + nivelPuntos + "\n¿Volver a intentar?");
				you_win(nivelPuntos);
			} else {
				cube.moveUp();
				if (cube.getY() < 0) {
					play = true;
					stop();
					alertaVbox.setVisible(true);
					info.set("HAS GANADO\nNIVEL: " + getNivel() + "\nPUNTOS GANADOS: " + getNivel() * getBonificacion() + "\n¿Volver a intentar?");
					you_win(getNivel() * getBonificacion() + "");
				}
			}
		} else {
			// la primera vez sólo subimos el cubo un nivel
			cube.moveUp();
			inicio = false;
		}
	}
	public void playCubo() {
		inicio = true;
		play = false;
		cube = new Cube(0, tower.getRows() - 1, getSize());// setsize();
		tower.clear();
		BaseController.getInstance().getEstadisticas().setPartidasCubo(partidas);
		partidas++;
		last = System.nanoTime();
		start();
	}

	@FXML
	void onNivelesAction(ActionEvent event) throws IOException {
		BaseController.getInstance().showLevelTower();
		alertaVbox.setVisible(false);
	}
	
	@FXML
    void onCancelarAction(ActionEvent event) {
		alertaVbox.setVisible(false);
		BaseController.getInstance().showMenu();
		tower.clear();
		stop();
    }

    @FXML
    void onContinuarAction(ActionEvent event) {
    	alertaVbox.setVisible(false);
    	playCubo();
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

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}

	public GridPane getPixeles() {
		return pixeles;
	}

	public void setPixeles(GridPane pixeles) {
		this.pixeles = pixeles;
	}
}
