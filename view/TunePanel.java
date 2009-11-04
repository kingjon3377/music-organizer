package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;
import model.collections.AllTunes;
import utils.ListenerButton;

/**
 * A panel to create and edit tunes.
 * 
 * @author Jonathan Lovelace
 */
public class TunePanel extends JFrame implements ActionListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -1818025890818661076L;
	/**
	 * Text box for the tune name
	 */
	private final JTextField nameBox = new JTextField();
	/**
	 * The tune this panel is editing.
	 */
	private final Tune tune;
	/**
	 * Constructor.
	 */
	public TunePanel() {
		this(new Tune());
	}
	/**
	 * Explicit-value constructor
	 * @param theTune The tune we're editing
	 */
	public TunePanel(final Tune theTune) {
		super("Edit Tune");
		final JPanel panel = new JPanel(new GridLayout(0, 2));
		setPreferredSize(new Dimension(100,100));
		panel.add(new JLabel("Tune name"));
		panel.add(nameBox);
		panel.add(new ListenerButton("Apply",this));
		panel.add(new ListenerButton("Revert", this));
		panel.add(new ListenerButton("Close", this));
		tune = theTune;
		add(panel);
		pack();
	}
	/**
	 * Handle button presses
	 * @param event The event we're handling
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if ("Apply".equals(event.getActionCommand())) {
			tune.setName(nameBox.getText());
			AllTunes.ALL_TUNES.add(tune);
		} else if ("Revert".equals(event.getActionCommand())) {
			nameBox.setText(tune.getName());
		} else if ("Close".equals(event.getActionCommand())) {
			this.setVisible(false);
		}
	}
}
