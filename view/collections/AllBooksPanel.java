package view.collections;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.book.Book;
import model.collections.AllBooks;

import org.eclipse.jdt.annotation.Nullable;

import utils.ListenerButton;
import view.BookPane;
import view.EditWindow;

/**
 * A panel to create and edit books.
 *
 * @author Jonathan Lovelace
 */
public class AllBooksPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -365416253560487210L;
	/**
	 * A list of books, of which one can be selected.
	 */
	private final transient JList<Book> bookList = new JList<>(AllBooks.ALL_BOOKS);

	/**
	 * Constructor.
	 */
	public AllBooksPanel() {
		super(new BorderLayout());
		add(new JLabel("Books"), BorderLayout.WEST);
		add(bookList, BorderLayout.CENTER);
		final JPanel panel = new JPanel();
		panel.add(new ListenerButton("Add Book", this));
		panel.add(new ListenerButton("Edit Book", this));
		panel.add(new ListenerButton("Remove Book", this));
		add(panel, BorderLayout.SOUTH);
	}

	/**
	 * Handle button presses.
	 *
	 * @param event
	 *            the event we're handling
	 */
	@Override
	public void actionPerformed(@Nullable final ActionEvent event) {
		if (event == null) {
			return;
		} else if ("Add Book".equals(event.getActionCommand())) {
			new EditWindow("Add book", new BookPane(), this).setVisible(true);
		} else if ("Edit Book".equals(event.getActionCommand())) {
			new EditWindow("Edit book", new BookPane(bookList
					.getSelectedValue()), this).setVisible(true);
		} else if ("Remove Book".equals(event.getActionCommand())) {
			AllBooks.ALL_BOOKS.remove(bookList.getSelectedIndex());
		}
	}

	/**
	 * Ensure new books get added to the AllBooks list.
	 *
	 * @param evt
	 *            the event we're handling
	 */
	@Override
	public void propertyChange(@Nullable final PropertyChangeEvent evt) {
		if (evt == null) {
			return;
		}
		final Object oldValue = evt.getOldValue();
		final Object newValue = evt.getNewValue();
		if ("book".equals(evt.getPropertyName()) && oldValue == null
				&& newValue instanceof Book) {
			AllBooks.ALL_BOOKS.add((Book) newValue);
		}
	}

}
