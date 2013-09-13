package com.sensus.dm.elec.action.model;

import java.util.Date;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class RemoteArmConnectAction.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class RemoteArmConnectAction extends ElectricMeterAction
{
	/**
	 * Instantiates a new remote arm connect action.
	 * 
	 */
	public RemoteArmConnectAction()
	{
		super(new ActionType(ActionTypeEnum.REMOTE_ARM_CONNECT));
	}

	/**
	 * Instantiates a new remote arm connect action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 * @param actionTime the action time
	 */
	public RemoteArmConnectAction(Boolean onDemand, Boolean isMonitored, Date actionTime)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
		setActionTime(actionTime);
	}

	/**
	 * Instantiates a new remote arm connect action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param actionTime the action time
	 */
	public RemoteArmConnectAction(Integer id, boolean isMonitored, boolean onDemand, Date actionTime)
	{
		this(isMonitored, onDemand, actionTime);
		setId(id);
	}

	/**
	 * Instantiates a new remote arm connect action.
	 * 
	 * @param action the action view
	 */
	public RemoteArmConnectAction(DMAction action)
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
	 * Send by device.
	 * 
	 * @return the boolean
	 */
	@Override
	public Boolean sendByDevice()
	{
		return false;
	}
}
