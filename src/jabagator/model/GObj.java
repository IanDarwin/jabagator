import java.awt.*;

/**
 * Top level of Graphical Objects that JabaGator knows about.
 *
 * Vaguely analogous to Component in java.awt.
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

	static Component view;

	static void setView(Component c) {
		view = c;
	}

	GObj() {
		if (view == null)
			throw new IllegalArgumentException(
				"Must setView() before constructing any GObj");

		x = y = w = h = 0;
	}

	/** some methods... */
	abstract void draw(Graphics g);

	/** If this object is selected */
	protected boolean selected;

	public void setSelected(boolean b) {
		selected = b;
	}
	public boolean isSelected() {
		return selected;
	}
	void doColor(Graphics g) {
		if (selected)
			g.setColor(Color.blue);
		else
			g.setColor(Color.black);
	}

	/** Get the Parameter String (name+main values) of this GObj */
	public String getParamString() {
		return this+"[@"+x+","+y+";w="+w+",h="+h;
	}

	/** Return true if the object contains the point */
	boolean contains(int cx, int cy) {
		System.out.println(getParamString()+".contains("+cx+","+cy+")");
		return (cx>=x && cx<=(x+w) &&
				cy>=y && cy<=(y+h));
	}

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

