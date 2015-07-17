package com.prosperitasglobal.cbof.dac;

import com.prosperitasglobal.cbof.model.Note;
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
	 * Insert note.
	 *
	 * @param note the note
	 * @return the internal results response< note>
	 */
	public Integer insertNote(Note note);

	/**
	 * Delete note.
	 *
	 * @param note the note
	 * @return the internal response
	 */
	public Integer deleteNote(Note note);

	/**
	 * Update note.
	 *
	 * @param note the note
	 * @return the internal results response< note>
	 */
	public Integer updateNote(Note note);

}
