import java.awt.*;

class GOval extends GObj {
	void draw(Graphics g) {
		doColor(g);
		g.drawOval(x, y, w, h);
	}
}
