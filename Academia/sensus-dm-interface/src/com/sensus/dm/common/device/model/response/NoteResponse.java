package com.sensus.dm.common.device.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.cbof.model.Note;

/**
 * The Class NoteResponse.
 * 
 * @author QAT Global.
 */
public class NoteResponse extends Response
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The notes. */
	private List<Note> notes;

	/** The file name. */
	private String fileName;

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
		if ((getNotes() != null) && !getNotes().isEmpty())
		{
			return getNotes().get(FIRST);
		}

		return null;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param noteList the new notes
	 */
	public void setNotes(List<Note> noteList)
	{
		notes = noteList;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NoteResponse [getNotes()=" + getNotes() + ", getFirstNote()=" + getFirstNote() + ", getFileName()="
				+ getFileName() + ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()="
				+ getMessageList() + ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()="
				+ isOperationSuccess() + "]";
	}
}
