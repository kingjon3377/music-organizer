package view;

import javax.swing.JFrame;
/**
 * Main driver.
 * @author Jonathan Lovelace
 *
 */
public final class MusicGUIDriver extends JFrame {
	/**
	 * Version UID for serialization. 
	 */
	private static final long serialVersionUID = 5859543985029073215L;
	/**
	 * This is singleton.
	 */
	public static final MusicGUIDriver DRIVER = new MusicGUIDriver();
	/**
	 * Entry point.
	 * @param args command-line arguments
	 */
	public static void main(final String[] args) {
		DRIVER.setVisible(true);
	}
}
