package view;

import java.awt.GridLayout;
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
	 * A list of recordings, of which one can be selected to be edited
	 */
	private final JList recordList = new JList(AllRecordings.ALL_RECORDINGS);

	/**
	 * Constructor
	 */
	public AllRecordingsPanel() {
		super(new GridLayout(0, 2));
		add(new JLabel("Recordings"));
		add(recordList);
		add(new ListenerButton("Add Recording", this));
		add(new ListenerButton("Edit Recording", this));
		add(new JLabel(""));
		add(new ListenerButton("Remove Recording", this));
	}

	/**
	 * Handle button presses.
	 * 
	 * @param event
	 *            the event we're handling
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if ("Add Recording".equals(event.getActionCommand())) {
			new EditWindow("Add recording", new RecordingPanel(), this).setVisible(true);
		} else if ("Edit Recording".equals(event.getActionCommand())) {
			new EditWindow("Edit recording", new RecordingPanel((Recording) recordList
					.getSelectedValue()), this).setVisible(true);
		} else if ("Remove Book".equals(event.getActionCommand())) {
			AllRecordings.ALL_RECORDINGS.remove(recordList.getSelectedIndex());
		}
	}

	/**
	 * Ensure new recordings get added to the AllRecordings list
	 * 
	 * @param evt
	 *            the event we're handling
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("recording".equals(evt.getPropertyName())) {
			if (evt.getOldValue() == null) {
				AllRecordings.ALL_RECORDINGS.add((Recording) evt.getNewValue());
			}
		}
	}
}
