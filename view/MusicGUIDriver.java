package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import view.collections.AllBooksPanel;
import view.collections.AllRecordingsPanel;
import view.collections.AllTunesPanel;
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIgnoreRepaint(false);
		add(new MusicMenu(), BorderLayout.NORTH);
		final JTabbedPane panel = new JTabbedPane();
		panel.addTab("Tunes",new AllTunesPanel());
		panel.addTab("Recordings", new AllRecordingsPanel());
		panel.addTab("Books",new AllBooksPanel());
		add(panel);
		setPreferredSize(new Dimension(640,480));
		pack();
	}
}
