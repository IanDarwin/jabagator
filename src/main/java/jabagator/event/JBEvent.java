package jabagator.event;

import jabagator.model.GObj;

public class JBEvent {
	JBAction action;
	GObj object;
	
	public JBEvent(JBAction action, GObj object) {
		super();
		this.action = action;
		this.object = object;
	}
	
	public String toString() {
		return "JBEvent[" + action + " for " + object + "]";
	}
}
