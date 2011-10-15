package jabagator.io;

import jabagator.model.JBModel;

public interface LoadSave {

	/** Convert FROM some external format TO our internal format, the JBModel */
	public abstract void load(JBModel model);

	/** Convert TO some external format FROM our internal format, the JBModel */
	public abstract void save(JBModel model);

}