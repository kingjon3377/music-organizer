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

import org.eclipse.jdt.annotation.Nullable;

/**
 * Factory to create UIElements for testing.
 * @author Phil Herold
 */
public final class UIElementFactory {

	/**
	 * This class shows a panel of the specified color as it's view.
	 */
	private static class ColorUIElement extends JPanel implements UIElement {
		/**
		 * The element's color.
		 */
		protected Color color;
		/**
		 * The name of the color.
		 */
		private String colorName;
		/**
		 * The small icon of the element.
		 */
		@Nullable private Icon smallIcon;
		/**
		 * The large icon of the element.
		 */
		@Nullable private Icon largeIcon;
		/**
		 * @param name the name of the color
		 * @param colr the color. "Nullable" to avoid warnings when called with Color class constants
		 */
		protected ColorUIElement(final String name, @Nullable final Color colr) {
			assert colr != null;
			this.color = colr;
			this.colorName = name;
		}
		/**
		 * @return the element
		 */
		@Override
		public Object getItem() {
			return this;
		}
		/**
		 * @return a description of the element
		 */
		@Override
		public String getDescription() {
			StringBuilder buf = new StringBuilder(colorName);
			buf.append(": ");
			buf.append(Integer.toHexString(color.getRGB()));
			final String retval = buf.toString();
			assert retval != null;
			return retval;
		}
		/**
		 * The size of a small icon.
		 */
		protected static final int SMALL_ICON_SIZE = 12;
		/**
		 * @return a small icon for the element
		 */
		@Override
		public Icon getSmallIcon() {
			if (smallIcon == null) {
				smallIcon = new ImageIcon() {
					@Override
					public int getIconHeight() {
						return SMALL_ICON_SIZE;
					}
					@Override
					public int getIconWidth() {
						return SMALL_ICON_SIZE;
					}
					// ESCA-JAVA0143: The superclass is this way, so we have to be too.
					@Override
					public synchronized void paintIcon(@Nullable final Component cmp,
							@Nullable final Graphics pen, final int xCoord,
							final int yCoord) {
						if (pen == null) {
							throw new IllegalStateException("Null graphics context");
						}
						pen.setColor(color);
						pen.fillRect(xCoord, yCoord, getIconWidth(), getIconHeight());
						pen.setColor(Color.BLACK);
						pen.drawRect(xCoord, yCoord, getIconWidth(), getIconHeight());
					}
				};
			}
			assert smallIcon != null;
			return smallIcon;
		}
		/**
		 * The size of a large icon.
		 */
		protected static final int LARGE_ICON_SIZE = 24;
		/**
		 * @return a large icon for the element
		 */
		@Override
		public Icon getLargeIcon() {
			if (largeIcon == null) {
				largeIcon = new ImageIcon() {
					@Override
					public int getIconHeight() {
						return LARGE_ICON_SIZE;
					}
					@Override
					public int getIconWidth() {
						return LARGE_ICON_SIZE;
					}
					// ESCA-JAVA0143: The superclass is this way, so we have to be too.
					@Override
					public synchronized void paintIcon(@Nullable final Component cmp,
							@Nullable final Graphics pen, final int xCoord,
							final int yCoord) {
						if (pen == null) {
							throw new IllegalStateException("Null graphics context");
						}
						pen.setColor(color);
						pen.fillRect(xCoord, yCoord, getIconWidth(), getIconHeight());
						pen.setColor(Color.BLACK);
						pen.drawRect(xCoord, yCoord, getIconWidth(), getIconHeight());
					}
				};
			}
			assert largeIcon != null;
			return largeIcon;
		}
		/**
		 * @return this
		 */
		@Override
		public JComponent getComponent() {
			return this;
		}
		/**
		 * @param pen the graphics context
		 */
		@Override
		public void paintComponent(@Nullable final Graphics pen) {
			if (pen == null) {
				throw new IllegalStateException("Null graphics context");
			}
			super.paintComponent(pen);
			pen.setColor(color);
			Dimension size = getSize();
			pen.fillRect(0, 0, size.width, size.height);
		}
		/**
		 * @return the name of the color
		 */
		@Override
		public String toString() {
			return colorName;
		}
	}

	/**
	 * Private constructor.
	 */
	private UIElementFactory() {
	}
	/**
	 * @param list the list to add elements to
	 */
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
