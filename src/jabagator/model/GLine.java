import java.awt.*;

class GLine extends GObj {
	void draw(Graphics g) {
		doColor(g);
		g.drawLine(x, y, x+w, x+h);
	}
}
