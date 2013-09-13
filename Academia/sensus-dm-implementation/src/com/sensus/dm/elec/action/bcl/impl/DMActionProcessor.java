/*
 *
 */
package com.sensus.dm.elec.action.bcl.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.messagetypes.meter.MeterError;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.processor.ActionProcessor;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.action.model.ConnectHanDeviceAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class DMActionProcessor.
 * 
 * @author QAT Global
 * 
 */
public class DMActionProcessor extends ActionProcessor
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(DMActionProcessor.class);

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = ". Error Message: ";

	/** The Constant ERRORS_FOUND. */
	private static final String ERRORS_FOUND = "### Errors found: ";

	/** The Constant ERROR_DETAIL. */
	private static final String ERROR_DETAIL = " Error detail: ";

	/** The Constant DETAIL. */
	private static final String DETAIL = " Detail : ";

	/** The Constant IDENTITY. */
	private static final String IDENTITY = " Identity: ";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = " FlexnetId: ";

	/** The Constant ACTION_PROCESSOR. */
	private static final String ACTION_PROCESSOR = "Action Processor: ";

	/** The Constant LOG_SUMMARIZE. */
	private static final String LOG_SUMMARIZE = "### Summarizing process items: ";

	/** The Constant STR_BEGINNING. */
	protected static final String STR_BEGINNING = " BEGINNING ###";

	/** The Constant STR_FINISHED. */
	protected static final String STR_FINISHED = " FINISHED ###";

	/** The Constant SLASH. */
	private static final String SLASH = "/";

	/** The Constant FAILED. */
	public static final String FAILED = "sensus.dm.common.process.nc.process.failed*";

	/** The Constant INVALID_DEVICE_TYPE. */
	public static final String INVALID_DEVICE_TYPE = "sensus.dm.common.process.nc.device.type.invalid*";

	/** The Constant TIMED_OUT. */
	public static final String TIMED_OUT = "sensus.dm.common.process.nc.process.timed.out*";

	/** The Constant COMPLETED. */
	public static final String COMPLETED = "sensus.dm.common.process.nc.process.completed*";

	/** The Constant PROCESSING_DEVICE. */
	public static final String PROCESSING_DEVICE = "sensus.dm.common.process.processingDevice*";

	/** The Constant INVALID_ACTION. */
	public static final String INVALID_ACTION = "sensus.dm.common.process.nc.process.invalid.action*";

	/** The Constant UNINSTALLED_ELECTRIC_METER. */
	public static final String UNINSTALLED_ELECTRIC_METER = "sensus.dm.common.process.nc.uninstalled.electric.meter*";

	/** The Constant INVALID_TARGET. */
	public static final String INVALID_TARGET = "sensus.dm.common.process.nc.process.invalid*";

	/** The Constant INVALID_ENROLLMENT_CODE. */
	public static final String INVALID_ENROLLMENT_CODE = "sensus.dm.common.process.nc.enrollmentCode.invalid*";

	/** The Constant DISCONNECTED. */
	public static final String DISCONNECTED = "sensus.dm.common.process.nc.process.disconnected*";

	/** The Constant EXCEPTION. */
	public static final String EXCEPTION = "sensus.dm.common.process.nc.process.exception*";

	/** The Constant DECODE_FAILED. */
	public static final String DECODE_FAILED = "sensus.dm.common.process.nc.process.decode.failed*";

	/** The Constant MISSING_METER_ID. */
	public static final String MISSING_METER_ID = "sensus.dm.common.process.nc.process.missing.meter.id*";

	/** The Constant INVALID_FOR_ELECTRIC_METERS. */
	public static final String INVALID_FOR_ELECTRIC_METERS =
			"sensus.dm.common.process.nc.process.invalid.for.electric.meters*";

	/** The Constant INVALID_FOR_DISCONNECTED_HAN_DEVICES. */
	public static final String INVALID_FOR_DISCONNECTED_HAN_DEVICES =
			"sensus.dm.common.process.nc.process.invalid.for.disconnected.han.devices*";

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The process summary bcl. */
	private IProcessSummaryBCL processSummaryBCL;

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/** The device bcl. */
	private IDeviceBCL deviceBCL;

	/** The han action processor. */
	private HanActionProcessor hanActionProcessor;

	/** The meter action processor. */
	private MeterActionProcessor meterActionProcessor;

	/** The validator. */
	private DMActionProcessorValidator validator;

	/** The Constant ENTEK_DEVICE_TYPE_ID. */
	protected static final int ENTEK_DEVICE_TYPE_ID = 26;

	/** The Constant CALLING_API. */
	protected static final String CALLING_API = " Calling Sensus BCF for flexnetId: ";

	/** The Constant API_CALLED. */
	protected static final String API_CALLED = " Sensus BCF called for flexnetId: ";

	/** The Constant IGNORING_CALL. */
	protected static final String IGNORING_CALL = " Ignoring call for flexnetId: ";

	/** The Constant EXCEPTION_FOUND. */
	protected static final String EXCEPTION_FOUND = " Exception/Error for flenxetId: ";

	/** The Constant DOT_ERRORS. */
	protected static final String DOT_ERRORS = " Errors: ";

	/** The Constant COMMA. */
	protected static final String COMMA = ", ";

	/** The Constant RNICORRELATION_ID. */
	protected static final String RNI_CORRELATION_ID = " RNICorrelationId: ";

	/** The Constant STR_LOG. */
	protected static final String STR_LOG = "### ";

	/** The Constant SIXTY. */
	protected static final int SIXTY = 60;

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	@Resource
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the electric meter bcl.
	 * 
	 * @return the electric meter bcl
	 */
	public IElectricMeterBCL getElectricMeterBCL()
	{
		return electricMeterBCL;
	}

	/**
	 * Sets the electric meter bcl.
	 * 
	 * @param electricMeterBCL the new electric meter bcl
	 */
	public void setElectricMeterBCL(IElectricMeterBCL electricMeterBCL)
	{
		this.electricMeterBCL = electricMeterBCL;
	}

	/**
	 * Gets the device bcl.
	 * 
	 * @return the device bcl
	 */
	public IDeviceBCL getDeviceBCL()
	{
		return deviceBCL;
	}

	/**
	 * Sets the device bcl.
	 * 
	 * @param deviceBCL the new device bcl
	 */
	public void setDeviceBCL(IDeviceBCL deviceBCL)
	{
		this.deviceBCL = deviceBCL;
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
	@Resource
	public void setProcessSummaryBCL(IProcessSummaryBCL processSummaryBCL)
	{
		this.processSummaryBCL = processSummaryBCL;
	}

	/**
	 * Gets the han action processor.
	 * 
	 * @return the han action processor
	 */
	public HanActionProcessor getHanActionProcessor()
	{
		return hanActionProcessor;
	}

	/**
	 * Sets the han action processor.
	 * 
	 * @param hanActionProcessor the new han action processor
	 */
	public void setHanActionProcessor(HanActionProcessor hanActionProcessor)
	{
		this.hanActionProcessor = hanActionProcessor;
	}

	/**
	 * Gets the meter action processor.
	 * 
	 * @return the meter action processor
	 */
	public MeterActionProcessor getMeterActionProcessor()
	{
		return meterActionProcessor;
	}

	/**
	 * Sets the meter action processor.
	 * 
	 * @param meterActionProcessor the new meter action processor
	 */
	public void setMeterActionProcessor(MeterActionProcessor meterActionProcessor)
	{
		this.meterActionProcessor = meterActionProcessor;
	}

	/**
	 * Gets the validator.
	 * 
	 * @return the validator
	 */
	public DMActionProcessorValidator getValidator()
	{
		return validator;
	}

	/**
	 * Sets the validator.
	 * 
	 * @param validator the new validator
	 */
	public void setValidator(DMActionProcessorValidator validator)
	{
		this.validator = validator;
	}

	/**
	 * Gets the dM action.
	 * 
	 * @return the dM action
	 */
	public DMAction getDMAction()
	{
		return (DMAction)getAction();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.scheduler.processor.ActionProcessor#execute()
	 */
	@Override
	public void execute()
	{
		printLogInfo(STR_LOG + ACTION_PROCESSOR + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
				+ STR_BEGINNING);

		ProcessRequest processRequest =
				new ProcessRequest(new DMProcess(getDMAction().getProcessId(), getDMAction(), getDMAction()
						.getRniEventId()), new UserContext(getAction().getCreateUser()));

		// for process items who will fail at sync calls or completed items which DM will not wait for notification back
		List<ProcessItem> updateItemList = new ArrayList<ProcessItem>();

		// handling blacklist to add device in process summarize.
		if (!verifyDevicesExcluded(processRequest, updateItemList))
		{
			printLogInfo(STR_LOG + "Action "
					+ getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ " - Error when checking OPT-OUT list.");
			return;
		}

		// We might have actions that has only opt out devices.
		if (!ValidationUtil.isNullOrEmpty(getDMAction().getDevices()))
		{

			// different treatment to cancel send han text message and cancel demand response
			if (getValidator().isCancelAction(getDMAction()))
			{
				executeCancelAction(processRequest, updateItemList);
			}

			// different treatment actions which sends All devices in one shot (RNI accepts a device list)
			if (!getDMAction().sendByDevice() && !executeActionByGroup(processRequest, updateItemList))
			{
				printLogInfo(STR_LOG + "Action "
						+ getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
						+ " not executed. First device of list: "
						+ getDMAction().getFirstDevice().getDeviceId());
			}

			// all other actions are handle the same way, individually
			else
			{
				processRequest.getFirstProcess().setProcessItems(new ArrayList<ProcessItem>());

				// roll devices list executing specific action for each device
				for (Device device : getDMAction().getDevices())
				{
					// executes the action according its type (demand reset, demand response, connect han, etc)
					if (!executeActionByDevice(getDMAction(), processRequest, device, updateItemList))
					{
						printLogInfo(STR_LOG + "Action "
								+ getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
								+ " not executed for deviceId " + device.getDeviceId());
					}

					processRequest.getFirstProcess().getProcessItems().clear();
				}
			}
		}

		// update process items that failed or are completed
		if (!ValidationUtil.isNullOrEmpty(updateItemList))
		{
			summarizeProcess(processRequest, updateItemList);
		}

		printLogInfo(STR_LOG + ACTION_PROCESSOR + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
				+ STR_FINISHED);

	}

	/**
	 * Check action result.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @param errors the errors
	 * @param updateProcessItemComplete the update process item complete
	 * @return true, if successful
	 */
	protected boolean checkActionResult(ProcessItem processItem, List<ProcessItem> updatedItemList,
			List<String> errors, boolean updateProcessItemComplete)
	{
		printLogInfo(STR_LOG + "Checking action result for Device Id: " + processItem.getDevice().getDeviceId());

		// if the API call failed or timed out, put this item on failed list
		if (!ValidationUtil.isNullOrEmpty(errors))
		{
			processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
			processItem.setMessage(DMUtil.generateMessageRni(errors.toString()));
			updatedItemList.add(processItem);

			for (String error : errors)
			{
				printLogInfo(STR_LOG + "There are errors for Device Id: "
						+ processItem.getDevice().getDeviceId() +
						" Error: " + error);
			}
		}

		// if there is no error and should mark item as complete on sync call
		else
		{
			if (updateProcessItemComplete)
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);
				updatedItemList.add(processItem);
			}
		}

		return ValidationUtil.isNullOrEmpty(errors);
	}

	/**
	 * Check errors.
	 * 
	 * @param processItem the process item
	 * @param updateItemList the update item list
	 * @param errors the errors
	 */
	private void checkErrors(ProcessItem processItem, List<ProcessItem> updateItemList, List<MeterError> errors)
	{
		for (MeterError error : errors)
		{
			if (error.getMeterIdentity().getMeterNo().equals(processItem.getDevice().getDeviceId()))
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
				processItem.setMessage(DMUtil.generateMessageRni(error.getErrorDetail().getDescription()));
				updateItemList.add(processItem);

				printLogInfo(STR_LOG + ERROR_DETAIL + FLEXNET_ID + error.getFlexnetId() +
						IDENTITY + error.getMeterIdentity() + DETAIL + error.getErrorDetail());
			}
		}
	}

	/**
	 * Check action result.
	 * 
	 * @param processItem the process item
	 * @param updateItemList the update item list
	 * @param errors the errors
	 * @return true, if successful
	 */
	protected boolean checkActionResult(ProcessItem processItem, List<ProcessItem> updateItemList,
			List<MeterError> errors)
	{
		printLogInfo(STR_LOG + "Checking action result for Device Id: " + processItem.getDevice().getDeviceId());

		if (ValidationUtil.isNullOrEmpty(errors))
		{
			return true;
		}

		printLogInfo(ERRORS_FOUND + errors.size());

		checkErrors(processItem, updateItemList, errors);

		return false;
	}

	/**
	 * Check action result.
	 * 
	 * @param processItems the process items
	 * @param updateItemList the update item list
	 * @param errors the errors
	 * @return true, if successful
	 */
	protected boolean checkActionResult(List<ProcessItem> processItems, List<ProcessItem> updateItemList,
			List<MeterError> errors)
	{
		printLogInfo(STR_LOG + "Checking action result for Device list.");

		if (ValidationUtil.isNullOrEmpty(errors))
		{
			return true;
		}

		printLogInfo(ERRORS_FOUND + errors.size());

		for (ProcessItem processItem : processItems)
		{
			checkErrors(processItem, updateItemList, errors);
		}

		return false;
	}

	/**
	 * Verify devices excluded.
	 * 
	 * @param processRequest the process request
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	private boolean verifyDevicesExcluded(ProcessRequest processRequest, List<ProcessItem> updateItemList)
	{
		// if there are devices excluded (opt out list filled) then create processItem for each one
		if (!ValidationUtil.isNullOrEmpty(getDMAction().getDevicesExcluded()))
		{
			for (Device device : getDMAction().getDevicesExcluded())
			{
				if (!checkProcessItem(getDMAction(), processRequest, device,
						ProcessItemStatusEnum.OPT_OUT))
				{
					return false;
				}

				updateItemList.add(processRequest.getFirstProcess().getFirstProcessItem());

				processRequest.getFirstProcess().getProcessItems().clear();
			}
		}

		return true;
	}

	/**
	 * Execute action by group.
	 * 
	 * @param processRequest the process request
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	private boolean executeActionByGroup(ProcessRequest processRequest,
			List<ProcessItem> updateItemList)
	{

		// check device per device if they have processItem already created
		// if not, create the processItem (in this case - remote action - it will always create)
		for (Device device : getDMAction().getDevices())
		{
			if (!checkProcessItem(getDMAction(), processRequest, device, ProcessItemStatusEnum.RUNNING))
			{
				return false;
			}
		}

		getMeterActionProcessor().setAction(getAction());

		// as soon as new actions appears, create a switch here
		if (getValidator().isRemoteAction(getDMAction()))
		{
			return getMeterActionProcessor()
					.executeRemoteAction(((DMAction)getAction()).getRniEventId(),
							processRequest.getFirstProcess(), updateItemList);
		}

		return false;

	}

	/**
	 * Execute cancel action.
	 * 
	 * @param processRequest the process request
	 * @param updateItemList the update item list
	 */
	private void executeCancelAction(ProcessRequest processRequest,
			List<ProcessItem> updateItemList)
	{

		// fetch to find the previous process in running state
		InternalResultsResponse<DMProcess> internalResponse =
				getProcessBCL().fetchProcessesWithProcessItemInProcessing(
						new ProcessRequest(processRequest.getFirstProcess(), processRequest.getUserContext(),
								ProcessItemStatusEnum.RUNNING));

		if (internalResponse.isInError()
				&& !ValidationUtil.isNullOrEmpty(internalResponse.getResultsList()))
		{
			printLogInfo(STR_LOG + " Failed to find process item using process_id: "
					+ getDMAction().getProcessId());
			return;
		}

		ProcessRequest request =
				new ProcessRequest(new DMProcess(internalResponse.getFirstResult().getAction(), internalResponse
						.getFirstResult().getProperties(), internalResponse.getFirstResult().getRniEventId()));

		for (ProcessItem processItem : internalResponse.getFirstResult().getProcessItems())
		{
			request.getFirstProcess().addProcessItem(processItem);

			switch (request.getFirstProcess().getAction().getActionType().getActionTypeEnum())
			{
				case INITIATE_DEMAND_RESPONSE_EVENT:

					DemandResponseEventAction action = new DemandResponseEventAction(request
							.getFirstProcess().getAction().getId());

					DMConvertUtil.convertPropertyToDemandResponse(action, request.getFirstProcess()
							.getProperties());

					getMeterActionProcessor().setAction(action);

					if (!getMeterActionProcessor().executeCancelDemandResponse(
							request.getFirstProcess().getFirstProcessItem(),
							request.getFirstProcess().getRniEventId()))
					{
						printLogInfo(STR_LOG + "Action "
								+ getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
								+ " not executed for flexnetId: "
								+ processItem.getDevice().getRadio().getFlexNetId()
								+ " RNI Event Id: " + request.getFirstProcess().getRniEventId());
					}
					break;

				case SEND_HAN_TEXT_MESSAGE:

					getHanActionProcessor().setAction(getAction());

					if (!getHanActionProcessor().executeCancelSendHanTextMessage(
							request.getFirstProcess().getFirstProcessItem()))
					{
						printLogInfo(STR_LOG + "Action "
								+ getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
								+ " not executed for flexnetId: "
								+ processItem.getDevice().getRadio().getFlexNetId());
					}
					break;

				default:
					break;

			}

			request.getFirstProcess().getProcessItems().clear();
		}

	}

	/**
	 * Execute han action.
	 * 
	 * @param rniEventId the rni event id
	 * @param processRequest the process request
	 * @param device the device
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	private boolean executeHanAction(Integer rniEventId, ProcessRequest processRequest, Device device,
			List<ProcessItem> updateItemList)
	{
		getHanActionProcessor().setAction(getAction());

		switch (getDMAction().getActionType().getActionTypeEnum())
		{
			case IMPORT_HAN_DEVICE:
				if (validateImportHanAction(processRequest, device))
				{
					return executeImportHanAction(rniEventId, processRequest, device, updateItemList);
				}

			case RETRY_IMPORT_HAN_DEVICE:
				ProcessItem processItem = processRequest.getFirstProcess().getFirstProcessItem();
				DMConvertUtil.convertPropertyToDevice(device, processItem.getProperties());
				processItem.setDevice(device);
				return executeImportHanAction(rniEventId, processRequest, device, updateItemList);

			case DELETE_HAN_DEVICE:
				return executeDeleteDeviceAction(processRequest.getFirstProcess().getFirstProcessItem(), updateItemList);

			case SEND_HAN_TEXT_MESSAGE:
				return getHanActionProcessor().executeSendHanTextMessage(
						processRequest.getFirstProcess().getFirstProcessItem(), updateItemList);

			case CONNECT_HAN_DEVICE:
				return getHanActionProcessor().executeConnectHanDevice(rniEventId,
						processRequest.getFirstProcess().getFirstProcessItem(), updateItemList);

			case DISCONNECT_HAN_DEVICE:
				return getHanActionProcessor().executeDisconnectHanDevice(
						processRequest.getFirstProcess().getFirstProcessItem(),
						updateItemList);

			case GET_HAN_CONNECTION_STATUS:
				return getHanActionProcessor().executeGetHanConnectionStatus(rniEventId,
						processRequest.getFirstProcess().getFirstProcessItem(), updateItemList);

			case GET_TAMPER_DETECT_TIMER:
				return getHanActionProcessor().executeGetTamperDetectTimeout(rniEventId,
						processRequest.getFirstProcess().getFirstProcessItem(), updateItemList);

			case GET_DEMAND_RESPONSE_SETUP_STATUS:
				return getHanActionProcessor().executeGetDemandResponseSetupStatus(rniEventId,
						processRequest.getFirstProcess().getFirstProcessItem(), updateItemList);

			default:
				break;
		}

		return false;
	}

	/**
	 * Validate import han action.
	 * 
	 * @param processRequest the process request
	 * @param device the device
	 * @return true, if successful
	 */
	private boolean validateImportHanAction(ProcessRequest processRequest, Device device)
	{
		ProcessItem processItem = processRequest.getFirstProcess().getFirstProcessItem();

		// create property To processItem
		processItem.setProperties(new ArrayList<Property>());
		DMConvertUtil.convertHanDeviceToProperty(device, processItem.getProperties());

		// insert properties for each process item (deviceId, manufacturer, model, etc)

		InternalResponse response = getProcessBCL().updateProcessItems(processRequest);

		if (response.isInError())
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ "Error saving processItem properties when importing for flexnetId "
					+ processItem.getDevice().getRadio().getFlexNetId() + ": "
					+ response.getMessageInfoList().toString());
			return false;
		}
		return true;
	}

	/**
	 * Execute import han action.
	 * 
	 * @param rniEventId the rni event id
	 * @param processRequest the process request
	 * @param device the device
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	private boolean executeImportHanAction(Integer rniEventId, ProcessRequest processRequest, Device device,
			List<ProcessItem> updateItemList)
	{

		// send only if it is NOT connected (validations inside)
		getHanActionProcessor().setAction(getAction());
		if (!getHanActionProcessor().executeImportHanDeviceAction(rniEventId,
				processRequest.getFirstProcess().getFirstProcessItem(),
				updateItemList))
		{
			return false;
		}

		// if no errors found when importing and if the install code is available, try to connect it
		if (ProcessItemStatusEnum.RUNNING.equals(processRequest.getFirstProcess().getFirstProcessItem()
				.getProcessItemStatusEnum())
				&& getValidator().isInstallCodeAvailable(device))
		{
			getHanActionProcessor().setAction(new ConnectHanDeviceAction());
			return getHanActionProcessor().executeConnectHanDevice(rniEventId,
					processRequest.getFirstProcess().getFirstProcessItem(),
					updateItemList);
		}

		return true;

	}

	/**
	 * Execute meter action.
	 * 
	 * @param rniEventId the rni event id
	 * @param processRequest the process request
	 * @param device the device
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	private boolean executeMeterAction(Integer rniEventId, ProcessRequest processRequest, Device device,
			List<ProcessItem> updateItemList)
	{
		getMeterActionProcessor().setAction(getAction());

		switch (getDMAction().getActionType().getActionTypeEnum())
		{
			case GET_DEMAND_RESPONSE_EVENT_STATUS:
				return getMeterActionProcessor().executeGetDemandResponseStatus(rniEventId, new ProcessItem(device));

			case INITIATE_DEMAND_RESET_EVENT:
				return getMeterActionProcessor().executeDemandResetEvent(
						processRequest.getFirstProcess().getFirstProcessItem(),
						updateItemList);

			case INITIATE_DEMAND_RESPONSE_EVENT:
				return getMeterActionProcessor().executeDemandResponseEvent(rniEventId
						, processRequest.getFirstProcess().getFirstProcessItem(), updateItemList);

			case INITIATE_DEMAND_RESPONSE_SETUP:

				// if it is a flexnet lcm and there is NOT an enrollment code set in the action,
				// then this came from the flexnet LCM detail page and values are set inside each lcmRelay
				if (ValidationUtil.isNull(((DemandResponseSetupAction)getDMAction()).getEnrollmentCode())
						&& getValidator().isEntekLCM(device))
				{
					return getMeterActionProcessor().executeDemandResponseSetupMultipleRelay(
							rniEventId, processRequest.getFirstProcess().getFirstProcessItem(),
							updateItemList);
				}

				// this came from the list page, so set the values the normal way and call only once
				return getMeterActionProcessor().executeDemandResponseSetup(rniEventId,
						processRequest.getFirstProcess().getFirstProcessItem(),
						updateItemList);

			case DEMAND_READ:
				return getMeterActionProcessor().executeDemandReadPing(rniEventId,
						processRequest.getFirstProcess().getFirstProcessItem(),
						updateItemList);

			case SET_TAMPER_DETECT_TIMER:
				return getMeterActionProcessor().executeSetTamperDetectTimeout(rniEventId,
						processRequest.getFirstProcess().getFirstProcessItem(),
						updateItemList);

			default:
				break;
		}

		return false;
	}

	/**
	 * Execute delete device action.
	 * 
	 * @param processItem the process item
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	private boolean executeDeleteDeviceAction(ProcessItem processItem,
			List<ProcessItem> updateItemList)
	{

		// ProcessItem processItem = new ProcessItem(device);
		// expire all process items related the device
		// the expire method also mark the process as complete in case the process is waiting for only this device
		// listener response
		InternalResponse response =
				getProcessBCL().updateProcessItemsToExpire(
						new ProcessRequest(new DMProcess(processItem), new UserContext(
								getAction().getCreateUser())));
		if (response.isInError())
		{
			printLogInfo(STR_LOG + " Error expiring process items for flexnetId: "
					+ processItem.getDevice().getRadio().getFlexNetIdAsString()
					+ EXCEPTION_FOUND + response.getMessageInfoList());
			return false;
		}

		// pass the process id as process item id once the delete han action needs it
		// need to add if after the query above, otherwise the query will not work
		// processRequest.getFirstProcess().addProcessItem(processItem);
		// processRequest.getFirstProcess().getFirstProcessItem().setId(processRequest.getFirstProcess().getId());

		// if it is a ENTEK LCM Han Device
		if (getValidator().isEntekLCM(processItem.getDevice()))
		{
			// ENTEK - Remove meter of table meter list
			getMeterActionProcessor().setAction(getAction());
			if (!getMeterActionProcessor().executeDeleteMeterAction(processItem, updateItemList))
			{
				return false;
			}
		}

		// If this is an Entek and has an error in the deleting from the meter list, should not continue
		// to the next action. If is not an Entek, will not cause a problem.
		if (ValidationUtil.isNullOrEmpty(updateItemList))
		{
			getHanActionProcessor().setAction(getAction());
			if (!getHanActionProcessor().executeDeleteHanDeviceAction(processItem, updateItemList))
			{
				return false;
			}
		}

		// check if the delete on RNI side worked fine
		// if so, deletes the device references on DMs side
		for (ProcessItem pi : updateItemList)
		{
			if (!ProcessItemStatusEnum.FAILED.equals(pi.getProcessItemStatusEnum()))
			{
				response = getElectricMeterBCL().deleteDevice(new DeviceRequest(pi.getDevice()));
				if (response.isInError())
				{
					printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
							+ " Error deleting flexnetId " + pi.getDevice().getRadio().getFlexNetId()
							+ " on DM tables: " + response.getMessageInfoList());
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Execute action.
	 * 
	 * @param action the action
	 * @param processRequest the process request
	 * @param device the device
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	private boolean executeActionByDevice(DMAction action, ProcessRequest processRequest, Device device,
			List<ProcessItem> updateItemList)
	{

		// creates processItem for those actions that process/processItem is needed
		// fetch processItem otherwise
		if (!checkProcessItem(action, processRequest, device, ProcessItemStatusEnum.RUNNING))
		{
			return false;
		}

		Integer rniEventId = ((DMAction)getAction()).getRniEventId();

		if (getValidator().isHanAction(action))
		{
			return executeHanAction(rniEventId, processRequest, device, updateItemList);
		}

		return executeMeterAction(rniEventId, processRequest, device, updateItemList);

	}

	/**
	 * Check process item.
	 * 
	 * @param action the action
	 * @param processRequest the process request
	 * @param device the device
	 * @param processItemStatus the process item status
	 * @return true, if successful
	 */
	private boolean checkProcessItem(DMAction action, ProcessRequest processRequest, Device device,
			ProcessItemStatusEnum processItemStatus)
	{
		if (action.isProcessRequired())
		{
			processRequest.getFirstProcess().addProcessItem(new ProcessItem(device, processItemStatus));

			InternalResultsResponse<ProcessItem> response =
					getProcessBCL().insertProcessItems(processRequest);

			if (response.isInError())
			{
				printLogInfo(STR_LOG + " Failed inserting process item id:"
						+ processRequest.getFirstProcess().getFirstProcessItem().getId() + ERROR_MESSAGE
						+ response.getMessageInfoList());
				return false;
			}

		}
		else
		{
			// try to fetch the process item
			// some actions don't create process (they use the same process id of its previous call - for example
			// Get Demand Response Status and Retry of Import HAN)
			InternalResultsResponse<DMProcess> fetchResponse = null;
			if (ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS.equals(action.getActionType().getActionTypeEnum())
					&& ValidationUtil.isNullOrZero(action.getProcessId()))
			{
				fetchResponse =
						getProcessSummaryBCL().fetchProcessesDemandResponseStatus(
								new ProcessRequest(new DMProcess(getDMAction(), new ProcessItem(device),
										ProcessStatusEnum.IN_PROCESS),
										device.getRadio().getCustomerId()));
			}
			else
			{
				fetchResponse = getProcessBCL().fetchProcessById(processRequest);
			}

			if (fetchResponse.isInError() && !fetchResponse.hasResults())
			{
				printLogInfo(STR_LOG + " Failed to find process item using device/process id: "
						+ device.getDeviceId() + SLASH + processRequest.getFirstProcess().getId() + ERROR_MESSAGE
						+ fetchResponse.getMessageInfoList());
				return false;
			}

			action.setRniEventId(fetchResponse.getFirstResult().getRniEventId());
			processRequest.getFirstProcess().addProcessItem(fetchResponse.getFirstResult().getFirstProcessItem());

		}

		return true;

	}

	/**
	 * Fetch electric meter.
	 * 
	 * @param device the device
	 * @return the device
	 */
	protected Device fetchElectricMeter(Device device)
	{
		switch (device.getDeviceType())
		{
			case HAN_DEVICE:
				return fetchDevice(((HanDevice)device).getElectricMeterFlexNetId(), device.getRadio()
						.getCustomerId());
			case LCM:
				return fetchDevice(((LCM)device).getElectricMeterFlexNetId(), device.getRadio()
						.getCustomerId());
			case ELECTRIC_METER:
				return device;
			default:
				return null;
		}

	}

	/**
	 * Fetch device.
	 * 
	 * @param flexnetId the flexnet id
	 * @param customerId the customer id
	 * @return the device
	 */
	protected Device fetchDevice(BigInteger flexnetId, String customerId)
	{
		if (ValidationUtil.isNull(flexnetId) || ValidationUtil.isNull(customerId))
		{
			return null;
		}

		InternalResultsResponse<Device> response =
				getElectricMeterBCL().fetchDeviceById(
						new DeviceRequest(new Device(flexnetId, DeviceTypeEnum.ELECTRIC_METER),
								new DMTenant(customerId)));

		if (response.isInError() || !response.hasResults())
		{
			return null;
		}

		return response.getFirstResult();
	}

	/**
	 * Summarize process.
	 * 
	 * @param processRequest the process request
	 * @param updatedItemList the failed item list
	 */
	private void summarizeProcess(ProcessRequest processRequest, List<ProcessItem> updatedItemList)
	{

		// updates all process items (failed or succeeded) and summarize them
		// summarize is called only if there is at least one failed item

		printLogInfo(LOG_SUMMARIZE + "There are " + updatedItemList.size()
				+ " process items to summarize.");

		processRequest.getFirstProcess().setProcessItems(updatedItemList);

		printLogInfo(LOG_SUMMARIZE + "Updating process items to process: " + processRequest.getFirstProcess().getId());

		// updates all process items that failed in one shot
		InternalResponse updatedResponse =
				getProcessBCL().updateProcessItems(processRequest);

		printLogInfo(LOG_SUMMARIZE + "Process items updated. Status: "
				+ updatedResponse.getStatus() + ERROR_MESSAGE
				+ updatedResponse.getMessageInfoList());

		if (updatedResponse.getStatus().equals(Status.OperationSuccess))
		{
			// summarize scheduler
			InternalResponse summarizeResponse = getProcessBCL().summarizeProcess(processRequest);

			printLogInfo(LOG_SUMMARIZE + "Summarize process called: "
					+ summarizeResponse.getStatus() + ERROR_MESSAGE
					+ summarizeResponse.getMessageInfoList());
		}
	}

	/**
	 * Prints the log info.
	 * 
	 * @param log the log
	 */
	protected void printLogInfo(String log)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(log);
		}
	}

	/**
	 * Prints the log error.
	 * 
	 * @param log the log
	 */
	protected void printLogError(String log)
	{
		if (LOG.isErrorEnabled())
		{
			LOG.error(log);
		}
	}

}
