import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** This will become the View part of an MVC.
 * It will display a list of GObjs and paint them.
 */
public class JBView extends Frame {
	final int PAD = 10;
	int width;
	int height;
	/** For showStatus() */
	Label status;
	/** The model that we are viewing */
	JBModel model;
	/** The vector of objects in the model */
	Vector v;
	// The Toolbar - part of JFC
	// JToolBar toolBar = new JToolBar();


	/** Construct the object including its GUI */
	JBView(JBModel m, int w, int h) {
		super("JabaGator");

		model = m;
		v = model.v;
		width = w;
		height = h;

		setLayout(new BorderLayout());
		add("South", status = new Label("Welcome to the world of Java"));

        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// if (data.unsavedChanges()
				//		do a YesNoQuit dialog
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		// Construct the MENU part of the GUI
		MenuBar mb = new MenuBar();
		setMenuBar(mb);

		ResourceBundle b = ResourceBundle.getBundle("Menus");

		MenuItem mi;
		Menu fm = mkMenu(b, "file");
		fm.add(mi=mkMenuItem(b, "file", "open"));
		fm.add(mi=mkMenuItem(b, "file", "new"));
		fm.add(mi=mkMenuItem(b, "file", "save"));
		fm.add(mi=mkMenuItem(b, "file", "saveas"));
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

		Menu vm = mkMenu(b, "edit");
		vm.add(mkMenuItem(b, "edit", "undo"));
		vm.addSeparator();
		vm.add(mi=mkMenuItem(b, "edit", "cut"));
		vm.add(mi=mkMenuItem(b, "edit", "copy"));
		vm.add(mi=mkMenuItem(b, "edit", "delete"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.delete();
			}
		});
		vm.add(mi=mkMenuItem(b, "edit", "paste"));
		mb.add(vm);

		Menu winMenu1 = mkMenu(b, "window");
		Menu palMenu = mkMenu(b, "palettes");
		palMenu.add(mi=mkCheckboxMenuItem(b, "palettes", "paintstyle", false));
		winMenu1.add(palMenu);
		mb.add(winMenu1);

		Menu hm = mkMenu(b, "help");
		hm.add(mi=mkMenuItem(b, "help", "about"));
		mb.add(hm);
		mb.setHelpMenu(hm);		// needed for portability (Motif, etc.).

		pack();

		// Construct the TOOLBAR part of the GUI - needs JFC
		// addTool(toolBar, "new");
		// addTool(toolBar, "open");
		// addTool(toolBar, "save");
		// toolBar.addSeparator();
		// addTool(toolBar, "cut");
		// addTool(toolBar, "copy");
		// addTool(toolBar, "paste");
	}

	// Convenience routine for building the Toolbar - needs JFC
    // public void addTool(JToolBar toolBar, String name) {
	// JButton b = (JButton) toolBar.add(
	//	new JButton(new ImageGlyph("images/" + name + ".gif")));
	// b.setToolTipText(name);
	// b.setPad(new Insets(0,0,0,0));
    // }

	/** Convenience routine to make a Menu */
	public Menu mkMenu(ResourceBundle b, String name) {
		String menuLabel;
		try { menuLabel = b.getString(name+".label"); }
		catch (MissingResourceException e) { menuLabel=name; }
		return new Menu(menuLabel);
	}
	/** Convenience routine to make a MenuItem */
	public MenuItem mkMenuItem(ResourceBundle b, String menu, String name) {
		String miLabel;
		try { miLabel = b.getString(menu + "." + name + ".label"); }
		catch (MissingResourceException e) { miLabel=name; }
		String key = null;
		try { key = b.getString(menu + "." + name + ".key"); }
		catch (MissingResourceException e) { key=null; }

		if (key == null)
			return new MenuItem(miLabel);
		else
			return new MenuItem(miLabel, new MenuShortcut(key.charAt(0)));
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
	public Dimension getMinimumSize() {
		return new Dimension(width, height);
	}
	public Dimension getPreferredSize() {
		return new Dimension(width+PAD, height+PAD);
	}
	public Dimension getMaximumSize() {
		return new Dimension(width*2, height*2);
	}
	public void paint(Graphics g) {
		for (int i = 0; i<v.size(); i++) {
			GObj j = (GObj)(v.elementAt(i));
			// System.out.println("Drawing " + j);
			j.draw(g);
		}
	}
}
