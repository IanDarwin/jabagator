/** This is a SKELETON ONLY for a draw program like Illustrator
 * This part just creates the whole mess.
 */
public class JabaGator {
	/** "main program" method - construct and show */
	public static void main(String av[]) {

		// create a JBModel object
		JBModel m = new JBModel();

		// create a JBView object, tell it to show up
		JBView v = new JBView(m, 400,300);
		v.setVisible(true);

		// connect the Controller
		v.addMouseListener(new JBCont(m, v));
		v.addMouseMotionListener(new JBCont(m, v));
	}
}

