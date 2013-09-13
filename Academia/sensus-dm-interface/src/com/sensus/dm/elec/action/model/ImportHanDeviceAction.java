package com.sensus.dm.elec.action.model;

import java.util.Date;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class ImportHanDeviceAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class ImportHanDeviceAction extends HanAction
{
	/** The connected. */
	private Integer connected;

	/** The disconnected. */
	private Integer disconnected;

	/**
	 * Instantiates a new import han device action.
	 */
	public ImportHanDeviceAction()
	{
		super(new ActionType(ActionTypeEnum.IMPORT_HAN_DEVICE));
	}

	/**
	 * Instantiates a new import han device action.
	 * 
	 * @param id the id
	 */
	public ImportHanDeviceAction(Integer id)
	{
		this();
		setId(id);
		setConnected(0);
		setDisconnected(0);
	}

	/**
	 * Instantiates a new import han device action.
	 * 
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public ImportHanDeviceAction(Boolean isMonitored, Boolean onDemand)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
	}

	/**
	 * Instantiates a new import han device action.
	 * 
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 * @param actionTime the action time
	 */
	public ImportHanDeviceAction(Boolean isMonitored, Boolean onDemand, Date actionTime)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
		setActionTime(actionTime);
	}

	/**
	 * Instantiates a new import han device action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public ImportHanDeviceAction(Integer id, boolean isMonitored, boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new import han device action.
	 * 
	 * @param action the action
	 */
	public ImportHanDeviceAction(DMAction action)
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
	 * Gets the connected.
	 * 
	 * @return the connected
	 */
	public Integer getConnected()
	{
		return connected;
	}

	/**
	 * Sets the connected.
	 * 
	 * @param connected the new connected
	 */
	public void setConnected(Integer connected)
	{
		this.connected = connected;
	}

	/**
	 * Gets the disconnected.
	 * 
	 * @return the disconnected
	 */
	public Integer getDisconnected()
	{
		return disconnected;
	}

	/**
	 * Sets the disconnected.
	 * 
	 * @param disconnected the new disconnected
	 */
	public void setDisconnected(Integer disconnected)
	{
		this.disconnected = disconnected;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.model.HanAction#toString()
	 */
	@Override
	public String toString()
	{
		return "ImportHanDeviceAction [getConnected()=" + getConnected() + ", getDisconnected()=" + getDisconnected()
				+ ", toString()=" + super.toString() + "]";
	}
}
