package model.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractListModel;

import model.Tune;

/**
 * A ListModel set of all tunes.
 * 
 * @author Jonathan Lovelace
 */
public class AllTunes extends AbstractListModel implements Set<Tune> {
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

	/**
	 * What we have backing the set.
	 */
	private final Map<Integer, Tune> tuneMap = new HashMap<Integer, Tune>();

	/**
	 * Add a tune.
	 * 
	 * @param tune
	 *            The tune to add
	 * @return true
	 */
	@Override
	public boolean add(Tune tune) {
		tuneMap.put(tune.getId(), tune);
		return true;
	}

	/**
	 * Add several tunes.
	 * 
	 * @param coll
	 *            The tunes to add
	 * @return true
	 */
	@Override
	public boolean addAll(Collection<? extends Tune> coll) {
		for (Tune tune : coll) {
			add(tune);
		}
		return true;
	}

	/**
	 * Clear the set.
	 */
	@Override
	public void clear() {
		tuneMap.clear();
	}

	/**
	 * @param obj
	 *            An object
	 * @return whether the set contains it
	 */
	@Override
	public boolean contains(Object obj) {
		return tuneMap.containsValue(obj);
	}

	/**
	 * @param coll
	 *            A collection of objects
	 * @return whether the set contains all of them.
	 */
	@Override
	public boolean containsAll(Collection<?> coll) {
		return tuneMap.values().containsAll(coll);
	}

	/**
	 * @return whether the set is empty
	 */
	@Override
	public boolean isEmpty() {
		return tuneMap.isEmpty();
	}

	/**
	 * @return an iterator over the tunes in the set.
	 */
	@Override
	public Iterator<Tune> iterator() {
		return tuneMap.values().iterator();
	}

	/**
	 * @param obj
	 *            an object to remove
	 * @return whether it was successfully removed
	 */
	@Override
	public boolean remove(Object obj) {
		return obj instanceof Tune && tuneMap.remove(((Tune) obj).getId()) != null;
	}

	/**
	 * @param coll
	 *            a collection of objects to remove
	 * @return whether all of them were successfully removed
	 */
	@Override
	public boolean removeAll(Collection<?> coll) {
		boolean retval = true;
		for (Object obj : coll) {
			retval &= remove(obj);
		}
		return retval;
	}

	/**
	 * @param c
	 *            Ignored
	 * @return nothing
	 * @throws UnsupportedOperationException
	 *             always
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("AllTunes doesn't support retainAll()");
	}
	/**
	 * @return the size of the set
	 */
	@Override
	public int size() {
		return tuneMap.size();
	}
	/**
	 * @return the set as an array
	 */
	@Override
	public Object[] toArray() {
		return tuneMap.values().toArray();
	}
	/**
	 * @param <T> A generic parameter
	 * @param array An array
	 * @return The set as an array (that array if it'll fit)
	 */
	@Override
	public <T> T[] toArray(T[] array) {
		return tuneMap.values().toArray(array);
	}
	/**
	 * @param index An index into the set
	 * @return The value at that index
	 */
	@Override
	public Object getElementAt(int index) {
		return tuneMap.get(Integer.valueOf(index));
	}
	/**
	 * @return the size of the set
	 */
	@Override
	public int getSize() {
		return tuneMap.size();
	}
}
