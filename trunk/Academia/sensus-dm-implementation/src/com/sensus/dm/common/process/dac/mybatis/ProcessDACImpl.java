package com.sensus.dm.common.process.dac.mybatis;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.process.dac.IProcessDAC;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemHistory;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class ProcessDACImpl.
 * 
 * @author QAT Global
 */
public class ProcessDACImpl extends SqlSessionDaoSupport implements IProcessDAC
{

	/** The Constant NINETY_NINE. */
	private static final int NINETY_NINE = 99;

	/** The Constant PARAMSIZE1. */
	private static final Integer PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant PARAMZIE9. */
	private static final Integer PARAMSIZE9 = 9;

	/** The Constant PARAMZIE10. */
	private static final Integer PARAMSIZE10 = 10;

	/** The Constant EVENT_STATUS_TIME. */
	private static final String EVENT_STATUS_TIME = "event_status_time";

	/** The Constant PROPERTY_VALUE. */
	private static final String PROPERTY_VALUE = "value";

	/** The Constant PROPERTY_NAME. */
	private static final String PROPERTY_NAME = "property_name";

	/** The Constant ACTION_ID. */
	private static final String ACTION_ID = "action_instance_id";

	/** The Constant PROCESS_TYPE_ID. */
	private static final String PROCESS_TYPE_ID = "process_type_id";

	/** The Constant STATUS. */
	private static final String STATUS = "status";

	/** The Constant MESSAGE. */
	private static final String MESSAGE = "message";

	/** The Constant PARTICIPATION. */
	private static final String PARTICIPATION = "participation";

	/** The Constant MONITORED. */
	private static final String MONITORED = "is_monitored";

	/** The Constant MONITORED_DASHBOARD. */
	private static final String MONITORED_DASHBOARD = "is_dashboard_monitored";

	/** The Constant START_DATETIME. */
	private static final String START_TIME = "start_time";

	/** The Constant END_DATETIME. */
	private static final String END_TIME = "end_time";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexNet_Id";

	/** The Constant MODIFIED_USER. */
	private static final String MODIFIED_USER = "modified_user";

	/** The Constant MODIFIED_USER. */
	private static final String SERVICE_TYPE = "service_type";

	/** The Constant PARAM_PROCESS_ENDPOINT_ID. */
	private static final String PARAM_PROCESS_ENDPOINT_ID = "p_process_endpoint_id";

	/** The Constant PARAM_PROCESS_ID. */
	private static final String PARAM_PROCESS_ID = "p_process_id";

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "process_id";

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customer_id";

	/** The Constant PROCESS_ITEM_ID. */
	private static final String PROCESS_ITEM_ID = "process_item_id";

	/** The Constant PROCESS_ITEM_STATUS. */
	private static final String PROCESS_ITEM_STATUS = "process_item_status";

	/** The Constant RNI_EVENT_ID. */
	private static final String RNI_EVENT_ID = "rni_event_id";

	/** The Constant MAC_ADDRESS. */
	private static final String MAC_ADDRESS = "mac_address";

	/** The Constant PROCESS_MAP. */
	private static final String PROCESS_MAP = "ProcessMap.";

	/** The Constant FETCH_PROCESSES. */
	private static final String FETCH_PROCESSES = PROCESS_MAP + "fetchProcesses";

	/** The Constant FETCH_TODAY_PROCESSES. */
	private static final String FETCH_TODAY_PROCESSES = PROCESS_MAP + "fetchTodayProcesses";

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = PROCESS_MAP + "fetchProcessById";

	/** The Constant FETCH_PROCESS_ITEMS_BY_DEVICE. */
	private static final String FETCH_PROCESS_ITEMS_BY_DEVICE = PROCESS_MAP + "fetchProcessItemsByDevice";

	/** The Constant FETCH_ALL_PROCESS_ITEMS. */
	private static final String FETCH_ALL_PROCESS_ITEMS = PROCESS_MAP + "fetchAllProcessItems";

	/** The Constant FETCH_MONITORED_PROCESS. */
	private static final String FETCH_MONITORED_PROCESS = PROCESS_MAP + "fetchMonitoredProcess";

	/** The Constant FETCH_SCHEDULED_PROCESSES. */
	private static final String FETCH_SCHEDULED_PROCESSES = PROCESS_MAP + "fetchScheduledProcesses";

	/** The Constant FETCH_STARTED_PROCESSES. */
	private static final String FETCH_STARTED_PROCESSES = PROCESS_MAP + "fetchStartedProcesses";

	/** The Constant FETCH_PROCESSES_IN_PROCESSING. */
	private static final String FETCH_PROCESSES_IN_PROCESSING = PROCESS_MAP + "fetchProcessesInProcessing";

	/** The Constant FETCH_COUNT_MONITORED_PROCESSES. */
	private static final String FETCH_COUNT_MONITORED_PROCESSES = PROCESS_MAP + "fetchCountMonitoredProcesses";

	/** The Constant INSERT_PROCESS_ITEM. */
	private static final String INSERT_PROCESS_ITEM = PROCESS_MAP + "insertProcessItem";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = PROCESS_MAP + "insertProcess";

	/** The Constant INSERT_PROCESS_PROPERTY. */
	private static final String INSERT_PROCESS_PROPERTY = PROCESS_MAP + "insertProcessProperty";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = PROCESS_MAP + "paginationTotalRows";

	/** The Constant SUMMARIZE_PROCESS. */
	private static final String SUMMARIZE_PROCESS = PROCESS_MAP + "summarizeProcess";

	/** The Constant UPDATE_PROCESS. */
	private static final String UPDATE_PROCESS = PROCESS_MAP + "updateProcess";

	/** The Constant UPDATE_PROCESS_ITEM. */
	private static final String UPDATE_PROCESS_ITEM = PROCESS_MAP + "updateProcessItem";

	/** The Constant CHECK_PROCESSING. */
	private static final String CHECK_PROCESSING = PROCESS_MAP + "checkProcessing";

	/** The Constant INSERT_DR_EVENT_REPORTS. */
	private static final String INSERT_DR_EVENT_REPORTS = PROCESS_MAP + "insertDrEventReports";

	/** The Constant FETCH_PROCESS_ITEMS_BY_PROCESS. */
	private static final String FETCH_PROCESS_ITEMS_BY_PROCESS = PROCESS_MAP + "fetchProcessItems";

	/** The Constant FETCH_PROCESS_ITEMS_PROCESS_PROPERTY. */
	private static final String FETCH_PROCESS_ITEMS_PROCESS_PROPERTY = PROCESS_MAP
			+ "fetchProcessItemsProcessProperty";

	/** The Constant INSERT_PROCESS_ENDPOINT_PROPERTY. */
	private static final String INSERT_PROCESS_ENDPOINT_PROPERTY = PROCESS_MAP + "insertProcessEndPointProperty";

	/** The Constant DEL_PROCESS_ENDPOINT_PROPERTY. */
	private static final String DEL_PROCESS_ENDPOINT_PROPERTY = PROCESS_MAP + "deleteProcessItemProperty";

	/** The Constant FETCH_PROCESS_ITEMS_BY_SCHEDULE. */
	private static final String FETCH_PROCESS_ITEMS_BY_SCHEDULE = PROCESS_MAP + "fetchProcessItemsBySchedule";

	/** The Constant FETCH_PROCESSES_PROPERTY. */
	private static final String FETCH_PROCESSES_PROPERTY = PROCESS_MAP + "fetchProcessProperties";

	/** The Constant FETCH_PROCESS_BY_RNI_EVENT_ID. */
	private static final String FETCH_PROCESS_BY_RNI_EVENT_ID = "fetchProcessByRniEventId";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (inquiryProcessRequest.isPreQueryCount())
		{
			response.getResultsSetInfo().setTotalRowsAvailable(
					(Integer)SensusMyBatisDacHelper.doQueryForObject(
							getSqlSession(), PAGINATION_TOTAL_ROWS, inquiryProcessRequest));

			if (ValidationUtil.isNullOrZero(response.getResultsSetInfo().getTotalRowsAvailable()))
			{
				return response;
			}
		}

		response.addResults(SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESSES,
				inquiryProcessRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessById(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();
		response.getResultsList().addAll(SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_BY_ID,
				processRequest.getFirstProcess()));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchMonitoredProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		return SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_MONITORED_PROCESS, inquiryProcessRequest,
				new InternalResultsResponse<DMProcess>());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#insertProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> insertProcess(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		HashMap<String, Object> paramMapProcess = new HashMap<String, Object>(PARAMSIZE9);

		if (!ValidationUtil.isNull(processRequest.getFirstProcess().getAction()))
		{
			paramMapProcess.put(ACTION_ID, processRequest.getFirstProcess().getAction().getId());
		}
		else
		{
			paramMapProcess.put(PROCESS_TYPE_ID, processRequest.getFirstProcess().getProcessType().getId());
		}

		paramMapProcess.put(START_TIME, processRequest.getFirstProcess().getStartTime());
		paramMapProcess.put(END_TIME, processRequest.getFirstProcess().getEndTime());
		paramMapProcess.put(MONITORED, processRequest.getFirstProcess().getIsMonitoredInstance());
		paramMapProcess.put(MONITORED_DASHBOARD, processRequest.getFirstProcess().getIsDashboardMonitored());
		paramMapProcess.put(STATUS, processRequest.getFirstProcess().getProcessStatusEnum().getValue());
		paramMapProcess.put(RNI_EVENT_ID, processRequest.getFirstProcess().getRniEventId());
		paramMapProcess.put(MODIFIED_USER, processRequest.getUserContext().getUserId());
		paramMapProcess.put(SERVICE_TYPE, processRequest.getServiceTypeEnum().getServiceTypeDescription());
		paramMapProcess.put(CUSTOMER_ID, processRequest.getTenant().getKey());

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_PROCESS, paramMapProcess);

		processRequest.getFirstProcess().setId(DMConvertUtil.checkResult(response, paramMapProcess, PARAM_PROCESS_ID));
		response.addResult(processRequest.getFirstProcess());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#insertProcessProperty(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse insertProcessProperty(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(PROCESS_ID, processRequest.getFirstProcess().getId());
		paramMap.put(MODIFIED_USER, processRequest.getUserContext().getUserId());

		for (Property property : processRequest.getFirstProcess().getProperties())
		{
			paramMap.put(PROPERTY_NAME, property.getPropertyName());
			paramMap.put(PROPERTY_VALUE, property.getPropertyValue());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_PROCESS_PROPERTY, paramMap);

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
	 * com.sensus.dm.common.process.dac.IProcessDAC#updateProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		for (DMProcess process : processRequest.getProcessList())
		{

			ProcessType processType = process.getProcessType();
			DMAction action = process.getAction();

			HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE10);
			paramMap.put(PROCESS_ID, process.getId());
			paramMap.put(MONITORED, process.getIsMonitoredInstance());
			paramMap.put(MONITORED_DASHBOARD, process.getIsDashboardMonitored());
			paramMap.put(MODIFIED_USER, processRequest.getUserContext().getUserId());

			if (!ValidationUtil.isNull(action))
			{
				paramMap.put(ACTION_ID, action.getId());
			}
			else if (!ValidationUtil.isNull(processType))
			{
				paramMap.put(PROCESS_TYPE_ID, processType.getId());
			}

			paramMap.put(STATUS, process.getProcessStatusEnumValue());

			paramMap.put(START_TIME, process.getStartTime());

			paramMap.put(END_TIME, process.getEndTime());

			paramMap.put(RNI_EVENT_ID, process.getRniEventId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_PROCESS, paramMap);

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
	 * com.sensus.dm.common.process.dac.IProcessDAC#insertProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ProcessItem> insertProcessItems(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();

		for (ProcessItem processItem : processRequest.getFirstProcess().getProcessItems())
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

			paramMap.put(FLEXNET_ID, processItem.getDevice().getRadio().getFlexNetId());
			paramMap.put(PROCESS_ID, processRequest.getFirstProcess().getId());
			paramMap.put(STATUS, processItem.getProcessItemStatusEnum().getValue());
			paramMap.put(MESSAGE, processItem.getMessage());
			paramMap.put(PARTICIPATION, processItem.getParticipation());
			paramMap.put(MODIFIED_USER, processRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_PROCESS_ITEM, paramMap);

			processItem.setId(DMConvertUtil.checkResult(response, paramMap, PARAM_PROCESS_ENDPOINT_ID));

			updateProcessItem(response, processItem, processRequest.getUserContext());

			if (response.isInError())
			{
				return response;
			}

			response.addResult(processItem);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#updateProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse updateProcessItems(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);

		for (ProcessItem processItem : processRequest.getFirstProcess().getProcessItems())
		{
			if (!ValidationUtil.isNull(processItem.getDevice()))
			{
				paramMap.put(FLEXNET_ID, processItem.getDevice().getRadio().getFlexNetId());
			}
			paramMap.put(PROCESS_ITEM_ID, processItem.getId());
			paramMap.put(PROCESS_ID, processRequest.getFirstProcess().getId());
			paramMap.put(STATUS, processItem.getProcessItemStatusEnum().getValue());
			paramMap.put(MESSAGE, processItem.getMessage());
			if (!ValidationUtil.isNull(processItem.getMessage()) && processItem.getMessage().length() > NINETY_NINE)
			{
				paramMap.put(MESSAGE, StringUtils.mid(processItem.getMessage(), 0, NINETY_NINE));
			}
			paramMap.put(PARTICIPATION, processItem.getParticipation());
			paramMap.put(MODIFIED_USER, processRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_PROCESS_ITEM, paramMap);

			DMConvertUtil.checkResult(response, paramMap);

			updateProcessItem(response, processItem, processRequest.getUserContext());

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
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchCheckProcessing(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse fetchCheckProcessing(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();

		Integer total =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), CHECK_PROCESSING,
						processRequest.getFirstProcess());

		if (!ValidationUtil.isNullOrZero(total))
		{
			response.setStatus(Status.ExceptionError);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchCountMonitoredProcesses(com.sensus.dm.common.process.model.
	 * request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(
			ProcessRequest processRequest)
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_COUNT_MONITORED_PROCESSES, processRequest,
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchTodayProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> internalResultsResponse = new InternalResultsResponse<DMProcess>();

		internalResultsResponse.addResults(
				SensusMyBatisDacHelper
						.doQueryForList(getSqlSession(), FETCH_TODAY_PROCESSES, inquiryProcessRequest));

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchScheduledProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchScheduledProcesses(InquiryProcessRequest request)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		response.addResults(
				SensusMyBatisDacHelper
						.doQueryForList(getSqlSession(), FETCH_SCHEDULED_PROCESSES, request));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchStartedProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchStartedProcesses(
			InquiryProcessRequest request)
	{

		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		response.addResults(
				SensusMyBatisDacHelper
						.doQueryForList(getSqlSession(), FETCH_STARTED_PROCESSES, request));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#summarizeProcess(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse summarizeProcess(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);

		for (ProcessItem processItem : processRequest.getFirstProcess().getProcessItems())
		{
			paramMap.put(PROCESS_ITEM_ID, processItem.getId());
			paramMap.put(PROCESS_ITEM_STATUS, processItem.getProcessItemStatusEnum().getValue());
			paramMap.put(MODIFIED_USER, processRequest.getUserContext().getUserId());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), SUMMARIZE_PROCESS, paramMap);

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
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessItemsByDevice(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByDevice(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_ITEMS_BY_DEVICE,
				processRequest.getFirstProcess(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessItemsBySchedule(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsBySchedule(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_ITEMS_BY_SCHEDULE,
				processRequest.getFirstProcess(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessesInProcessing(com.sensus.dm.common.process.model.request
	 * .
	 * InquiryProcessRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesInProcessing(InquiryProcessRequest request)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		response.addResults(SensusMyBatisDacHelper
				.doQueryForList(getSqlSession(), FETCH_PROCESSES_IN_PROCESSING, request));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessProperty(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Property> fetchProcessProperty(ProcessRequest processRequest)
	{
		InternalResultsResponse<Property> response = new InternalResultsResponse<Property>();

		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESSES_PROPERTY,
				processRequest.getFirstProcess().getId(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessItemsByProcessId(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByProcessId(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_ITEMS_BY_PROCESS,
				processRequest, response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/**
	 * Fetch process items process property.
	 * 
	 * @param processRequest the process request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsProcessProperty(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_ITEMS_PROCESS_PROPERTY,
				processRequest, response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchProcessByRniEventId(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessByRniEventId(
			ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_BY_RNI_EVENT_ID,
				processRequest.getFirstProcess(), response);

		DMConvertUtil.checkResult(response);

		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		processRequest.getFirstProcess().setId(response.getFirstResult().getId());

		response.getFirstResult().setProcessItems(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROCESS_ITEMS_BY_PROCESS,
						processRequest));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.dac.IProcessDAC#fetchAllProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchAllProcessItems(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> response = new InternalResultsResponse<ProcessItem>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_PROCESS_ITEMS,
				processRequest.getFirstProcess(), response);

		DMConvertUtil.checkResult(response);

		return response;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Insert process item histories.
	 * 
	 * @param processItem the process item
	 * @return the internal response
	 */
	private InternalResponse insertProcessItemHistories(ProcessItem processItem)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);

		for (ProcessItemHistory processItemHistory : processItem.getProcessItemHistories())
		{
			paramMap.put(PROCESS_ITEM_ID, processItem.getId());
			paramMap.put(MAC_ADDRESS, 1);
			paramMap.put(PROCESS_ITEM_STATUS, processItemHistory.getProcessItemHistoryStatusEnum());
			paramMap.put(EVENT_STATUS_TIME, processItemHistory.getStatusTime());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_DR_EVENT_REPORTS, paramMap);

			DMConvertUtil.checkResult(response, paramMap);

			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}
		}

		return response;
	}

	/**
	 * Insert process end point property.
	 * 
	 * @param processItem the process item
	 * @param user the user
	 * @return the internal response
	 */
	private InternalResponse insertProcessEndPointProperty(ProcessItem processItem, UserContext user)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(PROCESS_ID, processItem.getId());
		paramMap.put(MODIFIED_USER, user.getUserId());

		for (Property property : processItem.getProperties())
		{
			paramMap.put(PROPERTY_NAME, property.getPropertyName());
			paramMap.put(PROPERTY_VALUE, property.getPropertyValue());

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_PROCESS_ENDPOINT_PROPERTY, paramMap);
			DMConvertUtil.checkResult(response, paramMap);

			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}
		}

		return response;
	}

	/**
	 * Delete all process item property.
	 * 
	 * @param processItem the process item
	 * @return the internal response
	 */
	private InternalResponse deleteAllProcessItemProperty(ProcessItem processItem)
	{

		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);
		paramMap.put(PROCESS_ID, processItem.getId());

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DEL_PROCESS_ENDPOINT_PROPERTY, paramMap);

		DMConvertUtil.checkResult(response, paramMap);

		return response;
	}

	/**
	 * Update process item.
	 * 
	 * @param response the response
	 * @param processItem the process item
	 * @param user the user
	 */
	private void updateProcessItem(InternalResponse response,
			ProcessItem processItem, UserContext user)
	{
		// Method created to avoid redundancy code,
		// because this code is used in insert and update of process item.

		InternalResponse internalResponse = response;

		// insert ProcessItem History
		if (!internalResponse.isInError()
				&& !ValidationUtil.isNullOrEmpty(processItem.getProcessItemHistories()))
		{
			internalResponse = insertProcessItemHistories(processItem);
		}

		// delete and insert ProcessItem Property
		if (!internalResponse.isInError()
				&& !ValidationUtil.isNullOrEmpty(processItem.getProperties()))
		{
			internalResponse = deleteAllProcessItemProperty(processItem);

			if (!internalResponse.isInError())
			{
				internalResponse = insertProcessEndPointProperty(processItem, user);
			}
		}

		if (internalResponse.isInError())
		{
			response.addMessages(internalResponse.getMessageInfoList());
			response.setStatus(internalResponse.getStatus());
		}
	}
}
