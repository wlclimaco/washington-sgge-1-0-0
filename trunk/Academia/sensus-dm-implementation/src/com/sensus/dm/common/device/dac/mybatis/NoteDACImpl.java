package com.sensus.dm.common.device.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.dac.INoteDAC;
import com.sensus.cbof.model.Note;
import com.sensus.dm.common.device.model.request.NoteRequest;

/**
 * The Class NoteDACImpl.
 * 
 * @author QAT Global.
 */
public class NoteDACImpl extends SqlSessionDaoSupport implements INoteDAC
{
	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant DEVICE_MAP. */
	private static final String NOTE_MAP = "NoteMap.";

	/** The Constant INSERT_METER_NOTES. */
	private static final String INSERT_METER_NOTES = NOTE_MAP
			+ "insertMeterNotes";

	/** The Constant DELETE_METER_NOTES. */
	private static final String DELETE_METER_NOTES = NOTE_MAP
			+ "deleteMeterNotes";

	/** The Constant METER_NOTE_ID. */
	private static final String METER_NOTE_ID = "meter_note_id";

	/** The Constant NOTE. */
	private static final String NOTE = "note";

	/** The Constant NOTE_ID. */
	private static final String NOTE_ID = "note_id";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant FETCH_DEVICE_NOTES. */
	private static final String FETCH_DEVICE_NOTES = NOTE_MAP
			+ "fetchDeviceNotes";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.INoteDAC#insertMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResultsResponse<Note> insertMeterNote(NoteRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(
				PARAMSIZE4);
		paramMap.put(FLEXNET_ID, request.getNotes().get(0).getFlexNetId());
		paramMap.put(NOTE, request.getNotes().get(0).getText());
		paramMap.put(CREATE_USER, request.getUserContext().getUserId());

		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(),
				INSERT_METER_NOTES, paramMap);

		request.getNotes()
				.get(0)
				.setId(DMConvertUtil.checkResult(response, paramMap,
						METER_NOTE_ID));
		response.addResult(request.getNotes().get(0));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.INoteDAC#deleteMeterNote(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@Override
	public InternalResponse deleteMeterNote(NoteRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(
				PARAMSIZE2);
		paramMap.put(NOTE_ID, request.getNotes().get(0).getId());

		Integer result = (Integer)SensusMyBatisDacHelper.doQueryForObject(
				getSqlSession(), DELETE_METER_NOTES, paramMap);

		InternalResponse response = new InternalResponse();

		if (result == 0)
		{
			response.setStatus(Status.NoRowsRemovedError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.dac.INoteDAC#fetchDeviceNotes(com.sensus.dm.elec.device.model.request.NoteRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Note> fetchDeviceNotes(NoteRequest request)
	{
		InternalResultsResponse<Note> response = new InternalResultsResponse<Note>();

		response.getResultsList().addAll(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(),
						FETCH_DEVICE_NOTES, request.getFirstNote()));

		return response;
	}
}
