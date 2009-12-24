package utils;

import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * A JButton that takes its ActionListener as a constructor parameter.
 * 
 * @author kingjon
 */
public class ListenerButton extends JButton {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 7050897114145426560L;

	/**
	 * @param string
	 *            The button string
	 * @param listener
	 *            The listener to add
	 */
	public ListenerButton(final String string, final ActionListener listener) {
		super(string);
		addActionListener(listener);
	}
}
