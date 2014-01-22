package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class Filter.
 */
@SuppressWarnings("serial")
public class Filter extends SensusModel
{

	/** The filter. */
	private FilterEnum filterEnum;

	/** The display order. */
	private Integer displayOrder;

	/**
	 * Gets the filter.
	 *
	 * @return the filter
	 */
	public FilterEnum getFilterEnum()
	{
		return filterEnum;
	}

	/**
	 * Sets the filter.
	 *
	 * @param filter the new filter
	 */
	public void setFilterEnum(FilterEnum filter)
	{
		filterEnum = filter;
	}

	/**
	 * Sets the filter enum value.
	 *
	 * @param valueParam the new filter enum value
	 */
	public void setFilterEnumValue(String valueParam)
	{
		filterEnum = FilterEnum.enumForValue(valueParam);
	}

	/**
	 * Gets the filter enum value.
	 *
	 * @return the filter enum value
	 */
	public String getFilterEnumValue()
	{
		if (filterEnum == null)
		{
			return null;
		}

		return filterEnum.getValue();
	}

	/**
	 * Gets the display order.
	 *
	 * @return the display order
	 */
	public Integer getDisplayOrder()
	{
		return displayOrder;
	}

	/**
	 * Sets the display order.
	 *
	 * @param displayOrder the new display order
	 */
	public void setDisplayOrder(Integer displayOrder)
	{
		this.displayOrder = displayOrder;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Filter [getFilterEnum()=" + getFilterEnum() + ", getFilterEnumValue()=" + getFilterEnumValue()
				+ ", getDisplayOrder()=" + getDisplayOrder() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
