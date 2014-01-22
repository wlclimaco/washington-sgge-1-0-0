package com.sensus.lc.light.dac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;

/**
 * Mock class for NotificationHistoryDAC.
 */
public class MockNotificationHistoryDAC extends AbstractMockBase implements INotificationHistoryDAC
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.dac.INotificationHistoryDAC#fetchNotificationHistoryByRequest(com.sensus.lc.light.model
	 * .request.NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryByRequest(
			NotificationHistoryRequest request)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			NotificationHistory notificationHistory =
					TestBaseUtil
							.createNotificationHistory(TestBaseUtil.createUserContext(), TestBaseUtil.createLight());

			List<AlertClassification> alerts = new ArrayList<AlertClassification>();
			AlertClassification alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LAMP_FAILURE);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.COMMUNICATION_FAIL);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LOW_CURRENT);
			alerts.add(alertClassification);

			notificationHistory.setAlertClassifications(alerts);

			response.getResultsList().add(notificationHistory);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.dac.INotificationHistoryDAC#fetchLightNotificationHistory(com.sensus.lc.light.model.request
	 * .NotificationHistoryRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<LightHistory> fetchLightNotificationHistory(NotificationHistoryRequest request)
	{
		InternalResultsResponse<LightHistory> response = new InternalResultsResponse<LightHistory>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);

			LightHistory history = new LightHistory();
			history.setLightCount(1);
			history.setProcessId(1);
			history.setNotificationHistoryId(1);
			history.setDescription("sensus.mlc.mlc_action.label.locked, {0}");
			history.setParameterValue("1000");
			history.setName("sensus.mlc.mlc_action.set_locked");
			history.setStatusComplete(Boolean.TRUE);
			response.getResultsList().add(history);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.INotificationHistoryDAC#fetchLightHistoryHeader(com.sensus.lc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(NotificationHistoryRequest request)
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();
		HashMap<String, Integer> value = new HashMap<String, Integer>();
		value.put("header1", 1);
		response.getResultsList().add(value);
		response.setStatus(Status.OperationSuccess);
		return response;
	}

	@Override
	public InternalResultsResponse<NotificationHistory> insertNotificationHistory(
			NotificationHistoryMaintenanceRequest notificationHistoryMaintenanceRequest)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			NotificationHistory notificationHistory =
					TestBaseUtil
							.createNotificationHistory(TestBaseUtil.createUserContext(), TestBaseUtil.createLight());

			List<AlertClassification> alerts = new ArrayList<AlertClassification>();
			AlertClassification alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LAMP_FAILURE);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.COMMUNICATION_FAIL);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LOW_CURRENT);
			alerts.add(alertClassification);

			notificationHistory.setAlertClassifications(alerts);

			response.getResultsList().add(notificationHistory);
		}

		return response;
	}

	@Override
	public InternalResponse updateNotificationHistory(
			NotificationHistoryMaintenanceRequest notificationHistoryMaintenanceRequest)
	{
		InternalResultsResponse<AlertClassification> response = new InternalResultsResponse<AlertClassification>();

		AlertClassification alertClassification = new AlertClassification();
		alertClassification.setAlertSubType(AlertSubTypeEnum.HIGH_CURRENT);
		response.getResultsList().add(alertClassification);
		return response;
	}

	@Override
	public InternalResultsResponse<AlertClassification> insertAlertClassification(
			AlertClassificationMaintenanceRequest alertClassificationMaintenance)
	{
		InternalResultsResponse<AlertClassification> response = new InternalResultsResponse<AlertClassification>();

		AlertClassification alertClassification = new AlertClassification();
		alertClassification.setAlertSubType(AlertSubTypeEnum.HIGH_CURRENT);
		response.getResultsList().add(alertClassification);
		return response;
	}

	@Override
	public InternalResponse updateAlertClassification(
			AlertClassificationMaintenanceRequest alertClassificationMaintenanceRequest)
	{
		InternalResponse response = new InternalResponse();
		return response;
	}

	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryById(NotificationHistoryRequest request)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			NotificationHistory notificationHistory =
					TestBaseUtil
							.createNotificationHistory(TestBaseUtil.createUserContext(), TestBaseUtil.createLight());

			List<AlertClassification> alerts = new ArrayList<AlertClassification>();
			AlertClassification alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LAMP_FAILURE);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.COMMUNICATION_FAIL);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LOW_CURRENT);
			alerts.add(alertClassification);

			notificationHistory.setAlertClassifications(alerts);

			response.getResultsList().add(notificationHistory);
		}
		return response;
	}

	@Override
	public InternalResultsResponse<AlertClassification> fetchNotificationHistoryAlertById(
			NotificationHistoryRequest request)
	{
		InternalResultsResponse<AlertClassification> response = new InternalResultsResponse<AlertClassification>();

		if (getTestControl() == "NO_RESULTS")
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);

			List<AlertClassification> alerts = new ArrayList<AlertClassification>();
			AlertClassification alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LAMP_FAILURE);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.COMMUNICATION_FAIL);
			alerts.add(alertClassification);

			alertClassification = new AlertClassification();
			alertClassification.setAlertSubType(AlertSubTypeEnum.LOW_CURRENT);
			alerts.add(alertClassification);

			response.addResults(alerts);

		}

		return response;
	}

}
