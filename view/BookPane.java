package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CollectionEntry;
import model.TuneCollection;
import model.book.Book;
import model.book.BookEntry;
import utils.ListenerButton;
import alm.ArrayListModel;

/**
 * A window to add or edit a book.
 * 
 * @author Jonathan Lovelace
 * 
 */
public class BookPane extends JPanel implements ActionListener, PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 8657213337590334699L;
	/**
	 * The book we're dealing with
	 */
	private Book book;
	/**
	 * A text box for the book's title
	 */
	private final JTextField titleField = new JTextField();
	/**
	 * The intermediate model of the tunes, used to back the list
	 */
	private final ArrayListModel<BookEntry> tunes = new ArrayListModel<BookEntry>();
	/**
	 * The list of tunes.
	 */
	private final JList tunesList = new JList(tunes);

	/**
	 * Constructor.
	 * 
	 * @param theBook
	 *            The book this window is editing
	 */
	public BookPane(final Book theBook) {
		this();
		book = theBook;
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
		add(new ListenerButton("Revert", this));
	}

	/**
	 * Handle button presses
	 * 
	 * @param actEvent
	 *            The button-press event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent actEvent) {
		if ("Revert".equals(actEvent.getActionCommand())) {
			if (!tunes.isEmpty()) {
				tunes.clear();
			}
			if (book == null) {
				titleField.setText("");
			} else {
				titleField.setText(book.getTitle());
				tunes.addAll(book.getEntries());
			}
		} else if ("Apply".equals(actEvent.getActionCommand())) {
			apply();
		} else if ("Edit entry".equals(actEvent.getActionCommand())) {
			new EditWindow("Edit tune entry", new BookEntryPanel(tunes.get(tunesList
					.getSelectedIndex())), this).setVisible(true);
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
		if (book == null) {
			book = new Book();
			book.addAll(tunes);
			book.setTitle(titleField.getText());
			firePropertyChange("book", null, book);
		} else {
			book.setTitle(titleField.getText());
			for (CollectionEntry entry : book.getEntries()) {
				if (!tunes.contains(entry)) {
					book.removeEntry(entry);
				}
			}
			book.addAll(tunes);
			firePropertyChange("book", book, book);
		}
	}

	/**
	 * @return the Book this frame is for editing
	 */
	public TuneCollection getBook() {
		return book;
	}

	/**
	 * @param theBook
	 *            The Book this frame will edit
	 */
	public void setBook(final Book theBook) {
		if (!book.equals(theBook)) {
			book = theBook;
			actionPerformed(new ActionEvent(this, 0, "Revert"));
		}
	}

	/**
	 * Handle a spawned panel's "apply" event.
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if ("entry".equals(evt.getPropertyName())) {
			if (evt.getOldValue() == null) {
				tunes.add((BookEntry) evt.getNewValue());
			}
		}
	}
}
