
class GRect extends GObj {
	boolean rounded = false;
	int radius = 0;
	void draw(Graphics g) {
		g.drawRect(x, y, w, h);
	}
}
