package jabagator.model;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/** Represents a text bullet */
public class GText extends GObj {
	private static final long serialVersionUID = -7470013014379005359L;
	String text;
	Font font;
	int tWidth, tHeight;

	public String describe() { return "text"; }

	public void setText(String s) {
		font = getFont();
		text = s;
		//FontMetrics fm = getFontMetrics(font);
		Dimension d;
		//d = new Dimension(fm.stringWidth(text), fm.getAscent());
		d = new Dimension(200, 22); // arghh
		tWidth = d.width; tHeight = (d.height);
		System.out.println("GText: size = " + d);
		setSize(tWidth, tHeight);
		ctlPoints[nCtlPoints++]=new Point(CP_SIZE/2, tHeight-CP_SIZE/2);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
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
