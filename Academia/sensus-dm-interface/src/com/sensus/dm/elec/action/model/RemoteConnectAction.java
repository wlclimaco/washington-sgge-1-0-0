package com.sensus.dm.elec.action.model;

import java.util.Date;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class RemoteConnectAction.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class RemoteConnectAction extends ElectricMeterAction
{
	/**
	 * Instantiates a new remote connect action.
	 * 
	 */
	public RemoteConnectAction()
	{
		super(new ActionType(ActionTypeEnum.REMOTE_CONNECT));
	}

	/**
	 * Instantiates a new remote connect action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 * @param actionTime the action time
	 */
	public RemoteConnectAction(Boolean onDemand, Boolean isMonitored, Date actionTime)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
		setActionTime(actionTime);
	}

	/**
	 * Instantiates a new remote connect action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param actionTime the action time
	 */
	public RemoteConnectAction(Integer id, boolean isMonitored, boolean onDemand, Date actionTime)
	{
		this(isMonitored, onDemand, actionTime);
		setId(id);
	}

	/**
	 * Instantiates a new remote connect action.
	 * 
	 * @param action the action view
	 */
	public RemoteConnectAction(DMAction action)
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
