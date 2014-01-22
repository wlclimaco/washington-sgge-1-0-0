package com.sensus.lc.light.bcl;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.light.model.request.ChannelSetupAuditRequest;
import com.sensus.lc.light.model.request.LightIntensityRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.LightSetupNotificationRequest;
import com.sensus.lc.light.model.request.LightStatusNotificationRequest;
import com.sensus.lc.light.model.request.ProcessLightsRequest;
import com.sensus.lc.light.model.request.SetupDimmingConfigurationRequest;
import com.sensus.lc.light.model.request.UpdateLightNotificationRequest;
import com.sensus.lc.process.model.Process;

public class MockLightProcessorBCL extends AbstractMockBase implements ILightProcessorBCL
{
	@Override
	public InternalResultsResponse<NotificationHistory> processLightStatusNotification(
			LightStatusNotificationRequest request)
	{
		return treatNotificationResponse();
	}

	@Override
	public InternalResultsResponse<NotificationHistory> processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		return treatNotificationResponse();
	}

	@Override
	public InternalResponse processLightSetupNotification(LightSetupNotificationRequest request)
	{
		return treatInternalResponse();
	}

	@Override
	public InternalResultsResponse<Process> initiateSetupDimmingConfiguration(SetupDimmingConfigurationRequest request)
	{
		return treatProcessResponse();
	}

	@Override
	public InternalResultsResponse<Process> initiateGetLightStatus(LightRequest lightRequest)
	{
		return treatProcessResponse();
	}

	@Override
	public InternalResponse processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		return treatInternalResponse();
	}

	@Override
	public InternalResponse processLightBindingNotification(LightStatusNotificationRequest request)
	{
		return treatInternalResponse();
	}

	@Override
	public InternalResponse processSetLightIntensityNotification(LightIntensityRequest request)
	{
		return treatInternalResponse();
	}

	@Override
	public InternalResponse processUpdateLightStatusNotification(UpdateLightNotificationRequest request)
	{
		return treatInternalResponse();
	}

	@Override
	public InternalResponse processApplySmartpointPropertiesNotification(ProcessLightsRequest request)
	{
		return treatInternalResponse();
	}

	@Override
	public InternalResultsResponse<NotificationHistory> processClearAlarmNotification(AlarmNotificationRequest request)
	{
		return treatNotificationResponse();
	}

	@Override
	public InternalResultsResponse<Process> initiateUpdateLightIntensity(LightRequest lightRequest)
	{
		return treatProcessResponse();
	}

	@Override
	public InternalResultsResponse<Process> initiateDeleteAlert(LightRequest request)
	{
		return treatProcessResponse();
	}

	@Override
	public InternalResultsResponse<Process> initiateUpsertLightPoleId(LightMaintenanceRequest request)
	{
		return treatProcessResponse();
	}

	@Override
	public InternalResultsResponse<Process> initiateUpsertLightLatLng(LightMaintenanceRequest request)
	{
		return treatProcessResponse();
	}

	@Override
	public InternalResultsResponse<Process> initiateUpdateLightStatus(LightMaintenanceRequest request)
	{
		return treatProcessResponse();
	}

	private InternalResponse treatInternalResponse()
	{
		InternalResponse response = getInternalResponseDefault();
		if (SituationsEnum.SUCCESS == getSituationsEnum())
		{
			return response;
		}

		if (SituationsEnum.ERROR == getSituationsEnum() || SituationsEnum.VALIDATION == getSituationsEnum())
		{
			return getResponseError();
		}

		if (SituationsEnum.EXCEPTION == getSituationsEnum())
		{
			throw new RuntimeException();
		}
		return response;
	}

	private InternalResultsResponse<Process> treatProcessResponse()
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();

		if (SituationsEnum.SUCCESS == getSituationsEnum())
		{
			return response;
		}

		if (SituationsEnum.ERROR == getSituationsEnum() || SituationsEnum.VALIDATION == getSituationsEnum())
		{
			response.getResultsList().clear();
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		if (SituationsEnum.EXCEPTION == getSituationsEnum())
		{
			throw new RuntimeException();
		}

		return response;
	}

	private InternalResultsResponse<NotificationHistory> treatNotificationResponse()
	{
		InternalResultsResponse<NotificationHistory> response = getResultResponseNotification();

		if (SituationsEnum.SUCCESS == getSituationsEnum())
		{
			return response;
		}

		if (SituationsEnum.ERROR == getSituationsEnum() || SituationsEnum.VALIDATION == getSituationsEnum())
		{
			response.getResultsList().clear();
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		if (SituationsEnum.EXCEPTION == getSituationsEnum())
		{
			throw new RuntimeException();
		}

		return response;
	}

	@Override
	public void verifyCommunicationFailure(UserContext userContext)
	{
		if (SituationsEnum.SUCCESS == getSituationsEnum())
		{
			return;
		}

		if (SituationsEnum.EXCEPTION == getSituationsEnum())
		{
			throw new RuntimeException();
		}
	}

	@Override
	public void insertConsumptionToLightInCommunicationFailure(UserContext userContext)
	{
		if (SituationsEnum.SUCCESS == getSituationsEnum())
		{
			return;
		}

		if (SituationsEnum.EXCEPTION == getSituationsEnum())
		{
			throw new RuntimeException();
		}
	}
}
