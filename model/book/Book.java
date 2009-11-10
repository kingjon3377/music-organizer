package model.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A book is a collection of tunes, each with associated data, that is, a
 * collection of BookEntries.
 * 
 * @author Jonathan Lovelace
 * 
 */
public class Book implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 8636378088443950838L;
	/**
	 * The title of the book.
	 */
	private String title;
	/**
	 * The entries in the book.
	 */
	private List<BookEntry> entries = new ArrayList<BookEntry>();
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
	/**
	 * @return a copy of the list of entries
	 */
	public List<BookEntry> getEntries() {
		return new ArrayList<BookEntry>(entries);
	}
	/**
	 * Add an entry.
	 * @param entry the entry to add
	 */
	public void addEntry(final BookEntry entry) {
		entries.add(entry);
	}
	/**
	 * @param index an index into the list
	 * @return the entry at that index
	 */
	public BookEntry getEntry(final int index) {
		return entries.get(index);
	}
	/**
	 * Remove an entry from the list.
	 * @param index the index of the entry to remove.
	 */
	public void removeEntry(final int index) {
		entries.remove(index);
	}
	/**
	 * Remove an entry from the list.
	 * @param entry the entry to remove
	 */
	public void removeEntry(final BookEntry entry) {
		entries.remove(entry);
	}
	/**
	 * Add all the entries in the collection that aren't already in the list.
	 * @param coll a collection of entries
	 */
	public void addAll(final Collection<? extends BookEntry> coll) {
		for (BookEntry entry : coll) {
			if (!entries.contains(entry)) {
				entries.add(entry);
			}
		}
	}
}
