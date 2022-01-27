package tragaPerras;

import javafx.beans.property.ObjectProperty;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private ObjectProperty<Image> imagen1Property = new SimpleObjectProperty<>();
    private ObjectProperty<Image> imagen2Property = new SimpleObjectProperty<>();
    private ObjectProperty<Image> imagen3Property = new SimpleObjectProperty<>();
    private StringProperty puntosTotales = new SimpleStringProperty();

    private Imagen imagen1 = new Imagen();
    private Imagen imagen2 = new Imagen();
    private Imagen imagen3 = new Imagen();
    private Imagen cambio = new Imagen();

    private int valor1, valor2, valor3;
    private double sumaPuntos;

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

        puntosText.textProperty().bind(puntosTotales);

        imagen1Property.set(sieteImagen);
        imagen2Property.set(sieteImagen);
        imagen3Property.set(sieteImagen);

        puntosTotales.set("1000");

    }

    @FXML
    void apuesta1(ActionEvent event) {

        sumaPuntos = Integer.parseInt(puntosTotales.get());

        if (sumaPuntos >= 1) {
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

            sumaPuntos += recompensas(valor1, valor2, valor3) - 10;
            puntosTotales.set(String.valueOf(Math.round(sumaPuntos)));
        } else {
            App.alertaPuntos();
        }

    }

    @FXML
    void apuesta2(ActionEvent event) {

        sumaPuntos = Integer.parseInt(puntosTotales.get());

        if (sumaPuntos >= 2) {
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

            sumaPuntos += recompensas(valor1, valor2, valor3) - 20;
            puntosTotales.set(String.valueOf(Math.round(sumaPuntos)));
        } else {
            App.alertaPuntos();
        }

    }

    @FXML
    void apuesta5(ActionEvent event) {

        sumaPuntos = Integer.parseInt(puntosTotales.get());

        if (sumaPuntos >= 5) {
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

            sumaPuntos += recompensas(valor1, valor2, valor3) - 50;
            puntosTotales.set(String.valueOf(Math.round(sumaPuntos)));
        } else {
            App.alertaPuntos();
        }

    }

    public BorderPane getView() {
        return view;
    }

    public double recompensas(int valor1, int valor2, int valor3) {

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

        }

        return numero;
    }
}
