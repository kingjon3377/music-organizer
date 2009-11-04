package model;

import utils.UUIDManager;

/**
 * A tune.
 * @author Jonathan Lovelace
 */
public class Tune {
	/**
	 * UUID.
	 */
	private final int id;
	/**
	 * Tune name.
	 */
	private String name = "";
	/**
	 * Composer.
	 */
	private String composer = "";
	/**
	 * Explicit-value constructor.
	 * @param idNum The tune's UUID number
	 */
	public Tune(final int idNum) {
		id = UUIDManager.MANAGER.getId(idNum);
	}
	/**
	 * Default constructor
	 */
	public Tune() {
		id = UUIDManager.MANAGER.getId();
	}
	/**
	 * Accessor.
	 * @return the tune's UUID.
	 */
	public int getId() {
		return id;
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
	 * Hash based on ID.
	 * @return the ID, to hash by.
	 */
	@Override
	public int hashCode() {
		return id;
	}
	/**
	 * Equality based on ID.
	 * @param obj Another object
	 * @return true if that object is a Tune with the same ID.
	 */
	@Override
	public boolean equals(final Object obj) {
		return obj instanceof Tune && ((Tune) obj).id == id;
	}
}
