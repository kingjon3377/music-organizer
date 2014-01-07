package alm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Tests the ArrayListModel and ArrayListComboBoxModel classes, this time using UIElement
 * implementations in the model.
 * @author Phil Herold
 */
public final class UIElementTest {
	/**
	 * Whether to use large rather than small icons.
	 */
	protected static boolean useLargeIcons;
	/**
	 * The panel used by the test.
	 */
	protected static JPanel componentPanel;

	/**
	 * CellRenderer for a UIElement in a JList.
	 */
	private static class UIElementCellRenderer extends DefaultListCellRenderer {
		/**
		 * Needed to fix access warning.
		 */
		protected UIElementCellRenderer() {
			// nothing
		}
		/**
		 * @param list the list being rendered
		 * @param value the item being rendered
		 * @param index its index
		 * @param isSelected whether it's selected
		 * @param hasFocus whether the list has the focus
		 * @return a component to draw the item
		 */
		@Override
		public Component getListCellRendererComponent(
				@SuppressWarnings("rawtypes") final JList list,
				final Object value, final int index, final boolean isSelected,
				final boolean hasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list,
					value, index, isSelected, hasFocus);
			label.setIcon(useLargeIcons ? ((UIElement) value).getLargeIcon()
					: ((UIElement) value).getSmallIcon());
	        return label;
	    }
	}

	/**
	 * Entry point of application for JVM.
	 * @param args String array of arguments (not used)
	 */
	public static void main(final String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				final JFrame frame = new JFrame("UIElement Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// The data models.
				final ArrayListModel<UIElement> model = new ArrayListModel<>();
				UIElementFactory.addElementsToList(model);
				ArrayListComboBoxModel<UIElement> listModel = new ArrayListComboBoxModel<>();

				// The list is used to select a UIElement in the model, and show it's
				// corresponding view
				final JList<UIElement> list = new JList<UIElement>(listModel) {
			        // This method is called as the cursor moves within the list.
			        @Override
					public String getToolTipText(final MouseEvent e) {
			            int index = locationToIndex(e.getPoint());
						UIElement element = getModel()
								.getElementAt(index);
			            return element.getDescription();
			        }
				};
				list.setCellRenderer(new UIElementCellRenderer());
				JScrollPane sp = new JScrollPane(list);
				sp.getViewport().setPreferredSize(new Dimension(100, 100));
				list.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(final ListSelectionEvent e) {
						final UIElement element = list
								.getSelectedValue();
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								componentPanel.removeAll();
								if (element != null) {
									componentPanel.add(element.getComponent(), BorderLayout.CENTER);
									componentPanel.setToolTipText(element.getDescription());
								}
								frame.invalidate();
								frame.validate();
								frame.repaint();
							}
						});
					}
				});

				// Checkbox is used to swap between small and large icons in the list box
				final JCheckBox useLargeIconsCB = new JCheckBox("Use large icons"); //$NON-NLS-1$
				useLargeIconsCB.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						useLargeIcons = useLargeIconsCB.isSelected();
						list.setPrototypeCellValue(model.get(0)); //$NON-NLS-1$
						list.revalidate();
						list.repaint();
					}
				});

				// componentPanel shows the component (view) from the UIElement
				componentPanel = new JPanel(new BorderLayout());
				componentPanel.setPreferredSize(new Dimension(200, 100));
				componentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				// Place the components in a panel that will be added to our frame's content pane
				JPanel panel = new JPanel(new GridBagLayout());
				panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		        GridBagConstraints c = new GridBagConstraints();
		        c.anchor = GridBagConstraints.NORTHWEST;
		        c.insets = new Insets(2, 4, 0, 4);
		        c.weightx = 1.0;
		        c.gridwidth = 1;
		        c.gridheight = 1;
		        c.weightx = 1;
		        panel.add(useLargeIconsCB, c);
		        c.gridy = 1;
		        c.fill = GridBagConstraints.BOTH;
				panel.add(sp, c);
				c.weightx = 20;
				c.gridx = 1;
				panel.add(componentPanel, c);

				// add components to content pane and show the window
				frame.getContentPane().add(getToolBar(model.iterator(), listModel), BorderLayout.NORTH);
				frame.getContentPane().add(panel, BorderLayout.CENTER);

				frame.setSize(new Dimension(500, 300));
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Creates a JToolBar of toggle buttons using the given list of color items. The list
	 * (our model) is updated when a toggle button is pressed/un-pressed.
	 * @param elements TODO: document
	 * @param list TODO: document
	 * @return the populated toolbar
	 */
	protected static JToolBar getToolBar(final Iterator<UIElement> elements, final ArrayList<UIElement> list) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		while (elements.hasNext()) {
			final UIElement element = elements.next();
			final JToggleButton toggle = new JToggleButton(element.getSmallIcon());
			toolBar.add(toggle);
			toggle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					if (toggle.isSelected()) {
						list.add(element);
					} else {
						list.remove(element);
					}
				}
			});
		}
		return toolBar;
	}

}
