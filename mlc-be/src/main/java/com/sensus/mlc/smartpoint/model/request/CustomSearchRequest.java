package com.sensus.mlc.smartpoint.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class CustomSearchRequest.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
public class CustomSearchRequest extends LightingControlRequest
{

	/** The custom search. */
	private CustomSearch customSearch;

	/** The pagination all selected. */
	private Boolean paginationAllSelected;

	/** The selection pagination ids. */
	private List<Integer> selectionPaginationIds;

	/** The status messages. */
	private List<StatusMessage> statusMessages;

	/**
	 * Instantiates a new custom search request.
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
	 * Instantiates a new custom search request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public CustomSearchRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
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

	/**
	 * Sets the pagination all selected.
	 * 
	 * @param paginationAllSelected the new pagination all selected
	 */
	public void setPaginationAllSelected(Boolean paginationAllSelected)
	{
		this.paginationAllSelected = paginationAllSelected;
	}

	/**
	 * Gets the pagination all selected.
	 * 
	 * @return the pagination all selected
	 */
	public Boolean getPaginationAllSelected()
	{
		return paginationAllSelected;
	}

	/**
	 * Sets the selection pagination ids.
	 * 
	 * @param selectionPaginationIds the new selection pagination ids
	 */
	public void setSelectionPaginationIds(List<Integer> selectionPaginationIds)
	{
		this.selectionPaginationIds = selectionPaginationIds;
	}

	/**
	 * Gets the selection pagination ids.
	 * 
	 * @return the selection pagination ids
	 */
	public List<Integer> getSelectionPaginationIds()
	{
		return selectionPaginationIds;
	}

	/**
	 * Gets the status messages.
	 * 
	 * @return the status messages
	 */
	public List<StatusMessage> getStatusMessages()
	{
		return statusMessages;
	}

	/**
	 * Sets the status messages.
	 * 
	 * @param statusMessages the new status messages
	 */
	public void setStatusMessages(List<StatusMessage> statusMessages)
	{
		this.statusMessages = statusMessages;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomSearchRequest [getCustomSearch()=" + getCustomSearch() + ", getPaginationAllSelected()="
				+ getPaginationAllSelected() + ", getSelectionPaginationIds()=" + getSelectionPaginationIds()
				+ ", getStatusMessages()=" + getStatusMessages() + ", getTenant()=" + getTenant()
				+ ", getUserContext()=" + getUserContext() + "]";
	}

}