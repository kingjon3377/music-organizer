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

/**
 * Tests the ArrayListModel and ArrayListComboBoxModel classes.
 * @author Phil Herold
 */
public class ArrayListModelTest {
	/**
	 * Icon for rendering a color.
	 */
	private static class ColorIcon implements Icon {
		private Color color;
		public ColorIcon(final Color color) {
			this.color = color;
		}
		@Override
		public int getIconHeight() {
			return 12;
		}
		@Override
		public int getIconWidth() {
			return 12;
		}
		@Override
		public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
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
		private String name;
		private ColorIcon icon;
		public ColorItem(final String name, final Color color) {
			this.name = name;
			icon = new ColorIcon(color);
		}
		@Override
		public String toString() {
			return name;
		}
		public Icon getIcon() {
			return icon;
		}
	}

	/**
	 * CellRenderer for a ColorItem in a JList.
	 */
	private static class ColorItemCellRenderer extends DefaultListCellRenderer {
	    @Override
		public Component getListCellRendererComponent(final JList list,
				final Object value, final int index, final boolean isSelected,
				final boolean hasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list,
					value, index, isSelected, hasFocus);
			label.setIcon(((ColorItem) value).getIcon());
	        return label;
	    }
	}

	/**
	 * Entry point of application for JVM.
	 * @param args String array of arguments (not used)
	 */
	public static void main(final String[] args) {

		JFrame frame = new JFrame("ArrayListModel Test");        //$NON-NLS-1$
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// The data models.
		ArrayListModel<ColorItem> model = new ArrayListModel<ColorItem>();
		populateList(model);
		ArrayListComboBoxModel listModel = new ArrayListComboBoxModel();

		// JComboBox and JList views of the model
		JComboBox comboBox = new JComboBox(listModel);
		JList list = new JList(listModel);
		list.setCellRenderer(new ColorItemCellRenderer());
		JScrollPane sp = new JScrollPane(list);

		// Layout nicely in a panel of our window
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(2, 4, 0, 4);
        c.weightx = 1.0;
        c.gridwidth = c.gridheight = 1;
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
	 * @return ArrayListModel
	 */
	private static void populateList(final ArrayList<ColorItem> list) {
		list.add(new ColorItem("Red", Color.RED));         //$NON-NLS-1$
		list.add(new ColorItem("Yellow", Color.YELLOW));   //$NON-NLS-1$
		list.add(new ColorItem("Blue", Color.BLUE));       //$NON-NLS-1$
		list.add(new ColorItem("Green", Color.GREEN));     //$NON-NLS-1$
		list.add(new ColorItem("Cyan", Color.CYAN));       //$NON-NLS-1$
		list.add(new ColorItem("Magenta", Color.MAGENTA)); //$NON-NLS-1$
		list.add(new ColorItem("Orange", Color.ORANGE));   //$NON-NLS-1$
		list.add(new ColorItem("Pink", Color.PINK));       //$NON-NLS-1$
		list.add(new ColorItem("White", Color.WHITE));     //$NON-NLS-1$
		list.add(new ColorItem("Gray", Color.GRAY));       //$NON-NLS-1$
		list.add(new ColorItem("Black", Color.BLACK));     //$NON-NLS-1$
	}

	/**
	 * Creates a JToolBar of toggle buttons using the given list of color items. The list
	 * (our model) is updated when a toggle button is pressed/un-pressed.
	 * @param colorItems Iterator<ColorItem>
	 * @param list List
	 * @return
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
				public void actionPerformed(final ActionEvent e) {
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
