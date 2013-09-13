package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class GetRemoteConnectStatusAction.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class GetRemoteConnectStatusAction extends ElectricMeterAction
{
	/**
	 * Instantiates a new remote connect action.
	 * 
	 */
	public GetRemoteConnectStatusAction()
	{
		super(new ActionType(ActionTypeEnum.GET_REMOTE_CONNECT_STATUS));
	}

	/**
	 * Instantiates a new gets the remote connect status action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 */
	public GetRemoteConnectStatusAction(Boolean onDemand, Boolean isMonitored)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
	}

	/**
	 * Instantiates a new gets the remote connect status action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public GetRemoteConnectStatusAction(Integer id, boolean isMonitored, boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new gets the remote connect status action.
	 * 
	 * @param action the action
	 */
	public GetRemoteConnectStatusAction(DMAction action)
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
