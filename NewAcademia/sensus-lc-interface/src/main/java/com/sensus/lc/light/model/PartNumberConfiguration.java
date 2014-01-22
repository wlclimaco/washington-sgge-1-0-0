package com.sensus.lc.light.model;

import java.io.Serializable;
import java.util.Comparator;

import com.sensus.common.model.SensusModel;

/**
 * Model Object for SensusPartNumberConfiguration.
 *
 * @author Cristiane Cobo
 */
@SuppressWarnings("serial")
public class PartNumberConfiguration extends SensusModel implements Serializable,
		Comparator<PartNumberConfiguration>
{
	/** The sensus part number configuration id. */
	private Integer sensusPartNumberConfigurationId;

	/** The intensity level. */
	private Integer intensityLevel;

	/** The hardware setting. */
	private Integer hardwareSetting;

	/** The current scale. */
	private Integer currentScale;

	/** The full on required. */
	private Boolean fullOnRequired;

	/** The percentage. */
	private Integer percentage;

	/** The dimOnDelay. */
	private Integer dimOnDelay;

	/**
	 * Instantiates a new sensus part number configuration.
	 */
	public PartNumberConfiguration()
	{
	}

	/**
	 * Instantiates a new sensus part number configuration.
	 *
	 * @param sensusPartNumberConfigurationIdValue the sensus part number configuration id value
	 * @param intensityLevelValue the intensity level value
	 * @param hardwareSettingValue the hardware setting value
	 * @param currentScaleValue the current scale value
	 * @param fullOnRequiredValue the full on required value
	 * @param percentageValue the percentage value
	 */
	public PartNumberConfiguration(Integer sensusPartNumberConfigurationIdValue, Integer intensityLevelValue,
			Integer hardwareSettingValue, Integer currentScaleValue, Boolean fullOnRequiredValue,
			Integer percentageValue)
	{
		setSensusPartNumberConfigurationId(sensusPartNumberConfigurationIdValue);
		setIntensityLevel(intensityLevelValue);
		setHardwareSetting(hardwareSettingValue);
		setCurrentScale(currentScaleValue);
		setFullOnRequired(fullOnRequiredValue);
		setPercentage(percentageValue);
		setDimOnDelay(dimOnDelay);
	}

	/**
	 * Instantiates a new sensus part number configuration.
	 *
	 * @param intensityLevelValue the intensity level value
	 * @param percentageValue the percentage value
	 */
	public PartNumberConfiguration(Integer intensityLevelValue, Integer percentageValue)
	{
		setIntensityLevel(intensityLevelValue);
		setPercentage(percentageValue);
	}

	/**
	 * Gets the sensus part number configuration id.
	 *
	 * @return the sensus part number configuration id
	 */
	public Integer getSensusPartNumberConfigurationId()
	{
		return sensusPartNumberConfigurationId;
	}

	/**
	 * Sets the sensus part number configuration id.
	 *
	 * @param sensusPartNumberConfigurationId the new sensus part number configuration id
	 */
	public void setSensusPartNumberConfigurationId(Integer sensusPartNumberConfigurationId)
	{
		this.sensusPartNumberConfigurationId = sensusPartNumberConfigurationId;
	}

	/**
	 * Gets the intensity level.
	 *
	 * @return the intensity level
	 */
	public Integer getIntensityLevel()
	{
		return intensityLevel;
	}

	/**
	 * Sets the intensity level.
	 *
	 * @param intensityLevel the new intensity level
	 */
	public void setIntensityLevel(Integer intensityLevel)
	{
		this.intensityLevel = intensityLevel;
	}

	/**
	 * Gets the hardware setting.
	 *
	 * @return the hardware setting
	 */
	public Integer getHardwareSetting()
	{
		return hardwareSetting;
	}

	/**
	 * Sets the hardware setting.
	 *
	 * @param hardwareSetting the new hardware setting
	 */
	public void setHardwareSetting(Integer hardwareSetting)
	{
		this.hardwareSetting = hardwareSetting;
	}

	/**
	 * Gets the current scale.
	 *
	 * @return the current scale
	 */
	public Integer getCurrentScale()
	{
		return currentScale;
	}

	/**
	 * Sets the current scale.
	 *
	 * @param currentScale the new current scale
	 */
	public void setCurrentScale(Integer currentScale)
	{
		this.currentScale = currentScale;
	}

	/**
	 * Gets the full on required.
	 *
	 * @return the full on required
	 */
	public Boolean getFullOnRequired()
	{
		return fullOnRequired;
	}

	/**
	 * Sets the full on required.
	 *
	 * @param fullOnRequired the new full on required
	 */
	public void setFullOnRequired(Boolean fullOnRequired)
	{
		this.fullOnRequired = fullOnRequired;
	}

	/**
	 * Gets the percentage.
	 *
	 * @return the percentage
	 */
	public Integer getPercentage()
	{
		return percentage;
	}

	/**
	 * Sets the percentage.
	 *
	 * @param percentage the new percentage
	 */
	public void setPercentage(Integer percentage)
	{
		this.percentage = percentage;
	}

	/**
	 * Gets the dim on delay.
	 *
	 * @return the dim on delay
	 */
	public Integer getDimOnDelay()
	{
		return dimOnDelay;
	}

	/**
	 * Sets the dim on delay.
	 *
	 * @param dimOnDelay the new dim on delay
	 */
	public void setDimOnDelay(Integer dimOnDelay)
	{
		this.dimOnDelay = dimOnDelay;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(PartNumberConfiguration o1, PartNumberConfiguration o2)
	{
		return o1.getPercentage().compareTo(o2.getPercentage());
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "SensusPartNumberConfiguration [getSensusPartNumberConfigurationId()="
				+ getSensusPartNumberConfigurationId() + ", getIntensityLevel()=" + getIntensityLevel()
				+ ", getHardwareSetting()=" + getHardwareSetting() + ", getCurrentScale()=" + getCurrentScale()
				+ ", getFullOnRequired()=" + getFullOnRequired() + ", getPercentage()=" + getPercentage()
				+ ",getDimOnDelay()= " + getDimOnDelay() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}
}
