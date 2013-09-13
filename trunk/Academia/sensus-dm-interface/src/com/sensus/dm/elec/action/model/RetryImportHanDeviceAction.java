package com.sensus.dm.elec.action.model;

import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;

/**
 * The Class RetryImportHanDeviceAction.
 * 
 * @author - QAT Brazil.
 */
@SuppressWarnings("serial")
public class RetryImportHanDeviceAction extends HanAction
{
	/** The connected. */
	private Integer connected;

	/** The disconnected. */
	private Integer disconnected;

	/**
	 * Instantiates a new import han device action.
	 */
	public RetryImportHanDeviceAction()
	{
		super(new ActionType(ActionTypeEnum.RETRY_IMPORT_HAN_DEVICE));
	}

	/**
	 * Instantiates a new import han device action.
	 * 
	 * @param id the id
	 */
	public RetryImportHanDeviceAction(Integer id)
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
	public RetryImportHanDeviceAction(Boolean isMonitored, Boolean onDemand)
	{
		this();
		setIsMonitored(isMonitored);
		setOnDemand(onDemand);
	}

	/**
	 * Instantiates a new import han device action.
	 * 
	 * @param id the id
	 * @param isMonitored the is monitored
	 * @param onDemand the on demand
	 */
	public RetryImportHanDeviceAction(Integer id, boolean isMonitored, boolean onDemand)
	{
		this(isMonitored, onDemand);
		setId(id);
	}

	/**
	 * Instantiates a new retry import han device action.
	 * 
	 * @param action the action
	 */
	public RetryImportHanDeviceAction(DMAction action)
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
