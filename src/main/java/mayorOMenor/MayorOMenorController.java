package mayorOMenor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import menuController.BaseController;
import score.Score;

public class MayorOMenorController implements Initializable {


	@FXML
	private TextField apuestaText;

	@FXML
	private Button backButton;

	@FXML
	private Button biggerButton;

	@FXML
	private Label homeNumLabel;

	@FXML
	private Button lessButton;

	@FXML
	private Label myNumLabel;

	@FXML
	private Label puntosLabel;
	
	@FXML
    private Button equalButton;

	@FXML
	private BorderPane root;

	private IntegerProperty myNum = new SimpleIntegerProperty();
	private IntegerProperty homeNum = new SimpleIntegerProperty();

	private IntegerProperty score = new SimpleIntegerProperty();
	private StringProperty apuesta = new SimpleStringProperty();
	
	
	

	
 

	public MayorOMenorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MayorOMenor/MayorOMenor.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		// Bindings
		puntosLabel.textProperty().bind(score.asString());
		homeNumLabel.textProperty().bind(homeNum.asString());
		myNumLabel.textProperty().bind(myNum.asString());
		apuestaText.textProperty().bindBidirectional(apuesta);
		
		
		homeNumLabel.setVisible(false);
		
		//TODO NO LETRAS
		//Bloquear botones si no hay apuestas
		biggerButton.disableProperty().bind(apuesta.isEmpty());
		equalButton.disableProperty().bind(apuesta.isEmpty());
		lessButton.disableProperty().bind(apuesta.isEmpty());

		
		load_score();
		load();
		
		root.getStylesheets().add("css/MayorOMenor/MayorOMenor.css");
	}
	
	public void load_score() {
    	score.set(Score.getInstance().getTotalScore());
    }

	private void load() {
		int x = (int) (Math.random() * 10) + 1;
		int y = (int) (Math.random() * 10) + 1;

		myNum.set(x);
		homeNum.set(y);
		
		Score.getInstance().setTotalScore(score.intValue());
	}


	@FXML
	void onBiggerAction(ActionEvent event) {
		if (myNum.get() >= homeNum.get()) {
			you_win();
		} else {
			you_loose();		
		}
		load();
	}

	@FXML
	void onLessButton(ActionEvent event) {
		if (myNum.get() <= homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		load();
	}
	
	@FXML
    void onEqualButton(ActionEvent event) {
		if (myNum.get() == homeNum.get()) {
			you_win();
		} else {
			you_loose();
		}
		load();
    }
	
	private void you_loose() {
		score.set(score.get() - Integer.parseInt(apuesta.get()));
	}
	private void you_win() {
		score.set(score.get() + Integer.parseInt(apuesta.get()));
	}

	@FXML
	void onBackAction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	public BorderPane getView() {
		return root;
	}
}
