package store;

import javafx.scene.image.Image;

/**
 * The type Premios.
 */
public class Premios {

	/**
	 * Gets gif xokas.
	 *
	 * @return the gif xokas
	 */
	public Image getGifXokas() {
		return new Image("/images/store/gifXokas/xokas" + random(3) + ".gif");
	}

	/**
	 * Gets gif okay.
	 *
	 * @return the gif okay
	 */
	public Image getGifOkay() {
		return new Image("/images/store/gifOkay/okay" + random(3) + ".gif");
	}

	/**
	 * Get gif dinero image.
	 *
	 * @return the image
	 */
	public Image getGifDinero(){
		return new Image("/images/store/gifDinero/dinero" + random(3) + ".gif");
	}
	
	private  int random(int max) {
		return (int) (Math.random()*max);
	}
}
