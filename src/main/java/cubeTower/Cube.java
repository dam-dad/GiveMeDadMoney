package cubeTower;

public class Cube {
	
	private static final double SPEED = 0.15;

	private double time = 0.0;

	private int size;
	private int x;
	private int y;
	private String color;
	private int direction;

	public Cube(int x, int y, int size) {
		super();
		this.size = size;
		this.x = x;
		this.y = y;
		this.direction = 1;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void render(Tower tower) {
		for (int i = 0; i < size; i++) {
			tower.draw(x + i, y, color);
		}		
	}
	
	private boolean checkCollision(Tower tower) {
		return 	(x == 0 && direction == -1) ||
				(x + size == tower.getCols() && direction == 1);
	}
	
	public void update(double diff, Tower tower) {
		time += diff;
		if (checkCollision(tower)) {
			direction *= -1;
		}
		if (time > SPEED) {
			x += direction;
			time = 0.0;
		}
	}

	public void moveUp() {
		y -= 1;
	}
	
	public void reduce(Tower tower) {
		
		for (int i = x; i < x + size; i++) { 
						
			if (!tower.isCube(i, y + 1)) {
				if (i == x) {
					x += 1;				
				}				
				size -= 1;
			}
			
		}
		
	}

}
