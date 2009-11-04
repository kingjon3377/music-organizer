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
	private final Tune tune;
	/**
	 * Constructor
	 * @param theTune The tune in this entry
	 */
	public BookEntry(final Tune theTune) {
		tune = theTune;
	}
	/**
	 * Accessor
	 * @return the tune in the entry
	 */
	public final Tune getTune() {
		return tune;
	}
}
