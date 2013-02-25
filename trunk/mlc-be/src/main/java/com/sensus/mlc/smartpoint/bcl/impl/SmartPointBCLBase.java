package com.sensus.mlc.smartpoint.bcl.impl;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.dac.ISmartPointDAC;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.LightStateEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.StatusException;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.StatusMessageCategoryEnum;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.tenant.model.Tenant;

public class SmartPointBCLBase
{
	/** The LOG. */
	private static transient Log LOG = LogFactory.getLog(SmartPointBCLBase.class);

	/** The Constant ERROR_UPDATING_LIGHT_STATE. */
	private static final String ERROR_UPDATING_LIGHT_STATE = "Error updating light state.";

	/** The smartpoint dac. */
	private ISmartPointDAC smartpointDAC; // injected by Spring through setter

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The Constant SPACE. */
	protected static final String SPACE = " ";

	/** The Constant OPERATOR_PLUS. */
	protected static final String OPERATOR_MINUS = "-";

	/** The Constant OPERATOR_PLUS. */
	protected static final String OPERATOR_PLUS = "+";

	/**
	 * Gets the lc help.
	 *
	 * @return the lc help
	 */
	protected LCHelp getLcHelp()
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
	 * Gets the process bcl.
	 *
	 * @return the process bcl
	 */
	protected IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 *
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Spring Sets the smart point dac.
	 *
	 * @param smartpointDACParam the new smart point dac
	 */
	public void setSmartPointDAC(ISmartPointDAC smartpointDACParam)
	{
		smartpointDAC = smartpointDACParam;
	}

	/**
	 * Gets the smart point dac.
	 *
	 * @return the smart point dac
	 */
	protected ISmartPointDAC getSmartPointDAC()
	{
		return smartpointDAC;
	}

	/**
	 * Persist log info.
	 *
	 * @param message the message
	 */
	protected void persistLogInfo(String message)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(message);
		}
	}

	/**
	 * Persist log debug.
	 *
	 * @param message the msg
	 */
	protected void persistLogDebug(String message)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(message);
		}
	}

	/**
	 * Fetch light by id.
	 *
	 * @param lightRequest the light request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Light> fetchLightById(LightRequest lightRequest)
	{
		InternalResultsResponse<Light> response = getSmartPointDAC().fetchLightById(lightRequest);

		if (response.hasResults() && !ValidationUtil.isNull(response.getFirstResult().getOffSetSchedule()))
		{
			if (response.getFirstResult().getOffSetSchedule().getSunriseOffsetMinutes() < 0)
			{
				response.getFirstResult()
				.getOffSetSchedule()
				.setSunriseOffsetMinutes(
						response.getFirstResult().getOffSetSchedule().getSunriseOffsetMinutes() * -1);
				response.getFirstResult().getOffSetSchedule().setSunriseBefore(Boolean.TRUE);
			}
			else
			{
				response.getFirstResult().getOffSetSchedule().setSunriseBefore(Boolean.FALSE);
			}

			if (response.getFirstResult().getOffSetSchedule().getSunsetOffsetMinutes() < 0)
			{
				response.getFirstResult()
				.getOffSetSchedule()
				.setSunsetOffsetMinutes(
						response.getFirstResult().getOffSetSchedule().getSunsetOffsetMinutes() * -1);
				response.getFirstResult().getOffSetSchedule().setSunsetBefore(Boolean.TRUE);
			}
			else
			{
				response.getFirstResult().getOffSetSchedule().setSunsetBefore(Boolean.FALSE);
			}

		}

		return response;
	}

	/**
	 * Fetch ligthing configurations by part number.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<SensusPartNumberConfiguration> fetchLigthingConfigurationsByPartNumber(
			LightingConfigurationRequest request)
			{
		return getSmartPointDAC().fetchLigthingConfigurationsByPartNumber(request);
			}

	/**
	 * Gets the light list.
	 *
	 * @param lightRequest the light request
	 * @return the light list
	 */
	protected List<Light> getLightList(LightRequest lightRequest)
	{
		InquiryLightRequest inquiryLightRequest = new InquiryLightRequest(lightRequest.getUserContext());
		inquiryLightRequest.setSortExpression(new MLCSortExpression());
		inquiryLightRequest.getMlcSortExpression().setField(LightOrderByEnum.LIGHT_ID.getValue());
		inquiryLightRequest.getMlcSortExpression().setIsProperty(false);
		inquiryLightRequest.setSearch(lightRequest.getSearchLight());
		inquiryLightRequest.setSelectionPaginationIds(lightRequest.getSelectionPaginationIds());
		inquiryLightRequest.setPaginationAllSelected(lightRequest.getPaginationAllSelected());
		inquiryLightRequest.setPageSize(0);
		inquiryLightRequest.setTenant(lightRequest.getTenant());
		return getSmartPointDAC().fetchAllLights(inquiryLightRequest).getResultsList();
	}

	/**
	 * Insert process.
	 *
	 * @param lightRequest the light request
	 * @param lcAction the lc action
	 * @return the internal results response
	 */
	protected InternalResultsResponse<Process> insertProcess(LightRequest lightRequest, LCAction lcAction)
	{
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(lightRequest);
		Integer lightsAmount = getSmartPointDAC().countLights(inquiryLightRequest).getFirstResult();

		Process process = LCHelp.generateProcess(lightRequest.isMonitored(), lcAction, lightsAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(getNewDateUTC());

		ProcessRequest processRequest = createProcessRequest(lightRequest, process);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Creates the status message for binding.
	 *
	 * @return the status message
	 */
	protected StatusMessage createStatusMessageForBinding()
	{
		final StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setLightStatusEnum(LightStatusEnum.ACTIVE);
		statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.BINDING);
		return statusMessage;
	}

	/**
	 * Process light deactivated.
	 *
	 * @param request the request
	 * @param statusMessageCategory the status message category
	 * @return the status message
	 */
	protected StatusMessage processLightDeactivated(AlarmNotificationRequest request,
			StatusMessageCategoryEnum statusMessageCategory)
	{
		InternalResponse response =
				getSmartPointDAC().deleteLightFromAllTagScheduleGroup(request.getLight().getRniId());

		if (response.isInError())
		{
			persistLogDebug("**** Error deleting light from all tag, schedule and group.");
			return null;
		}

		// update LightState to DEACTIVATED
		persistLogDebug("**** Beginning updateLightState to DEACTIVATED");

		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(request.getUserContext());
		request.getLight().setLightStateEnum(LightStateEnum.DEACTIVATED);
		lightRequest.addLight(request.getLight());

		response = getSmartPointDAC().updateLightState(lightRequest);

		if (response.isInError())
		{
			persistLogDebug(ERROR_UPDATING_LIGHT_STATE);
			return null;
		}

		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setLightStatusEnum(LightStatusEnum.DEACTIVATED);
		statusMessage.setStatusMessageCategoryEnum(statusMessageCategory);

		return statusMessage;
	}

	/**
	 * Process light maintenance.
	 *
	 * @param statusMessageCategory the status message category
	 * @return the status message
	 */
	protected StatusMessage processLightMaintenance(StatusMessageCategoryEnum statusMessageCategory)
	{
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setLightStatusEnum(LightStatusEnum.MAINTENANCE);
		statusMessage.setStatusMessageCategoryEnum(statusMessageCategory);

		return statusMessage;
	}

	/**
	 * Process light active.
	 *
	 * @param statusMessageCategory the status message category
	 * @return the status message
	 */
	protected StatusMessage processLightActive(StatusMessageCategoryEnum statusMessageCategory)
	{
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setDate(new Date());
		statusMessage.setStatusMessageCategoryEnum(statusMessageCategory);
		statusMessage.setLightStatusEnum(LightStatusEnum.ACTIVE);
		return statusMessage;
	}

	/**
	 * Sets the selection pagination id.
	 *
	 * @param lightRequest the new selection pagination id
	 */
	protected void setSelectionPaginationId(LightRequest lightRequest)
	{
		if (ValidationUtil.isNull(lightRequest.getSearchLight()))
		{
			lightRequest.setSearchLight(new SearchLight());
		}

		if (lightRequest.getLights().size() == 1)
		{
			lightRequest.setSelectionPaginationIds(Arrays.asList(lightRequest.getFirstLight().getId()));
			lightRequest.setPaginationAllSelected(false);
		}
	}

	/**
	 * Insert status message by light.
	 *
	 * @param request the request
	 * @param updateAnalytics the update analytics
	 * @param simpleNotification the simple notification
	 * @param statusesMessages the statuses messages
	 * @return the internal results response
	 */
	protected InternalResultsResponse<StatusMessage> insertStatusMessageByLight(
			AlarmNotificationRequest request,
			Boolean updateAnalytics,
			Boolean simpleNotification,
			List<StatusMessage> statusesMessages)
			{
		InternalResultsResponse<StatusMessage> internalResponse = new InternalResultsResponse<StatusMessage>();

		if (ValidationUtil.isNullOrEmpty(statusesMessages) || ValidationUtil.isNull(request.getLight())
				|| ValidationUtil.isNull(request.getLight().getId()))
		{
			internalResponse.setStatus(Status.PersistenceError);
			return internalResponse;
		}

		Light light = request.getLight();

		// Fetch all current alarm/warning messages for the current light.
		List<CurrentAlarmWarningMessage> currentAlarmWarningMessages =
				getSmartPointDAC().fetchCurrentAlarmStatusMessagesByLight(light.getId());

		// Delete all current alarm/warning messages on current_alarm_warning_message table for the current light.
		getSmartPointDAC().deleteCurrentAlarmWarningMessageByLightID(light.getId());

		List<CurrentAlarmWarningMessage> currentAlarmWarningMessagesUpdated =
				new ArrayList<CurrentAlarmWarningMessage>();

		for (StatusMessage statusMessage : statusesMessages)
		{
			currentAlarmWarningMessagesUpdated.addAll(
					insertStatusMessageNoAlarmWarning(
							request,
							internalResponse,
							currentAlarmWarningMessages,
							statusMessage,
							simpleNotification, updateAnalytics)
					);

			// Case not insert status message then is alarm or warning
			if (ValidationUtil.isNull(statusMessage.getId()))
			{
				currentAlarmWarningMessagesUpdated.addAll(
						insertStatusMessageAlarmWarning(request,
								internalResponse,
								currentAlarmWarningMessages,
								statusMessage,
								simpleNotification,
								updateAnalytics)
						);
			}

			// update current status message to light
			light.setCurrentStatusMessage(statusMessage);
			updateCurrentStatusMessage(request, internalResponse);

			internalResponse.addResult(statusMessage);
		}

		updateLightPropertyStatus(request, currentAlarmWarningMessagesUpdated);
		return internalResponse;
			}

	/**
	 * Update analytics alarm warning.
	 *
	 * @param request the request
	 * @param currentAlarmWarningMessages the current alarm warning messages
	 * @param updateAnalytics the update analytics
	 * @param operator the operator
	 */
	protected void updateAnalyticsAlarmWarning(AlarmNotificationRequest request,
			List<CurrentAlarmWarningMessage> currentAlarmWarningMessages, Boolean updateAnalytics, String operator)
	{
		if (ValidationUtil.isNullOrEmpty(currentAlarmWarningMessages) || !updateAnalytics)
		{
			return;
		}

		for (CurrentAlarmWarningMessage currentAlarmWarningMessage : currentAlarmWarningMessages)
		{
			this.updateAnalyticsAlarmWarning(request.getTenant(), request.getLight(),
					currentAlarmWarningMessage.getStatusMessageSubtypeValue(), request.getUserContext().getUserId(),
					operator);
		}
	}

	/**
	 * Update analytics alarm warning.
	 *
	 * @param tenant the tenant
	 * @param light the light
	 * @param statusExceptionTypeValue the status exception type value
	 * @param username the username
	 * @param operator the operator
	 */
	protected void updateAnalyticsAlarmWarning(Tenant tenant, Light light, Integer statusExceptionTypeValue,
			String username, String operator)
	{
		getSmartPointDAC().updateAnalyticsAlarmWarning(tenant.getId(), light.getId(),
				statusExceptionTypeValue, username, operator);
	}

	/**
	 * Update current status message.
	 *
	 * @param request the request
	 */
	protected void updateCurrentStatusMessage(AlarmNotificationRequest request, InternalResponse response)
	{
		LightRequest lightRequest = new LightRequest(request.getUserContext());
		lightRequest.addLight(request.getLight());
		response = getSmartPointDAC().updateCurrentStatusMessage(lightRequest);
		if (response.isInError())
		{
			response.setStatus(Status.PersistenceError);
		}
	}

	/**
	 * Insert status message no alarm warning.
	 *
	 * @param request the request
	 * @param response the response
	 * @param currentAlarmWarningMessages the current alarm warning messages
	 * @param statusMessage the status message
	 * @param simpleNotification the simple notification
	 * @param updateAnalytics the update analytics
	 */
	private List<CurrentAlarmWarningMessage> insertStatusMessageNoAlarmWarning(
			AlarmNotificationRequest request,
			InternalResultsResponse<StatusMessage> response,
			List<CurrentAlarmWarningMessage> currentAlarmWarningMessages,
			StatusMessage statusMessage, Boolean simpleNotification, Boolean updateAnalytics)
	{
		// Get all alarms/warnings that reached in from RNI.
		List<StatusException> statusExceptions = statusMessage.getStatusExceptions();

		List<CurrentAlarmWarningMessage> currentAlarmWarningMessagesUpdated =
				new ArrayList<CurrentAlarmWarningMessage>();
		if (!ValidationUtil.isNullOrEmpty(statusExceptions))
		{
			return currentAlarmWarningMessagesUpdated;
		}

		if (ValidationUtil.isNull(statusMessage.getStatusMessageCategoryEnum()))
		{
			statusMessage.setStatusMessageCategoryEnum(StatusMessageCategoryEnum.CLEAR_ALARM);
		}

		CurrentAlarmWarningMessage currentMessage =
				checkStatusMessageType(currentAlarmWarningMessages, statusMessage);
		if (!ValidationUtil.isNull(currentMessage))
		{
			currentMessage.setMessageDate(statusMessage.getDate());
			getSmartPointDAC().insertCurrentAlarmStatusMessage(currentMessage);
			statusMessage.setId(currentMessage.getStatusMessageId());
			return currentAlarmWarningMessagesUpdated;
		}

		Date messageDate = getNewDateUTC();
		Integer statusMessageId = statusMessage.getId();
		Integer tenantId = request.getTenant().getId();
		Integer lightId = request.getLight().getId();

		if (ValidationUtil.isNull(statusMessageId))
		{
			statusMessageId =
					getSmartPointDAC().insertStatusMessage(
							statusMessage, request.getUserContext().getUserId(), tenantId,
							lightId, simpleNotification);

			messageDate = statusMessage.getDate();
			statusMessage.setId(statusMessageId);
		}

		if (ValidationUtil.isNullOrZero(statusMessageId))
		{
			response.setStatus(Status.PersistenceError);
			return currentAlarmWarningMessagesUpdated;
		}

		currentMessage = insertCurrentAlarmStatusMessage(
				request.getLight(),
				statusMessage,
				messageDate,
				null,
				request.getUserContext());

		currentAlarmWarningMessagesUpdated.add(currentMessage);
		return currentAlarmWarningMessagesUpdated;
	}

	/**
	 * Insert status message alarm warning.
	 *
	 * @param request the request
	 * @param response the response
	 * @param currentAlarmWarningMessages the current alarm warning messages
	 * @param statusMessage the status message
	 * @param simpleNotification the simple notification
	 * @param updateAnalytics the update analytics
	 * @return the list
	 */
	private List<CurrentAlarmWarningMessage> insertStatusMessageAlarmWarning(AlarmNotificationRequest request,
			InternalResultsResponse<StatusMessage> response,
			List<CurrentAlarmWarningMessage> currentAlarmWarningMessages,
			StatusMessage statusMessage, Boolean simpleNotification, Boolean updateAnalytics)
	{
		// Get all alarms/warnings that reached in from RNI.
		List<StatusException> statusExceptions = statusMessage.getStatusExceptions();

		// If there is no alarms/warnings.
		List<CurrentAlarmWarningMessage> currentAlarmWarningMessagesUpdated =
				new ArrayList<CurrentAlarmWarningMessage>();
		if (ValidationUtil.isNullOrEmpty(statusExceptions))
		{
			return currentAlarmWarningMessagesUpdated;
		}

		for (StatusException statusException : statusExceptions)
		{
			CurrentAlarmWarningMessage currentAlarmWarningMessage =
					checkStatusExceptionTypeEnum(currentAlarmWarningMessages, statusException);

			if (!ValidationUtil.isNull(currentAlarmWarningMessage))
			{
				currentAlarmWarningMessage.setMessageDate(statusMessage.getDate());
				statusMessage.setId(currentAlarmWarningMessage.getStatusMessageId());

				getSmartPointDAC().insertCurrentAlarmStatusMessage(currentAlarmWarningMessage);
				currentAlarmWarningMessagesUpdated.add(currentAlarmWarningMessage);
				continue;
			}

			Integer idStatusMessage = statusMessage.getId();
			Integer tenantId = request.getTenant().getId();
			Integer lightId = request.getLight().getId();
			String userName = request.getUserContext().getUserId();

			// Inserting the status message (this condition guarantees that we insert it only once).
			if (ValidationUtil.isNull(idStatusMessage))
			{
				idStatusMessage =
						getSmartPointDAC().insertStatusMessage(statusMessage, userName, tenantId, lightId,
								simpleNotification);

				statusMessage.setId(idStatusMessage);
			}

			if (ValidationUtil.isNullOrZero(idStatusMessage))
			{
				response.setStatus(Status.PersistenceError);
				continue;
			}

			// fetch in status message sub-type.
			Integer responseSubtype =
					getSmartPointDAC().fetchStatusMessageStatusSubtype(
							idStatusMessage,
							statusException.getStatusExceptionTypeEnumValue(),
							userName);

			if (!ValidationUtil.isNullOrZero(responseSubtype))
			{
				response.setStatus(Status.PersistenceError);
				continue;
			}

			// Inserting the status message sub-type.
			getSmartPointDAC().insertStatusMessageStatusSubtype(
					idStatusMessage,
					statusException.getStatusExceptionTypeEnumValue(),
					userName,
					updateAnalytics);


			// Inserting the current alarm/warning message.
			currentAlarmWarningMessage = insertCurrentAlarmStatusMessage(
					request.getLight(),
					statusMessage,
					statusMessage.getDate(),
					statusException.getStatusExceptionTypeEnum(),
					request.getUserContext());

			currentAlarmWarningMessagesUpdated.add(currentAlarmWarningMessage);
		}

		return currentAlarmWarningMessagesUpdated;
	}

	/**
	 * Check status exception type enum.
	 *
	 * @param currentAlarmWarningMessages the current alarm warning messages
	 * @param statusException the status exception
	 * @return the current alarm warning message
	 */
	private CurrentAlarmWarningMessage checkStatusExceptionTypeEnum(
			List<CurrentAlarmWarningMessage> currentAlarmWarningMessages, StatusException statusException)
	{
		if (ValidationUtil.isNullOrEmpty(currentAlarmWarningMessages))
		{
			return null;
		}

		for (CurrentAlarmWarningMessage currentAlarmWarningMessage : currentAlarmWarningMessages)
		{
			if (statusException.getStatusExceptionTypeEnum() == currentAlarmWarningMessage.getStatusMessageSubtype())
			{
				return currentAlarmWarningMessage;
			}
		}

		return null;
	}

	/**
	 * Check status message type.
	 *
	 * @param currentAlarmWarningMessages the current alarm warning messages
	 * @param statusMessage the status message
	 * @return the current alarm warning message
	 */
	private CurrentAlarmWarningMessage checkStatusMessageType(
			List<CurrentAlarmWarningMessage> currentAlarmWarningMessages,
			StatusMessage statusMessage)
	{
		if (ValidationUtil.isNullOrEmpty(currentAlarmWarningMessages))
		{
			return null;
		}

		for (CurrentAlarmWarningMessage current : currentAlarmWarningMessages)
		{
			if (current.getStatusMessage() == statusMessage.getLightStatusEnum())
			{
				return current;
			}
		}
		return null;
	}

	/**
	 * Insert current alarm status message.
	 *
	 * @param light the light
	 * @param statusMessage the status message
	 * @param messageDate the message date
	 * @param statusException the status exception
	 * @param userContext the user context
	 * @return the current alarm warning message
	 */
	private CurrentAlarmWarningMessage insertCurrentAlarmStatusMessage(
			Light light,
			StatusMessage statusMessage,
			Date messageDate,
			StatusExceptionTypeEnum statusException,
			UserContext userContext)
	{
		CurrentAlarmWarningMessage currentAlarmWarningMessage =
				new CurrentAlarmWarningMessage(
						light.getId(),
						statusMessage.getId(),
						statusMessage.getStatusMessageCategoryEnum(),
						statusMessage.getLightStatusEnum(),
						statusException,
						messageDate,
						userContext.getTenant().getId());

		getSmartPointDAC().insertCurrentAlarmStatusMessage(currentAlarmWarningMessage);
		return currentAlarmWarningMessage;
	}

	/**
	 * Update light property status.
	 *
	 * @param request the request
	 * @param currentAlarmWarningMessagesUpdated the current alarm warning messages updated
	 */
	private void updateLightPropertyStatus(
			AlarmNotificationRequest request,
			List<CurrentAlarmWarningMessage> currentAlarmWarningMessagesUpdated
			)
	{
		if (ValidationUtil.isNullOrEmpty(currentAlarmWarningMessagesUpdated))
		{
			return;
		}

		String currentLightStatus = null;
		String currentLightAlarmWarning = null;
		int lastStatusMessageId = 0;

		for (CurrentAlarmWarningMessage currentMessage : currentAlarmWarningMessagesUpdated)
		{
			if (!ValidationUtil.isNull(currentMessage.getStatusMessageId())
					&& (currentMessage.getStatusMessageId() > lastStatusMessageId))
			{
				currentLightStatus = String.valueOf(currentMessage.getStatusMessageValue());
				currentLightAlarmWarning = String.valueOf(currentMessage.getStatusMessageSubtypeValue());
				lastStatusMessageId = currentMessage.getStatusMessageId();
			}
		}

		List<LightParameter> lightParameters = new ArrayList<LightParameter>();
		lightParameters.add(new LightParameter(PropertyEnum.CURRENT_LIGHT_STATUS, currentLightStatus));
		lightParameters.add(new LightParameter(
				PropertyEnum.CURRENT_ALARM_WARNING_STATUS_SUBTYPE,
				currentLightAlarmWarning));


	}
}
