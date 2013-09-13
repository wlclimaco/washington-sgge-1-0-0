package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class ImportHanDeviceAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class DeleteHanDeviceAction extends HanAction
{
	/**
	 * Instantiates a new delete han device action.
	 */
	public DeleteHanDeviceAction()
	{
		super(new ActionType(ActionTypeEnum.DELETE_HAN_DEVICE));
	}

	/**
	 * Instantiates a new delete han device action.
	 * 
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public DeleteHanDeviceAction(Boolean isMonitored, Boolean onDemand)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
	}

	/**
	 * Instantiates a new delete han device action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public DeleteHanDeviceAction(Integer id, Boolean isMonitored, Boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new delete han device action.
	 * 
	 * @param action the action
	 */
	public DeleteHanDeviceAction(DMAction action)
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
