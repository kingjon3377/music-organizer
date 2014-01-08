package model.book;

import model.CollectionEntry;
import model.Tune;

import org.eclipse.jdt.annotation.Nullable;

/**
 * An entry in a book, consisting of a tune and associated data.
 * @author Jonathan Lovelace
 */
public class BookEntry implements CollectionEntry {
	/**
	 * The tune.
	 */
	@Nullable private Tune tune;
	/**
	 * The page number the tune is on.
	 */
	private int page;
	/**
	 * What key the tune is in this book.
	 */
	private String key;
	/**
	 * Constructor.
	 * @param theTune The tune in this entry
	 */
	public BookEntry(@Nullable final Tune theTune) {
		tune = theTune;
		page = 0;
		key = "";
	}
	/**
	 * Accessor.
	 * @return the tune in the entry
	 */
	@Override
	@Nullable
	public final Tune getTune() {
		return tune;
	}
	/**
	 * Mutator.
	 * @param newTune the new tune in the entry
	 */
	@Override
	public void setTune(@Nullable final Tune newTune) {
		tune = newTune;
	}
	/**
	 * @return the page the tune is on
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param pageNum the page the tune is on
	 */
	public void setPage(final int pageNum) {
		page = pageNum;
	}
	/**
	 * @return the key the tune is in in the book
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param newKey the key the tune is in in the book
	 */
	public void setKey(final String newKey) {
		key = newKey;
	}
	/**
	 * @return a String representation of the entry
	 */
	@Override
	public String toString() {
		final Tune lTune = tune;
		return (lTune == null ? "No tune" : lTune.toString()) + " on page " + page;
	}
}
