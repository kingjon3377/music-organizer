package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Tune;
import model.book.Book;
import model.book.BookEntry;
import model.collections.AllBooks;
import model.collections.AllRecordings;
import model.collections.AllTunes;
import model.recording.Recording;
import model.recording.RecordingEntry;

/**
 * A class to write objects to XML.
 * @author Jonathan Lovelace
 */
public class XMLWriter {
	/**
	 * What we'll delegate real I/O to.
	 */
	private final PrintWriter writer; // NOPMD
	/**
	 * Constructor.
	 * @param filename the file to write to
	 * @throws IOException Thrown by FileWriter constructor.
	 */
	public XMLWriter(final String filename) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
	}
	/**
	 * Write the XML document.
	 */
	public void write() {
		writer.println("<music>");
		writer.println("\t<tunes>");
		for (Tune tune : AllTunes.ALL_TUNES) {
			writeTune(tune);
		}
		writer.println("\t</tunes>");
		writer.println("\t<books>");
		for (Book book : AllBooks.ALL_BOOKS) {
			writeBook(book);
		}
		writer.println("\t</books>");
		writer.println("\t<records>");
		for (Recording record : AllRecordings.ALL_RECORDINGS) {
			writeRecording(record);
		}
		writer.println("\t</records>");
		writer.println("</music>");
		writer.close();
	}
	/**
	 * Write a tune to XML.
	 * @param tune the tune to write
	 */
	private void writeTune(final Tune tune) {
		writer.print("\t\t<tune name=\"");
		writer.print(tune.getName());
		writer.print("\" composer=\"");
		writer.print(tune.getComposer());
		if (!"".equals(tune.getTimeSignature()) && tune.getTimeSignature() != null) {
			writer.print("\" time=\"");
			writer.print(tune.getTimeSignature());
		}
		writer.print("\" id=\"");
		writer.print(AllTunes.ALL_TUNES.indexOf(tune));
		writer.println("\" />");
	}
	/**
	 * Write a book to XML.
	 * @param book the book to write
	 */
	private void writeBook(final Book book) {
		writer.print("\t\t<book title=\"");
		writer.print(book.getTitle());
		writer.print("\" id=\"");
		writer.print(AllBooks.ALL_BOOKS.indexOf(book));
		writer.println("\">");
		for (BookEntry entry : book.getEntries()) {
			writeBookEntry(entry);
		}
		writer.println("\t\t</book>");
	}
	/**
	 * Write a Recording to XML.
	 * @param record the recording to write
	 */
	private void writeRecording(final Recording record) {
		writer.print("\t\t<recording title=\"");
		writer.print(record.getTitle());
		writer.print("\" id=\"");
		writer.print(AllRecordings.ALL_RECORDINGS.indexOf(record));
		writer.println("\">");
		for (RecordingEntry entry : record.getEntries()) {
			writeRecordingEntry(entry);
		}
		writer.println("\t\t</recording>");
	}
	/**
	 * Write a BookEntry to XML.
	 * @param entry the entry to write
	 */
	private void writeBookEntry(final BookEntry entry) {
		writer.print("\t\t\t<book_entry tune=\"");
		writer.print(AllTunes.ALL_TUNES.indexOf(entry.getTune()));
		writer.print("\" page=\"");
		writer.print(entry.getPage());
		writer.print("\" key=\"");
		writer.print(entry.getKey());
		writer.println("\" />");
	}
	/**
	 * Write a RecordingEntry to XML.
	 * @param entry the entry to write
	 */
	private void writeRecordingEntry(final RecordingEntry entry) {
		writer.print("\t\t\t<record_entry tune=\"");
		writer.print(AllTunes.ALL_TUNES.indexOf(entry.getTune()));
		writer.print("\" track=\"");
		writer.print(entry.getTrack());
		writer.println("\" />");
	}
}
