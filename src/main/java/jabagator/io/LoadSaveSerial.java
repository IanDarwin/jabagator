package jabagator.io;

import jabagator.model.GObject;
import jabagator.model.JBModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/** 
 * Load and save the MODEL using Java Object Serialization.
 */
public class LoadSaveSerial implements LoadSave {
	/** The default filename. */
	protected final static String DEFAULT_FILE =  "jabagator.save";
	private static final LoadSaveSerial INSTANCE;
	
	/** Since we can't inherit static methods from an Interface in Java,
	 * make a Singleton and use its instance methods; this way we can
	 * use and interface...
	 */
	static {
		INSTANCE = new LoadSaveSerial();
	}

	public static LoadSave getInstance() {
		return INSTANCE;
	}

	public void load(JBModel model) {
		List<GObject> v = null;
		try (
			ObjectInputStream is = new ObjectInputStream(
				new FileInputStream(DEFAULT_FILE));) {
			// Read the entire data structure.
			v = (List<GObject>)is.readObject();
			model.setContents(v);
		} catch (ClassNotFoundException e) {
			System.err.println("I O Error " + e);
		} catch(IOException e) {
			System.err.println("I O Error " + e);
			return;
		}
		System.out.println("Loaded " + v.size() + " objects");
		for (GObject g : v) {
			System.out.println(g);
		}
	}

	public void save(JBModel model) {
		List<GObject> v = model.getContents();
		try (
			ObjectOutputStream os = new ObjectOutputStream(
				new FileOutputStream(DEFAULT_FILE));) {
			os.writeObject(v);
			os.flush();
		} catch(IOException e) {
			System.out.println("I O Error " + e);
			return;
		}
		System.out.println("Saved " + v.size() + " objects");
	}
}
