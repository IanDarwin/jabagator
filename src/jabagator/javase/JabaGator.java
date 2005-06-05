package main;

import java.awt.Color;

import javax.swing.JPanel;

import jabagator.*;

import com.darwinsys.macosui.MacOSAppAdapter;
import com.darwinsys.macosui.MacOSUtil;
import com.darwinsys.swingui.UtilGUI;

/** This is a SKELETON ONLY for a draw program like Illustrator
 * This part just creates the whole mess.
 */
public class JabaGator {
	/** "main program" method - construct and show */
	public static void main(String av[]) {

		if (MacOSUtil.isMacOS()) {
			MacOSUtil.setMacOS("JabaGator");
		}

		// create a JBModel object
		JBModel m = new JBModel();

		// create a JBView object, tell it to show up
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(null);

		// JBView is a Frame.
		JBView view = new JBView(m, panel);
		if (MacOSUtil.isMacOS()) {
			new MacOSAppAdapter(view, view, view, view, view).register();
		}
		view.setSize(500, 400);
		UtilGUI.centre(view);
		view.setVisible(true);

		m.setView(view);			// interconnect

		// connect the Controller
		JBCont c = new JBCont(m, view);
		panel.addMouseListener(c);
		panel.addMouseMotionListener(c);
	}
}
