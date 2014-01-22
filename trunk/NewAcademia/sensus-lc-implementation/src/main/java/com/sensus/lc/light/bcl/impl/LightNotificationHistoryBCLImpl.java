package com.sensus.lc.light.bcl.impl;

import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.bcl.ILightNotificationHistoryBCL;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.util.NotificationHistoryUtil;

/**
 * The INotificationHistoryBCL implementation, light business area.
 * 
 * @author Thiago - QAT
 */
public class LightNotificationHistoryBCLImpl implements ILightNotificationHistoryBCL
{

	/** The Constant UNLOCKED. */
	private static final String UNLOCKED = ".*unlocked.*";

	/** The Constant LOCKED. */
	private static final String LOCKED = ".*locked.*";

	/** The Constant FIVE. */
	private static final int FIVE = 5;

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The Constant SENSUS_MLC_MLC_ACTION. */
	private static final String SENSUS_MLC_MLC_ACTION = ".*sensus.mlc.mlc_action.*";

	/**
	 * DAC Attributes.
	 */
	private INotificationHistoryDAC notificationHistoryDAC;

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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchLightNotificationHistory(com.sensus.mlc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<LightHistory> fetchLightNotificationHistory(NotificationHistoryRequest request)
	{
		InternalResultsResponse<LightHistory> response =
				getNotificationHistoryDAC().fetchLightNotificationHistory(request);

		translateMessage(response, request.getUserContext());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchLightHistoryHeader(com.sensus.mlc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(NotificationHistoryRequest request)
	{
		return getNotificationHistoryDAC().fetchLightHistoryHeader(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightNotificationHistoryBCL#fetchNotificationHistoryByRequest(com.sensus.mlc.light.
	 * model.request.NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryByRequest(
			NotificationHistoryRequest request)
	{
		return getNotificationHistoryDAC().fetchNotificationHistoryByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightNotificationHistoryBCL#fetchNotificationHistoryById(com.sensus.mlc.light.model
	 * .request.NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryById(NotificationHistoryRequest request)
	{
		return getNotificationHistoryDAC().fetchNotificationHistoryById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.bcl.ILightNotificationHistoryBCL#fetchNotificationHistoryAlertById(com.sensus.lc.light.model
	 * .request.NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<AlertClassification> fetchNotificationHistoryAlertById(
			NotificationHistoryRequest request)
	{
		return getNotificationHistoryDAC().fetchNotificationHistoryAlertById(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#insertLight(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> insertNotificationHistory(
			NotificationHistoryMaintenanceRequest request)
	{
		NotificationHistory notificationHistory = request.getNotificationHistory();
		NotificationHistoryUtil.calculateNotificationHistoryPrecedence(notificationHistory);

		InternalResultsResponse<NotificationHistory> response =
				getNotificationHistoryDAC().insertNotificationHistory(request);
		for (AlertClassification alertClassification : notificationHistory.getAlertClassifications())
		{
			alertClassification.setNotificationHistoryId(notificationHistory.getId());
			AlertClassificationMaintenanceRequest alertClassificationMaintenance =
					new AlertClassificationMaintenanceRequest();
			alertClassificationMaintenance.setAlertClassification(alertClassification);
			getNotificationHistoryDAC().insertAlertClassification(alertClassificationMaintenance);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightNotificationHistoryBCL#updateNotificationHistory(com.sensus.mlc.light.model.request
	 * .NotificationHistoryMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateNotificationHistory(NotificationHistoryMaintenanceRequest request)
	{
		// Configure precedence
		NotificationHistory notificationHistory = request.getNotificationHistory();
		NotificationHistoryUtil.calculateNotificationHistoryPrecedence(notificationHistory);
		return getNotificationHistoryDAC().updateNotificationHistory(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightBCL#insertNotificationHistoryAlertClassification(com.sensus.mlc.light.model.request
	 * .AlertClassificationMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<AlertClassification> insertNotificationHistoryAlertClassification(
			AlertClassificationMaintenanceRequest request)
	{
		return getNotificationHistoryDAC().insertAlertClassification(request);
	}

	/**
	 * Translate message.
	 * 
	 * @param response the response
	 * @param userContext the user context
	 */
	private void translateMessage(InternalResultsResponse<LightHistory> response, UserContext userContext)
	{
		if (ValidationUtil.isNull(userContext) || ValidationUtil.isNullOrEmpty(userContext.getLocaleString()))
		{
			return;
		}
		Locale locale =
				new Locale(userContext.getLocaleString().substring(0, 2), userContext.getLocaleString().substring(
						THREE, FIVE));

		for (LightHistory lightHistory : response.getResultsList())
		{
			if (!checkDescriptionValue(lightHistory.getDescription()))
			{
				continue;
			}

			if (!ValidationUtil.isNullOrEmpty(lightHistory.getName()))
			{
				lightHistory.setName(
						SensusMessageUtil.getMessage(lightHistory.getName(), null, null, locale));
			}

			Object[] parameters = StringUtils.splitByWholeSeparator(lightHistory.getParameterValue(), ",");

			if (!ValidationUtil.isNull(parameters)
					&& (lightHistory.getDescription().matches(LOCKED) || lightHistory.getDescription()
							.matches(UNLOCKED)))
			{
				Object[] protectedParam = new Object[1];
				protectedParam[0] = parameters[0];

				parameters[0] = SensusMessageUtil.getMessage(String.valueOf(protectedParam[0]), null, null, locale);
			}

			lightHistory.setDescription(SensusMessageUtil.getMessage(lightHistory.getDescription(), parameters, null,
					locale));
		}
	}

	/**
	 * Check description value.
	 * 
	 * @param description the description
	 * @return the boolean
	 */
	private Boolean checkDescriptionValue(String description)
	{
		if (ValidationUtil.isNull(description))
		{
			return false;
		}
		if (description.matches(SENSUS_MLC_MLC_ACTION))
		{
			return true;
		}
		return false;
	}

}
