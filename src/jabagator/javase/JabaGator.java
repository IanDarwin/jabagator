/** This is a SKELETON ONLY for a draw program like Illustrator
 * This part just creates the whole mess.
 */
public class JabaGator {
	/** "main program" method - construct and show */
	public static void main(String av[]) {

		// create a JBModel object
		JBModel m = new JBModel();

		// create a JBView object, tell it to show up
		JBView v = new JBView(m, 500,400);
		v.setVisible(true);

		GObj.setView(v);		// model & view cohabit

		m.fakeObjs();		// construct a few objects to start

		// connect the Controller
		JBCont c = new JBCont(m, v);
		v.addMouseListener(c);
		v.addMouseMotionListener(c);
	}
}

