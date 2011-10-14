package jabagator.javase;

import jabagator.model.JBModel;

import java.awt.Color;

import javax.swing.JPanel;

import com.darwinsys.swingui.UtilGUI;

/** This is a SKELETON ONLY for a draw program like Illustrator
 * This part just creates the whole mess.
 */
public class JabaGator {
	/** "main program" method - construct and show */
	public static void main(String av[]) {

		// create a JBModel object
		JBModel m = new JBModel();

		// create a JBView object, tell it to show up
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		// JBView is a JFrame.
		JBView view = new JBView(m, panel);
		view.setSize(500, 400);
		UtilGUI.centre(view);
		view.setVisible(true);

		m.setView(view);			// interconnect

		// connect the Controller
		JBController c = new JBController(m, view);
		panel.addMouseListener(c);
		panel.addMouseMotionListener(c);
	}
}
