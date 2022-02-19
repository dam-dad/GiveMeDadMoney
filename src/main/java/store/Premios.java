package store;

import javafx.scene.image.Image;

public class Premios {
	
	

	public Image getGifXokas() {
		return new Image("/images/store/gifXokas/xokas" + random(3) + ".gif");
	}
	
	public Image getGifOkay() {
		return new Image("/images/store/gifOkay/okay" + random(3) + ".gif");
	}
	
	public Image getGifDinero(){
		return new Image("/images/store/gifDinero/dinero" + random(3) + ".gif");
	}
	
	private  int random(int max) {
		return (int) (Math.random()*max);
	}
}
