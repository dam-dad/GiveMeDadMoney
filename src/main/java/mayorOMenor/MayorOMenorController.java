package mayorOMenor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import menuController.BaseController;
import score.Score;

/**
 * The type Mayor o menor controller.
 */
public class MayorOMenorController implements Initializable {

	@FXML
	private TextField apuestaText;

	@FXML
	private Button backButton;

	@FXML
	private Button biggerButton;

	@FXML
	private Button equalButton;

	@FXML
	private ImageView homeCard;

	@FXML
	private Button lessButton;

	@FXML
	private ImageView myCard;

	@FXML
	private Label puntosLabel;

	@FXML
	private BorderPane root;

	@FXML
	private Button shuffleButton;

	private IntegerProperty myNum = new SimpleIntegerProperty();
	private IntegerProperty homeNum = new SimpleIntegerProperty();

	private IntegerProperty score = new SimpleIntegerProperty();
	private StringProperty apuesta = new SimpleStringProperty();

	private int partidas = 1;

	private BooleanProperty activa = new SimpleBooleanProperty();
	private BooleanProperty apostable = new SimpleBooleanProperty();

	TranslateTransition carta_casa = new TranslateTransition();
	TranslateTransition carta_propia = new TranslateTransition();

	// Alerta
	@FXML
	private VBox alertaBox;

	@FXML
	private Label alertaInfo;

	@FXML
	private Button continuarButton;

	private StringProperty info = new SimpleStringProperty();

	/**
	 * On continuar action.
	 *
	 * @param event the event
	 */
	@FXML
	void onContinuarAction(ActionEvent event) {
		alertaBox.setVisible(false);
	}

	/**
	 * Instantiates a new Mayor o menor controller.
	 *
	 * @throws IOException the io exception
	 */
	public MayorOMenorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MayorOMenor/MayorOMenor.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		// Bindings
		puntosLabel.textProperty().bind(score.asString());
		apuestaText.textProperty().bindBidirectional(apuesta);

		alertaInfo.textProperty().bind(info);

		// Fuerza que sea numerico
		apuestaText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					apuestaText.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		apuesta.addListener((o, ov, nv) -> {
			int num = 0;
			if (!nv.equals("")) {
				try {
					num = Integer.parseInt(nv);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			if (num > score.get()) {
				alertaBox.setVisible(true);
				apuesta.set("");
				info.set("Puntos Insuficientes. \n No tienes tantos puntos para apostar.");
				
			}
		});

		apostable.bind(apuesta.isEmpty());
		biggerButton.disableProperty().bind(apostable);
		equalButton.disableProperty().bind(apostable);
		lessButton.disableProperty().bind(apostable);

		activa.set(false);
		shuffleButton.disableProperty().bind(activa);

		load_score();
		shuffle();
	}

	/**
	 * Carga los puntos del la instancia Score al IntegerProperty del score
	 */
	public void load_score() {
		score.set(Score.getInstance().getTotalScore());
	}

	/**
	 * Genera un numero aleatorio que se va a usar para seleccionar la foto que se
	 * va a usar, a su vez inicia una animacion de la carta apareciendo.
	 */
	private void load_MyCard() {
		int myCard_num = (int) (Math.random() * 10) + 1;
		myNum.set(myCard_num);

		String url = "/images/MayorOMenor/" + myCard_num + ".png";
		myCard.setImage(new Image(url));

		carta_propia.setNode(myCard);
		carta_propia.setFromY(250);
		carta_propia.setToY(0);
		carta_propia.setDuration(Duration.seconds(0.70));
		carta_propia.playFromStart();
	}

	/**
	 * Genera un numero aleatorio que se va a usar para seleccionar la foto que se
	 * va a usar.
	 */
	private void load_HomeCard() {
		int homeCard_num = (int) (Math.random() * 10) + 1;
		homeNum.set(homeCard_num);
	}

	/**
	 * Revela la carta de la casa la cual cambia la imagen del marco de la carta por
	 * la carta que deberia tener
	 */
	private void reveal_homeCard() {
		String url = "/images/MayorOMenor/" + homeNum.get() + ".png";
		homeCard.setImage(new Image(url));
		activa.set(false);
		apuesta.set("");

		apuestaText.setDisable(true);
	}

	/**
	 * Activa la animacion de la carta al entrar
	 */
	private void cargar_carta() {
		carta_casa.setNode(homeCard);
		carta_casa.setFromY(-250);
		carta_casa.setToY(0);
		carta_casa.setDuration(Duration.seconds(0.50));
		carta_casa.playFromStart();

	}

	/**
	 * Baraja las cartas(pide una nueva), y reinicia todo el juego para volver a
	 * jugar
	 */
	private void shuffle() {
		String url = "/images/MayorOMenor/backCarta.png";
		homeCard.setImage(new Image(url));
		load_HomeCard();
		load_MyCard();
		activa.set(true);
	}

	/**
	 * On shuffle action. Baraja las cartas y prohibe apostar
	 *
	 * @param event the event
	 */
	@FXML
	void onShuffleAction(ActionEvent event) {
		shuffle();
		apuestaText.setDisable(false);
	}

	/**
	 * On bigger action.Comprueba si acertaste en la apuesta y aumenta una las partidas jugadas para las estadisticas
	 *
	 * @param event the event
	 */
	@FXML
	void onBiggerAction(ActionEvent event) {
		cargar_carta();
		if (myNum.get() >= homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		reveal_homeCard();
		BaseController.getInstance().getEstadisticas().setPartidasCubo(partidas);
		partidas++;
	}

	/**
	 * On less button. Comprueba si acertaste en la apuesta y aumenta una las partidas jugadas para las estadisticas
	 *
	 * @param event the event
	 */
	@FXML
	void onLessButton(ActionEvent event) {
		cargar_carta();
		if (myNum.get() <= homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		reveal_homeCard();
		BaseController.getInstance().getEstadisticas().setPartidasCubo(partidas);
		partidas++;
	}

	/**
	 * On equal button. Comprueba si acertaste en la apuesta y aumenta una las partidas jugadas para las estadisticas
	 *
	 * @param event the event
	 */
	@FXML
	void onEqualButton(ActionEvent event) {
		cargar_carta();
		if (myNum.get() == homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		reveal_homeCard();
		BaseController.getInstance().getEstadisticas().setPartidasCubo(partidas);
		partidas++;
	}

	/**
	 * Ajusta los puntos dependiendo de cuanto has apostado
	 */
	private void you_loose() {
		score.set(score.get() - Integer.parseInt(apuesta.get()));
		Score.getInstance().setTotalScore(score.intValue());
		estadisticas(false);
	}

	/**
	 * Ajusta los puntos dependiendo de cuanto has apostado
	 */
	private void you_win() {
		score.set(score.get() + Integer.parseInt(apuesta.get()));
		Score.getInstance().setTotalScore(score.intValue());
		estadisticas(true);
	}

	/**
	 * 
	 * @param operando TRUE has ganado puntos, FALSE has perdido
	 */
	private void estadisticas(Boolean operando) {
		int antesPuntos = BaseController.getInstance().getEstadisticas().getPuntosAntes();
		if (operando == true) {
			BaseController.getInstance().getEstadisticas().setPuntosDespues(antesPuntos + score.intValue());
		} else {
			BaseController.getInstance().getEstadisticas().setPuntosDespues(antesPuntos - score.intValue());
		}
	}

	/**
	 * On back action. Vuelve al menu de la aplicacion
	 *
	 * @param event the event
	 */
	@FXML
	void onBackAction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	/**
	 * Gets view.
	 *
	 * @return the view
	 */
	public BorderPane getView() {
		return root;
	}
}
