package menuController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import score.Score;


/**
 * The type Menu controller.
 */
public class MenuController implements Initializable {

	
	@FXML
	private BorderPane menuRootView;

	@FXML
	private Label scoreLabel;

	@FXML
	private Button settingsButton;

	@FXML
	private Button cubeTowerButton;
	
	@FXML
    private Button tragaperrasButton;
	
	@FXML
    private Button mayorOmenorButton;
	
    @FXML
    private Button storeButton;

	
	// MODEL
	private IntegerProperty total_score = new SimpleIntegerProperty();


	/**
	 * Instantiates a new Menu controller.
	 *
	 * @throws IOException the io exception
	 */
	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/MenuView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreLabel.textProperty().bind(total_score.asString());
		getView().getStylesheets().add("/css/style.css");

		Font.loadFont(getClass().getResourceAsStream("/fonts/ELEPHANT.TFF"), 14);

		load_score(); 
		
	}

	/**
	 * Load score.
	 */
	public void load_score() {
		total_score.set(Score.getInstance().getTotalScore());
	}

	/**
	 * On tragaperras action.
	 *
	 * @param event the event
	 */
	@FXML
    void onTragaperrasAction(ActionEvent event) {
		BaseController.getInstance().showTragaPerras();
    }

	/**
	 * On settings action.
	 *
	 * @param event the event
	 * @throws IOException the io exception
	 */
	@FXML
	void onSettingsAction(ActionEvent event) throws IOException {
		BaseController.getInstance().showSetting();

	}

	/**
	 * On mayor o menor action.
	 *
	 * @param event the event
	 */
	@FXML
    void onMayorOMenorAction(ActionEvent event) {
		BaseController.getInstance().showMayorOMenor();
    }

	/**
	 * On cube tower action.
	 *
	 * @param event the event
	 */
	@FXML
	void onCubeTowerAction	(ActionEvent event) {
		BaseController.getInstance().showCubeTower();
	}

	/**
	 * On store action.
	 *
	 * @param event the event
	 */
	@FXML
    void onStoreAction(ActionEvent event) {
		BaseController.getInstance().showStore();
    }

	/**
	 * Gets view.
	 *
	 * @return the view
	 */
	public BorderPane getView() {
		return menuRootView;
	}

}
