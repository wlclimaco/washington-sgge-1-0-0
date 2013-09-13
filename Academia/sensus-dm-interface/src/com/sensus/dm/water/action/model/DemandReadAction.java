package com.sensus.dm.water.action.model;

import java.util.Date;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class DemandResponseAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class DemandReadAction extends WaterMeterAction
{
	/**
	 * Instantiates a new demand response action.
	 */
	public DemandReadAction()
	{
		super(new ActionType(ActionTypeEnum.DEMAND_READ));
	}

	/**
	 * Instantiates a new demand read ping action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 * @param actionTime the action time
	 */
	public DemandReadAction(Boolean onDemand, Boolean isMonitored, Date actionTime)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
		setActionTime(actionTime);
	}

	/**
	 * Instantiates a new demand reset event action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param actionTime the action time
	 */
	public DemandReadAction(Integer id, Boolean isMonitored, Boolean onDemand, Date actionTime)
	{
		this(isMonitored, onDemand, actionTime);
		setId(id);
	}

	/**
	 * Instantiates a new demand read action.
	 * 
	 * @param action the action
	 */
	public DemandReadAction(DMAction action)
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
