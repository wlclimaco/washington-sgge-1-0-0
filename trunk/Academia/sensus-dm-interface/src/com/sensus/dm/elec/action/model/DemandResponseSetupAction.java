package com.sensus.dm.elec.action.model;

import java.util.Date;
import java.util.List;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.elec.device.model.LCMRelay;

/**
 * The Class DemandResponseSetupAction.
 * 
 * @author QAT Global
 * 
 */
@SuppressWarnings("serial")
public class DemandResponseSetupAction extends DemandResponseAction
{
	/** The last date. */
	private Date lastDate;

	/** The start minutes. */
	private Integer startMinutes;

	/** The end minutes. */
	private Integer endMinutes;

	/** The lcm relays. */
	private List<LCMRelay> lcmRelays;

	/**
	 * Instantiates a new meter action processor.
	 */
	public DemandResponseSetupAction()
	{
		super(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_SETUP));
	}

	/**
	 * Instantiates a new demand response setup action.
	 * 
	 * @param onDemand the on demand
	 * @param isMonitored the is monitored
	 */
	public DemandResponseSetupAction(Boolean onDemand, Boolean isMonitored)
	{
		this();
		setOnDemand(onDemand);
		setIsMonitored(isMonitored);
	}

	/**
	 * Instantiates a new demand response setup action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public DemandResponseSetupAction(Integer id, Boolean isMonitored, Boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new demand response setup action.
	 * 
	 * @param action the action
	 */
	public DemandResponseSetupAction(DMAction action)
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
	 * Gets the last date.
	 * 
	 * @return the last date
	 */
	public Date getLastDate()
	{
		return lastDate;
	}

	/**
	 * Sets the last date.
	 * 
	 * @param lastDate the new last date
	 */
	public void setLastDate(Date lastDate)
	{
		this.lastDate = lastDate;
	}

	/**
	 * Gets the start minutes.
	 * 
	 * @return the start minutes
	 */
	public Integer getStartMinutes()
	{
		return startMinutes;
	}

	/**
	 * Sets the start minutes.
	 * 
	 * @param startMinutes the new start minutes
	 */
	public void setStartMinutes(Integer startMinutes)
	{
		this.startMinutes = startMinutes;
	}

	/**
	 * Gets the end minutes.
	 * 
	 * @return the end minutes
	 */
	public Integer getEndMinutes()
	{
		return endMinutes;
	}

	/**
	 * Sets the end minutes.
	 * 
	 * @param endMinutes the new end minutes
	 */
	public void setEndMinutes(Integer endMinutes)
	{
		this.endMinutes = endMinutes;
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
	 * @see com.sensus.dm.elec.action.model.DemandResponseAction#toString()
	 */
	@Override
	public String toString()
	{
		return "DemandResponseSetupAction [getLastDate()=" + getLastDate() + ", getStartMinutes()=" + getStartMinutes()
				+ ", getEndMinutes()=" + getEndMinutes() + ", toString()=" + super.toString() + "]";
	}

}
