package view.search;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;
import model.collections.AllTunes;
import utils.ListenerButton;
import view.EditWindow;
import view.TunePanel;
import alm.ArrayListModel;

/**
 * A panel for searching by time signature.
 * 
 * @author Jonathan Lovelace
 * 
 */
public class TimeSearchPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -8824036391633868935L;
	/**
	 * A text box to enter the search term.
	 */
	private final transient JTextField searchField = new JTextField();
	/**
	 * A list-model to back the list of search results.
	 */
	private final transient ArrayListModel<Tune> results = new ArrayListModel<Tune>();
	/**
	 * The list of search results.
	 */
	private final transient JList list = new JList(results);

	/**
	 * Constructor.
	 */
	public TimeSearchPanel() {
		super(new BorderLayout());
		final JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.add(searchField);
		panel.add(new ListenerButton("Search", this));
		add(panel, BorderLayout.NORTH);
		add(list, BorderLayout.CENTER);
		final JPanel panelTwo = new JPanel(new GridLayout(0, 2));
		panelTwo.add(new ListenerButton("Edit Tune", this));
		panelTwo.add(new ListenerButton("Close", this));
		add(panelTwo, BorderLayout.SOUTH);
	}

	/**
	 * Handle button presses.
	 * 
	 * @param evt
	 *            the event to handle
	 */
	@Override
	public void actionPerformed(final ActionEvent evt) {
		if ("Search".equals(evt.getActionCommand())) {
			if (!results.isEmpty()) {
				results.clear();
			}
			for (Tune tune : AllTunes.ALL_TUNES) {
				if (tune.getTimeSignature().equals(searchField.getText())) {
					results.add(tune);
				}
			}
		} else if ("Edit Tune".equals(evt.getActionCommand())) {
			new EditWindow("Edit Tune", new TunePanel((Tune) list.getSelectedValue()),
					this).setVisible(true);
		} else if ("Close".equals(evt.getActionCommand())) {
			this.setVisible(false);
			for (ContainerListener listener : getContainerListeners()) {
				listener.componentRemoved(null);
			}
		}
	}

	/**
	 * Handle events from spawned EditWindows. TODO: Implement?
	 * 
	 * @param evt
	 *            the event to handle
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		// Do nothing for now
	}
}
