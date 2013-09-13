package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class GetDemandResponseEventStatusAction.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class GetDemandResponseEventStatusAction extends DemandResponseEventAction
{
	/**
	 * Instantiates a new meter action processor.
	 */
	public GetDemandResponseEventStatusAction()
	{
		setActionType(new ActionType(ActionTypeEnum.GET_DEMAND_RESPONSE_EVENT_STATUS));
	}

	/**
	 * Instantiates a new gets the demand response event status action.
	 * 
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public GetDemandResponseEventStatusAction(boolean isMonitored, boolean onDemand)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
	}

	/**
	 * Instantiates a new gets the demand response event status action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public GetDemandResponseEventStatusAction(Integer id, boolean isMonitored, boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new gets the demand response event status action.
	 * 
	 * @param action the action
	 */
	public GetDemandResponseEventStatusAction(DMAction action)
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
}
