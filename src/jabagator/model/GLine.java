import java.awt.*;
import java.awt.geom.*;

public class GLine extends GObj {
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
		g.drawLine(0, 0, 0+lWidth, 0+lHeight);
	}
}
