package model.recording;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.CollectionEntry;
import model.Tune;
import model.TuneCollection;

/**
 * A list of tunes, representing a CD.
 * @author Jonathan Lovelace
 */
public class Recording implements Serializable, TuneCollection {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 1046321508108731146L;
	/**
	 * The title of the CD
	 */
	private String title;
	/**
	 * The entries in the recording
	 */
	private List<RecordingEntry> entries = new ArrayList<RecordingEntry>();
	/**
	 * Constructor
	 */
	public Recording() {
		super();
		title = "";
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param newTitle the book's new title
	 */
	public void setTitle(final String newTitle) {
		title = newTitle;
	}
	/**
	 * @return a copy of the list of entries
	 */
	public List<RecordingEntry> getEntries() {
		return new ArrayList<RecordingEntry>(entries);
	}
	/**
	 * Add an entry.
	 * @param entry the entry to add
	 */
	public void addEntry(final RecordingEntry entry) {
		entries.add(entry);
	}
	/**
	 * @param index an index into the list
	 * @return the entry at that index
	 */
	public CollectionEntry getEntry(final int index) {
		return entries.get(index);
	}
	/**
	 * Remove an entry from the list.
	 * @param index the index of the entry to remove.
	 */
	public void removeEntry(final int index) {
		entries.remove(index);
	}
	/**
	 * Remove an entry from the list.
	 * @param entry the entry to remove
	 */
	public void removeEntry(final CollectionEntry entry) {
		entries.remove(entry);
	}
	/**
	 * Add all the entries in the collection that aren't already in the list.
	 * @param coll a collection of entries
	 */
	public void addAll(final Collection<? extends RecordingEntry> coll) {
		for (RecordingEntry entry : coll) {
			if (!entries.contains(entry)) {
				entries.add(entry);
			}
		}
	}
	/**
	 * @return a string representation of the recording
	 */
	@Override
	public String toString() {
		return title;
	}
	/**
	 * @param tunes a collection of tunes
	 * @return whether this recording contains all of them
	 */
	@Override
	public boolean containsAll(Collection<? extends Tune> tunes) {
		final Set<Tune> tunesInRecording = new HashSet<Tune>();
		for (RecordingEntry entry : entries) {
			tunesInRecording.add(entry.getTune());
		}
		return tunesInRecording.containsAll(tunes);
	}
}
