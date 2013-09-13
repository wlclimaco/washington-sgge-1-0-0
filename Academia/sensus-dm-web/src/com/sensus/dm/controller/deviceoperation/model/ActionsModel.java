package com.sensus.dm.controller.deviceoperation.model;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.SortExpression;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.elec.device.model.LCM;

/**
 * The Class ActionsModel.
 */
public class ActionsModel
{
	/** The device search. */
	private DeviceSearch deviceSearch;

	private List<LCM> lcmList;

	/** The parameters. */
	private Map<String, Object> parameters;

	/** The pagination all selected. */
	private Boolean paginationAllSelected;

	/** The selection pagination ids. */
	private List<BigInteger> selectionPaginationIds;

	/** The sort expressions. */
	private List<SortExpression> sortExpressions;

	/**
	 * Gets the parameters.
	 * 
	 * @return the parameters
	 */
	public Map<String, Object> getParameters()
	{
		return parameters;
	}

	/**
	 * Sets the parameters.
	 * 
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters)
	{
		this.parameters = parameters;
	}

	/**
	 * Gets the device search.
	 * 
	 * @return the deviceSearch
	 */
	public DeviceSearch getDeviceSearch()
	{
		return deviceSearch;
	}

	/**
	 * Sets the device search.
	 * 
	 * @param deviceSearch the deviceSearch to set
	 */
	public void setDeviceSearch(DeviceSearch deviceSearch)
	{
		this.deviceSearch = deviceSearch;
	}

	/**
	 * Gets the pagination all selected.
	 * 
	 * @return the paginationAllSelected
	 */
	public Boolean getPaginationAllSelected()
	{
		return paginationAllSelected;
	}

	/**
	 * Sets the pagination all selected.
	 * 
	 * @param paginationAllSelected the paginationAllSelected to set
	 */
	public void setPaginationAllSelected(Boolean paginationAllSelected)
	{
		this.paginationAllSelected = paginationAllSelected;
	}

	/**
	 * Gets the selection pagination ids.
	 * 
	 * @return the selectionPaginationIds
	 */
	public List<BigInteger> getSelectionPaginationIds()
	{
		return selectionPaginationIds;
	}

	/**
	 * Sets the selection pagination ids.
	 * 
	 * @param selectionPaginationIds the selectionPaginationIds to set
	 */
	public void setSelectionPaginationIds(List<BigInteger> selectionPaginationIds)
	{
		this.selectionPaginationIds = selectionPaginationIds;
	}

	/**
	 * Gets the sort expressions.
	 * 
	 * @return the sortExpressions
	 */
	public List<SortExpression> getSortExpressions()
	{
		return sortExpressions;
	}

	/**
	 * Sets the sort expressions.
	 * 
	 * @param sortExpressions the sortExpressions to set
	 */
	public void setSortExpressions(List<SortExpression> sortExpressions)
	{
		this.sortExpressions = sortExpressions;
	}

	/**
	 * @return the lcmList
	 */
	public List<LCM> getLcmList()
	{
		return lcmList;
	}

	/**
	 * @param lcmList the lcmList to set
	 */
	public void setLcmList(List<LCM> lcmList)
	{
		this.lcmList = lcmList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ActionsModel [getParameters()=" + getParameters() + ", getDeviceSearch()=" + getDeviceSearch()
				+ ", getPaginationAllSelected()=" + getPaginationAllSelected() + ", getSelectionPaginationIds()="
				+ getSelectionPaginationIds() + ", getSortExpressions()=" + getSortExpressions() + ", getLcmList()="
				+ getLcmList() + "]";
	}

}
