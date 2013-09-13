package com.sensus.dm.controller.note.unittest;

import com.sensus.dm.common.device.bcf.INoteBCF;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.device.model.response.NoteResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;

/**
 * The Class NoteBCFMockImpl.
 */
public class NoteBCFMockImpl extends BaseMockImpl implements INoteBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#insertMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse insertMeterNote(NoteRequest noteRequest)
	{
		NoteResponse noteResponse = new NoteResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return noteResponse;
		}

		return (NoteResponse)testOtherDefaultModes(noteResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#deleteMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse deleteMeterNote(NoteRequest request)
	{
		NoteResponse noteResponse = new NoteResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return noteResponse;
		}

		return (NoteResponse)testOtherDefaultModes(noteResponse);
	}

	@Override
	public NoteResponse fetchDeviceNotes(NoteRequest noteRequest)
	{
		NoteResponse noteResponse = new NoteResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			noteResponse.setNotes(noteRequest.getNotes());
			return noteResponse;
		}

		return (NoteResponse)testOtherDefaultModes(noteResponse);
	}

}