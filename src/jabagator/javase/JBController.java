package jabagator.javase;

import jabagator.model.GObj;
import jabagator.model.JBModel;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** JabaGator Mouse Controller
 */
public class JBController extends Object 
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
	public JBController(JBModel model, JBView view) {
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
		Component tmpObj = view.getPanel().getComponentAt(startX, startY);

		// If not instanceof GObj, user clicked in panel but not on GObj
		if (tmpObj != null && tmpObj instanceof GObj) {
			((GObj)tmpObj).setSelected(true);
			showStatus("findElement("+startX+","+startY+")="+selObj);
			selObj = tmpObj;
		} else {
			showStatus("");
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
}
