package com.sensus.lc.light.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.common.validation.ValidationUtil.isNullOrZero;
import static com.sensus.lc.base.util.LCPropertiesExtractorUtil.extractLightId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Location;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.bcl.IAnalyticsBCL;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.AnalyticsRequest.OperatorEnum;
import com.sensus.lc.base.util.LCConvertUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.base.util.LCHelpGeoCoding;
import com.sensus.lc.ecomode.bcl.IEcoModeBCL;
import com.sensus.lc.ecomode.model.EcoModeBaseline;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.bcl.ILightNotificationHistoryBCL;
import com.sensus.lc.light.bcl.ILightProcessorBCL;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.BlinkStatusEnum;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.DeleteLightReferenceEnum;
import com.sensus.lc.light.model.GetDataFromLightEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightDetailDataTypeEnum;
import com.sensus.lc.light.model.LightStateEnum;
import com.sensus.lc.light.model.LightTypeEnum;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.light.model.OperationalData;
import com.sensus.lc.light.model.PartNumberConfiguration;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.CommunicationFailureRequest;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.request.OperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.SetupDimmingConfigurationRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.tag.bcl.ITagBCL;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.TagRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The ILightProcessorBCL implementation, light business area.
 * 
 * @author Thiago - QAT
 */
public class LightProcessorBCLImpl implements ILightProcessorBCL
{
	private static final Logger LOG = LoggerFactory.getLogger(LightProcessorBCLImpl.class);

	/**
	 * DAC.
	 */
	private ILightDAC lightDAC;
	private INotificationHistoryDAC notificationHistoryDAC;

	/**
	 * BCLS.
	 */
	private IEcoModeBCL ecoModeBCL;
	private ITagBCL tagBCL;
	private IProcessBCL processBCL;
	private ILightBCL lightBCL;
	private ILightNotificationHistoryBCL notificationHistoryBCL;
	private IAnalyticsBCL analyticsBCL;

	/**
	 * Another attributes.
	 */
	private Integer calculateCommFailPageSize;

	/**
	 * Address Constants.
	 */
	private static final String STREET = "Street";
	private static final String CITY = "City";
	private static final String COUNTY = "County";
	private static final String STATE = "State";
	private static final String ZIP = "Zip";

	/**
	 * GeoCoding Attributes.
	 */
	private LCHelpGeoCoding lcHelpGeoCoding; // injected by Spring through setter

	/**
	 * Number Constants.
	 */
	private static final Integer ONE_HUNDRED = 100;

	/**
	 * Error Constants.
	 */
	private static final String REVERSE_LOOKUP_FAILED = "sensus.mlc.lightbclimpl.reverse.lookup.failed";
	private static final String ADD_PROPERTY_VALUE_FAILED = "sensus.mlc.lightbclipml.add.property.value.failed";
	private static final String ADD_TAG_FAILED = "sensus.mlc.lightbclimpl.add.tag.failed";
	private static final String LIGHT_ALREADY_IN_TAG = "sensus.mlc.tagvalidator.light.already.exist";
	private static final String ADD_LIGHT_TO_TAG_FAILED = "sensus.mlc.lightbclimpl.add.light.to.tag.failed";
	private static final String INCOMPLETE_DATA = "Incomplete Data";
	private static final String LIGHT_STATUS_ALREADY_ACTIVE = "sensus.mlc.mlc_action.light_status.already.active";

	/**
	 * Another Constants.
	 */
	private static final String ACTIVE = "Active";
	private static final String DEACTIVATED = "Deactivated";
	private static final String MAINTENANCE = "Maintenance";

	/**
	 * @return the lightDAC
	 */
	public ILightDAC getLightDAC()
	{
		return lightDAC;
	}

	/**
	 * @param lightDAC the lightDAC to set
	 */
	public void setLightDAC(ILightDAC lightDAC)
	{
		this.lightDAC = lightDAC;
	}

	/**
	 * @return the notificationHistoryDAC
	 */
	public INotificationHistoryDAC getNotificationHistoryDAC()
	{
		return notificationHistoryDAC;
	}

	/**
	 * @param notificationHistoryDAC the notificationHistoryDAC to set
	 */
	public void setNotificationHistoryDAC(INotificationHistoryDAC notificationHistoryDAC)
	{
		this.notificationHistoryDAC = notificationHistoryDAC;
	}

	/**
	 * @return the ecoModeBCL
	 */
	public IEcoModeBCL getEcoModeBCL()
	{
		return ecoModeBCL;
	}

	/**
	 * @param ecoModeBCL the ecoModeBCL to set
	 */
	public void setEcoModeBCL(IEcoModeBCL ecoModeBCL)
	{
		this.ecoModeBCL = ecoModeBCL;
	}

	/**
	 * @return the lightBCL
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * @param lightBCL the lightBCL to set
	 */
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * @return the tagBCL
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * @param tagBCL the tagBCL to set
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * @return the processBCL
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * @param processBCL the processBCL to set
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * @return the notificationHistoryBCL
	 */
	public ILightNotificationHistoryBCL getNotificationHistoryBCL()
	{
		return notificationHistoryBCL;
	}

	/**
	 * @param notificationHistoryBCL the notificationHistoryBCL to set
	 */
	public void setNotificationHistoryBCL(ILightNotificationHistoryBCL notificationHistoryBCL)
	{
		this.notificationHistoryBCL = notificationHistoryBCL;
	}

	/**
	 * Gets the analytics bcl.
	 * 
	 * @return the analytics bcl
	 */
	public IAnalyticsBCL getAnalyticsBCL()
	{
		return analyticsBCL;
	}

	/**
	 * Sets the analytics bcl.
	 * 
	 * @param analyticsBCL the new analytics bcl
	 */
	public void setAnalyticsBCL(IAnalyticsBCL analyticsBCL)
	{
		this.analyticsBCL = analyticsBCL;
	}

	/**
	 * Gets the calculate comm fail page size.
	 * 
	 * @return the calculate comm fail page size
	 */
	public Integer getCalculateCommFailPageSize()
	{
		return calculateCommFailPageSize;
	}

	/**
	 * Sets the calculate comm fail page size.
	 * 
	 * @param calculateCommFailPageSize the new calculate comm fail page size
	 */
	public void setCalculateCommFailPageSize(Integer calculateCommFailPageSize)
	{
		this.calculateCommFailPageSize = calculateCommFailPageSize;
	}

	/**
	 * Gets the lc help geo coding.
	 * 
	 * @return the lc help geo coding
	 */
	public LCHelpGeoCoding getLcHelpGeoCoding()
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processLightStatusNotification(com.sensus.mlc.light.model.request
	 * .NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> processLightStatusNotification(
			LightStatusNotificationRequest request)
	{
		NotificationTypeEnum type = NotificationTypeEnum.UNSOLICITED_STATUS;
		if (request.isForced())
		{
			type = NotificationTypeEnum.FORCED_STATUS;
		}

		// Insert notification history for this light
		Light light = request.getLight();
		InternalResultsResponse<NotificationHistory> response =
				prepareAndUpsertNotificationHistory(
						light,
						request.getLifeCycleState(),
						type,
						request.getUserContext(),
						createAlertClassificationsToInsert(request.getAlertSubTypes()),
						request.getTransactionId(),
						request.getMessageDate());

		if (response.isInError())
		{
			return response;
		}

		// Process light schedule and last operational data informations
		InternalResponse appCode90Response = processAppCode90(request, response.getFirstResult());
		if (appCode90Response.isInError())
		{
			response.merge(appCode90Response);
			return response;
		}

		// Process light configurations and setup dimming configuration
		InternalResponse appCode85Response = processAppCode85(request, response.getFirstResult());
		if (appCode85Response.isInError())
		{
			response.merge(appCode85Response);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processAlarmWarningNotification(com.sensus.mlc.light.model.request
	 * .AlarmNotificationRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		InternalResultsResponse<NotificationHistory> historyResponse =
				new InternalResultsResponse<NotificationHistory>();
		Light light = request.getLight();

		// Get latest notification history for this light
		NotificationHistory currentNotification = light.getLastNotificationHistory();

		// There is a notification whose type is alarm, test the alert types to update or create a new one.
		if (isNull(currentNotification)
				|| NotificationTypeEnum.ALARM != currentNotification.getNotificationType()
				// Set 'Active' to light
				|| NotificationTypeEnum.ALARM == currentNotification.getNotificationType()
				&& !isNullOrEmpty(currentNotification.getAlertClassifications())
				&& isNullOrEmpty(request.getAlertSubTypes()))
		{
			// Notification history not found or the last notification history isn't alarm, should persist a new one.
			historyResponse = prepareAndUpsertNotificationHistory(
					light,
					request.getLifeCycleState(),
					NotificationTypeEnum.ALARM,
					request.getUserContext(),
					createAlertClassificationsToInsert(request.getAlertSubTypes()),
					null,
					request.getMessageDate());

			if (historyResponse.isInError())
			{
				return historyResponse;
			}

			// Update current and voltage
			InternalResponse maintenanceResponse = processAppCode90(request, historyResponse.getFirstResult());
			return handleNotificationHistoryResponseError(historyResponse, maintenanceResponse);
		}

		// Iterates all alerts to verify if exists, case not, then insert
		NotificationHistoryRequest classificationRequest = new NotificationHistoryRequest();
		classificationRequest.setUserContext(request.getUserContext());
		classificationRequest.setNotificationHistoryCriteria(new NotificationHistoryCriteria());
		classificationRequest.getNotificationHistoryCriteria().setNotificationHistoryId(currentNotification.getId());
		InternalResultsResponse<AlertClassification> classificationResponse =
				getNotificationHistoryDAC().fetchNotificationHistoryAlertById(classificationRequest);

		List<AlertClassification> currentClassifications = new ArrayList<AlertClassification>();
		Date date = request.getMessageDate();
		for (AlertSubTypeEnum alertSubType : request.getAlertSubTypes())
		{
			AlertClassification classification = new AlertClassification();
			classification.setNotificationHistoryId(currentNotification.getId());
			classification.setAlertType(alertSubType.getAlertType());
			classification.setAlertSubType(alertSubType);
			currentClassifications.add(classification);

			if (checkNoiseAlert(
					historyResponse,
					currentNotification,
					classificationResponse.getResultsList(),
					alertSubType,
					date,
					request.getUserContext()))
			{
				continue;
			}

			// Case occurred some problem with check noise alert
			if (historyResponse.isInError())
			{
				return historyResponse;
			}

			// The alert that arrived doesn't exists, should be persisted.
			classification.setCreateDate(date);
			classification.setUpdateDate(date);
			classification.setMessageDate(date);
			classification.setCreateUser(request.getUserContext().getUserId());
			InternalResultsResponse<AlertClassification> alertResponse =
					getNotificationHistoryDAC().insertAlertClassification(
							new AlertClassificationMaintenanceRequest(classification));

			if (alertResponse.isInError())
			{
				return handleNotificationHistoryResponseError(historyResponse, alertResponse);
			}

			// Update analytics data
			prepareAndUpdateAnalytics(
					currentNotification,
					alertSubType,
					OperatorEnum.PLUS,
					request.getUserContext(),
					historyResponse);

			if (historyResponse.isInError())
			{
				return historyResponse;
			}
		}

		// Update notification history.
		currentNotification.setAlertClassifications(currentClassifications);
		prepareAndUpdateNotificationHistory(date, currentNotification, historyResponse);

		currentNotification.setUpdateDate(date);
		InternalResponse historyUpdate = getNotificationHistoryBCL().updateNotificationHistory(
				new NotificationHistoryMaintenanceRequest(currentNotification));

		if (historyUpdate.isInError())
		{
			return handleNotificationHistoryResponseError(historyResponse, historyUpdate);
		}

		// Update current and voltage
		InternalResponse maintenanceResponse = processAppCode90(request, currentNotification);
		return handleNotificationHistoryResponseError(historyResponse, maintenanceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processLightSetupNotification(com.sensus.mlc.light.model.request.
	 * LightSetupNotificationRequest)
	 */
	@Override
	public InternalResponse processLightSetupNotification(LightSetupNotificationRequest request)
	{
		InternalResponse response = new InternalResponse();

		// Insert the notification history.
		InternalResultsResponse<NotificationHistory> historyInsertResponse =
				prepareAndUpsertNotificationHistory(
						request.getLight(),
						request.getLight().getLifeCycleState(),
						NotificationTypeEnum.SETUP,
						request.getUserContext(),
						null,
						null,
						request.getMessageDate());

		if (historyInsertResponse.isInError())
		{
			response.setStatus(historyInsertResponse.getStatus());
			response.getMessageInfoList().addAll(historyInsertResponse.getMessageInfoList());
			return response;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightProcessorBCL#processChannelSetupAudit(com.sensus.mlc.light.model.request.
	 * ChannelSetupAuditRequest)
	 */
	@Override
	public InternalResponse processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		InternalResponse response = new InternalResponse();
		Light light = request.getLight();

		// Avoid noise
		// Insert notification history
		response = prepareAndUpsertNotificationHistory(
				request.getLight(),
				request.getLifeCycleState(),
				NotificationTypeEnum.CHANNEL_SETUP_AUDIT,
				request.getUserContext(),
				null,
				null,
				request.getMessageDate());

		if (response.isInError())
		{
			return response;
		}

		// Update life cycle state to light
		light.setLifeCycleState(request.getLifeCycleState());
		updateLight(light, request.getUserContext(), response);
		if (response.isInError() || LifeCycleStateEnum.DEACTIVATED != request.getLifeCycleState())
		{
			return response;
		}

		// If moving light to IDLE, need to clear all Tags/Groups/Schedules for that light and update.
		LightDeleteRequest deleteRequest = new LightDeleteRequest(request.getLight());
		deleteRequest.setUserContext(request.getUserContext());
		deleteRequest.setDeleteLightReference(DeleteLightReferenceEnum.DELETE_LIGHT_REFERENCES);
		InternalResponse deleteResponse = getLightBCL().deleteLightReferences(deleteRequest);
		if (deleteResponse.isInError())
		{
			response.setStatus(deleteResponse.getStatus());
			response.getMessageInfoList().addAll(deleteResponse.getMessageInfoList());
			return response;
		}

		// Update light state to OFF.
		light.setDeviceLifeCycleState(LightStateEnum.OFF);
		updateLight(light, request.getUserContext(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processLightBindingNotification(com.sensus.mlc.light.model.request
	 * .LightStatusNotificationRequest)
	 */
	@Override
	public InternalResponse processLightBindingNotification(LightStatusNotificationRequest request)
	{
		// Fetch light or Add new one
		UserContext userContext = request.getUserContext();
		Light light = request.getLight();
		InternalResultsResponse<Light> guaranteeResponse = guaranteeLightExistence(request.getLight(), userContext);
		if (guaranteeResponse.isInError())
		{
			return guaranteeResponse;
		}

		// Return light
		light = guaranteeResponse.getFirstResult();

		// Insert notification history
		return prepareAndUpsertNotificationHistory(
				light,
				light.getLifeCycleState(),
				NotificationTypeEnum.BINDING,
				userContext,
				null,
				null,
				request.getMessageDate());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processLightBindingNotification(com.sensus.mlc.light.model.request
	 * .LightIntensityRequest)
	 */
	@Override
	public InternalResponse processSetLightIntensityNotification(LightIntensityRequest request)
	{
		InternalResponse response = new InternalResponse();

		/*
		 * For each light
		 * process the message, insert the notification history and update the light state.
		 */
		UserContext userContext = request.getUserContext();
		for (Light light : request.getLightList())
		{
			InternalResponse historyResponse = processNotificationHistoryToLightIntensity(request, light);
			if (historyResponse.isInError())
			{
				response.getMessageInfoList().addAll(historyResponse.getMessageInfoList());
				continue;
			}

			// Process update light
			updateLight(light, userContext, response);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processUpdateLightStatus(com.sensus.mlc.light.model.request.LightRequest
	 * )
	 */
	@Override
	public InternalResponse processUpdateLightStatusNotification(UpdateLightNotificationRequest request)
	{
		InternalResponse response = new InternalResponse();
		UserContext userContext = request.getUserContext();

		for (Light light : request.getLightList())
		{
			if (!isNull(light.getProtect()) && light.getProtect())
			{
				continue;
			}

			// Process update light
			LifeCycleStateEnum state = request.getLifeCycleState();
			light.setLifeCycleState(state);
			updateLight(light, userContext, response);

			// Insert notification history for received light state.
			InternalResultsResponse<NotificationHistory> historyResponse =
					prepareAndUpsertNotificationHistory(
							light,
							state,
							NotificationTypeEnum.EDIT_STATUS,
							request.getUserContext(),
							null,
							null,
							request.getMessageDate());

			if (historyResponse.isInError())
			{
				response.setStatus(historyResponse.getStatus());
				response.getMessageInfoList().addAll(historyResponse.getMessageInfoList());
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processApplySmartpointPropertiesNotification(com.sensus.mlc.light
	 * .model.request.ProcessLightsRequest)
	 */
	@Override
	public InternalResponse processApplySmartpointPropertiesNotification(ProcessLightsRequest request)
	{
		InternalResponse response = new InternalResponse();
		Light light = request.getFirstLight();

		Double latitude = light.getRadio().getLocation().getLatitude();
		Double longitude = light.getRadio().getLocation().getLongitude();

		// Verify if Lat/Long need to be updated
		UserContext userContext = request.getUserContext();
		if (!isNull(latitude) && !isNull(longitude))
		{
			response = guaranteeLightExistence(light, userContext);
		}

		if (response.isInError())
		{
			return response;
		}

		// Insert notification history
		response = prepareAndUpsertNotificationHistory(
				light,
				light.getLifeCycleState(),
				NotificationTypeEnum.UPD_LIGHT_PROPERTY,
				userContext,
				null,
				null,
				request.getMessageDate());

		if (response.isInError())
		{
			return response;
		}

		// Process update light
		updateLight(light, userContext, response);

		// Update Eco-Mode data
		upsertEcoModeFromLight(userContext, light, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#initiateGetLightStatus(com.sensus.mlc.light.model.request.LightRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Process> initiateGetLightStatus(LightRequest lightRequest)
	{
		// Populate LCActionParameters according to the level of detail requested in lightRequest (STATUS and/or CONFIG)
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();

		actionParameters.add(new LCActionParameter(PropertyEnum.LIGHT_DETAIL_TYPE, String
				.valueOf(LightDetailDataTypeEnum.STATUS.getValue())));

		if (!isNull(lightRequest.getActionCriteria())
				&& GetDataFromLightEnum.ALL.equals(lightRequest.getActionCriteria().getGetDataFromLightEnum()))
		{
			actionParameters.add(new LCActionParameter(PropertyEnum.LIGHT_DETAIL_TYPE, String
					.valueOf(LightDetailDataTypeEnum.CONFIGURATION.getValue())));
		}

		// Recreate Light list in case of Quick Link select
		LCAction action = new LCAction(LCActionTypeEnum.GET_LIGHT_STATUS);
		action.setActionParameters(actionParameters);

		ProcessRequest processRequest = prepareProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#initiateSetupDimmingConfiguration(com.sensus.mlc.light.model.request
	 * .SetupDimmingConfigurationRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateSetupDimmingConfiguration(SetupDimmingConfigurationRequest request)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		Light light = request.getLight();
		Configuration configuration = light.getConfiguration();

		// Case light is not dimmable then return
		if (isNull(configuration.getDimmable())
				|| !configuration.getDimmable()
				|| isNull(configuration.getModelNumber()))
		{
			return response;
		}

		// Get sensus part number configurations to Light
		InternalResultsResponse<PartNumberConfiguration> partNumberResponse =
				getLightBCL().fetchPartNumberConfigurationsByModelNumber(
						configuration.getModelNumber());
		if (partNumberResponse.isInError())
		{
			response.setStatus(partNumberResponse.getStatus());
			response.addMessages(partNumberResponse.getMessageInfoList());
			return response;
		}

		// Configure attributes to submit process
		List<PartNumberConfiguration> lightConfigurations = partNumberResponse.getResultsList();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		String dimOnDelay = String.valueOf(lightConfigurations.get(0).getDimOnDelay());
		LCActionParameter actionParameter = new LCActionParameter(PropertyEnum.DIM_ON_DELAY, dimOnDelay);
		actionParameters.add(actionParameter);
		setActionParametersToDimmingConfiguration(lightConfigurations, actionParameters);

		LCAction action = new LCAction(LCActionTypeEnum.SETUP_DIMMING_CONFIGURATION);
		action.setActionParameters(actionParameters);

		ProcessRequest processRequest = new ProcessRequest(request.getUserContext());
		processRequest.setLightCriteria(new LightCriteria());
		processRequest.getLightCriteria().getLightIdList().add(light.getId());

		// Submit command to gateway for apply part number configurations
		InternalResultsResponse<Process> processResponse = getProcessBCL().submitProcess(processRequest, action);
		response.addResults(processResponse.getResultsList());
		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightProcessorBCL#processClearAlarmNotification(com.sensus.mlc.light.model.request.
	 * AlarmNotificationRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> processClearAlarmNotification(AlarmNotificationRequest request)
	{
		Light light = request.getLight();

		// If not exist alerts subtypes then this is a CLEAR ALL ALARMS action
		if (isNullOrEmpty(request.getAlertSubTypes()))
		{
			return prepareAndUpsertNotificationHistory(
					light, LifeCycleStateEnum.ACTIVE,
					NotificationTypeEnum.CLEAR_ALARM,
					request.getUserContext(),
					null,
					null,
					request.getMessageDate());
		}

		return prepareAndUpsertNotificationHistory(
				light,
				LifeCycleStateEnum.ACTIVE,
				NotificationTypeEnum.CLEAR_ALARM,
				request.getUserContext(),
				createAlertClassificationsToInsert(request.getAlertSubTypes()),
				null,
				request.getMessageDate());
	}

	@Override
	public InternalResultsResponse<Process> initiateUpdateLightIntensity(LightRequest request)
	{
		LCActionParameter actionParameter = new LCActionParameter();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		ActionCriteria actionCriteria = request.getActionCriteria();

		actionParameter = new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, null);
		if (!isNull(actionCriteria.getPercentage()))
		{
			String percentage = String.valueOf(actionCriteria.getPercentage());
			actionParameter = new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, percentage);
		}
		actionParameters.add(actionParameter);

		String lightBlink = String.valueOf(actionCriteria.getBlinkStatus().getValue());
		actionParameter = new LCActionParameter(PropertyEnum.LIGHT_BLINK, lightBlink);
		actionParameters.add(actionParameter);

		if (!isNull(actionCriteria.getOverride()))
		{
			String override = String.valueOf(actionCriteria.getOverride().getValue());
			actionParameter = new LCActionParameter(PropertyEnum.OVERRIDE, override);
			actionParameters.add(actionParameter);
		}

		if (!isNull(actionCriteria.getOverridePerDate()))
		{
			String overridePerDate = String.valueOf(actionCriteria.getOverridePerDate());
			actionParameter = new LCActionParameter(PropertyEnum.OVERRIDE_PER_DATE, overridePerDate);
			actionParameters.add(actionParameter);
		}

		LCAction action = new LCAction(LCActionTypeEnum.DIM);
		action.setActionParameters(actionParameters);

		if (actionCriteria.getBlinkStatus() == BlinkStatusEnum.SLOW
				|| actionCriteria.getBlinkStatus() == BlinkStatusEnum.FAST)
		{
			action.setActionType(LCActionTypeEnum.SET_BLINK_BY_LIGHT);

		}
		else if (actionCriteria.getIsClearOverride())
		{
			action.setActionType(LCActionTypeEnum.SET_CLEAR_OVERRIDE_BY_LIGHT);
		}
		else if (ONE_HUNDRED.equals(actionCriteria.getPercentage()))
		{
			action.setActionType(LCActionTypeEnum.TURN_ON);
		}
		else if (NumberUtils.INTEGER_ZERO.equals(actionCriteria.getPercentage()))
		{
			action.setActionType(LCActionTypeEnum.TURN_OFF);
		}

		// create a Process
		ProcessRequest processRequest = prepareProcessRequest(request, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	@Override
	public InternalResultsResponse<Process> initiateDeleteAlert(LightRequest request)
	{
		List<Light> lightsActive = getLightsNotAlerts(request);
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		if (!isNullOrEmpty(lightsActive))
		{
			request.getLightCriteria().setNotInlightIdList(extractLightId(lightsActive));
			response.addMessage(LIGHT_STATUS_ALREADY_ACTIVE, MessageSeverity.Info, MessageLevel.None,
					new Object[] {lightsActive.size()});
		}

		LCAction action = new LCAction(LCActionTypeEnum.CLEAR_ALL_ALARMS);
		setActionParametersByStatusMessage(request, action);

		ProcessRequest processRequest = prepareProcessRequest(request, ProcessItemStatusEnum.PENDING);
		InternalResultsResponse<Process> processResponse = getProcessBCL().submitProcess(processRequest, action);
		response.addResults(processResponse.getResultsList());

		if (processResponse.isInError())
		{
			response.addMessages(processResponse.getMessageInfoList());
			response.setStatus(processResponse.getStatus());
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Process> initiateUpsertLightPoleId(LightMaintenanceRequest request)
	{
		LCAction action = new LCAction(LCActionTypeEnum.UPDATE_LIGHT_POLE_ID);
		action.addActionParameter(new LCActionParameter(PropertyEnum.POLE_ID, request.getLight().getPoleId()));
		ProcessRequest processRequest = prepareProcessRequest(request, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	@Override
	public InternalResultsResponse<Process> initiateUpsertLightLatLng(LightMaintenanceRequest request)
	{
		Location location = request.getLight().getRadio().getLocation();
		LCAction action = new LCAction(LCActionTypeEnum.UPDATE_LIGHT_LAT_LONG);
		action.addActionParameter(new LCActionParameter(PropertyEnum.LATITUDE, String.valueOf(location.getLatitude())));
		action.addActionParameter(new LCActionParameter(PropertyEnum.LONGITUDE, String.valueOf(location.getLongitude())));
		ProcessRequest processRequest = prepareProcessRequest(request, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	@Override
	public InternalResultsResponse<Process> initiateUpdateLightStatus(LightMaintenanceRequest request)
	{
		Light light = request.getLight();
		LifeCycleStateEnum state = light.getLifeCycleState();
		LCAction action = new LCAction(LCActionTypeEnum.UPDATE_LIGHT_STATUS);
		action.setActionParameters(setLCActionParameter(state));

		if (state == LifeCycleStateEnum.DEACTIVATED)
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			updateLight(light, request.getUserContext(), response);
			if (response.isInError())
			{
				return response;
			}

			InternalResultsResponse<NotificationHistory> responseNotif = prepareAndUpsertNotificationHistory(
					light,
					state,
					NotificationTypeEnum.EDIT_STATUS,
					request.getUserContext(),
					null,
					null,
					LCDateUtil.getNewDateUTC());

			if (responseNotif.isInError())
			{
				response.merge(responseNotif);
				return response;
			}
		}

		ProcessRequest processRequest = prepareProcessRequest(request, ProcessItemStatusEnum.PENDING);
		return getProcessBCL().submitProcess(processRequest, action);
	}

	@Override
	public void verifyCommunicationFailure(UserContext userContext)
	{
		// Fetch light to apply communication failure
		Tenant tenant = userContext.<Tenant> getTenant();
		CommunicationFailureRequest request = new CommunicationFailureRequest(tenant);
		request.setPageSize(getCalculateCommFailPageSize());

		// Priming read
		InternalResultsResponse<Integer> response = getLightDAC().fetchLightsToAddCommunicationFailure(request);
		if (response.isInError() || response.getFirstResult().intValue() == NumberUtils.INTEGER_ZERO.intValue())
		{
			LOG.info(String.format(
					" ********** Tenant %s does not contains lights to apply communication failure."
					, tenant.getRniCode()));
			return;
		}

		// Do while more data
		do
		{
			// Process the data
			processCommunicationFailure(userContext, response);

			// Get the next page of data.
			response = getLightDAC().fetchLightsToAddCommunicationFailure(request);
			if (response.isInError())
			{
				LOG.error(String.format(
						" ********** Error to apply communication failure to lights on Tenant %s. Messages: %s",
						tenant.getRniCode(), response.getMessageInfoList()));
			}

		} while (response.getResultsList().size() != 0);
	}

	@Override
	public void insertConsumptionToLightInCommunicationFailure(UserContext userContext)
	{
		// fetch lights in communication failure
		Tenant tenant = userContext.<Tenant> getTenant();
		CommunicationFailureRequest request = new CommunicationFailureRequest(tenant);
		request.setPageSize(getCalculateCommFailPageSize());

		InternalResultsResponse<Integer> response = getLightDAC().fetchLightsInCommunicationFailure(request);

		if (response.isInError() || response.getFirstResult().intValue() == NumberUtils.INTEGER_ZERO.intValue())
		{
			LOG.info(String.format(
					" ********** Tenant %s does not contains lights in communication failure."
					, tenant.getRniCode()));
			return;
		}
		// Do while more data
		do
		{
			// Process the data
			processInsertConsumptionToLightsInCommunicationFailure(request, response);

			// Get the next page of data.
			response = getLightDAC().fetchLightsInCommunicationFailure(request);
			if (response.isInError() && !Status.NoRowsFoundError.equals(response.getStatus()))
			{
				LOG.error(String
						.format(" ********** Error to insert consumption to lights in communication failure on Tenant %s. Messages: %s",
								tenant.getRniCode(), response.getMessageInfoList()));
			}

		} while (response.getResultsList().size() != 0);

	}

	/**
	 * Process insert consumption to lights in communication failure.
	 * 
	 * @param request the request
	 * @param response the response
	 */
	private void processInsertConsumptionToLightsInCommunicationFailure(CommunicationFailureRequest request,
			InternalResultsResponse<Integer> response)
	{
		request.setLightIds(LCConvertUtil.getIdsToString(response.getResultsList(), ","));
		InternalResponse internalResponse = getLightDAC().calculateLightConsumptionInCommunicationFailure(request);
		if (internalResponse.isInError())
		{
			LOG.warn(String.format(
					" ********** Error to calculate light consumption on Tenant %s. Messages: %s"
					, request.getTenant().getId()
					, internalResponse.getMessageInfoList()));
			return;
		}
		request.setStartRow(request.getPageSize());
		request.setPageSize(request.getPageSize() + getCalculateCommFailPageSize());
	}

	/**
	 * Process notification history to light intensity.
	 * 
	 * @param request the request
	 * @param light the light
	 * @return the internal results response
	 */
	private InternalResponse processNotificationHistoryToLightIntensity(
			LightIntensityRequest request, Light light)
	{
		if (light.getBlinkStatus().equals(BlinkStatusEnum.SLOW) || light.getBlinkStatus().equals(BlinkStatusEnum.FAST))
		{
			return prepareAndUpsertNotificationHistory(
					light,
					light.getLifeCycleState(),
					NotificationTypeEnum.SET_LIGHT_BLINK,
					request.getUserContext(),
					null,
					null,
					request.getMessageDate());
		}

		return prepareAndUpsertNotificationHistory(
				light,
				light.getLifeCycleState(),
				NotificationTypeEnum.SET_LIGHT_INTENSITY,
				request.getUserContext(),
				null,
				null,
				request.getMessageDate());
	}

	/**
	 * Prepare and update notification history.
	 * 
	 * @param updateDate the update date
	 * @param currentNotification the current notification
	 * @param response the response
	 */
	private void prepareAndUpdateNotificationHistory(
			Date updateDate,
			NotificationHistory currentNotification,
			InternalResponse response)
	{
		// Update notification history.
		currentNotification.setUpdateDate(updateDate);
		InternalResponse historyUpdate = getNotificationHistoryBCL().updateNotificationHistory(
				new NotificationHistoryMaintenanceRequest(currentNotification));

		if (historyUpdate.isInError())
		{
			handleError(response, historyUpdate);
		}
	}

	/**
	 * Process communication failure.
	 * 
	 * @param userContext the user context
	 * @param request the request
	 */
	private void processCommunicationFailure(UserContext userContext, InternalResultsResponse<Integer> response)
	{
		// Create alert of communication failure type
		Date messageDate = LCDateUtil.getNewDateUTC();
		AlertSubTypeEnum communicationFail = AlertSubTypeEnum.COMMUNICATION_FAIL;
		AlertClassification classification = new AlertClassification();
		classification.setAlertType(communicationFail.getAlertType());
		classification.setAlertSubType(communicationFail);
		classification.setCreateDate(messageDate);
		classification.setUpdateDate(messageDate);
		classification.setMessageDate(messageDate);
		classification.setCreateUser(userContext.getUserId());
		for (Integer lightId : response.getResultsList())
		{
			// Insert notification history
			InternalResultsResponse<NotificationHistory> notificationResponse =
					prepareAndUpsertNotificationHistory(
							new Light(lightId),
							LifeCycleStateEnum.ACTIVE,
							NotificationTypeEnum.ALARM,
							userContext,
							Arrays.asList(classification),
							null,
							messageDate);

			if (notificationResponse.isInError())
			{
				LOG.warn(String.format(
						" ********** Error to create notification history to light %s on Tenant %s. Messages: %s"
						, lightId
						, userContext.<Tenant> getTenant().getRniCode()
						, notificationResponse.getMessageInfoList()));
			}
		}
	}

	/**
	 * Update light.
	 * 
	 * @param light the light
	 * @param userContext the user context
	 * @param response the response
	 */
	private void updateLight(Light light, UserContext userContext, InternalResponse response)
	{
		light.setModelAction(PersistanceActionEnum.UPDATE);
		light.setModifyUser(userContext.getUserId());
		light.setModifyDate(LCDateUtil.getNewDateUTC());
		LightMaintenanceRequest maintenanceRequest = new LightMaintenanceRequest(light);
		maintenanceRequest.setUserContext(userContext);
		InternalResponse updateResponse = getLightDAC().updateLight(maintenanceRequest);

		if (updateResponse.isInError())
		{
			response.setStatus(updateResponse.getStatus());
			response.getMessageInfoList().addAll(updateResponse.getMessageInfoList());
		}
	}

	/**
	 * Creates the alert classifications to insert.
	 * 
	 * @param alertSubtypes the alert subtypes
	 * @return the list
	 */
	private List<AlertClassification> createAlertClassificationsToInsert(List<AlertSubTypeEnum> alertSubtypes)
	{
		// Prepare alerts that came in the request.
		List<AlertClassification> classifications = new ArrayList<AlertClassification>();
		if (isNullOrEmpty(alertSubtypes))
		{
			return classifications;
		}

		for (AlertSubTypeEnum subtype : alertSubtypes)
		{
			AlertClassification classification = new AlertClassification();
			classification.setAlertType(subtype.getAlertType());
			classification.setAlertSubType(subtype);
			classifications.add(classification);
		}
		return classifications;
	}

	/**
	 * Prepare and update analytics.
	 * 
	 * @param notification the notification
	 * @param alertSubtype the alert subtype
	 * @param operator the operator
	 * @param userContext the user context
	 * @param response the response
	 */
	private void prepareAndUpdateAnalytics(
			NotificationHistory notification,
			AlertSubTypeEnum alertSubtype,
			OperatorEnum operator,
			UserContext userContext,
			InternalResultsResponse<NotificationHistory> response)
	{
		AnalyticsRequest request = new AnalyticsRequest(userContext);
		request.setNotificationHistory(notification);
		request.setOperator(operator);
		request.setAlertSubtype(alertSubtype);

		InternalResponse internalResponse = getAnalyticsBCL().updateAnalyticsAlarmWarning(request);
		handleNotificationHistoryResponseError(response, internalResponse);
	}

	/**
	 * Check noise alert.
	 * 
	 * @param historyResponse the history response
	 * @param currentNotification the current notification
	 * @param alertSubType the alert sub type
	 * @return true, if successful
	 */
	private boolean checkNoiseAlert(
			InternalResultsResponse<NotificationHistory> historyResponse,
			NotificationHistory currentNotification,
			List<AlertClassification> classifications,
			AlertSubTypeEnum alertSubType,
			Date date,
			UserContext userContext)
	{
		if (isNullOrEmpty(classifications))
		{
			return false;
		}

		for (AlertClassification alertClassification : classifications)
		{
			if (alertClassification.getAlertSubType() != alertSubType)
			{
				continue;
			}

			// Alert already exist, can't persist it again
			// in order to avoid the noise, this should be updated
			if (checkAndUpdateAnalyticsData(currentNotification, alertSubType, userContext, historyResponse))
			{
				alertClassification.setMessageDate(date);
			}

			alertClassification.setUpdateDate(date);
			InternalResponse alertUpdateResponse = getNotificationHistoryDAC().updateAlertClassification(
					new AlertClassificationMaintenanceRequest(alertClassification));

			if (alertUpdateResponse.isInError())
			{
				handleNotificationHistoryResponseError(historyResponse, alertUpdateResponse);
			}

			return true;
		}

		return false;
	}

	/**
	 * Check and update analytics data.<br>
	 * If the alert that came to be different from the last light alerts, then updates the data analytics
	 * 
	 * @param currentNotification the current notification
	 * @param classifications the classifications
	 * @param userContext the user context
	 * @param historyResponse the history response
	 */
	private boolean checkAndUpdateAnalyticsData(
			NotificationHistory currentNotification,
			AlertSubTypeEnum alertSubType,
			UserContext userContext,
			InternalResultsResponse<NotificationHistory> historyResponse)
	{
		if (isNullOrEmpty(currentNotification.getAlertClassifications()))
		{
			prepareAndUpdateAnalytics(
					currentNotification,
					alertSubType,
					OperatorEnum.PLUS,
					userContext,
					historyResponse);
			return true;
		}

		boolean isUpdateAnalytics = true;
		for (AlertClassification classification : currentNotification.getAlertClassifications())
		{
			if (alertSubType == classification.getAlertSubType())
			{
				isUpdateAnalytics = false;
				break;
			}
		}

		if (isUpdateAnalytics)
		{
			prepareAndUpdateAnalytics(
					currentNotification,
					alertSubType,
					OperatorEnum.PLUS,
					userContext,
					historyResponse);
		}
		return isUpdateAnalytics;
	}

	/**
	 * Gets the lights not alerts.
	 * 
	 * @param request the request
	 * @return the lights not alerts
	 */
	private List<Light> getLightsNotAlerts(LightRequest request)
	{
		if (isNull(request.getAlertCriteria()))
		{
			request.setAlertCriteria(new AlertCriteria());
		}

		request.getAlertCriteria().setNotAlert(true);
		InternalResultsResponse<Light> response = getLightDAC().fetchAllByRequest(request);

		// Reset not alert
		request.getAlertCriteria().setNotAlert(false);
		return response.getResultsList();
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
		if (isNullOrEmpty(lightRequest.getAlertCriteria().getAlertSubtypeList()))
		{
			return;
		}

		// Loop between all alerts subtypes
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		action.setActionType(LCActionTypeEnum.CLEAR_ALARM);
		for (AlertSubTypeEnum alertSubtype : lightRequest.getAlertCriteria().getAlertSubtypeList())
		{
			LCActionParameter parm =
					new LCActionParameter(PropertyEnum.STATUS_SUBTYPE_ID, String.valueOf(alertSubtype.getValue()));
			actionParameters.add(parm);
		}
		action.setActionParameters(actionParameters);
	}

	/**
	 * Sets the lc action parameter.
	 * 
	 * @param light the light
	 * @return the list
	 */
	private List<LCActionParameter> setLCActionParameter(LifeCycleStateEnum state)
	{
		LCActionParameter actionParameter = new LCActionParameter();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(actionParameter);
		switch (state)
		{
			case ACTIVE:

				actionParameter.setActionValue(ACTIVE);
				actionParameter.setProperty(PropertyEnum.ACTIVE);
				return actionParameters;

			case DEACTIVATED:

				actionParameter.setActionValue(DEACTIVATED);
				actionParameter.setProperty(PropertyEnum.DEACTIVATED);
				return actionParameters;

			case MAINTENANCE:

				actionParameter.setActionValue(MAINTENANCE);
				actionParameter.setProperty(PropertyEnum.MAINTENANCE);
				return actionParameters;
			default:
				return null;
		}
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
		if (isNull(light) || isNull(light.getPoleId()))
		{
			return;
		}

		EcoModeBaseline baseline = light.getEcoModeBaseline();

		if (!isNull(baseline)
				&& !isNull(baseline.getReplacedType())
				&& !isNull(baseline.getReplacedWattage()))
		{

			EcoModeBaseline newBaseline = new EcoModeBaseline(
					baseline.getReplacedType(),
					baseline.getReplacedWattage(),
					true);

			light.setEcoModeBaseline(newBaseline);
			EcoModeRequest ecoModeRequest = new EcoModeRequest(userContext);
			ecoModeRequest.getLights().add(light);

			// Update ecomode information.
			InternalResponse internalResponse = getEcoModeBCL().upsertEcoMode(ecoModeRequest);
			if (internalResponse.isInError())
			{
				response.addMessages(internalResponse.getMessageInfoList());
				response.setStatus(internalResponse.getStatus());
			}
		}
	}

	/**
	 * Prepare process request.
	 * 
	 * @param request the request
	 * @param processItemStatus the process item status
	 * @return the process request
	 */
	private ProcessRequest prepareProcessRequest(LightMaintenanceRequest request,
			ProcessItemStatusEnum processItemStatus)
	{
		LightRequest lightRequest = new LightRequest(request.getUserContext());
		lightRequest.setLightCriteria(new LightCriteria());
		lightRequest.setProcessCriteria(request.getProcessCriteria());
		lightRequest.getLightCriteria().getLightIdList().add(request.getLight().getId());
		return prepareProcessRequest(lightRequest, ProcessItemStatusEnum.PENDING);
	}

	/**
	 * Prepare process request.
	 * 
	 * @param lightRequest the light request
	 * @param processItemStatus the process item status
	 * @return the process request
	 */
	private ProcessRequest prepareProcessRequest(LightRequest lightRequest, ProcessItemStatusEnum processItemStatus)
	{
		ProcessRequest request = new ProcessRequest(lightRequest.getUserContext());

		if (!ValidationUtil.isNull(lightRequest.getProcessCriteria()))
		{
			request.setMonitored(lightRequest.getProcessCriteria().isMonitored());
		}

		request.setProcessItemStatusEnum(processItemStatus);
		request.setLightCriteria(lightRequest.getLightCriteria());
		request.setActionCriteria(lightRequest.getActionCriteria());
		request.setGroupCriteria(lightRequest.getGroupCriteria());
		request.setAlertCriteria(lightRequest.getAlertCriteria());
		request.setAddressCriteria(lightRequest.getAddressCriteria());
		request.setProcessCriteria(lightRequest.getProcessCriteria());
		request.setScheduleCriteria(lightRequest.getScheduleCriteria());
		request.setConfigurationCriteria(lightRequest.getConfigurationCriteria());
		request.setOperationalDataCriteria(lightRequest.getOperationalDataCriteria());
		request.setTagCriteria(lightRequest.getTagCriteria());
		request.setNotificationHistoryCriteria(lightRequest.getNotificationHistoryCriteria());
		request.setLightCustomSearchCriteria(lightRequest.getLightCustomSearchCriteria());
		return request;
	}

	/**
	 * Prepare and upsert notification history.
	 * 
	 * @param light the light
	 * @param lifeCycleState the life cycle state
	 * @param notificationType the notification type
	 * @param userContext the user context
	 * @param alerts the alerts
	 * @param transactionId the transaction id
	 * @param messageDate the message date
	 * @return the internal results response
	 */
	private InternalResultsResponse<NotificationHistory> prepareAndUpsertNotificationHistory(
			Light light,
			LifeCycleStateEnum lifeCycleState,
			NotificationTypeEnum notificationType,
			UserContext userContext,
			List<AlertClassification> alerts,
			String transactionId,
			Date messageDate)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();

		// Avoid notification noise, just update message date
		NotificationHistory currentNotification = light.getLastNotificationHistory();
		if (!isNull(currentNotification)
				&& currentNotification.getNotificationType() == notificationType
				&& notificationType != NotificationTypeEnum.ALARM
				&& notificationType != NotificationTypeEnum.CLEAR_ALARM)
		{
			currentNotification.setLifeCycleState(lifeCycleState);
			prepareAndUpdateNotificationHistory(messageDate, currentNotification, response);
			response.addResult(currentNotification);
			return response;
		}

		// Generate transaction id
		if (isNullOrEmpty(transactionId))
		{
			transactionId = UUID.randomUUID().toString();
		}

		// Propagate alerts to anothers notifications
		if (notificationType != NotificationTypeEnum.ALARM
				&& notificationType != NotificationTypeEnum.CLEAR_ALARM
				&& isNullOrEmpty(alerts)
				&& !isNull(currentNotification))
		{
			alerts = light.getLastNotificationHistory().getAlertClassifications();
		}

		NotificationHistory newNotificationHistory = new NotificationHistory();
		newNotificationHistory.setLifeCycleState(lifeCycleState);
		newNotificationHistory.setNotificationType(notificationType);
		newNotificationHistory.setLightId(light.getId());
		newNotificationHistory.setCreateUser(userContext.getUserId());
		newNotificationHistory.setCreateDate(LCDateUtil.getNewDateUTC());
		newNotificationHistory.setMessageDate(messageDate);
		newNotificationHistory.setUpdateDate(messageDate);
		newNotificationHistory.setTrasactionId(transactionId);

		// If light was in communication failure, then recovery last notification before communication failure
		boolean wasInCommunicationFailure = checkCommunicationFailure(light, newNotificationHistory, alerts);

		// Prepare all alerts for insert.
		prepareAlertsToInsert(alerts, newNotificationHistory);
		response =
				getNotificationHistoryBCL().insertNotificationHistory(
						new NotificationHistoryMaintenanceRequest(newNotificationHistory));

		if (response.isInError()
				|| isNullOrEmpty(alerts)
				|| notificationType != NotificationTypeEnum.ALARM
				|| wasInCommunicationFailure)
		{
			return response;
		}

		// Update analytics data
		for (AlertClassification alert : alerts)
		{
			prepareAndUpdateAnalytics(
					response.getFirstResult(),
					alert.getAlertSubType(),
					OperatorEnum.PLUS,
					userContext, response);
		}
		return response;
	}

	/**
	 * Communication Failure routine,
	 * once some status arrived then the light is not on communication failure anymore
	 * this routine below will verify if the light is under communication failure, if yes then
	 * we need to bring back the last notification history before that.
	 * 
	 * @param light the light
	 * @param newNotificationHistory the new notification history
	 * @param alerts the alerts
	 * @return true, if successful
	 */
	private Boolean checkCommunicationFailure(
			Light light,
			NotificationHistory newNotificationHistory,
			List<AlertClassification> alerts)
	{
		NotificationHistory currentNotification = light.getLastNotificationHistory();
		if (isNull(currentNotification) || isNullOrEmpty(currentNotification.getAlertClassifications()))
		{
			return false;
		}

		boolean isCommunicationFailure = false;
		for (AlertClassification alertClassification : currentNotification.getAlertClassifications())
		{
			// Check if light is under communication failure
			if (alertClassification.getAlertSubType() == AlertSubTypeEnum.COMMUNICATION_FAIL)
			{
				isCommunicationFailure = true;
			}
		}

		if (!isCommunicationFailure)
		{
			return false;
		}

		// Bring the last notification history before the comm fail.
		NotificationHistoryCriteria notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setLightId(light.getId());
		notificationHistoryCriteria.setFetchLastest(true);
		notificationHistoryCriteria.setFetchNotCommFail(true);
		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryDAC().fetchNotificationHistoryByRequest(notificationHistoryRequest);
		if (response.isInError())
		{
			return false;
		}

		NotificationHistory beforeCommFail = response.getFirstResult();
		newNotificationHistory.setLifeCycleState(beforeCommFail.getLifeCycleState());
		newNotificationHistory.setMessageDate(newNotificationHistory.getMessageDate());
		light.setLifeCycleState(beforeCommFail.getLifeCycleState());

		if (!isNullOrEmpty(alerts))
		{
			alerts.clear();
			alerts.addAll(beforeCommFail.getAlertClassifications());
		}
		return true;
	}

	/**
	 * Prepare alerts to insert.
	 * 
	 * @param alerts the alerts
	 * @param newNotificationHistory the new notification history
	 */
	private void prepareAlertsToInsert(List<AlertClassification> alerts, NotificationHistory newNotificationHistory)
	{
		if (isNullOrEmpty(alerts))
		{
			return;
		}

		newNotificationHistory.getAlertClassifications().clear();
		for (AlertClassification alert : alerts)
		{
			newNotificationHistory.getAlertClassifications().add(alert);
			alert.setCreateDate(newNotificationHistory.getCreateDate());
			alert.setUpdateDate(newNotificationHistory.getUpdateDate());
			alert.setCreateUser(newNotificationHistory.getCreateUser());
			if (!isNull(alert.getNotificationHistoryId()))
			{
				alert.setNotificationHistoryId(null);
				continue;
			}

			alert.setMessageDate(newNotificationHistory.getMessageDate());
		}
	}

	/**
	 * Prepare initiate light status.
	 * 
	 * @param light the light
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> prepareInitiateLightStatus(UserContext userContext, Light light)
	{
		// Bring the last binding notification history
		NotificationHistoryCriteria notificationHistoryCriteria = new NotificationHistoryCriteria();
		notificationHistoryCriteria.setLightId(light.getId());
		notificationHistoryCriteria.setFetchLastest(true);
		notificationHistoryCriteria.setNotificationType(NotificationTypeEnum.BINDING);

		NotificationHistoryRequest notificationHistoryRequest = new NotificationHistoryRequest();
		notificationHistoryRequest.setNotificationHistoryCriteria(notificationHistoryCriteria);
		InternalResultsResponse<NotificationHistory> historyResponse =
				getNotificationHistoryDAC().fetchNotificationHistoryByRequest(notificationHistoryRequest);

		// prepare request to get more informations
		LightRequest lightRequest = new LightRequest(userContext);
		lightRequest.setLightCriteria(new LightCriteria());
		lightRequest.getLightCriteria().getLightIdList().add(light.getId());
		lightRequest.setActionCriteria(new ActionCriteria());
		lightRequest.getActionCriteria().setGetDataFromLightEnum(GetDataFromLightEnum.ALL);

		// If binding notification is null, then this is installation process
		// If light is deactivated or last binding notification was less 1 hour ago
		// then do not get light status
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(LCDateUtil.getNewDateUTC());
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		NotificationHistory bindingNotification = historyResponse.getFirstResult();
		if (isNull(bindingNotification)
				|| LifeCycleStateEnum.DEACTIVATED == light.getLifeCycleState()
				|| bindingNotification.getUpdateDate().before(calendar.getTime()))
		{
			return initiateGetLightStatus(lightRequest);
		}

		return new InternalResultsResponse<Process>();
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
		FetchByIdRequest request = new FetchByIdRequest(light.getId(), light.getRadio().getFlexNetId());
		InternalResultsResponse<Light> fetchByIdResponse = getLightDAC().fetchById(request);
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response.addResult(light);

		Double latitude = new Double(0.00);
		Double longitude = new Double(0.00);

		if (!isNull(light.getRadio())
				&& !isNull(light.getRadio().getLocation())
				&& !isNullOrZero(light.getRadio().getLocation().getLatitude())
				&& !isNullOrZero(light.getRadio().getLocation().getLongitude()))
		{
			latitude = light.getRadio().getLocation().getLatitude();
			longitude = light.getRadio().getLocation().getLongitude();
		}

		if (fetchByIdResponse.hasResults())
		{
			// Light already exist.
			Light existingLight = fetchByIdResponse.getFirstResult();
			response.getResultsList().clear();
			response.addResult(existingLight);

			// Check if lat/long needs to be updated.
			Location lightLocation = existingLight.getRadio().getLocation();
			if (latitude.equals(lightLocation.getLatitude()) &&
					longitude.equals(lightLocation.getLongitude()))
			{
				// Send a status request.
				InternalResultsResponse<Process> processResponse =
						prepareInitiateLightStatus(userContext, existingLight);
				response.merge(processResponse);
				return response;
			}

			// Delete old tags and address references.
			LightDeleteRequest deleteTagRequest = new LightDeleteRequest(existingLight);
			deleteTagRequest.setDeleteLightReference(DeleteLightReferenceEnum.DELETE_ADDR_TAGS);
			InternalResponse deleteRefResponse = getLightDAC().deleteLightReference(deleteTagRequest);

			if (deleteRefResponse.isInError() && Status.NoRowsRemovedError != deleteRefResponse.getStatus())
			{
				response.merge(deleteRefResponse);
				return response;
			}

			// Process the new location reference.
			processAddressInformation(existingLight, userContext, response);

			// Send a status request.
			InternalResultsResponse<Process> processResponse = prepareInitiateLightStatus(userContext, existingLight);
			response.merge(processResponse);
			return response;
		}

		// Light doesn't exist, insert a new one.
		light.setModelAction(PersistanceActionEnum.INSERT);
		light.getConfiguration().setModelAction(PersistanceActionEnum.INSERT);
		light.getLightSchedule().setModelAction(PersistanceActionEnum.INSERT);
		light.getLastOperationalData().setModelAction(PersistanceActionEnum.INSERT);

		// Set create user and create date to every objects
		String createUser = userContext.getUserId();
		Date createDate = LCDateUtil.getNewDateUTC();
		light.setCreateUser(createUser);
		light.setCreateDate(createDate);
		light.getConfiguration().setCreateUser(createUser);
		light.getConfiguration().setCreateDate(createDate);
		light.getLightSchedule().setCreateUser(createUser);
		light.getLightSchedule().setCreateDate(createDate);
		light.getLastOperationalData().setCreateUser(createUser);
		light.getLastOperationalData().setCreateDate(createDate);

		// Complement attributes
		light.setTenant(userContext.<Tenant> getTenant());
		light.setProtect(false);
		light.setLightType(LightTypeEnum.OTHER);
		light.setLifeCycleState(LifeCycleStateEnum.UNKNOWN);
		light.setDeviceLifeCycleState(LightStateEnum.UNKNOWN);
		light.getConfiguration().setDateAdded(createDate);

		LightMaintenanceRequest maintenanceRequest = new LightMaintenanceRequest(light);
		maintenanceRequest.setUserContext(userContext);
		InternalResultsResponse<Light> newResponse = getLightDAC().insertLight(maintenanceRequest);
		if (newResponse.isInError())
		{
			return response;
		}

		// Insert address tags
		processAddressInformation(newResponse.getFirstResult(), userContext, response);

		// Light has been inserted, immediately request the light details thru a light status command.
		InternalResultsResponse<Process> processResponse =
				prepareInitiateLightStatus(userContext, newResponse.getFirstResult());
		response.merge(processResponse);
		return response;
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
		Double latitude = light.getRadio().getLocation().getLatitude();
		Double longitude = light.getRadio().getLocation().getLongitude();

		if (isNullOrZero(latitude) || isNullOrZero(longitude))
		{
			return;
		}

		// Fetch address information based on lat and long
		HashMap<String, String> addrInfo = getLcHelpGeoCoding().fetchLocationInfo(latitude, longitude);

		// If Address Information could not be retrieved, escape with an error.
		// Otherwise proceed to insert light
		if (isNull(addrInfo))
		{
			prepareError(REVERSE_LOOKUP_FAILED, response, InternalResponse.Status.ExternalError);
			return;
		}

		// Reset maintenance to other attributes
		light.getLastOperationalData().setModelAction(PersistanceActionEnum.NONE);
		light.getLightSchedule().setModelAction(PersistanceActionEnum.NONE);
		light.getConfiguration().setModelAction(PersistanceActionEnum.NONE);
		light.getRadio().getLocation().setModelAction(PersistanceActionEnum.UPDATE);

		// Update location
		light.setModelAction(PersistanceActionEnum.UPDATE);
		light.getRadio().getLocation().setAddress(addrInfo.get(STREET));
		light.getRadio().getLocation().setCity(addrInfo.get(CITY));
		light.getRadio().getLocation().setCounty(addrInfo.get(COUNTY));
		light.getRadio().getLocation().setState(addrInfo.get(STATE));
		light.getRadio().getLocation().setZip(addrInfo.get(ZIP));

		LightMaintenanceRequest maintenanceRequest = new LightMaintenanceRequest(light);
		maintenanceRequest.setUserContext(userContext);
		InternalResponse updateResponse = getLightBCL().updateLightBase(maintenanceRequest);

		if (updateResponse.isInError())
		{
			prepareError(ADD_PROPERTY_VALUE_FAILED, response, InternalResponse.Status.SystemError);
			return;
		}

		// This will tell if I have at least one non-empty address field
		boolean isAddrEmpty = true;
		for (String tagName : addrInfo.values())
		{
			if (isNullOrEmpty(tagName))
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

		// if no address tags, add light to "Incomplete Data" tag
		if (isAddrEmpty)
		{
			addLightToTag(INCOMPLETE_DATA, userContext, light, response);
		}
	}

	/**
	 * Adds the light to tag.
	 * 
	 * @param tagName the tag name
	 * @param userContext the user context
	 * @param light the light
	 * @param response the response
	 */
	private void addLightToTag(String tagName, UserContext userContext, Light light,
			InternalResponse response)
	{

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
			tag = getTagBCL().insertTag(tagRequest).getFirstResult();

			// problem creating tag.
			if (isNull(tag))
			{
				prepareError(ADD_TAG_FAILED, response, InternalResponse.Status.SystemError);
			}
		}
		else
		{
			// tag exists, just get the id.
			tag.setId(tagResponse.getFirstResult().getId());
		}

		// add light to tag
		tagRequest.addToTags(tag);
		tagRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
		tagRequest.setCurrentLightStatus(false);
		InternalResponse internalResponse = getTagBCL().insertLightToTag(tagRequest);
		if (internalResponse.isInError() && !isNullOrEmpty(tagResponse.getMessageInfoList()))
		{
			// verify if the problem was that light was already in tag. If yes, it is ok.
			if (!LIGHT_ALREADY_IN_TAG.equalsIgnoreCase(tagResponse.getMessageInfoList().get(0).getCode()))
			{
				// something bad happened when trying to add light to tag.
				prepareError(ADD_LIGHT_TO_TAG_FAILED, response, InternalResponse.Status.SystemError);
			}
		}
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
	 * Process app code90.
	 * 
	 * @param request the request
	 * @param notification the notification
	 * @return the internal response
	 */
	private InternalResponse processAppCode90(AlarmNotificationRequest request, NotificationHistory notification)
	{
		InternalResponse response = new InternalResponse();
		UserContext userContext = request.getUserContext();
		Light light = request.getLight();

		// Process update light
		updateLight(light, request.getUserContext(), response);
		if (response.isInError())
		{
			return response;
		}

		// Insert operational datas
		List<OperationalData> operationalDataList = request.getOperationalData();
		if (isNullOrEmpty(operationalDataList))
		{
			return response;
		}

		OperationalDataMaintenanceRequest operationalRequest = new OperationalDataMaintenanceRequest();
		operationalRequest.setUserContext(userContext);
		for (OperationalData operationalData : operationalDataList)
		{
			operationalData.setNotificationHistoryId(notification.getId());
			operationalRequest.setOperationalData(operationalData);
			response = getLightDAC().insertOperationalData(operationalRequest);
			if (response.isInError())
			{
				return response;
			}
		}

		return response;
	}

	/**
	 * Process app code85.
	 * 
	 * @param request the request
	 * @param notification the notification
	 * @return the internal response
	 */
	private InternalResponse processAppCode85(LightStatusNotificationRequest request, NotificationHistory notification)
	{
		InternalResponse response = new InternalResponse();
		if (!request.isForced())
		{
			return response;
		}

		UserContext userContext = request.getUserContext();
		Light light = request.getLight();

		// Case not exist 'Configurations' sent by gateway then do not nothing
		Configuration configuration = light.getConfiguration();
		if (isNull(configuration))
		{
			return response;
		}

		// Check update light configurations informations
		Light lightSearched = fetchLightById(light, userContext);
		checkUpdateConfigurations(light, lightSearched, userContext);

		// Update light configurations
		ConfigurationMaintenanceRequest maintenanceRequest = new ConfigurationMaintenanceRequest();
		maintenanceRequest.setUserContext(userContext);
		maintenanceRequest.setLightId(light.getId());
		maintenanceRequest.setConfiguration(configuration);
		response = getLightDAC().updateConfiguration(maintenanceRequest);

		// Case light is dimmable then apply part number configurations
		SetupDimmingConfigurationRequest dimmableRequest = new SetupDimmingConfigurationRequest();
		dimmableRequest.setUserContext(userContext);
		dimmableRequest.setLight(light);
		InternalResultsResponse<Process> dimmingResponse = initiateSetupDimmingConfiguration(dimmableRequest);
		if (dimmingResponse.isInError())
		{
			response.setStatus(dimmingResponse.getStatus());
			response.addMessages(dimmingResponse.getMessageInfoList());
		}
		return response;
	}

	/**
	 * Check update configurations.
	 * 
	 * @param light the light
	 * @param lightSearched the light searched
	 * @param userContext the user context
	 */
	private void checkUpdateConfigurations(Light light, Light lightSearched, UserContext userContext)
	{
		// Case not exist 'Configurations' informations to update then do not nothing
		if (isNull(lightSearched) || isNull(light.getConfiguration()))
		{
			return;
		}

		light.getConfiguration().setModifyUser(userContext.getUserId());
		light.getConfiguration().setModelAction(PersistanceActionEnum.INSERT);
		if (!isNull(lightSearched.getConfiguration()))
		{
			light.getConfiguration().setModelAction(PersistanceActionEnum.UPDATE);
		}
	}

	/**
	 * Fetch light by id.
	 * 
	 * @param light the light
	 * @param userContext the user context
	 * @return the light
	 */
	private Light fetchLightById(Light light, UserContext userContext)
	{
		FetchByIdRequest fetchRequest = new FetchByIdRequest(light.getId());
		fetchRequest.setUserContext(userContext);
		InternalResultsResponse<Light> lightResponse = getLightDAC().fetchById(fetchRequest);
		return lightResponse.getFirstResult();
	}

	/**
	 * Sets the action parameters to dimming configuration.
	 * 
	 * @param lightConfigurations the light configurations
	 * @param actionParameters the action parameters
	 */
	private void setActionParametersToDimmingConfiguration(List<PartNumberConfiguration> lightConfigurations,
			List<LCActionParameter> actionParameters)
	{
		if (isNullOrEmpty(lightConfigurations) || isNull(actionParameters))
		{
			return;
		}

		for (PartNumberConfiguration partNumberConfiguration : lightConfigurations)
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
	 * Handle error.
	 * 
	 * @param response the response
	 * @param responseOld the response old
	 * @return the internal results response
	 */
	private InternalResultsResponse<NotificationHistory> handleNotificationHistoryResponseError(
			InternalResultsResponse<NotificationHistory> response, InternalResponse responseOld)
	{
		response.setStatus(responseOld.getStatus());
		response.addMessages(responseOld.getMessageInfoList());
		return response;
	}

	/**
	 * Handle error.
	 * 
	 * @param response the response
	 * @param responseOld the response old
	 * @return the internal response
	 */
	private InternalResponse handleError(InternalResponse response, InternalResponse responseOld)
	{
		response.setStatus(responseOld.getStatus());
		response.addMessages(responseOld.getMessageInfoList());
		return response;
	}
}