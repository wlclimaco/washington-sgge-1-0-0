package com.sensus.mlc.process.bcl.impl;

import static com.sensus.mlc.base.util.LCDateUtil.DEFAULT_DATE_PATTERN_TO_FILENAME;
import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequestToCSV;
import static com.sensus.mlc.base.util.LCHelp.treatReturnFromBCL;

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

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.base.util.RemoteUserContext;
import com.sensus.mlc.base.util.RemoteUserContextHolder;
import com.sensus.mlc.mlcserver.client.MlcServerClient;
import com.sensus.mlc.mlcserver.type.AbortTransactionRequest;
import com.sensus.mlc.mlcserver.type.AbortTransactionResponse;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.MessageType;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ObjectFactory;
import com.sensus.mlc.mlcserver.type.PingURLRequest;
import com.sensus.mlc.mlcserver.type.PingURLResponse;
import com.sensus.mlc.mlcserver.type.Status;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.bcl.IProcessCommunicationGateway;
import com.sensus.mlc.process.dac.IProcessDAC;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessBCLImpl.
 */
public class ProcessBCLImpl implements IProcessBCL
{

	private static final String CSV_EXTENSION = ".csv";

	/** The Constant CHECK_RNI_STATUS_CONNECTION_REFUSED. */
	private static final String CHECK_RNI_STATUS_CONNECTION_REFUSED =
			"Check RNI Status: Connection refused by Gateway. Gateway may not be available. ";

	/** The all processes communication gateway. */
	private Map<String, IProcessCommunicationGateway> processesCommunicationGatewayFactory;

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The process dac. */
	private IProcessDAC processDAC; // injected by Spring through setter

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter

	/** The mlc gateway ws. */
	private MlcServerClient mlcGatewayWs;

	/** The gateway active. */
	private Map<Integer, Boolean> gatewayActiveMap;

	/** The factory. */
	private ObjectFactory factory;

	/** The temp file path. */
	private String tempFilePath; // injected by Spring - come from sensus-lc.properties;

	/** The system id. */
	private String systemId; // injected by Spring

	/** The system pwd. */
	private String systemPwd; // injected by Spring

	/** The Constant DEFAULT_PROCESS_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCL_EXCEPTION_MSG = "sensus.mlc.processbclimpl.defaultexception";

	/** The Constant RNI_OFFLINE_EXCEPTION. */
	private static final String RNI_OFFLINE_EXCEPTION = "sensus.mlc.processbclimpl.rniofflineexception";

	/** The Constant SENSUS_MLC_PROCESSVALIDATOR_PROCESS_NOT_FOUND. */
	private static final String SENSUS_MLC_PROCESSVALIDATOR_PROCESS_NOT_FOUND =
			"sensus.mlc.processvalidator.process.not.found";

	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(ProcessBCLImpl.class);

	/** The Constant RNI_SYNC_FAILURE. */
	private static final String RNI_SYNC_FAILURE = "RNI sync failure: ";

	/**
	 * Gets the processes communication gateway factory.
	 * 
	 * @return the processes communication gateway factory
	 */
	public Map<String, IProcessCommunicationGateway> getProcessesCommunicationGatewayFactory()
	{
		if (this.processesCommunicationGatewayFactory == null)
		{
			this.processesCommunicationGatewayFactory =
					SensusAppContext.getApplicationContext().getBeansOfType(IProcessCommunicationGateway.class);
		}

		return this.processesCommunicationGatewayFactory;
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
		return this.lcHelp;
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
		return this.processDAC;
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
	 * Gets the smart point accessor bcl.
	 * 
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		if (this.smartPointAccessorBCL == null)
		{
			this.smartPointAccessorBCL = (ISmartPointAccessorBCL)SensusAppContext.getApplicationContext().getBean(
					"smartPointAccessorBCLTarget");
		}
		return this.smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 * 
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/**
	 * Gets the smart point updater bcl.
	 * 
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		if (this.smartPointUpdaterBCL == null)
		{
			this.smartPointUpdaterBCL = (ISmartPointUpdaterBCL)SensusAppContext.getApplicationContext()
					.getBean("smartPointUpdaterBCLTarget");
		}
		return this.smartPointUpdaterBCL;
	}

	/**
	 * Sets the smart point updater bcl.
	 * 
	 * @param smartPointUpdaterBCL the new smart point updater bcl
	 */
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)
	{
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
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
		return this.mlcGatewayWs;
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
		return this.factory;
	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getSystemId()
	{
		return this.systemId;
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
		return this.systemPwd;
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
		return this.tempFilePath;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#fetchProcessById(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcessById(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchProcessById(processRequest);
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
		return getProcessDAC().fetchMonitoredProcesses(processRequest);
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
		Tenant tenant = processRequest.getTenant();
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

		// create the child LRP that will be inserted
		List<Light> lightList = new ArrayList<Light>();
		for (ProcessItem parentProcessItem : parentLRP.getProcessItems())
		{
			if (!ValidationUtil.isNull(parentProcessItem.getProcessStatusReasonEnumValue())
					&& (parentProcessItem.getProcessReason() == ProcessStatusReasonEnum.RNI_ASYNC_FAILURE))
			{
				lightList.add(parentProcessItem.getLight());
			}
		}

		LCAction action = parentLRP.getLcAction();
		Process process = this.lcHelp.generateProcess(true, action, lightList);

		// the retry process is always monitored, so the first parameter is true
		process.setIsParent(false);
		process.setParentProcess(parentLRP);
		process.setIsSubmitted(true);
		process.setRniCorrelationId(getLcHelp().generateRniCorrelationId());

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
		getMlcGatewayWs().getWsTemplate().setDefaultUri(processRequest.getTenant().getGatewayURL());

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
	 * com.sensus.mlc.process.bcl.IProcessBCL#fetchProcesses(com.sensus.mlc.process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<Process> fetchProcesses(InquiryProcessRequest processRequest)
	{
		return getProcessDAC().fetchProcesses(processRequest);
	}

	@Override
	public InternalResultsResponse<Process> submitProcess(ProcessRequest processRequest, LCAction action)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		Tenant tenant = processRequest.getTenant();
		if (!fetchRniLinkStatus(tenant).getFirstResult())
		{
			response.addMessage(RNI_OFFLINE_EXCEPTION, MessageSeverity.Error, MessageLevel.Other);
			response.setStatus(InternalResponse.Status.ExceptionError);
			return response;
		}

		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(processRequest);
		Integer lightsAmount = getSmartPointAccessorBCL().countLights(inquiryLightRequest).getFirstResult();

		if (ValidationUtil.isNullOrZero(lightsAmount))
		{
			return response;
		}

		getLcHelp();
		Process process = LCHelp.generateProcess(processRequest.isMonitored(), action, lightsAmount);
		process.setRniCorrelationId(getLcHelp().generateRniCorrelationId());
		process.setProcessItems(getProcessItem(processRequest));
		process.setIsSubmitted(true);

		processRequest.setProcess(process);
		sendActionToGateway(processRequest, action, response);

		if (response.isInError())
		{
			return response;
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

			Light light = new Light(lightId);
			LightRequest request = new LightRequest(userContext);
			request.addLight(light);

			InternalResultsResponse<Light> internalResult = getSmartPointAccessorBCL().fetchLightByRniId(request);
			if (!internalResult.hasResults())
			{
				continue;
			}

			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setDate(new Date());
			statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.SET_LIGHT_INTENSITY);
			statusMessage.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
			if ((process.getLcAction().getActionType() == LCActionTypeEnum.TURN_OFF)
					|| (process.getLcAction().getActionType() == LCActionTypeEnum.TURN_ON)
					|| (process.getLcAction().getActionType() == LCActionTypeEnum.DIM))
			{
				statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.SET_LIGHT_BLINK);
			}

			request.setLights(internalResult.getResultsList());
			request.getStatusMessages().add(statusMessage);
			getSmartPointUpdaterBCL().insertStatusMessage(request);
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
		for (Tenant tenant : fetchAllTenant().getResultsList())
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
				setGatewayActive(tenant, false);
				persistLogDebug(CHECK_RNI_STATUS_CONNECTION_REFUSED);
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
		for (Integer key : this.gatewayActiveMap.keySet())
		{
			if (key.equals(tenant.getId()))
			{
				internalResultsResponse.addResult(this.gatewayActiveMap.get(key));
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
		if (this.gatewayActiveMap == null)
		{
			this.gatewayActiveMap = new HashMap<Integer, Boolean>();
		}
		this.gatewayActiveMap.put(tenant.getId(), value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#fetchAllTenant()
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchAllTenant()
	{
		return getProcessDAC().fetchAllTenant();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessBCL#fetchTenantByRniCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByRniCode(String rniCode)
	{
		return getProcessDAC().fetchTenantByRniCode(rniCode);
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.SSS");
		Date date = getNewDateUTC();
		String dateFormated = format.format(date);

		String fileName =
				getTempFilePath() + processRequest.getUserContext().getUserId() + dateFormated + CSV_EXTENSION;

		InternalResultsResponse<Light> internalResultResponse =
				getSmartPointAccessorBCL().fetchAllLightsByProcess(processRequest);

		InquiryLightRequest inquiryLightRequest = new InquiryLightRequest(processRequest.getUserContext());
		inquiryLightRequest.setLights(internalResultResponse.getResultsList());
		inquiryLightRequest.setFileName(fileName);

		InquiryLightResponse inquiryLightResponse =
				getSmartPointAccessorBCL().generateSummaryFileCSV(inquiryLightRequest);

		ProcessResponse response = new ProcessResponse();
		if (inquiryLightResponse.isOperationSuccess())
		{
			response.setOperationSuccess(inquiryLightResponse.isOperationSuccess());
			response.setFileName(fileName);
		}
		else
		{
			response.addOperationFailedMessage(inquiryLightResponse.getMessageInfoList());
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
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		UserContext userContext = lightSelectionRequest.getUserContext();
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_PATTERN_TO_FILENAME);
		String dateFormated = format.format(getNewDateUTC());
		String fileName = this.tempFilePath + userContext.getUserId() + dateFormated + CSV_EXTENSION;

		ProcessRequest processRequest =
				createProcessRequestToCSV(lightSelectionRequest, fileName, NumberUtils.INTEGER_ZERO);

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
	 * @see
	 * com.sensus.mlc.process.bcl.IProcessBCL#generateFileCSV(com.sensus.mlc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public InquiryProcessResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest)
	{
		this.persistLogInfo("Generating CSV file for processes");

		InquiryProcessResponse inquiryProcessResponse = new InquiryProcessResponse();

		InternalResponse response = getProcessDAC().generateFileCSV(inquiryProcessRequest);

		if (response.isInError())
		{
			treatReturnFromBCL(inquiryProcessResponse, response, DEFAULT_PROCESS_BCL_EXCEPTION_MSG);
			return inquiryProcessResponse;
		}

		Process process = new Process();
		process.setId(inquiryProcessRequest.getProcessId());

		ProcessRequest processRequest = new ProcessRequest(inquiryProcessRequest.getUserContext());
		processRequest.setProcess(process);

		InternalResultsResponse<Process> responseProcess = fetchProcessById(processRequest);

		if (responseProcess.isInError() || ValidationUtil.isNull(responseProcess.getResultsList()))
		{
			inquiryProcessResponse
					.setOperationSuccess(com.sensus.common.model.response.InternalResponse.Status.ExceptionError);
			inquiryProcessResponse.addObjectErrorMessage(SENSUS_MLC_PROCESSVALIDATOR_PROCESS_NOT_FOUND,
					new Object[] {process.getId()});
			return inquiryProcessResponse;
		}

		// update process as completed
		processRequest.getProcessList().clear();
		processRequest.setProcess(responseProcess.getFirstResult());
		processRequest.getProcess().setIsProcessComplete(true);
		processRequest.getProcess().setEndTime(getNewDateUTC());

		response = updateProcess(processRequest);

		if (response.isInError())
		{
			treatReturnFromBCL(inquiryProcessResponse, response, DEFAULT_PROCESS_BCL_EXCEPTION_MSG);
			return inquiryProcessResponse;
		}

		inquiryProcessResponse.setFileName(inquiryProcessRequest.getFileName());
		return inquiryProcessResponse;
	}

	/**
	 * Gets the process item.
	 * 
	 * @param processRequest the process request
	 * @return the process item
	 */
	private List<ProcessItem> getProcessItem(ProcessRequest processRequest)
	{
		InternalResultsResponse<Light> lightResponse =
				getSmartPointAccessorBCL().fetchAllLights(LCHelp.createInquiryLightRequest(processRequest));

		if (!lightResponse.hasResults())
		{
			return null;
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
			this.persistLogInfo("Submit Process: Marshalling failure: ", ufe);
			response.setStatus(com.sensus.common.model.response.InternalResponse.Status.ExceptionError);
		}
		catch (Exception e)
		{
			setGatewayActive(processRequest.getTenant(), false);
			persistLogDebug(CHECK_RNI_STATUS_CONNECTION_REFUSED, e);

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

	/**
	 * Persist log debug.
	 * 
	 * @param logText the log text
	 */
	private void persistLogDebug(String logText)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(logText);
		}
	}

	/**
	 * Persist log debug.
	 * 
	 * @param logText the log text
	 * @param e the e
	 */
	private void persistLogDebug(String logText, Exception e)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(logText, e);
		}
	}
}
