package com.sensus.dm.common.device.bcf;

import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.device.model.response.NoteResponse;
import com.sensus.dm.common.util.AbstractMockBase;

/**
 * The Class MockNoteBCF.
 */
public class MockNoteBCF extends AbstractMockBase implements INoteBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#insertMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse insertMeterNote(NoteRequest noteRequest)
	{
		return new NoteResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#deleteMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse deleteMeterNote(NoteRequest request)
	{
		return new NoteResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.INoteBCF#fetchDeviceNotes(com.sensus.dm.common.device.model.request.NoteRequest)
	 */
	@Override
	public NoteResponse fetchDeviceNotes(NoteRequest request)
	{
		return new NoteResponse();
	}

}
