package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
	/**
	 * Constructor
	 */
	private MusicGUIDriver() {
		super();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIgnoreRepaint(false);
		add(new AllTunesPanel(), BorderLayout.WEST);
		add(new AllRecordingsPanel(), BorderLayout.CENTER);
		add(new AllBooksPanel(), BorderLayout.EAST);
		setPreferredSize(new Dimension(640,480));
		pack();
	}
}
