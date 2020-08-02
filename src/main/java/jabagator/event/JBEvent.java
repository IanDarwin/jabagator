package jabagator.event;

import jabagator.model.GObject;

public class JBEvent {
	JBAction action;
	GObject object;
	
	public JBEvent(JBAction action, GObject object) {
		super();
		this.action = action;
		this.object = object;
	}
	
	public String toString() {
		return "JBEvent[" + action + " for " + object + "]";
	}
}
