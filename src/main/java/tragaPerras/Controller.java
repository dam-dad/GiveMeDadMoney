package tragaPerras;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import menuController.BaseController;
import score.Score;

public class Controller implements Initializable {

	private IntegerProperty puntosTotales = new SimpleIntegerProperty();
	private IntegerProperty bottom1 = new SimpleIntegerProperty();
	private IntegerProperty bottom2 = new SimpleIntegerProperty();
	private IntegerProperty bottom3 = new SimpleIntegerProperty();

	private int sumaPuntos;
	private int tiradas = 1;

	private Figura figura1 = new Figura();
	private Figura figura2 = new Figura();
	private Figura figura3 = new Figura();

	private Pagos tablaPagos = new Pagos();

	@FXML
	private Button apuestaButton, volverButton;

	@FXML
	private HBox buttonsContainer;

	@FXML
	private Text puntosText;

	@FXML
	private HBox figuraContainers;

	@FXML
	private TextField apuestaText;

	@FXML
	private HBox resultTextContainer, cabeceraBox;

	@FXML
	private BorderPane view;

	@FXML
	private Button cancelarButton;

	@FXML
	private Button continuarButton;

	// alerta

	private StringProperty info = new SimpleStringProperty();
	@FXML
	private Label alertaInfo;

	@FXML
	private VBox alertaVbox;

	@FXML
	void onCancelarAction(ActionEvent event) {
		alertaVbox.setVisible(false);
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onContinuarAction(ActionEvent event) {
		alertaVbox.setVisible(false);

	}

	@FXML
	void MostrarPagos(ActionEvent event) {
		cabeceraBox.getChildren().add(tablaPagos);
		//tablaPagos.setVisible(true);

	}

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TragaPerras/MaquinaTP.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		alertaInfo.textProperty().bind(info);

		figuraContainers.getChildren().add(figura1);
		figuraContainers.getChildren().add(figura2);
		figuraContainers.getChildren().add(figura3);
		apuestaButton.disableProperty().bind(apuestaText.textProperty().isEmpty());



		puntosText.textProperty().bind(puntosTotales.asString());
		load_score();
	}

	@FXML
	void apuesta(ActionEvent event) {
		if (isNumeric(apuestaText.textProperty().getValue())) {
			figura1.roll();
			figura2.roll();
			figura3.roll();

			juego(Integer.parseInt(apuestaText.textProperty().getValue()));

			// Guarda puntos
			Score.getInstance().setTotalScore(puntosTotales.intValue());
			BaseController.getInstance().getEstadisticas().setPartidasCubo(tiradas);
			tiradas++;
			int antesPuntos = BaseController.getInstance().getEstadisticas().getPuntosAntes();
			BaseController.getInstance().getEstadisticas().setPuntosDespues(antesPuntos + puntosTotales.intValue());
		} else {
			alertaVbox.setVisible(true);
			info.set("Carácter inválido. \n El valor introducido no es un número.");
		}
	}

	@FXML
	void volver(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	public BorderPane getView() {
		return view;
	}

	public void juego(int numeroApuesta) {

		sumaPuntos = puntosTotales.get();

		if (sumaPuntos >= numeroApuesta && sumaPuntos != 0) {

			sumaPuntos += recompensas(figura1.getValueImagen(), figura2.getValueImagen(), figura3.getValueImagen(),
					numeroApuesta) - numeroApuesta;
			puntosTotales.set(sumaPuntos);

			bottom1.set((int) (Math.random() * Imagen.IMAGENES.size()));
			bottom2.set((int) (Math.random() * Imagen.IMAGENES.size()));
			bottom3.set((int) (Math.random() * Imagen.IMAGENES.size()));

		} else {
			alertaVbox.setVisible(true);
			info.set("Puntos Insuficientes. \n Necesitas mas puntos para poder jugar.");
		}

	}

	public TranslateTransition translateTransition(ImageView top, ImageView bottom) {
		TranslateTransition transition = new TranslateTransition();

		transition.setNode(top);
		transition.setFromY(10);
		transition.setToY(bottom.getFitHeight() + 20);
		transition.setDuration(Duration.seconds(0.10));
		transition.setInterpolator(Interpolator.LINEAR);
		return transition;

	}

	public static void listener(IntegerProperty bottomProperty, IntegerProperty topProperty, ImageView top,
			ImageView bottom) {

		bottomProperty.addListener((o, ov, nv) -> {
			topProperty.set((nv.intValue() + 1) % Imagen.IMAGENES.size());
			bottom.setImage(top.getImage());
			top.setImage(Imagen.IMAGENES.get(bottomProperty.get()).getImagen());
		});

	}

	public int recompensas(int valor1, int valor2, int valor3, int numeroApuesta) {

		int numero = 0;

		if (valor1 == valor2 && valor1 == valor3) {

			switch (valor1) {
			case 10:
				numero = valor1 * 2;
				break;
			case 25:
				numero = valor1 * 3;
				break;
			case 35:
				numero = valor1 * 4;
				break;
			case 50:
				numero = valor1 * 5;
				break;
			case 65:
				numero = valor1 * 6;
				break;
			case 75:
				numero = valor1 * 7;
				break;
			case 120:
				numero = valor1 * 8;
				break;

			}
			numero += numeroApuesta;

		} else if (valor1 == valor2) {

			switch (valor1) {
			case 10:
				numero = (int) (valor1 * 0.5);
				break;
			case 25:
				numero = (int) (valor1 * 0.75);
				break;
			case 35:
				numero = valor1;
				break;
			case 50:
				numero = (int) (valor1 * 1.25);
				break;
			case 65:
				numero = (int) (valor1 * 1.5);
				break;
			case 75:
				numero = (int) (valor1 * 2.5);
				break;
			case 120:
				numero = valor1 * 3;
				break;

			}
			numero += numeroApuesta;

		}
		if (valor2 == valor3) {

			switch (valor2) {
			case 10:
				numero = (int) (valor2 * 0.5);
				break;
			case 25:
				numero = (int) (valor2 * 0.75);
				break;
			case 35:
				numero = valor2;
				break;
			case 50:
				numero = (int) (valor2 * 1.5);
				break;
			case 65:
				numero = valor2 * 2;
				break;
			case 75:
				numero = (int) (valor2 * 2.5);
				break;
			case 120:
				numero = valor2 * 3;
				break;

			}
			numero += numeroApuesta;

		}

		return numero;
	}

	public void load_score() {
		puntosTotales.set(Score.getInstance().getTotalScore());
	}

	public static boolean isNumeric(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException exception) {
			resultado = false;
		}

		return resultado;
	}
}
