package menuController;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	private Button startButton;
	
	
	
	// MODEL
	private IntegerProperty total_score = new SimpleIntegerProperty();
	private ObjectProperty<Score> score_file = new SimpleObjectProperty<>();

	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/MenuView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreLabel.textProperty().bind(total_score.asString());
		getView().getStylesheets().add("/css/Menu/menu.css");

		score_file.addListener((o, ov, nv) -> {
			System.out.println("score");
			total_score.set(nv.getTotalScore());
		});

		load_score();
	}

	private void load_score() {
		Score score = new Score();
		try {
			score_file.set(score.read());
			total_score.set(score_file.getValue().getTotalScore());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void load_total_score() {
		int suma_score = 0 ;
		for (int i = 1; i < score_file.getValue().getGame().size(); i++) {
			suma_score += score_file.getValue().getGame().get(i).getGameScore();
		}
		total_score.set(suma_score);
	}


	@FXML
	void onSettingsAction(ActionEvent event) throws IOException {
		BaseController.getInstance().showSetting(score_file.get());

		//settingsController.setScore_file(getScore_file());
		//settingsController.showOnStage(GiveMeDADMoney.getPrimaryStage());
		//setScore_file(settingsController.getScore_file());
	}

	@FXML
	void onStartAction(ActionEvent event) {

	}

	public BorderPane getView() {
		return menuRootView;
	}

	public final ObjectProperty<Score> score_fileProperty() {
		return this.score_file;
	}

	public final Score getScore_file() {
		return this.score_fileProperty().get();
	}

	public final void setScore_file(final Score score_file) {
		this.score_fileProperty().set(score_file);
	}
}
