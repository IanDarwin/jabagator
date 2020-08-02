package jabagator.model;

import java.io.Serializable;

/** Class to do with Measurement */
public class M implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The number of units in a millimetre */
	public final static int SCALE=100;

	/** Convert millimetres to units */
	public static int mm2u(int mm) {
		return mm;
	}

	/** Convert units to millimetres */
	public static int u2mm(int units) {
		return units;
	}

	/** Convert units to pixels */
	public static int u2p(int units) {
		return units;
	}

	/** Convert pixels to units */
	public static int p2u(int pix) {
		return pix;
	}
}
