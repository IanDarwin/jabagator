package jabagator.model;

import java.awt.*;
import javax.swing.*;

/** Represents a text bullet */
public class GText extends GObj {
	String text;
	Font font;
	int tWidth, tHeight;
	/** 0, 1, 2, 3 */
	int indent;

	public String describe() { return "text"; }

	public void setText(String s) {
		font = getFont();
		text = s;
		FontMetrics fm = getFontMetrics(font);
		Dimension d = new Dimension(fm.stringWidth(text), fm.getAscent());
		tWidth = d.width; tHeight = (d.height);
		System.out.println("GText: size = " + d);
		setSize(tWidth, tHeight);
		ctlPoints[nCtlPoints++]=new Point(CP_SIZE/2, tHeight-CP_SIZE/2);
	}

	final int MAX_INDENT = 3;
	public void setIndent(int indent) {
		if (indent >= 0 && indent <= MAX_INDENT)
			this.indent = indent;
		else
			throw new IllegalArgumentException("Indent must be in 0.." + MAX_INDENT);
	}

	public void indent() {
		if (indent < MAX_INDENT)
			indent++;
	}
	public void undent() {
		if (indent > 0)
			--indent;
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
