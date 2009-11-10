package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Tune;
import model.book.BookEntry;
import model.collections.AllTunes;
import utils.ListenerButton;

/**
 * A panel to edit an entry in a book
 * @author Jonathan Lovelace
 */
public class BookEntryPanel extends JPanel implements ActionListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 295779226843204587L;
	/**
	 * The BookEntry we're dealing with.
	 */
	private BookEntry entry;
	/**
	 * A list of tunes, of which one can be selected
	 */
	private final JList tuneList = new JList(AllTunes.ALL_TUNES);
	/**
	 * A text box for the page number
	 */
	private final JTextField pageField = new JTextField();
	/**
	 * Constructor.
	 */
	public BookEntryPanel() {
		super(new GridLayout(0, 2));
		add(new JLabel("Tune"));
		add(tuneList);
		add(new JLabel("Page number"));
		add(pageField);
		add(new ListenerButton("Apply", this));
		add(new ListenerButton("Revert", this));
	}
	/**
	 * Constructor.
	 * @param theEntry The BookEntry this panel allows the user to edit
	 */
	public BookEntryPanel(final BookEntry theEntry) {
		this();
		entry = theEntry;
	}
	/**
	 * @return the BookEntry this panel is editing
	 */
	public BookEntry getEntry() {
		return entry;
	}
	/**
	 * @param newEntry The BookEntry this panel is to edit
	 */
	public void setEntry(final BookEntry newEntry) {
		if (!entry.equals(newEntry)) {
			entry = newEntry;
			actionPerformed(new ActionEvent(this, 0, "Revert"));
		}
	}
	/**
	 * Handle a button press
	 * 
	 * @param event
	 *            The event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		if ("Apply".equals(event.getActionCommand())) {
			apply();
		} else if ("Revert".equals(event.getActionCommand())) {
			if (entry != null) {
				tuneList.setSelectedValue(entry.getTune(), true);
				pageField.setText(Integer.toString(entry.getPage()));
			} else {
				tuneList.setSelectedIndices(new int[0]);
				pageField.setText("");
			}
		}
	}
	/**
	 * Called when the apply button is pressed.
	 */
	private void apply() {
		if (entry == null) {
			entry = new BookEntry((Tune)tuneList.getSelectedValue());
			firePropertyChange("entry",null,entry);
		} else {
			entry.setTune((Tune)tuneList.getSelectedValue());
			entry.setPage(Integer.parseInt(pageField.getText()));
			firePropertyChange("entry",entry,entry);
		}
	}
}