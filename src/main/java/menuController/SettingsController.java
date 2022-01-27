package menuController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.GiveMeDADMoney;
import score.Score;

public class SettingsController implements Initializable {

	@FXML
    private Button backButton;

    @FXML
    private Button changeUserNameButton;

    @FXML
    private Button downloadScoreButton;

    @FXML
    private Button importScoreButton;

    @FXML
    private Button playMusicButton;

    @FXML
    private BorderPane settingsView;

    @FXML
    private Button stopMusicButton;

    @FXML
    private TextField userNameText;

    @FXML
    private Slider volumenSlider;

	// MODELO
	private ObjectProperty<Score> score_file = new SimpleObjectProperty<>();
	private StringProperty userName = new SimpleStringProperty();

	private FileChooser fileChooser;

	public SettingsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/SettingsView.fxml"));
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

	public void load() {
		userNameText.textProperty().set(score_file.getValue().getUserName());

	}

	@FXML
	void onMusicStopAcction(ActionEvent event) {
		try {
			BaseController.getInstance().musica.pause();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void onMusicPlayAcction(ActionEvent event) {
		try {
			BaseController.getInstance().musica.play();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	void onVolumenChange(MouseEvent event) {
		System.out.println(volumenSlider.getValue());
	}

	@FXML
	void onBackAcction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onChangeUserNameAction(ActionEvent event) {

		score_file.getValue().setUserName(userName.getValue());

		System.out.println(score_file.getValue().getUserName());

	}

	@FXML
	void onDownloadScoreAction(ActionEvent event) {
		File destino = fileChooser.showSaveDialog(GiveMeDADMoney.getPrimaryStage());
		File origen = new File("score.xml");
		if (destino != null && origen != null) {
			try {
				destino.createNewFile();
				copiarXML(origen, destino);
			} catch (Exception e1) {
				System.err.println(e1.getMessage());
			}
		}
	}

	@FXML
	void onImportScoreAction(ActionEvent event) {
		if (GiveMeDADMoney.confirm("Importar", "Estas a punto de importar datos",
				"Puede perder datos no guardados, ¿estas seguro?") == true) {
			File fichero = fileChooser.showOpenDialog(GiveMeDADMoney.getPrimaryStage());
			if (fichero != null) {
				try {
					File file = new File("score.xml");
					copiarXML(fichero, file);
					load_score(fichero);
				} catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
			}
		}
	}

	private void load_score(File file) {
		Score score = new Score();
		try {
			score_file.set(score.read(file));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void copiarXML(File origen, File destino) {

		try {
			InputStream in = new FileInputStream(origen);
			OutputStream out = new FileOutputStream(destino);

			byte[] buf = new byte[1024];
			int len;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

			in.close();
			out.close();

		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}

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

	public BorderPane getView() {
		return settingsView;
	}

}
