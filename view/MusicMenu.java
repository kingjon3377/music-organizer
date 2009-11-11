package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A menu bar for the Save, Load, and Exit commands.
 * @author Jonathan Lovelace
 */
public class MusicMenu extends JMenuBar implements ActionListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -2123155356163135242L;
	/**
	 * Constructor.
	 */
	public MusicMenu() {
		super();
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		add(saveItem);
		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(this);
		add(loadItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		add(exitItem);
	}
	/**
	 * Handle menu item selections.
	 * @param event the event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		if ("Exit".equals(event.getActionCommand())) {
			MusicGUIDriver.DRIVER.dispose();
		} else if ("Save".equals(event.getActionCommand())) {
			// save();
		} else if ("Load".equals(event.getActionCommand())) {
			// load();
		}
	}
}
