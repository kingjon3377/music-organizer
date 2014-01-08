package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;

import org.eclipse.jdt.annotation.Nullable;

import utils.ListenerButton;

/**
 * A panel to create and edit tunes.
 *
 * @author Jonathan Lovelace
 */
public final class TunePanel extends JPanel implements ActionListener {
	/**
	 * The "revert" action string.
	 */
	private static final String REVERT = "Revert";
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -1818025890818661076L;
	/**
	 * Text box for the tune name.
	 */
	private final transient JTextField nameBox = new JTextField();
	/**
	 * Text box for the composer name.
	 */
	private final transient JTextField composerBox = new JTextField();
	/**
	 * Text box for the time signature.
	 */
	private final transient JTextField timeBox = new JTextField();
	/**
	 * The tune this panel is editing.
	 */
	@Nullable private transient Tune tune;

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
	 * Explicit-value constructor.
	 *
	 * @param theTune
	 *            The tune we're editing
	 */
	public TunePanel(@Nullable final Tune theTune) {
		this();
		tune = theTune;
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}

	/**
	 * Handle button presses.
	 *
	 * @param event
	 *            The event we're handling
	 */
	@Override
	public void actionPerformed(@Nullable final ActionEvent event) {
		if (event == null) {
			return;
		} else if ("Apply".equals(event.getActionCommand())) {
			apply();
		} else if (REVERT.equals(event.getActionCommand())) {
			final Tune lTune = tune;
			if (lTune == null) {
				nameBox.setText("");
				composerBox.setText("");
				timeBox.setText("");
			} else {
				nameBox.setText(lTune.getName());
				composerBox.setText(lTune.getComposer());
				timeBox.setText(lTune.getTimeSignature());
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
		final Tune lTune = tune;
		final String name = nameBox.getText();
		final String composer = composerBox.getText();
		final String time = timeBox.getText();
		if (lTune == null) {
			final Tune nTune = new Tune();
			nTune.setName(name == null ? "" : name);
			nTune.setComposer(composer == null ? "" : composer);
			nTune.setTimeSignature(time == null ? "" : time);
			tune = nTune;
			firePropertyChange("tune", null, nTune);
		} else {
			lTune.setName(name == null ? "" : name);
			lTune.setComposer(composer == null ? "" : composer);
			lTune.setTimeSignature(time == null ? "" : time);
			firePropertyChange("tune", tune, tune);
		}
	}
}
