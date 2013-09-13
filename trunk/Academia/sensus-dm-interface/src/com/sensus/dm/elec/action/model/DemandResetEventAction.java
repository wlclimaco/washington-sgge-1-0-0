package com.sensus.dm.elec.action.model;

import java.util.Date;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class DemandResetEventAction.
 * 
 * @author QAT Global
 */
@SuppressWarnings("serial")
public class DemandResetEventAction extends ElectricMeterAction
{
	/**
	 * Instantiates a new meter action processor.
	 */
	public DemandResetEventAction()
	{
		super(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT));
	}

	/**
	 * Instantiates a new demand reset event action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 * @param actionTime the action time
	 */
	public DemandResetEventAction(Boolean onDemand, Boolean isMonitored, Date actionTime)
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
	public DemandResetEventAction(Integer id, Boolean isMonitored, Boolean onDemand, Date actionTime)
	{
		this(isMonitored, onDemand, actionTime);
		setId(id);
	}

	/**
	 * Instantiates a new demand reset event action.
	 * 
	 * @param action the action
	 */
	public DemandResetEventAction(DMAction action)
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
