package com.sensus.lc.process.model;

import com.sensus.common.model.SensusModel;

/**
 * Object model for Part Number properties.
 *
 * @author Thiago Silva - QAT
 */
@SuppressWarnings("serial")
public class PartNumberConfiguration extends SensusModel
{
	/**
	 * Attributes.
	 */
	private Integer id;
	private Integer currentScaleConfiguration;
	private Boolean fullOnRequiredConfiguration;
	private Integer hardwareSettingConfiguration;

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}
	/**
	 * @return the currentScaleConfiguration
	 */
	public Integer getCurrentScaleConfiguration()
	{
		return currentScaleConfiguration;
	}
	/**
	 * @param currentScaleConfiguration the currentScaleConfiguration to set
	 */
	public void setCurrentScaleConfiguration(Integer currentScaleConfiguration)
	{
		this.currentScaleConfiguration = currentScaleConfiguration;
	}
	/**
	 * @return the fullOnRequiredConfiguration
	 */
	public Boolean getFullOnRequiredConfiguration()
	{
		return fullOnRequiredConfiguration;
	}
	/**
	 * @param fullOnRequiredConfiguration the fullOnRequiredConfiguration to set
	 */
	public void setFullOnRequiredConfiguration(Boolean fullOnRequiredConfiguration)
	{
		this.fullOnRequiredConfiguration = fullOnRequiredConfiguration;
	}
	/**
	 * @return the hardwareSettingConfiguration
	 */
	public Integer getHardwareSettingConfiguration()
	{
		return hardwareSettingConfiguration;
	}
	/**
	 * @param hardwareSettingConfiguration the hardwareSettingConfiguration to set
	 */
	public void setHardwareSettingConfiguration(Integer hardwareSettingConfiguration)
	{
		this.hardwareSettingConfiguration = hardwareSettingConfiguration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PartNumbertConfiguration [currentScaleConfiguration=" + currentScaleConfiguration
				+ ", fullOnRequiredConfiguration=" + fullOnRequiredConfiguration + ", hardwareSettingConfiguration="
				+ hardwareSettingConfiguration + "]";
	}

}
