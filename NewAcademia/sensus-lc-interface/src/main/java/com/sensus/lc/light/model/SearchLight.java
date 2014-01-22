package com.sensus.lc.light.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.SensusModel;

/**
 * The Class SearchLight.
 */
@SuppressWarnings("serial")
public class SearchLight extends SensusModel
{

	/** The light. */
	private Integer lightId;

	/** The status list. */
	private List<LightStatusEnum> statusList = new ArrayList<LightStatusEnum>();

	/** The property values. */
	private Map<String, List<Integer>> propertyValues = new HashMap<String, List<Integer>>();

	/** The search parameters. */
	private List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();

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
	 * @return the lightId
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * @param lightId the lightId to set
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
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
	public Map<String, List<Integer>> getPropertyValues()
	{
		return propertyValues;
	}

	/**
	 * Sets the property values.
	 * 
	 * @param propertyValues the property values
	 */
	public void setPropertyValues(Map<String, List<Integer>> propertyValues)
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

	/**
	 * @return the searchParameters
	 */
	public List<SearchParameter> getSearchParameters()
	{
		return searchParameters;
	}

	/**
	 * @param searchParameters the searchParameters to set
	 */
	public void setSearchParameters(List<SearchParameter> searchParameters)
	{
		this.searchParameters = searchParameters;
	}

	/**
	 * Adds the search parameters.
	 * 
	 * @param searchParameter the search parameter
	 */
	public void addSearchParameter(SearchParameter searchParameter)
	{
		searchParameters.add(searchParameter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.model.BaseSearch#toString()
	 */
	@Override
	public String toString()
	{
		return "SearchLight [getStatusList()=" + getStatusList()
				+ ", getPropertyValues()=" + getPropertyValues() + ", toString()=" + super.toString() + "]";
	}
}
