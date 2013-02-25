package com.sensus.mlc.process.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.smartpoint.dacd.SmartPointDACD.getParametersToFetchAllLights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCConvertUtil;
import com.sensus.mlc.process.csv.ProcessCSVDataSource;
import com.sensus.mlc.process.dac.IProcessDAC;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionCategoryEnum;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessFilter;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.LightPropertyForSearchEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessDAOImpl.
 */
public class ProcessDACImpl extends SqlSessionDaoSupport implements IProcessDAC
{

	private static final String ROLE_ROLE_ADMIN = "ROLE_Role.Admin";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "description";

	/** The Constant MLC_ACTION_DESCRIPTION. */
	private static final String LC_ACTION_DESCRIPTION = "lc_action_description";

	/** The Constant START_DATETIME. */
	private static final String START_DATETIME = "start_datetime";

	/** The Constant END_DATETIME. */
	private static final String END_DATETIME = "end_datetime";

	/** The Constant RNI_CORRELATION_ID. */
	private static final String RNI_CORRELATION_ID = "rni_correlation_id";

	/** The Constant MLC_ACTION_ID. */
	private static final String LC_ACTION_ID = "lc_action_id";

	/** The Constant LIGHT_INTENSITY. */
	private static final String LIGHT_INTENSITY = "light_intensity";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenant_id";

	/** The Constant RNI_ID. */
	private static final String RNI_ID = "rniId";

	/** The Constant PROCESS_ITEM_FAILURE_LIST. */
	private static final String PROCESS_ITEM_FAILURE_LIST = "processItemFailureList";

	/** The Constant LIGHT_PROPERTY. */
	private static final String LIGHT_PROPERTY = "lightProperty";

	/** The Constant SEARCH_TEXT. */
	private static final String SEARCH_TEXT = "searchText";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant USER_ID. */
	private static final String USER_ID = "user_id";

	/** The Constant USER_IDS. */
	private static final String USER_IDS = "userIds";

	/** The Constant START_DATE. */
	private static final String START_DATE = "startDate";

	/** The Constant END_DATE. */
	private static final String END_DATE = "endDate";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "orderBy";

	/** The Constant ESTIMATED_SECONDS_TO_COMPLETE. */
	private static final String ESTIMATED_SECONDS_TO_COMPLETE = "estimated_seconds_to_complete";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant IS_SUBMITTED. */
	private static final String IS_SUBMITTED = "is_submitted";

	/** The Constant IS_MONITORED_INSTANCE. */
	private static final String IS_MONITORED_INSTANCE = "is_monitored_instance";

	/** The Constant IS_PROCESS_COMPLETE. */
	private static final String IS_PROCESS_COMPLETE = "is_process_complete";

	/** The Constant IS_FIRST_LEVEL. */
	private static final String IS_FIRST_LEVEL = "is_first_level";

	/** The Constant PROCESS_RESULT. */
	private static final String PROCESS_RESULT = "process_result";

	/** The Constant SMARTPOINT_ID. */
	private static final String SMARTPOINT_ID = "smartpoint_id";

	/** The Constant PROCESS_ID. */
	private static final String PROCESS_ID = "process_id";

	/** The Constant FAILURE_ID. */
	private static final String FAILURE_ID = "failure_id";

	/** The Constant PARENT_PROCESS_ID. */
	private static final String PARENT_PROCESS_ID = "parent_process_id";

	/** The Constant PARAMETER_TYPE. */
	private static final String PARAMETER_TYPE = "property_id";

	/** The Constant ACTION_VALUE. */
	private static final String ACTION_VALUE = "value";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE6. */
	private static final Integer PARAMSIZE6 = 6;

	/** The Constant EVENT_ID. */
	private static final String EVENT_ID = "eventId";

	/** The Constant PARAMZIE9. */
	private static final Integer PARAMZIE9 = 9;

	/** The Constant PARAMSIZE14. */
	private static final Integer PARAMSIZE14 = 14;

	/** The Constant ACTION_CATEGORY_LIST. */
	private static final String ACTION_CATEGORY_LIST = "actionCategoryList";

	/** The Constant UPSERT_PROCESS_PROPERTY. */
	private static final String UPSERT_PROCESS_PROPERTY = "Process.upsertProcessProperty";

	/** The Constant UPSERT_PROCESS_PROPERTY. */
	private static final String UPSERT_UPDATE_PROCESS = "Process.updateProcess";

	/** The Constant DESCRIPTION. */
	private static final String FILE_DOWNLOADED = "File Downloaded";

	/** The Constant PROCESS_NAMESPACE. */
	private static final String PROCESS_NAMESPACE = "Process.";

	/** The Constant FETCH_MONITORED_PROCESSES. */
	private static final String FETCH_MONITORED_PROCESSES = PROCESS_NAMESPACE + "fetchMonitoredProcesses";

	/** The Constant FETCH_UNSUBMITTED_PROCESSES. */
	private static final String FETCH_UNSUBMITTED_PROCESSES = PROCESS_NAMESPACE + "fetchUnsubmittedProcesses";

	/** The Constant INSERT_SMARTPOINT_PROCESS. */
	private static final String INSERT_SMARTPOINT_PROCESS = PROCESS_NAMESPACE + "insertSmartpointProcess";

	private static final String INSERT_SMARTPOINT_PROCESS_DYNAMIC = PROCESS_NAMESPACE
			+ "insertSmartpointProcessDynamic";

	/** The Constant UPDATE_SMARTPOINT_PROCESS. */
	private static final String UPDATE_SMARTPOINT_PROCESS = PROCESS_NAMESPACE + "updateSmartpointProcess";

	/** The Constant UPDATE_IS_MONITORED_PROCESS. */
	private static final String UPDATE_IS_MONITORED_PROCESS = PROCESS_NAMESPACE + "updateIsMonitoredProcess";

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = PROCESS_NAMESPACE + "fetchProcessById";

	/** The Constant FETCH_PROCESS_BY_RNI_ID. */
	private static final String FETCH_PROCESS_BY_RNI_ID = PROCESS_NAMESPACE + "fetchProcessByRniId";

	/** The Constant FETCH_PROCESS_BY_FILE_NAME. */
	private static final String FETCH_PROCESS_BY_FILE_NAME = PROCESS_NAMESPACE + "fetchProcessByFileName";

	/** The Constant FETCH_PROCESS_BY_LIGHT. */
	private static final String FETCH_PROCESS_BY_LIGHT = PROCESS_NAMESPACE + "fetchProcessByLight";

	/** The Constant FETCH_PROCESSES. */
	private static final String FETCH_PROCESSES = PROCESS_NAMESPACE + "fetchProcesses";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = PROCESS_NAMESPACE + "PaginationTotalRows";

	/** The Constant FETCH_TENANT_BY_ID. */
	private static final String FETCH_TENANT_BY_ID = PROCESS_NAMESPACE + "fetchTenantById";

	/** The Constant FETCH_ALL_TENANT. */
	private static final String FETCH_ALL_TENANT = PROCESS_NAMESPACE + "fetchAllTenant";

	/** The Constant FETCH_TENANT_BY_RNI_CODE. */
	private static final String FETCH_TENANT_BY_RNI_CODE = PROCESS_NAMESPACE + "fetchTenantByRniCode";

	/** The Constant FETCH_COUNT_MONITORED_PROCESSES. */
	private static final String FETCH_COUNT_MONITORED_PROCESSES = PROCESS_NAMESPACE + "fetchCountMonitoredProcesses";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = PROCESS_NAMESPACE + "insertProcess";

	/** The Constant UPDATE_PROCESS_IS_FIRST_LEVEL. */
	private static final String UPDATE_PROCESS_IS_FIRST_LEVEL = PROCESS_NAMESPACE + "updateProcessIsFirstLevel";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.dao.IProcessDAO#fetchMonitoredProcesses(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
		paramMap.put(TENANT_ID, processRequest.getTenant().getId());
		paramMap.put(USER_ID, processRequest.getUserContext().getId());
		List<SortExpression> sortExpressions = processRequest.getSortExpressions();
		if (!ValidationUtil.isNullOrEmpty(sortExpressions) && !ValidationUtil.isNull(sortExpressions.get(0).getField()))
		{
			paramMap.put(ORDER_BY, sortExpressions.get(0));
		}

		doQueryForList(getSqlSession(), FETCH_MONITORED_PROCESSES, paramMap, response);

		setParentProcess(response.getResultsList());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchUnsubmittedProcesses()
	 */
	@Override
	public InternalResultsResponse<Process> fetchUnsubmittedProcesses()
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		doQueryForList(getSqlSession(), FETCH_UNSUBMITTED_PROCESSES, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#insertProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> insertProcess(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		HashMap<String, Object> paramMapProcess = setParamProcessToInsert(processRequest, response);

		Integer processId = (Integer)doQueryForObject(getSqlSession(), INSERT_PROCESS, paramMapProcess);

		if (ValidationUtil.isNull(processId))
		{
			return response;
		}

		processRequest.getProcess().setId(processId);
		response.addResult(processRequest.getProcess());
		setSmartpointProcess(processRequest, response);
		upsertProcessProperty(processRequest, processId);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#updateProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE6);
		HashMap<String, Object> paramMapProcessResult = new HashMap<String, Object>(PARAMSIZE4);

		paramMap.put(ID, processRequest.getProcess().getId());
		paramMap.put(END_DATETIME, processRequest.getProcess().getEndTime());
		paramMap.put(IS_MONITORED_INSTANCE, processRequest.getProcess().getIsMonitoredInstance());
		paramMap.put(IS_PROCESS_COMPLETE, processRequest.getProcess().getIsProcessComplete());
		paramMap.put(IS_SUBMITTED, processRequest.getProcess().getIsSubmitted());
		paramMap.put(RNI_CORRELATION_ID, processRequest.getProcess().getRniCorrelationId());

		doUpdate(getSqlSession(), UPSERT_UPDATE_PROCESS, paramMap, response);

		if (response.getStatus() != Status.OperationSuccess)
		{
			return response;
		}

		// Update the different process results
		if (!ValidationUtil.isNullOrEmpty(processRequest.getProcess().getProcessItems()))
		{
			for (ProcessItem process : processRequest.getProcess().getProcessItems())
			{
				paramMapProcessResult.put(PROCESS_RESULT, process.getProcessItemStatusEnumValue());
				paramMapProcessResult.put(SMARTPOINT_ID, process.getLight().getSmartPointId());
				paramMapProcessResult.put(PROCESS_ID, processRequest.getProcess().getId());
				if (process.getProcessResult() == ProcessItemStatusEnum.RNIASYNCFAILURE)
				{
					process.setProcessStatusReasonEnum(ProcessStatusReasonEnum.RNI_ASYNC_FAILURE);
				}
				if (!ValidationUtil.isNull(process.getProcessReason()))
				{
					paramMapProcessResult.put(FAILURE_ID, process.getProcessStatusReasonEnumValue());
				}

				doQueryForObject(getSqlSession(),
						UPDATE_SMARTPOINT_PROCESS, paramMapProcessResult);
				paramMapProcessResult.clear();
			}
		}

		if (!ValidationUtil.isNull(processRequest.getProcess().getLcAction())
				&& !ValidationUtil.isNullOrEmpty(processRequest.getProcess().getLcAction().getActionParameters()))
		{
			HashMap<String, Object> paramMapLcActionParameter = new HashMap<String, Object>(PARAMSIZE3);
			for (LCActionParameter lcActionParameter : processRequest.getProcess().getLcAction().getActionParameters())
			{
				paramMapLcActionParameter.put(PARAMETER_TYPE, lcActionParameter.getPropertyValue());
				paramMapLcActionParameter.put(ACTION_VALUE, lcActionParameter.getActionValue());
				paramMapLcActionParameter.put(PROCESS_ID, processRequest.getProcess().getId());
				doQueryForObject(getSqlSession(),
						UPSERT_PROCESS_PROPERTY, paramMapLcActionParameter);
				paramMapLcActionParameter.clear();
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#unmonitorProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse unmonitorProcess(ProcessRequest processRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Object> paramMap;
		for (Process process : processRequest.getProcessList())
		{
			paramMap = new HashMap<String, Object>(PARAMSIZE2);
			paramMap.put(ID, process.getId());
			paramMap.put(IS_MONITORED_INSTANCE, false);
			doUpdate(getSqlSession(), UPDATE_IS_MONITORED_PROCESS, paramMap, response);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchProcessById(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		response.addResult(fetchProcessById(processRequest.getProcess().getId()));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.dao.IProcessDAO#fetchProcessByRniId(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByRniId(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		Process process = this.fetchProcessByRniId(processRequest.getProcess().getRniCorrelationId());

		if (ValidationUtil.isNull(process))
		{
			response.setStatus(Status.NoRowsFoundError);
			return response;
		}

		response.addResult(process);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.dao.IProcessDAO#fetchProcessByFileName(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByFileName(ProcessRequest processRequest)
	{

		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		String fileName = "";
		for (LCActionParameter ac : processRequest.getProcess().getLcAction().getActionParameters())
		{
			if (ac.getProperty().equals(PropertyEnum.FILE_NAME))
			{
				fileName = ac.getActionValue();
			}
		}

		Process process = (Process)doQueryForObject(getSqlSession(), FETCH_PROCESS_BY_FILE_NAME, fileName);

		if (!ValidationUtil.isNull(process))
		{
			response.addResult(process);
			response.setStatus(Status.OperationSuccess);
			return response;
		}

		response.setStatus(Status.NoRowsFoundError);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.dao.IProcessDAO#fetchProcessByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByLight(LightRequest lightRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		doQueryForList(getSqlSession(), FETCH_PROCESS_BY_LIGHT, lightRequest.getFirstLight().getId(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.dao.IProcessDAO#fetchProcesses(com.sensus.mlc.process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcesses(InquiryProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMZIE9);
		paramMap.put(TENANT_ID, processRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, processRequest.getPageSize());
		paramMap.put(START_ROW, processRequest.getStartRow());
		ProcessFilter processFilter = processRequest.getProcessFilter();
		if (!ValidationUtil.isNull(processFilter))
		{
			if (!ValidationUtil.isNullOrEmpty(processFilter.getActionCategoryList()))
			{
				List<Integer> actionList = new ArrayList<Integer>();
				for (LCActionCategoryEnum actionCategoryEnum : processFilter
						.getActionCategoryList())
				{
					actionList.addAll(LCActionTypeEnum.getActionTypesByCategory(actionCategoryEnum));
				}
				paramMap.put(ACTION_CATEGORY_LIST, LCConvertUtil.getIdsToString(actionList, ","));
			}

			paramMap.put(END_DATE, processFilter.getEndDate());
			paramMap.put(START_DATE, processFilter.getStartDate());

			if (!ValidationUtil.isNull(processFilter.getLightTextSearch()))
			{
				if (processFilter.getLightTextSearch().getLightProperty() == LightPropertyForSearchEnum.RNI_ID)
				{
					paramMap.put(RNI_ID, processFilter.getLightTextSearch().getSearchText());

				}
				else if (processFilter.getLightTextSearch().getLightProperty() == LightPropertyForSearchEnum.EVENT_ID)
				{
					paramMap.put(EVENT_ID, processFilter.getLightTextSearch().getSearchText());
				}
				else
				{
					paramMap.put(LIGHT_PROPERTY, processFilter.getLightTextSearch().getLightProperty().getValue());
					paramMap.put(SEARCH_TEXT, processFilter.getLightTextSearch().getSearchText());
				}
			}

			if (!ValidationUtil.isNullOrEmpty(processFilter.getUserIds()))
			{
				paramMap.put(USER_IDS,
						LCConvertUtil.convertStringIdsToString(processFilter.getUserIds()));
			}
			// If user isn't Admin, he can just see your own process
			if (!processRequest.getUserContext().getUserRole().equals(ROLE_ROLE_ADMIN))
			{
				paramMap.put(USER_IDS, processRequest.getUserContext().getId());
			}

			paramMap.put(ORDER_BY, "start_datetime DESC");
			if (!ValidationUtil.isNullOrEmpty(processRequest.getSortExpressions()))
			{
				paramMap.put(ORDER_BY, processRequest.getSortExpressions().get(0));
			}
		}

		doQueryForList(getSqlSession(), FETCH_PROCESSES, paramMap, response);
		setParentProcess(response.getResultsList());

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(), PAGINATION_TOTAL_ROWS, paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchTenantById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantById(Integer id)
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();
		doQueryForList(getSqlSession(), FETCH_TENANT_BY_ID, id, internalResultsResponse);
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchAllTenant()
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchAllTenant()
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		doQueryForList(getSqlSession(), FETCH_ALL_TENANT, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchTenantByRniCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByRniCode(String rniCode)
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		doQueryForList(getSqlSession(), FETCH_TENANT_BY_RNI_CODE, rniCode, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.dao.IProcessDAO#updateCSVDownloaded(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		processRequest.setProcess(this.fetchProcessById(processRequest.getProcess().getId()));

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(ID, processRequest.getProcess().getId());
		paramMap.put(DESCRIPTION, FILE_DOWNLOADED);

		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPSERT_UPDATE_PROCESS, paramMap, response);

		return response;
	}

	/**
	 * Fetch process by id.
	 * 
	 * @param id the id
	 * @return the process
	 */
	private Process fetchProcessById(Integer id)
	{
		Process process = (Process)doQueryForObject(getSqlSession(), FETCH_PROCESS_BY_ID, id);

		if (!ValidationUtil.isNull(process.getParentProcess())
				&& !ValidationUtil.isNullOrZero(process.getParentProcess().getId()))
		{
			process.setParentProcess(fetchProcessById(process.getParentProcess().getId()));
		}

		return process;
	}

	/**
	 * Fetch process by rni id.
	 * 
	 * @param rniId the rni id
	 * @return the process
	 */
	private Process fetchProcessByRniId(String rniId)
	{
		Process process = (Process)doQueryForObject(getSqlSession(), FETCH_PROCESS_BY_RNI_ID, rniId);

		if (ValidationUtil.isNull(process))
		{
			return process;
		}

		// if a process was found, check parent
		if (!ValidationUtil.isNull(process.getParentProcess())
				&& !ValidationUtil.isNullOrZero(process.getParentProcess().getId()))
		{
			process.setParentProcess(this.fetchProcessById(process.getParentProcess()
					.getId()));
		}

		return process;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dac.IProcessDAC#fetchCountMonitoredProcesses(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(
			ProcessRequest processRequest)
	{

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(TENANT_ID, processRequest.getTenant().getId());
		paramMap.put(USER_ID, processRequest.getUserContext().getUserId());

		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		doQueryForList(getSqlSession(), FETCH_COUNT_MONITORED_PROCESSES, paramMap, response);

		return response;
	}

	/**
	 * Sets the parent process.
	 * 
	 * @param result the new parent process
	 */
	private void setParentProcess(List<Process> result)
	{
		if (ValidationUtil.isNullOrEmpty(result))
		{
			return;
		}

		// In case the Process has parent, get it too
		for (Process process : result)
		{
			if (!ValidationUtil.isNull(process.getParentProcess())
					&& !ValidationUtil.isNullOrZero(process.getParentProcess().getId()))
			{
				process.setParentProcess(this.fetchProcessById(process.getParentProcess().getId()));
			}
		}
	}

	/**
	 * Upsert process property.
	 * 
	 * @param processRequest the process request
	 * @param processId the process id
	 */
	private void upsertProcessProperty(ProcessRequest processRequest, Integer processId)
	{
		HashMap<String, Object> paramMapLcActionParameter = new HashMap<String, Object>(PARAMSIZE3);
		if (!ValidationUtil.isNullOrEmpty(processRequest.getProcess().getLcAction().getActionParameters()))
		{
			for (LCActionParameter lcActionParameter : processRequest.getProcess().getLcAction()
					.getActionParameters())

			{
				paramMapLcActionParameter.put(PARAMETER_TYPE, lcActionParameter.getPropertyValue());
				paramMapLcActionParameter.put(ACTION_VALUE, lcActionParameter.getActionValue());
				paramMapLcActionParameter.put(PROCESS_ID, processId);

				doQueryForObject(getSqlSession(), UPSERT_PROCESS_PROPERTY, paramMapLcActionParameter);
				paramMapLcActionParameter.clear();
			}
		}
	}

	/**
	 * Sets the smartpoint process.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @param processId the process id
	 * @param paramMapProcessResult the param map process result
	 */
	private void setSmartpointProcess(ProcessRequest processRequest, InternalResultsResponse<Process> response)
	{
		Process process = processRequest.getProcess();

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(PROCESS_ID, process.getId());
		paramMap.put(PROCESS_RESULT, processRequest.getProcessItemStatusEnumValue());
		paramMap.put(FAILURE_ID, processRequest.getProcessStatusReasonEnumValue());

		List<ProcessItem> processItemFailureList = processRequest.getProcessItemFailureList();
		if (ValidationUtil.isNull(process.getProcessItems())
				&& !ValidationUtil.isNull(processRequest.getSearchLight()))
		{
			paramMap.put(PROCESS_ITEM_FAILURE_LIST, processItemFailureList);

			InquiryLightRequest lightRequest = createInquiryLightRequest(processRequest);
			paramMap.putAll(getParametersToFetchAllLights(lightRequest));
			doInsert(getSqlSession(), INSERT_SMARTPOINT_PROCESS_DYNAMIC, paramMap, response);
			return;
		}

		// Add the different process results
		insertProcessItem(process.getProcessItems(), paramMap, response);

		// Add the different process results
		insertProcessItem(processItemFailureList, paramMap, response);
	}

	/**
	 * Insert process item.
	 * 
	 * @param processItems the process items
	 * @param paramMap the param map
	 * @param response the response
	 */
	private void insertProcessItem(List<ProcessItem> processItems,
			HashMap<String, Object> paramMap, InternalResultsResponse<Process> response)
	{
		if (ValidationUtil.isNullOrEmpty(processItems))
		{
			return;
		}

		for (ProcessItem processItem : processItems)
		{
			paramMap.put(PROCESS_RESULT, processItem.getProcessItemStatusEnumValue());
			paramMap.put(SMARTPOINT_ID, processItem.getLight().getSmartPointId());
			paramMap.put(FAILURE_ID, processItem.getProcessStatusReasonEnumValue());

			Integer idProcessResult =
					(Integer)doQueryForObject(getSqlSession(), INSERT_SMARTPOINT_PROCESS, paramMap);

			if (ValidationUtil.isNullOrZero(idProcessResult))
			{
				response.setStatus(Status.PersistenceError);
				return;
			}
		}
	}

	/**
	 * Sets the param process to insert.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 * @return the hash map
	 */
	private HashMap<String, Object> setParamProcessToInsert(ProcessRequest processRequest,
			InternalResultsResponse<Process> response)
	{
		Process process = processRequest.getProcess();
		LCAction lcAction = process.getLcAction();

		HashMap<String, Object> paramMapProcess = new HashMap<String, Object>(PARAMSIZE14);
		paramMapProcess.put(DESCRIPTION, process.getDescription());
		paramMapProcess.put(LC_ACTION_DESCRIPTION, lcAction.getDescription());
		paramMapProcess.put(START_DATETIME, process.getStartTime());
		paramMapProcess.put(RNI_CORRELATION_ID, process.getRniCorrelationId());
		paramMapProcess.put(END_DATETIME, process.getEndTime());
		paramMapProcess.put(TENANT_ID, processRequest.getTenant().getId());
		paramMapProcess.put(IS_FIRST_LEVEL, true);
		paramMapProcess.put(LC_ACTION_ID, lcAction.getActionType().getValue());
		paramMapProcess.put(ESTIMATED_SECONDS_TO_COMPLETE, process.getEstimatedSecondsToComplete());
		paramMapProcess.put(CREATE_USER, processRequest.getUserContext().getUserId());
		paramMapProcess.put(IS_MONITORED_INSTANCE, process.getIsMonitoredInstance());
		paramMapProcess.put(IS_PROCESS_COMPLETE, process.getIsProcessComplete());
		paramMapProcess.put(IS_SUBMITTED, process.getIsSubmitted());

		// Only if there is a parent
		if (!ValidationUtil.isNull(process.getParentProcess()))
		{
			paramMapProcess.put(PARENT_PROCESS_ID, process.getParentProcess().getId());

			// Update the parent so it is not marked as first level anymore
			HashMap<String, Object> paramMapAddChild = new HashMap<String, Object>(PARAMSIZE2);
			paramMapAddChild.put(PROCESS_ID, process.getParentProcess().getId());
			paramMapAddChild.put(IS_FIRST_LEVEL, false);

			doUpdate(getSqlSession(), UPDATE_PROCESS_IS_FIRST_LEVEL, paramMapAddChild, response);
		}

		// if it is group intensity, than the light intesinty is the second Action Parameter,
		// if it is just light intensity, than the light intensity is the first one.
		if (lcAction.getActionType() == LCActionTypeEnum.SET_INTENSITY_BY_GRP)
		{
			paramMapProcess.put(LIGHT_INTENSITY, lcAction.getActionParameters().get(1).getActionValue());
			return paramMapProcess;
		}

		if ((lcAction.getActionType() == LCActionTypeEnum.TURN_OFF)
				|| (lcAction.getActionType() == LCActionTypeEnum.TURN_ON)
				|| (lcAction.getActionType() == LCActionTypeEnum.DIM))
		{
			paramMapProcess.put(LIGHT_INTENSITY, lcAction.getActionParameters().get(0).getActionValue());
		}

		return paramMapProcess;
	}

	@Override
	public InternalResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResponse response = new InternalResponse();
		List<Process> list = fetchProcesses(inquiryProcessRequest).getResultsList();

		CSVFileGenerator.create(inquiryProcessRequest.getFileName(),
				new ProcessCSVDataSource(inquiryProcessRequest, list), response);

		return response;
	}
}
