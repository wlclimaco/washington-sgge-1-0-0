package com.sensus.lc.light.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

/**
 * Model object that contains light consumption properties.
 *
 * @see Light for more details about the light object and its relationships.
 *
 * @author Thiago Silva - QAT
 *
 */
@SuppressWarnings("serial")
public class LastOperationalData extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer parentId;

	/** The ac voltage. */
	private Float acVoltage;

	/** The ac voltage min. */
	private Float acVoltageMin;

	/** The ac voltage min date. */
	private Date acVoltageMinDate;

	/** The ac voltage max. */
	private Float acVoltageMax;

	/** The ac voltage max date. */
	private Date acVoltageMaxDate;

	/** The ac current. */
	private Float acCurrent;

	/** The ac current min. */
	private Float acCurrentMin;

	/** The ac current min date. */
	private Date acCurrentMinDate;

	/** The ac current max. */
	private Float acCurrentMax;

	/** The ac current max date. */
	private Date acCurrentMaxDate;

	/** The consumption. */
	private Float consumption;

	/** The consumption min. */
	private Float consumptionMin;

	/** The consumption max. */
	private Float consumptionMax;

	/** The consumption date. */
	private Date consumptionDate;

	public LastOperationalData()
	{
		super();
	}

	/**
	 * Gets the light id.
	 *
	 * @return the lightId
	 */
	public Integer getParentId()
	{
		return parentId;
	}

	/**
	 * Sets the light id.
	 *
	 * @param lightId the lightId to set
	 */
	public void setParentId(Integer lightId)
	{
		this.parentId = lightId;
	}

	/**
	 * Gets the consumption max.
	 *
	 * @return the consumptionMax
	 */
	public Float getConsumptionMax()
	{
		return consumptionMax;
	}

	/**
	 * Sets the consumption max.
	 *
	 * @param consumptionMax the consumptionMax to set
	 */
	public void setConsumptionMax(Float consumptionMax)
	{
		this.consumptionMax = consumptionMax;
	}

	/**
	 * Gets the ac voltage.
	 *
	 * @return the ac voltage
	 */
	public Float getAcVoltage()
	{
		return acVoltage;
	}

	/**
	 * Sets the ac voltage.
	 *
	 * @param acVoltage the new ac voltage
	 */
	public void setAcVoltage(Float acVoltage)
	{
		this.acVoltage = acVoltage;
	}

	/**
	 * Gets the ac voltage min.
	 *
	 * @return the ac voltage min
	 */
	public Float getAcVoltageMin()
	{
		return acVoltageMin;
	}

	/**
	 * Sets the ac voltage min.
	 *
	 * @param acVoltageMin the new ac voltage min
	 */
	public void setAcVoltageMin(Float acVoltageMin)
	{
		this.acVoltageMin = acVoltageMin;
	}

	/**
	 * Gets the ac voltage max.
	 *
	 * @return the ac voltage max
	 */
	public Float getAcVoltageMax()
	{
		return acVoltageMax;
	}

	/**
	 * Sets the ac voltage max.
	 *
	 * @param acVoltageMax the new ac voltage max
	 */
	public void setAcVoltageMax(Float acVoltageMax)
	{
		this.acVoltageMax = acVoltageMax;
	}

	/**
	 * Gets the ac current.
	 *
	 * @return the ac current
	 */
	public Float getAcCurrent()
	{
		return acCurrent;
	}

	/**
	 * Sets the ac current.
	 *
	 * @param acCurrent the new ac current
	 */
	public void setAcCurrent(Float acCurrent)
	{
		this.acCurrent = acCurrent;
	}

	/**
	 * Gets the ac current min.
	 *
	 * @return the ac current min
	 */
	public Float getAcCurrentMin()
	{
		return acCurrentMin;
	}

	/**
	 * Sets the ac current min.
	 *
	 * @param acCurrentMin the new ac current min
	 */
	public void setAcCurrentMin(Float acCurrentMin)
	{
		this.acCurrentMin = acCurrentMin;
	}

	/**
	 * Gets the ac current max.
	 *
	 * @return the ac current max
	 */
	public Float getAcCurrentMax()
	{
		return acCurrentMax;
	}

	/**
	 * Sets the ac current max.
	 *
	 * @param acCurrentMax the new ac current max
	 */
	public void setAcCurrentMax(Float acCurrentMax)
	{
		this.acCurrentMax = acCurrentMax;
	}

	/**
	 * Gets the consumption.
	 *
	 * @return the consumption
	 */
	public Float getConsumption()
	{
		return consumption;
	}

	/**
	 * Sets the consumption.
	 *
	 * @param consumption the consumption to set
	 */
	public void setConsumption(Float consumption)
	{
		this.consumption = consumption;
	}

	/**
	 * Gets the consumption min.
	 *
	 * @return the consumptionMin
	 */
	public Float getConsumptionMin()
	{
		return consumptionMin;
	}

	/**
	 * Sets the consumption min.
	 *
	 * @param consumptionMin the consumptionMin to set
	 */
	public void setConsumptionMin(Float consumptionMin)
	{
		this.consumptionMin = consumptionMin;
	}

	/**
	 * Gets the ac voltage min date.
	 *
	 * @return the acVoltageMinDate
	 */
	public Date getAcVoltageMinDate()
	{
		return acVoltageMinDate;
	}

	/**
	 * Sets the ac voltage min date.
	 *
	 * @param acVoltageMinDate the acVoltageMinDate to set
	 */
	public void setAcVoltageMinDate(Date acVoltageMinDate)
	{
		this.acVoltageMinDate = acVoltageMinDate;
	}

	/**
	 * Gets the ac voltage max date.
	 *
	 * @return the acVoltageMaxDate
	 */
	public Date getAcVoltageMaxDate()
	{
		return acVoltageMaxDate;
	}

	/**
	 * Sets the ac voltage max date.
	 *
	 * @param acVoltageMaxDate the acVoltageMaxDate to set
	 */
	public void setAcVoltageMaxDate(Date acVoltageMaxDate)
	{
		this.acVoltageMaxDate = acVoltageMaxDate;
	}

	/**
	 * Gets the ac current min date.
	 *
	 * @return the acCurrentMinDate
	 */
	public Date getAcCurrentMinDate()
	{
		return acCurrentMinDate;
	}

	/**
	 * Sets the ac current min date.
	 *
	 * @param acCurrentMinDate the acCurrentMinDate to set
	 */
	public void setAcCurrentMinDate(Date acCurrentMinDate)
	{
		this.acCurrentMinDate = acCurrentMinDate;
	}

	/**
	 * Gets the ac current max date.
	 *
	 * @return the acCurrentMaxDate
	 */
	public Date getAcCurrentMaxDate()
	{
		return acCurrentMaxDate;
	}

	/**
	 * Sets the ac current max date.
	 *
	 * @param acCurrentMaxDate the acCurrentMaxDate to set
	 */
	public void setAcCurrentMaxDate(Date acCurrentMaxDate)
	{
		this.acCurrentMaxDate = acCurrentMaxDate;
	}

	/**
	 * Gets the consumption date.
	 *
	 * @return the consumptionDate
	 */
	public Date getConsumptionDate()
	{
		return consumptionDate;
	}

	/**
	 * Sets the consumption date.
	 *
	 * @param consumptionDate the consumptionDate to set
	 */
	public void setConsumptionDate(Date consumptionDate)
	{
		this.consumptionDate = consumptionDate;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LightLastOperationalData [getLightId()=" + getParentId() + ", getConsumptionMax()="
				+ getConsumptionMax() + ", getAcVoltage()=" + getAcVoltage() + ", getAcVoltageMin()="
				+ getAcVoltageMin() + ", getAcVoltageMax()=" + getAcVoltageMax() + ", getAcCurrent()=" + getAcCurrent()
				+ ", getAcCurrentMin()=" + getAcCurrentMin() + ", getAcCurrentMax()=" + getAcCurrentMax()
				+ ", getConsumption()=" + getConsumption() + ", getConsumptionMin()=" + getConsumptionMin()
				+ ", getAcVoltageMinDate()=" + getAcVoltageMinDate() + ", getAcVoltageMaxDate()="
				+ getAcVoltageMaxDate() + ", getAcCurrentMinDate()=" + getAcCurrentMinDate()
				+ ", getAcCurrentMaxDate()=" + getAcCurrentMaxDate() + ", getConsumptionDate()=" + getConsumptionDate()
				+ ", toString()=" + super.toString() + "]";
	}

}
