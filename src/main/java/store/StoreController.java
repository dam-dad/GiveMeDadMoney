package store;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class StoreController implements Initializable {
	
	
	public StoreController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Store/Store.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		
	}

}
