package com.sensus.dm.elec.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;

/**
 * The Class HanDeviceSearch.
 * 
 * @author QAT Brazil.
 */
@SuppressWarnings("serial")
public class HanDeviceSearch extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The han device. */
	private HanDevice hanDevice;

	/** The han device type enum list. */
	private List<HanDeviceTypeEnum> hanDeviceTypeEnumList;

	/** The han lifecycle state enum list. */
	private List<HanLifecycleStateEnum> hanLifecycleStateEnumList;

	/**
	 * Instantiates a new han device search.
	 */
	public HanDeviceSearch()
	{
	}

	/**
	 * Instantiates a new han device search.
	 * 
	 * @param hanDeviceParam the han device param
	 */
	public HanDeviceSearch(HanDevice hanDeviceParam)
	{
		setHanDevice(hanDeviceParam);
	}

	/**
	 * Instantiates a new han device search.
	 * 
	 * @param hanDeviceTypesParam the han device types param
	 */
	public HanDeviceSearch(List<HanDeviceTypeEnum> hanDeviceTypesParam)
	{
		setHanDeviceTypeEnumList(hanDeviceTypesParam);
	}

	/**
	 * Instantiates a new han device search.
	 * 
	 * @param hanDeviceTypeParam the han device type param
	 */
	public HanDeviceSearch(HanDeviceTypeEnum hanDeviceTypeParam)
	{
		addHanDeviceTypeEnum(hanDeviceTypeParam);
	}

	/**
	 * Gets the han device.
	 * 
	 * @return the han device
	 */
	public HanDevice getHanDevice()
	{
		return hanDevice;
	}

	/**
	 * Sets the han device.
	 * 
	 * @param hanDevice the new han device
	 */
	public void setHanDevice(HanDevice hanDevice)
	{
		this.hanDevice = hanDevice;
	}

	/**
	 * Gets the han device type enum list.
	 * 
	 * @return the han device type enum list
	 */
	public List<HanDeviceTypeEnum> getHanDeviceTypeEnumList()
	{
		return hanDeviceTypeEnumList;
	}

	/**
	 * Sets the han device type enum list.
	 * 
	 * @param hanDeviceTypeEnumList the new han device type enum list
	 */
	public void setHanDeviceTypeEnumList(List<HanDeviceTypeEnum> hanDeviceTypeEnumList)
	{
		this.hanDeviceTypeEnumList = hanDeviceTypeEnumList;
	}

	/**
	 * Adds the han device type enum.
	 * 
	 * @param hanDeviceTypeEnumParam the han device type enum param
	 */
	public void addHanDeviceTypeEnum(HanDeviceTypeEnum hanDeviceTypeEnumParam)
	{
		if (getHanDeviceTypeEnumList() == null)
		{
			setHanDeviceTypeEnumList(new ArrayList<HanDeviceTypeEnum>());
		}

		getHanDeviceTypeEnumList().add(hanDeviceTypeEnumParam);
	}

	/**
	 * Gets the first device type.
	 * 
	 * @return the first device type
	 */
	public HanDeviceTypeEnum getFirstDeviceType()
	{
		if (getHanDeviceTypeEnumList() != null && !getHanDeviceTypeEnumList().isEmpty())
		{
			return getHanDeviceTypeEnumList().get(FIRST);
		}
		return null;
	}

	/**
	 * Gets the han lifecycle state enum list.
	 * 
	 * @return the han lifecycle state enum list
	 */
	public List<HanLifecycleStateEnum> getHanLifecycleStateEnumList()
	{
		return hanLifecycleStateEnumList;
	}

	/**
	 * Sets the han lifecycle state enum list.
	 * 
	 * @param hanLifecycleStateEnumList the new han lifecycle state enum list
	 */
	public void setHanLifecycleStateEnumList(List<HanLifecycleStateEnum> hanLifecycleStateEnumList)
	{
		this.hanLifecycleStateEnumList = hanLifecycleStateEnumList;
	}

	/**
	 * Adds the han lifecycle state enum.
	 * 
	 * @param hanLifecycleStateEnumParam the han lifecycle state enum param
	 */
	public void addHanLifecycleStateEnum(HanLifecycleStateEnum hanLifecycleStateEnumParam)
	{
		if (getHanLifecycleStateEnumList() == null)
		{
			setHanLifecycleStateEnumList(new ArrayList<HanLifecycleStateEnum>());
		}

		getHanLifecycleStateEnumList().add(hanLifecycleStateEnumParam);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "HanDeviceSearch [getHanDevice()=" + getHanDevice() + ", getHanDeviceTypeEnumList()="
				+ getHanDeviceTypeEnumList() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + ", getHanLifecycleStateEnumList()="
				+ getHanLifecycleStateEnumList() + "]";
	}

}
