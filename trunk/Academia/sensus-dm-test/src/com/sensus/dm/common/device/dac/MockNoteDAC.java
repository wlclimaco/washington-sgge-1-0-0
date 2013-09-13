package com.sensus.dm.common.device.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.cbof.model.Note;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockNoteDAC.
 * 
 * @author QAT Global.
 */
public class MockNoteDAC extends AbstractMockBase implements INoteDAC
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.INoteDAC#insertMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResultsResponse<Note> insertMeterNote(NoteRequest request)
	{
		return noteResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.INoteDAC#deleteMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResponse deleteMeterNote(NoteRequest request)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.INoteDAC#fetchDeviceNotes(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResultsResponse<Note> fetchDeviceNotes(NoteRequest request)
	{
		return new InternalResultsResponse<Note>();
	}

	/**
	 * Note results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Note> noteResultsResponse()
	{

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<Note>(TestBaseUtil.createNote());
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		return new InternalResultsResponse<Note>();
	}

}
