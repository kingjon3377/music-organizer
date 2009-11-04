package utils;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to handle UUIDs.
 * 
 * @author Jonathan Lovelace
 */
public final class UUIDManager {
	/**
	 * The singleton object
	 */
	public static final UUIDManager MANAGER = new UUIDManager();

	/**
	 * Singleton constructor
	 */
	private UUIDManager() {
		super();
		maxId = -1;
	}

	/**
	 * A set of IDs we've given out.
	 */
	private final Set<Integer> givenIds = new HashSet<Integer>();
	/**
	 * The highest ID we've given out
	 */
	private int maxId;

	/**
	 * @return a new UUID
	 */
	public int getId() {
		maxId++;
		givenIds.add(Integer.valueOf(maxId));
		return maxId;
	}
	/**
	 * If the given ID hasn't been used, register it and return it. If it has,
	 * return a new one.
	 * 
	 * @param id
	 *            the ID the caller would like to have
	 * @return an ID that hasn't previously been used, the one given if it
	 *         qualifies.
	 */
	public int getId(final int id) {
		if (givenIds.contains(Integer.valueOf(id))) {
			return getId(); // NOPMD
		} else {
			if (id > maxId) {
				maxId = id;
			}
			givenIds.add(Integer.valueOf(id));
			return id;
		}
	}
}