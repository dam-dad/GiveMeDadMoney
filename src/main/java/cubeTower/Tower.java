package cubeTower;

import javafx.scene.layout.Pane;

public class Tower {
	
	private int cols;
	private int rows;
	private Pane[][] rectangles;
	
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
	
	public int getCols() {
		return cols;
	}
	
	public int getRows() {
		return rows;
	}
	
	public Pane getRectangle(int x, int y) {
		return rectangles[x][y];
	}

	public Pane[][] getRectangles() {
		return rectangles;
	}
	
	public void setTransparent(int x, int y) {
		draw(x, y, "transparent");
	}

	public void setColor(int x, int y) {
		draw(x, y, "red");
	}
	
	public void draw(int x, int y, String color) {
		rectangles[x][y].setStyle("-fx-background-color:" + color);
	}
	
	public boolean isCube(int x, int y) {
		return !getRectangle(x, y).getStyle().contains("transparent");
	}

	public void clear() {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				setTransparent(i, j);
			}
		}
	}

}
