import java.awt.*;

/**
 * List of Graphical Objects that JabaGator knows about 
 */
public abstract class GObj extends Object {
	/** The current position in units */
	int x;
	/** The current position in units */
	int y;
	/** The current size in units */
	int w;
	/** The current size in units */
	int h;

	GObj() {
		x = y = w = h = 0;
	}

	/** some methods... */
	abstract void draw(Graphics g);

	void setXY(int nx, int ny) {
		x = nx;
		y = ny;
		System.out.println("GObj " + this + " moveto " + x + "," + y);
	}
	Dimension getXY() {
		return new Dimension(x,y);
	}
	void setWH(int nw, int nh) {
		w = nw;
		h = nh;
	}

}

