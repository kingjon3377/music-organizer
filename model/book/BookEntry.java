package model.book;

import model.Tune;

/**
 * An entry in a book, consisting of a tune and associated data.
 * @author kingjon
 */
public class BookEntry {
	/**
	 * The tune
	 */
	private Tune tune;
	/**
	 * The page number the tune is on
	 */
	private int page;
	/**
	 * Constructor
	 * @param theTune The tune in this entry
	 */
	public BookEntry(final Tune theTune) {
		tune = theTune;
		page = 0;
	}
	/**
	 * Accessor.
	 * @return the tune in the entry
	 */
	public final Tune getTune() {
		return tune;
	}
	/**
	 * Mutator.
	 * @param newTune the new tune in the entry
	 */
	public void setTune(final Tune newTune) {
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
	 * @return a String representation of the entry
	 */
	@Override
	public String toString() {
		return (tune == null ? "No tune" : tune.toString()) + " on page " + page;
	}
}
