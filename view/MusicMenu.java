package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.xml.sax.SAXException;

import utils.MusicXMLReader;
import utils.XMLWriter;

/**
 * A menu bar for the Save, Load, and Exit commands.
 * 
 * @author Jonathan Lovelace
 */
public class MusicMenu extends JMenuBar implements ActionListener {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = -2123155356163135242L;
	/**
	 * The file-chooser dialog.
	 */
	private static final JFileChooser FILE_CHOOSER = new JFileChooser();
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(MusicMenu.class.getName());

	/**
	 * Constructor.
	 */
	public MusicMenu() {
		super();
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(this);
		add(saveItem);
		JMenuItem loadItem = new JMenuItem("Load");
		loadItem.addActionListener(this);
		add(loadItem);
		add(new SearchMenu());
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		add(exitItem);
	}

	/**
	 * Handle menu item selections.
	 * 
	 * @param event
	 *            the event we're handling
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
		if ("Exit".equals(event.getActionCommand())) {
			MusicGUIDriver.DRIVER.dispose();
		} else if ("Save".equals(event.getActionCommand())) {
			if (FILE_CHOOSER.showSaveDialog(MusicGUIDriver.DRIVER) == JFileChooser.APPROVE_OPTION) {
				try {
					new XMLWriter(FILE_CHOOSER.getSelectedFile().getPath()).write();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE,
							"I/O error when trying to write to XML file", e);
				}
			}
		} else if ("Load".equals(event.getActionCommand())) {
			if (FILE_CHOOSER.showOpenDialog(MusicGUIDriver.DRIVER) == JFileChooser.APPROVE_OPTION) {
				try {
					new MusicXMLReader(FILE_CHOOSER.getSelectedFile().getPath());
				} catch (SAXException except) {
					LOGGER.log(Level.SEVERE,
							"XML parsing exception when trying to read from XML file",
							except);
				} catch (IOException except) {
					LOGGER.log(Level.SEVERE,
							"I/O error when trying to read from XML file", except);
				}
				MusicGUIDriver.DRIVER.repaint();
			}
		}
	}
}
