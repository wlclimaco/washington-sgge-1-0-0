package com.sensus.dm.common.device.bcl;

import java.math.BigInteger;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.cbof.model.Note;
import com.sensus.dm.common.device.model.request.NoteRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

public class MockNoteBCL extends AbstractMockBase implements INoteBCL
{

	/** The Constant NOTE_ID. */
	private static final String NOTE_ID = "1";

	/** The Constant NOTE_TEXT. */
	private static final String NOTE_TEXT = "Example note text";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#insertMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResultsResponse<Note> insertMeterNote(NoteRequest request)
	{
		return internalResulResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#deleteMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResponse deleteMeterNote(NoteRequest request)
	{
		return internalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.INoteBCL#fetchDeviceNotes(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Note> fetchDeviceNotes(NoteRequest request)
	{
		switch (getSituationsEnum())
		{
			case SUCCESS:
				return internalResulResponse();
			case ERROR:
				return getInternalResultsResponseError();
			default:
				return null;
		}
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Note> internalResulResponse()
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Note note = new Note(new BigInteger(NOTE_ID));
			note.setText(NOTE_TEXT);

			return new InternalResultsResponse<Note>(note);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResultsResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return new InternalResultsResponse<Note>();
	}

	/**
	 * Internal response.
	 *
	 * @return the internal response
	 */
	private InternalResponse internalResponse()
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// success response
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}
}
