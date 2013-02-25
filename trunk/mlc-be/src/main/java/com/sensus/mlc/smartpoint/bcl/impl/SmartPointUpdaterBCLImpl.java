package com.sensus.mlc.smartpoint.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Class SmartPointUpdaterBCLImpl.
 */
public class SmartPointUpdaterBCLImpl extends SmartPointBCLBase implements ISmartPointUpdaterBCL
{
	/** The Constant AMOUNT_UPDATE_LIGHT_STATUS_FAILED. */
	private static final String AMOUNT_UPDATE_LIGHT_STATUS_FAILED =
			"sensus.mlc.smartpointbclimpl.update.light.status.failed";

	/** The Constant ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED. */
	private static final String ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED =
			"sensus.mlc.smartpointbclimpl.add.property.to.customsearch.failed";

	/** The Constant RESET_MIN_MAX_FAILED. */
	private static final String RESET_MIN_MAX_FAILED =
			"sensus.mlc.smartpointbclimpl.update.min.max.failed";

	/** The Constant SMARTPOINT_STATUS_REQUIRED. */
	private static final String IS_REQUIRED = "sensus.mlc.validator.required";

	/** The temp file path. */
	private String tempFilePath; // injected by Spring - come from sensus-lc.properties;

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

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightStatus(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> updateLightStatus(LightRequest lightRequest)
	{
		// create a list of lights based on the user selection.
		List<Light> lightList = lightRequest.getLights();
		lightRequest.setSearchLight(new SearchLight());

		if (isNullOrEmpty(lightRequest.getLights()))
		{
			InquiryLightRequest inquiryLightResquest = createInquiryLightRequest(lightRequest);
			lightList = getSmartPointDAC().fetchAllLights(inquiryLightResquest).getResultsList();
		}

		// the code below change the status of light
		AlarmNotificationRequest alarmNotificationRequest = new AlarmNotificationRequest();
		alarmNotificationRequest.setUserContext(lightRequest.getUserContext());

		int errorLightsAmount = 0;

		for (Light light : lightList)
		{
			if (!isNull(light.getProtect()) && light.getProtect())
			{
				continue;
			}

			alarmNotificationRequest.setLight(light);
			alarmNotificationRequest.setLightStatusEnum(lightRequest.getLightStatusEnum());

			InternalResultsResponse<StatusMessage> statusMessageResponse =
					processUpdateLightStatus(alarmNotificationRequest, StatusMessageCategoryEnum.EDIT_STATUS);

			if (statusMessageResponse.isInError())
			{
				errorLightsAmount++;
			}
		}

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		if (errorLightsAmount > 0)
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(AMOUNT_UPDATE_LIGHT_STATUS_FAILED, MessageSeverity.Warning, MessageLevel.Other,
					new Object[] {errorLightsAmount});
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertColumnFilters(com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();

		switch (columnFilterRequest.getListTypeEnum())
		{
			case SMARTPOINTLIST:

				response = insertColumnsFiltersToCustomSearch(columnFilterRequest);
				break;
			default:
				response = new InternalResponse();
				List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

				messageInfoList.add(new MessageInfo(ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED,
						Message.MessageSeverity.Error,
						Message.MessageLevel.FieldValidation));

				response.addMessages(messageInfoList);
				response.setStatus(Status.ExceptionError);
				return response;
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertColumnsFiltersToCustomSearch(com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		if (!isNullOrEmpty(columnFilterRequest.getListColumn()))
		{
			response = getSmartPointDAC().insertColumnsToCustomSearch(columnFilterRequest);
		}

		if (!isNullOrEmpty(columnFilterRequest.getFilters()))
		{
			response = getSmartPointDAC().insertFiltersToCustomSearch(columnFilterRequest);
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#resetMinMaxValue(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> resetMinMaxValue(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> ligthResponse = new InternalResultsResponse<Light>();

		setSelectionPaginationId(lightRequest);

		LCAction action = new LCAction(LCActionTypeEnum.RESET_LIGHT_MIN_MAX);
		action.setActionParameters(Arrays.asList(new LCActionParameter(PropertyEnum.FLEXNET_ID)));
		InternalResponse processResponse = insertProcess(lightRequest, action);

		if (processResponse.isInError())
		{
			ligthResponse.addMessages(processResponse.getMessageInfoList());
			ligthResponse.setStatus(processResponse.getStatus());
			return ligthResponse;
		}

		InternalResponse response = getSmartPointDAC().resetMinMaxValue(lightRequest);

		InquiryLightRequest inquiryLightRequest = LCHelp.createInquiryLightRequest(lightRequest);
		ligthResponse = getSmartPointDAC().fetchAllLights(inquiryLightRequest);

		if (response.isInError())
		{
			List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();
			messageInfoList.add(new MessageInfo(RESET_MIN_MAX_FAILED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation));

			ligthResponse.addMessages(messageInfoList);
			ligthResponse.setStatus(response.getStatus());
		}

		return ligthResponse;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertStatusMessage(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse insertStatusMessage(LightRequest request)
	{
		InternalResponse response = new InternalResponse();

		if (isNull(request.getFirstMessage()))
		{
			List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

			messageInfoList.add(new MessageInfo(IS_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation,
					"StatusMessage"));

			response.addMessages(messageInfoList);
			response.setStatus(Status.ExceptionError);
			return response;
		}

		if (isNull(request.getFirstLight()))
		{
			List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

			messageInfoList.add(new MessageInfo(IS_REQUIRED,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation,
					"Light"));

			response.addMessages(messageInfoList);
			response.setStatus(Status.ExceptionError);
			return response;
		}

		StatusMessage statusMessage = request.getFirstMessage();

		AlarmNotificationRequest notificationRequest = new AlarmNotificationRequest(request.getUserContext());
		notificationRequest.setLight(request.getFirstLight());

		statusMessage = processLightDeactivated(notificationRequest, statusMessage.getStatusMessageCategoryEnum());
		return insertStatusMessageByLight(notificationRequest, false, true, Arrays.asList(statusMessage));
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertCustomSearch(com.sensus.mlc.smartpoint.model.request.CustomSearchRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		InternalResultsResponse<CustomSearch> response = getSmartPointDAC().insertCustomSearch(request);

		if (response.getStatus().equals(Status.OperationSuccess))
		{

			// save column and filter
			if (response.getStatus().equals(Status.OperationSuccess) && response.hasResults())
			{
				if (!isNullOrEmpty(request.getCustomSearch().getListColumn())
						&& !isNullOrEmpty(request.getCustomSearch().getListFilters()))
				{
					ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
					columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);

					columnFilterRequest.setCustomSearchId(response.getFirstResult().getId());

					columnFilterRequest.setListColumn(response.getFirstResult().getListColumn());
					columnFilterRequest.setFilters(response.getFirstResult().getListFilters());
					columnFilterRequest.setUserContext(request.getUserContext());

					InternalResponse responseColumnFilter = insertColumnFilters(columnFilterRequest);
					response = verifyMessage(response, responseColumnFilter);
				}
			}
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#deleteCustomSearch(com.sensus.mlc.smartpoint.model.request.CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		return getSmartPointDAC().deleteCustomSearch(request);
	}

	//	@Override
	//	public InternalResponse upsertLightProperty(LightStatusNotificationRequest request)
	//	{
	//		return getSmartPointDAC().upsertLightProperty(request.getUserContext().getUserId(),
	//				request.getLight().getRniId(), request.getLightParameters());
	//	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightProtected(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> updateLightProtected(LightRequest lightRequest)
	{
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.PROTECTED, String.valueOf(lightRequest.getProtect())));

		LCAction action = new LCAction(LCActionTypeEnum.SET_PROTECTED);
		action.setActionParameters(actionParameters);

		setSelectionPaginationId(lightRequest);

		InternalResponse processResponse = insertProcess(lightRequest, action);
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response.setStatus(processResponse.getStatus());
		response.addMessages(processResponse.getMessageInfoList());

		if (processResponse.isInError())
		{
			return response;
		}

		response = getSmartPointDAC().updateLightProtected(lightRequest);

		if (response.isInError())
		{
			return response;
		}

		return getSmartPointDAC().fetchLightById(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#verifyCommunicationMessage(com.sensus.common.model.UserContext)
	 */
	@Override
	public void verifyCommunicationMessage(UserContext userContext)
	{
		persistLogInfo("Beginning SmartPointBCL.verifyCommunicationMessage");

		// Iterating throught all available tenants.
		List<Light> lightsWithCommunicationFail = fetchLightsToAddCommunicationFailure(userContext, true);
		insertCommunicationFailure(userContext, lightsWithCommunicationFail, true);

		List<Light> lightsWithoutCommunicationFail = fetchLightsToAddCommunicationFailure(userContext, false);
		insertCommunicationFailure(userContext, lightsWithoutCommunicationFail, false);
		persistLogInfo("Ending SmartPointBCL.verifyCommunicationMessage");

	}


	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLight(LightRequest lightRequest)
	{
		return getSmartPointDAC().updateLight(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightSchedule(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightSchedule(LightRequest lightRequest)
	{
		return getSmartPointDAC().updateLightSchedule(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightLastOperationData(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightLastOperationData(LightRequest lightRequest)
	{
		return getSmartPointDAC().updateLightLastOperationData(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightLocation(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightLocation(LightRequest lightRequest)
	{
		return getSmartPointDAC().updateLightLocation(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#updateLightConfiguration(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResponse updateLightConfiguration(LightRequest lightRequest)
	{
		return getSmartPointDAC().updateLightConfiguration(lightRequest);
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL#insertLightSchedule(com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest)
	 */
	@Override
	public void insertLightSchedule(GuaranteeLightExistenceRequest lightRequest)
	{
		getSmartPointDAC().insertLightSchedule(lightRequest);

	}

	/**
	 * Fetch lights to add communication failure.
	 *
	 * @param userContext the user context
	 * @param isCommunicationFailure the is communication failure
	 * @return the list
	 */
	private List<Light> fetchLightsToAddCommunicationFailure(UserContext userContext, boolean isCommunicationFailure)
	{
		TenantRequest tenantRequest = new TenantRequest(userContext);
		tenantRequest.setCommunicationFailure(isCommunicationFailure);
		tenantRequest.setTenant(userContext.<Tenant> getTenant());
		InternalResultsResponse<Light> response =
				getSmartPointDAC().fetchCurrentLightMessagesByTenant(tenantRequest);

		if (response.isInError())
		{
			return null;
		}

		return response.getResultsList();
	}

	/**
	 * Insert communication failure.
	 *
	 * @param userContext the user context
	 * @param lights the lights
	 * @param existCommunicationFailure the exist communication failure
	 */
	private void insertCommunicationFailure(UserContext userContext, List<Light> lights,
			boolean existCommunicationFailure)
	{
		if (isNullOrEmpty(lights))
		{
			return;
		}

		Tenant tenant = userContext.getTenant();
		String userName = userContext.getUserId();

		for (Light light : lights)
		{
			// Delete all current alarm/warning messages on current_alarm_warning_message table for the current light.
			getSmartPointDAC().deleteCurrentAlarmWarningMessageByLightID(light.getId());

			StatusMessage currentStatusMessage = light.getCurrentStatusMessage();
			if (!existCommunicationFailure)
			{
				Date date = new Date();
				StatusMessage statusMessage = new StatusMessage();
				statusMessage.setDate(date);
				statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.ALARM);
				statusMessage.setLightStatusEnum(LightStatusEnum.WARNING);
				statusMessage.addStatusException(new StatusException(StatusExceptionTypeEnum.COMMUNICATION_FAIL));

				Integer statusMessageId =
						getSmartPointDAC().insertStatusMessage(statusMessage, userName, tenant.getId(),
								light.getId(), false);

				getSmartPointDAC().insertStatusMessageStatusSubtype(
						statusMessageId,
						StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue(),
						userName,
						true);

				currentStatusMessage.setId(statusMessageId);
				currentStatusMessage.setDate(date);
			}

			getSmartPointDAC().insertCurrentAlarmStatusMessage(
					new CurrentAlarmWarningMessage(
							light.getId(),
							currentStatusMessage.getId(),
							StatusMessageCategoryEnum.ALARM,
							LightStatusEnum.WARNING,
							StatusExceptionTypeEnum.COMMUNICATION_FAIL,
							currentStatusMessage.getDate(),
							tenant.getId()));

			// Update current light status id
			AlarmNotificationRequest request = new AlarmNotificationRequest(userContext);
			light.setCurrentStatusMessage(currentStatusMessage);
			request.setLight(light);
			updateCurrentStatusMessage(request, new InternalResponse());

			// Update light properties for communication failure.
			LightRequest lightRequest = new LightRequest(userContext, light);
			getSmartPointDAC().insertCommunicationFailureWarnings(lightRequest);
		}
	}

	/**
	 * Process update light status.
	 *
	 * @param request the request
	 * @param statusMessageCategoryEnum the status message category enum
	 * @return the internal results response
	 */
	private InternalResultsResponse<StatusMessage> processUpdateLightStatus(
			AlarmNotificationRequest request, StatusMessageCategoryEnum statusMessageCategoryEnum)
			{
		persistLogInfo("**** Beginning process update light status; lightStatus= " + request.getLightStatusEnum());

		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
		List<StatusMessage> statusMessageList = new ArrayList<StatusMessage>();
		boolean updateAnalytics = false;

		switch (request.getLightStatusEnum())
		{
			case ACTIVE:

				statusMessageList.add(processLightActive(statusMessageCategoryEnum));
				break;

			case DEACTIVATED:

				statusMessageList.add(processLightDeactive(request, statusMessageCategoryEnum));
				break;

			case MAINTENANCE:

				statusMessageList.add(processLightMaintenance(statusMessageCategoryEnum));
				break;
			default:
				return response;

		}
		if (isNullOrEmpty(statusMessageList))
		{
			response.setStatus(Status.PersistenceError);
			return response;
		}

		return insertStatusMessageByLight(request, updateAnalytics, false, statusMessageList);
			}

	/**
	 * Process light deactive.
	 *
	 * @param request the request
	 * @param statusMessageCategory the status message category
	 * @return the status message
	 */
	private StatusMessage processLightDeactive(AlarmNotificationRequest request,
			StatusMessageCategoryEnum statusMessageCategory)
	{
		InternalResponse response =
				getSmartPointDAC().deleteLightFromAllTagScheduleGroup(request.getLight().getRniId());

		if (response.isInError())
		{
			persistLogDebug("**** Error deleting light from all tag, schedule and group.");
			return null;
		}

		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
		statusMessage.setStatusMessageCategoryEnum(statusMessageCategory);

		return statusMessage;
	}

	/**
	 * Verify message.
	 *
	 * @param response the response
	 * @param responseProperty the response property
	 * @return the internal results response
	 */
	private InternalResultsResponse<CustomSearch> verifyMessage(InternalResultsResponse<CustomSearch> response,
			InternalResponse responseProperty)
			{
		if (!isNull(responseProperty) &&
				responseProperty.getStatus().equals(Status.OperationSuccess))
		{
			return response;
		}

		List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

		messageInfoList.add(new MessageInfo(ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED,
				Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation));

		response.addMessages(messageInfoList);
		response.setStatus(Status.ExceptionError);
		return response;
			}

}
