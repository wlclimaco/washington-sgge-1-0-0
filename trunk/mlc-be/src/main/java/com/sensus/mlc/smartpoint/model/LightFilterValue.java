package com.sensus.mlc.smartpoint.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class LightFilterValue.
 */
@SuppressWarnings("serial")
public class LightFilterValue extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The light filter type id. */
	private LightFilterTypeEnum lightFilterTypeId;

	/** The filter value. */
	private String filterValue;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the light filter type id.
	 * 
	 * @return the light filter type id
	 */
	public LightFilterTypeEnum getLightFilterTypeId()
	{
		return this.lightFilterTypeId;
	}

	/**
	 * Sets the light filter type id.
	 * 
	 * @param lightFilterTypeId the new light filter type id
	 */
	public void setLightFilterTypeId(LightFilterTypeEnum lightFilterTypeId)
	{
		this.lightFilterTypeId = lightFilterTypeId;
	}

	/**
	 * Gets the filter value.
	 * 
	 * @return the filter value
	 */
	public String getFilterValue()
	{
		return this.filterValue;
	}

	/**
	 * Sets the filter value.
	 * 
	 * @param filterValue the new filter value
	 */
	public void setFilterValue(String filterValue)
	{
		this.filterValue = filterValue;
	}

	@Override
	public String toString()
	{
		return "LightFilterValue [getId()=" + getId() + ", getLightFilterTypeId()=" + getLightFilterTypeId()
				+ ", getFilterValue()=" + getFilterValue() + "]";
	}
}
