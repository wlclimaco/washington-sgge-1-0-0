package com.sensus.lc.base.model;

import com.sensus.common.model.SortExpression;

/**
 * The Class MLCSortExpression.
 */
public class MLCSortExpression extends SortExpression
{

	/** The is property. */
	private Boolean property;

	/**
	 * Sets the checks if is property.
	 * 
	 * @param isProperty the new checks if is property
	 */
	public void setIsProperty(Boolean isProperty)
	{
		this.property = isProperty;
	}

	/**
	 * Gets the checks if is property.
	 * 
	 * @return the checks if is property
	 */
	public Boolean isProperty()
	{
		return property;
	}

}
