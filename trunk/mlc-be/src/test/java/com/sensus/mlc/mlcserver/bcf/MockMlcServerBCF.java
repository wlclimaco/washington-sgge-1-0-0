package com.sensus.mlc.mlcserver.bcf;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.mlcserver.model.request.MlcServerRequest;

public class MockMlcServerBCF extends AbstractMockBase implements IMlcServerBCF
{

	@Override
	public Response processLightBindingNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processLightSetupNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processLightStatusNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processAlarmWarningNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processReadLightStatusNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processApplyLightLevelNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processClearScheduleNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processApplyScheduleNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processClearAlarmsNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processApplySmartpointPropertiesNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processChannelSetupAuditNotification(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processApplyDimmingConfiguration(MlcServerRequest request)
	{
		return new Response();
	}

	@Override
	public Response processUpdateLightStatusNotification(MlcServerRequest request)
	{
		return new Response();
	}

}
