package com.sensus.lc.server.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.mlc.mlcserver.type.AlarmWarningNotification;
import com.sensus.mlc.mlcserver.type.ApplyDimmingConfigurationNotification;
import com.sensus.mlc.mlcserver.type.ApplyLightLevelNotification;
import com.sensus.mlc.mlcserver.type.ApplyScheduleNotification;
import com.sensus.mlc.mlcserver.type.ApplySmartpointPropertiesNotification;
import com.sensus.mlc.mlcserver.type.ChannelSetupAuditNotification;
import com.sensus.mlc.mlcserver.type.ClearAlarmsNotification;
import com.sensus.mlc.mlcserver.type.ClearScheduleNotification;
import com.sensus.mlc.mlcserver.type.LightBindingNotification;
import com.sensus.mlc.mlcserver.type.LightSetupNotification;
import com.sensus.mlc.mlcserver.type.LightStatusNotification;
import com.sensus.mlc.mlcserver.type.ReadLightStatusNotification;
import com.sensus.mlc.mlcserver.type.UpdateLightStatusNotification;

public class MockMlcServerBCL extends AbstractMockBase implements IMlcServerBCL
{
	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG =
			"sensus.mlc.serverbclimpl.defaultexception";

	@Override
	public InternalResponse processAlarmWarning(
			AlarmWarningNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processReadLightStatus(
			ReadLightStatusNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processLightStatus(LightStatusNotification notification)
	{

		return testSituationsResponse();
	}

	@Override
	public InternalResponse processLightSetup(
			LightSetupNotification notification)
	{

		return testSituationsResponse();
	}

	@Override
	public InternalResponse processLightBinding(
			LightBindingNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processApplyLightLevel(
			ApplyLightLevelNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processClearSchedule(
			ClearScheduleNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processApplySchedule(
			ApplyScheduleNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processClearAlarms(ClearAlarmsNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processApplySmartpointPropertiesNotification(
			ApplySmartpointPropertiesNotification notification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processChannelSetupAudit(ChannelSetupAuditNotification notification)
	{

		return testSituationsResponse();
	}

	@Override
	public InternalResponse processApplyDimmingConfigurationNotification(
			ApplyDimmingConfigurationNotification applyDimmingConfigurationNotification)
	{
		return testSituationsResponse();
	}

	@Override
	public InternalResponse processUpdateLightStatusNotification(UpdateLightStatusNotification updateStatusNotification)
	{
		return testSituationsResponse();
	}

	private InternalResponse testSituationsResponse()
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getInternalResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}

		return response;
	}

	private InternalResponse getInternalResponseError()
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.ExternalError);
		response.addFieldErrorMessage(DEFAULT_MCLSERVER_BCL_EXCEPTION_MSG);
		return response;
	}

	private InternalResponse getResponseDefault()
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.OperationSuccess);
		return response;
	}
}