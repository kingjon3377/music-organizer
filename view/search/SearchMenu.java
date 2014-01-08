package view.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.eclipse.jdt.annotation.Nullable;

import view.EditWindow;

/**
 * A menu from which to launch search dialogs.
 *
 * @author Jonathan Lovelace
 *
 */
public final class SearchMenu extends JMenu implements ActionListener,
		PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -7951281678448009460L;

	/**
	 * Constructor.
	 */
	public SearchMenu() {
		super("Search by ...");
		final JMenuItem tuneNameItem = new JMenuItem("Tune Name");
		tuneNameItem.addActionListener(this);
		add(tuneNameItem);
		final JMenuItem composerItem = new JMenuItem("Composer");
		composerItem.addActionListener(this);
		add(composerItem);
		final JMenuItem tuneItem = new JMenuItem("Tunes in Collection");
		tuneItem.addActionListener(this);
		add(tuneItem);
		final JMenuItem keyItem = new JMenuItem("Key of Tunes in Book");
		keyItem.addActionListener(this);
		add(keyItem);
		final JMenuItem timeItem = new JMenuItem("Time signature");
		timeItem.addActionListener(this);
		add(timeItem);
	}

	/**
	 * Handle menu item selections.
	 *
	 * @param event
	 *            the event we're handling
	 */
	@Override
	public void actionPerformed(@Nullable final ActionEvent event) {
		if (event == null) {
			return;
		} else if ("Tune Name".equals(event.getActionCommand())) {
			new EditWindow("Search by Tune Name", new TuneSearchPanel(), this)
					.setVisible(true);
		} else if ("Tunes in Collection".equals(event.getActionCommand())) {
			new EditWindow("Search by Tunes in Collection",
					new CollectionSearchPanel(), this).setVisible(true);
		} else if ("Key of Tunes in Book".equals(event.getActionCommand())) {
			new EditWindow("Search for Books containing Tunes in a Key",
					new KeySearchPanel(), this).setVisible(true);
		} else if ("Time signature".equals(event.getActionCommand())) {
			new EditWindow("Search by Time Signature", new TimeSearchPanel(),
					this).setVisible(true);
		} else if ("Composer".equals(event.getActionCommand())) {
			new EditWindow("Search by Composer", new ComposerSearchPanel(),
					this).setVisible(true);
		}
	}

	/**
	 * Handle events from spawned EditWindows.
	 *
	 * @param evt
	 *            the event to handle.
	 * @todo Implement?
	 */
	@Override
	public void propertyChange(@Nullable final PropertyChangeEvent evt) {
		// Do nothing at present
	}
}
