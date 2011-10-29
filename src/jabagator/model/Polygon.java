package jabagator.model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
	
	List<Point> points = new ArrayList<Point>();

	public void addPoint(int x, int y) {
		points.add(new Point(x,y));
	}

	public Rectangle getBounds() {
		int minx = 0, miny = 0, maxx = 0, maxy = 0;
		for (Point p : points) {
			int x = p.x;
			int y = p.y;
			if (x < minx) minx = x; else
			if (y < miny) miny = y;
			if (x > maxx) maxx = x; else
			if (y > maxy) maxy = y;
		}
		return new Rectangle(minx, miny, maxx -  minx, maxy - miny);
	}

}
