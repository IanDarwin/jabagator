package jabagator.io;

import jabagator.JBModel;

public interface LoadSave {

	public abstract void load(JBModel model);

	public abstract void save(JBModel model);

}