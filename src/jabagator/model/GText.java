import java.awt.Graphics;

public class GText extends GObj {
	String text;
	void setText(String s) {
		text = s;
	}
	void draw(Graphics g) {
		System.out.println("Paint text " + text " + at + x + "," + y);
		g.drawString(text, x, y);
	}
}
