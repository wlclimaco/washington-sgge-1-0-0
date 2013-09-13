package com.sensus.dm.common.process.bcl.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.LocaleUtil;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.AddDeviceToGroupAction;
import com.sensus.dm.common.action.model.AddDeviceToScheduleAction;
import com.sensus.dm.common.action.model.AddDeviceToTagAction;
import com.sensus.dm.common.action.model.AddGroupToScheduleAction;
import com.sensus.dm.common.action.model.CreateGroupAction;
import com.sensus.dm.common.action.model.CreateScheduleAction;
import com.sensus.dm.common.action.model.CreateTagAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromGroupAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromScheduleAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromTagAction;
import com.sensus.dm.common.action.model.EditGroupAction;
import com.sensus.dm.common.action.model.EditScheduleAction;
import com.sensus.dm.common.action.model.GenerateCSVFileAction;
import com.sensus.dm.common.action.model.RemoveGroupAction;
import com.sensus.dm.common.action.model.RemoveScheduleAction;
import com.sensus.dm.common.action.model.RemoveTagAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.bcl.IProcessTypeBCL;
import com.sensus.dm.common.process.dac.IProcessDAC;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.elec.device.model.DeviceClassEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class ProcessBCLImpl.
 * 
 * @author QAT Brazil
 * 
 */
public class ProcessBCLImpl implements IProcessBCL
{

	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Log LOG = LogFactory.getLog(ProcessBCLImpl.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant STR_CSV. */
	private static final String STR_CSV = ".csv";

	/** The Constant EMPTY_STR. */
	private static final String EMPTY_STR = "";

	/** The Constant ONE_STR. */
	private static final String ONE_STR = "1";

	/** The Constant FIVE. */
	private static final int FIVE = 5;

	/** The Constant PIPE. */
	private static final String PIPE = "|";

	/** The Constant SEPARATOR. */
	private static final String COMMA_SEPARATOR = ",";

	/** The Constant STR_LOG. */
	protected static final String STR_LOG = "### ";

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant FILE_DOWNLOADED_STR. */
	private static final String FILE_DOWNLOADED_STR = "File Downloaded";

	/** The Constant LOG_UNABLE_TO_GENERATE_DESCRIPTION. */
	private static final String LOG_UNABLE_TO_GENERATE_DESCRIPTION =
			"Unable to generate description for the following action : ";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant ADD_GROUP_TO_SCHEDULE. */
	private static final String ADD_GROUP_TO_SCHEDULE = "sensus.epm.epm_action.add_grp_to_schedule";

	/** The Constant ADD_SMP_TO_GROUP. */
	private static final String ADD_SMP_TO_GROUP = "sensus.epm.epm_action.add_smp_to_grp";

	/** The Constant ADD_SMP_TO_GROUP_ONE_FLEXNETID. */
	private static final String ADD_SMP_TO_GROUP_ONE_FLEXNETID = "sensus.epm.epm_action.add_smp_to_grp_one_flexnetid";

	/** The Constant ADD_SMP_TO_TAG. */
	private static final String ADD_SMP_TO_TAG = "sensus.epm.epm_action.add_smp_to_tag";

	/** The Constant ADD_SMP_TO_TAG_ONE_FLEXNETID. */
	private static final String ADD_SMP_TO_TAG_ONE_FLEXNETID = "sensus.epm.epm_action.add_smp_to_tag_one_flexnetid";

	/** The Constant APPLY_SMP_TO_SCHEDULE. */
	private static final String APPLY_SMP_TO_SCHEDULE = "sensus.epm.epm_action.apply_smp_to_scheduled_event";

	/** The Constant CONN_HAN_DEVICE. */
	private static final String CONN_HAN_DEVICE = "sensus.epm.epm_action.connect_han_device";

	/** The Constant CONN_HAN_DEVICE_ONE_SMP. */
	private static final String CONN_HAN_DEVICE_ONE_SMP = "sensus.epm.epm_action.connect_han_device_one_smp";

	/** The Constant DEL_GROUP. */
	private static final String DEL_GROUP = "sensus.epm.epm_action.del_grp";

	/** The Constant DEL_HAN_DEVICE. */
	private static final String DEL_HAN_DEVICE = "sensus.epm.epm_action.delete_han_device";

	/** The Constant DEL_HAN_DEVICE_ONE_SMP. */
	private static final String DEL_HAN_DEVICE_ONE_SMP = "sensus.epm.epm_action.delete_han_device_one_smp";

	/** The Constant DEL_SCHEDULE. */
	private static final String DEL_SCHEDULE = "sensus.epm.epm_action.del_scheduled_event";

	/** The Constant DEL_SMP_FROM_GROUP. */
	private static final String DEL_SMP_FROM_GROUP = "sensus.epm.epm_action.del_smp_from_grp";

	/** The Constant DEL_SMP_FROM_GROUP_ONE_FLEXNETID. */
	private static final String DEL_SMP_FROM_GROUP_ONE_FLEXNETID =
			"sensus.epm.epm_action.del_smp_from_grp_one_flexnetid";

	/** The Constant DEL_SMP_FROM_SCHEDULE. */
	private static final String DEL_SMP_FROM_SCHEDULE = "sensus.epm.epm_action.del_smp_from_scheduled_event";

	/** The Constant DEL_SMP_FROM_SCHEDULE_ONE_FLEXNETID. */
	private static final String DEL_SMP_FROM_SCHEDULE_ONE_FLEXNETID =
			"sensus.epm.epm_action.del_smp_from_scheduled_event_one_flexnetid";

	/** The Constant DEL_SMP_FROM_TAG. */
	private static final String DEL_SMP_FROM_TAG = "sensus.epm.epm_action.del_smp_from_tag";

	/** The Constant DEL_SMP_FROM_TAG_ONE_FLEXNETID. */
	private static final String DEL_SMP_FROM_TAG_ONE_FLEXNETID = "sensus.epm.epm_action.del_smp_from_tag_one_flexnetid";

	/** The Constant DELETE_TAG. */
	private static final String DELETE_TAG = "sensus.epm.epm_action.delete_tag";

	/** The Constant DEMAND_RESET. */
	private static final String DEMAND_RESET = "sensus.epm.epm_action.demand_reset";

	/** The Constant DEMAND_RESET_ONE_SMP. */
	private static final String DEMAND_RESET_ONE_SMP = "sensus.epm.epm_action.demand_reset_one_smp";

	/** The Constant DISC_HAN_DEVICE. */
	private static final String DISC_HAN_DEVICE = "sensus.epm.epm_action.disconnect_han_device";

	/** The Constant DISC_HAN_DEVICE_ONE_SMP. */
	private static final String DISC_HAN_DEVICE_ONE_SMP = "sensus.epm.epm_action.disconnect_han_device_one_smp";

	/** The Constant FILE_DOWNLOADED. */
	private static final String FILE_DOWNLOADED = "sensus.epm.epm_action.file_downloaded";

	/** The Constant GENERATE_CSV_FILE. */
	private static final String GENERATE_CSV_FILE = "sensus.epm.epm_action.generate_csv_file";

	/** The Constant GET_HAN_CONNECTION_STATUS. */
	private static final String GET_HAN_CONNECTION_STATUS = "sensus.epm.epm_action.get_han_connection_status";

	/** The Constant GET_HAN_CONNECTION_STATUS_ONE_SMP. */
	private static final String GET_HAN_CONNECTION_STATUS_ONE_SMP =
			"sensus.epm.epm_action.get_han_connection_status_one_smp";

	/** The Constant IMPORT_HAN_DEVICE. */
	private static final String IMPORT_HAN_DEVICE = "sensus.epm.epm_action.import_han_device";

	/** The Constant IMPORT_HAN_DEVICE_ONE_SMP. */
	private static final String IMPORT_HAN_DEVICE_ONE_SMP = "sensus.epm.epm_action.import_han_device_one_smp";

	/** The Constant INSERT_GROUP. */
	private static final String INSERT_GROUP = "sensus.epm.epm_action.insert_group";

	/** The Constant INSERT_SCHEDULE. */
	private static final String INSERT_SCHEDULE = "sensus.epm.epm_action.insert_scheduled_event";

	/** The Constant INSERT_TAG. */
	private static final String INSERT_TAG = "sensus.epm.epm_action.insert_tag";

	/** The Constant LINK_INVALID_URL. */
	private static final String LINK_INVALID_URL = "sensus.epm.processbclimpl.link.invalid.url";

	/** The Constant LINK_ERROR. */
	private static final String LINK_ERROR = "sensus.epm.processbclimpl.link.error";

	/** The Constant LINK_NO_DOMAIN_FOUND. */
	private static final String LINK_NO_DOMAIN_FOUND = "sensus.epm.processbclimpl.link.no.domain.found";

	/** The Constant LINK_NO_QUEUES_FOUND. */
	private static final String LINK_NO_QUEUES_FOUND = "sensus.epm.processbclimpl.link.no.queues.found";

	/** The Constant ON_DEMAND. */
	private static final String ON_DEMAND = "sensus.epm.epm_action.on_demand";

	/** The Constant SEND_HAN_TEXT_MESSAGE. */
	private static final String SEND_HAN_TEXT_MESSAGE = "sensus.epm.epm_action.send_han_text_message";

	/** The Constant SEND_HAN_TEXT_MESSAGE_ONE_SMP. */
	private static final String SEND_HAN_TEXT_MESSAGE_ONE_SMP = "sensus.epm.epm_action.send_han_text_message_one_smp";

	/** The Constant UPDATE_GROUP. */
	private static final String UPDATE_GROUP = "sensus.epm.epm_action.update_grp";

	/** The Constant UPDATE_SCHEDULE. */
	private static final String UPDATE_SCHEDULE = "sensus.epm.epm_action.update_scheduled_event";

	/** The Constant UPDATE_SCHEDULE_ONE_FLEXNETID. */
	private static final String UPDATE_SCHEDULE_ONE_FLEXNETID =
			"sensus.epm.epm_action.apply_smp_to_schedule_one_flexnetid";

	/** The Constant DEMAND_RESPONSE. */
	private static final String DEMAND_RESPONSE = "sensus.epm.epm_action.demand_response";

	/** The Constant DEMAND_RESPONSE_ONE_SMP. */
	private static final String DEMAND_RESPONSE_ONE_SMP = "sensus.epm.epm_action.demand_response_one_smp";

	/** The Constant INITIATE_DEMAND_RESPONSE_SETUP. */
	private static final String INITIATE_DEMAND_RESPONSE_SETUP = "sensus.epm.epm_action.setup_demand_response";

	/** The Constant INITIATE_DEMAND_RESPONSE_SETUP_ONE_SMP. */
	private static final String INITIATE_DEMAND_RESPONSE_SETUP_ONE_SMP =
			"sensus.epm.epm_action.setup_demand_response_one_smp";

	/** The Constant INITIATE_DEMAND_RESPONSE_SETUP. */
	private static final String DEMAND_READ_PING = "sensus.epm.epm_action.demand_read_ping";

	/** The Constant INITIATE_DEMAND_RESPONSE_SETUP_ONE_SMP. */
	private static final String DEMAND_READ_PING_ONE_SMP = "sensus.epm.epm_action.demand_read_ping_one_smp";

	/** The Constant REMOTE_CONNECT. */
	private static final String REMOTE_CONNECT = "sensus.epm.epm_action.remote_connect";

	/** The Constant REMOTE_CONNECT_ONE_SMP. */
	private static final String REMOTE_CONNECT_ONE_SMP = "sensus.epm.epm_action.remote_connect_one_smp";

	/** The Constant REMOTE_DISCONNECT. */
	private static final String REMOTE_DISCONNECT = "sensus.epm.epm_action.remote_disconnect";

	/** The Constant REMOTE_DISCONNECT_ONE_SMP. */
	private static final String REMOTE_DISCONNECT_ONE_SMP = "sensus.epm.epm_action.remote_disconnect_one_smp";

	/** The Constant REMOTE_ARM_CONNECT. */
	private static final String REMOTE_ARM_CONNECT = "sensus.epm.epm_action.remote_arm_connect";

	/** The Constant REMOTE_ARM_CONNECT_ONE_SMP. */
	private static final String REMOTE_ARM_CONNECT_ONE_SMP = "sensus.epm.epm_action.remote_arm_connect_one_smp";

	/** The Constant GET_REMOTE_CONNECT_STATUS. */
	private static final String GET_REMOTE_CONNECT_STATUS = "sensus.epm.epm_action.get_remote_connect_status";

	/** The Constant GET_REMOTE_CONNECT_STATUS_ONE_SMP. */
	private static final String GET_REMOTE_CONNECT_STATUS_ONE_SMP =
			"sensus.epm.epm_action.get_remote_connect_status_one_smp";

	/** The Constant SET_TAMPER_DETECTED. */
	private static final String SET_TAMPER_DETECTED = "sensus.epm.epm_action.set_tamper_detected";

	/** The Constant SET_TAMPER_DETECTED_ONE_SMP. */
	private static final String SET_TAMPER_DETECTED_ONE_SMP = "sensus.epm.epm_action.set_tamper_detected_one_smp";

	/** The Constant GET_TAMPER_DETECTED. */
	private static final String GET_TAMPER_DETECTED = "sensus.epm.epm_action.get_tamper_detected";

	/** The Constant GET_TAMPER_DETECTED_ONE_SMP. */
	private static final String GET_TAMPER_DETECTED_ONE_SMP = "sensus.epm.epm_action.get_tamper_detected_one_smp";

	/** The Constant GET_DEMAND_RESPONSE_SETUP. */
	private static final String GET_DEMAND_RESPONSE_SETUP = "sensus.epm.epm_action.get_setup_demand_response";

	/** The Constant GET_DEMAND_RESPONSE_SETUP_ONE_SMP. */
	private static final String GET_DEMAND_RESPONSE_SETUP_ONE_SMP =
			"sensus.epm.epm_action.get_setup_demand_response_one_smp";

	/** The Constant INVALID_NETWORK_ADDRESS. */
	private static final String INVALID_NETWORK_ADDRESS = "sensus.dm.common.process.invalid.networkaddress";

	/** The Constant INVALID_DEVICE_ID. */
	private static final String INVALID_DEVICE_ID = "sensus.dm.common.process.invalid.deviceid";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp file path. */
	private String tempFilePath;

	/** The active mq jmx url. */
	private String activeMqJmxUrl;

	/** The active mq jmx domain. */
	private String activeMqJmxDomain;

	/** The active mq jmx attributes. */
	private String activeMqJmxAttributes;

	/** The active mq jmx attribute values. */
	private String activeMqJmxAttributeValues;

	/** The process dac. */
	private IProcessDAC processDAC;

	/** The process type bcl. */
	private IProcessTypeBCL processTypeBCL;

	/** The process summary bcl. */
	private IProcessSummaryBCL processSummaryBCL;

	/** The default lcm relays. */
	private List<LCMRelay> defaultLcmRelays;

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
	 * Gets the active mq jmx url.
	 * 
	 * @return the active mq jmx url
	 */
	public String getActiveMqJmxUrl()
	{
		return activeMqJmxUrl;
	}

	/**
	 * Sets the active mq jmx url.
	 * 
	 * @param activeMqJmxUrl the new active mq jmx url
	 */
	public void setActiveMqJmxUrl(String activeMqJmxUrl)
	{
		this.activeMqJmxUrl = activeMqJmxUrl;
	}

	/**
	 * Gets the active mq jmx domain.
	 * 
	 * @return the active mq jmx domain
	 */
	public String getActiveMqJmxDomain()
	{
		return activeMqJmxDomain;
	}

	/**
	 * Sets the active mq jmx domain.
	 * 
	 * @param activeMqJmxDomain the new active mq jmx domain
	 */
	public void setActiveMqJmxDomain(String activeMqJmxDomain)
	{
		this.activeMqJmxDomain = activeMqJmxDomain;
	}

	/**
	 * Gets the active mq jmx attributes.
	 * 
	 * @return the active mq jmx attributes
	 */
	public String getActiveMqJmxAttributes()
	{
		return activeMqJmxAttributes;
	}

	/**
	 * Sets the active mq jmx attributes.
	 * 
	 * @param activeMqJmxAttributes the new active mq jmx attributes
	 */
	public void setActiveMqJmxAttributes(String activeMqJmxAttributes)
	{
		this.activeMqJmxAttributes = activeMqJmxAttributes;
	}

	/**
	 * Gets the active mq jmx attribute values.
	 * 
	 * @return the active mq jmx attribute values
	 */
	public String getActiveMqJmxAttributeValues()
	{
		return activeMqJmxAttributeValues;
	}

	/**
	 * Sets the active mq jmx attribute values.
	 * 
	 * @param activeMqJmxAttributeValues the new active mq jmx attribute values
	 */
	public void setActiveMqJmxAttributeValues(String activeMqJmxAttributeValues)
	{
		this.activeMqJmxAttributeValues = activeMqJmxAttributeValues;
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
	 * Gets the process type bcl.
	 * 
	 * @return the process type bcl
	 */
	public IProcessTypeBCL getProcessTypeBCL()
	{
		return processTypeBCL;
	}

	/**
	 * Sets the process type bcl.
	 * 
	 * @param processTypeBCL the new process type bcl
	 */
	public void setProcessTypeBCL(IProcessTypeBCL processTypeBCL)
	{
		this.processTypeBCL = processTypeBCL;
	}

	/**
	 * Gets the process summary bcl.
	 * 
	 * @return the process summary bcl
	 */
	public IProcessSummaryBCL getProcessSummaryBCL()
	{
		return processSummaryBCL;
	}

	/**
	 * Sets the process summary bcl.
	 * 
	 * @param processSummaryBCL the new process summary bcl
	 */
	public void setProcessSummaryBCL(IProcessSummaryBCL processSummaryBCL)
	{
		this.processSummaryBCL = processSummaryBCL;
	}

	/**
	 * Gets the default lcm relays.
	 * 
	 * @return the default lcm relays
	 */
	public List<LCMRelay> getDefaultLcmRelays()
	{
		return defaultLcmRelays;
	}

	/**
	 * Sets the default lcm relays.
	 * 
	 * @param defaultLcmRelays the new default lcm relays
	 */
	public void setDefaultLcmRelays(List<LCMRelay> defaultLcmRelays)
	{
		this.defaultLcmRelays = defaultLcmRelays;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcesses(inquiryProcessRequest);

		for (DMProcess pv : response.getResultsList())
		{
			generateDescription(pv, LocaleUtil.getLocale(inquiryProcessRequest.getUserContext().getLocaleString()));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessById(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessById(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcessById(processRequest);

		if (response.isInError())
		{
			return response;
		}

		// Apply process items to process.
		applyProcessItemsToProcess(processRequest, response);

		if (response.isInError())
		{
			return response;
		}

		for (DMProcess pv : response.getResultsList())
		{
			generateDescription(pv, LocaleUtil.getLocale(processRequest.getUserContext().getLocaleString()));
			readUnreachableDevice(pv);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchMonitoredProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchMonitoredProcess(inquiryProcessRequest);

		for (DMProcess pv : response.getResultsList())
		{
			generateDescription(pv, LocaleUtil.getLocale(inquiryProcessRequest.getUserContext().getLocaleString()));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcl.IProcessBCL#updateProcessStatus(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcessStatus(ProcessRequest request)
	{
		// update process
		InternalResponse response = getProcessDAC().updateProcess(request);
		if (response.isInError())
		{
			return response;
		}

		if (!ValidationUtil.isNullOrEmpty(request.getFirstProcess().getProcessItems()))
		{
			// updates process items statuses
			return updateProcessItems(request);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#insertProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> insertProcess(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> processResponse = new InternalResultsResponse<DMProcess>();

		// If there is processType look the Id by Description
		if (ValidationUtil.isNull(processRequest.getFirstProcess().getAction())
				&& !ValidationUtil.isNull(processRequest.getFirstProcess().getProcessType()))
		{
			InternalResultsResponse<ProcessType> processTypeResponse =
					getProcessTypeBCL().fetchProcessTypeByDescription(processRequest);

			if (processTypeResponse.isInError())
			{
				return handleError(processResponse, processTypeResponse);
			}

			processRequest.getFirstProcess().getProcessType().setId(processTypeResponse.getFirstResult().getId());
		}

		// insert the process
		processResponse = getProcessDAC().insertProcess(processRequest);
		if (processResponse.isInError())
		{
			return processResponse;
		}

		processRequest.getFirstProcess().setId(processResponse.getFirstResult().getId());

		InternalResponse response = null;

		// insert properties for process
		if (!ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProperties()))
		{
			response = getProcessDAC().insertProcessProperty(processRequest);
			if (response.isInError())
			{
				return handleError(processResponse, response);
			}
		}

		// insert process items
		if (!ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProcessItems()))
		{
			response = insertProcessItems(processRequest);
			if (response.isInError())
			{
				return handleError(processResponse, response);
			}
		}

		return processResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#insertProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ProcessItem> insertProcessItems(ProcessRequest processRequest)
	{
		return getProcessDAC().insertProcessItems(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#updateProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse updateProcessItems(ProcessRequest processRequest)
	{
		return getProcessDAC().updateProcessItems(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#updateProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse updateProcess(ProcessRequest processRequest)
	{

		InternalResponse response = getProcessDAC().updateProcess(processRequest);
		if (response.isInError())
		{
			return response;
		}

		// insert properties for process
		if (!ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProperties()))
		{
			return getProcessDAC().insertProcessProperty(processRequest);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#insertProcessProperty(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse insertProcessProperty(ProcessRequest request)
	{
		return getProcessDAC().insertProcessProperty(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#checkLinkStatus(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Boolean> checkLinkStatus(ProcessRequest processRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		try
		{
			JMXServiceURL url = new JMXServiceURL(getActiveMqJmxUrl());
			JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
			String[] domains = mbsc.getDomains();
			boolean domainOnline = false;

			for (String domain : domains)
			{
				if (getActiveMqJmxDomain().equals(domain))
				{
					domainOnline = true;
					break;
				}
			}

			if (!domainOnline)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug("domain not online");
				}

				response.addResult(false);
				response.addMessage(LINK_NO_DOMAIN_FOUND, MessageSeverity.Error, MessageLevel.Other);
				return response;
			}

			Hashtable<String, String> attribValues = new Hashtable<String, String>();
			String[] attributes = getActiveMqJmxAttributes().split(COMMA_SEPARATOR);
			String[] attributeValues = getActiveMqJmxAttributeValues().split(COMMA_SEPARATOR);

			for (int i = 0; i < attributes.length; i++)
			{
				attribValues.put(attributes[i], attributeValues[i]);
			}

			ObjectName objectName = new ObjectName(getActiveMqJmxDomain(), attribValues);

			Set<ObjectName> names = mbsc.queryNames(objectName, null);

			if (names == null || names.isEmpty())
			{
				response.addResult(false);
				response.addMessage(LINK_NO_QUEUES_FOUND, MessageSeverity.Error, MessageLevel.Other);
				return response;
			}

			response.addResult(true);
			return response;
		}
		catch (MalformedURLException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(e.getMessage());
			}

			response.addResult(false);
			response.addMessage(LINK_INVALID_URL, MessageSeverity.Fatal, MessageLevel.Other);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(e.getMessage());
			}

			response.addResult(false);
			response.addMessage(LINK_ERROR, MessageSeverity.Fatal, MessageLevel.Other);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessesToday(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InternalResultsResponse<DMProcess> responseProcess = new InternalResultsResponse<DMProcess>();

		// Fetch all today PROCESSING processes.
		inquiryProcessRequest.getProcessSearch().setProcessStatusEnums(new ArrayList<ProcessStatusEnum>());
		inquiryProcessRequest.getProcessSearch().getProcessStatusEnums().add(ProcessStatusEnum.IN_PROCESS);

		addDataToResponse(
				getProcessDAC().fetchProcessesInProcessing(inquiryProcessRequest),
				responseProcess);

		// Fetch all today STARTED processes.
		addDataToResponse(
				getProcessDAC().fetchStartedProcesses(inquiryProcessRequest),
				responseProcess);

		inquiryProcessRequest.getProcessSearch().setProcessItemHistoryStatusEnums(null);

		// Fetch all today SCHEDULED processes.
		addDataToResponse(
				getProcessDAC().fetchScheduledProcesses(inquiryProcessRequest),
				responseProcess);

		inquiryProcessRequest.setPageSize(FIVE);

		// Fetch all today COMPLETED processes.
		inquiryProcessRequest.getProcessSearch().setProcessStatusEnums(new ArrayList<ProcessStatusEnum>());
		inquiryProcessRequest.getProcessSearch().getProcessStatusEnums().add(ProcessStatusEnum.COMPLETED);

		addDataToResponse(
				getProcessDAC().fetchTodayProcesses(inquiryProcessRequest),
				responseProcess);

		// Fetch all today ABORTED processes.
		inquiryProcessRequest.getProcessSearch().getProcessStatusEnums().clear();
		inquiryProcessRequest.getProcessSearch().getProcessStatusEnums().add(ProcessStatusEnum.ABORTED);

		addDataToResponse(
				getProcessDAC().fetchTodayProcesses(inquiryProcessRequest),
				responseProcess);

		for (DMProcess pv : responseProcess.getResultsList())
		{
			generateDescription(pv, LocaleUtil.getLocale(inquiryProcessRequest.getUserContext().getLocaleString()));
		}

		return responseProcess;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#summarizeProcess(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResponse summarizeProcess(ProcessRequest processRequest)
	{
		return getProcessDAC().summarizeProcess(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchCheckProcessing(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResponse fetchCheckProcessing(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchCheckProcessing(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchCountMonitoredProcesses(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchCountMonitoredProcesses(
			ProcessRequest processRequest)
	{
		return getProcessDAC().fetchCountMonitoredProcesses(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessItemsByDevice(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchProcessItemsByDevice(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchProcessItemsByDevice(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchCommunicationSummary(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchCommunicationSummary(ProcessRequest processRequest)
	{
		return fetchProcessById(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchImportHanDeviceSummary(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchImportHanDeviceSummary(ProcessRequest processRequest)
	{
		return fetchProcessById(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchDemandReadPingSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchDemandReadPingSummary(ProcessRequest processRequest)
	{
		return fetchProcessById(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessItemsBySchedule(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessItemsBySchedule(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> processResponse = new InternalResultsResponse<DMProcess>();

		InternalResultsResponse<ProcessItem> response = getProcessDAC().fetchProcessItemsBySchedule(processRequest);
		if (response.isInError())
		{
			return handleError(processResponse, response);
		}

		processResponse.addResult(new DMProcess(response.getResultsList()));

		return processResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchStartedProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchStartedProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		return getProcessDAC().fetchStartedProcesses(inquiryProcessRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessesWithProcessItemInProcessing(com.sensus.dm.common.process
	 * .model.request.ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessesWithProcessItemInProcessing(ProcessRequest request)
	{
		InternalResultsResponse<DMProcess> response = getProcessDAC().fetchProcessById(request);

		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		InternalResultsResponse<Property> internalPropertyResponse = getProcessDAC().fetchProcessProperty(request);

		if (internalPropertyResponse.isInError())
		{
			response.getMessageInfoList().addAll(internalPropertyResponse.getMessageInfoList());
			response.setStatus(internalPropertyResponse.getStatus());
			return response;
		}

		InternalResultsResponse<ProcessItem> internalResponse = getProcessDAC().fetchProcessItemsByProcessId(request);

		if (internalResponse.isInError())
		{
			response.getMessageInfoList().addAll(internalResponse.getMessageInfoList());
			response.setStatus(internalResponse.getStatus());
			return response;
		}

		response.getFirstResult().setProperties(internalPropertyResponse.getResultsList());
		response.getFirstResult().setProcessItems(internalResponse.getResultsList());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessItemsByProcessId(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessItemsByProcessId(ProcessRequest processRequest)
	{
		InternalResultsResponse<DMProcess> processResponse = new InternalResultsResponse<DMProcess>();

		InternalResultsResponse<ProcessItem> response = getProcessDAC().fetchProcessItemsByProcessId(processRequest);
		if (response.isInError())
		{
			return handleError(processResponse, response);
		}

		processResponse.addResult(new DMProcess(response.getResultsList()));

		return processResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchProcessByRniEventId(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<DMProcess> fetchProcessByRniEventId(
			ProcessRequest processRequest)
	{
		return getProcessDAC().fetchProcessByRniEventId(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchAllProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchAllProcessItems(ProcessRequest processRequest)
	{
		return getProcessDAC().fetchAllProcessItems(processRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#updateProcessItemsToExpire(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResponse updateProcessItemsToExpire(ProcessRequest processRequest)
	{
		InternalResponse summarizeResponse = new InternalResponse();

		// Set ProcessItemStatusEnum = RUNNING, to get only process items running.
		processRequest.getFirstProcess().getFirstProcessItem().setProcessItemStatusEnum(ProcessItemStatusEnum.RUNNING);

		// fetch process items.
		InternalResultsResponse<ProcessItem> responseProcessItems = fetchAllProcessItems(processRequest);
		if (responseProcessItems.isInError())
		{
			return responseProcessItems;
		}

		processRequest.getFirstProcess().setProcessItems(responseProcessItems.getResultsList());

		for (ProcessItem processItem : responseProcessItems.getResultsList())
		{
			// set status to expired process items
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.EXPIRED);

			summarizeResponse = summarizeProcess(processRequest);

			if (summarizeResponse.isInError())
			{
				if (LOG.isInfoEnabled())
				{
					LOG.info(
							new StringBuilder(STR_LOG).append("Error summarizing response: ")
									.append(summarizeResponse.getMessageInfoList()));
				}

				return summarizeResponse;
			}

		}

		return summarizeResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchRelays(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchRelays(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> processItemResponse = new InternalResultsResponse<ProcessItem>();

		InternalResultsResponse<DMProcess> processResponse =
				getProcessSummaryBCL().fetchAllDemandResponseSetup(processRequest);

		if (processResponse.isInError())
		{
			processItemResponse.setStatus(processResponse.getStatus());
			processItemResponse.addMessages(processResponse.getMessageInfoList());
			return processItemResponse;
		}

		if (ValidationUtil.isNullOrEmpty(getDefaultLcmRelays()))
		{
			return processItemResponse;
		}

		List<LCMRelay> lcmRelayList = cloneLCMRelay();

		if (!ValidationUtil.isNullOrEmpty(processResponse.getResultsList())
				&& !ValidationUtil.isNullOrEmpty(processResponse.getFirstResult().getProperties()))
		{
			setDemandResponseSetup(processResponse, lcmRelayList);
		}

		processResponse = getProcessSummaryBCL().fetchLastTamperDetectTimeout(processRequest);

		if (processResponse.isInError())
		{
			processItemResponse.setStatus(processResponse.getStatus());
			processItemResponse.addMessages(processResponse.getMessageInfoList());
			return processItemResponse;
		}

		if (!ValidationUtil.isNullOrEmpty(processResponse.getResultsList())
				&& !ValidationUtil.isNullOrEmpty(processResponse.getFirstResult().getProperties()))
		{
			setTamperDetectTimeOut(processResponse, lcmRelayList);
		}

		LCM lcm = new LCM();
		lcm.setLcmRelays(lcmRelayList);
		processItemResponse.addResult(new ProcessItem(lcm));

		return processItemResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcl.IProcessBCL#fetchRelaysByProcessId(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public InternalResultsResponse<ProcessItem> fetchRelaysByProcessId(ProcessRequest processRequest)
	{
		InternalResultsResponse<ProcessItem> processItemResponse = new InternalResultsResponse<ProcessItem>();

		InternalResultsResponse<DMProcess> processResponse = fetchProcessById(
				new ProcessRequest(processRequest.getFirstProcess(), processRequest
						.getUserContext()));

		if (processResponse.isInError() || !processResponse.hasResults()
				|| ValidationUtil.isNullOrEmpty(processResponse.getFirstResult().getProcessItems())
				|| ValidationUtil.isNullOrEmpty(processResponse.getFirstResult().getProperties()))
		{
			processItemResponse.setStatus(processResponse.getStatus());
			processItemResponse.addMessages(processResponse.getMessageInfoList());
			return processItemResponse;
		}

		List<ProcessItem> processItemList = new ArrayList<ProcessItem>();

		List<LCMRelay> lcmRelayList =
				DMConvertUtil.convertPropertyToLCMRelay(processResponse.getFirstResult().getProperties());

		if (ValidationUtil.isNullOrEmpty(lcmRelayList))
		{
			LCMRelay lcmRelay = new LCMRelay();
			for (Property property : processResponse.getFirstResult().getProperties())
			{
				switch (PropertyEnum.enumForValue(property.getPropertyName()))
				{
					case DEMAND_RESPONSE_ENROLLMENT_CODE:
						lcmRelay.setEnrollmentCode(Integer.parseInt(property.getPropertyValue()));
						break;

					case DEMAND_RESPONSE_RANDOMIZE_START:
						lcmRelay.setStartMinutes(Integer.parseInt(property.getPropertyValue()));
						break;

					case DEMAND_RESPONSE_RANDOMIZE_END:
						lcmRelay.setEndMinutes(Integer.parseInt(property.getPropertyValue()));
						break;

					default:
						break;
				}
			}
			lcmRelayList.add(lcmRelay);
		}

		for (ProcessItem processItem : processResponse.getFirstResult().getProcessItems())
		{
			for (LCMRelay lcmRelay : lcmRelayList)
			{
				LCM lcm = new LCM(LCMTypeEnum.FLEXNET_LCM);
				lcm.setRadio(processItem.getDevice().getRadio());
				lcm.setDeviceId(processItem.getDevice().getDeviceId());
				lcm.addLCMRelay(lcmRelay);
				processItemList.add(new ProcessItem(lcm));
			}
		}
		processItemResponse.addResults(processItemList);
		return processItemResponse;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Clone lcm relay.
	 * 
	 * @return the list
	 */
	private List<LCMRelay> cloneLCMRelay()
	{
		List<LCMRelay> lcmRelayList = new ArrayList<LCMRelay>();

		for (LCMRelay lcmRelay : getDefaultLcmRelays())
		{
			lcmRelayList.add(new LCMRelay(lcmRelay.getRelay(), lcmRelay.getAmp(), lcmRelay.getDeviceClass()));
		}
		return lcmRelayList;
	}

	/**
	 * Sets the tamper detect time out.
	 * 
	 * @param processResponse the process response
	 * @param lcmRelayList the lcm relay list
	 */
	private void setTamperDetectTimeOut(InternalResultsResponse<DMProcess> processResponse, List<LCMRelay> lcmRelayList)
	{
		for (Property property : processResponse.getFirstResult().getProperties())
		{
			if (PropertyEnum.RELAY1_TAMPER_TIMEOUT.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(0).setTamperDetectTimer(property.getPropertyValue());
			}

			if (PropertyEnum.RELAY2_TAMPER_TIMEOUT.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(1).setTamperDetectTimer(property.getPropertyValue());
			}

			if (PropertyEnum.RELAY3_TAMPER_TIMEOUT.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(2).setTamperDetectTimer(property.getPropertyValue());
			}
		}
	}

	/**
	 * Sets the demand response setup.
	 * 
	 * @param processResponse the process response
	 * @param lcmRelayList the lcm relay list
	 */
	private void setDemandResponseSetup(InternalResultsResponse<DMProcess> processResponse, List<LCMRelay> lcmRelayList)
	{
		for (Property property : processResponse.getFirstResult().getProperties())
		{
			if (PropertyEnum.RELAY1_ENROLLMENT_CODE.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(0).setEnrollmentCode(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY2_ENROLLMENT_CODE.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(1).setEnrollmentCode(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY3_ENROLLMENT_CODE.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(2).setEnrollmentCode(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY1_DEVICE_CLASS.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(0).setDeviceClass(
						DeviceClassEnum.enumForValue(Integer.valueOf(property.getPropertyValue())));
			}

			if (PropertyEnum.RELAY2_DEVICE_CLASS.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(1).setDeviceClass(
						DeviceClassEnum.enumForValue(Integer.valueOf(property.getPropertyValue())));
			}

			if (PropertyEnum.RELAY3_DEVICE_CLASS.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(2).setDeviceClass(
						DeviceClassEnum.enumForValue(Integer.valueOf(property.getPropertyValue())));
			}

			if (PropertyEnum.RELAY1_RANDOMIZE_START.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(0).setStartMinutes(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY2_RANDOMIZE_START.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(1).setStartMinutes(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY3_RANDOMIZE_START.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(2).setStartMinutes(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY1_RANDOMIZE_END.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(0).setEndMinutes(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY2_RANDOMIZE_END.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(1).setEndMinutes(Integer.valueOf(property.getPropertyValue()));
			}

			if (PropertyEnum.RELAY3_RANDOMIZE_END.getValue().equals(property.getPropertyName()))
			{
				lcmRelayList.get(2).setEndMinutes(Integer.valueOf(property.getPropertyValue()));
			}
		}
	}

	/**
	 * Generate message values.
	 * 
	 * @param process the process
	 * @param locale the locale
	 * @return the object[]
	 */
	private Object[] generateMessageValues(DMProcess process, Locale locale)
	{
		String id = EMPTY_STR;
		String name = EMPTY_STR;
		String devices = String.valueOf(process.getTotalSmartpoints());
		String devicesParent = EMPTY_STR;

		if (devices.equals(ONE_STR))
		{
			if (!ValidationUtil.isNull(process.getFirstProcessItem())
					&& !ValidationUtil.isNull(process.getFirstProcessItem().getDevice())
					&& !ValidationUtil.isNull(process.getFirstProcessItem().getDevice().getDeviceId())
					&& !ValidationUtil.isNull(process.getFirstProcessItem().getDevice().getDeviceType()))
			{
				devices =
						new StringBuilder().append(process.getFirstProcessItem().getDevice().getRadio().getFlexNetId())
								.append(PIPE)
								.append(process.getFirstProcessItem().getDevice().getDeviceId())
								.append(PIPE)
								.append(process.getFirstProcessItem().getDevice().getDeviceType().toString())
								.toString();

				switch (process.getFirstProcessItem().getDevice().getDeviceType())
				{
					case ELECTRIC_METER:
						devices += PIPE + ((ElectricMeter)process.getFirstProcessItem().getDevice())
								.getDeviceType().toString();
						break;

					case HAN_DEVICE:
						devices += PIPE + ((HanDevice)process.getFirstProcessItem().getDevice()).getHanDeviceTypeEnum()
								.toString();
						break;

					case LCM:
						devices += PIPE + ((LCM)process.getFirstProcessItem().getDevice()).getLcmTypeEnum().toString();
						break;

					case WATER_METER:
						devices +=
								PIPE + ((WaterMeter)process.getFirstProcessItem().getDevice()).getWaterMeterTypeEnum()
										.toString();
						break;

					case GAS_METER:
						devices += PIPE + ((GasMeter)process.getFirstProcessItem().getDevice()).getGasMeterTypeEnum()
								.toString();
						break;

					default:
						break;
				}
			}
			else if (!ValidationUtil.isNullOrEmpty(process.getPropertyLink()))
			{
				devices = process.getPropertyLink();
			}

			if (!ValidationUtil.isNull(process.getAction())
					&&
					!ValidationUtil.isNull(process.getAction().getActionType())
					&&
					ActionTypeEnum.DISCONNECT_HAN_DEVICE.equals(process.getAction().getActionType()
							.getActionTypeEnum())
					&& !ValidationUtil.isNull(process.getPropertyMeterId()))
			{
				devicesParent = process.getPropertyMeterId();
			}
		}

		if (!ValidationUtil.isNullOrEmpty(process.getPropertyDevice()))
		{
			devices = process.getPropertyDevice();
		}

		if (!ValidationUtil.isNullOrEmpty(process.getPropertyId()))
		{
			id = process.getPropertyId();
		}

		if (!ValidationUtil.isNullOrEmpty(process.getPropertyValue()))
		{
			name = process.getPropertyValue();
			if (name.equals(PropertyEnum.ON_DEMAND.toString()))
			{
				name = SensusMessageUtil.getMessage(ON_DEMAND, null, null, locale);
			}
		}

		return new Object[] {id, name, devices, devicesParent};
	}

	/**
	 * Adds the data to response.
	 * 
	 * @param from the from
	 * @param to the to
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void addDataToResponse(InternalResultsResponse from, InternalResultsResponse to)
	{
		to.addResults(from.getResultsList());
		to.addMessages(from.getMessageInfoList());
		to.setStatus(from.getStatus());
	}

	/**
	 * Read unreachable device.
	 * 
	 * @param pv the pv
	 */
	private void readUnreachableDevice(DMProcess pv)
	{
		if (!ValidationUtil.isNullOrEmpty(pv.getPropertyFilePath()))
		{
			// Get FILE_PATH Property value
			File file = new File(pv.getPropertyFilePath() + pv.getId() + STR_CSV);

			if (file.exists())
			{
				String fileRead = DMUtil.readFile(file);
				String[] unreachableUploadIds = fileRead.split(COMMA_SEPARATOR);

				for (String id : unreachableUploadIds)
				{
					String[] ids = id.split("[|]");

					ProcessItem processItem = new ProcessItem();
					Device device = new Device();

					if (!ValidationUtil.isNullOrEmpty(pv.getPropertyDeviceType())
							&& PropertyEnum.DEVICE_ID.getValue().equals(pv.getPropertyDeviceType()))
					{
						processItem.setMessage(INVALID_DEVICE_ID);
						device.setDeviceId(ids[0]);
					}
					else
					{
						processItem.setMessage(INVALID_NETWORK_ADDRESS);
						HanDevice han = new HanDevice();
						han.setMacAddress(ids[0]);
						device = han;
					}

					processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.INVALID_ID);
					processItem.setDevice(device);

					pv.getProcessItems().add(processItem);
				}
			}
		}
	}

	/**
	 * Sets the process description.
	 * 
	 * @param pv the pv
	 * @param intlTextMulti the intl text multi
	 * @param intlTextSingle the intl text single
	 * @param locale the locale
	 * @return the string
	 */
	private String createProcessDescription(DMProcess pv, String intlTextMulti, String intlTextSingle, Locale locale)
	{
		// Used because when process has only one processItem, description needs to show device network
		// address.
		if (!ValidationUtil.isNullOrEmpty(intlTextSingle) && pv.getTotalSmartpoints() == 1)
		{
			return SensusMessageUtil.getMessage(intlTextSingle, generateMessageValues(pv, locale), null, locale);
		}

		return SensusMessageUtil.getMessage(intlTextMulti, generateMessageValues(pv, locale), null, locale);
	}

	/**
	 * Generate description.
	 * 
	 * @param process the process
	 * @param locale the locale
	 */
	private void generateDescription(DMProcess process, Locale locale)
	{
		if (!ValidationUtil.isNull(process.getProcessType()))
		{
			generateDescriptionProcess(process, locale);
		}
		else
		{
			generateDescriptionAction(process, locale);
		}
	}

	/**
	 * Generate description action.
	 * 
	 * @param process the process
	 * @param locale the locale
	 */
	private void generateDescriptionAction(DMProcess process, Locale locale)
	{
		switch (process.getAction().getActionType().getActionTypeEnum())
		{
			case INITIATE_DEMAND_RESET_EVENT:
				process.setDescription(createProcessDescription(process, DEMAND_RESET, DEMAND_RESET_ONE_SMP, locale));
				break;

			case DISCONNECT_HAN_DEVICE:
				process.setDescription(createProcessDescription(process, DISC_HAN_DEVICE, DISC_HAN_DEVICE_ONE_SMP,
						locale));
				break;

			case SEND_HAN_TEXT_MESSAGE:
				process.setDescription(createProcessDescription(process, SEND_HAN_TEXT_MESSAGE,
						SEND_HAN_TEXT_MESSAGE_ONE_SMP, locale));
				break;

			case GET_HAN_CONNECTION_STATUS:
				process.setDescription(
						createProcessDescription(process, GET_HAN_CONNECTION_STATUS, GET_HAN_CONNECTION_STATUS_ONE_SMP,
								locale));
				break;

			case CONNECT_HAN_DEVICE:
				process.setDescription(createProcessDescription(process, CONN_HAN_DEVICE, CONN_HAN_DEVICE_ONE_SMP,
						locale));
				break;

			case IMPORT_HAN_DEVICE:
				process.setDescription(createProcessDescription(process, IMPORT_HAN_DEVICE, IMPORT_HAN_DEVICE_ONE_SMP,
						locale));
				break;

			case DELETE_HAN_DEVICE:
				process.setDescription(createProcessDescription(process, DEL_HAN_DEVICE, DEL_HAN_DEVICE_ONE_SMP, locale));
				break;

			case INITIATE_DEMAND_RESPONSE_EVENT:
				process.setDescription(createProcessDescription(process, DEMAND_RESPONSE, DEMAND_RESPONSE_ONE_SMP,
						locale));
				break;

			case REMOTE_CONNECT:
				process.setDescription(createProcessDescription(process, REMOTE_CONNECT, REMOTE_CONNECT_ONE_SMP, locale));
				break;

			case REMOTE_DISCONNECT:
				process.setDescription(createProcessDescription(process, REMOTE_DISCONNECT, REMOTE_DISCONNECT_ONE_SMP,
						locale));
				break;

			case REMOTE_ARM_CONNECT:
				process.setDescription(createProcessDescription(process, REMOTE_ARM_CONNECT,
						REMOTE_ARM_CONNECT_ONE_SMP,
						locale));
				break;

			case GET_REMOTE_CONNECT_STATUS:
				process.setDescription(createProcessDescription(process, GET_REMOTE_CONNECT_STATUS,
						GET_REMOTE_CONNECT_STATUS_ONE_SMP,
						locale));
				break;

			case INITIATE_DEMAND_RESPONSE_SETUP:
				process.setDescription(createProcessDescription(process, INITIATE_DEMAND_RESPONSE_SETUP,
						INITIATE_DEMAND_RESPONSE_SETUP_ONE_SMP,
						locale));
				break;

			case DEMAND_READ:
				process.setDescription(createProcessDescription(process, DEMAND_READ_PING,
						DEMAND_READ_PING_ONE_SMP,
						locale));
				break;

			case SET_TAMPER_DETECT_TIMER:
				process.setDescription(createProcessDescription(process, SET_TAMPER_DETECTED,
						SET_TAMPER_DETECTED_ONE_SMP, locale));
				break;

			case GET_TAMPER_DETECT_TIMER:
				process.setDescription(createProcessDescription(process, GET_TAMPER_DETECTED,
						GET_TAMPER_DETECTED_ONE_SMP, locale));
				break;

			case GET_DEMAND_RESPONSE_SETUP_STATUS:
				process.setDescription(createProcessDescription(process, GET_DEMAND_RESPONSE_SETUP,
						GET_DEMAND_RESPONSE_SETUP_ONE_SMP, locale));
				break;

			default:
				if (LOG.isDebugEnabled())
				{
					LOG.debug(LOG_UNABLE_TO_GENERATE_DESCRIPTION
							+ process.getAction().getActionType().getActionTypeEnum().getActionTypeName());
				}
				break;
		}
	}

	/**
	 * Generate description process.
	 * 
	 * @param process the process
	 * @param locale the locale
	 */
	private void generateDescriptionProcess(DMProcess process, Locale locale)
	{
		String processDescription = process.getProcessType().getDescription();

		if (CreateScheduleAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, INSERT_SCHEDULE, null, locale));
		}
		else if (AddGroupToScheduleAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, ADD_GROUP_TO_SCHEDULE, null, locale));
		}
		else if (EditScheduleAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, UPDATE_SCHEDULE, null, locale));
		}
		else if (DeleteDeviceFromScheduleAction.ACTION.equals(processDescription))
		{
			process.setDescription(
					createProcessDescription(process, DEL_SMP_FROM_SCHEDULE, DEL_SMP_FROM_SCHEDULE_ONE_FLEXNETID,
							locale));
		}
		else if (AddDeviceToScheduleAction.ACTION.equals(processDescription))
		{
			process.setDescription(
					createProcessDescription(process, APPLY_SMP_TO_SCHEDULE, UPDATE_SCHEDULE_ONE_FLEXNETID, locale));
		}
		else if (RemoveScheduleAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, DEL_SCHEDULE, null, locale));
		}
		else if (CreateGroupAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, INSERT_GROUP, null, locale));
		}
		else if (RemoveGroupAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, DEL_GROUP, null, locale));
		}
		else if (EditGroupAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, UPDATE_GROUP, null, locale));
		}
		else if (AddDeviceToGroupAction.ACTION.equals(processDescription))
		{
			process.setDescription(
					createProcessDescription(process, ADD_SMP_TO_GROUP, ADD_SMP_TO_GROUP_ONE_FLEXNETID, locale));
		}
		else if (DeleteDeviceFromGroupAction.ACTION.equals(processDescription))
		{
			process.setDescription(
					createProcessDescription(process, DEL_SMP_FROM_GROUP, DEL_SMP_FROM_GROUP_ONE_FLEXNETID, locale));
		}
		else if (CreateTagAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, INSERT_TAG, null, locale));
		}
		else if (AddDeviceToTagAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, ADD_SMP_TO_TAG, ADD_SMP_TO_TAG_ONE_FLEXNETID,
					locale));
		}
		else if (DeleteDeviceFromTagAction.ACTION.equals(processDescription))
		{
			process.setDescription(
					createProcessDescription(process, DEL_SMP_FROM_TAG, DEL_SMP_FROM_TAG_ONE_FLEXNETID, locale));
		}
		else if (RemoveTagAction.ACTION.equals(processDescription))
		{
			process.setDescription(createProcessDescription(process, DELETE_TAG, null, locale));
		}
		else if (GenerateCSVFileAction.ACTION.equals(processDescription))
		{
			if (!FILE_DOWNLOADED_STR.equalsIgnoreCase(process.getDescription()))
			{
				process.setDescription(createProcessDescription(process, GENERATE_CSV_FILE, null, locale));
			}
			else
			{
				process.setDescription(createProcessDescription(process, FILE_DOWNLOADED, null, locale));
			}
		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(LOG_UNABLE_TO_GENERATE_DESCRIPTION + processDescription);
			}
		}
	}

	/**
	 * Checks if is action import or delete.
	 * 
	 * @param response the response
	 * @return true, if is action import or delete
	 */
	private boolean isActionImportOrDelete(InternalResultsResponse<DMProcess> response)
	{
		return !ValidationUtil.isNull(response.getFirstResult())
				&& !ValidationUtil.isNull(response.getFirstResult().getAction())
				&& (ActionTypeEnum.IMPORT_HAN_DEVICE.equals(response.getFirstResult().getAction().getActionType()
						.getActionTypeEnum()) || ActionTypeEnum.DELETE_HAN_DEVICE.equals(response.getFirstResult()
						.getAction().getActionType().getActionTypeEnum()));
	}

	/**
	 * Apply process items to process.
	 * 
	 * @param processRequest the process request
	 * @param response the response
	 */
	private void applyProcessItemsToProcess(ProcessRequest processRequest, InternalResultsResponse<DMProcess> response)
	{
		InternalResultsResponse<ProcessItem> internalResponse = new InternalResultsResponse<ProcessItem>();

		// specific query to import actions once the import can fail and don't create table HAN device records
		// specific query to delete actions once the record not exist in table HAN device records.
		if (isActionImportOrDelete(response))
		{
			internalResponse = getProcessDAC().fetchProcessItemsProcessProperty(processRequest);
		}
		else
		{
			internalResponse = getProcessDAC().fetchProcessItemsByProcessId(processRequest);
		}

		if (internalResponse.isInError())
		{
			response.getMessageInfoList().addAll(internalResponse.getMessageInfoList());
			response.setStatus(internalResponse.getStatus());
		}

		response.getFirstResult().setProcessItems(internalResponse.getResultsList());
	}

	/**
	 * Handle error.
	 * 
	 * @param processResponse the process response
	 * @param responseOld the response old
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> handleError(InternalResultsResponse<DMProcess> processResponse,
			InternalResponse responseOld)
	{
		processResponse.setStatus(responseOld.getStatus());
		processResponse.addMessages(responseOld.getMessageInfoList());
		return processResponse;
	}
}
