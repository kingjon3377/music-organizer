package model.recording;

import model.CollectionEntry;
import model.Tune;

/**
 * A track on a recording.
 * 
 * @author Jonathan Lovelace
 * 
 */
public class RecordingEntry implements CollectionEntry {
	/**
	 * The tune.
	 */
	private Tune tune;
	/**
	 * The track number.
	 */
	private int track;

	/**
	 * Constructor.
	 */
	public RecordingEntry() {
		setTune(null);
		track = 0;
	}

	/**
	 * Accessor.
	 * 
	 * @return the tune in the entry
	 */
	@Override
	public final Tune getTune() {
		return tune;
	}

	/**
	 * Mutator.
	 * 
	 * @param newTune
	 *            the new tune in the entry
	 */
	public final void setTune(final Tune newTune) {
		tune = newTune;
	}

	/**
	 * @return the track number
	 */
	public final int getTrack() {
		return track;
	}

	/**
	 * @param trackNum
	 *            the track the tune is on this recording
	 */
	public final void setTrack(final int trackNum) {
		track = trackNum;
	}

	/**
	 * @return a String representation of the entry
	 */
	@Override
	public String toString() {
		return "Track " + track + ": " + (tune == null ? "No tune" : tune.toString());
	}
}
