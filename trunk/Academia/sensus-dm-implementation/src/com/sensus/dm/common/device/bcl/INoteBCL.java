package com.sensus.dm.common.device.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.cbof.model.Note;
import com.sensus.dm.common.device.model.request.NoteRequest;

/**
 * The Interface INoteBCL.
 * 
 * @author QAT Global.
 */
public interface INoteBCL
{

	/**
	 * Insert meter note.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Note> insertMeterNote(NoteRequest request);

	/**
	 * Delete meter note.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	InternalResponse deleteMeterNote(NoteRequest request);

	/**
	 * Fetch device notes.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Note> fetchDeviceNotes(NoteRequest request);

}
