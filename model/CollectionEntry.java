package model;

/**
 * An interface for entries in books and recordings.
 * @author kingjon
 *
 */
public interface CollectionEntry {

	/**
	 * Accessor.
	 * 
	 * @return the tune in the entry
	 */
	public abstract Tune getTune();

	/**
	 * Mutator.
	 * 
	 * @param newTune
	 *            the new tune in the entry
	 */
	public abstract void setTune(final Tune newTune);

}