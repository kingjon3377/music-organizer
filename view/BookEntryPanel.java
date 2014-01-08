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

import model.Tune;
import model.book.BookEntry;
import model.collections.AllTunes;

import org.eclipse.jdt.annotation.Nullable;

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
	@Nullable private BookEntry entry;
	/**
	 * A list of tunes, of which one can be selected.
	 */
	private final transient JList<Tune> tuneList = new JList<>(AllTunes.ALL_TUNES);
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
			public boolean verify(@Nullable final JComponent input) {
				assert input instanceof JTextField;
				final String text = ((JTextField) input).getText();
				return text != null && verify(text);
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
	public BookEntryPanel(@Nullable final BookEntry theEntry) {
		this();
		entry = theEntry;
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}

	/**
	 * @return the BookEntry this panel is editing
	 */
	@Nullable public BookEntry getEntry() {
		return entry;
	}

	/**
	 * @param newEntry
	 *            The BookEntry this panel is to edit
	 */
	public void setEntry(@Nullable final BookEntry newEntry) {
		final BookEntry lEntry = entry;
		if (lEntry == null || !lEntry.equals(newEntry)) {
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
	public void actionPerformed(@Nullable final ActionEvent event) {
		if (event == null) {
			return;
		} else if ("Apply".equals(event.getActionCommand())) {
			apply();
		} else if (REVERT.equals(event.getActionCommand())) {
			final BookEntry lEntry = entry;
			if (lEntry == null) {
				tuneList.setSelectedIndices(new int[0]);
				pageField.setText("");
				keyField.setText("");
			} else {
				tuneList.setSelectedValue(lEntry.getTune(), true);
				pageField.setText(Integer.toString(lEntry.getPage()));
				keyField.setText(lEntry.getKey());
			}
		}
	}

	/**
	 * Called when the apply button is pressed.
	 */
	private void apply() {
		final BookEntry lEntry = entry;
		final String key = keyField.getText();
		if (lEntry == null) {
			final BookEntry nEntry = new BookEntry(tuneList.getSelectedValue());
			nEntry.setPage(Integer.parseInt(pageField.getText()));
			nEntry.setKey(key == null ? "" : key);
			entry = nEntry;
			firePropertyChange("entry", null, nEntry);
		} else {
			lEntry.setTune(tuneList.getSelectedValue());
			lEntry.setPage(Integer.parseInt(pageField.getText()));
			lEntry.setKey(key == null ? "" : key);
			firePropertyChange("entry", lEntry, lEntry);
		}
	}
}
