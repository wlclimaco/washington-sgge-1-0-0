package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class DisconnectHanDeviceAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class DisconnectHanDeviceAction extends HanAction
{
	/**
	 * Instantiates a new disconnect han device action.
	 */
	public DisconnectHanDeviceAction()
	{
		super(new ActionType(ActionTypeEnum.DISCONNECT_HAN_DEVICE));
	}

	/**
	 * Instantiates a new disconnect han device action.
	 * 
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public DisconnectHanDeviceAction(boolean isMonitored, boolean onDemand)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
	}

	/**
	 * Instantiates a new disconnect han device action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public DisconnectHanDeviceAction(Integer id, boolean isMonitored, boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new disconnect han device action.
	 * 
	 * @param action the action
	 */
	public DisconnectHanDeviceAction(DMAction action)
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

}
