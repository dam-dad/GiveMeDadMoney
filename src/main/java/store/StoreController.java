package store;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import menuController.BaseController;
import score.Score;

public class StoreController implements Initializable {

	private Premios premios;

	private IntegerProperty score = new SimpleIntegerProperty();

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
	private Label labelPuntos;

	@FXML
	void onMenuAction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	public StoreController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Store/Store.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelPuntos.textProperty().bind(score.asString());
		premios = new Premios();
		load_score();
	}

	@FXML
	void onDineroAction(ActionEvent event) {
		rewardVBox.setVisible(true);
		gifView.setImage(premios.getGifDinero());
		you_lose(500+"");
	}

	@FXML
	void onOkayAction(ActionEvent event) {
		rewardVBox.setVisible(true);
		gifView.setImage(premios.getGifOkay());
		you_lose(500+"");
	}

	@FXML
	void onXokasAction(ActionEvent event) {
		rewardVBox.setVisible(true);
		gifView.setImage(premios.getGifXokas());
		you_lose(500+"");
	}

	@FXML
	void onBackAction(ActionEvent event) {
		rewardVBox.setVisible(false);
	}

	private void you_lose(String nivelPuntos) {
		score.set(score.get() - Integer.parseInt(nivelPuntos));
		Score.getInstance().setTotalScore(score.intValue());
	}
	
	public void load_score() {
		score.set(Score.getInstance().getTotalScore());
	}

	public BorderPane getView() {
		return root;
	}

}
