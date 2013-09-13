package com.sensus.dm.elec.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class ElectricMeterSearch.
 * 
 * @author QAT Brazil.
 */
@SuppressWarnings("serial")
public class ElectricMeterSearch extends SensusModel
{
	/** The electric meter. */
	private ElectricMeter electricMeter;

	/** The electric meter lifecycle state enum list. */
	private List<ElectricMeterLifecycleStateEnum> electricMeterLifecycleStateEnumList;

	/** The remote connect status enum list. */
	private List<RemoteConnectStatusEnum> remoteConnectStatusEnumList;

	/**
	 * Instantiates a new electric meter search.
	 */
	public ElectricMeterSearch()
	{
	}

	/**
	 * Instantiates a new electric meter search.
	 * 
	 * @param electricMeterParam the electric meter param
	 */
	public ElectricMeterSearch(ElectricMeter electricMeterParam)
	{
		setElectricMeter(electricMeterParam);
	}

	/**
	 * Gets the electric meter.
	 * 
	 * @return the electric meter
	 */
	public ElectricMeter getElectricMeter()
	{
		return electricMeter;
	}

	/**
	 * Sets the electric meter.
	 * 
	 * @param electricMeter the new electric meter
	 */
	public void setElectricMeter(ElectricMeter electricMeter)
	{
		this.electricMeter = electricMeter;
	}

	/**
	 * Gets the electric meter lifecycle state enum list.
	 * 
	 * @return the electric meter lifecycle state enum list
	 */
	public List<ElectricMeterLifecycleStateEnum> getElectricMeterLifecycleStateEnumList()
	{
		return electricMeterLifecycleStateEnumList;
	}

	/**
	 * Sets the electric meter lifecycle state enum list.
	 * 
	 * @param electricMeterLifecycleStateEnumList the new electric meter lifecycle state enum list
	 */
	public void setElectricMeterLifecycleStateEnumList(
			List<ElectricMeterLifecycleStateEnum> electricMeterLifecycleStateEnumList)
	{
		this.electricMeterLifecycleStateEnumList = electricMeterLifecycleStateEnumList;
	}

	/**
	 * Adds the electric meter lifecycle state enum.
	 * 
	 * @param electricMeterLifecycleStateEnum the electric meter lifecycle state enum
	 */
	public void addElectricMeterLifecycleStateEnum(ElectricMeterLifecycleStateEnum electricMeterLifecycleStateEnum)
	{
		if (getElectricMeterLifecycleStateEnumList() == null)
		{
			setElectricMeterLifecycleStateEnumList(new ArrayList<ElectricMeterLifecycleStateEnum>());
		}

		getElectricMeterLifecycleStateEnumList().add(electricMeterLifecycleStateEnum);
	}

	/**
	 * Gets the remote connect status enum list.
	 * 
	 * @return the remote connect status enum list
	 */
	public List<RemoteConnectStatusEnum> getRemoteConnectStatusEnumList()
	{
		return remoteConnectStatusEnumList;
	}

	/**
	 * Sets the remote connect status enum list.
	 * 
	 * @param remoteConnectStatusEnum the new remote connect status enum list
	 */
	public void setRemoteConnectStatusEnumList(List<RemoteConnectStatusEnum> remoteConnectStatusEnum)
	{
		remoteConnectStatusEnumList = remoteConnectStatusEnum;
	}

	/**
	 * Adds the remote connect status enum.
	 * 
	 * @param remoteConnectStatusEnum the remote connect status enum
	 */
	public void addRemoteConnectStatusEnum(RemoteConnectStatusEnum remoteConnectStatusEnum)
	{
		if (getRemoteConnectStatusEnumList() == null)
		{
			setRemoteConnectStatusEnumList(new ArrayList<RemoteConnectStatusEnum>());
		}

		getRemoteConnectStatusEnumList().add(remoteConnectStatusEnum);
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "ElectricMeterSearch [getElectricMeter()=" + getElectricMeter()
				+ ", getElectricMeterLifecycleStateEnumList()=" + getElectricMeterLifecycleStateEnumList()
				+ ", getRemoteConnectStatusEnumList()=" + getRemoteConnectStatusEnumList() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
