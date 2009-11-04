package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import utils.ListenerButton;
/**
 * Main driver.
 * @author Jonathan Lovelace
 *
 */
public final class MusicGUIDriver extends JFrame implements ActionListener {
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
		add(new ListenerButton("Add Tune", this));
		setPreferredSize(new Dimension(640,480));
		pack();
	}
	/**
	 * Handle button presses
	 * @param event The event to handle
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if ("Add Tune".equals(event.getActionCommand())) {
			new TunePanel().setVisible(true);
		}
	}
}
