package cubeTower;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Cube {

	private double time = 0.0;
 
	private int size;
	private int x;
	private int y;
	private String color;
	private int direction;
	private double speed;
	private TranslateTransition cuboCaida;

	@FXML
	private ImageView total;

	public Cube(int x, int y, int size) {
		super();
		this.size = size;
		this.x = x;
		this.y = y;
		this.direction = 1;
		this.speed = 0.35;
	}

	public int getSize() {
		return size;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void render(Tower tower) {
		for (int i = 0; i < size; i++) {
			tower.draw(x + i, y, color);
		}
	}

	private boolean checkCollision(Tower tower) {
		return (x == 0 && direction == -1) || (x + size == tower.getCols() && direction == 1);
	}

	public void update(double diff, Tower tower) {
		time += diff;
		if (checkCollision(tower)) {
			direction *= -1;
		}
		if (time > speed) {
			x += direction;
			time = 0.0;
		}
	}

	public void moveUp() {
		y -= 1;
		speed *= 0.75;
	}

	public void reduce(Tower tower) {
		int duracion = 0;
		int total = x + size - 1;
		
		for (int i = x; i <= total; i++) {
			cuboCaida = new TranslateTransition();
			if (!tower.isCube(i, y + 1) || !tower.isCubeFall(i, y+1)) {
				if (i == x) {
					x += 1;
				}
				size -= 1;

				cuboCaida.setNode(tower.getRectangle(i, y));
				cuboCaida.setFromY(0);
				cuboCaida.setToY(500);
				cuboCaida.setDuration(Duration.seconds(1));
				cuboCaida.play();
				//cuboCaida.stop();
				tower.draw(i, y, "rgba(255, 255, 255, .7)");
			}
		}
	}
}
