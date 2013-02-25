package com.sensus.mlc.wui.smartpoint.model;

/**
 * Enumeration representing the tab search filter options on the SmartPoint page (filters by various device types).
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public enum SmartpointSearchTypeEnum
{

	/** The ALL. */
	ALL("all"),
	/** The ALERTS. */
	ALERTS("alerts"),
	/** The LIGHTS. */
	LIGHTS("lights"),
	/** The MOTIONSENSORS. */
	MOTIONSENSORS("sensors"),
	/** The TOWERS. */
	TOWERS(
			"towers");

	/**
	 * The value sent as request parameter from the UI.
	 */
	private String key;

	/**
	 * Constructor for enumeration values.
	 * 
	 * @param keyIn the UI request parameter value.
	 */
	SmartpointSearchTypeEnum(String keyIn)
	{
		setKey(keyIn);
	}

	/**
	 * Sets the key.
	 * 
	 * @param keyIn the new key
	 */
	private void setKey(String keyIn)
	{
		key = keyIn;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}
}
