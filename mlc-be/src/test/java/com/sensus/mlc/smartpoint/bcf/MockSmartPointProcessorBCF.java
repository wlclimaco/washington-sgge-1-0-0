package com.sensus.mlc.smartpoint.bcf;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.smartpoint.model.response.AlarmNotificationResponse;

public class MockSmartPointProcessorBCF extends AbstractMockBase implements ISmartPointProcessorBCF
{

	@Override
	public ProcessResponse initiateUpdateLightIntensity(LightRequest lightRequest)
	{
		return new ProcessResponse();
	}

	@Override
	public ProcessResponse initiateGetLightStatus(LightRequest lightRequest)
	{
		return new ProcessResponse();
	}

	@Override
	public ProcessResponse initiateDeleteAlert(LightRequest lightRequest)
	{
		return new ProcessResponse();
	}

	@Override
	public ProcessResponse initiateUpsertLightProperty(LightRequest lightRequest)
	{
		return new ProcessResponse();
	}

	@Override
	public ProcessResponse initiateUpdateLightStatus(LightRequest lightRequest)
	{
		return new ProcessResponse();
	}

	@Override
	public Response processApplySmartpointPropertiesNotification(ProcessLightsRequest request)
	{
		return getResponseBySituation();
	}

	@Override
	public Response processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		Response response = new Response();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest alarmNotificationRequest)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Response processForcedLightStatusNotification(ForcedLightStatusNotificationRequest request)
	{
		return getResponseBySituation();
	}

	@Override
	public AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Response processLightStatusNotification(LightStatusNotificationRequest request)
	{
		Response response = new Response();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Response processLightBindingNotification(LightStatusNotificationRequest request)
	{
		Response response = new Response();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}

	@Override
	public Response processLightSetupNotification(LightSetupNotificationRequest request)
	{
		Response response = new Response();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;

	}

	@Override
	public Response processSetLightIntensityNotification(ProcessLightsRequest request)
	{
		return getResponseBySituation();
	}

	private Response getResponseBySituation()
	{
		Response response = new Response();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setOperationSuccess(Boolean.TRUE);
		}

		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(Boolean.FALSE);
		}
		return response;
	}
}
