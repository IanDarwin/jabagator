package jabagator.io;

import jabagator.model.GObj;
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
		ObjectInputStream is = null;
		List<GObj> v = null;
		try {
			is = new ObjectInputStream(
				new FileInputStream(DEFAULT_FILE));
			// Read the entire data structure.
			v = (List<GObj>)is.readObject();
			model.setContents(v);
		} catch (ClassNotFoundException e) {
			System.err.println("I O Error " + e);
		} catch(IOException e) {
			System.err.println("I O Error " + e);
			return;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// CANTHAPPEN
				}
			}
		}
		System.out.println("Loaded " + v.size() + " objects");
	}

	public void save(JBModel model) {
		ObjectOutputStream os = null;
		List<GObj> v = model.getContents();
		try {
			os = new ObjectOutputStream(
				new FileOutputStream(DEFAULT_FILE));
			os.writeObject(v);
			os.flush();
		} catch(IOException e) {
			System.out.println("I O Error " + e);
			return;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// CANTHAPPEN
				}
			}
		}
		System.out.println("Saved " + v.size() + " objects");
	}
}
