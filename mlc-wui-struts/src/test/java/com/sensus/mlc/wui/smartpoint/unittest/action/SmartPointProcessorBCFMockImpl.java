package com.sensus.mlc.wui.smartpoint.unittest.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;
import com.sensus.mlc.smartpoint.model.response.AlarmNotificationResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class SmartPointProcessorBCFMockImpl extends BaseMockImpl implements ISmartPointProcessorBCF
{

	@Override
	public ProcessResponse initiateUpdateLightIntensity(LightRequest lightRequest)
	{
		// Takes care of failure scenario
		ProcessResponse response = new ProcessResponse();
		if (ValidationUtil.isNullOrEmpty(lightRequest.getSelectionPaginationIds())
				&& ValidationUtil.isNullOrEmpty(lightRequest.getLights())
				&& ValidationUtil.isNull(lightRequest.getPaginationAllSelected()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			List<Process> listProcesses = new ArrayList<Process>();
			Process process = new Process();
			process.setId(1);
			process.setDescription("Turn ON FlexNet ID A234560");
			process.setStartTime(new Date());
			process.setEstimatedSecondsToComplete(30);
			process.setIsParent(true);
			process.setIsProcessComplete(false);
			process.setIsSubmitted(true);

			listProcesses.add(process);
			response.setProcesses(listProcesses);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ProcessResponse initiateGetLightStatus(LightRequest lightRequest)
	{
		// Takes care of failure scenario
		ProcessResponse response = new ProcessResponse();
		if (ValidationUtil.isNullOrEmpty(lightRequest.getSelectionPaginationIds())
				&& (ValidationUtil.isNull(lightRequest.getFirstLight())
				|| ValidationUtil.isNullOrZero(lightRequest.getFirstLight().getId())))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			List<Process> lrpList = new ArrayList<Process>();
			Process lrp = new Process();
			lrp.setId(1);
			lrpList.add(lrp);
			response.setProcesses(lrpList);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ProcessResponse initiateDeleteAlert(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		if ((ValidationUtil.isNull(lightRequest.getFirstLight()))
				&& ValidationUtil.isNullOrEmpty(lightRequest.getSelectionPaginationIds()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ProcessResponse initiateUpsertLightProperty(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		if (ValidationUtil.isNull(lightRequest.getFirstLight().getId()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			List<ProcessItem> processItemList = new ArrayList<ProcessItem>();

			response.setProcesses(new ArrayList<Process>());

			ProcessItem processResult = new ProcessItem();
			ProcessItem processResult2 = new ProcessItem();

			processResult.setProcessStatusReasonEnum(ProcessStatusReasonEnum.ABORTED);
			Light light = new Light();
			light.setId(1);
			processResult.setLight(light);
			processResult.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);
			processResult.setProcessItemStatusEnumValue(1);
			Light light2 = new Light();
			light2.setId(2);
			processResult2.setLight(light2);
			processResult2.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);

			processItemList.add(processResult);
			processItemList.add(processResult2);

			response.setProcesses(new ArrayList<Process>());
			Process process = new Process();
			process.setId(1);
			process.setEndTime(new Date());
			process.setIsProcessComplete(true);
			process.setProcessItems(processItemList);
			process.setStartTime(new Date());
			process.setIsSubmitted(true);
			response.getProcesses().add(process);

			return response;
		}
		throw new RuntimeException("KAbom");
	}

	@Override
	public ProcessResponse initiateUpdateLightStatus(LightRequest lightRequest)
	{
		return null;
	}

	@Override
	public Response processApplySmartpointPropertiesNotification(ProcessLightsRequest request)
	{
		return null;
	}

	@Override
	public Response processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		return null;
	}

	@Override
	public AlarmNotificationResponse processClearAlarmNotification(AlarmNotificationRequest alarmNotificationRequest)
	{
		AlarmNotificationResponse response = new AlarmNotificationResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public Response processForcedLightStatusNotification(ForcedLightStatusNotificationRequest request)
	{
		return null;
	}

	@Override
	public AlarmNotificationResponse processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		return null;
	}

	@Override
	public Response processLightStatusNotification(LightStatusNotificationRequest request)
	{
		return null;
	}

	@Override
	public Response processLightBindingNotification(LightStatusNotificationRequest request)
	{
		return null;
	}

	@Override
	public Response processLightSetupNotification(LightSetupNotificationRequest request)
	{
		return null;
	}

	@Override
	public Response processSetLightIntensityNotification(ProcessLightsRequest request)
	{
		return null;
	}

}
