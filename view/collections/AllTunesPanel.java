package view.collections;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Tune;
import model.collections.AllTunes;
import utils.ListenerButton;
import view.EditWindow;
import view.TunePanel;

/**
 * A panel to allow the user to select a tune to edit.
 * 
 * @author Jonathan Lovelace
 */
public class AllTunesPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 3775829302043384549L;
	/**
	 * A list of tunes, of which one can be selected.
	 */
	private final JList tuneList = new JList(AllTunes.ALL_TUNES);

	/**
	 * Constructor.
	 */
	public AllTunesPanel() {
		super(new GridLayout(0, 2));
		add(new JLabel("Tunes"));
		add(tuneList);
		add(new ListenerButton("Add Tune", this));
		add(new ListenerButton("Edit Tune", this));
	}

	/**
	 * Handle button presses.
	 * 
	 * @param event
	 *            the event we're handling
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if ("Add Tune".equals(event.getActionCommand())) {
			new EditWindow("Add tune", new TunePanel(), this).setVisible(true);
		} else if ("Edit Tune".equals(event.getActionCommand())) {
			new EditWindow("Edit tune",
					new TunePanel((Tune) tuneList.getSelectedValue()), this)
					.setVisible(true);
		}
	}

	/**
	 * Ensure new tunes get added to the AllTunes list.
	 * @param evt the event we're handling
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("tune".equals(evt.getPropertyName())) {
			if (evt.getOldValue() == null) {
				AllTunes.ALL_TUNES.add((Tune) evt.getNewValue());
			}
		}
	}
}
