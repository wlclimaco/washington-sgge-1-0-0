package com.sensus.dm.elec.action.model;

import java.util.List;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.elec.device.model.LCMRelay;

/**
 * The Class GetTamperDetectTimerAction.
 * 
 * @author QAT Global
 * 
 */
@SuppressWarnings("serial")
public class GetTamperDetectTimerAction extends ElectricMeterAction
{

	/** The lcm relays. */
	private List<LCMRelay> lcmRelays;

	/**
	 * Gets the lcm relays.
	 * 
	 * @return the lcm relays
	 */
	public List<LCMRelay> getLcmRelays()
	{
		return lcmRelays;
	}

	/**
	 * Sets the lcm relays.
	 * 
	 * @param lcmRelays the new lcm relays
	 */
	public void setLcmRelays(List<LCMRelay> lcmRelays)
	{
		this.lcmRelays = lcmRelays;
	}

	/**
	 * Instantiates a new meter action processor.
	 */
	public GetTamperDetectTimerAction()
	{
		setActionType(new ActionType(ActionTypeEnum.GET_TAMPER_DETECT_TIMER));
	}

	/**
	 * Instantiates a new demand response setup action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 */
	public GetTamperDetectTimerAction(Boolean onDemand, Boolean isMonitored)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
	}

	/**
	 * Instantiates a new gets the tamper detect timer action.
	 * 
	 * @param action the action
	 */
	public GetTamperDetectTimerAction(DMAction action)
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

	@Override
	public String toString()
	{
		return "GetTamperDetectTimerAction [toString()=" + super.toString() + "]";
	}

}
