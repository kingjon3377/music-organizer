package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * A panel to create and edit tunes.
 * 
 * @author Jonathan Lovelace
 */
public class TunePanel extends JPanel {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -1818025890818661076L;

	/**
	 * Constructor.
	 */
	public TunePanel() {
		super(new GridLayout(0, 2));
	}
}
