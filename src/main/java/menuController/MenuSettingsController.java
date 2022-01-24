package menuController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.GiveMeDADMoney;
import score.Score;

public class MenuSettingsController implements Initializable {

	@FXML
	private Button changeUserNameButton;

	@FXML
	private BorderPane settingsView;

	@FXML
	private TextField userNameText;

	@FXML
	private Button downloadScoreButton;

	@FXML
	private Button importScoreButton;

	// MODELO
	private ObjectProperty<Score> score_file = new SimpleObjectProperty<>();

	private StringProperty userName = new SimpleStringProperty();

	private Stage stage;

	private FileChooser fileChooser;

	public MenuSettingsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/MenuSettingsView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userName.bind(userNameText.textProperty());

		// FILECHOOSER
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Puntuacion (*.xml)", "*.xml"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los ficheros (*.*)", "*.*"));
		fileChooser.setInitialDirectory(new File("."));

	}

	private void load() {
		userNameText.textProperty().set(score_file.getValue().getUserName());

	}

	@FXML
	void onChangeUserNameAction(ActionEvent event) {

		score_file.getValue().setUserName(userName.getValue());

		System.out.println(score_file.getValue().getUserName());
	}

	@FXML
	void onDownloadScoreAction(ActionEvent event) {

		File fichero = fileChooser.showSaveDialog(GiveMeDADMoney.getPrimaryStage());
		if (fichero != null) {
			try {
				
			} catch (Exception e1) {
				
			}
		}

	}

	@FXML
	void onImportScoreAction(ActionEvent event) {

	}

	public void showOnStage(Window owner) {
		stage = new Stage();
		stage.setTitle("Editar propiedad");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(owner);
		stage.setScene(new Scene(settingsView, 400, 200));
		load();
		stage.showAndWait();
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
