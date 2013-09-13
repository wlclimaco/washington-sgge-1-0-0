package com.sensus.dm.common.device.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Note;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;

/**
 * The Class NoteRequest.
 * 
 * @author QAT Global.
 */
public class NoteRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The notes. */
	private List<Note> notes;

	/**
	 * Instantiates a new note request.
	 */
	public NoteRequest()
	{
	}

	/**
	 * Instantiates a new note request.
	 * 
	 * @param note the note
	 */
	public NoteRequest(Note note)
	{
		addNote(note);
	}

	/**
	 * Instantiates a new note request.
	 * 
	 * @param note the note
	 * @param sortExpression the sort expression
	 */
	public NoteRequest(Note note, SortExpression sortExpression)
	{
		this(note);
		addSortExpressions(sortExpression);
	}

	/**
	 * Instantiates a new note request.
	 * 
	 * @param notesParam the notes param
	 */
	public NoteRequest(List<Note> notesParam)
	{
		setNotes(notesParam);
	}

	/**
	 * Instantiates a new note request.
	 * 
	 * @param userContext the user context
	 */
	public NoteRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the notes.
	 * 
	 * @return the notes
	 */
	public List<Note> getNotes()
	{
		return notes;
	}

	/**
	 * Gets the first note.
	 * 
	 * @return the first note
	 */
	public Note getFirstNote()
	{
		if (getNotes() != null && !getNotes().isEmpty())
		{
			return getNotes().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes the new notes
	 */
	public void setNotes(List<Note> notes)
	{
		this.notes = notes;
	}

	/**
	 * Adds the note.
	 * 
	 * @param note the note
	 */
	public void addNote(Note note)
	{
		if (getNotes() == null)
		{
			setNotes(new ArrayList<Note>());
		}

		getNotes().add(note);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "NoteRequest [getNotes()=" + getNotes() + ", getFirstNote()=" + getFirstNote() + ", toString()="
				+ super.toString() + "]";
	}

}
