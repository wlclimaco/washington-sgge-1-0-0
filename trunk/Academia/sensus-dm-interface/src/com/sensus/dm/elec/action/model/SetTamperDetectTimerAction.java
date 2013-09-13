package com.sensus.dm.elec.action.model;

import java.util.List;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.elec.device.model.LCMRelay;

/**
 * The Class SetTamperDetectTimerAction.
 * 
 * @author QAT Global
 * 
 */
@SuppressWarnings("serial")
public class SetTamperDetectTimerAction extends ElectricMeterAction
{

	/** The lcm relays. */
	private List<LCMRelay> lcmRelays;

	/**
	 * Instantiates a new meter action processor.
	 */
	public SetTamperDetectTimerAction()
	{
		setActionType(new ActionType(ActionTypeEnum.SET_TAMPER_DETECT_TIMER));
	}

	/**
	 * Instantiates a new demand response setup action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 */
	public SetTamperDetectTimerAction(Boolean onDemand, Boolean isMonitored)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
	}

	/**
	 * Instantiates a new sets the tamper detect timer action.
	 * 
	 * @param id the id
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 */
	public SetTamperDetectTimerAction(Integer id, Boolean onDemand, Boolean isMonitored)
	{
		this(onDemand, isMonitored);
		setId(id);
	}

	/**
	 * Instantiates a new sets the tamper detect timer action.
	 * 
	 * @param action the action
	 */
	public SetTamperDetectTimerAction(DMAction action)
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.action.model.DMAction#toString()
	 */
	@Override
	public String toString()
	{
		return "SetTamperDetectTimerAction [toString()=" + super.toString() + "]";
	}

}
