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
	private Button dineroButton;

	@FXML
	private Button dineroButton1;

	@FXML
	private ImageView gifView;

	@FXML
	private Button menuButton;

	@FXML
	private VBox rewardVBox;

	@FXML
	private BorderPane root;

	@FXML
	private Button xokasButton;

	@FXML
	void onMenuAction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	Premios premios;

	public StoreController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Store/Store.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		premios = new Premios();
	}

	@FXML
	void onDineroAction(ActionEvent event) {
		rewardVBox.setVisible(true);
		gifView.setImage(premios.getGifDinero());
	}

	@FXML
	void onOkayAction(ActionEvent event) {
		rewardVBox.setVisible(true);
		gifView.setImage(premios.getGifOkay());
	}

	@FXML
	void onXokasAction(ActionEvent event) {
		rewardVBox.setVisible(true);
		gifView.setImage(premios.getGifXokas());
	}

	@FXML
	void onBackAction(ActionEvent event) {
		rewardVBox.setVisible(false);
	}

	public BorderPane getView() {
		return root;
	}

}
