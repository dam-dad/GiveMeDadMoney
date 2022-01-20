package menuController;

import java.io.File;
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

public class MenuRootController implements Initializable {

	@FXML
	private Button exitButton;

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

	public MenuRootController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/MenuRootView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreLabel.textProperty().bind(total_score.asString());

		score_file.addListener((o, ov, nv) -> {
			System.out.println("listo");

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

	private void saveScore() {
		try {
			getScore_file().setTotalScore(total_score.get());
			getScore_file().save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void onExitAction(ActionEvent event) {
		saveScore();
	}

	@FXML
	void onSettingsAction(ActionEvent event) {

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
