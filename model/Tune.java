package model;


/**
 * A tune.
 * @author Jonathan Lovelace
 */
public class Tune {
	/**
	 * Tune name.
	 */
	private String name;
	/**
	 * Composer.
	 */
	private String composer;
	/**
	 * Default constructor
	 */
	public Tune() {
		name = "";
		composer = "";
	}
	/**
	 * Accessor.
	 * @return the tune's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Mutator
	 * @param newName the tune's new name
	 */
	public void setName(final String newName) {
		name = newName;
	}
	/**
	 * @return the tune's composer
	 */
	public String getComposer() {
		return composer;
	}
	/**
	 * @param compos the tune's composer
	 */
	public void setComposer(final String compos) {
		composer = compos;
	}
	/**
	 * @return a string representation of the tune
	 */
	@Override
	public String toString() {
		return '"' + name + "\" by " + composer;
	}
}
