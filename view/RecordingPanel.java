package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CollectionEntry;
import model.recording.Recording;
import model.recording.RecordingEntry;

import org.eclipse.jdt.annotation.Nullable;

import utils.ListenerButton;
import alm.ArrayListModel;

/**
 * A panel to add or edit a CD.
 *
 * @author Jonathan Lovelace
 */
public final class RecordingPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * The "revert" action string.
	 */
	private static final String REVERT = "Revert";
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -4055552130521585980L;
	/**
	 * Logger.
	 */
	@SuppressWarnings("null")
	private static final Logger LOGGER = Logger.getLogger(RecordingPanel.class.getName());
	/**
	 * The recording we're dealing with.
	 */
	@Nullable private Recording recording;
	/**
	 * A text box for the recording's title.
	 */
	private final transient JTextField titleField = new JTextField();
	/**
	 * The intermediate model of the tunes, used to back the list.
	 */
	private final transient ArrayListModel<RecordingEntry> tunes = new ArrayListModel<>();
	/**
	 * The list of tunes.
	 */
	private final transient JList<RecordingEntry> tunesList = new JList<>(tunes);

	/**
	 * Constructor.
	 *
	 * @param record
	 *            The recording this window is editing
	 */
	public RecordingPanel(@Nullable final Recording record) {
		this();
		recording = record;
		actionPerformed(new ActionEvent(this, 0, REVERT));
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
		add(new ListenerButton(REVERT, this));
	}

	/**
	 * Handle button presses.
	 *
	 * @param actEvent
	 *            The button-press event we're handling
	 */
	@Override
	public void actionPerformed(@Nullable final ActionEvent actEvent) {
		if (actEvent == null) {
			return;
		} else if (REVERT.equals(actEvent.getActionCommand())) {
			if (!tunes.isEmpty()) {
				tunes.clear();
			}
			final Recording lRecord = recording;
			if (lRecord == null) {
				titleField.setText("");
			} else {
				titleField.setText(lRecord.getTitle());
				try {
					tunes.addAll(lRecord.getEntries());
				} catch (IndexOutOfBoundsException except) {
					LOGGER.log(Level.INFO,
							"Expected IndexOutOfBoundsException", except);
				}
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
		final Recording lRecord = recording;
		final String title = titleField.getText();
		if (lRecord == null) {
			final Recording nRecord = new Recording();
			nRecord.addAll(tunes);
			nRecord.setTitle(title == null ? "" : title);
			recording = nRecord;
			firePropertyChange("recording", null, nRecord);
		} else {
			lRecord.setTitle(title == null ? "" : title);
			for (CollectionEntry entry : lRecord.getEntries()) {
				if (entry != null && !tunes.contains(entry)) {
					lRecord.removeEntry(entry);
				}
			}
			lRecord.addAll(tunes);
			firePropertyChange("recording", recording, recording);
		}
	}

	/**
	 * @return the Recording this panel is for editing
	 */
	@Nullable public Recording getRecording() {
		return recording;
	}

	/**
	 * @param record
	 *            the Recording this panel will edit
	 */
	public void setRecording(@Nullable final Recording record) {
		final Recording lRecord = recording;
		if (lRecord == null || !lRecord.equals(record)) {
			recording = record;
			actionPerformed(new ActionEvent(this, 0, REVERT));
		}
	}

	/**
	 * Handle a spawned panel's "apply" event.
	 * @param evt An event from a spawned panel.
	 */
	@Override
	public void propertyChange(@Nullable final PropertyChangeEvent evt) {
		if (evt == null) {
			return;
		}
		final Object newValue = evt.getNewValue();
		if ("entry".equals(evt.getPropertyName()) && evt.getOldValue() == null
				&& newValue instanceof RecordingEntry) {
			tunes.add((RecordingEntry) newValue);
		}
	}

}
