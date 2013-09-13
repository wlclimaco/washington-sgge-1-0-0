package com.sensus.dm.common.device.bcf;

import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.device.model.response.NoteResponse;

/**
 * The Interface INoteBCF.
 * 
 * @author QAT Global.
 */
public interface INoteBCF
{

	/**
	 * Insert meter note.
	 * 
	 * @param noteRequest the note request
	 * @return the note response
	 */
	NoteResponse insertMeterNote(NoteRequest noteRequest);

	/**
	 * Delete meter note.
	 * 
	 * @param request the request
	 * @return the note response
	 */
	NoteResponse deleteMeterNote(NoteRequest request);

	/**
	 * Fetch device notes.
	 * 
	 * @param request the request
	 * @return the note response
	 */
	NoteResponse fetchDeviceNotes(NoteRequest request);

}
