package com.prosperitasglobal.cbof.model.request;

import com.prosperitasglobal.cbof.model.Note;
import com.qat.framework.model.request.MaintenanceRequest;

/**
 * The Class NoteMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class NoteMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Note note;

	/**
	 * The Constructor.
	 */
	public NoteMaintenanceRequest()
	{

	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public Note getNote()
	{
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the note
	 */
	public void setNote(Note note)
	{
		this.note = note;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NoteMaintenanceRequest [getNote()=" + getNote() + ", getUserContext()=" + getUserContext() + "]";
	}

}