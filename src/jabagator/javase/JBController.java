package jabagator;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** jbmcont - JabaGator Mouse Controller
 */
public class JBCont extends Object 
		implements MouseListener, MouseMotionListener {
	/** The component we are controlling */
	JBModel model;
	/** The view on the model */
	JBView view;
	/** true if we are in drag */
	boolean inDrag;
	/** starting location of a drag */
	int startX = -1, startY = -1;
	/** current location of a drag */
	int curX, curY;

	/** Construct an JBController object */
	public JBCont(JBModel model, JBView view) {
		super();
		this.model = model;
		this.view = view;
		curX = curY = 0;
	}

	public void showStatus(String s) {
		view.showStatus(s);
	}

	// Five methods from MouseListener:

	/** Called when the mouse has been clicked (pressed and release). */
	public void mouseClicked(MouseEvent e)  {
	}
	/** Called when the mouse exits the window . */
	public void mouseEntered(MouseEvent e)  {
	}
	/** Called when the mouse exits the window . */
	public void mouseExited(MouseEvent e)  {
	}

	/** The object being worked upon */
	private Component selObj;

	/** Called when the mouse has been pressed. */
	public void mousePressed(MouseEvent e)  {
		Point p = e.getPoint();
		startX = p.x; startY = p.y;
		showStatus("mousePressed at " + startX + "," + startY);
		inDrag = true;
		if (selObj != null)
			((GObj)selObj).setSelected(false);
		selObj = findElement(startX, startY);
		if (selObj != null) {
			((GObj)selObj).setSelected(true);
			showStatus("findElement("+startX+","+startY+")="+selObj);
		}
	}

	/** Called when the mouse has been released. */
	public void mouseReleased(MouseEvent e)  {
		// If we just finished a drag, the view needs
		// to be refreshed.
		// if (inDrag) {
		//	showStatus("drag ended");
		// }
		inDrag = false;
	}

	// And two methods from MouseMotionListener:

	/** Called by AWT when the mouse is moved with a button held down. */
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		curX = p.x; curY = p.y;
		showStatus("mouse Dragged to " + curX + "," + curY);
		if (selObj == null) {
			return;
		}
		selObj.setLocation(curX, curY);
		view.repaint();
	}

	/** Called by AWT when the mouse is moved with no buttons down;
	 * we don't care about this, but have to provide this since we
	 * implement MouseMotionListener.
	 */
	public void mouseMoved(MouseEvent e) {
	}

	/** Returns the Component that contains the given element, if any */
	public Component findElement(int oldx, int oldy) {
		Iterator it = model.iterator();
		while (it.hasNext()) {
			Component j = (Component)it.next();
			Rectangle r = j.getBounds();
			if (oldx > r.x && oldx < (r.x + r.width) &&
				oldy > r.y && oldy < (r.y + r.height))
				return j;
		}
		return null;
	}
}
