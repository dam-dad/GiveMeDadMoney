package tragaPerras;

import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;

/**
 * The type Imagen.
 */
public class Imagen  {

    /**
     * The constant LEMON.
     */
    public static final Imagen LEMON = new Imagen("/images/TragaPerras/leamon.png", 10);
    /**
     * The constant CHERRY.
     */
    public static final Imagen CHERRY = new Imagen("/images/TragaPerras/cherry.png", 25);
    /**
     * The constant WATERMELON.
     */
    public static final Imagen WATERMELON = new Imagen("/images/TragaPerras/sandia.png", 35);
    /**
     * The constant GRAPE.
     */
    public static final Imagen GRAPE = new Imagen("/images/TragaPerras/uva.png", 50);
    /**
     * The constant DIAMOND.
     */
    public static final Imagen DIAMOND = new Imagen("/images/TragaPerras/diamond.png", 65);
    /**
     * The constant BAR.
     */
    public static final Imagen BAR = new Imagen("/images/TragaPerras/bar.png", 75);
    /**
     * The constant SEVEN.
     */
    public static final Imagen SEVEN = new Imagen("/images/TragaPerras/seven.png", 120);

    /**
     * The constant IMAGENES.
     */
    public static final List<Imagen> IMAGENES = Arrays.asList(LEMON, CHERRY, WATERMELON, GRAPE, DIAMOND, BAR, SEVEN);

    private Image imagen;
    private int valor;

    /**
     * Instantiates a new Imagen.
     *
     * @param imagen the imagen
     * @param valor  the valor
     */
    public Imagen(String imagen, int valor) {
        this.imagen = new Image(imagen);
        this.valor = valor;
    }

    /**
     * Random imagen imagen.
     *
     * @return the imagen
     */
    public static Imagen randomImagen() {
        return IMAGENES.get((int) (Math.random() * IMAGENES.size()));
    }

    /**
     * Gets valor.
     *
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * Sets valor.
     *
     * @param valor the valor
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Gets imagen.
     *
     * @return the imagen
     */
    public Image getImagen() {
		return imagen;
	}

    /**
     * Sets imagen.
     *
     * @param imagen the imagen
     */
    public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

}
