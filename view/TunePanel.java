package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;
import model.collections.AllTunes;
import utils.ListenerButton;

/**
 * A panel to create and edit tunes.
 * 
 * @author Jonathan Lovelace
 */
public class TunePanel extends JPanel implements ActionListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -1818025890818661076L;
	/**
	 * Text box for the tune name
	 */
	private final JTextField nameBox = new JTextField();
	/**
	 * Text box for the composer name
	 */
	private final JTextField composerBox = new JTextField();
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
		add(new JLabel("Composer"));
		add(composerBox);
		add(new ListenerButton("Apply",this));
		add(new ListenerButton("Revert", this));
		add(new ListenerButton("Close", this));
		actionPerformed(new ActionEvent(this, 0, "Revert"));
		tune = theTune;
	}
	/**
	 * Handle button presses
	 * @param event The event we're handling
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if ("Apply".equals(event.getActionCommand())) {
			tune.setName(nameBox.getText());
			tune.setComposer(composerBox.getText());
			AllTunes.ALL_TUNES.add(tune);
		} else if ("Revert".equals(event.getActionCommand())) {
			nameBox.setText(tune.getName());
			composerBox.setText(tune.getComposer());
		} else if ("Close".equals(event.getActionCommand())) {
			this.setVisible(false);
		}
	}
}
