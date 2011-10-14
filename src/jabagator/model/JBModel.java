package jabagator;

import jabagator.model.GLine;
import jabagator.model.GObj;
import jabagator.model.GOval;
import jabagator.model.GPoly;
import jabagator.model.GRect;
import jabagator.model.GText;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javase.JBView;

import javax.swing.JOptionPane;

/** This might someday be a draw program like Adobe Illustrator
 *
 * This part is the MODEL.
 */
public class JBModel {
	/** The View */
	JBView view;
	/** The list of GObjs to be displayed. Make Arraylist after add works. */
	List<GObj> v;
	/** The default filename. */
	protected final static String DEFAULT_FILE =  "jabagator.save";
	/** A mini-clipboard. */
	GObj clipObject;

	public JBModel() {
		v = new ArrayList<GObj>();
	}

	public void setView(JBView c) {
		view = c;
	}

	public void showStatus(String s) {
		view.showStatus(s);
	}

	public void add(GObj o) {
		v.add(o);
		view.addGObj(o);
		//view.repaint();
		o.repaint();
	}

	public void delete() {
		GObj o;
		if ((o = getSelected()) != null) {
			v.remove(o);
			view.removeGObj(o);
			view.repaint();
			showStatus("Deleted one object");
		} else
		JOptionPane.showMessageDialog(view,
			"Can only delete if you have something selected",
			"Can't delete", JOptionPane.WARNING_MESSAGE);
	}

	public void cut() {
		GObj o;
		if ((o = getSelected()) != null) {
			clipObject = o;
			v.remove(o);
			view.removeGObj(o);
			view.repaint();
			showStatus("Cut one " + o.describe());
		} else
		JOptionPane.showMessageDialog(view,
			"Can only cut if you have something selected",
			"Can't cut", JOptionPane.WARNING_MESSAGE);
	}

	public void copy() {
		GObj o;
		if ((o = getSelected()) != null) {
			clipObject = o;
			showStatus("Copied one " + o.describe());
		} else
		JOptionPane.showMessageDialog(view,
			"Can only copy if you have something selected",
			"Can't copy", JOptionPane.WARNING_MESSAGE);
	}

	public void paste() {
		if (clipObject != null) {
			GObj o;
			try {
				o = (GObj)clipObject.clone();
			} catch (CloneNotSupportedException e) {
				System.err.println("CantHappen: " + e);
				return;
			}
			Dimension d = view.getSize();
			o.setLocation(d.width/2, d.height/2);
			add(o);
			setSelected(o);
			showStatus("Pasted one " + o.describe());
		} else
			JOptionPane.showMessageDialog(view,
				"Nothing to paste",
				"Paste", JOptionPane.WARNING_MESSAGE);
	}

	public void editSelected() {
		GObj go;
		if ((go = getSelected()) == null) {
			JOptionPane.showMessageDialog(view,
				"Nothing selected to edit",
				"Paste", JOptionPane.WARNING_MESSAGE);
			return;
		}
		go.editAttributes();
	}

	/** Find the currently selected object, if any.
	 * Should return List, not GObj.
	 */
	protected GObj getSelected() {
		System.out.println("Finding Selected object...");
		Iterator<GObj> it = v.iterator();
		while (it.hasNext()) {
			GObj o = (GObj)it.next();
			if (o.isSelected()) {
				System.out.println("It's a " + o.describe());
				return o;
			}
		}
		return null;
	}

	/** Enforce the requirement that there be only one selected object. */
	protected void setSelected(GObj ng) {
		GObj g = getSelected();
		if (g != null)
			g.setSelected(false);
		ng.setSelected(true);
	}


	void createFakeObjs() {
		GObj t;
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

	public void setContents(List<GObj> v) {
		this.v = v;
	}
	public List<GObj> getContents() {
		return v;
	}

	public void undo() {
		Toolkit.getDefaultToolkit().beep();
		System.out.println("Undo not implemented");
	}
}
