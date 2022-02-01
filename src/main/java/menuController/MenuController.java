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
import score.Score;


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

	
	// MODEL
	private IntegerProperty total_score = new SimpleIntegerProperty();
	
	
	
	
	

	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/MenuView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreLabel.textProperty().bind(total_score.asString());
		getView().getStylesheets().add("/css/Menu/menu.css");

		load_score();
		
	}
	public void load_score() {
		total_score.set(Score.getInstance().getTotalScore());
	}
	
	@FXML
    void onTragaperrasAction(ActionEvent event) {
		BaseController.getInstance().showTragaPerras();
    }
		
	@FXML
	void onSettingsAction(ActionEvent event) throws IOException {
		BaseController.getInstance().showSetting();

	}
	
	@FXML
    void onMayorOMenorAction(ActionEvent event) {
		BaseController.getInstance().showMayorOMenor();
    }

	@FXML
	void onCubeTowerAction	(ActionEvent event) {
		BaseController.getInstance().showCubeTower();
	}

	public BorderPane getView() {
		return menuRootView;
	}

}
