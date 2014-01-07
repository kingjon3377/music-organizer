package view.collections;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.collections.AllRecordings;
import model.recording.Recording;
import utils.ListenerButton;
import view.EditWindow;
import view.RecordingPanel;

/**
 * A panel to create and edit recordings.
 *
 * @author Jonathan Lovelace
 */
public class AllRecordingsPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -785663569301003616L;
	/**
	 * A list of recordings, of which one can be selected to be edited.
	 */
	private final transient JList<Recording> recordList = new JList<>(AllRecordings.ALL_RECORDINGS);

	/**
	 * Constructor.
	 */
	public AllRecordingsPanel() {
		super(new BorderLayout());
		add(new JLabel("Recordings"), BorderLayout.WEST);
		add(recordList, BorderLayout.CENTER);
		final JPanel panel = new JPanel();
		panel.add(new ListenerButton("Add Recording", this));
		panel.add(new ListenerButton("Edit Recording", this));
		panel.add(new ListenerButton("Remove Recording", this));
		add(panel, BorderLayout.SOUTH);
	}

	/**
	 * Handle button presses.
	 *
	 * @param event
	 *            the event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		if ("Add Recording".equals(event.getActionCommand())) {
			new EditWindow("Add recording", new RecordingPanel(), this).setVisible(true);
		} else if ("Edit Recording".equals(event.getActionCommand())) {
			new EditWindow("Edit recording", new RecordingPanel(recordList
					.getSelectedValue()), this).setVisible(true);
		} else if ("Remove Book".equals(event.getActionCommand())) {
			AllRecordings.ALL_RECORDINGS.remove(recordList.getSelectedIndex());
		}
	}

	/**
	 * Ensure new recordings get added to the AllRecordings list.
	 *
	 * @param evt
	 *            the event we're handling
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final Object newValue = evt.getNewValue();
		if ("recording".equals(evt.getPropertyName())
				&& evt.getOldValue() == null && newValue instanceof Recording) {
			AllRecordings.ALL_RECORDINGS.add((Recording) newValue);
		}
	}
}
