package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class SmartpointMiddleMap.
 */
@SuppressWarnings("serial")
public class SmartpointMiddleMap extends SensusModel
{

	/** The middle lat min. */
	private Double middleLatMin;

	/** The middle lat max. */
	private Double middleLatMax;

	/** The middle lon min. */
	private Double middleLonMin;

	/** The middle lon max. */
	private Double middleLonMax;

	/**
	 * Instantiates a new smartpoint middle map.
	 */
	public SmartpointMiddleMap()
	{
	}

	/**
	 * Instantiates a new smartpoint middle map.
	 * 
	 * @param middleLatMin the middle lat min
	 * @param middleLatMax the middle lat max
	 * @param middleLonMin the middle lon min
	 * @param middleLonMax the middle lon max
	 */
	public SmartpointMiddleMap(Double middleLatMinValue, Double middleLatMaxValue,
			Double middleLonMinValue, Double middleLonMaxValue)
	{
		super();
		middleLatMin = middleLatMinValue;
		middleLatMax = middleLatMaxValue;
		middleLonMin = middleLonMinValue;
		middleLonMax = middleLonMaxValue;
	}

	/**
	 * Gets the middle lat min.
	 * 
	 * @return the middle lat min
	 */
	public Double getMiddleLatMin()
	{
		return middleLatMin;
	}

	/**
	 * Sets the middle lat min.
	 * 
	 * @param middleLatMin the new middle lat min
	 */
	public void setMiddleLatMin(Double middleLatMin)
	{
		this.middleLatMin = middleLatMin;
	}

	/**
	 * Gets the middle lat max.
	 * 
	 * @return the middle lat max
	 */
	public Double getMiddleLatMax()
	{
		return middleLatMax;
	}

	/**
	 * Sets the middle lat max.
	 * 
	 * @param middleLatMax the new middle lat max
	 */
	public void setMiddleLatMax(Double middleLatMax)
	{
		this.middleLatMax = middleLatMax;
	}

	/**
	 * Gets the middle lon min.
	 * 
	 * @return the middle lon min
	 */
	public Double getMiddleLonMin()
	{
		return middleLonMin;
	}

	/**
	 * Sets the middle lon min.
	 * 
	 * @param middleLonMin the new middle lon min
	 */
	public void setMiddleLonMin(Double middleLonMin)
	{
		this.middleLonMin = middleLonMin;
	}

	/**
	 * Gets the middle lon max.
	 * 
	 * @return the middle lon max
	 */
	public Double getMiddleLonMax()
	{
		return middleLonMax;
	}

	/**
	 * Sets the middle lon max.
	 * 
	 * @param middleLonMax the new middle lon max
	 */
	public void setMiddleLonMax(Double middleLonMax)
	{
		this.middleLonMax = middleLonMax;
	}

}
