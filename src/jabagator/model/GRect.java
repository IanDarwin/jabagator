import java.awt.*;


class GRect extends GObj {
	boolean rounded = false;
	int radius = 0;

	public GRect() {
		this(0,0);
	}

	public GRect(int w, int h) {
		super();
		setSize(w, h);
		ctlPoints[nCtlPoints++] = new Point(CP_SIZE/2, CP_SIZE/2);
		ctlPoints[nCtlPoints++] = new Point(w-CP_SIZE/2, CP_SIZE/2);
		ctlPoints[nCtlPoints++] = new Point(w-CP_SIZE/2, h-CP_SIZE/2);
		ctlPoints[nCtlPoints++] = new Point(CP_SIZE/2, h-CP_SIZE/2);
	}

	public String describe() { return "rectangle"; }

	public void draw(Graphics g) {
		Dimension d = getSize();
		g.drawRect(0, 0, d.width-1, d.height-1);
	}
}
