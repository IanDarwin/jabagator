import java.awt.*;
import java.util.*;

/** This might someday be a draw program like Adobe Illustrator
 *
 * This part is the MODEL.
 */
public class JBModel {
	/** The list of GObjs to be displayed */
	Vector v;

	JBModel() {
		v = new Vector();
	}

	void delete() {
		System.out.println("Would delete current object");
	}

	void fakeObjs() {
	// highly fake:
		GObj t;
	// highly fake:
		t = new GText();
		((GText)t).setText("Hello");
		t.setXY(100, 100);
		v.addElement(t);
	// highly fake:
		t = new GLine();
		t.setXY(150, 200);
		t.setWH(20,20);
		v.addElement(t);
	// highly fake:
		t = new GOval();
		t.setXY(50, 100);
		t.setWH(60,20);
		v.addElement(t);
	// highly fake:
		t = new GRect();
		t.setXY(250, 200);
		t.setWH(20,40);
		v.addElement(t);
	// "This feelin' of fakin' it, of not really makin' it,
	// I still haven't shaken it..."  -- simon & garfunkel
		Polygon p = new Polygon();
		p.addPoint(0,0);
		p.addPoint(0, 50);
		p.addPoint(50,0);
		t = new GPoly(p);
		t.setXY(300,70);
		v.addElement(t);
	// highly fake:
		t = new GRect();
		t.setXY(450, 300);
		t.setWH(20,40);
		v.addElement(t);
	}
}

