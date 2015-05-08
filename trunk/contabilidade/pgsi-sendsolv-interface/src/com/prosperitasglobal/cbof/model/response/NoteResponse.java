package com.prosperitasglobal.cbof.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.cbof.model.Note;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class NoteResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:05 AM
 */
public class NoteResponse extends InquiryResponse
{

	/** Attributes */
	private List<Note> noteList;

	/**
	 * The Constructor.
	 */
	public NoteResponse()
	{

	}

	/**
	 * Gets the note list.
	 *
	 * @return the note list
	 */
	public List<Note> getNoteList()
	{
		return noteList;
	}

	/**
	 * Sets the note list.
	 *
	 * @param noteList the note list
	 */
	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setNoteList((List<Note>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NoteResponse [getNoteList()=" + getNoteList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}