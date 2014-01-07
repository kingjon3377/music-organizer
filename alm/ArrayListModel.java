package alm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * ArrayList which can be used as a ListModel in UI code.
 * @param <E> the type of item in the list
 * @author Phil Herold
 */
public class ArrayListModel<E> extends ArrayList<E> implements ListModel<E> {
    /**
     * This class implements an empty ArrayListModel.
     * @param <E> the type of element there are none of
     */
    protected static class EmptyList<E> extends ArrayListModel<E> {
    	/**
    	 * @return 0, the size of the empty list
    	 */
    	@Override
		public int size() {
            return 0;
        }
    	/**
    	 * @param obj ignored
    	 * @return false
    	 */
    	@Override
		public boolean contains(final Object obj) {
            return false;
        }
    	/**
    	 * @param index ignored
    	 * @return nothing; always throws
    	 */
    	@Override
		public E get(final int index) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
    /**
     * @param <E> the type of thing we want an immutable list of none of
     * @return an empty and immutable list
     */
    public static <E> ArrayListModel<E> emptyList() {
    	return new EmptyList<>();
    }
    /** List of ListDataListeners. */
	protected List<ListDataListener> listDataListeners;

	/**
	 * Augments superclass method to fire an appropriate event when an item is added to the
	 * collection.
	 * @param index the index to add it at
	 * @param element the element to add
	 */
	@Override
	public void add(final int index, final E element) {
		super.add(index, element);
		fireIntervalAdded(index, index);
	}

	/**
	 * Augments superclass method to fire an appropriate event when an item is added to the
	 * collection.
	 * @param o E
	 * @return boolean true (as per the contract of <code>Collection.add()</code>)
	 */
	@Override
	public boolean add(final E o) {
		boolean ok = super.add(o);
		if (ok) {
			int index = size() - 1;
			fireIntervalAdded(index, index);
		}
		return ok;
	}

	/**
	 * Augments superclass method to fire an appropriate event when a collection is added to
	 * this collection.
	 * @param coll Collection
	 * @return boolean true if obj was successfully added
	 */
	@Override
	public boolean addAll(final Collection<? extends E> coll) {
		int firstIndex = size() - 1;
		boolean ok = super.addAll(coll);
		if (ok) {
			fireIntervalAdded(firstIndex, size() - 1);
		}
		return ok;
	}

	/**
	 * Augments superclass method to fire an appropriate event when the collection is emptied.
	 */
	@Override
	public void clear() {
		int lastIndex = size() - 1;
		super.clear();
		fireIntervalRemoved(0, lastIndex);
	}

	// ESCA-JAVA0132: Necessary consequence of implementing two similar but not identical interfaces
	/**
	 * Augments superclass method to fire an appropriate event when the element at the given index
	 * is removed from the collection.
	 * @param index int
	 * @return the element removed from the list
	 */
	@Override
	public E remove(final int index) {
		E element = super.remove(index);
		if (element != null) {
			fireIntervalRemoved(index, index);
		}
		return element;
	}

	/**
	 * Overrides superclass method, forwarding it to the <code>remove(index)</code> method
	 * so the appropriate event can be fired.
	 * @param obj Object
	 * @return true if the element was removed, false otherwise
	 */
	@Override
	public boolean remove(final Object obj) {
		boolean ok = true;
		int index = indexOf(obj);
		if (index != -1) {
			ok = remove(index) != null;
		}
		return ok;
	}

	/**
	 * Augments superclass method to fire an appropriate event when a range of items is removed
	 * from the collection.
	 * @param fromIndex int
	 * @param toIndex int
	 */
	@Override
	public void removeRange(final int fromIndex, final int toIndex) {
		super.removeRange(fromIndex, toIndex);
		fireIntervalRemoved(fromIndex, toIndex);
	}

	/**
	 * Augments superclass method to fire an appropriate event when the given collection is
	 * removed from this collection.
	 * @param coll Collection
	 * @return boolean true if the collection was successfully removed
	 */
	@Override
	public boolean removeAll(final Collection<?> coll) {
		int lastIndex = size() - 1;
		boolean ok = super.removeAll(coll);
		if (ok) {
			fireIntervalRemoved(0, lastIndex);
		}
		return ok;
	}

	/**
	 * Augments superclass method to fire an appropriate event when the given collection is
	 * retained in this collection.
	 * @param coll the collection we want to retain all of
	 * @return true if successful
	 */
	@Override
	public boolean retainAll(final Collection<?> coll) {
		int lastIndex = size() - 1;
		boolean ok = super.retainAll(coll);
		if (ok) {
			fireIntervalRemoved(0, lastIndex);
			fireIntervalAdded(0, size() - 1);
		}
		return ok;
	}

	/**
	 * Augments superclass method to fire an appropriate event when an item in the collection
	 * is modified.
	 * @param index the index to set
	 * @param element the element to set there
	 * @return the element previously at that position
	 */
	@Override
	public E set(final int index, final E element) {
		final E retval = super.set(index, element);
		fireIntervalUpdated(index, index);
		return retval;
	}

	/**
	 * Implementation of the method in the ListModel interface.
	 * @return int
	 * @see javax.swing.ListModel#getSize()
	 */
	@Override
	public int getSize() {
		return super.size();
	}

	/**
	 * Implementation of the method in the ListModel interface.
	 * @param index int
	 * @return Object
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	@Override
	public E getElementAt(final int index) {
		return super.get(index);
	}

	/**
	 * Implementation of the method in the ListModel interface.
	 * @param listener ListDataListener
	 * @see javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener)
	 */
	@Override
	public void addListDataListener(final ListDataListener listener) {
		if (listDataListeners == null) {
			listDataListeners = new ArrayList<>();
		}
		if (!listDataListeners.contains(listener)) {
			listDataListeners.add(listener);
		}
	}

	/**
	 * Implementation of the method in the ListModel interface.
	 * @param listener ListDataListener
	 * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.ListDataListener)
	 */
	@Override
	public void removeListDataListener(final ListDataListener listener) {
		if (listDataListeners != null) {
			listDataListeners.remove(listener);
		}
	}

	/**
	 * Helper method to fire an event to all listeners when an interval in the collection
	 * is added.
	 * @param firstIndex  int
	 * @param lastIndex int
	 */
	protected void fireIntervalAdded(final int firstIndex, final int lastIndex) {
		if (listDataListeners != null) {
			ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, firstIndex, lastIndex);
			for (ListDataListener listener: listDataListeners) {
				listener.intervalAdded(e);
			}
		}
	}

	/**
	 * Helper method to fire an event to all listeners when an interval in the collection
	 * is removed.
	 * @param firstIndex  int
	 * @param lastIndex int
	 */
	protected void fireIntervalRemoved(final int firstIndex, final int lastIndex) {
		if (listDataListeners != null) {
			ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, firstIndex, lastIndex);
			for (ListDataListener listener: listDataListeners) {
				listener.intervalRemoved(e);
			}
		}
	}

	/**
	 * Helper method to fire an event to all listeners when an interval in the collection
	 * is updated.
	 * @param firstIndex  int
	 * @param lastIndex int
	 */
	protected void fireIntervalUpdated(final int firstIndex, final int lastIndex) {
		if (listDataListeners != null) {
			ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, firstIndex, lastIndex);
			for (ListDataListener listener: listDataListeners) {
				listener.contentsChanged(e);
			}
		}
	}

}
