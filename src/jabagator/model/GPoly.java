import java.awt.*;

class GPoly extends GObj {
	Polygon points;

	GPoly() {
		points = null;
		w = h = 0;
	}
	GPoly(Polygon p) {
		setData(p);
	}
	void setData(Polygon p) {
		points = p;
		Rectangle d = p.getBounds();
		w = d.width;
		h = d.height;
	}
	public void draw(Graphics g) {
		doColor(g);
		if (points == null)
			return;
		g.drawPolygon(points);
	}
	public void setXY(int nx, int ny) {
		points.translate(nx-x, ny-y);
		super.setXY(nx, ny);
	}
}
