package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNull;

/**
 * Criteria for operational data.
 *
 * @author Thiago - QAT
 */
public class OperationalDataCriteria
{

	/**
	 * Attributes.
	 */
	private Integer voltageGreater;

	/** The voltage less. */
	private Integer voltageLess;

	/** The current greater. */
	private Integer currentGreater;

	/** The current less. */
	private Integer currentLess;

	/** The consumption greater. */
	private Integer consumptionGreater;

	/** The consumption less. */
	private Integer consumptionLess;

	/**
	 * Gets the voltage greater.
	 *
	 * @return the voltageGreater
	 */
	public Integer getVoltageGreater()
	{
		return voltageGreater;
	}

	/**
	 * Sets the voltage greater.
	 *
	 * @param voltageGreater the voltageGreater to set
	 */
	public void setVoltageGreater(Integer voltageGreater)
	{
		this.voltageGreater = voltageGreater;
	}

	/**
	 * Gets the voltage less.
	 *
	 * @return the voltageLess
	 */
	public Integer getVoltageLess()
	{
		return voltageLess;
	}

	/**
	 * Sets the voltage less.
	 *
	 * @param voltageLess the voltageLess to set
	 */
	public void setVoltageLess(Integer voltageLess)
	{
		this.voltageLess = voltageLess;
	}

	/**
	 * Gets the current greater.
	 *
	 * @return the currentGreater
	 */
	public Integer getCurrentGreater()
	{
		return currentGreater;
	}

	/**
	 * Sets the current greater.
	 *
	 * @param currentGreater the currentGreater to set
	 */
	public void setCurrentGreater(Integer currentGreater)
	{
		this.currentGreater = currentGreater;
	}

	/**
	 * Gets the current less.
	 *
	 * @return the currentLess
	 */
	public Integer getCurrentLess()
	{
		return currentLess;
	}

	/**
	 * Sets the current less.
	 *
	 * @param currentLess the currentLess to set
	 */
	public void setCurrentLess(Integer currentLess)
	{
		this.currentLess = currentLess;
	}

	/**
	 * Gets the consumption greater.
	 *
	 * @return the consumptionGreater
	 */
	public Integer getConsumptionGreater()
	{
		return consumptionGreater;
	}

	/**
	 * Sets the consumption greater.
	 *
	 * @param consumptionGreater the consumptionGreater to set
	 */
	public void setConsumptionGreater(Integer consumptionGreater)
	{
		this.consumptionGreater = consumptionGreater;
	}

	/**
	 * Gets the consumption less.
	 *
	 * @return the consumptionLess
	 */
	public Integer getConsumptionLess()
	{
		return consumptionLess;
	}

	/**
	 * Sets the consumption less.
	 *
	 * @param consumptionLess the consumptionLess to set
	 */
	public void setConsumptionLess(Integer consumptionLess)
	{
		this.consumptionLess = consumptionLess;
	}

	/**
	 * Checks for filter.
	 *
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return !isNull(getVoltageGreater()) ||
				!isNull(getVoltageLess()) ||
				!isNull(getCurrentGreater()) ||
				!isNull(getCurrentLess()) ||
				!isNull(getConsumptionGreater()) ||
				!isNull(getConsumptionLess());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OperationalDataCriteria [getVoltageGreater()=" + getVoltageGreater() + ", getVoltageLess()="
				+ getVoltageLess() + ", getCurrentGreater()=" + getCurrentGreater() + ", getCurrentLess()="
				+ getCurrentLess() + ", getConsumptionGreater()=" + getConsumptionGreater() + ", getConsumptionLess()="
				+ getConsumptionLess() + ", hasFilter()=" + hasFilter() + ", toString()=" + super.toString() + "]";
	}

}
