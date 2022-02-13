package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import menuController.BaseController;

public class Niveles implements Initializable {
	
	private CubeTowerController cube;
	
    @FXML
    private BorderPane root;
	
	public Niveles() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/dificultad.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
    void onFacilAction(ActionEvent event) {
		cube.setSpeed(1.5*1e9);
		cube.setSize(4);
		//TODO PONER ACCION DE DAR PARA ATRAS AL JUEGO
    }

    @FXML
    void onMedioAction(ActionEvent event) {
    	cube.setSpeed(0.5*1e9);
    	cube.setSize(3);
    	//TODO PONER ACCION DE DAR PARA ATRAS AL JUEGO
    }

    @FXML
    void onDificilAction(ActionEvent event) {
    	cube.setSpeed(0.3*1e9);
    	cube.setSize(2);
    	//TODO PONER ACCION DE DAR PARA ATRAS AL JUEGO
    }
    
    public BorderPane getView() {
    	return root;
    }

	
}
