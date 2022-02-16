package mayorOMenor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
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
import javafx.util.Duration;
import menuController.BaseController;
import score.Score;

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
	


	private BooleanProperty activa = new SimpleBooleanProperty();	
	private BooleanProperty apostable = new SimpleBooleanProperty();

	// pruebas
	TranslateTransition carta_casa = new TranslateTransition();
	TranslateTransition carta_propia = new TranslateTransition();

	public MayorOMenorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MayorOMenor/MayorOMenor.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		// Bindings
		puntosLabel.textProperty().bind(score.asString());
		apuestaText.textProperty().bindBidirectional(apuesta);

		// Fuerza que sea numerico
		apuestaText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					apuestaText.setText(newValue.replaceAll("[^\\d]", ""));
				}
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

	public void load_score() {
		score.set(Score.getInstance().getTotalScore());
	}

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

	private void load_HomeCard() {
		int homeCard_num = (int) (Math.random() * 10) + 1;
		homeNum.set(homeCard_num);
	}

	private void reveal_homeCard() {
		String url = "/images/MayorOMenor/" + homeNum.get() + ".png";
		homeCard.setImage(new Image(url));
		activa.set(false);
		apuesta.set("");
		
		apuestaText.setDisable(true);
		
	}
	


	private void cargar_carta() {
		carta_casa.setNode(homeCard);
		carta_casa.setFromY(-250);
		carta_casa.setToY(0);
		carta_casa.setDuration(Duration.seconds(0.50));
		carta_casa.playFromStart();
		
	}

	private void shuffle() {
		String url = "/images/MayorOMenor/backCarta.png";
		homeCard.setImage(new Image(url));
		load_HomeCard();
		load_MyCard();
		activa.set(true);
		

	}

	@FXML
	void onShuffleAction(ActionEvent event) {
		shuffle();
		apuestaText.setDisable(false);
	}

	@FXML
	void onBiggerAction(ActionEvent event) {
		cargar_carta();
		if (myNum.get() >= homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		reveal_homeCard();
	}

	@FXML
	void onLessButton(ActionEvent event) {
		cargar_carta();
		if (myNum.get() <= homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		reveal_homeCard();
	}

	@FXML
	void onEqualButton(ActionEvent event) {
		cargar_carta();
		if (myNum.get() == homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		reveal_homeCard();
	}
	
	private void you_loose() {
		score.set(score.get() - Integer.parseInt(apuesta.get()));
	}

	private void you_win() {
		score.set(score.get() + Integer.parseInt(apuesta.get()));
	}

	@FXML
	void onBackAction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	public BorderPane getView() {
		return root;
	}
}
