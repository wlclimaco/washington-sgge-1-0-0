package com.sensus.mlc.smartpoint.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class LightingConfigurationRequest.
 */
public class LightingConfigurationRequest extends LightingControlRequest
{

	/** The light. */
	private Light light;

	/** The part number. */
	private Integer partNumber;

	/** The lighting configurations. */
	private List<SensusPartNumberConfiguration> lightingConfigurations;

	/**
	 * Instantiates a new lighting configuration request.
	 */
	public LightingConfigurationRequest()
	{
	}

	/**
	 * Instantiates a new lighting configuration request.
	 * 
	 * @param userContext the user context
	 */
	public LightingConfigurationRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new lighting configuration request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightingConfigurationRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the part number.
	 * 
	 * @return the part number
	 */
	public Integer getPartNumber()
	{
		return partNumber;
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

	/**
	 * Sets the lighting configurations.
	 * 
	 * @param lightingConfigurations the new lighting configurations
	 */
	public void setLightingConfigurations(List<SensusPartNumberConfiguration> lightingConfigurations)
	{
		this.lightingConfigurations = lightingConfigurations;
	}

	/**
	 * Gets the lighting configurations.
	 * 
	 * @return the lighting configurations
	 */
	public List<SensusPartNumberConfiguration> getLightingConfigurations()
	{
		return lightingConfigurations;
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Light getLight()
	{
		return light;
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "LightingConfigurationRequest [getPartNumber()=" + getPartNumber() + ", getLightingConfigurations()="
				+ getLightingConfigurations() + ", getLight()=" + getLight() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}

}
