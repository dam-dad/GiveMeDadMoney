package tragaPerras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Figura extends ScrollPane implements Initializable {
	
	private TranslateTransition topTransition;
	private TranslateTransition bottomTransition;
	private ParallelTransition transition; 
	
	private ObjectProperty<Imagen> current = new SimpleObjectProperty<>();
	
	private int counter;
	
	@FXML
	private VBox pane;
	
	@FXML
	private ImageView topImage;
	
	@FXML
	private ImageView bottomImage;
	
	public Figura() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TragaPerras/Figura.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		topTransition = new TranslateTransition();
		topTransition.setNode(topImage);
		topTransition.setFromY(0);
		topTransition.setToY(bottomImage.getFitHeight());
		topTransition.setDuration(Duration.seconds(1.0));
		topTransition.setInterpolator(Interpolator.LINEAR);
		
		bottomTransition = new TranslateTransition();
		bottomTransition.setNode(bottomImage);
		bottomTransition.setFromY(0);
		bottomTransition.setToY(bottomImage.getFitHeight());
		bottomTransition.setDuration(Duration.seconds(1.0));
		bottomTransition.setInterpolator(Interpolator.LINEAR);
	
		transition = new ParallelTransition(topTransition, bottomTransition);
		transition.setOnFinished(e -> {
			counter--;
			if (counter > 0) {
				// cambio las imágenes
				
				transition.play();
			}
		});
		
	}
	
	public void roll() {
		
		counter = 5;
		transition.play();
		
	}

}
