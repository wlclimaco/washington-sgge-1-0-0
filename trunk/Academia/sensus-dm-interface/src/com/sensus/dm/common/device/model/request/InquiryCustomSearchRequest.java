package com.sensus.dm.common.device.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.CustomSearch;

/**
 * The Class InquiryCustomSearchRequest.
 * 
 * @author - QAT Brazil
 */
public class InquiryCustomSearchRequest extends InquiryPaginationRequest
{

	/** The property. */
	private Boolean property;

	/** The custom search. */
	private CustomSearch customSearch;

	/** The is dashboard. */
	private Boolean isDashboard = false;

	/**
	 * Instantiates a new inquiry custom search request.
	 * 
	 */
	public InquiryCustomSearchRequest()
	{
	}

	/**
	 * Instantiates a new inquiry custom search request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryCustomSearchRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Gets the property.
	 * 
	 * @return the property
	 */
	public Boolean getProperty()
	{
		return property;
	}

	public CustomSearch getCustomSearch()
	{
		return customSearch;
	}

	public void setCustomSearch(CustomSearch customSearch)
	{
		this.customSearch = customSearch;
	}

	/**
	 * Sets the property.
	 * 
	 * @param property the new property
	 */
	public void setProperty(Boolean property)
	{
		this.property = property;
	}

	/**
	 * Gets the checks if is dashboard.
	 * 
	 * @return the checks if is dashboard
	 */
	public Boolean getIsDashboard()
	{
		return isDashboard;
	}

	/**
	 * Sets the checks if is dashboard.
	 * 
	 * @param isDashboard the new checks if is dashboard
	 */
	public void setIsDashboard(Boolean isDashboard)
	{
		this.isDashboard = isDashboard;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.model.request.InquiryCustomSearchRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryCustomSearchRequest [getProperty()=" + getProperty() + ", getPaginationAllSelected()="
				+ getPaginationAllSelected() + ", getSelectionPaginationIds()=" + getSelectionPaginationIds()
				+ ", getStartRow()=" + getStartRow() + ", getPageSize()=" + getPageSize() + ", getSortExpressions()="
				+ getSortExpressions() + ", getUserContext()=" + getUserContext()
				+ ", getCustomSearch()=" + getCustomSearch() + "]";
	}

}