package com.sensus.lc.light.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.lc.light.model.PartNumberConfiguration;

/**
 * The Class LightingConfigurationsResponse.
 */
public class LightingConfigurationsResponse extends Response

{

	/** The lighting configurations. */
	private List<PartNumberConfiguration> lightingConfigurations;

	/** The part number. */
	private Integer partNumber;

	/**
	 * Gets the lighting configurations.
	 *
	 * @return the lighting configurations
	 */
	public List<PartNumberConfiguration> getLightingConfigurations()
	{
		return this.lightingConfigurations;
	}

	/**
	 * Sets the lighting configurations.
	 *
	 * @param lightingConfigurations the new lighting configurations
	 */
	public void setLightingConfigurations(List<PartNumberConfiguration> lightingConfigurations)
	{
		this.lightingConfigurations = lightingConfigurations;
	}

	/**
	 * Gets the part number.
	 *
	 * @return the part number
	 */
	public Integer getPartNumber()
	{
		return this.partNumber;
	}

	/**
	 * Sets the part number.
	 *
	 * @param partNumber the new part number
	 */
	public void setPartNumber(Integer partNumber)
	{
		this.partNumber = partNumber;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightingConfigurationsResponse [getLightingConfigurations()=" + this.getLightingConfigurations()
				+ ", getPartNumber()=" + this.getPartNumber() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()=" + this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()=" + this.isOperationSuccess() + "]";
	}

}
