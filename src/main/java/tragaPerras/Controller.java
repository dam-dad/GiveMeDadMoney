package tragaPerras;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    void apuesta1(ActionEvent event) {

    }

    @FXML
    void apuesta2(ActionEvent event) {

    }

    @FXML
    void apuesta5(ActionEvent event) {

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

    }

    public BorderPane getView() {
        return view;
    }
}
