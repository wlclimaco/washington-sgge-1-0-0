package com.sensus.mlc.smartpoint.model;

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
public class LightLastOperationalData extends SensusModel
{

	/**
	 * Attributes.
	 */
	private Integer lightId;

	private Integer acVoltage;

	private Integer acVoltageMin;

	private Date acVoltageMinDate;

	private Integer acVoltageMax;

	private Date acVoltageMaxDate;

	private Double acCurrent;

	private Double acCurrentMin;

	private Date acCurrentMinDate;

	private Double acCurrentMax;

	private Date acCurrentMaxDate;

	private Double consumption;

	private Double consumptionMin;

	private Double consumptionMax;

	private Date consumptionDate;


	/**
	 * @return the lightId
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * @param lightId the lightId to set
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * @return the consumptionMax
	 */
	public Double getConsumptionMax()
	{
		return consumptionMax;
	}

	/**
	 * @param consumptionMax the consumptionMax to set
	 */
	public void setConsumptionMax(Double consumptionMax)
	{
		this.consumptionMax = consumptionMax;
	}

	/**
	 * @return the acVoltage
	 */
	public Integer getAcVoltage()
	{
		return acVoltage;
	}

	/**
	 * @param acVoltage the acVoltage to set
	 */
	public void setAcVoltage(Integer acVoltage)
	{
		this.acVoltage = acVoltage;
	}

	/**
	 * @return the acVoltageMin
	 */
	public Integer getAcVoltageMin()
	{
		return acVoltageMin;
	}

	/**
	 * @param acVoltageMin the acVoltageMin to set
	 */
	public void setAcVoltageMin(Integer acVoltageMin)
	{
		this.acVoltageMin = acVoltageMin;
	}

	/**
	 * @return the acVoltageMax
	 */
	public Integer getAcVoltageMax()
	{
		return acVoltageMax;
	}

	/**
	 * @param acVoltageMax the acVoltageMax to set
	 */
	public void setAcVoltageMax(Integer acVoltageMax)
	{
		this.acVoltageMax = acVoltageMax;
	}

	/**
	 * @return the acCurrent
	 */
	public Double getAcCurrent()
	{
		return acCurrent;
	}

	/**
	 * @param acCurrent the acCurrent to set
	 */
	public void setAcCurrent(Double acCurrent)
	{
		this.acCurrent = acCurrent;
	}

	/**
	 * @return the acCurrentMin
	 */
	public Double getAcCurrentMin()
	{
		return acCurrentMin;
	}

	/**
	 * @param acCurrentMin the acCurrentMin to set
	 */
	public void setAcCurrentMin(Double acCurrentMin)
	{
		this.acCurrentMin = acCurrentMin;
	}

	/**
	 * @return the acCurrentMax
	 */
	public Double getAcCurrentMax()
	{
		return acCurrentMax;
	}

	/**
	 * @param acCurrentMax the acCurrentMax to set
	 */
	public void setAcCurrentMax(Double acCurrentMax)
	{
		this.acCurrentMax = acCurrentMax;
	}

	/**
	 * @return the consumption
	 */
	public Double getConsumption()
	{
		return consumption;
	}

	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(Double consumption)
	{
		this.consumption = consumption;
	}

	/**
	 * @return the consumptionMin
	 */
	public Double getConsumptionMin()
	{
		return consumptionMin;
	}

	/**
	 * @param consumptionMin the consumptionMin to set
	 */
	public void setConsumptionMin(Double consumptionMin)
	{
		this.consumptionMin = consumptionMin;
	}

	/**
	 * @return the acVoltageMinDate
	 */
	public Date getAcVoltageMinDate()
	{
		return acVoltageMinDate;
	}

	/**
	 * @param acVoltageMinDate the acVoltageMinDate to set
	 */
	public void setAcVoltageMinDate(Date acVoltageMinDate)
	{
		this.acVoltageMinDate = acVoltageMinDate;
	}

	/**
	 * @return the acVoltageMaxDate
	 */
	public Date getAcVoltageMaxDate()
	{
		return acVoltageMaxDate;
	}

	/**
	 * @param acVoltageMaxDate the acVoltageMaxDate to set
	 */
	public void setAcVoltageMaxDate(Date acVoltageMaxDate)
	{
		this.acVoltageMaxDate = acVoltageMaxDate;
	}

	/**
	 * @return the acCurrentMinDate
	 */
	public Date getAcCurrentMinDate()
	{
		return acCurrentMinDate;
	}

	/**
	 * @param acCurrentMinDate the acCurrentMinDate to set
	 */
	public void setAcCurrentMinDate(Date acCurrentMinDate)
	{
		this.acCurrentMinDate = acCurrentMinDate;
	}

	/**
	 * @return the acCurrentMaxDate
	 */
	public Date getAcCurrentMaxDate()
	{
		return acCurrentMaxDate;
	}

	/**
	 * @param acCurrentMaxDate the acCurrentMaxDate to set
	 */
	public void setAcCurrentMaxDate(Date acCurrentMaxDate)
	{
		this.acCurrentMaxDate = acCurrentMaxDate;
	}

	/**
	 * @return the consumptionDate
	 */
	public Date getConsumptionDate()
	{
		return consumptionDate;
	}

	/**
	 * @param consumptionDate the consumptionDate to set
	 */
	public void setConsumptionDate(Date consumptionDate)
	{
		this.consumptionDate = consumptionDate;
	}




}
