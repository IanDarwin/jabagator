package jabagator.model;

import java.awt.*;

public class GOval extends GObj {
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
		ctlPoints[0].setLocation(new Point(d.width/2, d.height/2));
		g.drawOval(0, 0, d.width-1, d.height-1);
	}
}
