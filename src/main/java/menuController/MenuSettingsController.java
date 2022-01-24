package menuController;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import score.Score;


public class MenuSettingsController implements Initializable {
	
	@FXML
    private Button changeUserNameButton;

    @FXML
    private BorderPane settingsView;

    @FXML
    private TextField userNameText;

    
    
    
	private ObjectProperty<Score> score_file = new SimpleObjectProperty<>();
	
	private StringProperty userName = new SimpleStringProperty();

    
    
	private Stage stage;


	public MenuSettingsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/MenuSettingsView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userName.bind(userNameText.textProperty());

	}
	
	private void load() {
		userNameText.textProperty().set(score_file.getValue().getUserName());

	}
	
	
	
	@FXML
    void onChangeUserNameAction(ActionEvent event) {

		score_file.getValue().setUserName(userName.getValue());
		
		System.out.println(score_file.getValue().getUserName());
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
