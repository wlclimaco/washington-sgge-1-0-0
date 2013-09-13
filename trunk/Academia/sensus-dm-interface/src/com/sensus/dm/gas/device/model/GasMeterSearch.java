package com.sensus.dm.gas.device.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;

/**
 * The Class GasMeterSearch.
 * 
 * @author QAT Global.
 */
@SuppressWarnings("serial")
public class GasMeterSearch extends SensusModel
{

	/** The gas meter. */
	private GasMeter gasMeter;

	/** The water gas meter status enum list. */
	private List<WaterGasMeterStatusEnum> waterGasMeterStatusEnumList;

	/** The alarm enum list. */
	private List<AlarmEnum> alarmEnumList;

	// -------------------------------------------------------------------------
	// Constructors:

	/**
	 * Instantiates a new gas meter search.
	 */
	public GasMeterSearch()
	{
	}

	/**
	 * Instantiates a new gas meter search.
	 * 
	 * @param gasMeterParam the gas meter param
	 */
	public GasMeterSearch(GasMeter gasMeterParam)
	{
		setGasMeter(gasMeterParam);
	}

	/**
	 * Instantiates a new gas meter search.
	 * 
	 * @param gasMeterParam the gas meter param
	 * @param alarmEnumListParam the alarm enum list param
	 */
	public GasMeterSearch(GasMeter gasMeterParam, List<AlarmEnum> alarmEnumListParam)
	{
		this(gasMeterParam);
		setAlarmEnumList(alarmEnumListParam);
	}

	// -------------------------------------------------------------------------
	// Getters and setters:

	/**
	 * Gets the gas meter.
	 * 
	 * @return the gas meter
	 */
	public GasMeter getGasMeter()
	{
		return gasMeter;
	}

	/**
	 * Sets the gas meter.
	 * 
	 * @param gasMeter the new gas meter
	 */
	public void setGasMeter(GasMeter gasMeter)
	{
		this.gasMeter = gasMeter;
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
		return "GasMeterSearch [getGasMeter()=" + getGasMeter() + ", getWaterGasMeterStatusEnumList()="
				+ getWaterGasMeterStatusEnumList() + ", getAlarmEnumList()=" + getAlarmEnumList()
				+ ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
