package com.sensus.lc.process.bcl.impl;

import static com.sensus.lc.base.util.LCDateUtil.DEFAULT_DATE_PATTERN_TO_FILENAME;
import static com.sensus.lc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.lc.base.util.LCHelp.createProcessRequestToCSV;
import static com.sensus.lc.base.util.LCHelp.treatReturnFromBCL;
import static com.sensus.lc.process.util.ProcessUtil.translateMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.oxm.UnmarshallingFailureException;
import org.supercsv.cellprocessor.Optional;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.util.ConvertTimeZone;
import com.sensus.lc.base.util.LCCSVUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.base.util.RemoteUserContext;
import com.sensus.lc.base.util.RemoteUserContextHolder;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.bcl.ILightCSVBCL;
import com.sensus.lc.light.bcl.ILightNotificationHistoryBCL;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.light.model.criteria.ProcessCriteria;
import com.sensus.lc.light.model.request.CSVRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSummaryCSVRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.bcl.IProcessCommunicationGateway;
import com.sensus.lc.process.dac.IProcessDAC;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.InquiryProcessRequest;
import com.sensus.lc.process.model.request.ProcessCSVRequest;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;
import com.sensus.lc.process.util.ProcessUtil;
import com.sensus.lc.schedule.bcl.IScheduleBCL;
import com.sensus.lc.server.client.MlcServerClient;
import com.sensus.lc.tenant.bcl.ITenantBCL;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.AbortTransactionRequest;
import com.sensus.mlc.mlcserver.type.AbortTransactionResponse;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.MessageType;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ObjectFactory;
import com.sensus.mlc.mlcserver.type.PingURLRequest;
import com.sensus.mlc.mlcserver.type.PingURLResponse;
import com.sensus.mlc.mlcserver.type.Status;

/**
 * The Class ProcessBCLImpl.
 */
public class ProcessBCLImpl implements IProcessBCL
{

	private static final String CSV_EXTENSION = ".csv";
	private static final String SENSUS_MLC_PROCESSVALIDATOR_PROCESS_ALREADY_RETRY =
			"sensus.mlc.processvalidator.process.already.retry";
	private static final String CHECK_RNI_STATUS_CONNECTION_REFUSED =
			"Check RNI Status: Connection refused by Gateway. Gateway may not be available. ";

	private LCHelp lcHelp; // injected by Spring through setter
	private IProcessDAC processDAC; // injected by Spring through setter
	private ILightBCL lightBCL; // injected by Spring
	private ILightNotificationHistoryBCL lightNotificationHistoryBCL;
	private ITenantBCL tenantBCL;
	private ILightCSVBCL lightCSVBCL;
	private MlcServerClient mlcGatewayWs; // injected by Spring
	private ObjectFactory factory; // injected by Spring
	private String tempFilePath; // injected by Spring - come from sensus-lc.properties;
	private String systemId; // injected by Spring
	private String systemPwd; // injected by Spring
	private List<CSVColumn> lightSummaryExportColumns;

	private Map<String, IProcessCommunicationGateway> processesCommunicationGatewayFactory;
	private Map<Integer, Boolean> gatewayActiveMap;
	private List<CSVColumn> processExportColumns;

	private static final String DEFAULT_PROCESS_BCL_EXCEPTION_MSG = "sensus.mlc.processbclimpl.defaultexception";
	private static final String RNI_OFFLINE_EXCEPTION = "sensus.mlc.processbclimpl.rniofflineexception";

	/** The Constant GROUP_BCL_BEAN. */
	private static final String LIGHT_CSV_BCL_BEAN = "lightCSVBCLTarget";

	/** The LOG. */
	private static final Log LOG = LogFactory.getLog(ProcessBCLImpl.class);

	/** The Constant RNI_SYNC_FAILURE. */
	private static final String RNI_SYNC_FAILURE = "RNI sync failure: ";
	private static final String START_TIME = "startTime";

	/**
	 * Gets the processes communication gateway factory.
	 * 
	 * @return the processes communication gateway factory
	 */
	public Map<String, IProcessCommunicationGateway> getProcessesCommunicationGatewayFactory()
	{
		if (processesCommunicationGatewayFactory == null)
		{
			processesCommunicationGatewayFactory =
					SensusAppContext.getApplicationContext().getBeansOfType(IProcessCommunicationGateway.class);
		}

		return processesCommunicationGatewayFactory;
	}

	/**
	 * Gets the process communication gateway.
	 * 
	 * @param action the action
	 * @return the process communication gateway
	 */
	public IProcessCommunicationGateway getProcessCommunicationGateway(LCAction action)
	{
		if (ValidationUtil.isNull(action) || ValidationUtil.isNull(action.getActionType()))
		{
			return null;
		}

		Set<String> keys = getProcessesCommunicationGatewayFactory().keySet();
		for (String key : keys)
		{
			IProcessCommunicationGateway processCommunicationGateway =
					getProcessesCommunicationGatewayFactory().get(key);

			if (processCommunicationGateway.isSupportedAction(action.getActionType()))
			{
				return processCommunicationGateway;
			}
		}

		return null;
	}

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Sets the process dac.
	 * 
	 * @param processDAC the new process dac
	 */
	public void setProcessDAC(IProcessDAC processDAC)
	{
		this.processDAC = processDAC;
	}

	/**
	 * Gets the process dac.
	 * 
	 * @return the process dac
	 */
	public IProcessDAC getProcessDAC()
	{
		return processDAC;
	}

	/**
	 * Gets the schedule bcl.
	 * 
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return (IScheduleBCL)SensusAppContext.getApplicationContext().getBean("scheduleBCLTarget");
	}

	/**
	 * Gets the light bcl.
	 * 
	 * @return the light bcl
	 */
	public ILightBCL getLightBCL()
	{
		if (lightBCL == null)
		{
			lightBCL = (ILightBCL)SensusAppContext.getApplicationContext().getBean(
					"lightBCLTarget");
		}

		return lightBCL;
	}

	/**
	 * Sets the light bcl.
	 * 
	 * @param lightBCL the new light bcl
	 */
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * Gets the light notification history bcl.
	 * 
	 * @return the light notification history bcl
	 */
	public ILightNotificationHistoryBCL getLightNotificationHistoryBCL()
	{
		return lightNotificationHistoryBCL;
	}

	/**
	 * Sets the light notification history bcl.
	 * 
	 * @param lightNotificationHistoryBCL the new light notification history bcl
	 */
	public void setLightNotificationHistoryBCL(ILightNotificationHistoryBCL lightNotificationHistoryBCL)
	{
		this.lightNotificationHistoryBCL = lightNotificationHistoryBCL;
	}

	/**
	 * Gets the tenant bcl.
	 * 
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 * 
	 * @param tenantBCL the new tenant bcl
	 */
	public void setTenantBCL(ITenantBCL tenantBCL)
	{
		this.tenantBCL = tenantBCL;
	}

	/**
	 * @return the lightCSVBCL
	 */
	public ILightCSVBCL getLightCSVBCL()
	{
		if (lightCSVBCL == null)
		{
			lightCSVBCL = (ILightCSVBCL)SensusAppContext.getApplicationContext().getBean(LIGHT_CSV_BCL_BEAN);
		}

		return lightCSVBCL;

	}

	/**
	 * @param lightCSVBCL the lightCSVBCL to set
	 */
	public void setLightCSVBCL(ILightCSVBCL lightCSVBCL)
	{
		this.lightCSVBCL = lightCSVBCL;
	}

	/**
	 * Sets the mlc gateway ws.
	 * 
	 * @param mlcGatewayWs the new mlc gateway ws
	 */
	public void setMlcGatewayWs(MlcServerClient mlcGatewayWs)
	{
		this.mlcGatewayWs = mlcGatewayWs;
	}

	/**
	 * Gets the mlc gateway ws.
	 * 
	 * @return the mlc gateway ws
	 */
	public MlcServerClient getMlcGatewayWs()
	{
		return mlcGatewayWs;
	}

	/**
	 * Sets the factory.
	 * 
	 * @param factory the new factory
	 */
	public void setFactory(ObjectFactory factory)
	{
		this.factory = factory;
	}

	/**
	 * Gets the factory.
	 * 
	 * @return the factory
	 */
	public ObjectFactory getFactory()
	{
		return factory;
	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getSystemId()
	{
		return systemId;
	}

	/**
	 * Sets the system id.
	 * 
	 * @param systemId the new system id
	 */
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}

	/**
	 * Gets the system pwd.
	 * 
	 * @return the system pwd
	 */
	public String getSystemPwd()
	{
		return systemPwd;
	}

	/**
	 * Sets the system pwd.
	 * 
	 * @param systemPwd the new system pwd
	 */
	public void setSystemPwd(String systemPwd)
	{
		this.systemPwd = systemPwd;
	}

	/**
	 * Gets the temp file path.
	 * 
	 * @return the temp file path
	 */
	public String getTempFilePath()
	{
		return tempFilePath;
	}

	/**
	 * Sets the temp file path.
	 * 
	 * @param tempFilePath the new temp file path
	 */
	public void setTempFilePath(String tempFilePath)
	{
		this.tempFilePath = tempFilePath;
	}

	/**
	 * Gets the process export columns.
	 * 
	 * @return the process export columns
	 */
	public List<CSVColumn> getProcessExportColumns()
	{
		return processExportColumns;
	}

	/**
	 * Sets the process export columns.
	 * 
	 * @param processExportColumns the new process export columns
	 */
	public void setProcessExportColumns(List<CSVColumn> processExportColumns)
	{
		this.processExportColumns = processExportColumns;
	}

	/**
	 * Gets the light summary export columns.
	 * 
	 * @return the light summary export columns
	 */
	public List<CSVColumn> getLightSummaryExportColumns()
	{
		return lightSummaryExportColumns;
	}

	/**
	 * Sets the light summary export columns.
	 * 
	 * @param lightSummaryExportColumns the new light summary export columns
	 */
	public void setLightSummaryExportColumns(List<CSVColumn> lightSummaryExportColumns)
	{
		this.lightSummaryExportColumns = lightSummaryExportColumns;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#fetchProcessById(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getProcessDAC().fetchProcessById(processRequest);

		translateMessage(response.getResultsList(), processRequest.getUserContext());

		return response;
	}

	@Override
	public InternalResultsResponse<Process> fetchSummaryByProcessId(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchSummaryByProcessId(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#fetchMonitoredProcesses(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getProcessDAC().fetchMonitoredProcesses(processRequest);

		translateMessage(response.getResultsList(), processRequest.getUserContext());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#insertProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> insertProcess(ProcessRequest processRequest)
	{
		Process process = processRequest.getProcess();
		if (!ValidationUtil.isNull(process) && ValidationUtil.isNull(process.getIsMonitoredInstance()))
		{
			process.setIsMonitoredInstance(false);
		}
		return getProcessDAC().insertProcess(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#unmonitorProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse unmonitorProcess(ProcessRequest processRequest)
	{
		return getProcessDAC().unmonitorProcess(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#updateProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{
		return getProcessDAC().updateProcess(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#fetchProcessByRniId(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByRniId(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchProcessByRniId(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#fetchProcessByFileName(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByFileName(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchProcessByFileName(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#retryProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse retryProcess(ProcessRequest processRequest)
	{
		this.persistLogInfo("**** Beginning ProcessBCL.retryProcess");

		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		// Check availability link status
		Tenant tenant = processRequest.getUserContext().getTenant();
		if (!fetchRniLinkStatus(tenant).getFirstResult())
		{
			response.setStatus(InternalResponse.Status.ExceptionError);
			return response;
		}

		// Fetch process parent
		response = fetchProcessById(processRequest);
		if (response.isInError())
		{
			response.setStatus(InternalResponse.Status.ExceptionError);
			return response;
		}

		Process parentLRP = response.getFirstResult();

		if ((!ValidationUtil.isNull(parentLRP))
				&& (!ValidationUtil.isNull(parentLRP.getHasChild()) && parentLRP.getHasChild()))
		{
			response.setStatus(InternalResponse.Status.ExceptionError);
			response.addMessage(SENSUS_MLC_PROCESSVALIDATOR_PROCESS_ALREADY_RETRY, MessageSeverity.Error,
					MessageLevel.None);
			return response;
		}

		// create the child LRP that will be inserted
		List<Light> lightList = new ArrayList<Light>();
		for (ProcessItem parentProcessItem : parentLRP.getProcessItems())
		{
			if (ValidationUtil.isNull(parentProcessItem.getLight()))
			{
				continue;
			}
			lightList.add(parentProcessItem.getLight());
		}

		LCAction action = parentLRP.getLcAction();
		Process process = ProcessUtil.generateProcess(true, action, lightList);

		// the retry process is always monitored, so the first parameter is true
		process.setIsParent(false);
		process.setParentProcess(parentLRP);
		process.setIsSubmitted(true);
		process.setRniCorrelationId(LCHelp.generateRniCorrelationId());

		processRequest.getProcessList().clear();
		processRequest.setProcess(process);
		sendActionToGateway(processRequest, action, response);

		this.persistLogInfo("**** Finished ProcessBCL.retryProcess");

		// Insert process in database
		return insertProcess(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#abortProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse abortProcess(ProcessRequest processRequest)
	{
		this.persistLogInfo("**** Beginning ProcessBCL.abortProcess");

		InternalResponse response = new InternalResponse();
		Process process = getProcessDAC().fetchProcessById(processRequest).getFirstResult();
		processRequest.getProcessList().clear();
		processRequest.setProcess(process);

		AbortTransactionRequest request = getFactory().createAbortTransactionRequest();
		request.setTransactionID(process.getRniCorrelationId());
		request.setCustomerID(process.getTenant().getRniCode());

		// Comment this to disable security
		setRemoteUserCredentials();
		getMlcGatewayWs().getWsTemplate().setDefaultUri(
				processRequest.getUserContext().<Tenant> getTenant().getGatewayURL());

		AbortTransactionResponse wsResponse = getMlcGatewayWs().setAborted(request);

		if (!ValidationUtil.isNull(wsResponse.getStatus()) && (wsResponse.getStatus() != Status.SUCCESS))
		{
			this.persistLogInfo("Process not aborted.");

			for (MessageInfo message : wsResponse.getMessage())
			{
				if (!ValidationUtil.isNull(message.getMessageType())
						&& message.getMessageType().equals(MessageType.TRANSACTION_NOT_FOUND))
				{
					this.persistLogInfo("Process failed message: " + message.getMessageText());
					process.setIsProcessComplete(true);
					process.setEndTime(LCDateUtil.getNewDateUTC());
					for (ProcessItem processItem : process.getProcessItems())
					{
						if (processItem.getProcessResult() == ProcessItemStatusEnum.PENDING)
						{
							processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);
							processItem.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
						}
					}

					return getProcessDAC().updateProcess(processRequest);
				}
				response.setStatus(InternalResponse.Status.ExternalError);
			}

			this.persistLogInfo("**** Finished ProcessBCL.abortProcess. Status=" + wsResponse.getStatus());
			return response;
		}

		// update Process to complete and mark all Process Results that are not complete
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		for (ProcessItem processItem : process.getProcessItems())
		{
			if (processItem.getProcessResult() == ProcessItemStatusEnum.PENDING)
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.MLCFAILURE);
				processItem.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
			}
		}

		this.persistLogInfo("**** Finished ProcessBCL.abortProcess. Beginning ProcessDAC.updateProcess. Status="
				+ wsResponse.getStatus());
		return getProcessDAC().updateProcess(processRequest);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#fetchProcessByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessByLight(LightRequest lightRequest)
	{
		return getProcessDAC().fetchProcessByLight(lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#fetchAllProcess(com.sensus.mlc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> fetchAllProcess(InquiryProcessRequest processRequest)
	{
		InternalResultsResponse<Process> response = getProcessDAC().fetchAllProcess(processRequest);

		translateMessage(response.getResultsList(), processRequest.getUserContext());
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#submitProcess(com.sensus.mlc.process.model.request.ProcessRequest,
	 * com.sensus.mlc.process.model.LCAction)
	 */
	@Override
	public InternalResultsResponse<Process> submitProcess(ProcessRequest processRequest, LCAction action)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		Tenant tenant = processRequest.getUserContext().<Tenant> getTenant();
		if (!fetchRniLinkStatus(tenant).getFirstResult())
		{
			response.addMessage(RNI_OFFLINE_EXCEPTION, MessageSeverity.Error, MessageLevel.Other);
			response.setStatus(InternalResponse.Status.ExceptionError);
			return response;
		}

		List<ProcessItem> processItemList = getProcessItem(processRequest);
		Process process = ProcessUtil.generateProcess(processRequest.isMonitored(), action, processItemList.size());
		processRequest.setProcess(process);
		if (!ValidationUtil.isNullOrEmpty(processItemList))
		{
			process.setRniCorrelationId(LCHelp.generateRniCorrelationId());
			process.setProcessItems(processItemList);
			process.setIsSubmitted(true);

			sendActionToGateway(processRequest, action, response);
			if (response.isInError())
			{
				return response;
			}
		}

		// Insert process in database
		return insertProcess(processRequest);
	}

	/**
	 * Call this before the Web Service is invoked.
	 * Sets the userid and password in the thread local <code>RemoteUserContext</code>.
	 * These credentials will be put into a SOAP
	 * header by a WS client interceptor.
	 * Currently reading Uid/Pwd from UserContext Object
	 * 
	 */
	private void setRemoteUserCredentials()
	{
		RemoteUserContext remoteUserCtx = RemoteUserContextHolder.getContext();
		if (remoteUserCtx == null)
		{
			remoteUserCtx = new RemoteUserContext();
		}
		remoteUserCtx.setUserName(getSystemId());
		remoteUserCtx.setPassword(getSystemPwd());
		RemoteUserContextHolder.setContext(remoteUserCtx);
	}

	/**
	 * Check sync failure.
	 * 
	 * @param wsResponse the ws response
	 * @param processRequest the process request
	 */
	private void checkSyncFailure(MlcGatewayResponse wsResponse, ProcessRequest processRequest)
	{
		if (ValidationUtil.isNull(wsResponse.getStatus()) || (Status.SUCCESS == wsResponse.getStatus()))
		{
			return;
		}

		Process process = processRequest.getProcess();
		process.setIsProcessComplete(true);
		process.setEndTime(getNewDateUTC());

		for (ProcessItem processItem : process.getProcessItems())
		{
			if ((processItem.getProcessReason() != ProcessStatusReasonEnum.LIGHT_PROTECTED) &&
					(processItem.getProcessReason() != ProcessStatusReasonEnum.LIGHT_DEACTIVATED) &&
					(processItem.getProcessReason() != ProcessStatusReasonEnum.LIGHT_IN_MAINTENANCE))
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.RNISYNCFAILURE);
				processItem.setProcessStatusReasonEnum(ProcessStatusReasonEnum.RNI_SYNC_FAILURE);
				if (LOG.isInfoEnabled())
				{
					for (MessageInfo message : wsResponse.getMessage())
					{
						LOG.info(RNI_SYNC_FAILURE + message);
					}
				}
			}
		}

		// if the light does not exists at RNI, set as deactivated
		insertMessageToLightNotExisting(wsResponse, process, processRequest.getUserContext());
	}

	/**
	 * Insert message to light not existing.
	 * 
	 * @param wsResponse the ws response
	 * @param process the process
	 * @param userContext the user context
	 */
	private void insertMessageToLightNotExisting(MlcGatewayResponse wsResponse, Process process, UserContext userContext)
	{
		if (ValidationUtil.isNullOrEmpty(wsResponse.getMessage()))
		{
			return;
		}

		for (MessageInfo message : wsResponse.getMessage())
		{
			if (ValidationUtil.isNull(message.getMessageType())
					|| !message.getMessageType().equals(MessageType.DEVICE_DOES_NOT_EXIST))
			{
				continue;
			}

			Integer lightId = Integer.parseInt(message.getObjectId());
			this.persistLogInfo("Light " + message.getObjectId() + " does not exists at RNI, setting as DEACTIVATED.");

			FetchByIdRequest request = new FetchByIdRequest(lightId);
			request.setUserContext(userContext);

			InternalResultsResponse<Light> internalResult = getLightBCL().fetchById(request);

			if (!internalResult.hasResults())
			{
				continue;
			}

			NotificationHistory notificationHistory = new NotificationHistory();
			notificationHistory.setMessageDate(new Date());
			notificationHistory.setNotificationType(NotificationTypeEnum.SET_LIGHT_INTENSITY);
			notificationHistory.setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);

			if ((process.getLcAction().getActionType() == LCActionTypeEnum.TURN_OFF)
					|| (process.getLcAction().getActionType() == LCActionTypeEnum.TURN_ON)
					|| (process.getLcAction().getActionType() == LCActionTypeEnum.DIM))
			{
				notificationHistory.setNotificationType(NotificationTypeEnum.SET_LIGHT_BLINK);
			}

			notificationHistory.setLightId(internalResult.getFirstResult().getId());

			NotificationHistoryMaintenanceRequest maintenanceRequest = new NotificationHistoryMaintenanceRequest();
			maintenanceRequest.setNotificationHistory(notificationHistory);

			getLightNotificationHistoryBCL().insertNotificationHistory(maintenanceRequest);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#checkRniStatus()
	 */
	@Override
	public void checkRniStatus()
	{
		// Tests all gateway URL in the Tenant data table.
		PingURLRequest request = getFactory().createPingURLRequest();
		String lastGatewayURL = "";
		Boolean lastGatewayActive;

		UserContext userContext = new UserContext();
		userContext.setUserId(getSystemId());

		// prepare the credentials to call the Web Service
		setRemoteUserCredentials();

		PingURLResponse wsResponse = new PingURLResponse();
		for (Tenant tenant : getTenantBCL().fetchAllTenant().getResultsList())
		{
			try
			{
				// if the url gateway location is the same of the last, it's not necessary to ping again.
				if (!lastGatewayURL.equals(tenant.getGatewayURL()))
				{
					getMlcGatewayWs().getWsTemplate().setDefaultUri(tenant.getGatewayURL());
					wsResponse = getMlcGatewayWs().pingURL(request);
				}
				lastGatewayActive = !ValidationUtil.isNull(wsResponse);
				lastGatewayURL = tenant.getGatewayURL();
				setGatewayActive(tenant, lastGatewayActive);
			}
			catch (Exception e)
			{
				persistLogError(CHECK_RNI_STATUS_CONNECTION_REFUSED, e);
				setGatewayActive(tenant, false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#fetchRniLinkStatus()
	 */
	@Override
	public InternalResultsResponse<Boolean> fetchRniLinkStatus(Tenant tenant)
	{
		InternalResultsResponse<Boolean> internalResultsResponse = new InternalResultsResponse<Boolean>();
		for (Integer key : gatewayActiveMap.keySet())
		{
			if (key.equals(tenant.getId()))
			{
				internalResultsResponse.addResult(gatewayActiveMap.get(key));
				return internalResultsResponse;
			}
		}
		internalResultsResponse.addResult(false);
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#setGatewayActive(com.sensus.mlc.base.model.Tenant, java.lang.Boolean)
	 */
	@Override
	public void setGatewayActive(Tenant tenant, Boolean value)
	{
		if (gatewayActiveMap == null)
		{
			gatewayActiveMap = new HashMap<Integer, Boolean>();
		}
		gatewayActiveMap.put(tenant.getId(), value);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#updateCSVDownloaded(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		return getProcessDAC().updateCSVDownloaded(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#generateSumaryFileCSV(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse generateSumaryFileCSV(ProcessRequest processRequest)
	{
		// Create the process criteria in order to fetch all lights by process
		ProcessCriteria processCriteria = new ProcessCriteria();

		// process id that has informations that will be export the csv file
		processCriteria.setProcessId(processRequest.getProcessList().get(0).getId());
		processCriteria.setFailed(false);

		List<ProcessItem> processItems = processRequest.getProcessList().get(0).getProcessItems();
		if (!ValidationUtil.isNullOrEmpty(processItems))
		{
			if (ProcessItemStatusEnum.MLCFAILURE == processItems.get(0).getProcessResult())
			{
				processCriteria.setFailed(true);
			}
		}

		LightRequest lightRequest = new LightRequest();
		lightRequest.setProcessCriteria(processCriteria);
		lightRequest.setUserContext(processRequest.getUserContext());

		InternalResultsResponse<Light> internalResultResponse = getLightBCL().fetchAllByRequest(lightRequest);

		ProcessResponse response = new ProcessResponse();

		if (internalResultResponse.isInError())
		{
			response.setOperationSuccess(false);
			response.addOperationFailedMessage(internalResultResponse.getMessageInfoList());
			return response;
		}

		LightSummaryCSVRequest summaryCSVRequest = new LightSummaryCSVRequest();
		summaryCSVRequest.setUserContext(processRequest.getUserContext());
		summaryCSVRequest.setFileName(processRequest.getFileName());
		summaryCSVRequest.setProcessId(processRequest.getProcessId());

		CSVInternalResponse csvInternalResponse = getLightCSVBCL().generateLightSummaryFileCSV(summaryCSVRequest);
		csvInternalResponse.setFileName(processRequest.getFileName());

		if (csvInternalResponse.isInError())
		{
			response.setOperationSuccess(false);
			response.addOperationFailedMessage(csvInternalResponse.getMessageInfoList());
			return response;
		}

		// Produce the CSV file handling any errors.
		LCCSVUtil.processCSVRequest(summaryCSVRequest, csvInternalResponse, internalResultResponse.getResultsList(),
				getLightSummaryExportColumns());

		if (!csvInternalResponse.isInError())
		{
			if (summaryCSVRequest.getProcessId() != null)
			{
				LCCSVUtil.handleCSVResponse(summaryCSVRequest, csvInternalResponse, this);
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#fetchCountMonitoredProcesses(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchCountMonitoredProcesses(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#insertCSVProcess(com.sensus.mlc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		UserContext userContext = request.getUserContext();
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN_TO_FILENAME);
		String dateFormated = format.format(getNewDateUTC());
		String fileName = tempFilePath + userContext.getUserId() + dateFormated + CSV_EXTENSION;

		ProcessRequest processRequest = createProcessRequestToCSV(request, fileName, NumberUtils.INTEGER_ZERO);

		InternalResultsResponse<Process> response = insertProcess(processRequest);
		ProcessResponse processResponse = new ProcessResponse();
		processResponse.setProcesses(response.getResultsList());
		processResponse.setFileName(fileName);

		if (response.isInError())
		{
			treatReturnFromBCL(processResponse, response, DEFAULT_PROCESS_BCL_EXCEPTION_MSG);
		}

		return processResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.process.bcl.IProcessBCL#generateFileCSV(com.sensus.lc.process.model.request.ProcessCSVRequest)
	 */
	@Override
	public CSVInternalResponse generateFileCSV(ProcessCSVRequest request)
	{
		persistLogInfo("Generating CSV file for processes");

		CSVInternalResponse response = new CSVInternalResponse(request.getFileName());

		InternalResultsResponse<Process> processResponse = fetchProcessToExport(request);

		if (processResponse.isInError() || !processResponse.hasResults())
		{
			response.merge(processResponse);
			return response;
		}

		prepareCSV(getProcessExportColumns(), request);

		LCCSVUtil.processCSVRequest(request, response, processResponse.getResultsList(), getProcessExportColumns());

		if (!response.isInError())
		{
			if (request.getProcessId() != null)
			{
				LCCSVUtil.handleCSVResponse(request, response, this);
			}
		}

		return response;
	}

	/**
	 * Gets the process item.
	 * 
	 * @param processRequest the process request
	 * @return the process item
	 */
	private List<ProcessItem> getProcessItem(ProcessRequest processRequest)
	{
		// Set all filters to light request
		LightRequest request = new LightRequest(processRequest.getUserContext());
		request.setLightCriteria(processRequest.getLightCriteria());
		request.setActionCriteria(processRequest.getActionCriteria());
		request.setGroupCriteria(processRequest.getGroupCriteria());
		request.setAlertCriteria(processRequest.getAlertCriteria());
		request.setAddressCriteria(processRequest.getAddressCriteria());
		request.setProcessCriteria(processRequest.getProcessCriteria());
		request.setScheduleCriteria(processRequest.getScheduleCriteria());
		request.setConfigurationCriteria(processRequest.getConfigurationCriteria());
		request.setOperationalDataCriteria(processRequest.getOperationalDataCriteria());
		request.setTagCriteria(processRequest.getTagCriteria());
		request.setNotificationHistoryCriteria(processRequest.getNotificationHistoryCriteria());
		request.setLightCustomSearchCriteria(processRequest.getLightCustomSearchCriteria());

		// Fetch all lights
		InternalResultsResponse<Light> lightResponse = getLightBCL().fetchAllByRequest(request);
		if (!lightResponse.hasResults())
		{
			return new ArrayList<ProcessItem>();
		}

		List<Light> lights = lightResponse.getResultsList();
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();

		for (Light light : lights)
		{
			ProcessItem processItem = new ProcessItem();
			processItem.setLight(light);
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.PENDING);
			processItems.add(processItem);
		}

		return processItems;
	}

	/**
	 * Send to gateway.
	 * 
	 * @param processRequest the process request
	 * @param action the action
	 * @param response the response
	 */
	private void sendActionToGateway(ProcessRequest processRequest, LCAction action,
			InternalResultsResponse<Process> response)
	{
		try
		{
			IProcessCommunicationGateway processCommunicatinoGateway = getProcessCommunicationGateway(action);

			if (ValidationUtil.isNull(processCommunicatinoGateway))
			{
				this.persistLogInfo("No such submit process to action type: " + action.getActionType());
				response.setStatus(com.sensus.common.model.response.InternalResponse.Status.ExceptionError);
			}

			MlcGatewayResponse wsResponse = processCommunicatinoGateway.sendAction(processRequest);
			if (wsResponse == null)
			{
				Process process = processRequest.getProcess();
				process.setIsProcessComplete(true);
				process.setEndTime(getNewDateUTC());
				completeProcessItems(process.getProcessItems());
				return;
			}

			checkSyncFailure(wsResponse, processRequest);
		}
		catch (UnmarshallingFailureException ufe)
		{
			persistLogError("Submit Process: Marshalling failure: ", ufe);
			response.setStatus(com.sensus.common.model.response.InternalResponse.Status.ExceptionError);
		}
		catch (Exception e)
		{
			setGatewayActive(processRequest.getUserContext().<Tenant> getTenant(), false);
			persistLogError(CHECK_RNI_STATUS_CONNECTION_REFUSED, e);

			response.addMessage(RNI_OFFLINE_EXCEPTION, MessageSeverity.Error, MessageLevel.Other);
			response.setStatus(InternalResponse.Status.ExceptionError);
		}
	}

	/**
	 * Complete process items.
	 * 
	 * @param processItems the process items
	 */
	private void completeProcessItems(List<ProcessItem> processItems)
	{
		if (ValidationUtil.isNullOrEmpty(processItems))
		{
			return;
		}

		for (ProcessItem processItem : processItems)
		{
			if ((processItem.getProcessReason() != ProcessStatusReasonEnum.LIGHT_PROTECTED) &&
					(processItem.getProcessReason() != ProcessStatusReasonEnum.LIGHT_DEACTIVATED) &&
					(processItem.getProcessReason() != ProcessStatusReasonEnum.LIGHT_IN_MAINTENANCE))
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
			}
		}
	}

	/**
	 * Persist log info.
	 * 
	 * @param logText the log text
	 */
	private void persistLogInfo(String logText)
	{
		this.persistLogInfo(logText, null);
	}

	/**
	 * Persist log info.
	 * 
	 * @param logText the log text
	 * @param e the e
	 */
	private void persistLogInfo(String logText, Exception e)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(logText, e);
		}
	}

	private void persistLogError(String logText, Exception e)
	{
		if (LOG.isErrorEnabled())
		{
			LOG.error(logText, e);
		}
	}

	/**
	 * Fetch process to export.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> fetchProcessToExport(ProcessCSVRequest request)
	{
		InquiryProcessRequest inquiryProcessRequest = request.getInquiryProcessRequest();
		inquiryProcessRequest.setUserContext(request.getUserContext());

		InternalResultsResponse<Process> processResponse = fetchAllProcess(inquiryProcessRequest);

		return processResponse;
	}

	/**
	 * Prepare csv.
	 * 
	 * @param columnsToExport the columns to export
	 * @param request the request
	 */
	private static void prepareCSV(List<CSVColumn> columnsToExport, CSVRequest request)
	{
		if (ValidationUtil.isNullOrEmpty(columnsToExport))
		{
			return;
		}

		// look for columns date to apply time zone
		for (CSVColumn col : columnsToExport)
		{
			if (START_TIME.equals(col.getName()))
			{
				col.setWriteCellProcessor(new Optional(new ConvertTimeZone(LCDateUtil.DEFAULT_HOUR_FORMAT, request
						.getTimezone(), null)));
			}
		}
	}
}
