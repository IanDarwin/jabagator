package jabagator.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Top level of Graphical Objects that JabaGator knows about.
 *
 * Vaguely analogous to Component in java.awt.
 */
public abstract class GObject implements Cloneable, Serializable {
	/** The UIDL, for serialization. */
	public final static long serialVersionUID=-1842877245453958698L;
	/** The size of a control point. */
	final static int CP_SIZE = 4;
	/** The control points */
	Point[] ctlPoints;
	/** How many of same. May be <= ctlPoints.length. */
	int nCtlPoints;
	int x, y;		// location
	int width, height;

	public GObject() {
		ctlPoints = new Point[4];	// adequate for most.
		nCtlPoints = 0;
	}

	/** What kind of thing this is. */
	public abstract String describe();

	/** If this object is selected */
	protected transient boolean selected;
	
	/** draw -- common code for paint & print -- draw the object. */
	public abstract void draw(Graphics g);

	public void setSelected(boolean b) {
		if (selected == b)
			return;		// save unnecessary repaints
		selected = b;
		repaint();
	}

	public boolean isSelected() {
		return selected;
	}

	/** paint -- do selection color and control points, then draw(). */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if (selected)
			g2.setColor(Color.BLUE);
		else
			g2.setColor(Color.BLACK);
		for (int i=0; i<nCtlPoints; i++)
			g2.fill(getControlPoint(new java.awt.Point(ctlPoints[i].getX(), ctlPoints[i].getY())));
		draw(g);
	}

	/** print -- just calls draw() directly. */
	public void print(Graphics g) {
		g.setColor(Color.BLACK);	// xxx interim
		draw(g);
	}

	/** Edit the Attributes. */
	public void editAttributes() {
		JOptionPane.showMessageDialog(null,
			"GObj Editor: not written yet", "GObj",
			JOptionPane.WARNING_MESSAGE);
	}

	/** Get the Parameter String (name+main values) of this GObj */
	public String toString() {
		return String.format("%s[%d,%d]", getClass(), x, y);
	}

	/** Clone. Object.clone is protected, so you must write this
	 * method even if it has nothing to do other than call super.clone().
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	public Dimension getSize() {
		return new Dimension(width, height);
	}
	
	public Point getLocation() {
		return new Point(x, y);
	}
	
	public void setLocation(int x, int y) {
		this.x = x; this.y = y;
	}

	/** Return a small box around a control point.
	 * @author Jonathan Knudsen, in O'Reilly's <I>Java 2D</I> book.
	 */
	protected Shape getControlPoint(Point2D p) {
		return new Rectangle2D.Double(
			p.getX() - CP_SIZE / 2, p.getY() - CP_SIZE / 2, CP_SIZE, CP_SIZE);
	}
	
	protected void repaint() {
		
	}
}
