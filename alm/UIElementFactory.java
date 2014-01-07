package alm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Factory to create UIElements for testing.
 * @author Phil Herold
 */
public class UIElementFactory {

	/**
	 * This class shows a panel of the specified color as it's view.
	 */
	private static class ColorUIElement extends JPanel implements UIElement {
		private Color color;
		private String colorName;
		private Icon smallIcon;
		private Icon largeIcon;
		public ColorUIElement(final String colorName, final Color color) {
			this.color = color;
			this.colorName = colorName;
		}
		@Override
		public Object getItem() {
			return this;
		}

		@Override
		public String getDescription() {
			StringBuffer buf = new StringBuffer(colorName);
			buf.append(": "); //$NON-NLS-1$
			buf.append(Integer.toHexString(color.getRGB()));
			return buf.toString();
		}

		@Override
		public Icon getSmallIcon() {
			if (smallIcon == null) {
				smallIcon = new ImageIcon() {
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
				};
			}
			return smallIcon;
		}

		@Override
		public Icon getLargeIcon() {
			if (largeIcon == null) {
				largeIcon = new ImageIcon() {
					@Override
					public int getIconHeight() {
						return 24;
					}
					@Override
					public int getIconWidth() {
						return 24;
					}
					@Override
					public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
						g.setColor(color);
						g.fillRect(x, y, getIconWidth(), getIconHeight());
						g.setColor(Color.BLACK);
						g.drawRect(x, y, getIconWidth(), getIconHeight());
					}
				};
			}
			return largeIcon;
		}

		@Override
		public JComponent getComponent() {
			return this;
		}

		@Override
		public void paintComponent(final Graphics g) {
			super.paintComponent(g);
			g.setColor(color);
			Dimension size = getSize();
			g.fillRect(0, 0, size.width, size.height);
		}

		@Override
		public String toString() {
			return colorName;
		}
	}

	/**
	 * Private constructor
	 */
	private UIElementFactory() {
	}

	public static void addElementsToList(final List<UIElement> list) {
		list.add(new ColorUIElement("Red", Color.RED));         //$NON-NLS-1$
		list.add(new ColorUIElement("Yellow", Color.YELLOW));   //$NON-NLS-1$
		list.add(new ColorUIElement("Blue", Color.BLUE));       //$NON-NLS-1$
		list.add(new ColorUIElement("Green", Color.GREEN));     //$NON-NLS-1$
		list.add(new ColorUIElement("Cyan", Color.CYAN));       //$NON-NLS-1$
		list.add(new ColorUIElement("Magenta", Color.MAGENTA)); //$NON-NLS-1$
		list.add(new ColorUIElement("Orange", Color.ORANGE));   //$NON-NLS-1$
		list.add(new ColorUIElement("Pink", Color.PINK));       //$NON-NLS-1$
		list.add(new ColorUIElement("White", Color.WHITE));     //$NON-NLS-1$
		list.add(new ColorUIElement("Gray", Color.GRAY));       //$NON-NLS-1$
		list.add(new ColorUIElement("Black", Color.BLACK));     //$NON-NLS-1$
	}
}
