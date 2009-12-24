package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;
import utils.ListenerButton;

/**
 * A panel to create and edit tunes.
 * 
 * @author Jonathan Lovelace
 */
public final class TunePanel extends JPanel implements ActionListener {
	private static final String REVERT = "Revert";
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -1818025890818661076L;
	/**
	 * Text box for the tune name
	 */
	private final transient JTextField nameBox = new JTextField();
	/**
	 * Text box for the composer name
	 */
	private final transient JTextField composerBox = new JTextField();
	/**
	 * Text box for the time signature
	 */
	private final transient JTextField timeBox = new JTextField();
	/**
	 * The tune this panel is editing.
	 */
	private transient Tune tune;

	/**
	 * Constructor.
	 */
	public TunePanel() {
		super(new GridLayout(0, 2));
		add(new JLabel("Tune name"));
		add(nameBox);
		add(new JLabel("Composer"));
		add(composerBox);
		add(new JLabel("Time signature"));
		add(timeBox);
		add(new ListenerButton("Apply", this));
		add(new ListenerButton(REVERT, this));
		add(new ListenerButton("Close", this));
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}

	/**
	 * Explicit-value constructor
	 * 
	 * @param theTune
	 *            The tune we're editing
	 */
	public TunePanel(final Tune theTune) {
		this();
		tune = theTune;
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}

	/**
	 * Handle button presses
	 * 
	 * @param event
	 *            The event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		if ("Apply".equals(event.getActionCommand())) {
			apply();
		} else if (REVERT.equals(event.getActionCommand())) {
			if (tune == null) {
				nameBox.setText("");
				composerBox.setText("");
				timeBox.setText("");
			} else {
				nameBox.setText(tune.getName());
				composerBox.setText(tune.getComposer());
				timeBox.setText(tune.getTimeSignature());
			}
		} else if ("Close".equals(event.getActionCommand())) {
			this.setVisible(false);
			for (ContainerListener listener : getContainerListeners()) {
				listener.componentRemoved(null);
			}
		}
	}

	/**
	 * Called when the apply button is pressed.
	 */
	private void apply() {
		if (tune == null) {
			tune = new Tune();
			tune.setName(nameBox.getText());
			tune.setComposer(composerBox.getText());
			tune.setTimeSignature(timeBox.getText());
			firePropertyChange("tune",null,tune);
		} else {
			tune.setName(nameBox.getText());
			tune.setComposer(composerBox.getText());
			tune.setTimeSignature(timeBox.getText());
			firePropertyChange("tune",tune,tune);
		}
	}
}
