package jabagator.model;

import java.awt.Graphics;

public class GOval extends GObj {

	private static final long serialVersionUID = 4582577759976387965L;

	public GOval() {
		ctlPoints[nCtlPoints++] = new Point(0, 0);
	}

	public String describe() { 
		Dimension d = getSize();
		return d.width==d.height?"circle":"oval";
	}

	public void draw(Graphics g) {
		Dimension d = getSize();
		// The one control point is always in the centre
		ctlPoints[0].setLocation(d.width/2, d.height/2);
		g.drawOval(0, 0, d.width-1, d.height-1);
	}
}
