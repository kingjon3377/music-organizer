package model.book;

import java.util.HashMap;

/**
 * A book is a collection of tunes, each with associated data, that is, a
 * collection of BookEntries.
 * 
 * @author Jonathan Lovelace
 * 
 */
public class Book extends HashMap<Integer,BookEntry> {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 8636378088443950838L;
	/**
	 * The title of the book.
	 */
	private String title;
	/**
	 * Constructor
	 */
	public Book() {
		super();
		title = "";
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param newTitle the book's new title
	 */
	public void setTitle(final String newTitle) {
		title = newTitle;
	}
}
