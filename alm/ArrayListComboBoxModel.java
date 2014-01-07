package alm;


import javax.swing.ComboBoxModel;

/**
 * This class extends ArrayListModel to support a collection as a model for a JComboBox.
 * @author Phil Herold
 */
public class ArrayListComboBoxModel extends ArrayListModel implements ComboBoxModel {
	private Object selectedItem;

	/**
	 * Implements the method in the ComboBoxModel interface
	 * @return Object
	 * @see javax.swing.ComboBoxModel#getSelectedItem()
	 */
	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	/**
	 * Implements the method in the ComboBoxModel interface
	 * @parm item Object
	 * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
	 */
	@Override
	public void setSelectedItem(final Object item) {
		selectedItem = item;
	    fireIntervalUpdated(-1, -1);
	}

}
