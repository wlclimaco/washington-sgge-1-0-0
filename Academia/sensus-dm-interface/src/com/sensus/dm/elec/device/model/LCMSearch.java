package com.sensus.dm.elec.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.device.model.AlarmEnum;

/**
 * The Class LCMSearch.
 * 
 * @author QAT Brazil.
 */
@SuppressWarnings("serial")
public class LCMSearch extends SensusModel
{
	/** The lcm. */
	private LCM lcm;

	/** The lcm type enum list. */
	private List<LCMTypeEnum> lcmTypeEnumList;

	/** The lcm life cycle state enum list. */
	private List<LCMLifecycleStateEnum> lcmLifecycleStateEnumList;

	/** The alarm enum list. */
	private List<AlarmEnum> alarmEnumList;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new lCM search.
	 */
	public LCMSearch()
	{
	}

	/**
	 * Instantiates a new lCM search.
	 * 
	 * @param lcmParam the lcm param
	 */
	public LCMSearch(LCM lcmParam)
	{
		setLcm(lcmParam);
	}

	/**
	 * Instantiates a new lCM search.
	 * 
	 * @param lcmParam the lcm param
	 * @param alarmEnumListParam the alarm enum list param
	 */
	public LCMSearch(LCM lcmParam, List<AlarmEnum> alarmEnumListParam)
	{
		this(lcmParam);
		setAlarmEnumList(alarmEnumListParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the lcm.
	 * 
	 * @return the lcm
	 */
	public LCM getLcm()
	{
		return lcm;
	}

	/**
	 * Sets the lcm.
	 * 
	 * @param lcm the new lcm
	 */
	public void setLcm(LCM lcm)
	{
		this.lcm = lcm;
	}

	/**
	 * Gets the lcm type enum list.
	 * 
	 * @return the lcm type enum list
	 */
	public List<LCMTypeEnum> getLcmTypeEnumList()
	{
		return lcmTypeEnumList;
	}

	/**
	 * Sets the lcm type enum list.
	 * 
	 * @param lcmTypeEnumList the new lcm type enum list
	 */
	public void setLcmTypeEnumList(List<LCMTypeEnum> lcmTypeEnumList)
	{
		this.lcmTypeEnumList = lcmTypeEnumList;
	}

	/**
	 * Adds the lcm type enum.
	 * 
	 * @param lcmTypeEnumParam the lcm type enum param
	 */
	public void addLcmTypeEnum(LCMTypeEnum lcmTypeEnumParam)
	{
		if (getLcmTypeEnumList() == null)
		{
			setLcmTypeEnumList(new ArrayList<LCMTypeEnum>());
		}

		getLcmTypeEnumList().add(lcmTypeEnumParam);
	}

	/**
	 * Gets the lcm life cycle state enum list.
	 * 
	 * @return the lcm life cycle state enum list
	 */
	public List<LCMLifecycleStateEnum> getLcmLifecycleStateEnumList()
	{
		return lcmLifecycleStateEnumList;
	}

	/**
	 * Sets the lcm life cycle state enum list.
	 * 
	 * @param lcmLifecycleStateEnumList the new lcm life cycle state enum list
	 */
	public void setLcmLifecycleStateEnumList(List<LCMLifecycleStateEnum> lcmLifecycleStateEnumList)
	{
		this.lcmLifecycleStateEnumList = lcmLifecycleStateEnumList;
	}

	/**
	 * Adds the lcm life cycle state enum.
	 * 
	 * @param lcmLifecycleStateEnumParam the lcm life cycle state enum param
	 */
	public void addLcmLifecycleStateEnum(LCMLifecycleStateEnum lcmLifecycleStateEnumParam)
	{
		if (getLcmLifecycleStateEnumList() == null)
		{
			setLcmLifecycleStateEnumList(new ArrayList<LCMLifecycleStateEnum>());
		}

		getLcmLifecycleStateEnumList().add(lcmLifecycleStateEnumParam);
	}

	/**
	 * Gets the alarm enum list.
	 * 
	 * @return the alarm enum list
	 */
	public List<AlarmEnum> getAlarmEnumList()
	{
		return alarmEnumList;
	}

	/**
	 * Sets the alarm enum list.
	 * 
	 * @param alarmEnumList the new alarm enum list
	 */
	public void setAlarmEnumList(List<AlarmEnum> alarmEnumList)
	{
		this.alarmEnumList = alarmEnumList;
	}

	/**
	 * Adds the alarm enum.
	 * 
	 * @param alarmEnum the alarm enum
	 */
	public void addAlarmEnum(AlarmEnum alarmEnum)
	{
		if (getAlarmEnumList() == null)
		{
			setAlarmEnumList(new ArrayList<AlarmEnum>());
		}

		getAlarmEnumList().add(alarmEnum);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LCMSearch [getLcm()=" + getLcm() + ", getLcmTypeEnumList()=" + getLcmTypeEnumList()
				+ ", getLcmLifecycleStateEnumList()=" + getLcmLifecycleStateEnumList() + ", getAlarmEnumList()="
				+ getAlarmEnumList() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
