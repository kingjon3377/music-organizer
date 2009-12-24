package view.search;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.regex.Pattern;

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
 * A panel to search by composer.
 * 
 * @author Jonathan Lovelace
 * 
 */
public class ComposerSearchPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1353620881465119541L;
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
	public ComposerSearchPanel() {
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
				// the case-sensitive equivalent of
				// tune.getName().contains(searchField.getText()) -- taken from
				// http://stackoverflow.com/questions/86780/
				if (Pattern.compile(Pattern.quote(searchField.getText()),
						Pattern.CASE_INSENSITIVE).matcher(tune.getComposer()).find()) {
					results.add(tune);
				}
			}
		} else if ("Edit Tune".equals(evt.getActionCommand())) {
			new EditWindow("Edit tune", new TunePanel((Tune) list.getSelectedValue()),
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
	 * @param evt the event to handle
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		// Do nothing for now
	}
}
