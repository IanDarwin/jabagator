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
	// highly fake:
		GObj t = new GText();
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
		t.setWH(20,20);
		v.addElement(t);
	// highly fake:
		t = new GRect();
		t.setXY(250, 200);
		t.setWH(20,40);
		v.addElement(t);
	}
}

