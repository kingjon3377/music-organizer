package view.collections;

import java.awt.BorderLayout;
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
	private final transient JList<Tune> tuneList = new JList<>(AllTunes.ALL_TUNES);

	/**
	 * Constructor.
	 */
	public AllTunesPanel() {
		super(new BorderLayout());
		add(new JLabel("Tunes"), BorderLayout.WEST);
		add(tuneList, BorderLayout.CENTER);
		final JPanel panel = new JPanel();
		panel.add(new ListenerButton("Add Tune", this));
		panel.add(new ListenerButton("Edit Tune", this));
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
		if ("Add Tune".equals(event.getActionCommand())) {
			new EditWindow("Add tune", new TunePanel(), this).setVisible(true);
		} else if ("Edit Tune".equals(event.getActionCommand())) {
			new EditWindow("Edit tune",
					new TunePanel(tuneList.getSelectedValue()), this)
					.setVisible(true);
		}
	}

	/**
	 * Ensure new tunes get added to the AllTunes list.
	 * @param evt the event we're handling
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("tune".equals(evt.getPropertyName()) && evt.getOldValue() == null) {
			AllTunes.ALL_TUNES.add((Tune) evt.getNewValue());
		}
	}
}
