package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;
import model.collections.AllTunes;
import model.recording.RecordingEntry;

import org.eclipse.jdt.annotation.Nullable;

import utils.ListenerButton;

/**
 * A panel to edit an entry in a recording.
 *
 * @author Jonathan Lovelace
 */
public final class RecordingEntryPanel extends JPanel implements ActionListener {
	/**
	 * The "revert" action string.
	 */
	private static final String REVERT = "Revert";
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -936277429156304813L;
	/**
	 * The entry we're dealing with.
	 */
	@Nullable private RecordingEntry entry;
	/**
	 * A list of the tunes that the entry might include.
	 */
	private final transient JList<Tune> tuneList = new JList<>(AllTunes.ALL_TUNES);
	/**
	 * A text box for the track number.
	 */
	private final transient JTextField trackField = new JTextField();

	/**
	 * Constructor.
	 */
	public RecordingEntryPanel() {
		super(new GridLayout(0, 2));
		add(new JLabel("Tune"));
		add(tuneList);
		add(new JLabel("Track number"));
		add(trackField);
		add(new ListenerButton("Apply", this));
		add(new ListenerButton(REVERT, this));
	}

	/**
	 * Constructor.
	 *
	 * @param theEntry
	 *            The RecordingEntry this panel allows the user to edit
	 */
	public RecordingEntryPanel(@Nullable final RecordingEntry theEntry) {
		this();
		entry = theEntry;
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}

	/**
	 * @return the RecordingEntry this panel is editing
	 */
	@Nullable public RecordingEntry getEntry() {
		return entry;
	}

	/**
	 * @param newEntry
	 *            the RecordingEntry this panel is to edit
	 */
	public void setEntry(@Nullable final RecordingEntry newEntry) {
		final RecordingEntry lEntry = entry;
		if (lEntry == null || !lEntry.equals(newEntry)) {
			entry = newEntry;
			actionPerformed(new ActionEvent(this, 0, REVERT));
		}
	}

	/**
	 * Handle a button press.
	 *
	 * @param event
	 *            The event we're handling
	 */
	@Override
	public void actionPerformed(@Nullable final ActionEvent event) {
		if (event ==  null) {
			return;
		} else if ("Apply".equals(event.getActionCommand())) {
			apply();
		} else if (REVERT.equals(event.getActionCommand())) {
			final RecordingEntry lEntry = entry;
			if (lEntry == null) {
				tuneList.setSelectedIndices(new int[0]);
				trackField.setText("");
			} else {
				tuneList.setSelectedValue(lEntry.getTune(), true);
				trackField.setText(Integer.toString(lEntry.getTrack()));
			}
		}
	}

	/**
	 * Called when the apply button is pressed.
	 */
	private void apply() {
		final RecordingEntry lEntry = entry;
		if (lEntry == null) {
			final RecordingEntry nEntry = new RecordingEntry();
			nEntry.setTune(tuneList.getSelectedValue());
			nEntry.setTrack(Integer.parseInt(trackField.getText()));
			entry = nEntry;
			firePropertyChange("entry", null, nEntry);
		} else {
			lEntry.setTune(tuneList.getSelectedValue());
			lEntry.setTrack(Integer.parseInt(trackField.getText()));
			firePropertyChange("entry", entry, entry);
		}
	}
}
