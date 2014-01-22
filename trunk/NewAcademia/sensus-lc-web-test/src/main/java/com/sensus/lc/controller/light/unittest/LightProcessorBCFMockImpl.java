package com.sensus.lc.controller.light.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.bcf.ILightProcessorBCF;
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
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.response.ProcessResponse;

public class LightProcessorBCFMockImpl extends BaseMockImpl implements ILightProcessorBCF
{

	private List<Process> generateOneProcess()
	{
		List<Process> processes = new ArrayList<Process>();

		Process process = new Process();
		process.setId(2);

		processes.add(process);

		return processes;
	}

	@Override
	public ProcessResponse initiateDeleteAlert(LightRequest request)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setProcesses(generateOneProcess());
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse initiateGetLightStatus(LightRequest request)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setProcesses(generateOneProcess());
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse initiateUpdateLightIntensity(LightRequest request)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setProcesses(generateOneProcess());
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse initiateUpdateLightStatus(LightMaintenanceRequest request)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setProcesses(generateOneProcess());
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public ProcessResponse initiateUpsertLightLatLng(LightMaintenanceRequest request)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setProcesses(generateOneProcess());
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);

	}

	@Override
	public ProcessResponse initiateUpsertLightPoleId(LightMaintenanceRequest request)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setProcesses(generateOneProcess());
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

	@Override
	public AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (AlarmNotificationResponse)testOtherDefaultModes(response);
	}

	@Override
	public Response processApplySmartpointPropertiesNotification(ProcessLightsRequest request)
	{
		Response response = new Response();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return testOtherDefaultModes(response);
	}

	@Override
	public Response processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		Response response = new Response();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return testOtherDefaultModes(response);
	}

	@Override
	public AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest request)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (AlarmNotificationResponse)testOtherDefaultModes(response);
	}

	@Override
	public Response processLightBindingNotification(LightStatusNotificationRequest request)
	{
		Response response = new Response();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return testOtherDefaultModes(response);
	}

	@Override
	public Response processLightSetupNotification(LightSetupNotificationRequest request)
	{
		Response response = new Response();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return testOtherDefaultModes(response);
	}

	@Override
	public Response processLightStatusNotification(LightStatusNotificationRequest request)
	{
		Response response = new Response();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return testOtherDefaultModes(response);
	}

	@Override
	public Response processSetLightIntensityNotification(LightIntensityRequest request)
	{
		Response response = new Response();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return testOtherDefaultModes(response);
	}

	@Override
	public Response processUpdateLightStatusNotification(UpdateLightNotificationRequest request)
	{
		Response response = new Response();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return testOtherDefaultModes(response);
	}

}
