package com.sensus.dm.elec.action.bcl.impl;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.sensus.bcf.connectdisconnect.ConnectDisconnectService;
import com.sensus.bcf.drserver.service.DrService;
import com.sensus.bcf.meterlifecycle.service.MeterLifecycleService;
import com.sensus.bcf.meterreading.service.MeterReadingService;
import com.sensus.bcf.meterreading.service.camel.MeterReadingServiceSOAImpl;
import com.sensus.bcf.meterreading.service.camel.PostRefreshOptions;
import com.sensus.common.messagetypes.dr.LoadControlEvent;
import com.sensus.common.messagetypes.dr.LoadControlParam;
import com.sensus.common.messagetypes.dr.ParameterIdentifierEnum;
import com.sensus.common.messagetypes.error.ErrorDetail;
import com.sensus.common.messagetypes.events.InitiatorId;
import com.sensus.common.messagetypes.loadshed.MeterRelays;
import com.sensus.common.messagetypes.meter.MeterError;
import com.sensus.common.messagetypes.meter.MeterIdentity;
import com.sensus.common.messagetypes.meterinfo.Meter;
import com.sensus.common.messagetypes.meterinfo.MeterLifeCycleState;
import com.sensus.common.messagetypes.meterinfo.MeterLifeCycleStateType;
import com.sensus.common.messagetypes.service.ServiceType;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.DemandResponseSetupAction;
import com.sensus.dm.elec.action.model.SetTamperDetectTimerAction;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.gateway.cd.clientcontext.ClientContext;
import com.sensus.gateway.cd.clientcontext.ObjectFactory;

/**
 * The Class MeterActionProcessor.
 * 
 * @author QAT Global
 */
public class MeterActionProcessor extends DMActionProcessor
{

	/** The Constant JMS_URL_PREFIX. */
	private static final String JMS_URL_PREFIX = "jms:";

	/** The Constant DEMAND_READ_PING_QUEUE_LISTENER. */
	public static final String DEMAND_READ_PING_QUEUE_LISTENER = "DeviceManager.DemandReadPingQueue";

	/** The Constant DEMAND_READ_PING_QUEUE_LISTENER_JMS. */
	public static final String DEMAND_READ_PING_QUEUE_LISTENER_JMS = JMS_URL_PREFIX + DEMAND_READ_PING_QUEUE_LISTENER;

	/** The business component facade implementation is defined in the BCF layer. */
	private DrService drService;

	/** The meter reading service. */
	private MeterReadingService meterReadingService;

	/** The meter lifecycle service. */
	private MeterLifecycleService meterLifecycleService;

	/** The connect disconnect service. */
	private ConnectDisconnectService connectDisconnectService;

	/** The client context marshaller. */
	private Jaxb2Marshaller clientContextMarshaller;

	/** The demand read ping profile. */
	private String demandReadPingProfile;

	/** The Constant REMOTE_CONNECT_DISCONNECT_QUEUE. */
	public static final String REMOTE_CONNECT_DISCONNECT_QUEUE = "DeviceManager.CDStateChangeNotification.Event.Queue"; // listener

	/** The Constant REMOTE_CONNECT_DISCONNECT_QUEUE_ENDPOINT. */
	public static final String REMOTE_CONNECT_DISCONNECT_QUEUE_ENDPOINT = JMS_URL_PREFIX
			+ REMOTE_CONNECT_DISCONNECT_QUEUE; // sync
	// call

	/** The Constant ALL_DEVICE_CLASS. */
	private static final String ALL_DEVICE_CLASS = "All";

	/** The Constant TIME_OUT. */
	private static final Integer TIME_OUT = 255;

	/** The Constant INT_ZERO. */
	private static final int INT_ZERO = 0;

	/** The Constant INT_ONE. */
	private static final int INT_ONE = 1;

	/**
	 * Gets the meter reading service.
	 * 
	 * @return the meterReadingService
	 */
	public MeterReadingService getMeterReadingService()
	{
		return meterReadingService;
	}

	/**
	 * Sets the meter reading service.
	 * 
	 * @param meterReadingService the meterReadingService to set
	 */
	public void setMeterReadingService(MeterReadingService meterReadingService)
	{
		this.meterReadingService = meterReadingService;
	}

	/**
	 * Gets the meter lifecycle service.
	 * 
	 * @return the meter lifecycle service
	 */
	public MeterLifecycleService getMeterLifecycleService()
	{
		return meterLifecycleService;
	}

	/**
	 * Sets the meter lifecycle service.
	 * 
	 * @param meterLifecycleService the new meter lifecycle service
	 */
	public void setMeterLifecycleService(MeterLifecycleService meterLifecycleService)
	{
		this.meterLifecycleService = meterLifecycleService;
	}

	/**
	 * Gets the dr service.
	 * 
	 * @return the dr service
	 */
	public DrService getDrService()
	{
		return drService;
	}

	/**
	 * Sets the dr service.
	 * 
	 * @param drService the new dr service
	 */
	public void setDrService(DrService drService)
	{
		this.drService = drService;
	}

	/**
	 * Gets the connect disconnect service.
	 * 
	 * @return the connect disconnect service
	 */
	public ConnectDisconnectService getConnectDisconnectService()
	{
		return connectDisconnectService;
	}

	/**
	 * Sets the connect disconnect service.
	 * 
	 * @param connectDisconnectService the new connect disconnect service
	 */
	public void setConnectDisconnectService(ConnectDisconnectService connectDisconnectService)
	{
		this.connectDisconnectService = connectDisconnectService;
	}

	/**
	 * Gets the client context marshaller.
	 * 
	 * @return the client context marshaller
	 */
	public Jaxb2Marshaller getClientContextMarshaller()
	{
		return clientContextMarshaller;
	}

	/**
	 * Sets the client context marshaller.
	 * 
	 * @param clientContextMarshaller the new client context marshaller
	 */
	public void setClientContextMarshaller(Jaxb2Marshaller clientContextMarshaller)
	{
		this.clientContextMarshaller = clientContextMarshaller;
	}

	/**
	 * Gets the demand read ping profile.
	 * 
	 * @return the demandReadPingProfile
	 */
	public String getDemandReadPingProfile()
	{
		return demandReadPingProfile;
	}

	/**
	 * Sets the demand read ping profile.
	 * 
	 * @param demandReadPingProfile the demandReadPingProfile to set
	 */
	public void setDemandReadPingProfile(String demandReadPingProfile)
	{
		this.demandReadPingProfile = demandReadPingProfile;
	}

	/**
	 * Gets the DM action.
	 * 
	 * @return the DM action
	 */
	@Override
	public DMAction getDMAction()
	{
		return (DMAction)getAction();
	}

	/**
	 * Execute remote action.
	 * 
	 * @param rniEventId the rni event id
	 * @param process the process
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeRemoteAction(Integer rniEventId, DMProcess process, List<ProcessItem> updatedItemList)
	{
		// remote actions are only allowed for electric meters
		// not HAN devices or Entek LCMs
		List<ProcessItem> goodProcessItems = getValidator().validateRemoteAction(process, updatedItemList);

		List<MeterError> errors = null;

		try
		{
			switch (getDMAction().getActionType().getActionTypeEnum())
			{
				case REMOTE_CONNECT:
					errors = getConnectDisconnectService().remoteConnect(
							createMeterRelays(goodProcessItems),
							createClientContext(rniEventId),
							Boolean.FALSE);
					break;

				case REMOTE_DISCONNECT:
					errors = getConnectDisconnectService().remoteDisconnect(
							createMeterRelays(goodProcessItems),
							createClientContext(rniEventId),
							Boolean.FALSE);
					break;

				case REMOTE_ARM_CONNECT:
					errors = getConnectDisconnectService().remoteArm(
							createMeterRelays(goodProcessItems),
							createClientContext(rniEventId),
							Boolean.FALSE);
					break;

				case GET_REMOTE_CONNECT_STATUS:
					errors = getConnectDisconnectService().getRemoteConnectionState(
							createMeterRelays(goodProcessItems),
							createClientContext(rniEventId),
							Boolean.FALSE);
					break;

				default:
					break;

			}

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ DOT_ERRORS + ex.getMessage());

			// mark all process items as failed
			errors = createMeterErrors(goodProcessItems, EXCEPTION);
		}

		// verify errors and update updatedItemList
		return checkActionResult(goodProcessItems, updatedItemList, errors);

	}

	/**
	 * Execute delete meter action.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDeleteMeterAction(ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		// only valid for Entek LCMs
		// not meters or HAN devices
		List<MeterError> errors;

		try
		{
			if (!getValidator().validateDeleteMeter(processItem, updatedItemList))
			{
				printLogInfo(STR_LOG + MISSING_METER_ID + ". FlexnetId: "
						+ processItem.getDevice().getRadio().getFlexNetIdAsString() + ". Process_endpoint_id: "
						+ processItem.getId());
				return false;
			}

			// This method notifies the RNI of a meter that has been uninstalled. The
			// lifecycle state of the meter in the RNI will be changed from Install to
			// Inventory to indicate that the meter is no longer installed.
			errors = getMeterLifecycleService().meterRemoveNotification(
					createMeters(processItem.getDevice().getDeviceId(), processItem.getDevice().getRadio()
							.getFlexNetId().intValue(), processItem.getDevice().getRadio().getCustomerId()));

			if (ValidationUtil.isNullOrEmpty(errors))
			{
				// This method notifies the RNI of a meter that has been retired. The
				// meter(s) will be administratively removed from the RNI DB. This may not
				// be invoked for a meter that is still installed i.e. the meter must be in
				// Inventory.
				errors = getMeterLifecycleService().meterRetireNotification(
						createMeterIdentities(processItem.getDevice().getDeviceId(), processItem.getDevice().getRadio()
								.getCustomerId()));
			}

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getId() + DOT_ERRORS
					+ ex.getMessage());

			// add general exception to fail gracefully
			errors = new ArrayList<MeterError>();
			errors.add(createMeterError(processItem.getDevice().getDeviceId(), processItem.getDevice().getRadio()
					.getFlexNetId().intValue(), EXCEPTION));
		}

		// verify errors (update process items as failed)
		return checkActionResult(processItem, updatedItemList, errors);

	}

	/**
	 * Execute cancel demand response.
	 * 
	 * @param processItem the process item
	 * @param processId the process id
	 * @return true, if successful
	 */
	public boolean executeCancelDemandResponse(ProcessItem processItem, Integer processId)
	{
		// no device needs to be validated for this action
		try
		{
			List<String> errors = getDrService().cancelLoadControlEvent(
					DMConvertUtil.convertToHanInterface(processItem.getDevice()),
					createLoadControlEvent(null, null, (DemandResponseEventAction)getAction(), processId),
					processId.toString());

			if (!ValidationUtil.isNullOrEmpty(errors))
			{
				printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
						+ API_CALLED
						+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
						+ processItem.getId() + ". Errors: " + errors.toString());

				return false;
			}

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			return false;
		}

		return true;
	}

	/**
	 * Execute demand read ping.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDemandReadPing(Integer rniEventId, ProcessItem processItem, List<ProcessItem> updatedItemList)
	{
		// demand read ping is only allowed for water meters
		if (!getValidator().validateDemandReadPing(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL + processItem.getDevice().getRadio().getFlexNetId());
			return false;
		}

		List<MeterError> errors;

		try
		{
			MeterReadingServiceSOAImpl mrs = (MeterReadingServiceSOAImpl)getMeterReadingService();
			mrs.setInitiatorId(InitiatorId.DEVICEMANAGER);

			errors =
					mrs.onDemandReadPing(
							DMConvertUtil.convertToMeterIdentities(processItem.getDevice())
							, new ArrayList<com.sensus.bcf.meterreading.cim.Version_1.ReadingTypeSelector>()
							, rniEventId.toString(), DEMAND_READ_PING_QUEUE_LISTENER_JMS,
							new PostRefreshOptions(), getDemandReadPingProfile());
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.toString());

			// add general exception to fail gracefully
			errors = new ArrayList<MeterError>();
			errors.add(createMeterError(processItem.getDevice().getDeviceId(), processItem.getDevice().getRadio()
					.getFlexNetId().intValue(), EXCEPTION));
		}

		// verify errors (update process items as failed)
		return checkActionResult(processItem, updatedItemList, errors);

	}

	/**
	 * Execute demand response setup.
	 * 
	 * @param rniEventId the process id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDemandResponseSetup(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// set up demand response is only allowed for
		// HAN devices and Entek LCMs but not meters
		if (!getValidator().validateDRSetup(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId());
			return false;
		}

		DemandResponseSetupAction action = (DemandResponseSetupAction)getAction();

		List<LoadControlParam> lcps = new ArrayList<LoadControlParam>();

		// all parameters are optional, so check to see if the parameter exists before sending
		if (!ValidationUtil.isNull(action.getEnrollmentCode()))
		{
			lcps.add(createLoadControlParam(ParameterIdentifierEnum.utilityEnrollmentGroup,
					String.valueOf(action.getEnrollmentCode())));
		}

		if (!ValidationUtil.isNull(action.getStartMinutes()))
		{
			lcps.add(createLoadControlParam(ParameterIdentifierEnum.startRandomizationTime,
					String.valueOf(action.getStartMinutes() * SIXTY)));
		}

		if (!ValidationUtil.isNull(action.getEndMinutes()))
		{
			lcps.add(createLoadControlParam(ParameterIdentifierEnum.stopRandomizationTime,
					String.valueOf(action.getEndMinutes() * SIXTY)));
		}

		if (!ValidationUtil.isNullOrEmpty(lcps))
		{
			return executeSetLoadControlParam(rniEventId, processItem, INT_ONE, lcps, updatedItemList, true);
		}

		return false;

	}

	/**
	 * Creates the load control param.
	 * 
	 * @param paramIdentifier the param identifier
	 * @param paramValue the param value
	 * @return the load control param
	 */
	private LoadControlParam createLoadControlParam(ParameterIdentifierEnum paramIdentifier, String paramValue)
	{
		LoadControlParam lcp = new LoadControlParam();
		lcp.setParameterIdentifier(paramIdentifier);
		lcp.setParameterValue(new BigInteger(paramValue));
		return lcp;
	}

	/**
	 * Execute demand response setup multiple relay.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDemandResponseSetupMultipleRelay(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// set up demand response is only allowed for
		// HAN devices and Entek LCMs but not meters

		List<LoadControlParam> lcps = new ArrayList<LoadControlParam>();

		for (LCMRelay relay : ((DemandResponseSetupAction)getDMAction()).getLcmRelays())
		{
			// all parameters are optional, so check to see if the parameter exists before sending
			if (!ValidationUtil.isNull(relay.getEnrollmentCode()))
			{
				lcps.add(createLoadControlParam(ParameterIdentifierEnum.utilityEnrollmentGroup,
						String.valueOf(relay.getEnrollmentCode())));
			}

			if (!ValidationUtil.isNull(relay.getStartMinutes()))
			{
				lcps.add(createLoadControlParam(ParameterIdentifierEnum.startRandomizationTime,
						String.valueOf(relay.getStartMinutes() * SIXTY)));
			}

			if (!ValidationUtil.isNull(relay.getEndMinutes()))
			{
				lcps.add(createLoadControlParam(ParameterIdentifierEnum.stopRandomizationTime,
						String.valueOf(relay.getEndMinutes() * SIXTY)));
			}

			if (!ValidationUtil.isNull(relay.getDeviceClassValue()))
			{
				lcps.add(createLoadControlParam(ParameterIdentifierEnum.deviceClass,
						String.valueOf(relay.getDeviceClassValue())));
			}

			if (!ValidationUtil.isNullOrEmpty(lcps)
					&& !executeSetLoadControlParam(rniEventId, processItem, relay.getRelay(), lcps, updatedItemList,
							true))
			{
				return false;
			}

			lcps.clear();

		}

		return true;

	}

	/**
	 * Execute demand response event.
	 * 
	 * @param rniEventId the process id
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDemandResponseEvent(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// demand response is only allowed for meters and Entek LCMs
		// not HAN devices
		if (!getValidator().validateDeviceDemandResponse(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId());
			return false;
		}

		printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName() + CALLING_API
				+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
				+ rniEventId);

		List<String> errors;
		try
		{
			errors =
					getDrService().initLoadControlEvent(
							DMConvertUtil.convertToHanInterface(processItem.getDevice()),
							createLoadControlEvent(processItem, updatedItemList,
									(DemandResponseEventAction)getAction(), rniEventId),
							rniEventId.toString());

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<String>();
			errors.add(EXCEPTION);
		}

		// checks the result for the action
		return checkActionResult(processItem, updatedItemList, errors, false);

	}

	/**
	 * Execute get demand response status.
	 * 
	 * @param processItem the process item
	 * @return true, if successful
	 */
	public boolean executeGetDemandResponseStatus(Integer rniEventId, ProcessItem processItem)
	{
		// get Demand Response event status is allowed for
		// meters, connected HAN devices, and Entek LCMs
		if (!getValidator().validateDRGetStatus(processItem))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId());
			return false;
		}

		try
		{
			return ValidationUtil.isNullOrEmpty(getDrService().getLoadControlEventReport(
					DMConvertUtil.convertToHanInterface(processItem.getDevice()),
					rniEventId, TIME_OUT, rniEventId.toString()));

		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			return false;
		}

	}

	/**
	 * Execute demand reset event.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @return true, if successful
	 */
	public boolean executeDemandResetEvent(ProcessItem processItem,
			List<ProcessItem> updatedItemList)
	{
		// demand reset is only allowed for meters
		// not HAN devices or Entek LCMs
		if (!getValidator().validateElectricMeter(processItem, updatedItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId());
			return false;
		}

		List<MeterError> errors;

		try
		{
			MeterReadingServiceSOAImpl mrs = (MeterReadingServiceSOAImpl)getMeterReadingService();
			mrs.setInitiatorId(InitiatorId.DEVICEMANAGER);

			errors = mrs.sendDemandReset(
					DMConvertUtil.convertToMeterIdentities(processItem.getDevice()),
					processItem.getId().toString(), null, null, null);
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors = new ArrayList<MeterError>();
			errors.add(createMeterError(processItem.getDevice().getDeviceId(), processItem.getDevice().getRadio()
					.getFlexNetId().intValue(), EXCEPTION));

		}

		// verify errors and update updatedItemList
		return checkActionResult(processItem, updatedItemList, errors);

	}

	/**
	 * Calculate demand response duration.
	 * 
	 * @param now the now
	 * @param actionTime the action time
	 * @param durationAction the duration action
	 * @return the integer
	 */
	private Integer calculateDemandResponseDuration(Date now, Date actionTime, Integer durationAction)
	{
		int duration = DMUtil.calculateDuration(actionTime, now);
		if (duration < durationAction)
		{
			return durationAction - duration;
		}

		return INT_ONE;
	}

	/**
	 * Check action time.
	 * 
	 * @param demandResponse the demand response
	 * @return the date
	 */
	private Date checkActionTime(DemandResponseEventAction demandResponse)
	{
		Date now = DMConvertUtil.convertDateToDefaultUTC(new Date());
		if (demandResponse.getActionTime().before(now))
		{
			demandResponse.setDemandResponseDuration(calculateDemandResponseDuration(now,
					demandResponse.getActionTime(), demandResponse.getDemandResponseDuration()));
			return now;
		}
		return demandResponse.getActionTime();
	}

	/**
	 * Creates the load control event.
	 * 
	 * @param processItem the process item
	 * @param updatedItemList the updated item list
	 * @param demandResponse the demand response
	 * @param processId the process id
	 * @return the load control event
	 */
	private LoadControlEvent createLoadControlEvent(ProcessItem processItem, List<ProcessItem> updatedItemList,
			DemandResponseEventAction demandResponse, Integer processId)
	{

		LoadControlEvent lcEvent = new LoadControlEvent();

		lcEvent.setUtilityEnrollmentGroup(demandResponse.getEnrollmentCode());

		// UtilityEnrollmentGroup and eventId must be numeric
		// These parameter conversions have been verified by the validator
		lcEvent.setEventId(processId);

		// Validator confirms that (0 <= incoming value <= 100) for the following conversions
		lcEvent.setAverageLoadAdjustmentPercentage(INT_ZERO);
		if (!ValidationUtil.isNull(demandResponse.getLoadAdjustment()))
		{
			lcEvent.setAverageLoadAdjustmentPercentage(demandResponse.getLoadAdjustment());
		}

		lcEvent.setDutyCycle(INT_ZERO);
		if (!ValidationUtil.isNull(demandResponse.getDutyCycleRate()))
		{
			lcEvent.setDutyCycle(demandResponse.getDutyCycleRate());
		}

		lcEvent.setCoolingTemperatureOffset(INT_ZERO);
		if (!ValidationUtil.isNull(demandResponse.getCooling()))
		{
			lcEvent.setCoolingTemperatureOffset(demandResponse.getCooling());
		}

		lcEvent.setHeatingTemperatureOffset(INT_ZERO);
		if (!ValidationUtil.isNull(demandResponse.getHeating()))
		{
			lcEvent.setHeatingTemperatureOffset(demandResponse.getHeating());
		}

		// Default message start time to 0 == "display now"
		lcEvent.setStartTime(INT_ZERO);
		if (!ValidationUtil.isNull(demandResponse.getActionTime()))
		{
			lcEvent.setStartTime(DMConvertUtil.convertDateToInteger(checkActionTime(demandResponse)));
		}

		lcEvent.setDuration(demandResponse.getDemandResponseDuration()); // Minute

		lcEvent.setCriticalityLevel(demandResponse.getCriticalityLevel());

		if (ValidationUtil.isNullOrEmpty(demandResponse.getDeviceClasses()))
		{
			lcEvent.getDeviceClass().add(ALL_DEVICE_CLASS);
		}
		else
		{
			lcEvent.getDeviceClass().addAll(demandResponse.getDeviceClasses());
		}

		// these both default to "true", handled within the XSD
		lcEvent.setRandomizedStartTime(demandResponse.getRandomizeStart());
		lcEvent.setRandomizedEndTime(demandResponse.getRandomizeEnd());

		return lcEvent;
	}

	/**
	 * Creates the meters.
	 * 
	 * @param deviceId the device id
	 * @param flexnetId the flexnet id
	 * @param customerId the customer id
	 * @return the list
	 */
	private List<Meter> createMeters(String deviceId, Integer flexnetId, String customerId)
	{

		MeterIdentity mi = new MeterIdentity();
		mi.setMeterNo(deviceId);
		mi.setCustomerId(customerId);
		mi.setServiceType(ServiceType.ELECTRIC);

		MeterLifeCycleState lcs = new MeterLifeCycleState();
		lcs.setType(MeterLifeCycleStateType.INVENTORY);
		lcs.setEffectiveDate(DMUtil.createXMLGregorianCalendar(DMConvertUtil.convertDateToDefaultUTC(Calendar
				.getInstance().getTime())));

		Meter m = new Meter();
		m.setMeterIdentity(mi);
		m.setFlexnetId(flexnetId);
		m.setLifeCycleState(lcs);

		List<Meter> meters = new ArrayList<Meter>();
		meters.add(m);

		return meters;
	}

	/**
	 * Creates the meter identities.
	 * 
	 * @param deviceId the device id
	 * @param customerId the customer id
	 * @return the list
	 */
	private List<MeterIdentity> createMeterIdentities(String deviceId, String customerId)
	{
		List<MeterIdentity> meterIdentities = new ArrayList<MeterIdentity>();

		MeterIdentity mi = new MeterIdentity();
		mi.setMeterNo(deviceId);
		mi.setCustomerId(customerId);
		mi.setServiceType(ServiceType.ELECTRIC);
		meterIdentities.add(mi);

		return meterIdentities;

	}

	/**
	 * Execute set tamper detect timeout.
	 * 
	 * @param rniEventId the process id
	 * @param processItem the process item
	 * @param updateItemList the update item list
	 * @return true, if successful
	 */
	public boolean executeSetTamperDetectTimeout(Integer rniEventId, ProcessItem processItem,
			List<ProcessItem> updateItemList)
	{

		SetTamperDetectTimerAction action = (SetTamperDetectTimerAction)getDMAction();

		// check if have relays is on Action
		if (ValidationUtil.isNull(action.getLcmRelays()))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
					+ rniEventId + " LCM Relays not found.");
			return false;
		}

		if (!getValidator().validateTamperDetect(processItem, updateItemList))
		{
			printLogInfo(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ IGNORING_CALL
					+ processItem.getDevice().getRadio().getFlexNetId() + RNI_CORRELATION_ID
					+ rniEventId);
			return false;
		}

		List<LoadControlParam> lcps = new ArrayList<LoadControlParam>();

		for (LCMRelay relay : action.getLcmRelays())
		{
			Integer value = Integer.parseInt(relay.getTamperDetectTimer()) * SIXTY;
			LoadControlParam lcp = new LoadControlParam();
			lcp.setParameterIdentifier(ParameterIdentifierEnum.tamperDetectTimeoutThreshold);
			lcp.setParameterValue(new BigInteger(String.valueOf(value)));

			if (!executeSetLoadControlParam(rniEventId, processItem, relay.getRelay(), lcps, updateItemList, true))
			{
				return false;
			}

			lcps.clear();
		}

		return true;

	}

	/**
	 * Execute set load control param.
	 * 
	 * @param rniEventId the rni event id
	 * @param processItem the process item
	 * @param relayId the relay id
	 * @param lcps the lcps
	 * @param updatedItemList the updated item list
	 * @param updateProcessItemComplete the update process item complete
	 * @return true, if successful
	 */
	private boolean executeSetLoadControlParam(Integer rniEventId, ProcessItem processItem, Integer relayId,
			List<LoadControlParam> lcps, List<ProcessItem> updatedItemList, boolean updateProcessItemComplete)
	{

		List<String> errors = null;

		try
		{
			errors =
					getDrService().setLoadControlParameters(
							DMConvertUtil.convertToSensusHanDevice(processItem.getDevice()), lcps, relayId,
							rniEventId.toString());
		}
		catch (Exception ex)
		{
			printLogError(STR_LOG + getDMAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ EXCEPTION_FOUND
					+ processItem.getDevice().getRadio().getFlexNetId() + DOT_ERRORS
					+ ex.getMessage());

			errors.add(EXCEPTION);
		}

		return checkActionResult(processItem, updatedItemList, errors, updateProcessItemComplete);

	}

	/**
	 * Creates the meter relays.
	 * 
	 * @param processItems the process items
	 * @return the list
	 */
	private List<MeterRelays> createMeterRelays(List<ProcessItem> processItems)
	{
		List<MeterRelays> meterRelaysList = new ArrayList<MeterRelays>();

		for (ProcessItem processItem : processItems)
		{
			meterRelaysList.add(DMConvertUtil.convertToMeterRelays(processItem.getDevice()));
		}

		return meterRelaysList;
	}

	/**
	 * Creates the client context.
	 * 
	 * @param transactionId the transaction id
	 * @return the string
	 */
	private String createClientContext(Integer transactionId)
	{
		// Constructs client context xml from schema defined in ws-common
		ObjectFactory objectFactory = new ObjectFactory();
		ClientContext clientContext = objectFactory.createClientContext();
		clientContext.setTransactionId(transactionId.toString());
		clientContext.setDestinationURI(null);
		StringWriter stringWriter = new StringWriter();
		getClientContextMarshaller().marshal(clientContext, new StreamResult(stringWriter));

		return stringWriter.toString();
	}

	/**
	 * Creates the meter error.
	 * 
	 * @param deviceId the device id
	 * @param flexnetId the flexnet id
	 * @param error the error
	 * @return the meter error
	 */
	private MeterError createMeterError(String deviceId, Integer flexnetId, String error)
	{
		ErrorDetail detail = new ErrorDetail();
		detail.setDescription(error);

		MeterIdentity identity = new MeterIdentity();
		identity.setMeterNo(deviceId);

		MeterError meterError = new MeterError();
		meterError.setFlexnetId(flexnetId);
		meterError.setMeterIdentity(identity);
		meterError.setErrorDetail(detail);

		return meterError;
	}

	/**
	 * Creates the meter errors.
	 * 
	 * @param processItems the process items
	 * @param error the error
	 * @return the list
	 */
	private List<MeterError> createMeterErrors(List<ProcessItem> processItems, String error)
	{
		List<MeterError> errors = new ArrayList<MeterError>();
		for (ProcessItem processItem : processItems)
		{
			errors.add(createMeterError(processItem.getDevice().getDeviceId(), processItem.getDevice().getRadio()
					.getFlexNetId().intValue(), error));
		}
		return errors;
	}

}
