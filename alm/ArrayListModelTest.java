package alm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Tests the ArrayListModel and ArrayListComboBoxModel classes.
 * @author Phil Herold
 */
public class ArrayListModelTest {
	/**
	 * Icon for rendering a color.
	 */
	private static class ColorIcon implements Icon {
		/**
		 * The color of the icon.
		 */
		private Color color;
		/**
		 * @param colr the color of the icon.
		 */
		protected ColorIcon(final Color colr) {
			this.color = colr;
		}
		/**
		 * @return the height of the icon.
		 */
		@Override
		public int getIconHeight() {
			return 12;
		}
		/**
		 * @return the width of the icon
		 */
		@Override
		public int getIconWidth() {
			return 12;
		}
		/**
		 * @param c source of properties useful for painting
		 * @param g the drawing pen
		 * @param x the starting X coordinate
		 * @param y the starting Y coordinate
		 */
		@Override
		public void paintIcon(@Nullable final Component c,
				@Nullable final Graphics g, final int x, final int y) {
			if (g == null) {
				throw new IllegalStateException("Null graphics context");
			}
			g.setColor(color);
			g.fillRect(x, y, getIconWidth(), getIconHeight());
			g.setColor(Color.BLACK);
			g.drawRect(x, y, getIconWidth(), getIconHeight());
		}
	}

	/**
	 * This class will represent items in the data model (added to the collection).
	 */
	private static class ColorItem {
		/**
		 * The name of the item.
		 */
		private String name;
		/**
		 * The item's icon.
		 */
		private ColorIcon icon;
		/**
		 * @param itemName the name of the item.
		 * @param itemColor the color of the item. "Nullable" to avoid warnings when called with Color class constants
		 */
		protected ColorItem(final String itemName, @Nullable final Color itemColor) {
			assert itemColor != null;
			this.name = itemName;
			icon = new ColorIcon(itemColor);
		}
		/**
		 * @return the item's name
		 */
		@Override
		public String toString() {
			return name;
		}
		/**
		 * @return the item's icon
		 */
		public Icon getIcon() {
			return icon;
		}
	}

	/**
	 * CellRenderer for a ColorItem in a JList.
	 */
	private static class ColorItemCellRenderer extends DefaultListCellRenderer {
		/**
		 * Constructor,  needed to fix access warning.
		 */
		protected ColorItemCellRenderer() {
			// nothing
		}
		/**
		 * @param list the list being drawn
		 * @param value the current value in the list being drawn
		 * @param index its index
		 * @param isSelected whether it's selected
		 * @param hasFocus whether it has focus
		 * @return a component that can draw the item
		 */
		@Override
		public Component getListCellRendererComponent(
				@SuppressWarnings("rawtypes") @Nullable final JList list,
				@Nullable final Object value, final int index, final boolean isSelected,
				final boolean hasFocus) {
			if (list == null) {
				throw new IllegalStateException("Called with null list");
			}
			final JLabel label = (JLabel) super.getListCellRendererComponent(list,
					value, index, isSelected, hasFocus);
			if (label == null) {
				throw new IllegalStateException("Superclass renderer returned null");
			} else if (value instanceof ColorItem) {
				label.setIcon(((ColorItem) value).getIcon());
			}
	        return label;
	    }
	}

	/**
	 * Entry point of application for JVM.
	 * @param args String array of arguments (not used)
	 */
	public static void main(final String[] args) {
		JFrame frame = new JFrame("ArrayListModel Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// The data models.
		ArrayListModel<ColorItem> model = new ArrayListModel<>();
		populateList(model);
		ArrayListComboBoxModel<ColorItem> listModel = new ArrayListComboBoxModel<>();

		// JComboBox and JList views of the model
		JComboBox<ColorItem> comboBox = new JComboBox<>(listModel);
		JList<ColorItem> list = new JList<>(listModel);
		list.setCellRenderer(new ColorItemCellRenderer());
		JScrollPane sp = new JScrollPane(list);

		// Layout nicely in a panel of our window
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(2, 4, 0, 4);
        c.weightx = 1.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Combo box:"), c);        //$NON-NLS-1$
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboBox, c);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("List box:"), c);         //$NON-NLS-1$
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(sp, c);

		// add components to content pane and show the window
		frame.getContentPane().add(getToolBar(model.iterator(), listModel), BorderLayout.NORTH);
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.setSize(new Dimension(500, 300));
		frame.setVisible(true);
	}

	/**
	 * Helper method to populate the list of ColorItems for our data model.
	 * @param list the list to populate
	 */
	private static void populateList(final ArrayList<ColorItem> list) {
		list.add(new ColorItem("Red", Color.RED));
		list.add(new ColorItem("Yellow", Color.YELLOW));
		list.add(new ColorItem("Blue", Color.BLUE));
		list.add(new ColorItem("Green", Color.GREEN));
		list.add(new ColorItem("Cyan", Color.CYAN));
		list.add(new ColorItem("Magenta", Color.MAGENTA));
		list.add(new ColorItem("Orange", Color.ORANGE));
		list.add(new ColorItem("Pink", Color.PINK));
		list.add(new ColorItem("White", Color.WHITE));
		list.add(new ColorItem("Gray", Color.GRAY));
		list.add(new ColorItem("Black", Color.BLACK));
	}

	/**
	 * Creates a JToolBar of toggle buttons using the given list of color items. The list
	 * (our model) is updated when a toggle button is pressed/un-pressed.
	 * @param colorItems TODO: document
	 * @param list TODO: document
	 * @return the populated toolbar
	 */
	private static JToolBar getToolBar(final Iterator<ColorItem> colorItems, final List<ColorItem> list) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		while (colorItems.hasNext()) {
			final ColorItem item = colorItems.next();
			final JToggleButton toggle = new JToggleButton(item.getIcon());
			toolBar.add(toggle);
			toggle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(@Nullable final ActionEvent e) {
					if (toggle.isSelected()) {
						list.add(item);
					} else {
						list.remove(item);
					}
				}
			});
		}
		return toolBar;
	}

}
