package view.search;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import model.Tune;
import model.TuneCollection;
import model.book.Book;
import model.collections.AllBooks;
import model.collections.AllRecordings;
import model.collections.AllTunes;
import model.recording.Recording;

import org.eclipse.jdt.annotation.Nullable;

import utils.ListenerButton;
import view.BookPane;
import view.EditWindow;
import view.RecordingPanel;
import alm.ArrayListModel;

/**
 * A panel to search for collections containing specified tunes.
 *
 * @author Jonathan Lovelace
 */
public class CollectionSearchPanel extends JPanel implements ActionListener,
		PropertyChangeListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -19316879878099148L;
	/**
	 * A list of all tunes.
	 */
	private final transient JList<Tune> tuneList = new JList<>(AllTunes.ALL_TUNES);
	/**
	 * A list-model to back the list of search results.
	 */
	private final transient ArrayListModel<TuneCollection> results = new ArrayListModel<>();
	/**
	 * The list of search results.
	 */
	private final transient JList<TuneCollection> list = new JList<>(results);

	/**
	 * Constructor.
	 */
	public CollectionSearchPanel() {
		super(new BorderLayout());
		final JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.add(new JLabel("Tunes"));
		panel.add(tuneList);
		tuneList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		panel.add(new JLabel(""));
		panel.add(new ListenerButton("Search", this));
		add(panel, BorderLayout.NORTH);
		add(list, BorderLayout.CENTER);
		final JPanel panelTwo = new JPanel(new GridLayout(0, 2));
		panelTwo.add(new ListenerButton("Edit Collection", this));
		panelTwo.add(new ListenerButton("Close", this));
		add(panelTwo, BorderLayout.SOUTH);
	}

	/**
	 * Handle button presses.
	 *
	 * @param evt
	 *            the event to handle
	 */
	@Override
	public void actionPerformed(@Nullable final ActionEvent evt) {
		if (evt == null) {
			return;
		} else if ("Search".equals(evt.getActionCommand())) {
			search();
		} else if ("Edit Collection".equals(evt.getActionCommand())) {
			if (list.getSelectedValue() instanceof Book) {
				new EditWindow("Edit book", new BookPane((Book) list.getSelectedValue()),
						this).setVisible(true);
			} else if (list.getSelectedValue() instanceof Recording) {
				new EditWindow("Edit recording", new RecordingPanel((Recording) list
						.getSelectedValue()), this).setVisible(true);
			} else {
				throw new IllegalStateException("Value was an unknown type of TuneCollection");
			}
		} else if ("Close".equals(evt.getActionCommand())) {
			this.setVisible(false);
			for (ContainerListener listener : getContainerListeners()) {
				listener.componentRemoved(null);
			}
		}
	}

	/**
	 * Search for collections containing the tunes.
	 */
	private void search() {
		if (!results.isEmpty()) {
			results.clear();
		}
		final Set<TuneCollection> collections = new HashSet<>();
		collections.addAll(AllBooks.ALL_BOOKS);
		collections.addAll(AllRecordings.ALL_RECORDINGS);
		for (TuneCollection coll : collections) {
			if (coll.containsAll(tuneList.getSelectedValuesList())) {
				results.add(coll);
			}
		}
	}
	/**
	 * Handle events from spawned EditWindows. TODO: Implement?
	 * @param evt the event to handle
	 */
	@Override
	public void propertyChange(@Nullable final PropertyChangeEvent evt) {
		// Do nothing for now
	}
}
