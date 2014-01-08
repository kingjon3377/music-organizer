package model;

import org.eclipse.jdt.annotation.Nullable;

/**
 * An interface for entries in books and recordings.
 *
 * TODO: Should these really be able to take null?
 *
 * @author Jonathan Lovelace
 *
 */
public interface CollectionEntry {

	/**
	 * Accessor.
	 *
	 * @return the tune in the entry
	 */
	@Nullable Tune getTune();

	/**
	 * Mutator.
	 *
	 * @param newTune
	 *            the new tune in the entry
	 */
	void setTune(@Nullable final Tune newTune);

}
