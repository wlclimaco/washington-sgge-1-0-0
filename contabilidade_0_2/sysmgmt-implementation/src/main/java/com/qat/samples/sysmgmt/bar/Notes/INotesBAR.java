package com.qat.samples.sysmgmt.bar.Notes;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.util.model.Note;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

/**
 * The Interface NotesBAR.. (Data Access Component - DAC)
 */
public interface INotesBAR
{

	/**
	 * Fetch notes by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Note fetchNotesById(FetchByIdRequest request);

	/**
* Insert notes.
*
* @param notes the notes
*
* @return the internal response
*/
	public InternalResponse insertNotes(Note notes);

	/**
* Update notes.
*
* @param notes the notes
*
* @return the internal response
*/
	public InternalResponse updateNotes(Note notes);

	/**
* Delete notes.
*
* @param notes the notes
*
* @return the internal response
*/
	public InternalResponse deleteNotesById(Note notes);

	/**
* Delete all notess.
*
* @return the internal response
*/
	public InternalResponse deleteAllNotess();

	/**
* Fetch all notess.
*
* @return the list< notes>
*/
	public InternalResultsResponse<Note> fetchAllNotess(Note  notes);

	/**
* Fetch notess by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Note> fetchNotessByRequest(PagedInquiryRequest request);

}
