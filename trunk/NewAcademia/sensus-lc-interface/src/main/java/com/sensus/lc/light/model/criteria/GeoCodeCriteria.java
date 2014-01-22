package com.sensus.lc.light.model.criteria;

import static com.sensus.common.validation.ValidationUtil.isNull;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Location;

/**
 * Criteria for Geo Code.
 *
 * @author Thiago - QAT
 *
 */
/**
 * @author QATEmployee
 * 
 */
public class GeoCodeCriteria
{
	/**
	 * Attributes.
	 */
	private List<Location> locationList = new ArrayList<Location>();

	private Double bottomLeftLat;

	/** The bottom left long. */
	private Double bottomLeftLong;

	/** The top right lat. */
	private Double topRightLat;

	/** The top right long. */
	private Double topRightLong;

	/** The middle lat min. */
	private Double middleLatMin;

	/** The middle lat max. */
	private Double middleLatMax;

	/** The middle long min. */
	private Double middleLongMin;

	/** The middle long max. */
	private Double middleLongMax;

	/**
	 * IT IS USED TO CALC MAP PRECISION.
	 * please verify how to use, all map functionality depends of it.
	 */
	private Integer trunc;

	/**
	 * @return the locationList
	 */
	public List<Location> getLocationList()
	{
		return locationList;
	}

	/**
	 * @param locationList the locationList to set
	 */
	public void setLocationList(List<Location> locationList)
	{
		this.locationList = locationList;
	}

	/**
	 * Adds the location list.
	 * 
	 * @param location the location
	 */
	public void addLocationList(Location location)
	{
		locationList.add(location);
	}

	/**
	 * Gets the trunc.
	 * 
	 * @return the trunc
	 */
	public Integer getTrunc()
	{
		return trunc;
	}

	/**
	 * Sets the trunc.
	 * 
	 * @param trunc the trunc to set
	 */
	public void setTrunc(Integer trunc)
	{
		this.trunc = trunc;
	}

	/**
	 * Gets the bottom left lat.
	 * 
	 * @return the bottomLeftLat
	 */
	public Double getBottomLeftLat()
	{
		return bottomLeftLat;
	}

	/**
	 * Sets the bottom left lat.
	 * 
	 * @param bottomLeftLat the bottomLeftLat to set
	 */
	public void setBottomLeftLat(Double bottomLeftLat)
	{
		this.bottomLeftLat = bottomLeftLat;
	}

	/**
	 * Gets the bottom left long.
	 * 
	 * @return the bottomLeftLong
	 */
	public Double getBottomLeftLong()
	{
		return bottomLeftLong;
	}

	/**
	 * Sets the bottom left long.
	 * 
	 * @param bottomLeftLong the bottomLeftLong to set
	 */
	public void setBottomLeftLong(Double bottomLeftLong)
	{
		this.bottomLeftLong = bottomLeftLong;
	}

	/**
	 * Gets the top right lat.
	 * 
	 * @return the topRightLat
	 */
	public Double getTopRightLat()
	{
		return topRightLat;
	}

	/**
	 * Sets the top right lat.
	 * 
	 * @param topRightLat the topRightLat to set
	 */
	public void setTopRightLat(Double topRightLat)
	{
		this.topRightLat = topRightLat;
	}

	/**
	 * Gets the top right long.
	 * 
	 * @return the topRightLong
	 */
	public Double getTopRightLong()
	{
		return topRightLong;
	}

	/**
	 * Sets the top right long.
	 * 
	 * @param topRightLong the topRightLong to set
	 */
	public void setTopRightLong(Double topRightLong)
	{
		this.topRightLong = topRightLong;
	}

	/**
	 * Gets the middle lat min.
	 * 
	 * @return the middleLatMin
	 */
	public Double getMiddleLatMin()
	{
		return middleLatMin;
	}

	/**
	 * Sets the middle lat min.
	 * 
	 * @param middleLatMin the middleLatMin to set
	 */
	public void setMiddleLatMin(Double middleLatMin)
	{
		this.middleLatMin = middleLatMin;
	}

	/**
	 * Gets the middle lat max.
	 * 
	 * @return the middleLatMax
	 */
	public Double getMiddleLatMax()
	{
		return middleLatMax;
	}

	/**
	 * Sets the middle lat max.
	 * 
	 * @param middleLatMax the middleLatMax to set
	 */
	public void setMiddleLatMax(Double middleLatMax)
	{
		this.middleLatMax = middleLatMax;
	}

	/**
	 * Gets the middle long min.
	 * 
	 * @return the middleLongMin
	 */
	public Double getMiddleLongMin()
	{
		return middleLongMin;
	}

	/**
	 * Sets the middle long min.
	 * 
	 * @param middleLongMin the middleLongMin to set
	 */
	public void setMiddleLongMin(Double middleLongMin)
	{
		this.middleLongMin = middleLongMin;
	}

	/**
	 * Gets the middle long max.
	 * 
	 * @return the middleLongMax
	 */
	public Double getMiddleLongMax()
	{
		return middleLongMax;
	}

	/**
	 * Sets the middle long max.
	 * 
	 * @param middleLongMax the middleLongMax to set
	 */
	public void setMiddleLongMax(Double middleLongMax)
	{
		this.middleLongMax = middleLongMax;
	}

	/**
	 * Checks for filter.
	 * 
	 * @return true, if successful
	 */
	public boolean hasFilter()
	{
		return !isNull(getBottomLeftLat()) ||
				!isNull(getBottomLeftLong()) ||
				!isNull(getTopRightLat()) ||
				!isNull(getTopRightLong()) ||
				!isNull(getMiddleLatMin()) ||
				!isNull(getMiddleLatMax()) ||
				!isNull(getMiddleLongMin()) ||
				!isNull(getMiddleLongMax());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "GeoCodeCriteria [getTrunc()=" + getTrunc() + ", getBottomLeftLat()=" + getBottomLeftLat()
				+ ", getBottomLeftLong()=" + getBottomLeftLong() + ", getTopRightLat()=" + getTopRightLat()
				+ ", getTopRightLong()=" + getTopRightLong() + ", getMiddleLatMin()=" + getMiddleLatMin()
				+ ", getMiddleLatMax()=" + getMiddleLatMax() + ", getMiddleLongMin()=" + getMiddleLongMin()
				+ ", getMiddleLongMax()=" + getMiddleLongMax() + ", hasFilter()=" + hasFilter() + ", toString()="
				+ super.toString() + "]";
	}

}
