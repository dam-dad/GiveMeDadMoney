package store;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import menuController.BaseController;

public class StoreController implements Initializable {

	@FXML
	private Button backButton;

	@FXML
	private ImageView gifView;

	@FXML
	private Button reward1Button;

	@FXML
	private VBox rewardVBox;

	@FXML
	private BorderPane root;

	@FXML
	private Button menuButton;

	@FXML
	void onMenuAction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onReward1Action(ActionEvent event) {
		rewardVBox.setVisible(true);
		gifView.setImage(new Image("/images/Store/gifFuncionan/tercero.gif"));
	}

	public StoreController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Store/Store.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void onBackAction(ActionEvent event) {
		rewardVBox.setVisible(false);
	}

	public BorderPane getView() {
		return root;
	}

}
