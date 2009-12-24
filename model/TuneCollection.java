package model;

import java.util.Collection;
import java.util.List;

/**
 * A collection of entries.
 * @author Jonathan Lovelace
 *
 */
public interface TuneCollection {

	/**
	 * @param index an index into the list
	 * @return the entry at that index
	 */
	CollectionEntry getEntry(final int index);
	/**
	 * @return the entries in the collection
	 */
	List<? extends CollectionEntry> getEntries();
	/**
	 * @param tunes a collection of tunes
	 * @return whether this collection contains all of them
	 */
	boolean containsAll(final Collection<? extends Tune> tunes);
}
