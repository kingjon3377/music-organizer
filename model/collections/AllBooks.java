package model.collections;

import model.book.Book;
import alm.ArrayListModel;

/**
 * A list of all Books.
 * @author Jonathan Lovelace
 *
 */
public class AllBooks extends ArrayListModel<Book> {
	/**
	 * Version UID for serialization. 
	 */
	private static final long serialVersionUID = 4529117699433560416L;
	/**
	 * Singleton
	 */
	public static final AllBooks ALL_BOOKS = new AllBooks();
	/**
	 * Singleton constructor.
	 */
	private AllBooks() {
		super();
	}
}
