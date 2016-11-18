/**
 * 
 */
package jabagator.io;

import jabagator.model.JBModel;

/**
 * PLACEHOLDER for loading and saving SVG
 * @author Ian Darwin
 */
public class LoadSaveSVG implements LoadSave {
	/** The default filename. */
	protected final static String DEFAULT_FILE =  "jabagator.svg";
	
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

	/* (non-Javadoc)
	 * @see jabagator.io.LoadSave#load(jabagator.model.JBModel)
	 */
	@Override
	public void load(JBModel model) {
		throw new RuntimeException("SVG load not written yet, so sorry!");
	}

	/* (non-Javadoc)
	 * @see jabagator.io.LoadSave#save(jabagator.model.JBModel)
	 */
	@Override
	public void save(JBModel model) {
		throw new RuntimeException("SVG save not written yet, so sorry!");
	}

}
