package com.sensus.lc.process.bcl.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.bcl.ILightProcessorBCL;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.AlertTypeEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.AlarmNotificationRequest;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.mlc.mlcserver.type.AlarmWarningInfo;
import com.sensus.mlc.mlcserver.type.AlarmWarningSubType;
import com.sensus.mlc.mlcserver.type.AlarmWarningType;
import com.sensus.mlc.mlcserver.type.ClearAlarmsType;
import com.sensus.mlc.mlcserver.type.InitiateClearAlarmsRequest;
import com.sensus.mlc.mlcserver.type.MlcGatewayResponse;
import com.sensus.mlc.mlcserver.type.Smartpoint;

/**
 * The Class ProcessCleaningAlarm.
 */
public class ProcessCleaningAlarm extends AbstractProcessCommunicationGateway
{
	/**
	 * Attributes.
	 */
	private ILightProcessorBCL lightProcessorBCL;

	/**
	 * Gets the light processor bcl.
	 * 
	 * @return the light processor bcl
	 */
	public ILightProcessorBCL getLightProcessorBCL()
	{
		return lightProcessorBCL;
	}

	/**
	 * Sets the light processor bcl.
	 * 
	 * @param lightProcessorBCL the new light processor bcl
	 */
	public void setLightProcessorBCL(ILightProcessorBCL lightProcessorBCL)
	{
		this.lightProcessorBCL = lightProcessorBCL;
	}

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

		Tenant tenant = processRequest.getUserContext().<Tenant> getTenant();
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

		AlertSubTypeEnum alertSubtype = AlertSubTypeEnum.enumForValue(Integer.valueOf(actionValue));
		AlertTypeEnum alertType = alertSubtype.getAlertType();

		AlarmWarningInfo alarmWarningInfo = new AlarmWarningInfo();
		alarmWarningInfo.setAlarmWarningType(AlarmWarningType.valueOf(alertType.name()));
		alarmWarningInfo.setAlarmWarningSubType(AlarmWarningSubType.valueOf(alertSubtype.name()));
		return alarmWarningInfo;
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
		request.setMessageDate(LCDateUtil.getNewDateUTC());
		Process process = processRequest.getProcess();
		List<Light> lights = getLights(process.getProcessItems());

		for (Light light : lights)
		{
			request.setLight(light);
			getLightProcessorBCL().processClearAlarmNotification(request);
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
		return String.valueOf(AlertSubTypeEnum.COMMUNICATION_FAIL.getValue()).equals(actionValue);
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
