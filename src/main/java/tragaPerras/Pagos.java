package tragaPerras;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import menuController.BaseController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The type Pagos.
 */
public class Pagos implements Initializable {

    @FXML
    private VBox view;

    @FXML
    private Button volverButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Instantiates a new Pagos.
     *
     * @throws IOException the io exception
     */
    public Pagos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TragaPerras/Pagos.fxml"));
        loader.setController(this);
        loader.load();
    }

    /**
     * On volver action.
     *
     * @param event the event
     */
    @FXML
    void onVolverAction(ActionEvent event) {
        BaseController.getInstance().showTragaPerras();
    }

    /**
     * Gets view.
     *
     * @return the view
     */
    public VBox getView() {
        return view;
    }
}
