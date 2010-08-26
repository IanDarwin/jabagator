package jabagator.model;

import java.awt.*;
import javax.swing.*;

public class GText extends GObj {
	String text;
	Font font;
	int tWidth, tHeight;

	public String describe() { return "text"; }

	void setText(String s) {
		font = getFont();
		text = s;
		FontMetrics fm = getFontMetrics(font);
		Dimension d = new Dimension(fm.stringWidth(text), fm.getAscent());
		tWidth = d.width; tHeight = (d.height);
		System.out.println("GText: size = " + d);
		setSize(tWidth, tHeight);
		ctlPoints[nCtlPoints++]=new Point(CP_SIZE/2, tHeight-CP_SIZE/2);
	}

	/** draw -- common code for paint & print -- draw the object. */
	protected void draw(Graphics g) {
		//g.setFont(font);
		g.drawString(text, 1, getSize().height-1);
		g.drawLine(0, tHeight-CP_SIZE/2, tWidth, tHeight-CP_SIZE/2);
	}

	public String toString() {
		Point d = getLocation();
		return getClass()+"[" + text + "@"+d.x+","+d.y+"]";
	}
}
