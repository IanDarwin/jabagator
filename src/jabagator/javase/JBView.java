import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.util.*;
import javax.swing.*;

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

		panel = c;

		Container cp = getContentPane();

		model = m;

		cp.setLayout(new BorderLayout());
		toolBar = new JToolBar();

		ImageIcon ii = new ImageIcon("images" + '/' + "cut" + ".gif");
		cutAction = new AbstractAction("", ii) {
			public void actionPerformed(ActionEvent e) {
				model.cut();
				pasteAction.setEnabled(true);
			}
		};
		copyAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				model.copy();
				pasteAction.setEnabled(true);
			}
		};
		pasteAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				model.paste();
			}
		};

		// Construct the JToolBar 
		addToToolBar(toolBar, "Circle").addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				GOval g = new GOval();
				g.setLocation(100, 100);
				g.setSize(100, 100);
				model.add(g);
			}
		});
		addToToolBar(toolBar, "Rectangle").addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				GRect g = new GRect();
				g.setLocation(100, 100);
				g.setSize(100, 100);
				model.add(g);
			}
		});
		addToToolBar(toolBar, "Line");
		addToToolBar(toolBar, "Text").addActionListener(new ActionListener() {
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
		});
		toolBar.addSeparator();
		toolBar.add(cutAction);
		addToToolBar(toolBar, "copy").addActionListener(copyAction);
		addToToolBar(toolBar, "paste").addActionListener(pasteAction);

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

		ResourceBundle b = ResourceBundle.getBundle("Menus");

		JMenuItem mi;
		JMenu fm = mkMenu(b, "file");
		fm.add(mi=mkMenuItem(b, "file", "open"));
		mi.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				model.load();
			}
		});
		fm.add(mi=mkMenuItem(b, "file", "new"));
		fm.add(mi=mkMenuItem(b, "file", "save"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.save();
			}
		});
		fm.add(mi=mkMenuItem(b, "file", "saveas"));
		fm.add(mi=mkMenuItem(b, "file", "print"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doPrint();
			}
		});
		fm.addSeparator();
		fm.add(mi = mkMenuItem(b, "file", "exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		mb.add(fm);

		JMenu em = mkMenu(b, "edit");
		em.add(mi = mkMenuItem(b, "edit", "undo"));
		mi.setEnabled(false);
		em.addSeparator();
		em.add(mi=mkMenuItem(b, "edit", "cut"));
		mi.addActionListener(cutAction);
		em.add(mi=mkMenuItem(b, "edit", "copy"));
		mi.addActionListener(copyAction);
		em.add(mi=mkMenuItem(b, "edit", "delete"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.delete();
			}
		});
		em.add(mi=mkMenuItem(b, "edit", "paste"));
		mi.addActionListener(pasteAction);
		em.addSeparator();
		em.add(mi=mkMenuItem(b, "edit", "attributes"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.editSelected();
			}
		});
		mb.add(em);

		JMenu winMenu1 = mkMenu(b, "window");

		JMenu palMenu = mkMenu(b, "palettes");
		// palMenu.add(mi=mkCheckboxMenuItem(b, "palettes", "paintstyle", false));
		winMenu1.add(palMenu);
		mb.add(winMenu1);

		JMenu hm = mkMenu(b, "help");
		hm.add(mi=mkMenuItem(b, "help", "about"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JBView.this,
					"JabaGator(tm), the portable illustration program\n" +
					"Copyright (c) 1999, 2000 Ian F. Darwin\n" +
					"ian@darwinsys.com, www.darwinsys.com/freeware\n" +
					"Some icons  Copyright(C) 1998  by  Dean S. Jones\n" +
					"dean@gallant.com www.gallant.com/icons.htm",
					"About JabaGator(tm)", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mb.add(hm);
		// mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		// Set initial state of actions
		pasteAction.setEnabled(false);

		// Add the JComponent into the main window.
		cp.add(BorderLayout.CENTER, c);

		pack();
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

	/** Convenience routine for building the JToolBar */
    public JButton addToToolBar(JToolBar toolBar, String name) {
		JButton b = 
			new JButton(new ImageIcon("images" + '/' + name + ".gif"));
		toolBar.add(b);
		b.setToolTipText(name);
		// b.setPad(new Insets(0,0,0,0));
		return b;
    }

	/** Convenience routine to make a Menu */
	public JMenu mkMenu(ResourceBundle b, String name) {
		String menuLabel;
		try { menuLabel = b.getString(name+".label"); }
		catch (MissingResourceException e) { menuLabel=name; }
		return new JMenu(menuLabel);
	}

	/** Convenience routine to make a MenuItem */
	public JMenuItem mkMenuItem(ResourceBundle b, String menu, String name) {
		String miLabel;
		try { miLabel = b.getString(menu + "." + name + ".label"); }
		catch (MissingResourceException e) { miLabel=name; }
		String key = null;
		try { key = b.getString(menu + "." + name + ".key"); }
		catch (MissingResourceException e) { key=null; }

		if (key == null)
			return new JMenuItem(miLabel);
		else
			return new JMenuItem(miLabel /*, new MenuShortcut(key.charAt(0))*/);
	}

	/** Convenience routine to make a CheckboxMenuItem; these
	 * can not have MenuShortcuts
	 */
	public CheckboxMenuItem mkCheckboxMenuItem(ResourceBundle b, String menu, String name, boolean setting) {
		String miLabel;
		try { miLabel = b.getString(menu + "." + name + ".label"); }
		catch (MissingResourceException e) { miLabel=name; }

		// Paranoia?
		String key = null;
		try {
			key = b.getString(menu + "." + name + ".key");
			System.err.println("Warning: shortcut "+key+
				" for CheckboxMenuItem "+name+" ignored.");
		}
		catch (MissingResourceException e) { key=null; }

		return new CheckboxMenuItem(miLabel, setting);
	}
}
