package com.sensus.mlc.process.bcl.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.AlarmWarningType;
import com.sensus.mlc.mlcserver.type.ClearAlarmsType;
import com.sensus.mlc.mlcserver.type.InitiateClearAlarmsRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.Smartpoint;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItem;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ProcessCleaningAlarm.
 */
class ProcessCleaningAlarm extends AbstractProcessCommunicationGateway
{

	/**
	 * Instantiates a new process cleaning alarm.
	 */
	public ProcessCleaningAlarm()
	{
		setSupportedActions(EnumSet.of(LCActionTypeEnum.CLEAR_ALL_ALARMS, LCActionTypeEnum.CLEAR_ALARM));
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcl.IProcessCommunicationGateway#sendAction(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public MlcGatewayResponse sendAction(ProcessRequest processRequest)
	{
		Process process = processRequest.getProcess();

		// Case alarm is communication failure then not comunicate with gateway and finished process
		if (checkCommunicationFailure(process.getLcAction()))
		{
			processCommunicationFailure(processRequest);
			return null;
		}

		Tenant tenant = processRequest.getTenant();
		List<Smartpoint> wsSmartPointList = generateWsSmartPointList(process, tenant);

		if (ValidationUtil.isNullOrEmpty(wsSmartPointList))
		{
			return null;
		}

		InitiateClearAlarmsRequest request = getFactory().createInitiateClearAlarmsRequest();
		request.setCustomerID(tenant.getRniCode());
		request.setTransactionID(process.getRniCorrelationId());
		request.getSmartpoint().addAll(wsSmartPointList);

		request.setClearAlarmsType(ClearAlarmsType.CLEAR_ALL);
		setClearAlarmToList(processRequest, request);
		persistLog(processRequest, wsSmartPointList);

		setRemoteUserCredentials(tenant);
		return getMlcGatewayWs().clearAlarm(request);
	}

	/**
	 * Sets the clear alarm to list.
	 * 
	 * @param process the process
	 * @param request the request
	 */
	private void setClearAlarmToList(ProcessRequest processRequest, InitiateClearAlarmsRequest request)
	{
		Process process = processRequest.getProcess();
		LCAction action = process.getLcAction();
		if (isClearAllAlarms(action))
		{
			return;
		}

		request.setClearAlarmsType(ClearAlarmsType.USE_LIST);

		for (LCActionParameter actionParameter : action.getActionParameters())
		{
			String actionValue = actionParameter.getActionValue();
			AlarmWarningInfo alarmWarningInfo = createAlarmWarningInfo(actionValue);
			if (ValidationUtil.isNull(alarmWarningInfo))
			{
				continue;
			}
			request.getAlarms().add(alarmWarningInfo);
		}
	}

	/**
	 * Creates the alarm warning info.
	 * 
	 * @param actionValue the action value
	 * @return the alarm warning info
	 */
	private AlarmWarningInfo createAlarmWarningInfo(String actionValue)
	{
		if (ValidationUtil.isNull(actionValue))
		{
			return null;
		}

		StatusExceptionTypeEnum statusException = StatusExceptionTypeEnum.enumForValue(Integer.valueOf(actionValue));
		LightStatusEnum lightStatus = statusException.getLightStatusEnum();

		AlarmWarningInfo alarmWarningInfo = new AlarmWarningInfo();
		alarmWarningInfo.setAlarmWarningType(AlarmWarningType.valueOf(lightStatus.name()));
		alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.valueOf(statusException.name()));
		return alarmWarningInfo;

		// if (StatusExceptionTypeEnum.LAMP_FAILURE.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.LAMP_FAILURE);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.POWER_FAILURE.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.POWER_FAILURE);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.BOARD_FAILURE.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.BOARD_FAILURE);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.METROLOGY_ERROR.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.METROLOGY_ERROR);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.METROLOGY_COM_FAILURE);
		// return alarmWarningInfo;
		// }
		//
		// alarmWarningInfo.setAlarmWarningType(AlarmWarningType.WARNING);
		// if (StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.POWER_SURGE_DETECTED);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.BROWN_OUT_DETECTED);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.HIGH_CURRENT.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.HIGH_CURRENT);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.LOW_CURRENT.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.LOW_CURRENT);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.REVERSE_ENERGY.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.REVERSE_ENERGY);
		// return alarmWarningInfo;
		// }
		//
		// if (StatusExceptionTypeEnum.METROLOGY_RESET.getValueString().equals(actionValue))
		// {
		// alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.METROLOGY_RESET);
		// return alarmWarningInfo;
		// }
		// return null;
	}

	private boolean checkCommunicationFailure(LCAction action)
	{
		if (isClearAllAlarms(action))
		{
			return false;
		}

		for (LCActionParameter actionParameter : action.getActionParameters())
		{
			String actionValue = actionParameter.getActionValue();
			if (!isCommunicationFailure(actionValue))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Process communication failure.
	 * 
	 * @param processRequest the process request
	 */
	private void processCommunicationFailure(ProcessRequest processRequest)
	{
		AlarmNotificationRequest request = new AlarmNotificationRequest(processRequest.getUserContext());
		Process process = processRequest.getProcess();
		List<Light> lights = getLights(process.getProcessItems());

		for (Light light : lights)
		{
			request.setLight(light);
			getSmartPointProcessorBCL().processClearAlarmNotification(request);
		}
	}

	/**
	 * Checks if is clear all alarms.
	 * 
	 * @param action the action
	 * @return true, if is clear all alarms
	 */
	private boolean isClearAllAlarms(LCAction action)
	{
		if ((action.getActionType() == LCActionTypeEnum.CLEAR_ALL_ALARMS)
				|| ValidationUtil.isNullOrEmpty(action.getActionParameters()))
		{
			return true;
		}
		return false;
	}

	/**
	 * Checks if is communication failure.
	 * 
	 * @param actionValue the action value
	 * @return true, if is communication failure
	 */
	private boolean isCommunicationFailure(String actionValue)
	{
		return StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValueString().equals(actionValue);
	}

	/**
	 * Gets the lights.
	 * 
	 * @param processItems the process items
	 * @return the lights
	 */
	private List<Light> getLights(List<ProcessItem> processItems)
	{
		List<Light> lights = new ArrayList<Light>();
		if (ValidationUtil.isNullOrEmpty(processItems))
		{
			return lights;
		}

		for (ProcessItem processItem : processItems)
		{
			lights.add(processItem.getLight());
		}

		return lights;
	}
}
