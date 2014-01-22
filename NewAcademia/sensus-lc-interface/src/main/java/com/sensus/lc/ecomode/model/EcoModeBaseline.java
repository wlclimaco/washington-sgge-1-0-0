/*
 *
 */
package com.sensus.lc.ecomode.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class EcoModeBaseline.
 */
@SuppressWarnings("serial")
public class EcoModeBaseline extends SensusModel
{
	/** The replaced type. */
	private LightEcoModeTypeEnum replacedType;

	/** The replace wattage. */
	private Double replacedWattage;

	/** The calculate retroactive ecomode. */
	private Boolean calculateRetroactiveEcomode = Boolean.FALSE;

	/**
	 * Instantiates a new eco mode baseline.
	 */
	public EcoModeBaseline()
	{
	}

	/**
	 * Instantiates a new eco mode baseline.
	 * 
	 * @param type the replaced type
	 * @param wattage the replace wattage
	 * @param calculateRetroactive the calculate retroactive ecomode
	 */
	public EcoModeBaseline(
			LightEcoModeTypeEnum type,
			Double wattage,
			Boolean calculateRetroactive)
	{
		setReplacedType(type);
		setReplacedWattage(wattage);
		setCalculateRetroactiveEcomode(calculateRetroactive);
	}

	/**
	 * Instantiates a new eco mode baseline.
	 * 
	 * @param lightTarget the light
	 * @param wattage the replace wattage
	 */
	public EcoModeBaseline(Double wattage)
	{
		setReplacedWattage(wattage);
	}

	/**
	 * Gets the replaced type.
	 * 
	 * @return the replaced type
	 */
	public LightEcoModeTypeEnum getReplacedType()
	{
		return replacedType;
	}

	/**
	 * Gets the replaced type value.
	 * 
	 * @return the replaced type value
	 */
	public Integer getReplacedTypeValue()
	{
		if (getReplacedType() == null)
		{
			return null;
		}
		return getReplacedType().getValue();
	}

	/**
	 * Sets the replaced type.
	 * 
	 * @param replacedType the new replaced type
	 */
	public void setReplacedType(LightEcoModeTypeEnum replacedType)
	{
		this.replacedType = replacedType;
	}

	/**
	 * Sets the replaced type value.
	 * 
	 * @param replacedValue the new replaced type value
	 */
	public void setReplacedTypeValue(Integer replacedValue)
	{
		replacedType = LightEcoModeTypeEnum.enumForValue(replacedValue);
	}

	/**
	 * Gets the replaced wattage.
	 * 
	 * @return the replaced wattage
	 */
	public Double getReplacedWattage()
	{
		return replacedWattage;
	}

	/**
	 * Sets the replaced wattage.
	 * 
	 * @param replacedWattage the new replaced wattage
	 */
	public void setReplacedWattage(Double replacedWattage)
	{
		this.replacedWattage = replacedWattage;
	}

	/**
	 * Checks if is calculate retroactive ecomode.
	 * 
	 * @return the boolean
	 */
	public Boolean isCalculateRetroactiveEcomode()
	{
		return calculateRetroactiveEcomode;
	}

	/**
	 * Sets the calculate retroactive ecomode.
	 * 
	 * @param calculateRetroactiveEcomode the new calculate retroactive ecomode
	 */
	public void setCalculateRetroactiveEcomode(Boolean calculateRetroactiveEcomode)
	{
		this.calculateRetroactiveEcomode = calculateRetroactiveEcomode;
	}

	@Override
	public String toString()
	{
		return "EcoModeBaseline [getReplacedType()=" + getReplacedType() + ", getReplacedTypeValue()="
				+ getReplacedTypeValue() + ", getReplacedWattage()=" + getReplacedWattage()
				+ ", isCalculateRetroactiveEcomode()=" + isCalculateRetroactiveEcomode() + ", toString()="
				+ super.toString() + "]";
	}
}
