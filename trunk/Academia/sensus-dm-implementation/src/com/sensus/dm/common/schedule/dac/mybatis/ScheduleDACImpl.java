package com.sensus.dm.common.schedule.dac.mybatis;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.Message;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.schedule.dac.IScheduleDAC;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;

/**
 * The Class ScheduleDACImpl.
 * 
 * @author QAT Global.
 */
public class ScheduleDACImpl extends SqlSessionDaoSupport implements IScheduleDAC
{
	/** The Constant PARAMSIZE1. */
	private static final int PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE2. */
	private static final int PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE4. */
	private static final int PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final int PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final int PARAMSIZE6 = 6;

	/** The Constant ACTION_ID. */
	private static final String ACTION_ID = "action_instance_id";

	/** The Constant CREATE_DATE. */
	private static final String CREATE_DATE = "create_date";

	/** The Constant STATUS. */
	private static final String STATUS = "status";

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "description";

	/** The Constant END_DATE. */
	private static final String END_DATE = "end_date";

	/** The Constant NEXT_EXECUTION. */
	private static final String NEXT_EXECUTION = "next_excution";

	/** The Constant PARAM_SCHEDULE_ID. */
	private static final String PARAM_SCHEDULE_ID = "p_schedule_id";

	/** The Constant PROPERTY_NAME. */
	private static final String PROPERTY_NAME = "property_name";

	/** The Constant PROPERTY_VALUE. */
	private static final String PROPERTY_VALUE = "property_value";

	/** The Constant SCHEDULE_ID. */
	private static final String SCHEDULE_ID = "schedule_id";

	/** The Constant SCHEDULE_NAME. */
	private static final String SCHEDULE_NAME = "name";

	/** The Constant START_DATE. */
	private static final String START_DATE = "start_date";

	/** The Constant USER_ID. */
	private static final String USER_ID = "user_id";

	/** The Constant MODIFIED_USER. */
	private static final String SERVICE_TYPE = "service_type";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customer_id";

	/** The Constant SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_EVENT_ALREADY_EXISTS. */
	private static final String SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_EVENT_ALREADY_EXISTS =
			"sensus.epm.scheduledeventvalidator.schedule.already.exists";

	/** The Constant SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_IN_PROCESS. */
	private static final String SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_IN_PROCESS =
			"sensus.dm.common.schedule.validator.schedule.in.process";

	/** The Constant SCHEDULE_MAP. */
	private static final String SCHEDULE_MAP = "ScheduleMap.";

	/** The Constant FETCH_SCHEDULE. */
	private static final String FETCH_SCHEDULE = SCHEDULE_MAP + "fetchSchedule";

	/** The Constant COUNT_SCHEDULE_BY_NAME. */
	private static final String COUNT_SCHEDULE_BY_NAME = SCHEDULE_MAP + "countScheduleByName";

	/** The Constant COUNT_PROCESS_BY_SCHEDULE. */
	private static final String COUNT_PROCESS_BY_SCHEDULE = SCHEDULE_MAP + "countProcessBySchedule";

	/** The Constant FETCH_ALL_SCHEDULES. */
	private static final String FETCH_ALL_SCHEDULES = SCHEDULE_MAP + "fetchAllSchedules";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = SCHEDULE_MAP + "PaginationTotalRows";

	/** The Constant INSERT_SCHEDULE. */
	private static final String INSERT_SCHEDULE = SCHEDULE_MAP + "insertSchedule";

	/** The Constant UPDATE_SCHEDULE. */
	private static final String UPDATE_SCHEDULE = SCHEDULE_MAP + "updateSchedule";

	/** The Constant DELETE_SCHEDULE. */
	private static final String DELETE_SCHEDULE = SCHEDULE_MAP + "deleteSchedule";

	/** The Constant INSERT_SCHEDULE_PROPERTY. */
	private static final String INSERT_SCHEDULE_PROPERTY = SCHEDULE_MAP + "insertScheduleProperty";

	/** The Constant DELETE_SCHEDULE_PROPERTY. */
	private static final String DELETE_SCHEDULE_PROPERTY = SCHEDULE_MAP + "deleteScheduleProperty";

	/** The Constant FETCH_SCHEDULES_TO_EXECUTE. */
	private static final String FETCH_SCHEDULES_TO_EXECUTE = SCHEDULE_MAP + "fetchSchedulesToExecute";

	/** The Constant FETCH_SCHEDULED_BY_DEVICE. */
	private static final String FETCH_SCHEDULE_BY_DEVICE = SCHEDULE_MAP + "fetchScheduleByDevice";

	/** The Constant FETCH_SCHEDULED_BY_GROUP. */
	private static final String FETCH_SCHEDULE_BY_GROUP = SCHEDULE_MAP + "fetchScheduleByGroup";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.mybatis.map.IScheduleDAC#fetchAllSchedule(com.sensus.dm.common.schedule.model.
	 * request.InquiryScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMSchedule> fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();

		if (inquiryScheduleRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryScheduleRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_SCHEDULES, inquiryScheduleRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.mybatis.map.IScheduleDAC#fetchSchedule(com.sensus.dm.common.schedule.model.Schedule
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedule(ScheduleRequest scheduleRequest)
	{
		return SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_SCHEDULE,
				scheduleRequest, new InternalResultsResponse<DMSchedule>());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.mybatis.map.IScheduleDAC#insertSchedule(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> insertSchedule(ScheduleRequest scheduleRequest)
	{
		DMSchedule schedule = scheduleRequest.getSchedule();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(SCHEDULE_NAME, schedule.getName());
		paramMap.put(DESCRIPTION, schedule.getDescription());
		paramMap.put(ACTION_ID, schedule.getDmAction().getId());
		paramMap.put(STATUS, schedule.getStatusEnum().getValue());
		paramMap.put(START_DATE, schedule.getStartTime());
		paramMap.put(CREATE_DATE, schedule.getCreateDate());
		paramMap.put(NEXT_EXECUTION, schedule.getFrequency().getNextExecution());
		paramMap.put(END_DATE, schedule.getEndDate());
		paramMap.put(USER_ID, scheduleRequest.getUserContext().getUserId());
		paramMap.put(SERVICE_TYPE, scheduleRequest.getServiceTypeEnum().getServiceTypeDescription());
		paramMap.put(CUSTOMER_ID, scheduleRequest.getTenant().getKey());

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_SCHEDULE, paramMap);

		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();
		schedule.setId(DMConvertUtil.checkResult(response, paramMap, PARAM_SCHEDULE_ID));

		response.addResult(schedule);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.dac.IScheduleDAC#updateSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		DMSchedule schedule = scheduleRequest.getSchedule();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(SCHEDULE_ID, schedule.getId());
		paramMap.put(SCHEDULE_NAME, schedule.getName());
		paramMap.put(DESCRIPTION, schedule.getDescription());
		paramMap.put(STATUS, schedule.getStatusEnum().getValue());
		paramMap.put(START_DATE, schedule.getStartTime());
		paramMap.put(USER_ID, scheduleRequest.getUserContext().getUserId());
		paramMap.put(END_DATE, schedule.getEndDate());
		paramMap.put(NEXT_EXECUTION, schedule.getFrequency().getNextExecution());

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_SCHEDULE, paramMap);

		InternalResponse response = new InternalResponse();
		DMConvertUtil.checkResult(response, paramMap, null);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.mybatis.map.IScheduleDAC#deleteSchedule(com.sensus.dm.common.schedule.model.
	 * Schedule
	 * )
	 */
	@Override
	public InternalResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);
		paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());

		InternalResponse response = new InternalResponse();

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_SCHEDULE, paramMap);

		DMConvertUtil.checkResult(response, paramMap);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#insertScheduleProperty(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse insertScheduleProperty(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);

		for (Property property : scheduleRequest.getSchedule().getProperties())
		{
			paramMap.clear();

			paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());
			paramMap.put(PROPERTY_NAME, property.getPropertyName());
			paramMap.put(PROPERTY_VALUE, property.getPropertyValue());
			paramMap.put(USER_ID, scheduleRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_SCHEDULE_PROPERTY, paramMap);

			DMConvertUtil.checkResult(response, paramMap);

			if (response.isInError())
			{
				return response;
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#deleteAllScheduleProperty(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteAllScheduleProperty(ScheduleRequest scheduleRequest)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());

		if (!ValidationUtil.isNullOrEmpty(scheduleRequest.getSchedule().getProperties()))
		{
			for (Property property : scheduleRequest.getSchedule().getProperties())
			{
				paramMap.put(PROPERTY_NAME, property.getPropertyName());
				SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_SCHEDULE_PROPERTY, paramMap);
			}
		}
		else
		{
			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_SCHEDULE_PROPERTY, paramMap);
		}

		DMConvertUtil.checkResult(response, paramMap);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.dac.IScheduleDAC#canDelete(com.sensus.dm.common.schedule.model.Schedule)
	 */
	@Override
	public InternalResultsResponse<Boolean> canScheduleBeDeleted(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (isScheduleDeleted(scheduleRequest))
		{
			response.addResult(true);
			return response;
		}

		adjustErrorResponse(response, SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_IN_PROCESS);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#canScheduleBeInserted(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> canScheduleBeInserted(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (isUniqueName(scheduleRequest))
		{
			response.addResult(true);
			return response;
		}

		adjustErrorResponse(response, SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_EVENT_ALREADY_EXISTS);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#canScheduleBeUpdated(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> canScheduleBeUpdated(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (!isUniqueName(scheduleRequest))
		{
			adjustErrorResponse(response, SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_EVENT_ALREADY_EXISTS);
			return response;
		}

		if (isScheduleDeleted(scheduleRequest))
		{
			response.addResult(true);
			return response;
		}

		adjustErrorResponse(response, SENSUS_EPM_SCHEDULE_VALIDATOR_SCHEDULED_IN_PROCESS);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchSchedulesToExecute(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedulesToExecute(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_SCHEDULES_TO_EXECUTE, scheduleRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchScheduleByGroup(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByGroup(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();

		SensusMyBatisDacHelper.doQueryForList(
				getSqlSession(), FETCH_SCHEDULE_BY_GROUP, scheduleRequest.getSchedule().getDmAction(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.dac.IScheduleDAC#fetchScheduleByDevice(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByDevice(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();

		SensusMyBatisDacHelper.doQueryForList(
				getSqlSession(), FETCH_SCHEDULE_BY_DEVICE, scheduleRequest.getSchedule().getDmAction(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/**
	 * Checks if is name unique.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the boolean
	 */
	private Boolean isUniqueName(ScheduleRequest scheduleRequest)
	{
		Integer totalRows = (Integer)SensusMyBatisDacHelper.doQueryForObject(
				getSqlSession(), COUNT_SCHEDULE_BY_NAME, scheduleRequest);

		return ValidationUtil.isNullOrZero(totalRows);
	}

	/**
	 * Checks if is delete schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the boolean
	 */
	private Boolean isScheduleDeleted(ScheduleRequest scheduleRequest)
	{
		Integer totalRows = (Integer)SensusMyBatisDacHelper.doQueryForObject(
				getSqlSession(), COUNT_PROCESS_BY_SCHEDULE, scheduleRequest.getSchedule());

		return ValidationUtil.isNullOrZero(totalRows);
	}

	/**
	 * Adjust error response.
	 * 
	 * @param response the response
	 * @param errorCode the error code
	 */
	private void adjustErrorResponse(InternalResultsResponse<Boolean> response, String errorCode)
	{
		response.getResultsList().add(false);
		response.setStatus(Status.ExceptionError);
		response.addMessage(errorCode, Message.MessageSeverity.Error, Message.MessageLevel.FieldValidation);
	}
}
