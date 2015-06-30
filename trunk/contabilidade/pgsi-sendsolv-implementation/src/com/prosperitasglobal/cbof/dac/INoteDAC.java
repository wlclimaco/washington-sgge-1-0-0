package com.prosperitasglobal.cbof.dac;

import java.util.List;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Note;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface INoteDAC.
 */
public interface INoteDAC
{
	/**
	 * Fetch note by id.
	 *
	 * @param id the id
	 * @return the internal results response< note>
	 */
	public InternalResultsResponse<Note> fetchNoteById(Integer id);

	/**
	 * Fetch note by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< note>
	 */
	public InternalResultsResponse<Note> fetchNoteByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Insert note.
	 *
	 * @param note the note
	 * @return the internal results response< note>
	 */
	public InternalResultsResponse<Note> insertNote(Note note);

	/**
	 * Delete note.
	 *
	 * @param note the note
	 * @return the internal response
	 */
	public InternalResponse deleteNote(Note note);

	/**
	 * Update note.
	 *
	 * @param note the note
	 * @return the internal results response< note>
	 */
	public InternalResultsResponse<Note> updateNote(Note note);

	/**
	 * Maintain note associations.
	 *
	 * @param noteList the note list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	@SuppressWarnings("rawtypes")
	public Integer maintainNoteAssociations(List<Note> noteList, Integer parentId, String associateStatement,
			InternalResultsResponse response);
}
