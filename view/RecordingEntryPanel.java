package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CollectionEntry;
import model.Tune;
import model.collections.AllTunes;
import model.recording.RecordingEntry;
import utils.ListenerButton;
/**
 * A panel to edit an entry in a recording
 * @author Jonathan Lovelace
 */
public final class RecordingEntryPanel extends JPanel implements ActionListener {
	private static final String REVERT = "Revert";
	/**
	 * Version UID for serialization 
	 */
	private static final long serialVersionUID = -936277429156304813L;
	/**
	 * The entry we're dealing with.
	 */
	private RecordingEntry entry;
	/**
	 * A list of the tunes that the entry might include.
	 */
	private final transient JList tuneList = new JList(AllTunes.ALL_TUNES);
	/**
	 * A text box for the track number.
	 */
	private final transient JTextField trackField = new JTextField();
	/**
	 * Constructor.
	 */
	public RecordingEntryPanel() {
		super(new GridLayout(0,2));
		add(new JLabel("Tune"));
		add(tuneList);
		add(new JLabel("Track number"));
		add(trackField);
		add(new ListenerButton("Apply", this));
		add(new ListenerButton(REVERT, this));
	}
	/**
	 * Constructor.
	 * @param theEntry The RecordingEntry this panel allows the user to edit
	 */
	public RecordingEntryPanel(final RecordingEntry theEntry) {
		this();
		entry = theEntry;
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}
	/**
	 * @return the RecordingEntry this panel is editing
	 */
	public CollectionEntry getEntry() {
		return entry;
	}
	/**
	 * @param newEntry the RecordingEntry this panel is to edit
	 */
	public void setEntry(final RecordingEntry newEntry) {
		if (!entry.equals(newEntry)) {
			entry = newEntry;
			actionPerformed(new ActionEvent(this, 0, REVERT));
		}
	}
	/**
	 * Handle a button press
	 * 
	 * @param event
	 *            The event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		if ("Apply".equals(event.getActionCommand())) {
			apply();
		} else if (REVERT.equals(event.getActionCommand())) {
			if (entry == null) {
				tuneList.setSelectedIndices(new int[0]);
				trackField.setText("");
			} else {
				tuneList.setSelectedValue(entry.getTune(), true);
				trackField.setText(Integer.toString(entry.getTrack()));
			}
		}
	}
	/**
	 * Called when the apply button is pressed.
	 */
	private void apply() {
		if (entry == null) {
			entry = new RecordingEntry();
			entry.setTune((Tune) tuneList.getSelectedValue());
			entry.setTrack(Integer.parseInt(trackField.getText()));
			firePropertyChange("entry", null, entry);
		} else {
			entry.setTune((Tune) tuneList.getSelectedValue());
			entry.setTrack(Integer.parseInt(trackField.getText()));
			firePropertyChange("entry", entry, entry);
		}
	}
}
