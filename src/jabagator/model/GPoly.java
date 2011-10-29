package jabagator.model;

import java.awt.Graphics;

public class GPoly extends GObj {

	private static final long serialVersionUID = 4374771334128119402L;
	Polygon points;

	public GPoly() {
		this(null);
	}

	public GPoly(Polygon p) {
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
		// g.drawPolygon(points);
	}
}
