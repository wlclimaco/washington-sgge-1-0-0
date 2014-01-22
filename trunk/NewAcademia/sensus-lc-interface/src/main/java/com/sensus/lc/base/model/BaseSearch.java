package com.sensus.lc.base.model;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.light.model.SearchParameter;

/**
 * The Class BaseSearch.
 */

@SuppressWarnings("serial")
public class BaseSearch extends SensusModel
{

	/** The id. */
	private Integer id;

	/** The search parameters. */
	private List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();

	/**
	 * Instantiates a new base search.
	 */
	public BaseSearch()
	{

	}

	/**
	 * Instantiates a new base search.
	 * 
	 * @param idValue the id value
	 * @param searchParametersValue the search parameters value
	 */
	public BaseSearch(Integer idValue, List<SearchParameter> searchParametersValue)
	{
		setId(idValue);
		setSearchParameters(searchParametersValue);
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
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Gets the search parameters.
	 * 
	 * @return the search parameters
	 */
	public List<SearchParameter> getSearchParameters()
	{
		return searchParameters;
	}

	/**
	 * Sets the search parameters.
	 * 
	 * @param searchParameters the new search parameters
	 */
	public void setSearchParameters(List<SearchParameter> searchParameters)
	{
		this.searchParameters = searchParameters;
	}

	/**
	 * Adds the search parameter.
	 * 
	 * @param searchParameter the search parameter
	 */
	public void addSearchParameter(SearchParameter searchParameter)
	{
		getSearchParameters().add(searchParameter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "BaseSearch [getId()=" + getId() + ", getSearchParameters()=" + getSearchParameters()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}

}
