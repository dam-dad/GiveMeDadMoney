package tragaPerras;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * The type Figura.
 */
public class Figura extends ScrollPane implements Initializable {
	
	private TranslateTransition topTransition;
	private TranslateTransition bottomTransition;
	private ParallelTransition transition; 
	
	private IntegerProperty top = new SimpleIntegerProperty();
	private IntegerProperty bottom = new SimpleIntegerProperty();
	
	private int counter;
	
	@FXML
	private VBox pane;
	
	@FXML
	private ImageView topImage;
	
	@FXML
	private ImageView bottomImage;

	/**
	 * Instantiates a new Figura. Se carga el fxml de Figura
	 */
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
		topTransition.setFromY(10);
		topTransition.setToY(bottomImage.getFitHeight() + 22);
		topTransition.setDuration(Duration.seconds(0.10));
		topTransition.setInterpolator(Interpolator.LINEAR);
		
		bottomTransition = new TranslateTransition();
		bottomTransition.setNode(bottomImage);
		bottomTransition.setFromY(10);
		bottomTransition.setToY(bottomImage.getFitHeight() + 22);
		bottomTransition.setDuration(Duration.seconds(0.10));
		bottomTransition.setInterpolator(Interpolator.LINEAR);
	
		transition = new ParallelTransition(topTransition, bottomTransition);
		transition.setOnFinished(e -> {
			counter--;
			if (counter >= 0) {
				
				if (counter == 0) {
					bottomTransition.setInterpolator(Interpolator.EASE_OUT);
					topTransition.setInterpolator(Interpolator.EASE_OUT);
				}
				
				bottom.set((bottom.get() + 1) % Imagen.IMAGENES.size());				
				transition.play();
			}
		});
		
		bottom.addListener((o, ov, nv) -> {
			top.set((nv.intValue() + 1) % Imagen.IMAGENES.size());
			bottomImage.setImage(topImage.getImage());
			topImage.setImage(Imagen.IMAGENES.get(bottom.get()).getImagen());
		});
		bottom.set((int) (Math.random() * Imagen.IMAGENES.size()));
		
	}

	/**
	 * Roll. Hace el efecto de "giro"
	 */
	public void roll() {
		
		counter = 10;
		bottom.set((int) (Math.random() * Imagen.IMAGENES.size()));
		bottom.set(((bottom.get() - 1) < 0 ? Imagen.IMAGENES.size() : bottom.get()) - 1);
		transition.play();
		
	}

	/**
	 * Get value imagen int. Devuelve el valor de la imagen
	 *
	 * @return the int
	 */
	public int getValueImagen(){

		return Imagen.IMAGENES.get(bottom.get()).getValor();
	}

}
