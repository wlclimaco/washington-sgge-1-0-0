package com.sensus.mlc.wui.smartpoint.model;

/**
 * Enumeration representing the states of the UI on/off toggle switch.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public enum LightSwitchTypeEnum
{

	/** The ON. */
	ON(100, "on"),
	/** The OFF. */
	OFF(0, "off"),
	/** The NONE. */
	NONE(-1, "invalid");

	/**
	 * The name sent from the UI.
	 */
	private String name;

	/**
	 * The corresponding light output intensity in percent.
	 */
	private Integer intensity;

	/**
	 * Eumeration constructor.
	 * 
	 * @param intensityIn the light intensity
	 * @param nameIn the switch state name
	 */
	LightSwitchTypeEnum(Integer intensityIn, String nameIn)
	{
		setName(nameIn);
		setIntensity(intensityIn);
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param nameIn the name to set
	 */
	public void setName(String nameIn)
	{
		name = nameIn;
	}

	/**
	 * Sets the intensity.
	 * 
	 * @param intensityIn the intensity to set
	 */
	public void setIntensity(Integer intensityIn)
	{
		intensity = intensityIn;
	}

	/**
	 * Gets the intensity.
	 * 
	 * @return the intensity
	 */
	public Integer getIntensity()
	{
		return intensity;
	}
}
