package cubeTower;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * The type Tower.
 */
public class Tower {
	
	private int cols;
	private int rows;
	private Pane[][] rectangles;

	/**
	 * Instantiates a new Tower.
	 *
	 * @param cols the cols
	 * @param rows the rows
	 */
	public Tower(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		this.rectangles = new Pane[cols][rows];
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				Pane rectangulo = new Pane();
				rectangulo.setMaxWidth(Double.MAX_VALUE);
				rectangulo.setMaxHeight(Double.MAX_VALUE);
				rectangles[i][j] = rectangulo;
				setTransparent(i, j);
			}
		}
	}

	/**
	 * Gets cols.
	 *
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Gets rows.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Gets rectangle.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the rectangle
	 */
	public Pane getRectangle(int x, int y) {
		return rectangles[x][y];
	}

	/**
	 * Get rectangles pane [ ] [ ].
	 *
	 * @return the pane [ ] [ ]
	 */
	public Pane[][] getRectangles() {
		return rectangles;
	}

	/**
	 * Sets transparent.
	 *
	 * @param x the x
	 * @param y the y
	 * Pinta en transparente
	 */
	public void setTransparent(int x, int y) {
		draw(x, y, "transparent");
	}

	/**
	 * Sets color.
	 *
	 * @param x the x
	 * @param y the y
	 * Pinta en el color predefinido
	 */
	public void setColor(int x, int y) {
		draw(x, y, "#FF7B00");
	}

	/**
	 * Draw.
	 *
	 * @param x     the x
	 * @param y     the y
	 * @param color the color
	 * Funcion que pinta el cubo exacto
	 */
	public void draw(int x, int y, String color) {
		rectangles[x][y].setStyle("-fx-background-color:" + color);
	}

	/**
	 * Draw.
	 *
	 * @param node  the node
	 * @param color the color
	 * Lo mismo que el anterior pero se le pasa el nodo directamente
	 */
	public void draw(Node node, String color) {
		node.setStyle("-fx-background-color:" + color);
	}


	/**
	 * Is cube boolean.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the boolean
	 * Revisa si es un cubo transparente
	 */
	public boolean isCube(int x, int y) {
		return !getRectangle(x, y).getStyle().contains("transparent");
	}

	/**
	 * Is cube fall boolean.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the boolean
	 * Revisa si es un cubo pintado
	 */
	public boolean isCubeFall(int x, int y) {
		return  !getRectangle(x, y).getStyle().contains("rgba(255, 255, 255, .7)");
	}

	/**
	 * Clear.
	 * Borra toda la torre dejandola vacia
	 */
	public void clear() {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				setTransparent(i, j);
			}
		}
	}

}
