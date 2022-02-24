package tragaPerras;

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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import menuController.BaseController;
import score.Score;

/**
 * The type Controller.
 */
public class Controller implements Initializable {

	private IntegerProperty puntosTotales = new SimpleIntegerProperty();

	private int sumaPuntos;
	private int tiradas = 1;

	private Figura figura1 = new Figura();
	private Figura figura2 = new Figura();
	private Figura figura3 = new Figura();

	
	private static Controller instance;

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

	/**
	 * On cancelar action.
	 *
	 * @param event the event
	 */
	@FXML
	void onCancelarAction(ActionEvent event) {
		alertaVbox.setVisible(false);
		BaseController.getInstance().showMenu();
	}

	/**
	 * On continuar action.
	 *
	 * @param event the event
	 */
	@FXML
	void onContinuarAction(ActionEvent event) {
		alertaVbox.setVisible(false);
	}

	/**
	 * Mostrar pagos.
	 *
	 * @param event the event
	 */
	@FXML
	void MostrarPagos(ActionEvent event) {
		BaseController.getInstance().showTablaPagos();

	}

	/**
	 * Instantiates a new Controller.
	 *
	 * @throws IOException the io exception
	 */
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

	/**
	 * Apuesta.
	 *
	 * @param event the event
	 */
	@FXML
	void apuesta(ActionEvent event) {
		if (isNumeric(apuestaText.textProperty().getValue())) {
			BaseController.getInstance().play_sound();

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

	/**
	 * Volver.
	 *
	 * @param event the event
	 */
	@FXML
	void volver(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	/**
	 * Gets view. Obtienes la vista de la tragaperras
	 *
	 * @return the view
	 */
	public BorderPane getView() {
		return view;
	}

	/**
	 * Juego. Recoge la información necesaria y la envía a otra función
	 *
	 * @param numeroApuesta the numero apuesta
	 */
	public void juego(int numeroApuesta) {

		sumaPuntos = puntosTotales.get();

		if (sumaPuntos >= numeroApuesta && sumaPuntos != 0) {
			figura1.roll();
			figura2.roll();
			figura3.roll();

			sumaPuntos += recompensas(figura1.getValueImagen(), figura2.getValueImagen(), figura3.getValueImagen(),
					numeroApuesta) - numeroApuesta;
			puntosTotales.set(sumaPuntos);

		} else {
			alertaVbox.setVisible(true);
			info.set("Puntos Insuficientes. \n Necesitas mas puntos para poder jugar.");
		}

	}

	/**
	 * Recompensas int. Se hace el calculo de los valores de la imagen
	 *
	 * @param valor1        the valor 1
	 * @param valor2        the valor 2
	 * @param valor3        the valor 3
	 * @param numeroApuesta the numero apuesta
	 * @return the int
	 */
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

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static Controller getInstance() {
		if (instance == null) {
			try {
				instance = new Controller();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}

	/**
	 * Show traga perras.
	 */
	public void showTragaPerras() {
		view.setCenter(getView());
	}

	/**
	 * Load score. Carga los puntos del la instancia Score al IntegerProperty del score
	 */
	public void load_score() {
		puntosTotales.set(Score.getInstance().getTotalScore());
	}

	/**
	 * Is numeric boolean. Comprueba si los datos introducidos son numeros y no letras
	 *
	 * @param cadena the cadena
	 * @return the boolean
	 */
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
