import java.awt.*;

public class GText extends GObj {
	String text;
	Font font;
	void setText(String s) {
		font = view.getFont();
		text = s;
		FontMetrics fm = view.getFontMetrics(font);
		Dimension d = new Dimension(fm.stringWidth(text), fm.getAscent());
		w = d.width; h = -(d.height);
	}
	void draw(Graphics g) {
		doColor(g);
		// System.out.println("Paint text " + text + " at " + x + "," + y);
		g.setFont(font);
		g.drawString(text, x, y);
	}
}
