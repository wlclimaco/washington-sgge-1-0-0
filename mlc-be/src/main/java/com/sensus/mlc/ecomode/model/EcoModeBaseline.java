/*
 *
 */
package com.sensus.mlc.ecomode.model;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class EcoModeBaseline.
 */
@SuppressWarnings("serial")
public class EcoModeBaseline extends SensusModel
{

	/** The light. */
	private Integer lightId;

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
	 * @param lightTarget the light
	 * @param type the replaced type
	 * @param wattage the replace wattage
	 * @param calculateRetroactive the calculate retroactive ecomode
	 */
	public EcoModeBaseline(
			Light lightTarget,
			LightEcoModeTypeEnum type,
			Double wattage,
			Boolean calculateRetroactive)
	{
		setLightId(lightTarget.getId());
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
	public EcoModeBaseline(Light lightTarget, Double wattage)
	{
		setLightId(lightTarget.getId());
		setReplacedWattage(wattage);
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Integer getLightId()
	{
		return this.lightId;
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the pole id.
	 * 
	 * @return the pole id
	 */
	public String getPoleId()
	{
		// FIXME - properties
		// if (getLightId() == null)
		// {
		// return null;
		// }
		// return getLight().getParameterValue(PropertyEnum.POLE_ID);
		return "";
	}

	/**
	 * Sets the pole id.
	 * 
	 * @param poleId the new pole id
	 */
	public void setPoleId(String poleId)
	{
		// FIXME - properties, getLight() -> getLightId()
		// if (getLight() == null)
		// {
		// setLight(new Light());
		// }
		// getLight().addParameterValue(PropertyEnum.POLE_ID, poleId);
	}

	/**
	 * Gets the replaced type.
	 * 
	 * @return the replaced type
	 */
	public LightEcoModeTypeEnum getReplacedType()
	{
		return this.replacedType;
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
		this.replacedType = LightEcoModeTypeEnum.enumForValue(replacedValue);
	}

	/**
	 * Gets the replaced wattage.
	 * 
	 * @return the replaced wattage
	 */
	public Double getReplacedWattage()
	{
		return this.replacedWattage;
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
		return this.calculateRetroactiveEcomode;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "EcoModeBaseline [getLight()=" + getLightId() + ", getPoleId()=" + getPoleId() + ", getReplacedType()="
				+ getReplacedType() + ", getReplacedTypeValue()=" + getReplacedTypeValue() + ", getReplacedWattage()="
				+ getReplacedWattage() + ", isCalculateRetroactiveEcomode()=" + isCalculateRetroactiveEcomode()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
