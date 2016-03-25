package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.util.Note;

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
	public Integer insertNote(Note note, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete note.
	 * 
	 * @param note the note
	 * @return the internal response
	 */
	public Integer deleteNote(Note note, InternalResultsResponse<?> response);

	/**
	 * Update note.
	 * 
	 * @param note the note
	 * @return the internal results response< note>
	 */
	public Integer updateNote(Note note, InternalResultsResponse<?> response);

	public InternalResultsResponse<Note> fetchNoteByRequest(PagedInquiryRequest request);

}
