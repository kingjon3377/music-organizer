package view;

import java.awt.Dimension;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A window to hold any of the edit panes.
 * 
 * @author Jonathan Lovelace
 */
public class EditWindow extends JFrame implements ContainerListener {
	/**
	 * Version UID for serialization
	 */
	private static final long serialVersionUID = 3045048240354105063L;

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The title of the window
	 * @param panel
	 *            The panel the window is wrapping
	 * @param list
	 *            Something to be notified when the object the panel is editing
	 *            changes.
	 */
	public EditWindow(final String title, final JPanel panel,
			final PropertyChangeListener list) {
		super(title);
		add(panel);
		panel.addPropertyChangeListener(list);
		panel.addContainerListener(this);
		setPreferredSize(new Dimension(640,480));
		pack();
	}
	/**
	 * Do nothing
	 */
	@Override
	public void componentAdded(final ContainerEvent event) {
		// Do nothing
	}
	/**
	 * Handle the fake event intended to close this window.
	 */
	@Override
	public void componentRemoved(final ContainerEvent event) {
		if (event == null) {
			setVisible(false);
			setEnabled(false);
			dispose();
		}
	}
}
