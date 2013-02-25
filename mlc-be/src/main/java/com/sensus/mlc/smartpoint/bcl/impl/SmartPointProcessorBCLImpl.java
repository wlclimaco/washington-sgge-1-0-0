package com.sensus.mlc.smartpoint.bcl.impl;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCHelpGeoCoding;
import com.sensus.mlc.base.util.LCPropertiesExtractorUtil;
import com.sensus.mlc.ecomode.bcl.IEcoModeBCL;
import com.sensus.mlc.ecomode.model.EcoModeBaseline;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.dac.ISmartPointDAC;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightDetailDataTypeEnum;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.LightTypeEnum;
import com.sensus.mlc.smartpoint.model.OperationalData;
import com.sensus.mlc.smartpoint.model.OperationalDataTypeEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.tag.bcl.ITagBCL;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Class SmartPointProcessorBCLImpl.
 */
public class SmartPointProcessorBCLImpl extends SmartPointBCLBase implements ISmartPointProcessorBCL
{

	/** The Constant GATEWAY_FINISHED_WITH_ERROR_SMARTPOINT_BCL_PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION. */
	private static final String GATEWAY_FINISHED_WITH_ERROR_SMARTPOINT_BCL_PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION =
			"**** GATEWAY: Finished with error. SmartpointBCL.processApplySmartpointPropertiesNotification";

	/** The Constant GATEWAY_FINISHED_SMARTPOINT_BCL_LIGHT_IS_DEACTIVATED_FALSE. */
	private static final String GATEWAY_FINISHED_SMARTPOINT_BCL_LIGHT_IS_DEACTIVATED_FALSE =
			"**** GATEWAY: Finished SmartpointBCL.lightIsDeactivated = false";

	/** The Constant NO_DIMMING_CONFIGURATION. */
	private static final String NO_DIMMING_CONFIGURATION = "sensus.mlc.smartpointbclimpl.none.configuration.dimming";

	/** The Constant LIGHT_STATUS_ALREADY_ACTIVE. */
	private static final String LIGHT_STATUS_ALREADY_ACTIVE = "sensus.mlc.mlc_action.light_status.already.active";

	/** The Constant ADD_LIGHT_FAILED. */
	private static final String ADD_LIGHT_FAILED = "sensus.mlc.smartpointbclimpl.add.light.failed";

	/** The Constant REVERSE_LOOKUP_FAILED. */
	private static final String REVERSE_LOOKUP_FAILED = "sensus.mlc.smartpointbclimpl.reverse.lookup.failed";

	/** The Constant REQUEST_LIGHT_DETAILS_FAILED. */
	private static final String REQUEST_LIGHT_DETAILS_FAILED =
			"sensus.mlc.smartpointbclimpl.request.light.details.failed";

	/** The Constant ADD_PROPERTY_VALUE_FAILED. */
	private static final String ADD_PROPERTY_VALUE_FAILED = "sensus.mlc.smartpointbclipml.add.property.value.failed";

	/** The Constant ADD_LIGHT_TO_TAG_FAILED. */
	private static final String ADD_LIGHT_TO_TAG_FAILED = "sensus.mlc.smartpointbclimpl.add.light.to.tag.failed";

	/** The Constant ADD_TAG_FAILED. */
	private static final String ADD_TAG_FAILED = "sensus.mlc.smartpointbclimpl.add.tag.failed";

	/** The Constant UPDATE_LOCATION_FAILED. */
	private static final String UPDATE_LOCATION_FAILED = "sensus.mlc.smartpointbclimpl.update.location.failed";

	/** The Constant DELETE_OLD_LOCATION_FAILED. */
	private static final String DELETE_OLD_LOCATION_FAILED = "sensus.mlc.smartpointbclimpl.delete.old.location.failed";

	/** The Constant LIGHT_ALREADY_IN_TAG. */
	private static final String LIGHT_ALREADY_IN_TAG = "sensus.mlc.tagvalidator.light.already.exist";

	/** The Constant ERROR_UPDATING_LIGHT_STATE. */
	private static final String ERROR_UPDATING_LIGHT_STATE = "Error updating light state.";

	/** The Constant ERROR_INSERTING_COMPLETE_STATUS_MESSAGE. */
	private static final String ERROR_INSERTING_COMPLETE_STATUS_MESSAGE = "Error inserting complete status message";

	/** The Constant ERROR_UPSERTING_LIGHT_PROPERTY. */
	private static final String ERROR_UPSERTING_LIGHT_PROPERTY = "Error upserting light property.";

	/** The Constant ERROR_UPSERTING_ECOMODE. */
	private static final String ERROR_UPSERTING_ECOMODE = "sensus.mlc.smartpointbclimpl.update.ecomode.failed";

	/** The Constant ALL. */
	private static final String ALL = "All";

	/** The active. */
	private static String ACTIVE = "Active";

	/** The deactivated. */
	private static String DEACTIVATED = "Deactivated";

	/** The maintenance. */
	private static String MAINTENANCE = "Maintenance";

	/** Static fields. */
	private static String STREET = "Street";

	/** The CITY. */
	private static String CITY = "City";

	/** The COUNTY. */
	private static String COUNTY = "County";

	/** The STATE. */
	private static String STATE = "State";

	/** The ZIP. */
	private static String ZIP = "Zip";

	/** The Constant ONE_HUNDRED. */
	private static final int ONE_HUNDRED = 100;

	/** The Constant UPDATE_PROPERTY_LIGHT_FAILED. */
	private static final String UPDATE_PROPERTY_LIGHT_POLE_ID_FAILED = "sensus.mlc.smartpointbclimpl.update.light.pole.id";

	/** The Constant UPDATE_LAT_LONG_FAILED. */
	private static final String UPDATE_LAT_LONG_FAILED = "sensus.mlc.smartpointbclimpl.update.light.lat.long.failed";

	/** The Constant UPDATE_LIGHT_LAST_OPERATIONAL_DATA_FAILED. */
	private static final String UPDATE_LIGHT_LAST_OPERATIONAL_DATA_FAILED = "Update light last operational data failed.";

	/** The incompleteData. */
	private String incompleteData; // injected by Spring through setter

	/** The time in minutes to ask for a readLightStatus after the last binding message. */
	private Integer timeForReadLightStatus; // injected by Spring through setter

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The lc help geo coding. */
	private LCHelpGeoCoding lcHelpGeoCoding; // injected by Spring through setter

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter

	/** The eco mode bcl. */
	private IEcoModeBCL ecoModeBCL; // injected by Spring through setter

	/** The smart point dac. */
	private ISmartPointDAC smartPointDAC;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.impl.SmartPointBCLBase#getSmartPointDAC()
	 */
	@Override
	public ISmartPointDAC getSmartPointDAC()
	{
		return smartPointDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.impl.SmartPointBCLBase#setSmartPointDAC(com.sensus.mlc.smartpoint.dac.ISmartPointDAC
	 * )
	 */
	@Override
	public void setSmartPointDAC(ISmartPointDAC smartPointDAC)
	{
		this.smartPointDAC = smartPointDAC;
	}

	/**
	 * Gets the incomplete data.
	 *
	 * @return the incomplete data
	 */
	public String getIncompleteData()
	{
		return incompleteData;
	}

	/**
	 * Sets the incomplete data.
	 *
	 * @param data the new incomplete data
	 */
	public void setIncompleteData(String data)
	{
		incompleteData = data;
	}

	/**
	 * Gets the lc help geo coding.
	 *
	 * @return the lc help geo coding
	 */
	private LCHelpGeoCoding getLcHelpGeoCoding()
	{
		return lcHelpGeoCoding;
	}

	/**
	 * Sets the lc help geo coding.
	 *
	 * @param lcHelpGeoCoding the new lc help geo coding
	 */
	public void setLcHelpGeoCoding(LCHelpGeoCoding lcHelpGeoCoding)
	{
		this.lcHelpGeoCoding = lcHelpGeoCoding;
	}

	/**
	 * Gets the time for read light status.
	 *
	 * @return the time for read light status
	 */
	private Integer getTimeForReadLightStatus()
	{
		return timeForReadLightStatus;
	}

	/**
	 * Sets the time for read light status.
	 *
	 * @param timeForReadLightStatus the new time for read light status
	 */
	public void setTimeForReadLightStatus(Integer timeForReadLightStatus)
	{
		this.timeForReadLightStatus = timeForReadLightStatus;
	}

	/**
	 * Sets the tag bcl.
	 *
	 * @param tagBCL the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * Gets the tag bcl.
	 *
	 * @return the tag bcl
	 */
	private ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Gets the smart point updater bcl.
	 *
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		return smartPointUpdaterBCL;
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
	 * Gets the eco mode bcl.
	 *
	 * @return the eco mode bcl
	 */
	public IEcoModeBCL getEcoModeBCL()
	{
		return ecoModeBCL;
	}

	/**
	 * Sets the eco mode bcl.
	 *
	 * @param ecoModeBCL the new eco mode bcl
	 */
	public void setEcoModeBCL(IEcoModeBCL ecoModeBCL)
	{
		this.ecoModeBCL = ecoModeBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateUpdateLightIntensity(com.sensus.mlc.smartpoint.
	 * model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateUpdateLightIntensity(LightRequest lightRequest)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.initiateUpdateLightIntensity");

		LCActionParameter actionParameter = new LCActionParameter();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();

		actionParameter = new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, null);
		if (!ValidationUtil.isNull(lightRequest.getPercentage()))
		{
			String percentage = String.valueOf(lightRequest.getPercentage());
			actionParameter = new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, percentage);
		}
		actionParameters.add(actionParameter);

		String lightBlink = String.valueOf(lightRequest.getLightBlinkEnum().getValue());
		actionParameter = new LCActionParameter(PropertyEnum.LIGHT_BLINK, lightBlink);
		actionParameters.add(actionParameter);

		String override = String.valueOf(lightRequest.getOverrideEnum().getValue());
		actionParameter = new LCActionParameter(PropertyEnum.OVERRIDE, override);
		actionParameters.add(actionParameter);

		if (!ValidationUtil.isNull(lightRequest.getOverridePerDate()))
		{
			String overridePerDate = String.valueOf(lightRequest.getOverridePerDate());
			actionParameter = new LCActionParameter(PropertyEnum.OVERRIDE_PER_DATE, overridePerDate);
			actionParameters.add(actionParameter);
		}

		LCAction action = new LCAction(LCActionTypeEnum.DIM);
		action.setActionParameters(actionParameters);

		if (lightRequest.getLightBlinkEnum().equals(LightBlinkEnum.SLOW)
				|| lightRequest.getLightBlinkEnum().equals(LightBlinkEnum.FAST))
		{
			action.setActionType(LCActionTypeEnum.SET_BLINK_BY_LIGHT);

		}
		else if (lightRequest.getIsClearOverride())
		{
			action.setActionType(LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT);
		}
		else if (lightRequest.getPercentage().equals(ONE_HUNDRED))
		{
			action.setActionType(LCActionTypeEnum.TURN_ON);
		}
		else if (lightRequest.getPercentage().equals(NumberUtils.INTEGER_ZERO))
		{
			action.setActionType(LCActionTypeEnum.TURN_OFF);
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.initiateUpdateLightIntensity");

		// create a Process
		ProcessRequest processRequest = createProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateUpsertLightProperty(com.sensus.mlc.smartpoint.model
	 * .request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateUpsertLightProperty(LightRequest lightRequest)
	{
		InternalResponse response = new InternalResponse();

		// create a list of lights based on the user selection
		setSelectionPaginationId(lightRequest);

		LCAction action = new LCAction();

		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();

		if(!ValidationUtil.isNullOrEmpty(lightRequest.getFirstLight().getPoleId()))
		{
			action = new LCAction(LCActionTypeEnum.UPDATE_LIGHT_POLE_ID);
			updateLight(response, lightRequest);

			if(response.isInError())
			{
				processResponse.addMessages(response.getMessageInfoList());
				processResponse.setStatus(response.getStatus());
				return processResponse;
			}

		}

		if(!ValidationUtil.isNullOrZero(lightRequest.getFirstLight().getLightLocation().getLatitude())
				|| !ValidationUtil.isNullOrZero(lightRequest.getFirstLight().getLightLocation().getLongitude()))
		{
			action = new LCAction(LCActionTypeEnum.UPDATE_LIGHT_LAT_LONG);

			upsertLightLocation(response, lightRequest);

			if(response.isInError())
			{
				processResponse.addMessages(response.getMessageInfoList());
				processResponse.setStatus(response.getStatus());
				return processResponse;
			}

		}

		ProcessRequest processRequest = createProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateSetupDimmingConfiguration(com.sensus.mlc.smartpoint
	 * .model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateSetupDimmingConfiguration(LightRequest lightRequest)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.initiateSetupDimmingConfiguration");

		InternalResultsResponse<Light> responseLight = getSmartPointDAC().fetchLightByRniId(lightRequest);
		lightRequest.getFirstLight().setId(responseLight.getFirstResult().getId());

		setSelectionPaginationId(lightRequest);

		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(lightRequest);
		List<Light> lightList = getSmartPointDAC().fetchAllLights(inquiryLightRequest).getResultsList();

		if (ValidationUtil.isNullOrEmpty(lightList))
		{
			return new InternalResultsResponse<Process>();
		}

		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		for (Light light : lightList)
		{
			List<SensusPartNumberConfiguration> lightConfigurations = new ArrayList<SensusPartNumberConfiguration>();
			setDimmingConfigurationByLight(light, lightConfigurations, response, lightRequest.getUserContext());

			if (ValidationUtil.isNullOrEmpty(lightConfigurations))
			{
				return response;
			}

			List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
			String dimOnDelay = String.valueOf(lightConfigurations.get(0).getDimOnDelay());
			LCActionParameter actionParameter = new LCActionParameter(PropertyEnum.DIM_ON_DELAY, dimOnDelay);
			actionParameters.add(actionParameter);
			setActionParametersToDimmingConfiguration(lightConfigurations, actionParameters);

			LCAction action = new LCAction(LCActionTypeEnum.SETUP_DIMMING_CONFIGURATION);
			action.setActionParameters(actionParameters);

			ProcessRequest processRequest = createProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
			processRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));

			InternalResultsResponse<Process> processResponse =
					getProcessBCL().submitProcess(processRequest, action);
			response.addResults(processResponse.getResultsList());
			response.addMessages(processResponse.getMessageInfoList());

			if (processResponse.isInError())
			{
				response.setStatus(processResponse.getStatus());
			}
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.initiateSetupDimmingConfiguration.; Status="
				+ response.getStatus());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateUpdateLightStatus(com.sensus.mlc.smartpoint.model
	 * .request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateUpdateLightStatus(LightRequest lightRequest)
	{
		LCAction action = new LCAction(LCActionTypeEnum.UPDATE_LIGHT_STATUS);
		action.setActionParameters(setLCActionParameter(lightRequest));

		if (lightRequest.getLightStatusEnum().equals(LightStatusEnum.DEACTIVATED))
		{
			InternalResultsResponse<Light> responseLightStatus =
					getSmartPointUpdaterBCL().updateLightStatus(lightRequest);
			if (responseLightStatus.isInError())
			{
				InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
				response.setStatus(responseLightStatus.getStatus());
				response.addMessages(responseLightStatus.getMessageInfoList());
				return response;
			}
		}

		ProcessRequest processRequest = createProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateDeleteAlert(com.sensus.mlc.smartpoint.model.request
	 * .LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateDeleteAlert(LightRequest lightRequest)
	{
		// If is detail page request, add the unique light on light list
		setSelectionPaginationId(lightRequest);

		List<Light> lightsActive = getLightsActive(lightRequest);
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		if (!ValidationUtil.isNullOrEmpty(lightsActive))
		{
			lightRequest.setUnselectionPaginationIds(LCPropertiesExtractorUtil.extractLightId(lightsActive));
			response.addMessage(LIGHT_STATUS_ALREADY_ACTIVE, MessageSeverity.Info, MessageLevel.None,
					new Object[] {lightsActive.size()});
		}

		LCAction action = new LCAction(LCActionTypeEnum.CLEAR_ALL_ALARMS);
		setActionParametersByStatusMessage(lightRequest, action);

		ProcessRequest processRequest = createProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
		InternalResultsResponse<Process> processResponse = getProcessBCL().submitProcess(processRequest, action);
		response.addMessages(processResponse.getMessageInfoList());
		response.addResults(processResponse.getResultsList());

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processApplySmartpointPropertiesNotification(com.sensus
	 * .mlc.smartpoint.model.request.ProcessLightsRequest)
	 */
	@Override
	public InternalResponse processApplySmartpointPropertiesNotification(ProcessLightsRequest request)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processApplySmartpointPropertiesNotification");

		InternalResponse response = new InternalResponse();
		Light light = request.getFirstLight();
		Double latitude = light.getLightLocation().getLatitude();
		Double longitude = light.getLightLocation().getLongitude();

		persistLogInfo("**** Lat/Long=" + latitude + "/" + longitude);

		// Verify if Lat/Long need to be updated
		UserContext userContext = request.getUserContext();
		if (!ValidationUtil.isNull(latitude) && !ValidationUtil.isNull(longitude))
		{
			response = guaranteeLightExistence(light, userContext);
		}

		if (response.isInError())
		{
			persistLogInfo(GATEWAY_FINISHED_WITH_ERROR_SMARTPOINT_BCL_PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION);
			return response;
		}

		// Copy the last status message
		StatusMessage statusMessage = light.getCurrentStatusMessage();
		statusMessage.setId(null);
		statusMessage.setDate(new Date());
		statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.UPD_LIGHT_PROPERTY);

		AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(userContext);
		notificationRequest.setLight(light);
		response = insertStatusMessageByLight(notificationRequest, true, true, Arrays.asList(statusMessage));

		if (response.isInError())
		{
			persistLogInfo(GATEWAY_FINISHED_WITH_ERROR_SMARTPOINT_BCL_PROCESS_APPLY_SMARTPOINT_PROPERTIES_NOTIFICATION);
			return response;
		}

		// FIXME - properties
		/*
		 * response =
		 * getSmartPointDAC().upsertLightProperty(userContext.getUserId(), light.getRniId(),
		 * light.getParameters());
		 */

		// Update Eco-Mode data
		upsertEcoModeFromLight(userContext, light, response);

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.processApplySmartpointPropertiesNotification");

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processChannelSetupAuditNotification(com.sensus.mlc.smartpoint
	 * .model.request.ChannelSetupAuditRequest)
	 */
	@Override
	public InternalResponse processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		persistLogInfo("**** GATEWAY: Beginnin SmartpointBCL.processChannelSetupAuditNotification");

		InternalResponse response = new InternalResponse();

		// Add a StatusMessage with categ = CHANNEL_SETUP_AUDIT. Copy Alarms/Warnings from cur.message for the light
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.CHANNEL_SETUP_AUDIT);
		statusMessage.setLightStatusEnum(request.getLightStatusEnum());

		// If light is Active and was in Maintenance before, request details.
		if ((LightStatusEnum.ACTIVE == request.getLightStatusEnum())
				&& lightIsInMaintenance(request.getLight().getId(), request.getAllowedGroupIdList()))
		{
			requestLightDetails(request.getLight(), request.getUserContext(), ALL, response);
		}

		if (response.isInError())
		{
			return response;
		}

		// Adds the StatusMessage
		AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(request.getUserContext());
		notificationRequest.setLight(request.getLight());
		response = insertStatusMessageByLight(notificationRequest, false, true, Arrays.asList(statusMessage));

		if (response.isInError())
		{
			persistLogDebug(ERROR_INSERTING_COMPLETE_STATUS_MESSAGE);
			response.setStatus(Status.PersistenceError);
		}

		// If moving light to IDLE, need to clear all Tags/Groups/Schedules for that light and update
		// LightState to DEACTIVATED
		if (LightStatusEnum.DEACTIVATED != request.getLightStatusEnum())
		{
			return response;
		}

		getSmartPointDAC().deleteLightFromAllTagScheduleGroup(request.getLight().getRniId());

		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(request.getUserContext());
		request.getLight().setLightStateEnum(LightStateEnum.DEACTIVATED);
		lightRequest.addLight(request.getLight());

		// update LightState to DEACTIVATED
		InternalResponse result = getSmartPointDAC().updateLightState(lightRequest);

		if (result.isInError())
		{
			persistLogDebug(ERROR_UPDATING_LIGHT_STATE);
			response.setStatus(result.getStatus());
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.processChannelSetupAuditNotification");
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processAlarmWarningNotification(com.sensus.mlc.smartpoint
	 * .model.request.AlarmNotificationRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processAlarmWarningNotification");

		InternalResultsResponse<StatusMessage> statusResponse =
				processAlarmWarning(request, StatusMessageCategoryEnum.ALARM);

		if (statusResponse.isInError())
		{
			persistLogDebug(ERROR_UPDATING_LIGHT_STATE);
			return statusResponse;
		}

		// Update light informations
		UserContext userContext = request.getUserContext();
		Light light = request.getLight();
		StatusMessage statusMessage = statusResponse.getFirstResult();
		InternalResponse response = processAppCode90(statusMessage.getId(),
				userContext,
				light,
				request.getOperationalData());

		if (response.isInError())
		{
			persistLogDebug(ERROR_UPDATING_LIGHT_STATE);
			statusResponse.setStatus(response.getStatus());
			return statusResponse;
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.processAlarmWarningNotification; response="
				+ statusResponse.getStatus());

		return statusResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processLightStatusNotification(com.sensus.mlc.smartpoint
	 * .model.request.LightStatusNotificationRequest)
	 */
	@Override
	public InternalResponse processLightStatusNotification(LightStatusNotificationRequest request)
	{
		final Light light = request.getLight();
		final Integer reqLightID = light.getId();

		// LightStatus message only carries information about lightState, so determine overall Status based on that.
		final LightStateEnum lightStateEnum = light.getLightStateEnum();
		final List<Integer> allowedGroupIdList = request.getAllowedGroupIdList();
		UserContext userContext = request.getUserContext();
		final UserContext reqUserContext = userContext;

		persistLogInfo("**** GATEWAY: Begin SmartpointBCL.processLightStatusNotification; lightState="
				+ lightStateEnum);

		InternalResponse response = new InternalResponse();

		// Add a StatusMessage with cat.=UNSOLICITED_STATUS.Copy Alarms/Warnings from current message for light

		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setStatusExceptions(light.getCurrentStatusMessage().getStatusExceptions());
		statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.UNSOLICITED_STATUS);

		// This will override the status retrieved above.
		if (lightStateEnum.equals(LightStateEnum.MAINTENANCE))
		{
			statusMessage.setLightStatusEnum(LightStatusEnum.MAINTENANCE);
		}
		else if (lightStateEnum.equals(LightStateEnum.UNKNOWN))
		{
			statusMessage.setLightStatusEnum(LightStatusEnum.UNKNOWN);
		}
		else
		{
			if (ValidationUtil.isNullOrEmpty(statusMessage.getStatusExceptions()))
			{
				statusMessage.setLightStatusEnum(LightStatusEnum.ACTIVE);
			}

			if (lightIsInMaintenance(reqLightID, allowedGroupIdList))
			{
				requestLightDetails(light, reqUserContext, ALL, response);

				if (response.isInError())
				{
					return response;
				}
			}
		}

		// Adds the StatusMessage
		AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(userContext);
		notificationRequest.setLight(light);
		InternalResultsResponse<StatusMessage> statusResponse =
				insertStatusMessageByLight(notificationRequest, false, false, Arrays.asList(statusMessage));

		if (statusResponse.isInError())
		{
			persistLogDebug(ERROR_INSERTING_COMPLETE_STATUS_MESSAGE);
			response.setStatus(statusResponse.getStatus());
			return response;
		}

		// Update properties, light status (ON/OFF) and operational data
		response = processAppCode90(
				statusResponse.getFirstResult().getId(),
				reqUserContext,
				light,
				request.getOperationalData());

		persistLogInfo("**** GATEWAY: End SmartpointBCL.processLightStatusNotification; Status="
				+ response.getStatus());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processLightBindingNotification(com.sensus.mlc.smartpoint
	 * .model.request.LightStatusNotificationRequest)
	 */
	@Override
	public InternalResponse processLightBindingNotification(LightStatusNotificationRequest request)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processLightBindingNotification");

		UserContext userContext = request.getUserContext();
		String userName = userContext.getUserId();
		Light reqLight = request.getLight();

		// Fetch light or Add new one
		InternalResultsResponse<Light> response = guaranteeLightExistence(reqLight, userContext);

		if (response.isInError())
		{
			return response;
		}

		// If a new light was added, and there are no previous messages, then Add the first one
		AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(request.getUserContext());
		notificationRequest.setLight(reqLight);
		StatusMessage statusMessage = createStatusMessageForBinding();
		Integer idStatusMessage = 0;

		if (response.getFirstResult().getProtect())
		{
			InternalResultsResponse<Light> internalResponse =
					fetchLightById(new LightRequest(userContext, response.getFirstResult()));

			if (internalResponse.isInError()
					|| (internalResponse.hasResults() && !ValidationUtil.isNull(internalResponse.getFirstResult()
							.getCurrentStatusMessage())))
			{
				return internalResponse;
			}

			InternalResultsResponse<StatusMessage> responseStatusMessage =
					insertStatusMessageByLight(notificationRequest, false, true, Arrays.asList(statusMessage));

			if (responseStatusMessage.isInError())
			{
				response.setStatus(responseStatusMessage.getStatus());
				response.addMessages(responseStatusMessage.getMessageInfoList());
				return response;
			}
			idStatusMessage = responseStatusMessage.getFirstResult().getId();
		}
		else
		{
			InternalResultsResponse<StatusMessage> responseStatusMessage =
					insertStatusMessageByLight(notificationRequest, false, false, Arrays.asList(statusMessage));

			if (responseStatusMessage.isInError())
			{
				response.setStatus(responseStatusMessage.getStatus());
				response.addMessages(responseStatusMessage.getMessageInfoList());
				return response;
			}
			idStatusMessage = responseStatusMessage.getFirstResult().getId();
		}

		if (ValidationUtil.isNullOrZero(idStatusMessage))
		{
			return response;
		}

		if (lightIsInMaintenance(reqLight.getId(), request.getAllowedGroupIdList()))
		{
			requestLightDetails(reqLight, request.getUserContext(), ALL, response);
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
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateGetLightStatus(com.sensus.mlc.smartpoint.model.
	 * request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateGetLightStatus(LightRequest lightRequest)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.initiateGetLightStatus");

		// Complete Light Information - Process uses SmartPoint Id.
		// check if it is just one light from the detail page, or if it comes from a user selection
		setSelectionPaginationId(lightRequest);

		// Populate LCActionParameters according to the level of detail requested in lightRequest (STATUS and/or CONFIG)
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.LIGHT_DETAIL_TYPE, String
				.valueOf(LightDetailDataTypeEnum.STATUS.getValue())));
		actionParameters.add(new LCActionParameter(PropertyEnum.LIGHT_DETAIL_TYPE, String
				.valueOf(LightDetailDataTypeEnum.CONFIGURATION.getValue())));

		// Recreate Light list in case of Quick Link select
		LCAction action = new LCAction(LCActionTypeEnum.GET_LIGHT_STATUS);
		action.setActionParameters(actionParameters);

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.initiateGetLightStatus");

		ProcessRequest processRequest = createProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processForcedLightStatusNotification(com.sensus.mlc.smartpoint
	 * .model.request.ForcedLightStatusNotificationRequest)
	 */
	@Override
	public InternalResponse processForcedLightStatusNotification(ForcedLightStatusNotificationRequest request)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processForcedLightStatusNotification");

		// Alarm/Warnings - This will also add the Status Message
		InternalResultsResponse<StatusMessage> statusResponse =
				processAlarmWarning(request, StatusMessageCategoryEnum.FORCED_STATUS);
		if (statusResponse.isInError())
		{
			return statusResponse;
		}

		Light light = request.getLight();
		if (LightStatusEnum.DEACTIVATED.equals(light.getCurrentLightStatusEnum()))
		{
			light.setCurrentLightStatusEnumValue(LightStatusEnum.DEACTIVATED.getValue());
		}

		// AppCode 90 + 85 - Affects properties, light Status (ON/OFF) and operational data
		UserContext userContext = request.getUserContext();
		InternalResponse response = processAppCode90(statusResponse.getFirstResult().getId(),
				userContext,
				light,
				request.getOperationalData());

		if (response.isInError())
		{
			statusResponse.setStatus(response.getStatus());
			statusResponse.addMessages(response.getMessageInfoList());
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.processForcedLightStatusNotification; result="
				+ statusResponse.getStatus());

		return statusResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processLightSetupNotification(com.sensus.mlc.smartpoint
	 * .model.request.LightSetupNotificationRequest)
	 */
	@Override
	public InternalResponse processLightSetupNotification(LightSetupNotificationRequest request)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processLightSetupNotification");
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		if (!ValidationUtil.isNull(request.getLight().getId()))
		{
			persistLogInfo("**** Light is already exist, ignoring Light Setup Notification");
			return response;
		}

		response = guaranteeLightExistence(request.getLight(), request.getUserContext());
		if (response.isInError())
		{
			// If no light was inserted, this is still ok. No message will be recorded.
			if (Status.NoRowsInsertedError.equals(response.getStatus()))
			{
				response.setStatus(Status.OperationSuccess);
				response.getMessageInfoList().clear();
			}

			persistLogInfo("**** No light was inserted.");
			return response;
		}

		// Only if a light was retrieved, proceed. This will be the case for setup messages after the first one
		if (ValidationUtil.isNull(response.getFirstResult()))
		{
			response.setStatus(Status.NoRowsFoundError);
			return response;
		}

		Light light = response.getFirstResult();
		if (ValidationUtil.isNull(light.getCurrentStatusMessage()))
		{
			return response;
		}

		// This message does NOT change status
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setLightStatusEnum(light.getCurrentStatusMessage().getLightStatusEnum());
		statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.SETUP);

		AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(request.getUserContext());
		notificationRequest.setLight(response.getFirstResult());
		InternalResultsResponse<StatusMessage> statusResponse =
				insertStatusMessageByLight(notificationRequest, false, false, Arrays.asList(statusMessage));

		if (statusResponse.isInError())
		{
			response.setStatus(statusResponse.getStatus());
			response.addMessages(statusResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processSetLightIntensityNotification(com.sensus.mlc.smartpoint
	 * .model.request.ProcessLightsRequest)
	 */
	@Override
	public InternalResponse processSetLightIntensityNotification(ProcessLightsRequest request)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processSetLightIntensityNotification");

		InternalResponse response = new InternalResponse();
		for (Light light : request.getLights())
		{
			StatusMessage statusMessage = new StatusMessage();
			statusMessage.setLightStatusEnum(LightStatusEnum.ACTIVE);
			statusMessage.setDate(new Date());
			statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.SET_LIGHT_INTENSITY);

			if (light.getLightBlinkEnum().equals(LightBlinkEnum.SLOW)
					|| light.getLightBlinkEnum().equals(LightBlinkEnum.FAST))
			{
				statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.SET_LIGHT_BLINK);
			}

			AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(request.getUserContext());
			notificationRequest.setLight(light);
			response = insertStatusMessageByLight(notificationRequest, false, false, Arrays.asList(statusMessage));

			if (response.isInError())
			{
				continue;
			}

			LightRequest lightRequest = new LightRequest();
			lightRequest.setUserContext(request.getUserContext());
			lightRequest.addLight(light);

			response = getSmartPointDAC().updateLightState(lightRequest);

			// Set the light intensity to ON or OFF
			if (response.isInError())
			{
				persistLogDebug(ERROR_UPDATING_LIGHT_STATE);
				response.setStatus(Status.PersistenceError);
				return response;
			}

			// FIXME - properties
			/*
			 * esponse = getSmartPointDAC().upsertLightProperty(request.getUserContext().getUserId(),
			 * light.getRniId(),
			 * light.getParameters());
			 */

			if (response.isInError())
			{
				persistLogDebug(ERROR_UPSERTING_LIGHT_PROPERTY);
				response.setStatus(Status.PersistenceError);
				return response;
			}
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.processSetLightIntensityNotification; Status="
				+ response.getStatus());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processClearAlarmNotification(com.sensus.mlc.smartpoint
	 * .model.request.AlarmNotificationRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> processClearAlarmNotification(AlarmNotificationRequest request)
	{
		List<StatusMessage> statusMessageList = processLightAlarm(request, StatusMessageCategoryEnum.CLEAR_ALARM);

		if (ValidationUtil.isNullOrEmpty(statusMessageList) || ValidationUtil.isNull(request.getLight())
				|| ValidationUtil.isNull(request.getLight().getId()))
		{
			InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
			response.setStatus(Status.PersistenceError);
			return response;
		}

		AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(request.getUserContext());
		notificationRequest.setLight(request.getLight());

		// if statusMessageList is Active, this is a CLEAR ALL ALARMS
		if (!ValidationUtil.isNullOrEmpty(statusMessageList)
				&& statusMessageList.get(0).getLightStatusEnum().equals(LightStatusEnum.ACTIVE))
		{
			// just create a active status for this light
			StatusMessage statusMessage = createStatusMessageForBinding();
			return insertStatusMessageByLight(notificationRequest, true, true, Arrays.asList(statusMessage));
		}

		return insertStatusMessageByLight(notificationRequest, true, true, statusMessageList);
	}

	/**
	 * Sets the dimming configuration by light.
	 *
	 * @param light the light
	 * @param lightConfigurations the light configurations
	 * @param response the response
	 * @param userContext the user context
	 * @return the dimming configuration by light
	 */
	private void setDimmingConfigurationByLight(Light light, List<SensusPartNumberConfiguration> lightConfigurations,
			InternalResultsResponse<Process> response, UserContext userContext)
	{
		// fetch the Dimming Configuration by Light
		LightingConfigurationRequest configurationRequest = new LightingConfigurationRequest(userContext);
		configurationRequest.setLight(light);

		InternalResultsResponse<SensusPartNumberConfiguration> configurationResponse =
				fetchLigthingConfigurationsByPartNumber(configurationRequest);

		if (!configurationResponse.hasResults())
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(NO_DIMMING_CONFIGURATION, MessageSeverity.Error,
					MessageLevel.FieldValidation);

			persistLogInfo("**** GATEWAY: applyDimmingConfiguration Message = There is no Dimming Configuration for this Model Number.");
		}

		lightConfigurations.addAll(configurationResponse.getResultsList());
	}

	/**
	 * Sets the action parameters to dimming configuration.
	 *
	 * @param lightConfigurations the light configurations
	 * @param actionParameters the action parameters
	 */
	private void setActionParametersToDimmingConfiguration(List<SensusPartNumberConfiguration> lightConfigurations,
			List<LCActionParameter> actionParameters)
	{
		if (ValidationUtil.isNullOrEmpty(lightConfigurations) || ValidationUtil.isNull(actionParameters))
		{
			return;
		}

		for (SensusPartNumberConfiguration partNumberConfiguration : lightConfigurations)
		{
			Integer intensityLevel = partNumberConfiguration.getIntensityLevel();

			PropertyEnum hardwareSetting = PropertyEnum.valueOf("HARDWARE_SETTING_CONFIGURATION_" + intensityLevel);
			String hardwareSettingValue = String.valueOf(partNumberConfiguration.getHardwareSetting());
			actionParameters.add(new LCActionParameter(hardwareSetting, hardwareSettingValue));

			PropertyEnum currentScale = PropertyEnum.valueOf("CURRENT_SCALE_CONFIGURATION_" + intensityLevel);
			String currentScaleValue = String.valueOf(partNumberConfiguration.getCurrentScale());
			actionParameters.add(new LCActionParameter(currentScale, currentScaleValue));

			PropertyEnum fullOnRequired = PropertyEnum.valueOf("FULL_ON_REQUIRED_CONFIGURATION_" + intensityLevel);
			String fullOnRequiredValue = String.valueOf(partNumberConfiguration.getFullOnRequired());
			actionParameters.add(new LCActionParameter(fullOnRequired, fullOnRequiredValue));
		}
	}

	/**
	 * Sets the lc action parameter.
	 *
	 * @param lightRequest the light request
	 * @return the list
	 */
	private List<LCActionParameter> setLCActionParameter(LightRequest lightRequest)
	{
		LCActionParameter actionParameter = new LCActionParameter();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		switch (lightRequest.getLightStatusEnum())
		{
			case ACTIVE:

				LCActionParameter actionParameterActive =
				new LCActionParameter(PropertyEnum.ACTIVE, ACTIVE);
				actionParameter.setActionValue(ACTIVE);
				actionParameters.add(actionParameterActive);
				return actionParameters;

			case DEACTIVATED:

				LCActionParameter actionParameterDeactivated =
				new LCActionParameter(PropertyEnum.DEACTIVATED, DEACTIVATED);
				actionParameters.add(actionParameterDeactivated);
				return actionParameters;

			case MAINTENANCE:

				LCActionParameter actionParameterMaintenance =
				new LCActionParameter(PropertyEnum.MAINTENANCE, MAINTENANCE);
				actionParameters.add(actionParameterMaintenance);
				return actionParameters;
			default:
				return null;

		}
	}

	/**
	 * Gets the lights active.
	 *
	 * @param lightRequest the light request
	 * @return the lights active
	 */
	private List<Light> getLightsActive(LightRequest lightRequest)
	{
		final SearchLight searchLight = lightRequest.getSearchLight();

		// create a list of lights based on the user selection
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(lightRequest);

		String activeValue = String.valueOf(LightStatusEnum.ACTIVE.getValue());
		SearchParameter searchParameter = new SearchParameter(PropertyEnum.ALL_STATUS, activeValue);
		searchLight.addSearchParameter(searchParameter);
		searchLight.addStatus(LightStatusEnum.ACTIVE);
		inquiryLightRequest.setSearchLight(searchLight);

		List<Light> lights = getSmartPointDAC().fetchAllLights(inquiryLightRequest).getResultsList();

		// Remove parameters to fech lights active
		searchLight.getSearchParameters().remove(searchParameter);
		searchLight.getStatusList().remove(LightStatusEnum.ACTIVE);

		return lights;
	}

	/**
	 * Gets the action parameters by status message.
	 *
	 * @param lightRequest the light request
	 * @param action the action
	 * @return the action parameters by status message
	 */
	private void setActionParametersByStatusMessage(LightRequest lightRequest, LCAction action)
	{
		if (ValidationUtil.isNullOrEmpty(lightRequest.getStatusMessages()))
		{
			return;
		}

		// Loop between all request status messages
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		action.setActionType(LCActionTypeEnum.CLEAR_ALARM);
		for (StatusMessage statusMessage : lightRequest.getStatusMessages())
		{
			StatusException statusException = statusMessage.getStatusExceptions().get(0);
			LCActionParameter parm = new LCActionParameter(PropertyEnum.STATUS_SUBTYPE_ID,
					String.valueOf(statusException.getStatusExceptionTypeEnumValue()));
			actionParameters.add(parm);
		}
		action.setActionParameters(actionParameters);
	}

	/**
	 * Guarantee light existence.
	 *
	 * @param light the light
	 * @param userContext the user context
	 * @return the internal results response
	 */
	private InternalResultsResponse<Light> guaranteeLightExistence(Light light, UserContext userContext)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		List<Integer> allowedGroupIdList =
				LCPropertiesExtractorUtil.extractAuthorityId(userContext.getAuthorities());

		Double latitude = new Double(0.00);
		Double longitude = new Double(0.00);

		if (!ValidationUtil.isNullOrZero(light.getLightLocation().getLatitude())
				&& !ValidationUtil.isNullOrZero(light.getLightLocation().getLongitude()))
		{
			latitude = light.getLightLocation().getLatitude();
			longitude = light.getLightLocation().getLongitude();
		}

		LightRequest lightRequest = new LightRequest();
		lightRequest.addLight(light);
		lightRequest.setAllowedGroupIdList(allowedGroupIdList);

		// Verify if this rni Id exists in the LC
		InternalResultsResponse<Light> lightResponse =
				getSmartPointDAC().fetchLightByRniId(lightRequest);

		if (lightResponse.isInError())
		{
			persistLogInfo("**** GATEWAY: guaranteeLightExistence:RNI Id[" + light.getRniId()
					+ "] NOT IN LC. Add SmartPoint & Light ");

			// If lat and lng are 0.0, this indicates a SETUP message. When RNI receives a setup message, it does not
			// know what is the real tenant, so it sets it to ACME (always). To prevent adding the light with the wrong
			// tenant to LC, the code below will exit this routine without adding a light.
			if (new Double(0.00).equals(latitude) && (new Double(0.00).equals(longitude)))
			{
				response.setStatus(Status.NoRowsInsertedError);
			}
			else
			{
				// RNI ID NOT IN LC. Add SmartPoint & Light
				GuaranteeLightExistenceRequest existenceRequest = new GuaranteeLightExistenceRequest(userContext);

				// At this point, I don't know the status of the light (ON/OFF/BLINKING). Set it to UNKNOWN.
				light.setLightStateEnum(LightStateEnum.UNKNOWN);
				existenceRequest.addLight(light);
				// try to insert new light.
				Light newLight = insertLight(existenceRequest);
				if (ValidationUtil.isNull(newLight) || ValidationUtil.isNullOrZero(newLight.getId()))
				{
					prepareError(ADD_LIGHT_FAILED, response, InternalResponse.Status.SystemError);
				}
				else
				{
					// Set the light with the newly created Id.
					light.setId(newLight.getId());
					light.setSmartPointId(newLight.getSmartPointId());
					light.setCreateDate(newLight.getCreateDate());
					light.setProtect(true);
					// Add light to the response
					response.getResultsList().add(light);
					// Process lat and long
					processAddressInformation(light, userContext, response);
					// Immediately request the light details
					if (!response.isInError())
					{
						existenceRequest.setIsMonitored(false);
						existenceRequest.setCurrentLightStatus(false);
						// request ALL details for the light
						existenceRequest.getLightDetailDataType().add(LightDetailDataTypeEnum.CONFIGURATION);
						existenceRequest.getLightDetailDataType().add(LightDetailDataTypeEnum.STATUS);
						InternalResultsResponse<Process> getStatusResponse =
								initiateGetLightStatus(existenceRequest);
						if (getStatusResponse.isInError())
						{
							prepareError(REQUEST_LIGHT_DETAILS_FAILED, response,
									InternalResponse.Status.SystemError);
						}

						persistLogInfo("**** GATEWAY: guaranteeLightExistence:Requested Light Details, getStatusResponse.isInError()["
								+ getStatusResponse.isInError() + " ]");
					}
				}
			}
		}
		else
		{
			persistLogInfo("**** GATEWAY: guaranteeLightExistence:RNI ID[" + light.getRniId() + "] EXISTS IN LC.");

			// RniId exists. This means I need to verify if lat/long changed before updating properties.
			Light newLight = lightResponse.getFirstResult();
			// Add light to the response
			response.getResultsList().add(newLight);
			// Check if lat/long need to be updated
			if (getSmartPointDAC().locationHasChanged(light.getRniId(), latitude, longitude, allowedGroupIdList))
			{
				// First, dissociate light from all address related tags
				if (!getSmartPointDAC().deleteAddrTagsForLight(light.getRniId()).isInError())
				{
					newLight.setRniId(light.getRniId());
					// FIXME - properties
					/* newLight.setParameters(light.getParameters()); */
					// And Associate to the new address
					processAddressInformation(light, userContext, response);
				}
				else
				{
					prepareError(DELETE_OLD_LOCATION_FAILED, response, InternalResponse.Status.SystemError);
				}
			}

			if (lightIsInMaintenance(light.getId(), allowedGroupIdList)
					|| lightIsInDeactivated(light.getId(), allowedGroupIdList)
					|| requestReadLightStatus(light.getId(), allowedGroupIdList))
			{
				lightRequest = new LightRequest(userContext);
				lightRequest.addLight(light);
				lightRequest.setIsMonitored(false);

				// request ALL details for the light
				lightRequest.getLightDetailDataType().add(LightDetailDataTypeEnum.CONFIGURATION);
				lightRequest.getLightDetailDataType().add(LightDetailDataTypeEnum.STATUS);
				InternalResultsResponse<Process> getStatusResponse = initiateGetLightStatus(lightRequest);
				if (getStatusResponse.isInError())
				{
					prepareError(REQUEST_LIGHT_DETAILS_FAILED, response, InternalResponse.Status.SystemError);
				}

				persistLogInfo("**** GATEWAY: guaranteeLightExistence:Requested Light Details, getStatusResponse.isInError()[ "
						+ getStatusResponse.isInError() + "] ");
			}

		}
		return response;
	}

	/**
	 * Insert light.
	 *
	 * @param existenceRequest the existence request
	 * @return the light
	 */
	private Light insertLight(GuaranteeLightExistenceRequest existenceRequest)
	{
		Light light = new Light();

		// for insert a light is necessary insert first in smartPoint, because light need of the smartPointId.
		Integer smartPointId = getSmartPointDAC().insertSmartPoint(existenceRequest);

		if (ValidationUtil.isNullOrZero(smartPointId))
		{
			return light;
		}

		existenceRequest.getFirstLight().setSmartPointId(smartPointId);
		Integer lightId = getSmartPointDAC().insertLight(existenceRequest);
		light.setId(lightId);

		if (ValidationUtil.isNullOrZero(lightId))
		{
			return light;
		}

		// insert lightConfiguration
		light.getLightConfiguration().setLightId(lightId);
		existenceRequest.getFirstLight().setLightTypeEnum(LightTypeEnum.OTHER);
		getSmartPointDAC().insertLightConfiguration(existenceRequest);

		// insert lightLocation
		light.getLightLocation().setLightId(lightId);

		getSmartPointDAC().insertLightLocation(existenceRequest);

		// insert lightSchedule
		light.getLightSchedule().setLightId(lightId);

		getSmartPointDAC().insertLightSchedule(existenceRequest);

		// insert light Last Operational Data
		light.getLightLastOperationalData().setLightId(lightId);

		getSmartPointDAC().insertLightLastOperationalData(existenceRequest);

		// fetch light which just inserted
		existenceRequest.getFirstLight().setSmartPointId(smartPointId);
		light = getSmartPointDAC().fetchLightToInsert(existenceRequest);

		return light;

	}

	/**
	 * Prepares a message related to failure during the reverse lookup of lat and long.
	 *
	 * @param msg the msg
	 * @param response the response
	 * @param status the status
	 */
	private void prepareError(String msg, InternalResponse response, InternalResponse.Status status)
	{
		response.addMessage(msg, MessageSeverity.Error, MessageLevel.Object);
		response.setStatus(status);
	}

	/**
	 * Process address information.
	 *
	 * @param light the light
	 * @param userContext the user context
	 * @param response the response
	 */
	private void processAddressInformation(Light light, UserContext userContext, InternalResponse response)
	{
		// Extract Lat/Long
		Double latitude = light.getLightLocation().getLatitude();
		Double longitude = light.getLightLocation().getLongitude();

		persistLogInfo("**** GATEWAY: Beginning processAddressInformation: Lat[" + latitude + "], Long["
				+ longitude
				+ " ] ");

		if (ValidationUtil.isNullOrZero(latitude) || ValidationUtil.isNullOrZero(longitude))
		{
			persistLogInfo("**** Address Information not processed for zero Lat/Long");
			return;
		}

		// Fetch address information based on lat and long
		HashMap<String, String> addrInfo = getLcHelpGeoCoding().fetchLocationInfo(latitude, longitude);
		LightRequest lightRequest = new LightRequest();

		// If Address Information could not be retrieved, escape with an error. Otherwise proceed to insert light
		// and smartpoint
		if (ValidationUtil.isNull(addrInfo))
		{
			prepareError(REVERSE_LOOKUP_FAILED, response, InternalResponse.Status.ExternalError);
		}
		else
		{
			preparePropertiesLocation(addrInfo, light);
			lightRequest.addLight(light);
			upsertLightLocation(response, lightRequest);

			if (response.isInError())
			{
				prepareError(ADD_PROPERTY_VALUE_FAILED, response, InternalResponse.Status.SystemError);
			}
			else
			{
				// This will tell if I have at least one non-empty address field
				boolean isAddrEmpty = true;
				for (String tagName : addrInfo.values())
				{
					if (StringUtils.isBlank(tagName))
					{
						continue;
					}

					// If I don't have an empty tag name, work with it
					isAddrEmpty = false;
					addLightToTag(tagName, userContext, light, response);
					if (response.isInError())
					{
						break;
					}
				}

				persistLogInfo("**** GATEWAY: processAddressInformation: isAddrEmpty[" + isAddrEmpty + "]");

				// if no address tags, add light to "Incomplete Data" tag
				if (isAddrEmpty)
				{
					addLightToTag(incompleteData, userContext, light, response);
				}
				else
				{
					// address tags, update light lat/lng
					lightRequest = new LightRequest(userContext);
					lightRequest.addLight(light);
					InternalResponse internalResponse = getSmartPointDAC().updateLightLocation(lightRequest);
					if (internalResponse.isInError())
					{
						// something bad happened when trying to update light lat/lng.
						prepareError(UPDATE_LOCATION_FAILED, response, InternalResponse.Status.SystemError);
					}
				}
			}
		}

		persistLogInfo("**** GATEWAY: Finished processAddressInformation");
	}

	/**
	 * Prepare properties.
	 *
	 * @param addrInfo the addr info
	 * @param light the light
	 * @return the list with the following properties: DATE_ADDED, STREET, CITY, COUNTY, STATE, ZIP
	 */
	private void preparePropertiesLocation(HashMap<String, String> addrInfo, Light light)
	{
		light.getLightLocation().setStreetName(addrInfo.get(STREET));
		light.getLightLocation().setCityName(addrInfo.get(CITY));
		light.getLightLocation().setCountyName(addrInfo.get(COUNTY));
		light.getLightLocation().setStateName(addrInfo.get(STATE));
		light.getLightLocation().setZipCode(addrInfo.get(ZIP));
	}

	/**
	 * Light is in maintenance.
	 *
	 * @param lightId the light id
	 * @param allowedGroupIdList the allowed group id list
	 * @return true, if successful
	 */
	private boolean lightIsInMaintenance(Integer lightId, List<Integer> allowedGroupIdList)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.lightIsInMaintenance");

		InternalResultsResponse<StatusMessage> statusMessage =
				getSmartPointDAC().fetchStatusMessageByLightID(lightId, allowedGroupIdList);
		StatusMessage currentStatusMessage = statusMessage.getFirstResult();

		// If a current message exists. If there is no, assume the light is not in maintenance
		if (ValidationUtil.isNull(currentStatusMessage))
		{
			persistLogInfo("**** GATEWAY: Finished SmartpointBCL.lightIsInMaintenance = false");
			return false;
		}

		return LightStatusEnum.MAINTENANCE == currentStatusMessage.getLightStatusEnum();
	}

	/**
	 * Light is in maintenance.
	 *
	 * @param lightId the light id
	 * @param allowedGroupIdList the allowed group id list
	 * @return true, if successful
	 */
	private boolean lightIsInDeactivated(Integer lightId, List<Integer> allowedGroupIdList)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.lightIsInDeactivated");

		InternalResultsResponse<StatusMessage> statusMessage =
				getSmartPointDAC().fetchStatusMessageByLightID(lightId, allowedGroupIdList);
		StatusMessage currentStatusMessage = statusMessage.getFirstResult();

		// If a current message exists. If there is no, assume the light is not in Deactivated
		if (ValidationUtil.isNull(currentStatusMessage))
		{
			persistLogInfo(GATEWAY_FINISHED_SMARTPOINT_BCL_LIGHT_IS_DEACTIVATED_FALSE);
			return false;
		}

		return LightStatusEnum.DEACTIVATED == currentStatusMessage.getLightStatusEnum();
	}

	/**
	 * Light is in maintenance.
	 *
	 * @param lightId the light id
	 * @param allowedGroupIdList the allowed group id list
	 * @return true, if successful
	 */
	private boolean requestReadLightStatus(Integer lightId, List<Integer> allowedGroupIdList)
	{

		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.requestReadLightStatus");

		InternalResultsResponse<StatusMessage> statusMessage =
				getSmartPointDAC().fetchStatusMessageByLightIDMessageType(lightId,
						StatusMessageCategoryEnum.BINDING,
						allowedGroupIdList);

		if (!statusMessage.hasResults())
		{
			persistLogInfo(GATEWAY_FINISHED_SMARTPOINT_BCL_LIGHT_IS_DEACTIVATED_FALSE);
			return false;
		}

		Calendar maxTimeLimitCalend = Calendar.getInstance();
		maxTimeLimitCalend.add(Calendar.MINUTE, -getTimeForReadLightStatus());

		Calendar statusMessageCalend = Calendar.getInstance();
		statusMessageCalend.setTime(statusMessage.getFirstResult().getDate());

		// if the last binding message arrived more than one hour ago (param injected by spring), then ask for a
		// readlightstatus (STATUS + CONFIG) again
		return maxTimeLimitCalend.after(statusMessageCalend);
	}

	/**
	 * Adds the light to tag. If tag does not exist, try to create a new one.
	 *
	 * @param tagName the name of the tag I am looking for
	 * @param userContext the user context
	 * @param light the light
	 * @param response the response
	 */
	private void addLightToTag(String tagName, UserContext userContext, Light light,
			InternalResponse response)
	{

		persistLogInfo("**** GATEWAY: beginning addLightToTag ");

		TagRequest tagRequest = new TagRequest(userContext);
		Tag tag = new Tag();
		tag.setName(tagName);

		// This is an address related tag
		tag.setAddressRelated(true);
		tagRequest.setTag(tag);

		// Verify if tag already exists.

		InternalResultsResponse<Tag> tagResponse = getTagBCL().fetchTagByName(tagRequest);
		// tag does not exists, try to create it.
		if (!tagResponse.hasResults())
		{
			persistLogInfo("**** Creating TAG name=" + tag.getName());
			tag = getTagBCL().insertTag(tagRequest).getFirstResult();

			// problem creating tag.
			if (ValidationUtil.isNull(tag))
			{
				prepareError(ADD_TAG_FAILED, response, InternalResponse.Status.SystemError);
			}
		}
		else
		{
			// tag exists, just get the id.
			tag.setId(tagResponse.getFirstResult().getId());
		}

		persistLogInfo("**** Add light to TAG: " + tag.getName());

		// add light to tag
		tagRequest.addToTags(tag);
		tagRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		tagRequest.setCurrentLightStatus(false);
		InternalResponse internalResponse = getTagBCL().insertSmartPointToTag(tagRequest);
		if (internalResponse.isInError() && !ValidationUtil.isNullOrEmpty(tagResponse.getMessageInfoList()))
		{
			// verify if the problem was that smp was already in tag. If yes, it is ok.
			if (!tagResponse.getMessageInfoList().get(0).getCode().equalsIgnoreCase(LIGHT_ALREADY_IN_TAG))
			{
				// something bad happened when trying to add light to tag.
				prepareError(ADD_LIGHT_TO_TAG_FAILED, response, InternalResponse.Status.SystemError);
			}
		}

		persistLogInfo("**** GATEWAY: Finished addLightToTag");
	}

	/**
	 * Request Light Details.
	 *
	 * @param light the light
	 * @param userContext the user context
	 * @param type the type
	 * @param response the response
	 */
	private void requestLightDetails(Light light, UserContext userContext, String type, InternalResponse response)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.requestLightDetails;");

		// Immediately request the light details.
		final LightRequest lightRequest = new LightRequest(userContext);
		lightRequest.addLight(light);
		lightRequest.setIsMonitored(false);
		lightRequest.getLightDetailDataType().add(LightDetailDataTypeEnum.STATUS);

		if (type.equalsIgnoreCase(ALL))
		{
			// Request all details for the light.
			lightRequest.getLightDetailDataType().add(LightDetailDataTypeEnum.CONFIGURATION);
			persistLogInfo("**** Asking for STATUS and CONFIG");
		}

		InternalResultsResponse<Process> statusResponse = initiateGetLightStatus(lightRequest);

		if (statusResponse.isInError())
		{
			prepareError(REQUEST_LIGHT_DETAILS_FAILED, response, InternalResponse.Status.SystemError);
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.requestLightDetails; isInError="
				+ statusResponse.isInError());
	}

	/**
	 * This is a generic routine to process Alarms/Warnings and Forced Status. The type of message will be sent
	 * by the corresponding operation (processAlarmWarningNotification or processForcedLightStatusNotification)
	 *
	 * @param request the request
	 * @param statusMessageCategoryEnum the status message category enum
	 * @return the internal results response
	 */
	private InternalResultsResponse<StatusMessage> processAlarmWarning(
			AlarmNotificationRequest request, StatusMessageCategoryEnum statusMessageCategoryEnum)
			{
		persistLogInfo("**** Beginning process alarm and warning; lightStatus=" + request.getLightStatusEnum());

		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
		List<StatusMessage> statusMessageList = new ArrayList<StatusMessage>();
		boolean updateAnalytics = false;

		switch (request.getLightStatusEnum())
		{
			case DEACTIVATED:

				statusMessageList.add(processLightDeactivated(request, statusMessageCategoryEnum));
				break;

			case MAINTENANCE:

				statusMessageList.add(processLightMaintenance(statusMessageCategoryEnum));
				break;

			default:

				statusMessageList.addAll(processLightAlarm(request, statusMessageCategoryEnum));
				updateAnalytics = true;
				break;
		}

		if (ValidationUtil.isNullOrEmpty(statusMessageList))
		{
			response.setStatus(Status.PersistenceError);
			return response;
		}

		return insertStatusMessageByLight(request, updateAnalytics, false, statusMessageList);
			}

	/**
	 * Process light alarm.
	 *
	 * @param request the request
	 * @param statusMessageCategory the status message category
	 * @return the list
	 */
	private List<StatusMessage> processLightAlarm(AlarmNotificationRequest request,
			StatusMessageCategoryEnum statusMessageCategory)
			{
		// Verify if need to request light details (In case the light got out of MAINTENANCE)
		if (lightIsInMaintenance(request.getLight().getId(), request.getAllowedGroupIdList()))
		{
			InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
			requestLightDetails(request.getLight(), request.getUserContext(), ALL, response);

			if (response.isInError())
			{
				return null;
			}
		}

		// If no exceptions, Status = ACTIVE)
		if (request.getStatusException().isEmpty())
		{
			persistLogInfo("**** No exceptions, light ACTIVE");
			return Arrays.asList(processLightActive(statusMessageCategory));
		}

		Map<LightStatusEnum, StatusMessage> alarmWarningMap = new HashMap<LightStatusEnum, StatusMessage>();

		// Calculate Status
		for (StatusException statusException : request.getStatusException())
		{
			StatusExceptionTypeEnum statusExceptionEnum = statusException.getStatusExceptionTypeEnum();
			LightStatusEnum lightStatus = statusExceptionEnum.getLightStatusEnum();
			StatusMessage statusMessage = alarmWarningMap.get(lightStatus);

			if (ValidationUtil.isNull(statusMessage))
			{
				// Add a StatusMessage with category passed in
				statusMessage = new StatusMessage();
				statusMessage.setDate(new Date());
				statusMessage.setStatusMessageCategoryEnum(statusMessageCategory);
				statusMessage.setLightStatusEnum(lightStatus);
			}

			statusMessage.addStatusException(statusException);
			alarmWarningMap.put(lightStatus, statusMessage);
		}

		if (alarmWarningMap.isEmpty())
		{
			return null;
		}

		return new ArrayList<StatusMessage>(alarmWarningMap.values());
			}

	/**
	 * 90= STATUS. Update properties and Light State.
	 *
	 * @param idStatusMessage the id status message
	 * @param userContext the user context
	 * @param light the light
	 * @param operationalData the operational data
	 * @return the internal response
	 */
	private InternalResponse processAppCode90(Integer idStatusMessage, UserContext userContext, Light light,  List<OperationalData> operationalData)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processAppCode90");

		// update LightState
		LightRequest lightRequest = new LightRequest(userContext);
		lightRequest.addLight(light);

		InternalResponse response = getSmartPointDAC().updateLightState(lightRequest);
		if (response.isInError())
		{
			return response;
		}

		// Operational Data
		if (!ValidationUtil.isNullOrEmpty(operationalData))
		{
			response = getSmartPointDAC().insertOperationalData(userContext.getUserId(), idStatusMessage,
					operationalData);

			if (response.isInError())
			{
				return response;
			}
		}

		// Update for each property
		response = processAppCode85(userContext, lightRequest);

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.processAppCode90=" + response.getStatus());
		return response;
	}

	/**
	 * 85=NA2W Channel Setup Audit 2. Only Update properties.
	 *
	 * @param userContext the user context
	 * @param lightRequest the light request
	 * @return the internal response
	 */
	private InternalResponse processAppCode85(UserContext userContext, LightRequest lightRequest)
	{
		if (ValidationUtil.isNull(lightRequest.getFirstLight().getLightLastOperationalData()))
		{
			return new InternalResponse();
		}

		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.processAppCode85");

		// Update for each property
		InternalResponse response = new InternalResponse();

		upsertLightLastOperationalData(response, lightRequest);

		if (!ligthIsDimmable(lightRequest.getFirstLight()))
		{
			return response;
		}

		if (!ValidationUtil.isNullOrEmpty(lightRequest.getFirstLight().getLightConfiguration().getModelNumber()))
		{

			InternalResultsResponse<Process> responseSetupDimmingConfiguration =
					initiateSetupDimmingConfiguration(lightRequest);
			if (responseSetupDimmingConfiguration.isInError())
			{
				response.setStatus(Status.ExceptionError);
				response.addMessages(responseSetupDimmingConfiguration.getMessageInfoList());
			}
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.processAppCode85; status=" + response.getStatus());
		return response;
	}

	/**
	 * Ligth is dimmable.
	 *
	 * @param light the light
	 * @return true, if successful
	 */
	private boolean ligthIsDimmable(Light light)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.ligthIsDimmable");

		if (light.getLightConfiguration().getDimmable())
		{
			persistLogInfo("**** GATEWAY: Finished SmartpointBCL.ligthIsDimmable. Light is Dimmable");
			return true;
		}


		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.ligthIsDimmable. Light is NOT Dimmable");

		return false;
	}

	/**
	 * Initialize consumption.
	 *
	 * @param userId the user id
	 * @param response the response
	 * @param idStatusMessage the id status message
	 */
	private void initializeConsumption(String userId, InternalResultsResponse<Light> response,
			Integer idStatusMessage)
	{
		persistLogInfo("**** GATEWAY: Beginning SmartpointBCL.initializeConsumption");

		List<OperationalData> operationalData = new ArrayList<OperationalData>();
		operationalData.add(new OperationalData(new Float(0), OperationalDataTypeEnum.CUMULATIVECONSUMPTION));
		if (getSmartPointDAC().insertOperationalData(userId, idStatusMessage, operationalData).isInError())
		{
			response.setStatus(Status.PersistenceError);
		}

		persistLogInfo("**** GATEWAY: Finished SmartpointBCL.initializeConsumption; Status="
				+ response.getStatus());
	}

	/**
	 * Upsert eco mode from light.
	 *
	 * @param userContext the user context
	 * @param light the light
	 * @param response the response
	 */
	private void upsertEcoModeFromLight(UserContext userContext, Light light, InternalResponse response)
	{
		if (ValidationUtil.isNull(light) || ValidationUtil.isNull(light.getPoleId()))
		{
			response.addMessage(ERROR_UPSERTING_ECOMODE, MessageSeverity.Warning, MessageLevel.FieldValidation);
			return;
		}


		EcoModeBaseline baseline = light.getEcoModeBaseline();

		if (ValidationUtil.isNull(baseline)
				|| ValidationUtil.isNull(baseline.getReplacedType())
				|| ValidationUtil.isNull(baseline.getReplacedWattage()))
		{
			// Case light not eco-mode informations then return without execute action because do not required values to
			// calculate eco-mode
			return;
		}

		EcoModeBaseline newBaseline = new EcoModeBaseline(
				light,
				baseline.getReplacedType(),
				baseline.getReplacedWattage(),
				true);

		InquiryEcoModeRequest ecoModeRequest = new InquiryEcoModeRequest(userContext);
		ecoModeRequest.addEcoModeBaseline(newBaseline);
		ecoModeRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));

		InternalResponse internalResponse = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
		if (internalResponse.isInError())
		{
			response.addMessages(internalResponse.getMessageInfoList());
			response.setStatus(internalResponse.getStatus());
		}
	}

	/**
	 * Update light.
	 *
	 * @param response the response
	 * @param lightRequest the light request
	 */
	private void updateLight(InternalResponse response, LightRequest lightRequest)
	{
		response = getSmartPointDAC().updateLight(lightRequest);

		if(response.isInError())
		{
			prepareError(UPDATE_PROPERTY_LIGHT_POLE_ID_FAILED, response, Status.ExceptionError);
		}
	}

	/**
	 * Upsert light location.
	 *
	 * @param response the response
	 * @param lightRequest the light request
	 */
	private void upsertLightLocation(InternalResponse response, LightRequest lightRequest)
	{
		InternalResultsResponse<Light> lightResponse = new InternalResultsResponse<Light>();

		lightResponse = getSmartPointDAC().fetchLightLocationById(lightRequest);

		if(!lightResponse.hasResults())
		{
			GuaranteeLightExistenceRequest guaranteeRequest = new GuaranteeLightExistenceRequest();
			guaranteeRequest.addLight(lightRequest.getFirstLight());
			getSmartPointDAC().insertLightLocation(guaranteeRequest);
		}
		else
		{
			response = getSmartPointDAC().updateLightLocation(lightRequest);

			if(response.isInError())
			{
				prepareError(UPDATE_LAT_LONG_FAILED, response, Status.ExceptionError);
			}
		}
	}

	/**
	 * Upsert light last operational data.
	 *
	 * @param response the response
	 * @param lightRequest the light request
	 */
	private void upsertLightLastOperationalData(InternalResponse response, LightRequest lightRequest)
	{
		InternalResultsResponse<Light> lightResponse = new InternalResultsResponse<Light>();

		lightResponse = getSmartPointDAC().fetchLightLocationById(lightRequest);

		if(!lightResponse.hasResults())
		{
			GuaranteeLightExistenceRequest guaranteeRequest = new GuaranteeLightExistenceRequest();
			guaranteeRequest.addLight(lightRequest.getFirstLight());
			getSmartPointDAC().insertLightLastOperationalData(guaranteeRequest);
		}
		else
		{
			response = getSmartPointDAC().updateLightLastOperationData(lightRequest);

			if(response.isInError())
			{
				prepareError(UPDATE_LIGHT_LAST_OPERATIONAL_DATA_FAILED, response, Status.ExceptionError);
			}
		}
	}
}
