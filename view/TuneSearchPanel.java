package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;
import model.collections.AllTunes;
import utils.ListenerButton;
import alm.ArrayListModel;

/**
 * A panel to search for tunes by name
 * 
 * @author Jonathan Lovelace
 */
public class TuneSearchPanel extends JPanel implements ActionListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -6012429142002267887L;
	/**
	 * A text box to enter the search term
	 */
	private final JTextField searchField = new JTextField();
	/**
	 * A list-model to back the list of search results.
	 */
	private final ArrayListModel<Tune> results = new ArrayListModel<Tune>();
	/**
	 * The list of search results
	 */
	private final JList list = new JList(results);

	/**
	 * Constructor.
	 */
	public TuneSearchPanel() {
		super(new BorderLayout());
		final JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.add(searchField);
		panel.add(new ListenerButton("Search", this));
		add(panel, BorderLayout.NORTH);
		add(list, BorderLayout.CENTER);
	}

	/**
	 * Handle button presses
	 * 
	 * @param evt
	 *            the event to handle
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if ("Search".equals(evt.getActionCommand())) {
			results.clear();
			for (Tune tune : AllTunes.ALL_TUNES) {
				// the case-sensitive equivalent of
				// tune.getName().contains(searchField.getText()) -- taken from
				// http://stackoverflow.com/questions/86780/
				if (Pattern.compile(Pattern.quote(searchField.getText()),
						Pattern.CASE_INSENSITIVE).matcher(tune.getName()).find()) {
					results.add(tune);
				}
			}
		}
	}
}
