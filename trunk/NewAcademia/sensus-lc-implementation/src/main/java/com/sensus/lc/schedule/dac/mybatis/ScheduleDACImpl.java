package com.sensus.lc.schedule.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.schedule.dac.IScheduleDAC;
import com.sensus.lc.schedule.model.DaysOfWeekEnum;
import com.sensus.lc.schedule.model.Event;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleOrderByEnum;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class ScheduleDAOImpl.
 */
public class ScheduleDACImpl extends SqlSessionDaoSupport implements IScheduleDAC
{

	/** The Constant SCHEDULE_EVENT_ID. */
	private static final String SCHEDULE_EVENT_ID = "schedule_event_id";

	/** The Constant DAY_OF_WEEK_ID. */
	private static final String DAY_OF_WEEK_ID = "day_of_week_id";

	/** The Constant SCHEDULE_NAME. */
	private static final String SCHEDULE_NAME = "name";

	/** The Constant SCHEDULE_DESC. */
	private static final String SCHEDULE_DESC = "description";

	/** The Constant SCHEDULE_SUNRISE. */
	private static final String SCHEDULE_SUNRISE = "sunrise_offset";

	/** The Constant SCHEDULE_SUNSET. */
	private static final String SCHEDULE_SUNSET = "sunset_offset";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenant_id";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant SCHEDULE_ID. */
	private static final String SCHEDULE_ID = "schedule_id";

	/** The Constant SCHEDULE_EVENT_TIME. */
	private static final String SCHEDULE_EVENT_TIME = "event_time";

	/** The Constant SCHEDULE_INTENSITY. */
	private static final String INTENSITY = "intensity";

	/** The Constant SCHEDULE_USE_TABLES. */
	private static final String SCHEDULE_USE_TABLES = "use_sunrise_sunset_tables";

	/** The Constant PROPERTY_ENUM_VALUE. */
	private static final String PROPERTY_ENUM_VALUE = "property_enum_value";

	/** The Constant USE_TABLES. */
	private static final Boolean USE_TABLES = true;

	/** The Constant MODIFY_USER. */
	private static final String MODIFY_USER = "modify_user";

	/** The Constant LIGHT_ID. */
	private static final String LIGHT_ID = "light_id";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "orderBy";

	/** The Constant UNSELECTED_IDS. */
	private static final String UNSELECTED_IDS = "unSelectedIds";

	/** The Constant SELECTED_IDS. */
	private static final String SELECTED_IDS = "selectedIds";

	/** The Constant SELECTEDIDS. */
	private static final String SCHEDULE_TYPE = "schedule_type";

	/** The Constant SELECTEDIDS. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant SELECTEDIDS. */
	private static final String STAR_TROW = "startRow";

	/** The Constant PARAMSIZE1. */
	private static final Integer PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMSIZE16. */
	private static final Integer PARAMSIZE14 = 14;

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_SCHEDULE_ALREADY_EXISTS. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_SCHEDULE_ALREADY_EXISTS =
			"sensus.mlc.schedulevalidator.schedule.already.exists";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_SCHEDULE_DOES_NOT_EXIST. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_SCHEDULE_DOES_NOT_EXIST =
			"sensus.mlc.schedulevalidator.schedule.does.not.exist";

	/** The Constant SCHEDULE_NAMESPACE. */
	private static final String SCHEDULE_NAMESPACE = "Schedule.";

	/** The Constant FETCH_LIGHT_BY_SCHEDULE. */
	private static final String FETCH_LIGHT_BY_SCHEDULE = SCHEDULE_NAMESPACE + "fetchLightBySchedule";

	/** The Constant INSERT_SCHEDULE_EVENT. */
	private static final String INSERT_SCHEDULE_EVENT = SCHEDULE_NAMESPACE + "insertScheduleEvent";

	/** The Constant INSERT_SCHEDULE_EVENT_DAYOFWEEK. */
	private static final String INSERT_SCHEDULE_EVENT_DAYOFWEEK = SCHEDULE_NAMESPACE + "insertScheduleEventDayOfWeek";

	/** The Constant INSERT_SCHEDULE. */
	private static final String INSERT_SCHEDULE = SCHEDULE_NAMESPACE + "insertSchedule";

	/** The Constant FETCH_ALL_SCHEDULES. */
	private static final String FETCH_ALL_SCHEDULES = SCHEDULE_NAMESPACE + "fetchAllSchedules";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = SCHEDULE_NAMESPACE + "PaginationTotalRows";

	/** The Constant UPDATE_SCHEDULE. */
	private static final String UPDATE_SCHEDULE = SCHEDULE_NAMESPACE + "updateSchedule";

	/** The Constant DELETE_SCHEDULE_EVENT. */
	private static final String DELETE_SCHEDULE_EVENT = SCHEDULE_NAMESPACE + "deleteScheduleEvent";

	/** The Constant DELETE_SCHEDULE. */
	private static final String DELETE_SCHEDULE = SCHEDULE_NAMESPACE + "deleteSchedule";

	/** The Constant FETCH_SCHEDULE_BI_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = SCHEDULE_NAMESPACE + "fetchScheduleById";

	/** The Constant REMOVE_LIGHT_SCHEDULE. */
	private static final String REMOVE_LIGHT_SCHEDULE = SCHEDULE_NAMESPACE + "removeLightSchedule";

	/** The Constant APPLY_LIGHT_SCHEDULE. */
	private static final String APPLY_LIGHT_SCHEDULE = SCHEDULE_NAMESPACE + "applyLightSchedule";

	/** The Constant UPDATE_SCHEDULE_CENTER. */
	private static final String UPDATE_SCHEDULE_CENTER = SCHEDULE_NAMESPACE + "updateScheduleCenter";

	/** The Constant APPLY_UNKNOW_OFFSET_SCHEDULE. */
	private static final String APPLY_UNKNOW_OFFSET_SCHEDULE = SCHEDULE_NAMESPACE + "applyUnknownOffsetSchedule";

	/** The Constant APPLY_UNKNOW_EVENT_SCHEDULE. */
	private static final String APPLY_UNKNOW_EVENT_SCHEDULE = SCHEDULE_NAMESPACE + "applyUnknownEventSchedule";

	/** The Constant COUNT_SCHEDULE_BY_NAME. */
	private static final String COUNT_SCHEDULE_BY_NAME = SCHEDULE_NAMESPACE + "countScheduleByName";

	/** The Constant COUNT_RUNNING_PROCESS_FOR_SCHEDULE. */
	private static final String COUNT_RUNNING_PROCESS_FOR_SCHEDULE = SCHEDULE_NAMESPACE
			+ "countRunningProcessForSchedule";

	/** The Constant ALLOWED_GROUP_ID_LIST. */
	private static final String ALLOWED_GROUP_ID_LIST = "allowedGroupIdList";

	/** The Constant BLINK_LEVEL. */
	private static final String BLINK_LEVEL = "blink_level";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.dao.IScheduleDAO#insertSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Schedule> insertSchedule(ScheduleRequest scheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE14);

		paramMap.put(SCHEDULE_NAME, scheduleRequest.getSchedule().getName());
		paramMap.put(SCHEDULE_DESC, scheduleRequest.getSchedule().getDescription());
		paramMap.put(TENANT_ID, scheduleRequest.getTenant().getId());
		paramMap.put(CREATE_USER, scheduleRequest.getUserContext().getUserId());
		paramMap.put(SCHEDULE_USE_TABLES, USE_TABLES);
		ScheduleTypeEnum scheduleTypeEnum = scheduleRequest.getSchedule().getScheduleTypeEnum();
		paramMap.put(SCHEDULE_TYPE, scheduleTypeEnum.getValue());

		if (scheduleTypeEnum == ScheduleTypeEnum.OFFSET)
		{
			OffsetSchedule offsetSchedule = (OffsetSchedule)scheduleRequest.getSchedule();
			paramMap.put(INTENSITY, offsetSchedule.getIntensity());
			paramMap.put(SCHEDULE_SUNRISE, offsetSchedule.getSunriseOffsetMinutes());
			paramMap.put(SCHEDULE_SUNSET, offsetSchedule.getSunsetOffsetMinutes());
		}

		Integer idSchedule = (Integer)doQueryForObject(getSqlSession(), INSERT_SCHEDULE, paramMap);

		scheduleRequest.getSchedule().setId(idSchedule);

		InternalResultsResponse<Schedule> response = new InternalResultsResponse<Schedule>();

		response.addResult(scheduleRequest.getSchedule());

		if (scheduleTypeEnum != ScheduleTypeEnum.EVENT)
		{
			return response;
		}

		upsertEventSchedule(scheduleRequest, paramMap, response, CREATE_USER);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#fetchAllSchedules(com.sensus.mlc.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<Schedule> fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		paramMap.put(TENANT_ID, inquiryScheduleRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryScheduleRequest.getPageSize());
		paramMap.put(STAR_TROW, inquiryScheduleRequest.getStartRow());
		paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryScheduleRequest.getAllowedGroupIdList());
		paramMap.put(ORDER_BY, ScheduleOrderByEnum.NAME_COLUMN.getValue());

		if (!ValidationUtil.isNullOrEmpty(inquiryScheduleRequest.getSelectionPaginationIds()))
		{
			if (!ValidationUtil.isNull(inquiryScheduleRequest.getPaginationAllSelected())
					&& inquiryScheduleRequest.getPaginationAllSelected())
			{
				paramMap.put(UNSELECTED_IDS, inquiryScheduleRequest.getSelectionPaginationIds());
			}
			else
			{
				paramMap.put(SELECTED_IDS, inquiryScheduleRequest.getSelectionPaginationIds());
			}
		}

		if (!ValidationUtil.isNull(inquiryScheduleRequest.getScheduleTypeEnum()))
		{
			paramMap.put(SCHEDULE_TYPE, inquiryScheduleRequest.getScheduleTypeEnum().getValue());
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryScheduleRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryScheduleRequest.getSortExpressions().get(0));
		}

		InternalResultsResponse<Schedule> response = new InternalResultsResponse<Schedule>();
		doQueryForList(getSqlSession(), FETCH_ALL_SCHEDULES, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(), PAGINATION_TOTAL_ROWS, paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.dao.IScheduleDAO#updateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE14);

		paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());
		paramMap.put(SCHEDULE_NAME, scheduleRequest.getSchedule().getName());
		paramMap.put(SCHEDULE_DESC, scheduleRequest.getSchedule().getDescription());
		paramMap.put(MODIFY_USER, scheduleRequest.getUserContext().getUserId());

		ScheduleTypeEnum scheduleTypeEnum = scheduleRequest.getSchedule().getScheduleTypeEnum();
		if (scheduleTypeEnum == ScheduleTypeEnum.OFFSET)
		{
			OffsetSchedule offSetSchedule = (OffsetSchedule)scheduleRequest.getSchedule();

			paramMap.put(SCHEDULE_SUNRISE, offSetSchedule.getSunriseOffsetMinutes());
			paramMap.put(SCHEDULE_SUNSET, offSetSchedule.getSunsetOffsetMinutes());
			paramMap.put(INTENSITY, offSetSchedule.getIntensity());
		}

		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_SCHEDULE, paramMap, response);

		if (scheduleTypeEnum != ScheduleTypeEnum.EVENT)
		{
			return response;
		}

		doRemove(getSqlSession(), DELETE_SCHEDULE_EVENT, paramMap, response);

		upsertEventSchedule(scheduleRequest, paramMap, response, MODIFY_USER);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#deleteSchedule(com.sensus.mlc.schedule.model.Schedule)
	 */
	@Override
	public InternalResponse deleteSchedule(Schedule schedule)
	{
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), DELETE_SCHEDULE, schedule, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#fetchScheduleById(com.sensus.mlc.schedule.model.Schedule)
	 */
	@Override
	public InternalResultsResponse<Schedule> fetchScheduleById(Schedule schedule)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);
		paramMap.put(SCHEDULE_ID, schedule.getId());

		InternalResultsResponse<Schedule> response = new InternalResultsResponse<Schedule>();
		doQueryForList(getSqlSession(), FETCH_SCHEDULE_BY_ID, paramMap, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dac.IScheduleDAC#deleteLightFromSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteLightFromSchedule(ScheduleRequest scheduleRequest)
	{
		Schedule schedule = scheduleRequest.getSchedule();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);

		// Since we set the schedule type as Unknown before...:
		if (scheduleRequest.getSchedule().getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			paramMap.put(SCHEDULE_TYPE, ScheduleTypeEnum.UNDETERMINED_OFFSET.getValue());
		}

		if (scheduleRequest.getSchedule().getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
		{
			paramMap.put(SCHEDULE_TYPE, ScheduleTypeEnum.UNDETERMINED_EVENT.getValue());
		}

		InternalResponse response = new InternalResponse();
		for (Integer lightId : schedule.getLights())
		{
			paramMap.put(LIGHT_ID, lightId);
			doRemove(getSqlSession(), REMOVE_LIGHT_SCHEDULE, paramMap, response);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.dac.IScheduleDAC#applyLightToSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse applyLightToSchedule(ScheduleRequest scheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());
		paramMap.put(CREATE_USER, scheduleRequest.getUserContext().getUserId());
		paramMap.put(LIGHT_ID, scheduleRequest.getSelectionPaginationIds().get(0));

		InternalResponse response = new InternalResponse();

		doUpdate(getSqlSession(), APPLY_LIGHT_SCHEDULE, paramMap, response);

		// Any time a point is added to a Schedule, need to update the Schedule' center point
		doUpdate(getSqlSession(), UPDATE_SCHEDULE_CENTER, paramMap, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#applyUnknownOffsetSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownOffsetSchedule(ScheduleRequest scheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(CREATE_USER, scheduleRequest.getUserContext().getUserId());
		paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());

		InternalResponse response = new InternalResponse();

		for (Integer lightId : scheduleRequest.getSchedule().getLights())
		{
			paramMap.put(LIGHT_ID, lightId);
			doUpdate(getSqlSession(), APPLY_UNKNOW_OFFSET_SCHEDULE, paramMap, response);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#applyUnknownEventSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse applyUnknownEventSchedule(ScheduleRequest scheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(CREATE_USER, scheduleRequest.getUserContext().getUserId());
		paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());

		InternalResponse response = new InternalResponse();

		for (Integer lightId : scheduleRequest.getSchedule().getLights())
		{
			paramMap.put(LIGHT_ID, lightId);
			doUpdate(getSqlSession(), APPLY_UNKNOW_EVENT_SCHEDULE, paramMap, response);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#canDelete(com.sensus.mlc.schedule.model.Schedule)
	 */
	@Override
	public Boolean fetchCanDelete(Schedule schedule)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(PROPERTY_ENUM_VALUE, PropertyEnum.SCHEDULE_ID.getValue());
		paramMap.put(SCHEDULE_ID, schedule.getId().toString());

		// Check if there are any Process running for this schedule
		return (Integer)doQueryForObject(getSqlSession(), COUNT_RUNNING_PROCESS_FOR_SCHEDULE, paramMap) == 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#canInsert(com.sensus.mlc.schedule.model.Schedule, java.util.List,
	 * com.sensus.mlc.base.model.Tenant)
	 */
	@Override
	public Boolean fetchCanInsert(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{
		if (isNameUnique(schedule, tenant))
		{
			return true;
		}

		list.add(new MessageInfo(SENSUS_MLC_SCHEDULEVALIDATOR_SCHEDULE_ALREADY_EXISTS, Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation));

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.dao.IScheduleDAO#canUpdate(com.sensus.mlc.schedule.model.Schedule, java.util.List,
	 * com.sensus.mlc.base.model.Tenant)
	 */
	@Override
	public Boolean fetchCanUpdate(Schedule schedule, List<MessageInfo> list, Tenant tenant)
	{
		InternalResultsResponse<Schedule> response = fetchScheduleById(schedule);

		// The update keep the actual name?
		if (!ValidationUtil.isNull(response.getFirstResult())
				&& schedule.getName().equalsIgnoreCase(response.getFirstResult().getName()))
		{
			return true;
		}

		if (response.getStatus().equals(Status.OperationSuccess))
		{
			// Will the new name be a duplicate ?
			return fetchCanInsert(schedule, list, tenant);
		}

		list.add(new MessageInfo(SENSUS_MLC_SCHEDULEVALIDATOR_SCHEDULE_DOES_NOT_EXIST, Message.MessageSeverity.Error,
				Message.MessageLevel.Object));
		return false;
	}

	/**
	 * Checks if is name unique.
	 * 
	 * @param schedule the schedule
	 * @param tenant the tenant
	 * @return the boolean
	 */
	private Boolean isNameUnique(Schedule schedule, Tenant tenant)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(2);
		paramMap.put(SCHEDULE_NAME, schedule.getName());
		paramMap.put(TENANT_ID, tenant.getId());

		return ValidationUtil
				.isNullOrZero((Integer)doQueryForObject(getSqlSession(), COUNT_SCHEDULE_BY_NAME, paramMap));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.dac.IScheduleDAC#fetchLightBySchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightBySchedule(ScheduleRequest scheduleRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());
		paramMap.put(TENANT_ID, scheduleRequest.getTenant().getId());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_LIGHT_BY_SCHEDULE, paramMap, response);

		return response;
	}

	/**
	 * Upsert event schedule.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param paramMap the param map
	 * @param response the response
	 * @param userKey the user key
	 */
	private void upsertEventSchedule(
			ScheduleRequest scheduleRequest,
			HashMap<String, Object> paramMap,
			InternalResponse response,
			String userKey)
	{
		EventSchedule eventSchedule = (EventSchedule)scheduleRequest.getSchedule();

		for (Event eventData : eventSchedule.getEvents())
		{
			paramMap.put(SCHEDULE_EVENT_TIME, eventData.getTime());
			paramMap.put(INTENSITY, eventData.getIntensity());
			paramMap.put(BLINK_LEVEL, eventData.getLightBlinkEnum().getValue());
			paramMap.put(SCHEDULE_ID, scheduleRequest.getSchedule().getId());
			paramMap.put(TENANT_ID, scheduleRequest.getTenant().getId());
			paramMap.put(userKey, scheduleRequest.getUserContext().getUserId());

			Integer eventId = (Integer)doQueryForObject(getSqlSession(), INSERT_SCHEDULE_EVENT, paramMap);

			for (DaysOfWeekEnum dayOfWeek : eventData.getDays())
			{
				paramMap.put(DAY_OF_WEEK_ID, dayOfWeek.getValue());
				paramMap.put(SCHEDULE_EVENT_ID, eventId);
				paramMap.put(userKey, scheduleRequest.getUserContext().getUserId());
				doInsert(getSqlSession(), INSERT_SCHEDULE_EVENT_DAYOFWEEK, paramMap, response);
				paramMap.clear();
			}
		}
	}
}
