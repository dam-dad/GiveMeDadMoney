package tragaPerras;

import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.GiveMeDADMoney;
import score.Score;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private ObjectProperty<Image> imagen1Property = new SimpleObjectProperty<>();
    private ObjectProperty<Image> imagen2Property = new SimpleObjectProperty<>();
    private ObjectProperty<Image> imagen3Property = new SimpleObjectProperty<>();
    private IntegerProperty puntosTotales = new SimpleIntegerProperty();

    private Imagen imagen1 = new Imagen();
    private Imagen imagen2 = new Imagen();
    private Imagen imagen3 = new Imagen();
    private Imagen cambio = new Imagen();

    private int valor1, valor2, valor3;
    private double sumaPuntos;

    TranslateTransition transition1 = new TranslateTransition();
    TranslateTransition transition2 = new TranslateTransition();
    TranslateTransition transition3 = new TranslateTransition();

    @FXML
    private Button apuesta1Button;

    @FXML
    private Button apuesta2Button;

    @FXML
    private Button apuesta5Button;

    @FXML
    private HBox buttonsContainer;

    @FXML
    private Text highScoreText;

    @FXML
    private ImageView imageBlock1;

    @FXML
    private ImageView imageBlock2;

    @FXML
    private ImageView imageBlock3;

    @FXML
    private Button playAgainButton;

    @FXML
    private HBox playAgainContainer;

    @FXML
    private Text puntosText;

    @FXML
    private Text resultText;

    @FXML
    private HBox resultTextContainer;

    @FXML
    private BorderPane view;

    @FXML
    void MostrarPagos(ActionEvent event) {

    }

    @FXML
    void juegaDeNuevo(ActionEvent event) {

    }

    public Controller() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TragaPerras/MaquinaTP.fxml"));
        loader.setController(this);
        loader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image sieteImagen = new Image(imagen1.siete().getRuta());

        imageBlock1.imageProperty().bind(imagen1Property);
        imageBlock2.imageProperty().bind(imagen2Property);
        imageBlock3.imageProperty().bind(imagen3Property);

       

        imagen1Property.set(sieteImagen);
        imagen2Property.set(sieteImagen);
        imagen3Property.set(sieteImagen);

        puntosTotales.set(Score.getInstance().getTotalScore());

        transition1.setNode(imageBlock1);
        transition1.setFromY(-250);
        transition1.setToY(0);
        transition1.setCycleCount(7);
        transition1.setDuration(Duration.seconds(0.10));

        transition2.setNode(imageBlock2);
        transition2.setFromY(-250);
        transition2.setToY(0);
        transition2.setCycleCount(10);
        transition2.setDuration(Duration.seconds(0.10));

        transition3.setNode(imageBlock3);
        transition3.setFromY(-250);
        transition3.setToY(0);
        transition3.setCycleCount(13);
        transition3.setDuration(Duration.seconds(0.10));
    }
    
    private void saveScore() {
        Score.getInstance().setTotalScore(puntosTotales.intValue());
	}

    @FXML
    void apuesta1(ActionEvent event) {

        juego(10);
        saveScore();

    }

    @FXML
    void apuesta2(ActionEvent event) {

        juego(20);
        saveScore();

    }

    @FXML
    void apuesta5(ActionEvent event) {

        juego(50);
        saveScore();

    }

    public BorderPane getView() {
        return view;
    }

    public void juego(int numeroApuesta) {

        sumaPuntos = puntosTotales.get();

        if (sumaPuntos >= numeroApuesta) {
            transition1.play();
            transition2.play();
            transition3.play();

            Image imagenNew;

            cambio = imagen1.randomImagen();

            imagenNew = new Image(cambio.getRuta());
            imagen1Property.set(imagenNew);
            valor1 = cambio.getValor();

            cambio = imagen2.randomImagen();
            imagenNew = new Image(cambio.getRuta());
            imagen2Property.set(imagenNew);
            valor2 = cambio.getValor();

            cambio = imagen3.randomImagen();
            imagenNew = new Image(cambio.getRuta());
            imagen3Property.set(imagenNew);
            valor3 = cambio.getValor();

            sumaPuntos += recompensas(valor1, valor2, valor3, numeroApuesta) - numeroApuesta;
            puntosTotales.set("" + Math.round(sumaPuntos));
        } else {
            GiveMeDADMoney.error("Información de Puntos", "Puntos Insuficientes.",
                    "Necesitas mas puntos para poder jugar.");
        }

    }

    public double recompensas(int valor1, int valor2, int valor3, int numeroApuesta) {

        double numero = 0;

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
                    numero = valor1 * 0.5;
                    break;
                case 25:
                    numero = valor1 * 0.75;
                    break;
                case 35:
                    numero = valor1 * 1;
                    break;
                case 50:
                    numero = valor1 * 1.25;
                    break;
                case 65:
                    numero = valor1 * 1.5;
                    break;
                case 75:
                    numero = valor1 * 2.5;
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
                    numero = valor2 * 0.5;
                    break;
                case 25:
                    numero = valor2 * 0.75;
                    break;
                case 35:
                    numero = valor2 * 1;
                    break;
                case 50:
                    numero = valor2 * 1.5;
                    break;
                case 65:
                    numero = valor2 * 2;
                    break;
                case 75:
                    numero = valor2 * 2.5;
                    break;
                case 120:
                    numero = valor2 * 3;
                    break;

            }
            numero += numeroApuesta;

        }

        return numero;
    }
}
