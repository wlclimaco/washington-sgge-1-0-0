package com.sensus.mlc.process.bcl.impl;

import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.util.LCActionUtil;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleResponse;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ScheduleType;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.tenant.model.Tenant;

class ProcessDeletionSchedule extends AbstractProcessCommunicationGateway
{
	/**
	 * Instantiates a new process deletion schedule.
	 */
	public ProcessDeletionSchedule()
	{
		setSupportedActions(EnumSet.of(LCActionTypeEnum.DEL_SCHEDULE));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessCommunicationGateway#sendAction(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public MlcGatewayResponse sendAction(ProcessRequest processRequest)
	{
		UserContext userContext = processRequest.getUserContext();
		Tenant tenant = processRequest.getTenant();
		Process process = processRequest.getProcess();
		setRemoteUserCredentials(tenant);

		List<Smartpoint> wsSmartPointList = generateWsSmartPointList(process, tenant);
		if (ValidationUtil.isNullOrEmpty(wsSmartPointList))
		{
			return null;
		}

		InitiateClearScheduleRequest request =
				getFactory().createInitiateClearScheduleRequest();
		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getSmartpoint().addAll(wsSmartPointList);

		// Get the type of schedule
		Schedule schedule = new OffsetSchedule();
		schedule.setId(getScheduleIdByProcess(process));

		ScheduleRequest scheduleRequest = new ScheduleRequest(userContext);
		scheduleRequest.setSchedule(schedule);

		InternalResultsResponse<Schedule> scheduleResponse = getScheduleBCL().fetchScheduleById(scheduleRequest);
		schedule = scheduleResponse.getFirstResult();
		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
		{
			request.getScheduleType().add(ScheduleType.EVENT);
		}
		else if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			request.getScheduleType().add(ScheduleType.OFFSET);
		}

		// Call Web Service
		InitiateClearScheduleResponse wsResponse = getMlcGatewayWs().clearSchedule(request);
		deleteUnknownSchedule(processRequest, schedule, wsResponse);

		persistLog(processRequest, wsSmartPointList);
		return wsResponse;
	}

	/**
	 * Delete unknown schedule.
	 * 
	 * @param processRequest the process request
	 * @param schedule the schedule
	 * @param wsResponse the ws response
	 */
	private void deleteUnknownSchedule(ProcessRequest processRequest, Schedule schedule, MlcGatewayResponse wsResponse)
	{
		if ((wsResponse.getStatus() == null)
				|| !wsResponse.getStatus().equals(com.sensus.mlc.mlcserver.type.Status.SUCCESS))
		{
			return;
		}

		Process process = processRequest.getProcess();

		// apply unknown schedule for the smartpoints
		ScheduleRequest scheduleRequestApplyUnknown = new ScheduleRequest(processRequest.getUserContext());
		List<Light> lightList = new ArrayList<Light>();
		for (ProcessItem processItem : process.getProcessItems())
		{
			lightList.add(processItem.getLight());
		}
		schedule.setLights(lightList);
		scheduleRequestApplyUnknown.setSchedule(schedule);
		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
		{
			getScheduleBCL().applyUnknownEventSchedule(scheduleRequestApplyUnknown);
			process.getLcAction().setActionType(LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_EVENT);
		}
		else if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			getScheduleBCL().applyUnknownOffsetSchedule(scheduleRequestApplyUnknown);
			process.getLcAction().setActionType(LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_OFFSET);
		}

		// delete schedule
		getScheduleBCL().deleteSchedule(scheduleRequestApplyUnknown);

		// feeds the process with the information removes schedule the light
		getLcHelp();
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(processRequest);
		Integer lightsAmount = getSmartPointAccessorBCL().countLights(inquiryLightRequest).getFirstResult();
		processRequest.getProcess().getLcAction().setDescription(LCActionUtil.generateActionDescription(process));
		processRequest.getProcess().setDescription(LCActionUtil.generateDescription(process, lightsAmount));
	}

}
