package com.sensus.lc.process.bcl.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleResponse;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.ScheduleType;
import com.sensus.mlc.mlcserver.type.Smartpoint;

/**
 * The Class ProcessDeletionSmartpointSchedule.
 */
public class ProcessDeletionSmartpointSchedule extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process deletion smartpoint schedule.
	 */
	public ProcessDeletionSmartpointSchedule()
	{
		setSupportedActions(EnumSet.of(
				LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_EVENT,
				LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_OFFSET));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessCommunicationGateway#sendAction(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public MlcGatewayResponse sendAction(ProcessRequest processRequest)
	{
		Tenant tenant = processRequest.getUserContext().<Tenant> getTenant();
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

		if (process.getLcAction().getActionType() == LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_EVENT)
		{
			request.getScheduleType().add(ScheduleType.EVENT);
		}
		else if (process.getLcAction().getActionType() == LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_OFFSET)
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
		if (wsResponse.getStatus() != null
				&& !com.sensus.mlc.mlcserver.type.Status.SUCCESS.equals(wsResponse.getStatus()))
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
	private List<Integer> getLightsFromProcess(Process process)
	{
		List<Integer> lightIdList = new ArrayList<Integer>();
		for (ProcessItem processItem : process.getProcessItems())
		{
			lightIdList.add(processItem.getLight().getId());
		}
		return lightIdList;
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
		if (process.getLcAction().getActionType() == LCActionTypeEnum.DEL_LIGHT_FROM_SCHEDULE_EVENT)
		{
			schedule = new EventSchedule();
		}
		return schedule;
	}

}
