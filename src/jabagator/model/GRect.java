import java.awt.*;


class GRect extends GObj {
	boolean rounded = false;
	int radius = 0;
	void draw(Graphics g) {
		doColor(g);
		g.drawRect(x, y, w, h);
	}
}
