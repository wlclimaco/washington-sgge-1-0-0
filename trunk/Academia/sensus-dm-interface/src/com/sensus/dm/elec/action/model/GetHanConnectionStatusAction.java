package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class GetHanConnectionStatusAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class GetHanConnectionStatusAction extends HanAction
{
	/**
	 * Instantiates a new gets the han connection status action.
	 */
	public GetHanConnectionStatusAction()
	{
		super(new ActionType(ActionTypeEnum.GET_HAN_CONNECTION_STATUS));
	}

	/**
	 * Instantiates a new gets the han connection status action.
	 * 
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public GetHanConnectionStatusAction(boolean isMonitored, boolean onDemand)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
	}

	/**
	 * Instantiates a new gets the han connection status action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public GetHanConnectionStatusAction(Integer id, boolean isMonitored, boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new gets the han connection status action.
	 * 
	 * @param action the action
	 */
	public GetHanConnectionStatusAction(DMAction action)
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
