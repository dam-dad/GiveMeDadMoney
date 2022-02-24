package tragaPerras;

import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;

/**
 * The type Imagen.
 */
public class Imagen  {

    public static final Imagen LEMON = new Imagen("/images/TragaPerras/leamon.png", 10);
    public static final Imagen CHERRY = new Imagen("/images/TragaPerras/cherry.png", 25);
    public static final Imagen WATERMELON = new Imagen("/images/TragaPerras/sandia.png", 35);
    public static final Imagen GRAPE = new Imagen("/images/TragaPerras/uva.png", 50);
    public static final Imagen DIAMOND = new Imagen("/images/TragaPerras/diamond.png", 65);
    public static final Imagen BAR = new Imagen("/images/TragaPerras/bar.png", 75);
    public static final Imagen SEVEN = new Imagen("/images/TragaPerras/seven.png", 120);

    public static final List<Imagen> IMAGENES = Arrays.asList(LEMON, CHERRY, WATERMELON, GRAPE, DIAMOND, BAR, SEVEN);

    private Image imagen;
    private int valor;

    /**
     * Instantiates a new Imagen. Contructor de la clase Imagen
     *
     * @param imagen the imagen
     * @param valor  the valor
     */
    public Imagen(String imagen, int valor) {
        this.imagen = new Image(imagen);
        this.valor = valor;
    }

    /**
     * Random imagen imagen. Devuelve una imagen aleatoria de la lista IMAGENES
     *
     * @return the imagen
     */
    public static Imagen randomImagen() {
        return IMAGENES.get((int) (Math.random() * IMAGENES.size()));
    }

    /**
     * Gets valor. Obtienes el valor de cada imagen
     *
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * Gets imagen. Obtienes la imagen
     *
     * @return the imagen
     */
    public Image getImagen() {
		return imagen;
	}

}
