package mayorOMenor;

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
import javafx.scene.layout.BorderPane;

public class MayorOMenorController implements Initializable {

	@FXML
	private Button biggerButton;

	@FXML
	private Label homeNumLabel;

	@FXML
	private Button lessButton;

	@FXML
	private Label myNumLabel;
	
	
	@FXML
	private BorderPane root;

	private IntegerProperty myNum = new SimpleIntegerProperty();
	private IntegerProperty homeNum = new SimpleIntegerProperty();


	public MayorOMenorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MayorOMenor/MayorOMenor.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {

		homeNumLabel.textProperty().bind(homeNum.asString());
		myNumLabel.textProperty().bind(myNum.asString());

		load();
	}

	private void load() {
		int x = (int) (Math.random() * 10)+1;
		int y = (int) (Math.random() * 10)+1;

		myNum.set(x);
		homeNum.set(y);
	}
	
	

	@FXML
	void onBiggerAction(ActionEvent event) {
		if (myNum.get() >= homeNum.get()) {
			System.out.println("Bien");
		}else {
			System.out.println("mal");
		}
		load();
	}

	@FXML
	void onLessButton(ActionEvent event) {
		if (myNum.get() <= homeNum.get()) {
			System.out.println("Bien");
		}else {
			System.out.println("mal");
		}
		load();
	}

	public BorderPane getView() {
		return root;
	}
}
