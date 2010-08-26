package jabagator.model;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Top level of Graphical Objects that JabaGator knows about.
 *
 * Vaguely analogous to Component in java.awt.
 */
public abstract class GObj extends JComponent 
	implements Cloneable, java.io.Serializable {
	/** The UIDL, for serialization. */
	public static long serialVersionUID=-1842877245453958698L;
	/** The size of a control point. */
	final static int CP_SIZE = 4;
	/** The control points */
	Point2D[] ctlPoints;
	/** How many of same. May be <= ctlPoints.length. */
	int nCtlPoints;

	GObj() {
		ctlPoints = new Point2D[4];	// adequate for most.
		nCtlPoints = 0;
	}

	/** What kind of thing this is. */
	public abstract String describe();

	/** If this object is selected */
	protected transient boolean selected;

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
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if (selected)
			g2.setColor(Color.blue);
		else
			g2.setColor(Color.black);
		for (int i=0; i<nCtlPoints; i++)
			g2.fill(getControlPoint(ctlPoints[i]));
		draw(g);
	}

	/** print -- just calls draw() directly. */
	public void print(Graphics g) {
		g.setColor(Color.black);	// interim
		draw(g);
	}

	/** draw -- common code for paint & print -- draw the object. */
	protected abstract void draw(Graphics g);

	/** Edit the Attributes. */
	public void editAttributes() {
		JOptionPane.showMessageDialog(null,
			"GObj Editor: not written yet", "GObj",
			JOptionPane.WARNING_MESSAGE);
	}

	/** Get the Parameter String (name+main values) of this GObj */
	public String toString() {
		Rectangle b = getBounds();
		return getClass()+"[@"+b.x+","+b.y+";w="+b.width+",h="+b.height+"]";
	}

	/** Clone. Object.clone is protected, so you must write this
	 * method even if it has nothing to do other than call super.clone().
	 */
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/** Return a small box around a control point.
	 * @author Jonathan Knudsen, in O'Reilly's <I>Java 2D</I> book.
	 */
	protected Shape getControlPoint(Point2D p) {
		return new Rectangle2D.Double(
			p.getX() - CP_SIZE / 2, p.getY() - CP_SIZE / 2, CP_SIZE, CP_SIZE);
	}
}
