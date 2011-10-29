package jabagator.model;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GLine extends GObj {

	private static final long serialVersionUID = 1L;
	protected int lWidth;
	protected int lHeight;

	public String describe() { return "line"; }

	public GLine(int w, int h) {
		lWidth = w;
		lHeight = h;
		setSize(lWidth, lHeight);
		ctlPoints[nCtlPoints++] = new Point(CP_SIZE/2, CP_SIZE/2);
		ctlPoints[nCtlPoints++] = new Point(lWidth-CP_SIZE/2, lHeight-CP_SIZE/2);
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawLine(0, 0, 0+lWidth, 0+lHeight);
	}
}
