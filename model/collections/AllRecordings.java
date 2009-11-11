package model.collections;

import model.recording.Recording;
import alm.ArrayListModel;

/**
 * A list of all Recordings
 * @author Jonathan Lovelace
 */
public class AllRecordings extends ArrayListModel<Recording> {
	/**
	 * Version UID for serialization. 
	 */
	private static final long serialVersionUID = -2542421957193448015L;
	/**
	 * Singleton.
	 */
	public static final AllRecordings ALL_RECORDINGS = new AllRecordings();
	/**
	 * Singleton constructor.
	 */
	private AllRecordings() {
		super();
	}
}
