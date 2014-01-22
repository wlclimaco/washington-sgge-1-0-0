package com.sensus.lc.light.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.Request;
import com.sensus.lc.light.model.LightFilterTypeEnum;

/**
 * Request object for the Light Filter.
 * 
 */
public class LightFilterRequest extends Request
{

	/** The filters. */
	private List<LightFilterTypeEnum> filterList;

	/**
	 * Constructor.
	 */
	public LightFilterRequest()
	{

	}

	/**
	 * Instantiates a new light filter request.
	 * 
	 * @param context the context
	 */
	public LightFilterRequest(UserContext context)
	{
		setUserContext(context);
	}

	/**
	 * Gets the filter list.
	 * 
	 * @return the filterList
	 */
	public List<LightFilterTypeEnum> getFilterList()
	{
		if (filterList == null)
		{
			filterList = new ArrayList<LightFilterTypeEnum>();
		}

		return filterList;
	}

	/**
	 * Sets the filter list.
	 * 
	 * @param filterList the filterList to set
	 */
	public void setFilterList(List<LightFilterTypeEnum> filterList)
	{
		this.filterList = filterList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "LightFilterRequest [getFilterList()=" + getFilterList() + ", toString()=" + super.toString() + "]";
	}

}
