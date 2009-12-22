package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.book.Book;
import model.book.BookEntry;
import model.collections.AllBooks;
import utils.ListenerButton;
import alm.ArrayListModel;
/**
 * A panel to search for tunes in books by key.
 * @author kingjon
 *
 */
public class KeySearchPanel extends JPanel implements ActionListener, PropertyChangeListener {
	/**
	 * Version UID for serialization. 
	 */
	private static final long serialVersionUID = 502479920471260131L;
	/**
	 * A t4ext box to enter the search term.
	 */
	private final JTextField searchField = new JTextField();
	/**
	 * A list-model to back the list of search results
	 */
	private final ArrayListModel<Book> results = new ArrayListModel<Book>();
	/**
	 * The list of search results
	 */
	private final JList list = new JList(results);
	/**
	 * Constructor
	 */
	public KeySearchPanel() {
		super (new BorderLayout());
		final JPanel panel = new JPanel(new GridLayout(0,2));
		panel.add(searchField);
		panel.add(new ListenerButton("Search",this));
		add(panel, BorderLayout.NORTH);
		add(list, BorderLayout.CENTER);
		final JPanel panelTwo = new JPanel(new GridLayout(0,2));
		panelTwo.add(new ListenerButton("View Book", this));
		panelTwo.add(new ListenerButton("Close", this));
		add(panelTwo, BorderLayout.SOUTH);
	}
	/**
	 * Handle button presses
	 * 
	 * @param evt
	 *            the event to handle
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if ("Search".equals(evt.getActionCommand())) {
			if (!results.isEmpty()) {
				results.clear();
			}
			for (Book book : AllBooks.ALL_BOOKS) {
				for (BookEntry entry : book.getEntries()) {
					if (entry.getKey().equalsIgnoreCase(searchField.getText())) {
						results.add(book);
						break;
					}
				}
			}
		} else if ("View Book".equals(evt.getActionCommand())) {
			new EditWindow("View Book", new BookPane((Book) list.getSelectedValue()),this).setVisible(true);
		} else if ("Close".equals(evt.getActionCommand())) {
			this.setVisible(false);
			for (ContainerListener listener : getContainerListeners()) {
				listener.componentRemoved(null);
			}
		}
	}
	/**
	 * Handle events from spawned EditWindows. TODO: Implement?
	 * @param evt the event to handle
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Do nothing for now
	}
}
