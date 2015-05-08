package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.QATModel;

/**
 * The Class Range.
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 10:00:05 AM
 */
@SuppressWarnings("serial")
public class Range extends QATModel
{

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The upper limit. */
	private Double upperLimit;

	/** The lower limit. */
	private Double lowerLimit;

	/**
	 * The Constructor.
	 */
	public Range()
	{

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * @param name the name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the upper limit.
	 *
	 * @return the upper limit
	 */
	public Double getUpperLimit()
	{
		return upperLimit;
	}

	/**
	 * Sets the upper limit.
	 *
	 * @param upperLimit the upper limit
	 */
	public void setUpperLimit(Double upperLimit)
	{
		this.upperLimit = upperLimit;
	}

	/**
	 * Gets the lower limit.
	 *
	 * @return the lower limit
	 */
	public Double getLowerLimit()
	{
		return lowerLimit;
	}

	/**
	 * Sets the lower limit.
	 *
	 * @param lowerLimit the lower limit
	 */
	public void setLowerLimit(Double lowerLimit)
	{
		this.lowerLimit = lowerLimit;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Range [getId()=" + getId() + ", getName()=" + getName() + ", getUpperLimit()=" + getUpperLimit()
				+ ", getLowerLimit()=" + getLowerLimit() + ", toString()=" + super.toString() + "]";
	}

}