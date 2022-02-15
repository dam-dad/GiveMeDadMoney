package tragaPerras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.GiveMeDADMoney;
import menuController.BaseController;
import score.Score;

public class Controller implements Initializable {

	private ObjectProperty<Image> imagen1Property = new SimpleObjectProperty<>();
	private ObjectProperty<Image> imagen2Property = new SimpleObjectProperty<>();
	private ObjectProperty<Image> imagen3Property = new SimpleObjectProperty<>();
	private IntegerProperty puntosTotales = new SimpleIntegerProperty();
	private IntegerProperty top1 = new SimpleIntegerProperty();
	private IntegerProperty top2 = new SimpleIntegerProperty();
	private IntegerProperty top3 = new SimpleIntegerProperty();
	private IntegerProperty bottom1 = new SimpleIntegerProperty();
	private IntegerProperty bottom2 = new SimpleIntegerProperty();
	private IntegerProperty bottom3 = new SimpleIntegerProperty();

	private int valor1, valor2, valor3;
	private int sumaPuntos;
	private ParallelTransition transition;

	private int counter;

	TranslateTransition topTransition1 = new TranslateTransition();
	TranslateTransition topTransition2 = new TranslateTransition();
	TranslateTransition topTransition3 = new TranslateTransition();
	TranslateTransition bottomTransition1 = new TranslateTransition();
	TranslateTransition bottomTransition2 = new TranslateTransition();
	TranslateTransition bottomTransition3 = new TranslateTransition();

	@FXML
	private Button apuestaButton, volverButton;

	@FXML
	private ImageView bottomImage1, topImage1, bottomImage2, topImage2, bottomImage3, topImage3;

	@FXML
	private HBox buttonsContainer;

	@FXML
	private Text puntosText;

	@FXML
	private TextField apuestaText;

	@FXML
	private HBox resultTextContainer;

	@FXML
	private BorderPane view;

	@FXML
	void MostrarPagos(ActionEvent event) {

	}

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TragaPerras/MaquinaTP.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		view.getStylesheets().add("css/TragaPerras/TragaPerras.css");

		apuestaButton.disableProperty().bind(apuestaText.textProperty().isEmpty());

		puntosText.textProperty().bind(puntosTotales.asString());
		load_score();

		topTransition1 = translateTransition(topImage1, bottomImage1);
		topTransition2 = translateTransition(topImage2, bottomImage2);
		topTransition3 = translateTransition(topImage3, bottomImage3);

		bottomTransition1 = translateTransition(bottomImage1, bottomImage1);
		bottomTransition2 = translateTransition(bottomImage2, bottomImage2);
		bottomTransition3 = translateTransition(bottomImage3, bottomImage3);

		transition = new ParallelTransition(topTransition1, bottomTransition1, topTransition2, bottomTransition2,
				topTransition3, bottomTransition3);
		transition.setOnFinished(e -> {
			counter--;
			if (counter >= 0) {

				if (counter == 0) {
					bottomTransition1.setInterpolator(Interpolator.EASE_OUT);
					bottomTransition2.setInterpolator(Interpolator.EASE_OUT);
					bottomTransition3.setInterpolator(Interpolator.EASE_OUT);
					topTransition1.setInterpolator(Interpolator.EASE_OUT);
					topTransition2.setInterpolator(Interpolator.EASE_OUT);
					topTransition3.setInterpolator(Interpolator.EASE_OUT);
				}

				bottom1.set((bottom1.get() + 1) % Imagen.IMAGENES.size());
				bottom2.set((bottom2.get() + 1) % Imagen.IMAGENES.size());
				bottom3.set((bottom3.get() + 1) % Imagen.IMAGENES.size());
				transition.play();
			}
		});

		listener(bottom1, top1, topImage1, bottomImage1);
		listener(bottom2, top2, topImage2, bottomImage2);
		listener(bottom3, top3, topImage3, bottomImage3);

	}

	private void saveScore() {
		Score.getInstance().setTotalScore(puntosTotales.intValue());
	}

	@FXML
	void apuesta(ActionEvent event) {
		if (isNumeric(apuestaText.textProperty().getValue())) {

			counter = 10;
			bottom1.set(((bottom1.get() - 1) < 0 ? Imagen.IMAGENES.size() : bottom1.get()) - 1);
			bottom2.set(((bottom2.get() - 1) < 0 ? Imagen.IMAGENES.size() : bottom2.get()) - 1);
			bottom3.set(((bottom3.get() - 1) < 0 ? Imagen.IMAGENES.size() : bottom3.get()) - 1);
			transition.play();

			juego(Integer.parseInt(apuestaText.textProperty().getValue()));
			saveScore();
		} else {
			GiveMeDADMoney.error("Error de formato.", "Carácter inválido.", "El valor introducido no es un número.");
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

			Imagen i1 = Imagen.randomImagen();
			imagen1Property.set(i1.getImagen());
			valor1 = i1.getValor();

			Imagen i2 = Imagen.randomImagen();
			imagen2Property.set(i2.getImagen());
			valor2 = i2.getValor();

			Imagen i3 = Imagen.randomImagen();
			imagen3Property.set(i3.getImagen());
			valor3 = i3.getValor();

			sumaPuntos += recompensas(valor1, valor2, valor3, numeroApuesta) - numeroApuesta;
			puntosTotales.set(sumaPuntos);

			bottom1.set((int) (Math.random() * Imagen.IMAGENES.size()));
			bottom2.set((int) (Math.random() * Imagen.IMAGENES.size()));
			bottom3.set((int) (Math.random() * Imagen.IMAGENES.size()));

		} else {
			GiveMeDADMoney.error("Información de Puntos", "Puntos Insuficientes.",
					"Necesitas mas puntos para poder jugar.");
		}

	}

	public TranslateTransition translateTransition(ImageView top, ImageView bottom) {
		TranslateTransition transition = new TranslateTransition();

		transition.setNode(top);
		transition.setFromY(10);
		transition.setToY(bottom.getFitHeight() + 14);
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
