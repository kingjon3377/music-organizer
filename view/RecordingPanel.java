package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.recording.Recording;
import model.recording.RecordingEntry;
import utils.ListenerButton;
import alm.ArrayListModel;

/**
 * A panel to add or edit a CD
 * 
 * @author Jonathan Lovelace
 */
public class RecordingPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -4055552130521585980L;
	/**
	 * The recording we're dealing with.
	 */
	private Recording recording;
	/**
	 * A text box for the recording's title.
	 */
	private final JTextField titleField = new JTextField();
	/**
	 * The intermediate model of the tunes, used to back the list
	 */
	private final ArrayListModel<RecordingEntry> tunes = new ArrayListModel<RecordingEntry>();
	/**
	 * The list of tunes.
	 */
	private final JList tunesList = new JList(tunes);

	/**
	 * Constructor.
	 * 
	 * @param record
	 *            The recording this window is editing
	 */
	public RecordingPanel(final Recording record) {
		this();
		recording = record;
	}

	/**
	 * Constructor.
	 */
	public RecordingPanel() {
		super(new GridLayout(0, 2));
		add(new JLabel("Recording Title"));
		add(titleField);
		add(new JLabel("Tune entries"));
		add(tunesList);
		tunesList.setEnabled(true);
		add(new ListenerButton("Add tune entry", this));
		add(new ListenerButton("Remove entry", this));
		add(new ListenerButton("Edit entry", this));
		add(new JLabel());
		add(new ListenerButton("Apply", this));
		add(new ListenerButton("Revert", this));
	}

	/**
	 * Handle button presses
	 * 
	 * @param actEvent
	 *            The button-press event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent actEvent) {
		if ("Revert".equals(actEvent.getActionCommand())) {
			if (!tunes.isEmpty()) {
				tunes.clear();
			}
			if (recording == null) {
				titleField.setText("");
			} else {
				titleField.setText(recording.getTitle());
				tunes.addAll(recording.getEntries());
			}
		} else if ("Apply".equals(actEvent.getActionCommand())) {
			apply();
		} else if ("Edit entry".equals(actEvent.getActionCommand())) {
			new EditWindow("Edit tune entry", new RecordingEntryPanel(tunes.get(tunesList
					.getSelectedIndex())), this).setVisible(true);
		} else if ("Remove entry".equals(actEvent.getActionCommand())) {
			tunes.remove(tunesList.getSelectedIndex());
		} else if ("Add tune entry".equals(actEvent.getActionCommand())) {
			new EditWindow("Add existing tune", new RecordingEntryPanel(), this)
					.setVisible(true);
		}
	}

	/**
	 * Called when the "apply" button is pressed. Applies changes the user has
	 * made to the event this panel is editing.
	 */
	private void apply() {
		if (recording == null) {
			recording = new Recording();
			recording.addAll(tunes);
			recording.setTitle(titleField.getText());
			firePropertyChange("recording", null, recording);
		} else {
			recording.setTitle(titleField.getText());
			for (RecordingEntry entry : recording.getEntries()) {
				if (!tunes.contains(entry)) {
					recording.removeEntry(entry);
				}
			}
			recording.addAll(tunes);
			firePropertyChange("recording", recording, recording);
		}
	}

	/**
	 * @return the Recording this panel is for editing
	 */
	public Recording getRecording() {
		return recording;
	}

	/**
	 * @param record
	 *            the Recording this panel will edit
	 */
	public void setRecording(final Recording record) {
		if (!recording.equals(record)) {
			recording = record;
			actionPerformed(new ActionEvent(this, 0, "Revert"));
		}
	}

	/**
	 * Handle a spawned panel's "apply" event.
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("entry".equals(evt.getPropertyName())) {
			if (evt.getOldValue() == null) {
				tunes.add((RecordingEntry) evt.getNewValue());
			}
		}
	}

}
