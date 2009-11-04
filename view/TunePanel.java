package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;

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
	 * Text box for the tune name
	 */
	private final JTextField nameBox = new JTextField();
	/**
	 * The tune this panel is editing.
	 */
	private final Tune tune;
	/**
	 * Constructor.
	 */
	public TunePanel() {
		this(new Tune());
	}
	/**
	 * Explicit-value constructor
	 * @param theTune The tune we're editing
	 */
	public TunePanel(final Tune theTune) {
		super(new GridLayout(0, 2));
		add(new JLabel("Tune name"));
		add(nameBox);
		tune = theTune;
	}
}
