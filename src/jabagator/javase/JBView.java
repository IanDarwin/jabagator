package jabagator;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.*;
import javax.swing.*;
import java.net.URL;

import com.darwinsys.swingui.I18N;
import com.darwinsys.swingui.IntlAction;

/** This will become the View part of an MVC.
 * It will display a list of GObjs and paint them.
 */
public class JBView extends JFrame {
	final int PAD = 10;
	/** For showStatus() */
	JLabel statusLabel;
	/** The model that we are viewing */
	JBModel model;
	/** The JToolBar - part of JFC */
	JToolBar toolBar;
	/** The "main" container. */
	JComponent panel;
	/** Actions */
	Action cutAction, copyAction, pasteAction;

	/** Construct the object including its GUI */
	JBView(JBModel m, JComponent c) {
		super("JabaGator");

		// Get the resources for menus, buttons, etc. 
		ResourceBundle b = ResourceBundle.getBundle("GatorAid");

		panel = c;

		Container cp = getContentPane();

		model = m;

		cp.setLayout(new BorderLayout());

		// Construct the JToolBar 
		toolBar = new JToolBar();

		cp.add(BorderLayout.NORTH, toolBar); 
		cp.add(BorderLayout.SOUTH,
			statusLabel = new JLabel("Welcome to JabaGator"));

        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// if (m.unsavedChanges())
				//		do a YesNoQuit dialog
				// meanwhile:
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		// Construct the MENU part of the GUI
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenuItem mi;
		JMenu fm = I18N.mkMenu(b, "file");
		Action openAction = new IntlAction(
			b, "file.open", getJLFImageIcon("general/Open")) {
			public void actionPerformed(ActionEvent e) {
				model.load();
			}
		};
		fm.add(openAction);
		toolBar.add(openAction);

		Action newAction = new IntlAction(
			b, "file.new", getJLFImageIcon("general/New")) {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JBView.this,
					"Not Written Yet", "Not Written Yet",
					JOptionPane.ERROR_MESSAGE);
			}
		};
		fm.add(newAction);
		toolBar.add(newAction);

		Action saveAction = new IntlAction(
			b, "file.save", getJLFImageIcon("general/Save")) {
			public void actionPerformed(ActionEvent e) {
				model.save();
			}
		};
		fm.add(saveAction);
		toolBar.add(saveAction);

		fm.add(mi=I18N.mkMenuItem(b, "file", "saveas"));
		fm.add(mi=I18N.mkMenuItem(b, "file", "print"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPrint();
			}
		});
		fm.addSeparator();
		fm.add(mi = I18N.mkMenuItem(b, "file", "exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		mb.add(fm);

		// GRAPHICS ACTIONS
		Action circleAction = 
			new IntlAction(b, "graphics.circle", getMyImageIcon("Circle")){
			public void actionPerformed(ActionEvent e) {
				GOval g = new GOval();
				g.setLocation(100, 100);
				g.setSize(100, 100);
				model.add(g);
			}
		};
		toolBar.add(circleAction);

		Action rectAction =
			new IntlAction(b, "graphics.rect", getMyImageIcon("Rectangle")) {
			public void actionPerformed(ActionEvent e) {
				GRect g = new GRect();
				g.setLocation(100, 100);
				g.setSize(100, 100);
				model.add(g);
			}
		};
		toolBar.add(rectAction);

		Action drawLineAction =
			new IntlAction(b, "graphics.line", getMyImageIcon("Line")) {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(JBView.this,
				"Not written yet", "Not written yet",
				JOptionPane.ERROR_MESSAGE);
			}
		};
		toolBar.add(drawLineAction);

		Action textAction =
			new IntlAction(b, "graphics.text", getMyImageIcon("Text")) {
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(JBView.this,
					"Text:", "Text", JOptionPane.QUESTION_MESSAGE); 
				if (text == null)
					return;
				GText g = new GText();
				model.add(g);
				g.setLocation(100, 100);
				g.setText(text);
			}
		};
		toolBar.add(textAction);

		// EDIT MENU AND ACTIONS

		JMenu em = I18N.mkMenu(b, "edit");
		mb.add(em);
		toolBar.addSeparator();

		cutAction = 
			new IntlAction(b, "edit.cut", getJLFImageIcon("general/Cut")) {
			public void actionPerformed(ActionEvent e) {
				model.cut();
				pasteAction.setEnabled(true);
			}
		};
		toolBar.add(cutAction);
		em.add(cutAction);

		copyAction = 
			new IntlAction(b, "edit.copy", getJLFImageIcon("general/Copy")) {
			public void actionPerformed(ActionEvent e) {
				model.copy();
				pasteAction.setEnabled(true);
			}
		};
		toolBar.add(copyAction);
		em.add(copyAction);

		pasteAction = 
			new IntlAction(b, "edit.paste", getJLFImageIcon("general/Paste")) {
			public void actionPerformed(ActionEvent e) {
				model.paste();
			}
		};
		toolBar.add(pasteAction);
		em.add(pasteAction);

		Action undoAction = 
			new IntlAction(b, "edit.undo", getJLFImageIcon("general/Undo")) {
			public void actionPerformed(ActionEvent e) {
				// model.undo();
			}
		};
		toolBar.add(undoAction);
		em.add(undoAction);
		undoAction.setEnabled(false);

		Action deleteAction = 
			new IntlAction(b, "edit.delete", getJLFImageIcon("general/Delete")) {
			public void actionPerformed(ActionEvent e) {
				model.delete();
			}
		};
		toolBar.add(deleteAction);
		em.add(deleteAction);

		em.addSeparator();
		em.add(mi=I18N.mkMenuItem(b, "edit", "attributes"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.editSelected();
			}
		});

		JMenu winMenu1 = I18N.mkMenu(b, "window");
		mb.add(winMenu1);

		JMenu palMenu = I18N.mkMenu(b, "palettes");
		winMenu1.add(palMenu);
		// palMenu.add(mi=I18N.mkCheckboxMenuItem(b, "palettes", "paintstyle", false));

		JMenu hm = I18N.mkMenu(b, "help");
		mb.add(hm);
		// mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		Action aboutAction = 
			new IntlAction(b, "help.about", getJLFImageIcon("general/About")) {
			public void actionPerformed(ActionEvent e) {
				showAboutBox();
			}
		};
		toolBar.add(aboutAction);
		hm.add(aboutAction);

		// Set initial state of actions
		pasteAction.setEnabled(false);

		// Add the JComponent into the main window.
		cp.add(BorderLayout.CENTER, c);

		pack();
	}

	public void showAboutBox() {
		JOptionPane.showMessageDialog(JBView.this,
			"JabaGator(tm), the portable illustration program\n" +
			"Copyright (c) 1999-2003 Ian F. Darwin\n" +
			"http://www.darwinsys.com/\n" +
			"Icons from the Sun JLF Image Repository (c) Sun Micro.\n"+
			"Other icons Copyright(C) 1998  by  Dean S. Jones\n" +
			"dean@gallant.com www.gallant.com/icons.htm",
			"About JabaGator(tm)", JOptionPane.INFORMATION_MESSAGE);
	}

	public void addGObj(GObj g) {
		panel.add(g);
	}
	public void removeGObj(GObj g) {
		panel.remove(g);
	}
	public void removeAllGObjs() {
		panel.removeAll();
	}

	public void showStatus(String s) {
		statusLabel.setText(s);
	}

	protected void doPrint() {
		try {
			PrinterJob pjob = PrinterJob.getPrinterJob();
			pjob.setJobName("JabaGator - " + "Untitled1");
			pjob.setCopies(1);
			// Tell the print system how to print our pages.
			pjob.setPrintable(new Printable() {
				/** called from the printer system to print each page */
				public int print(Graphics pg, PageFormat pf, int pageNum) {
					if (pageNum>0)		// we only print one page
						return Printable.NO_SUCH_PAGE;	// ie., end of job

					// Now (drum roll please), ask "panel" to paint itself
					// on the printer, by calling its print() method with 
					// a Printjob Graphics instead of a Window Graphics.
					panel.printAll(pg);

					// Tell print system that the page is ready to print
					return Printable.PAGE_EXISTS;
				}
			});

			if (pjob.printDialog() == false)	// choose printer
				return;				// user cancelled

			pjob.print();			 // Finally, do the printing.
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(this,
				"Printer error" + pe, "Printing error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/** Convenience routine to get an application-local image */
	private ImageIcon getMyImageIcon(String name) {
		String fullName = "/images" + '/' + name + ".gif";
		return getMyImage(fullName);
	}

	/** Convenience routine to get a JLF-standard image */
	private ImageIcon getJLFImageIcon(String name) {
		String imgLocation = "/toolbarButtonGraphics/" + name + "24.gif";
		return getMyImage(imgLocation);
	}

	private ImageIcon getMyImage(String imgName) {
		URL imageURL = getClass().getResource(imgName);

		if (imageURL == null) {
			throw new IllegalArgumentException("No image: " + imgName);
		}
		ImageIcon ii = new ImageIcon(imageURL);
		return ii;
	}

	/** Convenience routine for building the JToolBar */
    public JButton addToToolBar(JToolBar toolBar, String descr, ImageIcon ii) {
		JButton b = new JButton(ii);
		toolBar.add(b);
		b.setToolTipText(descr);
		return b;
    }
}
