package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class Niveles implements Initializable {
	
	private CubeTowerController cube;
	
	public Niveles() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/niveles.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
    void onPrimerNivelAction(ActionEvent event) {
		cube.setSpeed(1.5*1e9);
		cube.setSize(4);
    }

    @FXML
    void onSegundoNivelAction(ActionEvent event) {
    	cube.setSpeed(0.5*1e9);
    	cube.setSize(3);
    }

    @FXML
    void onTercerNivelAction(ActionEvent event) {
    	cube.setSpeed(0.3*1e9);
    	cube.setSize(2);
    }

	
}
