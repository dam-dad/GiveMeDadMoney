package store;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import menuController.BaseController;
import score.Score;

/**
 * The type Store controller.
 */
public class StoreController implements Initializable {

	private Premios premios;

	private IntegerProperty score = new SimpleIntegerProperty();
	

	@FXML
    private Label alertaInfo;

    @FXML
    private VBox alertaVbox;

    @FXML
    private Button backButton;

    @FXML
    private Button continuarButton;

    @FXML
    private Button dineroButton;

    @FXML
    private ImageView gifView;

    @FXML
    private Label labelPuntos;

    @FXML
    private Button menuButton;

    @FXML
    private Button okayButton;

    @FXML
    private VBox rewardVBox;

    @FXML
    private BorderPane root;

    @FXML
    private Button xokasButton;
    
    private StringProperty info = new SimpleStringProperty();


    @FXML
    void onContinuarAction(ActionEvent event) {
    	alertaVbox.setVisible(false);
    }


	/**
	 * On menu action.
	 *
	 * @param event the event
	 */
	@FXML
	void onMenuAction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	/**
	 * Instantiates a new Store controller.
	 *
	 * @throws IOException the io exception
	 */
	public StoreController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Store/Store.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		alertaInfo.textProperty().bind(info);

		labelPuntos.textProperty().bind(score.asString());
		premios = new Premios();
		load_score();
	}

	/**
	 * On dinero action.
	 *
	 * @param event the event
	 */
	@FXML
	void onDineroAction(ActionEvent event) {
		if (lose_money(500 + "") == true) {
			rewardVBox.setVisible(true);
			gifView.setImage(premios.getGifDinero());
		}else {
	    	alertaVbox.setVisible(true);
	    	info.set("Puntos Insuficientes. \n No tienes tantos puntos para pagar.");
		}
	}
	/**
	 * On okay action.
	 *
	 * @param event the event
	 */

	@FXML
	void onOkayAction(ActionEvent event) {
		if (lose_money(500 + "") == true) {
			rewardVBox.setVisible(true);
			gifView.setImage(premios.getGifOkay());
		}else {
	    	alertaVbox.setVisible(true);
	    	info.set("Puntos Insuficientes. \n No tienes tantos puntos para pagar.");

		}
	}

	/**
	 * On xokas action.
	 *
	 * @param event the event
	 */
	@FXML
	void onXokasAction(ActionEvent event) {
		if (lose_money(500 + "") == true) {
			rewardVBox.setVisible(true);
			gifView.setImage(premios.getGifXokas());
		}else {
			alertaVbox.setVisible(true);
			info.set("Puntos Insuficientes. \n No tienes tantos puntos para pagar.");
		}
	}

	/**
	 * On back action.
	 *
	 * @param event the event
	 */
	@FXML
	void onBackAction(ActionEvent event) {
		rewardVBox.setVisible(false);
	}

	private boolean lose_money(String precio) {
		int cantidad = Integer.parseInt(precio);
		if (score.get() >= cantidad) {
			score.set(score.get() - cantidad);
			Score.getInstance().setTotalScore(score.intValue());
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Load score.
	 */
	public void load_score() {
		score.set(Score.getInstance().getTotalScore());
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
