package com.sensus.dm.common.device.bcl.impl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.bcl.INoteBCL;
import com.sensus.dm.common.device.dac.INoteDAC;
import com.sensus.cbof.model.Note;
import com.sensus.dm.common.device.model.request.NoteRequest;

/**
 * The Class NoteBCLImpl.
 * 
 * @author QAT Global.
 */
public class NoteBCLImpl implements INoteBCL
{

	/** The device dac. */
	private INoteDAC noteDAC;

	/**
	 * Gets the note dac.
	 * 
	 * @return the note dac
	 */
	public INoteDAC getNoteDAC()
	{
		return noteDAC;
	}

	/**
	 * Sets the note dac.
	 * 
	 * @param noteDAC the new note dac
	 */
	public void setNoteDAC(INoteDAC noteDAC)
	{
		this.noteDAC = noteDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.INoteBCL#insertMeterNote(com.sensus.dm.common.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResultsResponse<Note> insertMeterNote(NoteRequest request)
	{
		return getNoteDAC().insertMeterNote(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.INoteBCL#deleteMeterNote(com.sensus.dm.common.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResponse deleteMeterNote(NoteRequest request)
	{
		return getNoteDAC().deleteMeterNote(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.INoteBCL#fetchDeviceNotes(com.sensus.dm.common.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResultsResponse<Note> fetchDeviceNotes(NoteRequest request)
	{
		return getNoteDAC().fetchDeviceNotes(request);
	}

}
