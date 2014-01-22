package com.sensus.lc.light.model;

import com.sensus.common.model.SensusModel;

/**
 * The Class LightTextSearch.
 */
@SuppressWarnings("serial")
public class LightTextSearch extends SensusModel
{

	/** The light property. */
	private LightPropertyForSearchEnum lightProperty;

	/** The search text. */
	private String searchText;

	/**
	 * Sets the light property.
	 * 
	 * @param lightProperty the new light property
	 */
	public void setLightProperty(LightPropertyForSearchEnum lightProperty)
	{
		this.lightProperty = lightProperty;
	}

	/**
	 * Gets the light property.
	 * 
	 * @return the light property
	 */
	public LightPropertyForSearchEnum getLightProperty()
	{
		return lightProperty;
	}

	/**
	 * Sets the search text.
	 * 
	 * @param searchText the new search text
	 */
	public void setSearchText(String searchText)
	{
		this.searchText = searchText;
	}

	/**
	 * Gets the search text.
	 * 
	 * @return the search text
	 */
	public String getSearchText()
	{
		return searchText;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightTextSearch [getLightProperty()=" + getLightProperty() + ", getSearchText()=" + getSearchText()
				+ "]";
	}

}
