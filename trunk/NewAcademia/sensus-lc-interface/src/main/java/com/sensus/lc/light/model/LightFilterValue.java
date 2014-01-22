package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class LightFilterValue.
 */
@SuppressWarnings("serial")
public class LightFilterValue extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The light filter type . */
	private LightFilterTypeEnum lightFilterType;

	/** The filter value. */
	private String filterValue;

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
	public LightFilterTypeEnum getLightFilterType()
	{
		return lightFilterType;
	}

	/**
	 * Sets the light filter type id.
	 *
	 * @param lightFilterType the new light filter type
	 */
	public void setLightFilterType(LightFilterTypeEnum lightFilterType)
	{
		this.lightFilterType = lightFilterType;
	}

	/**
	 * Gets the filter value.
	 *
	 * @return the filter value
	 */
	public String getFilterValue()
	{
		return filterValue;
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

	/**
	 * Sets the light filter type value.
	 *
	 * @param valueInternal the new light filter type value
	 */
	public void setLightFilterTypeValue(Integer valueInternal)
	{
		lightFilterType = LightFilterTypeEnum.enumForValue(valueInternal);
	}

	/**
	 * Gets the light filter type value.
	 *
	 * @return the light filter type value
	 */
	public Integer getLightFilterTypeValue()
	{
		return lightFilterType.getValue();
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "LightFilterValue [getId()=" + getId() + ", getLightFilterTypeId()=" + getLightFilterType()
				+ ", getFilterValue()=" + getFilterValue() + "]";
	}
}
