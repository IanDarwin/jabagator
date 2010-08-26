package jabagator.model;

import java.awt.*;

class GPoly extends GObj {
	Polygon points;

	GPoly() {
		this(null);
	}

	GPoly(Polygon p) {
		if (p == null)
			p = new Polygon();
		setData(p);
	}

	public String describe() { return "polygon"; }

	public void addPoint(int x, int y) {
		points.addPoint(x, y);
		if (nCtlPoints < 4)
		ctlPoints[nCtlPoints++] = new Point(x, y);
		computeSize();
	}

	void setData(Polygon p) {
		points = p;
		if (points == null)
			return;
		computeSize();
	}
	protected void computeSize() {
		Rectangle r = points.getBounds();
		setSize(r.width, r.height);
	}

	public void draw(Graphics g) {
		if (points == null)
			return;
		g.drawPolygon(points);
	}
}
