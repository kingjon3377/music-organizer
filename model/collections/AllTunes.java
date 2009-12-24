package model.collections;

import model.Tune;
import alm.ArrayListModel;

/**
 * A List of all tunes.
 * 
 * @author Jonathan Lovelace
 */
public final class AllTunes extends ArrayListModel<Tune> {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -6562973402514730346L;
	/**
	 * Singleton.
	 */
	public static final AllTunes ALL_TUNES = new AllTunes();

	/**
	 * Singleton constructor.
	 */
	private AllTunes() {
		super();
	}
}
