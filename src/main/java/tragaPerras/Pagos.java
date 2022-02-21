package tragaPerras;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Pagos extends VBox implements Initializable {

    @FXML
    private VBox view;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Pagos() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TragaPerras/Pagos.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getView() {
        return view;
    }
}
