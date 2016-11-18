package jabagator.javase;

import java.awt.Component;
import java.awt.Graphics;

import jabagator.model.GObj;

/** An AWT delegate for a GObj 
 */
public class AWTGObj extends Component {
	GObj inner;
	
	public boolean equals(Object obj) {
		return inner.equals(obj);
	}

	public void paint(Graphics g) {
		inner.paint(g);
	}

	public void setSize(int w, int h) {
		inner.setSize(w, h);
	}

	public java.awt.Dimension getSize() {
		jabagator.model.Dimension d = inner.getSize();
		return new java.awt.Dimension(d.getWidth(), d.getHeight());
	}

	public java.awt.Point getLocation() {
		jabagator.model.Point p = inner.getLocation();
		return new java.awt.Point(p.x, p.y);
	}

	public int hashCode() {
		return inner.hashCode();
	}

	public void setLocation(int x, int y) {
		inner.setLocation(x, y);
	}

	public void setSelected(boolean b) {
		// TODO Auto-generated method stub
		
	}


}
