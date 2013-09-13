package com.sensus.dm.common.base.model.request;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;

/**
 * The Class InquiryPaginationRequest.
 * 
 * @author QAT Brazil.
 */
public class InquiryPaginationRequest extends DeviceManagerInquiryRequest
{

	/** The pagination all selected. */
	private Boolean paginationAllSelected;

	/** The selection pagination ids. */
	private List<BigInteger> selectionPaginationIds;

	/**
	 * Instantiates a new inquiry pagination request.
	 */
	public InquiryPaginationRequest()
	{
	}

	/**
	 * Instantiates a new inquiry pagination request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryPaginationRequest(UserContext userContext)
	{
		super(userContext);
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
	 * Sets the pagination all selected.
	 * 
	 * @param paginationAllSelected the new pagination all selected
	 */
	public void setPaginationAllSelected(Boolean paginationAllSelected)
	{
		this.paginationAllSelected = paginationAllSelected;
	}

	/**
	 * Gets the selection pagination ids.
	 * 
	 * @return the selection pagination ids
	 */
	public List<BigInteger> getSelectionPaginationIds()
	{
		return selectionPaginationIds;
	}

	/**
	 * Sets the selection pagination ids.
	 * 
	 * @param selectionPaginationIds the new selection pagination ids
	 */
	public void setSelectionPaginationIds(List<BigInteger> selectionPaginationIds)
	{
		this.selectionPaginationIds = selectionPaginationIds;
	}

	/**
	 * Adds the selection pagination id.
	 * 
	 * @param id the id
	 */
	public void addSelectionPaginationId(BigInteger id)
	{
		if (getSelectionPaginationIds() == null)
		{
			setSelectionPaginationIds(new ArrayList<BigInteger>());
		}

		getSelectionPaginationIds().add(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryPaginationRequest [getPaginationAllSelected()=" + getPaginationAllSelected()
				+ ", getSelectionPaginationIds()=" + getSelectionPaginationIds() + ", toString()=" + super.toString()
				+ "]";
	}

}
