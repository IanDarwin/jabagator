package jabagator;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

import jabagator.model.*;

/** This might someday be a draw program like Adobe Illustrator
 *
 * This part is the MODEL.
 */
public class JBModel {
	/** The View */
	JBView view;
	/** The list of GObjs to be displayed. Make Arraylist after add works. */
	Vector v;
	/** The default filename. */
	protected final static String DEFAULT_FILE =  "jabagator.save";
	/** A mini-clipboard. */
	GObj clipObject;

	public JBModel() {
		v = new Vector();
	}

	public Iterator iterator() {
		return v.iterator();
	}

	public void setView(JBView c) {
		view = c;
	}

	public void showStatus(String s) {
		view.showStatus(s);
	}

	public void add(GObj o) {
		v.addElement(o);
		view.addGObj(o);
		//view.repaint();
		o.repaint();
	}

	public void delete() {
		GObj o;
		if ((o = getSelected()) != null) {
			v.removeElement(o);
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
			v.removeElement(o);
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
				"Nothing to paste",
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
		Iterator it = v.iterator();
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

	public void load() {
		fakeObjs();
		try {
			ObjectInputStream is = new ObjectInputStream(
				new FileInputStream(DEFAULT_FILE));
			// Read the entire data structure.
			v = (Vector)is.readObject();
			// Now that the read succeeded, clear old from View, add new.
			view.removeAllGObjs();
			Iterator it = v.iterator();
			while (it.hasNext()) {
				GObj go = (GObj) it.next();
				view.addGObj(go);
			}
			is.close();
		} catch (ClassNotFoundException e) {
			System.err.println("I O Error " + e);
		} catch(IOException e) {
			System.err.println("I O Error " + e);
			return;
		}
		view.repaint();
		showStatus("Loaded " + v.size() + " objects");
	}

	public void save() {
		try {
			ObjectOutputStream os = new ObjectOutputStream(
				new FileOutputStream(DEFAULT_FILE));
			os.writeObject(v);
			os.flush();
			os.close();
		} catch(IOException e) {
			showStatus("I O Error " + e);
			return;
		}
		showStatus("Saved " + v.size() + " objects");
	}

	private void fakeObjs() {
		GObj t;
		// highly fake:
			t = new GText();
			view.addGObj(t);
			((GText)t).setText("Hello");
			t.setLocation(300, 200);
			v.addElement(t);
		// highly fake:
			t = new GLine(20, 20);
			t.setLocation(150, 200);
			v.addElement(t);
			view.addGObj(t);
		// highly fake:
			t = new GOval();
			t.setLocation(50, 100);
			t.setSize(60,20);
			v.addElement(t);
			view.addGObj(t);
		// highly fake:
			t = new GRect(50, 50);
			t.setLocation(200, 150);
			t.setSize(50,50);
			v.addElement(t);
			view.addGObj(t);
		// "This feelin' of fakin' it, of not really makin' it,
		// I still haven't shaken it..."  -- simon & garfunkel
			GPoly p = new GPoly();
			p.addPoint(0,0);
			p.addPoint(0, 50);
			p.addPoint(50,0);
			v.addElement(p);
			view.addGObj(p);
			p.setLocation(300,70);
		// highly fake:
			// t = new GCubic();
			// t.setLocation(450, 300);
			// t.setSize(20,40);
			// v.addElement(t);
			// view.addGObj(t);
	}
}
