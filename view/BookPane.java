package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CollectionEntry;
import model.TuneCollection;
import model.book.Book;
import model.book.BookEntry;

import org.eclipse.jdt.annotation.Nullable;

import utils.ListenerButton;
import alm.ArrayListModel;

/**
 * A window to add or edit a book.
 *
 * @author Jonathan Lovelace
 *
 */
public final class BookPane extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * The "revert" action string.
	 */
	private static final String REVERT = "Revert";
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 8657213337590334699L;
	/**
	 * The book we're dealing with.
	 */
	@Nullable private Book book;
	/**
	 * Logger.
	 */
	@SuppressWarnings("null")
	private static final Logger LOGGER = Logger.getLogger(BookPane.class
			.getName());
	/**
	 * A text box for the book's title.
	 */
	private final transient JTextField titleField = new JTextField();
	/**
	 * The intermediate model of the tunes, used to back the list.
	 */
	private final transient ArrayListModel<BookEntry> tunes = new ArrayListModel<>();
	/**
	 * The list of tunes.
	 */
	private final transient JList<BookEntry> tunesList = new JList<>(tunes);

	/**
	 * Constructor.
	 *
	 * @param theBook
	 *            The book this window is editing
	 */
	public BookPane(@Nullable final Book theBook) {
		this();
		book = theBook;
		actionPerformed(new ActionEvent(this, 0, REVERT));
	}

	/**
	 * Constructor.
	 */
	public BookPane() {
		super(new GridLayout(0, 2));
		add(new JLabel("Book Title"));
		add(titleField);
		add(new JLabel("Tune entries"));
		add(tunesList);
		tunesList.setEnabled(true);
		add(new ListenerButton("Add tune entry", this));
		add(new ListenerButton("Remove entry", this));
		add(new ListenerButton("Edit entry", this));
		add(new JLabel());
		add(new ListenerButton("Apply", this));
		add(new ListenerButton(REVERT, this));
	}

	/**
	 * Handle button presses.
	 *
	 * @param actEvent
	 *            The button-press event we're handling
	 */
	@Override
	public void actionPerformed(@Nullable final ActionEvent actEvent) {
		if (actEvent == null) {
			return;
		} else if (REVERT.equals(actEvent.getActionCommand())) {
			if (!tunes.isEmpty()) {
				tunes.clear();
			}
			final Book localBook = book;
			if (localBook == null) {
				titleField.setText("");
			} else {
				titleField.setText(localBook.getTitle());
				try {
					tunes.addAll(localBook.getEntries());
				} catch (IndexOutOfBoundsException except) {
					LOGGER.log(Level.INFO,
							"Expected IndexOutOfBoundsException", except);
				}
			}
		} else if ("Apply".equals(actEvent.getActionCommand())) {
			apply();
		} else if ("Edit entry".equals(actEvent.getActionCommand())) {
			new EditWindow("Edit tune entry", new BookEntryPanel(tunes
					.get(tunesList.getSelectedIndex())), this).setVisible(true);
		} else if ("Remove entry".equals(actEvent.getActionCommand())) {
			tunes.remove(tunesList.getSelectedIndex());
		} else if ("Add tune entry".equals(actEvent.getActionCommand())) {
			new EditWindow("Add existing tune", new BookEntryPanel(), this)
					.setVisible(true);
		}
	}

	/**
	 * Called when the "apply" button is pressed. Applies changes the user has
	 * made to the event this panel is editing.
	 */
	private void apply() {
		final Book localBook = book;
		final String title = titleField.getText();
		if (localBook == null) {
			book = new Book();
			book.addAll(tunes);
			assert book != null;
			book.setTitle(title == null ? "" : title);
			firePropertyChange("book", null, book);
		} else {
			localBook.setTitle(title == null ? "" : title);
			for (CollectionEntry entry : localBook.getEntries()) {
				if (entry != null && !tunes.contains(entry)) {
					localBook.removeEntry(entry);
				}
			}
			localBook.addAll(tunes);
			firePropertyChange("book", book, localBook);
		}
	}

	/**
	 * @return the Book this frame is for editing
	 */
	@Nullable
	public TuneCollection getBook() {
		return book;
	}

	/**
	 * @param theBook
	 *            The Book this frame will edit
	 */
	public void setBook(final Book theBook) {
		final Book old = book;
		if (old == null || !old.equals(theBook)) {
			book = theBook;
			actionPerformed(new ActionEvent(this, 0, REVERT));
		}
	}

	/**
	 * Handle a spawned panel's "apply" event.
	 * @param evt An event from a spawned panel.
	 */
	@Override
	public void propertyChange(@Nullable final PropertyChangeEvent evt) {
		if (evt == null) {
			return;
		}
		final Object newValue = evt.getNewValue();
		if ("entry".equals(evt.getPropertyName()) && evt.getOldValue() == null
				&& newValue instanceof BookEntry) {
			tunes.add((BookEntry) newValue);
		}
	}
}
