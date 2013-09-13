package com.sensus.dm.elec.action.model;

import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class CancelSendHanTextMessageAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class CancelDemandResponseEventAction extends DMAction
{

	/**
	 * Instantiates a new cancel event action.
	 */
	public CancelDemandResponseEventAction()
	{
		super(new ActionType(ActionTypeEnum.CANCEL_DEMAND_RESPONSE_EVENT));
	}

	/**
	 * Instantiates a new cancel demand response event action.
	 * 
	 * @param createUser the create user
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param processId the process id
	 * @param deviceParam the device param
	 */
	public CancelDemandResponseEventAction(String createUser, boolean isMonitored, boolean onDemand, Integer processId,
			Device deviceParam)
	{
		this();
		setCreateUser(createUser);
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
		setProcessId(processId);
		addDevice(deviceParam);
	}

	/**
	 * Instantiates a new cancel demand response event action.
	 * 
	 * @param createUser the create user
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param processId the process id
	 * @param rniEventId the rni event id
	 * @param deviceParam the device param
	 */
	public CancelDemandResponseEventAction(String createUser, boolean isMonitored, boolean onDemand, Integer processId,
			Integer rniEventId, List<Device> deviceParam)
	{
		this();
		setCreateUser(createUser);
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
		setProcessId(processId);
		setRniEventId(rniEventId);
		setDevices(deviceParam);
	}

	/**
	 * Instantiates a new cancel demand response event action.
	 * 
	 * @param action the action
	 */
	public CancelDemandResponseEventAction(DMAction action)
	{
		this();
		setId(action.getId());
		setCreateDate(action.getCreateDate());
		setCreateUser(action.getCreateUser());
		setDevices(action.getDevices());
		setGroups(action.getGroups());
		setActionTime(action.getActionTime());
		setIsMonitored(action.getIsMonitored());
		setProcessId(action.getProcessId());
		setTags(action.getTags());
		setTotalDevices(action.getTotalDevices());
	}

	/**
	 * Checks if is process required.
	 * 
	 * @return the boolean
	 */
	@Override
	public Boolean isProcessRequired()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.action.model.DMAction#toString()
	 */
	@Override
	public String toString()
	{
		return "CancelDemandResponseEventAction [toString()=" + super.toString() + "]";
	}

}
