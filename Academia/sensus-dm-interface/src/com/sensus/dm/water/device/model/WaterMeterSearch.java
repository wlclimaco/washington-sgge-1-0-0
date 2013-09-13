package com.sensus.dm.water.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.device.model.AlarmEnum;

/**
 * The Class WaterMeterSearch.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class WaterMeterSearch extends SensusModel
{

	/** The water meter. */
	private WaterMeter waterMeter;

	/** The water gas meter status enum list. */
	private List<WaterGasMeterStatusEnum> waterGasMeterStatusEnumList;

	/** The alarm enum list. */
	private List<AlarmEnum> alarmEnumList;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new water meter search.
	 */
	public WaterMeterSearch()
	{
	}

	/**
	 * Instantiates a new water meter search.
	 * 
	 * @param waterMeterParam the water meter param
	 */
	public WaterMeterSearch(WaterMeter waterMeterParam)
	{
		setWaterMeter(waterMeterParam);
	}

	/**
	 * Instantiates a new water meter search.
	 * 
	 * @param waterMeterParam the water meter param
	 * @param alarmEnumListParam the alarm enum list param
	 */
	public WaterMeterSearch(WaterMeter waterMeterParam, List<AlarmEnum> alarmEnumListParam)
	{
		this(waterMeterParam);
		setAlarmEnumList(alarmEnumListParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the water meter.
	 * 
	 * @return the water meter
	 */
	public WaterMeter getWaterMeter()
	{
		return waterMeter;
	}

	/**
	 * Sets the water meter.
	 * 
	 * @param waterMeter the new water meter
	 */
	public void setWaterMeter(WaterMeter waterMeter)
	{
		this.waterMeter = waterMeter;
	}

	/**
	 * Gets the water gas meter status enum list.
	 * 
	 * @return the water gas meter status enum list
	 */
	public List<WaterGasMeterStatusEnum> getWaterGasMeterStatusEnumList()
	{
		return waterGasMeterStatusEnumList;
	}

	/**
	 * Sets the water gas meter status enum list.
	 * 
	 * @param waterGasMeterStatusEnumList the new water gas meter status enum list
	 */
	public void setWaterGasMeterStatusEnumList(List<WaterGasMeterStatusEnum> waterGasMeterStatusEnumList)
	{
		this.waterGasMeterStatusEnumList = waterGasMeterStatusEnumList;
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
		return "WaterMeterSearch [getWaterMeter()=" + getWaterMeter() + ", getWaterGasMeterStatusEnumList()="
				+ getWaterGasMeterStatusEnumList() + ", getAlarmEnumList()=" + getAlarmEnumList()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
