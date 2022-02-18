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

public class StoreController implements Initializable {

	@FXML
	private ImageView ima;

	@FXML
	private Button prueba;

	@FXML
	private Button reward1Button;

	@FXML
	private VBox rewardVBox;

	@FXML
	private BorderPane root;

	@FXML
	void onReward1Action(ActionEvent event) {
		rewardVBox.setVisible(true);
	}

	@FXML
	void prueba(ActionEvent event) {
		ima.setImage(new Image("/images/giphy.gif"));
		
		
	}

	public StoreController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Store/Store.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public BorderPane getView() {
		return root;
	}

}
