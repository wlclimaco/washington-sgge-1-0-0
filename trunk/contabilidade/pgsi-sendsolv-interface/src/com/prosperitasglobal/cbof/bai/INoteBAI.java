package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.cbof.model.request.NoteMaintenanceRequest;
import com.prosperitasglobal.cbof.model.response.NoteResponse;

/**
 * The Interface INoteBAI.
 */
public interface INoteBAI
{
	/**
	 * Insert note.
	 *
	 * @param request the request
	 * @return the note response
	 */
	public NoteResponse insertNote(NoteMaintenanceRequest request);

	/**
	 * Insert organization.
	 *
	 * @param request the request
	 * @return the organization paged response
	 */
	public NoteResponse updateNote(NoteMaintenanceRequest request);

	/**
	 * Insert organization.
	 *
	 * @param request the request
	 * @return the organization paged response
	 */
	public NoteResponse deleteNote(NoteMaintenanceRequest request);
}
