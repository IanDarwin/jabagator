package jabagator.model;

import java.io.Serializable;

public class Point implements Serializable {
	private static final long serialVersionUID = 5703500346887747007L;
	
	public int x;
	public int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point(Point original) {
		this.x = original.x;
		this.y = original.y;
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
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
