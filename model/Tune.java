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
	private String name;
	/**
	 * Explicit-value constructor.
	 * @param idNum The tune's UUID number
	 * @param newName The tune's name
	 */
	public Tune(final int idNum, final String newName) {
		id = UUIDManager.MANAGER.getId(idNum);
		name = newName;
	}
	/**
	 * Default constructor
	 */
	public Tune() {
		id = UUIDManager.MANAGER.getId();
		name = "";
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
}
