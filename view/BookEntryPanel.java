package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CollectionEntry;
import model.Tune;
import model.book.BookEntry;
import model.collections.AllTunes;
import utils.ListenerButton;

/**
 * A panel to edit an entry in a book.
 *
 * @author Jonathan Lovelace
 */
public final class BookEntryPanel extends JPanel implements ActionListener {
	/**
	 * The "revert" action string.
	 */
	private static final String REVERT = "Revert";
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 295779226843204587L;
	/**
	 * The BookEntry we're dealing with.
	 */
	private BookEntry entry;
	/**
	 * A list of tunes, of which one can be selected.
	 */
	private final transient JList<Tune> tuneList = new JList<Tune>(AllTunes.ALL_TUNES);
	/**
	 * A text box for the page number.
	 */
	private final transient JTextField pageField = new JTextField();
	/**
	 * A text box for the key.
	 */
	private final transient JTextField keyField = new JTextField();

	/**
	 * Constructor.
	 */
	public BookEntryPanel() {
		super(new GridLayout(0, 2));
		add(new JLabel("Tune"));
		add(tuneList);
		add(new JLabel("Page number"));
		add(pageField);
		add(new JLabel("Key"));
		add(keyField);
		keyField.setInputVerifier(new InputVerifier() {
			/**
			 * If a Key is this long, it's too long to be right.
			 */
			private static final int TOO_LONG = 3; // NOPMD
			@Override
			public boolean verify(final JComponent input) {
				return verify(((JTextField) input).getText());
			}

			private boolean verify(final String input) {
				return input.length() < TOO_LONG
						&& (input.length() < 1 || Character.isLetter(input.charAt(0)))
						&& (input.length() < 2 || input.charAt(1) == '#' || input
								.charAt(1) == 'b');
			}
		});
		add(new ListenerButton("Apply", this));
		add(new ListenerButton(REVERT, this));
	}

	/**
	 * Constructor.
	 *
	 * @param theEntry
	 *            The BookEntry this panel allows the user to edit
	 */
	public BookEntryPanel(final BookEntry theEntry) {
		this();
		entry = theEntry;
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}

	/**
	 * @return the BookEntry this panel is editing
	 */
	public CollectionEntry getEntry() {
		return entry;
	}

	/**
	 * @param newEntry
	 *            The BookEntry this panel is to edit
	 */
	public void setEntry(final BookEntry newEntry) {
		if (!entry.equals(newEntry)) {
			entry = newEntry;
			actionPerformed(new ActionEvent(this, 0, REVERT));
		}
	}

	/**
	 * Handle a button press.
	 *
	 * @param event
	 *            The event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		if ("Apply".equals(event.getActionCommand())) {
			apply();
		} else if (REVERT.equals(event.getActionCommand())) {
			if (entry == null) {
				tuneList.setSelectedIndices(new int[0]);
				pageField.setText("");
				keyField.setText("");
			} else {
				tuneList.setSelectedValue(entry.getTune(), true);
				pageField.setText(Integer.toString(entry.getPage()));
				keyField.setText(entry.getKey());
			}
		}
	}

	/**
	 * Called when the apply button is pressed.
	 */
	private void apply() {
		if (entry == null) {
			entry = new BookEntry(tuneList.getSelectedValue());
			entry.setPage(Integer.parseInt(pageField.getText()));
			entry.setKey(keyField.getText());
			firePropertyChange("entry", null, entry);
		} else {
			entry.setTune(tuneList.getSelectedValue());
			entry.setPage(Integer.parseInt(pageField.getText()));
			entry.setKey(keyField.getText());
			firePropertyChange("entry", entry, entry);
		}
	}
}
