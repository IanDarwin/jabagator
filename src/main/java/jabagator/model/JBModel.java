package jabagator.model;

import jabagator.javase.JBView;
import jabagator.view.MessageLevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** This might someday be a draw program like Adobe Illustrator
 *
 * This part is the MODEL.
 */
public class JBModel {
	/** The View */
	JBView view;
	/** The list of GObjs to be displayed. */
	List<GObject> v;
	/** The default filename. */
	protected final static String DEFAULT_FILE =  "jabagator.save";
	/** A mini-clipboard. */
	GObject clipObject;

	public JBModel() {
		v = new ArrayList<GObject>();
	}

	public void setView(JBView c) {
		view = c;
	}

	public void showStatus(String s) {
		view.showStatus(s);
	}

	public void add(GObject o) {
		v.add(o);
		view.addGObj(o);
		//view.repaint();
		o.repaint();
	}

	public void delete() {
		GObject o;
		if ((o = getSelected()) != null) {
			v.remove(o);
			view.removeGObj(o);
			view.repaint();
			showStatus("Deleted one object");
		} else
			view.showMessageDialog(
			"Can only delete if you have something selected",
			"Can't delete", MessageLevel.WARNING);
	}

	public void cut() {
		GObject o;
		if ((o = getSelected()) != null) {
			clipObject = o;
			v.remove(o);
			view.removeGObj(o);
			view.repaint();
			showStatus("Cut one " + o.describe());
		} else
			view.showMessageDialog(
			"Can only cut if you have something selected",
			"Can't cut", MessageLevel.WARNING);
	}

	public void copy() {
		GObject o;
		if ((o = getSelected()) != null) {
			clipObject = o;
			showStatus("Copied one " + o.describe());
		} else
		view.showMessageDialog(
			"Can only copy if you have something selected",
			"Can't copy", MessageLevel.WARNING);
	}

	public void paste() {
		if (clipObject != null) {
			GObject o;
			try {
				o = (GObject)clipObject.clone();
			} catch (CloneNotSupportedException e) {
				System.err.println("CantHappen: " + e);
				return;
			}
			int width = view.getWidth();
			int height = view.getHeight();
			o.setLocation(width/2, height/2);
			add(o);
			setSelected(o);
			showStatus("Pasted one " + o.describe());
		} else
			view.showMessageDialog(
				"Nothing to paste",
				"Paste", MessageLevel.WARNING);
	}

	public void editSelected() {
		GObject go;
		if ((go = getSelected()) == null) {
			view.showMessageDialog(
				"Nothing selected to edit",
				"Paste", MessageLevel.WARNING);
			return;
		}
		go.editAttributes();
	}

	/** Find the currently selected object, if any.
	 * Should return List, not GObj.
	 */
	protected GObject getSelected() {
		System.out.println("Finding Selected object...");
		Iterator<GObject> it = v.iterator();
		while (it.hasNext()) {
			GObject o = (GObject)it.next();
			if (o.isSelected()) {
				System.out.println("It's a " + o.describe());
				return o;
			}
		}
		return null;
	}

	/** Enforce the requirement that there be only one selected object. */
	protected void setSelected(GObject ng) {
		GObject g = getSelected();
		if (g != null)
			g.setSelected(false);
		ng.setSelected(true);
	}


	void createFakeObjs() {
		GObject t;
		// highly fake:
			t = new GText();
			view.addGObj(t);
			((GText)t).setText("Hello");
			t.setLocation(300, 200);
			v.add(t);
		// highly fake:
			t = new GLine(20, 20);
			t.setLocation(150, 200);
			v.add(t);
			view.addGObj(t);
		// highly fake:
			t = new GOval();
			t.setLocation(50, 100);
			t.setSize(60,20);
			v.add(t);
			view.addGObj(t);
		// highly fake:
			t = new GRect(50, 50);
			t.setLocation(200, 150);
			t.setSize(50,50);
			v.add(t);
			view.addGObj(t);
		// "This feelin' of fakin' it, of not really makin' it,
		// I still haven't shaken it..."  -- simon & garfunkel
			GPoly p = new GPoly();
			p.addPoint(0,0);
			p.addPoint(0, 50);
			p.addPoint(50,0);
			v.add(p);
			view.addGObj(p);
			p.setLocation(300,70);
		// highly fake:
			// t = new GCubic();
			// t.setLocation(450, 300);
			// t.setSize(20,40);
			// v.addElement(t);
			// view.addGObj(t);
	}

	public void setContents(List<GObject> v) {
		this.v = v;
	}
	public List<GObject> getContents() {
		return v;
	}

	public void undo() {
		view.showMessageDialog("Undo not implemented", "Error", MessageLevel.WARNING);
	}
}
