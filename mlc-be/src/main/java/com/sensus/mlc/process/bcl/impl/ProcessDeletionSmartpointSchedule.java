package com.sensus.mlc.process.bcl.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleResponse;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ScheduleType;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessDeletionSmartpointSchedule.
 */
class ProcessDeletionSmartpointSchedule extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process deletion smartpoint schedule.
	 */
	public ProcessDeletionSmartpointSchedule()
	{
		setSupportedActions(EnumSet.of(
				LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_EVENT,
				LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_OFFSET));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessCommunicationGateway#sendAction(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public MlcGatewayResponse sendAction(ProcessRequest processRequest)
	{
		Tenant tenant = processRequest.getTenant();
		Process process = processRequest.getProcess();
		setRemoteUserCredentials(tenant);

		List<Smartpoint> wsSmartPointList = generateWsSmartPointList(process, tenant);
		if (ValidationUtil.isNullOrEmpty(wsSmartPointList))
		{
			return null;
		}

		InitiateClearScheduleRequest request = getFactory().createInitiateClearScheduleRequest();
		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getSmartpoint().addAll(wsSmartPointList);

		if (process.getLcAction().getActionType() == LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_EVENT)
		{
			request.getScheduleType().add(ScheduleType.EVENT);
		}
		else if (process.getLcAction().getActionType() == LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_OFFSET)
		{
			request.getScheduleType().add(ScheduleType.OFFSET);
		}

		// Call Web Service
		InitiateClearScheduleResponse wsResponse = getMlcGatewayWs().clearSchedule(request);
		insertUnknownSchedule(processRequest, wsResponse);

		persistLog(processRequest, wsSmartPointList);
		return wsResponse;
	}

	/**
	 * Insert unknown schedule.
	 * 
	 * @param processRequest the process request
	 * @param wsResponse the ws response
	 */
	private void insertUnknownSchedule(ProcessRequest processRequest, MlcGatewayResponse wsResponse)
	{
		if ((wsResponse.getStatus() == null)
				|| !wsResponse.getStatus().equals(com.sensus.mlc.mlcserver.type.Status.SUCCESS))
		{
			return;
		}

		Process process = processRequest.getProcess();

		// apply unknown schedule for the smartpoints
		Schedule schedule = getScheduleInstanceByActionType(process);
		schedule.setLights(getLightsFromProcess(process));
		ScheduleRequest scheduleRequestApplyUnknown = new ScheduleRequest(processRequest.getUserContext());
		scheduleRequestApplyUnknown.setSchedule(schedule);
		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.EVENT)
		{
			getScheduleBCL().applyUnknownEventSchedule(scheduleRequestApplyUnknown);
			return;
		}

		if (schedule.getScheduleTypeEnum() == ScheduleTypeEnum.OFFSET)
		{
			getScheduleBCL().applyUnknownOffsetSchedule(scheduleRequestApplyUnknown);
		}
	}

	/**
	 * Gets the lights from process.
	 * 
	 * @param process the process
	 * @return the lights from process
	 */
	private List<Light> getLightsFromProcess(Process process)
	{
		List<Light> lightList = new ArrayList<Light>();
		for (ProcessItem processItem : process.getProcessItems())
		{
			lightList.add(processItem.getLight());
		}
		return lightList;
	}

	/**
	 * Gets the schedule instance by action type.
	 * 
	 * @param process the process
	 * @return the schedule instance by action type
	 */
	private Schedule getScheduleInstanceByActionType(Process process)
	{
		Schedule schedule = new OffsetSchedule();
		if (process.getLcAction().getActionType() == LCActionTypeEnum.DEL_SMP_FROM_SCHEDULE_EVENT)
		{
			schedule = new EventSchedule();
		}
		return schedule;
	}

}
