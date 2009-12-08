package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * A menu from which to launch search dialogs.
 * 
 * @author Jonathan Lovelace
 * 
 */
public final class SearchMenu extends JMenu implements ActionListener, PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -7951281678448009460L;

	/**
	 * Constructor.
	 */
	public SearchMenu() {
		super("Search by ...");
		JMenuItem tuneNameItem = new JMenuItem("Tune Name");
		tuneNameItem.addActionListener(this);
		add(tuneNameItem);
	}

	/**
	 * Handle menu item selections.
	 * 
	 * @param event
	 *            the event we're handling
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if ("Tune Name".equals(event.getActionCommand())) {
			new EditWindow("Search by Tune Name", new TuneSearchPanel(), this)
					.setVisible(true);
		}
	}
	/**
	 * Handle events from spawned EditWindows.
	 * @param evt the event to handle.
	 * @todo Implement?
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Do nothing at present
	}
}