package com.sensus.mlc.smartpoint.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sensus.mlc.base.model.BaseSearch;

/**
 * The Class SearchLight.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class SearchLight extends BaseSearch
{
	/** The light. */
	private Light light;

	/** The status list. */
	private List<LightStatusEnum> statusList = new ArrayList<LightStatusEnum>();

	/** The property values. */
	private HashMap<String, List<Integer>> propertyValues;

	/**
	 * Instantiates a new search light.
	 */
	public SearchLight()
	{
	}

	/**
	 * Instantiates a new search light.
	 */
	public SearchLight(final List<SearchParameter> searchParameters)
	{
		setSearchParameters(searchParameters);
	}

	/**
	 * Sets the light.
	 * 
	 * @param light the new light
	 */
	public void setLight(Light light)
	{
		this.light = light;
	}

	/**
	 * Gets the light.
	 * 
	 * @return the light
	 */
	public Light getLight()
	{
		return light;
	}

	/**
	 * Adds the status.
	 * 
	 * @param status the status
	 */
	public void addStatus(LightStatusEnum status)
	{
		getStatusList().add(status);
	}

	/**
	 * Sets the status list.
	 * 
	 * @param statusList the new status list
	 */
	public void setStatusList(List<LightStatusEnum> statusList)
	{
		this.statusList = statusList;
	}

	/**
	 * Gets the status list.
	 * 
	 * @return the status list
	 */
	public List<LightStatusEnum> getStatusList()
	{
		return statusList;
	}

	/**
	 * Gets the property values.
	 * 
	 * @return the property values
	 */
	public HashMap<String, List<Integer>> getPropertyValues()
	{
		return propertyValues;
	}

	/**
	 * Sets the property values.
	 * 
	 * @param propertyValues the property values
	 */
	public void setPropertyValues(HashMap<String, List<Integer>> propertyValues)
	{
		this.propertyValues = propertyValues;
	}

	/**
	 * Gets the search parameter value.
	 * 
	 * @param property the property
	 * @return the search parameter value
	 */
	public String getSearchParameterValue(PropertyEnum property)
	{
		if (getSearchParameters().isEmpty())
		{
			return null;
		}

		for (SearchParameter searchParameter : getSearchParameters())
		{
			if (property == searchParameter.getPropertyEnum())
			{
				return searchParameter.getValue();
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.BaseSearch#toString()
	 */
	@Override
	public String toString()
	{
		return "SearchLight [getLight()=" + getLight() + ", getStatusList()=" + getStatusList()
				+ ", getPropertyValues()=" + getPropertyValues() + ", getId()=" + getId() + ", getSearchParameters()="
				+ getSearchParameters() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
