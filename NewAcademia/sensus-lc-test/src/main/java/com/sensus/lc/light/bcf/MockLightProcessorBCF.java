package com.sensus.lc.light.bcf;

import static com.sensus.lc.base.TestBaseUtil.createNotificationHistory;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.Response;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.light.model.response.AlarmNotificationResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

public class MockLightProcessorBCF extends AbstractMockBase implements ILightProcessorBCF
{
	@Override
	public Response processLightStatusNotification(LightStatusNotificationRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public Response processLightSetupNotification(LightSetupNotificationRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public Response processLightBindingNotification(LightStatusNotificationRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public Response processSetLightIntensityNotification(LightIntensityRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public Response processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public Response processApplySmartpointPropertiesNotification(ProcessLightsRequest processLightsRequest)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public Response processUpdateLightStatusNotification(UpdateLightNotificationRequest request)
	{
		return treatAlarmNotificationResponse();
	}

	@Override
	public ProcessResponse initiateUpdateLightIntensity(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse initiateGetLightStatus(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse initiateDeleteAlert(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse initiateUpsertLightPoleId(LightMaintenanceRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse initiateUpsertLightLatLng(LightMaintenanceRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessResponse initiateUpdateLightStatus(LightMaintenanceRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Treat response.
	 * 
	 * @return the response
	 */
	private AlarmNotificationResponse treatAlarmNotificationResponse()
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();

		if (SituationsEnum.SUCCESS == getSituationsEnum())
		{
			response.setNotificationHistories(createNotificationHistoryList(TEN));
			return response;
		}

		if ((SituationsEnum.ERROR == getSituationsEnum()) || (SituationsEnum.VALIDATION == getSituationsEnum()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		if (SituationsEnum.EXCEPTION == getSituationsEnum())
		{
			throw new RuntimeException();
		}

		return response;
	}

	private List<NotificationHistory> createNotificationHistoryList(int amount)
	{

		List<NotificationHistory> notifications = new ArrayList<NotificationHistory>();

		UserContext userContext = createUserContext();
		Light light = TestBaseUtil.createLight();

		for (int i = 0; i < amount; i++)
		{
			notifications.add(createNotificationHistory(userContext, light));
		}
		return notifications;
	}
}
