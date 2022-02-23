package cubeTower;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * The type Cube.
 */
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

	/**
	 * Instantiates a new Cube.
	 *
	 * @param x    the x
	 * @param y    the y
	 * @param size the size
	 */
	public Cube(int x, int y, int size) {
		super();
		this.size = size;
		this.x = x;
		this.y = y;
		this.direction = 1;
		this.speed = 0.35;
	}

	/**
	 * Gets size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets color.
	 *
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets color.
	 *
	 * @param color the color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets speed.
	 *
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets speed.
	 *
	 * @param speed the speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Render.
	 *
	 * @param tower the tower
	 */
	public void render(Tower tower) {
		for (int i = 0; i < size; i++) {
			tower.draw(x + i, y, color);
		}
	}

	private boolean checkCollision(Tower tower) {
		return (x == 0 && direction == -1) || (x + size == tower.getCols() && direction == 1);
	}

	/**
	 * Update.
	 *
	 * @param diff  the diff
	 * @param tower the tower
	 */
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

	/**
	 * Move up.
	 */
	public void moveUp() {
		y -= 1;
		speed *= 0.75;
	}

	/**
	 * Reduce.
	 *
	 * @param tower the tower
	 */
	public void reduce(Tower tower) {
		int duracion = 0;
		int total = x + size - 1;

		for (int i = x; i <= total; i++) {
			
			if (!tower.isCube(i, y + 1) || !tower.isCubeFall(i, y + 1)) {
				if (i == x) {
					x += 1;
				}
				size -= 1;
				final Node node=tower.getRectangle(i, y);
				cuboCaida = new TranslateTransition();
				cuboCaida.setNode(node);
				cuboCaida.setFromY(0);
				cuboCaida.setToY(500);
				cuboCaida.setDuration(Duration.seconds(1));
				cuboCaida.setOnFinished(e -> {
					node.setTranslateY(0);
					tower.draw(node, "transparent");
				});
				cuboCaida.play();
				// cuboCaida.stop();
				tower.draw(i, y, "rgba(255, 255, 255, .7)");
			}
		}
	}
}
