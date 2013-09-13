package com.sensus.dm.common.device.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class CustomSearchRequest.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
public class CustomSearchRequest extends TenantRequest
{
	/** The custom search. */
	private CustomSearch customSearch;

	/**
	 * Instantiates a new custom search request.
	 * 
	 */
	public CustomSearchRequest()
	{
	}

	/**
	 * Instantiates a new custom search request.
	 * 
	 * @param userContext the user context
	 */
	public CustomSearchRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the custom search.
	 * 
	 * @return the custom search
	 */
	public CustomSearch getCustomSearch()
	{
		return customSearch;
	}

	/**
	 * Sets the custom search.
	 * 
	 * @param customSearch the new custom search
	 */
	public void setCustomSearch(CustomSearch customSearch)
	{
		this.customSearch = customSearch;
	}

	@Override
	public String toString()
	{
		return "CustomSearchRequest [getCustomSearch()=" + getCustomSearch() + ", toString()=" + super.toString() + "]";
	}

}
