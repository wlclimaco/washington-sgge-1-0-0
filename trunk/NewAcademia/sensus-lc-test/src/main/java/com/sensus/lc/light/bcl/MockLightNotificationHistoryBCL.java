package com.sensus.lc.light.bcl;

import java.util.Date;
import java.util.HashMap;

import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.NotificationTypeEnum;
import com.sensus.lc.light.model.PrecedenceEnum;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;

/**
 * The Class MockLightNotificationHistoryBCL.
 */
public class MockLightNotificationHistoryBCL extends AbstractMockBase implements ILightNotificationHistoryBCL
{

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<LightHistory> fetchLightNotificationHistory(NotificationHistoryRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		InternalResultsResponse<LightHistory> response = new InternalResultsResponse<LightHistory>();
		response.getResultsList().add(createLightHistory());
		return response;
	}

	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(NotificationHistoryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryByRequest(
			NotificationHistoryRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();
		response.getResultsList().add(createNotificationHistory());
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryById(NotificationHistoryRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();
		response.getResultsList().add(createNotificationHistory());
		return response;
	}

	@Override
	public InternalResultsResponse<NotificationHistory> insertNotificationHistory(
			NotificationHistoryMaintenanceRequest request)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		response.getResultsList().add(createNotificationHistory());

		return response;
	}

	@Override
	public InternalResponse updateNotificationHistory(NotificationHistoryMaintenanceRequest request)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
		}
		response.getResultsList().add(createNotificationHistory());

		return response;
	}

	@Override
	public InternalResultsResponse<AlertClassification> insertNotificationHistoryAlertClassification(
			AlertClassificationMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private NotificationHistory createNotificationHistory()
	{
		NotificationHistory notificationHistory = new NotificationHistory();
		notificationHistory.setId(1);
		notificationHistory.setCreateDate(new Date());
		notificationHistory.setPrecedence(PrecedenceEnum.ACTIVE);
		notificationHistory.setNotificationType(NotificationTypeEnum.UNSOLICITED_STATUS);
		notificationHistory.setLightId(1);
		return notificationHistory;
	}

	private LightHistory createLightHistory()
	{
		LightHistory lightHistory = new LightHistory();
		lightHistory.setName("name");
		lightHistory.setCreateDate(new Date());
		lightHistory.setDescription("description");
		lightHistory.setNotificationHistoryId(1);
		lightHistory.setLightCount(5);
		lightHistory.setModelAction(PersistanceActionEnum.NONE);
		lightHistory.setStatusComplete(true);
		return lightHistory;
	}

	@Override
	public InternalResultsResponse<AlertClassification> fetchNotificationHistoryAlertById(
			NotificationHistoryRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
