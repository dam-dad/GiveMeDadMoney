package tragaPerras;

import java.util.Arrays;
import java.util.List;

import javafx.scene.image.Image;

public class Imagen  {
	
	public static final Imagen LEMON = new Imagen("/images/TragaPerras/leamon.png", 10);
	public static final Imagen CHERRY = new Imagen("/images/TragaPerras/cherry.png", 25);
	public static final Imagen WATERMELON = new Imagen("/images/TragaPerras/sandia.png", 35);
	public static final Imagen GRAPE = new Imagen("/images/TragaPerras/uva.png", 50);
	public static final Imagen DIAMOND = new Imagen("/images/TragaPerras/diamond.png", 65);
	public static final Imagen BAR = new Imagen("/images/TragaPerras/bar.png", 75);
	public static final Imagen SEVEN = new Imagen("/images/TragaPerras/seven.png", 120);
	
	private static final List<Imagen> IMAGENES = Arrays.asList(LEMON, CHERRY, WATERMELON, GRAPE, DIAMOND, BAR, SEVEN);

    private Image imagen;
    private int valor;

    public Imagen(String imagen, int valor) {
        this.imagen = new Image(imagen);
        this.valor = valor;
    }

    public static Imagen randomImagen() {
        return IMAGENES.get((int) (Math.random() * 7));
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public Image getImagen() {
		return imagen;
	}
    
    public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

}
