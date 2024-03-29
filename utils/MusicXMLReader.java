package utils;

import java.io.IOException;
import java.io.Serializable;

import model.Tune;
import model.book.Book;
import model.book.BookEntry;
import model.collections.AllBooks;
import model.collections.AllRecordings;
import model.collections.AllTunes;
import model.recording.Recording;
import model.recording.RecordingEntry;

import org.eclipse.jdt.annotation.Nullable;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * A SAX parser for the music organization XML.
 *
 * @author Jonathan Lovelace
 *
 */
public class MusicXMLReader extends DefaultHandler implements Serializable {
	/**
	 * Version UID for serialization.
	 */
	private static final long serialVersionUID = 5253135372738553184L;
	/**
	 * The recording we're currently filling.
	 */
	@Nullable private transient Recording currentRecording;
	/**
	 * The book we're currently filling.
	 */
	@Nullable private transient Book currentBook;

	/**
	 * Constructor.
	 *
	 * @param filename
	 *            Filename of the XML file that contains a map.
	 * @throws SAXException
	 *             when thrown by factory method
	 * @throws IOException
	 *             when the parser encounters an I/O error
	 */
	@SuppressWarnings("null")
	public MusicXMLReader(final String filename) throws SAXException, IOException {
		super();
		parserSetContentHandler(XMLReaderFactory.createXMLReader()).parse(filename);
	}

	/**
	 * Avoids having an XMLReader local variable.
	 *
	 * @param reader
	 *            An XMLReader
	 * @return the XMLReader, with its ContentHandler having been set to this.
	 */
	private XMLReader parserSetContentHandler(final XMLReader reader) {
		reader.setContentHandler(this);
		return reader;
	}

	/**
	 * Called when we've finished parsing an element.
	 *
	 * @param namespaceURI
	 *            the Namespace URI
	 * @param localName
	 *            the local name
	 * @param qualifiedName
	 *            the qualified XML name
	 * @throws SAXException
	 *             required by spec
	 */
	@Override
	public void endElement(@Nullable final String namespaceURI, @Nullable final String localName,
			@Nullable final String qualifiedName) throws SAXException {
		if ("recording".equals(localName)) {
			currentRecording = null; // NOPMD
		} else if ("book".equals(localName)) {
			currentBook = null; // NOPMD
		}
	}

	/**
	 * Start parsing an element.
	 *
	 * @param namespaceURI
	 *            the namespace URI of the element
	 * @param localName
	 *            the local name of the element
	 * @param qualifiedName
	 *            the qualified name of the element
	 * @param atts
	 *            attributes of the element
	 * @throws SAXException
	 *             required by spec
	 */
	@Override
	public void startElement(@Nullable final String namespaceURI,
			@Nullable final String localName,
			@Nullable final String qualifiedName,
			@Nullable final Attributes atts) throws SAXException {
		if (atts == null) {
			throw new IllegalArgumentException("Can't work with null Attributes");
		} else if ("tune".equals(localName)) {
			parseTune(atts);
		} else if ("book".equals(localName)) {
			currentBook = parseBook(atts);
		} else if ("book_entry".equals(localName)) {
			final Book cbk = currentBook;
			if (cbk == null) {
				throw new SAXException(new IllegalStateException(
						"book_entry tag outside of any book"));
			} else {
				cbk.addEntry(parseBookEntry(atts));
			}
		} else if ("recording".equals(localName)) {
			currentRecording = parseRecording(atts);
		} else if ("record_entry".equals(localName)) {
			final Recording crec = currentRecording;
			if (crec == null) {
				throw new SAXException(new IllegalStateException(
						"record_entry tag outside of any recording"));
			} else {
				crec.addEntry(parseRecordEntry(atts));
			}
		}
	}

	/**
	 * Parse a tune.
	 *
	 * @param atts
	 *            The XML tag's attributes
	 */
	private static void parseTune(final Attributes atts) {
		final Tune tune = new Tune();
		final String name = atts.getValue("name");
		tune.setName(name == null ? "" : name);
		final String composer = atts.getValue("composer");
		tune.setComposer(composer == null ? "" : composer);
		final String time = atts.getValue("time");
		tune.setTimeSignature(time == null ? "" : time);
		AllTunes.ALL_TUNES.add(Integer.parseInt(atts.getValue("id")), tune);
	}

	/**
	 * Parse a book.
	 *
	 * @param atts
	 *            The XML tag's attributes
	 * @return the book
	 */
	private static Book parseBook(final Attributes atts) {
		return parseBook(new Book(), atts);
	}
	/**
	 * Parse a book.
	 * @param book The book to assign the values in the tag
	 * @param atts the XML tag's attributes
	 * @return the book, with its data initialized.
	 */
	private static Book parseBook(final Book book, final Attributes atts) {
		final String title = atts.getValue("title");
		book.setTitle(title == null ? "" : title);
		AllBooks.ALL_BOOKS.add(Integer.parseInt(atts.getValue("id")), book);
		return book;
	}

	/**
	 * Parse a book entry.
	 *
	 * @param atts
	 *            the XML tag's attributes
	 * @return the entry
	 */
	private static BookEntry parseBookEntry(final Attributes atts) {
		final BookEntry entry = new BookEntry(AllTunes.ALL_TUNES.get(Integer.parseInt(atts
				.getValue("tune"))));
		final String key = atts.getValue("key");
		entry.setKey(key == null ? "" : key);
		entry.setPage(Integer.parseInt(atts.getValue("page")));
		return entry;
	}

	/**
	 * Parse a recording.
	 *
	 * @param atts
	 *            the XML tag's attributes
	 * @return the recording
	 */
	private static Recording parseRecording(final Attributes atts) {
		final Recording record = new Recording();
		final String title = atts.getValue("title");
		record.setTitle(title == null ? "" : title);
		AllRecordings.ALL_RECORDINGS.add(Integer.parseInt(atts.getValue("id")), record);
		return record;
	}

	/**
	 * Parse a recording entry.
	 *
	 * @param atts
	 *            the XML tag's attributes
	 * @return the recording entry
	 */
	private static RecordingEntry parseRecordEntry(final Attributes atts) {
		final RecordingEntry entry = new RecordingEntry();
		entry.setTune(AllTunes.ALL_TUNES.get(Integer.parseInt(atts.getValue("tune"))));
		entry.setTrack(Integer.parseInt(atts.getValue("track")));
		return entry;
	}
}
