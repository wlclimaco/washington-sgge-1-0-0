package com.sensus.dm.common.process.model;

import java.util.Date;
import java.util.List;

import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.SearchTypeEnum;

/**
 * The Class ProcessSearch.
 * 
 * @author QAT Brazil
 * 
 */
@SuppressWarnings("serial")
public class ProcessSearch extends BaseSearch
{
	/** The process status enums. */
	private List<ProcessStatusEnum> processStatusEnums;

	/** The process item history status enums. */
	private List<ProcessItemHistoryStatusEnum> processItemHistoryStatusEnums;

	/** The search type. */
	private SearchTypeEnum searchType;

	/**
	 * Instantiates a new process filter.
	 */
	public ProcessSearch()
	{
	}

	/**
	 * Instantiates a new process filter.
	 * 
	 * @param searchTypeParam the search type param
	 * @param searchTextParam the search text param
	 */
	public ProcessSearch(SearchTypeEnum searchTypeParam, String searchTextParam)
	{
		setSearchType(searchTypeParam);
		setSearchText(searchTextParam);
	}

	/**
	 * Instantiates a new process filter.
	 * 
	 * @param startDate the start date
	 */
	public ProcessSearch(Date startDate)
	{
		setStartDate(startDate);
	}

	/**
	 * Instantiates a new process filter.
	 * 
	 * @param startDate the start date
	 * @param endDate the end date
	 */
	public ProcessSearch(Date startDate, Date endDate)
	{
		setStartDate(startDate);
		setEndDate(endDate);
	}

	/**
	 * Gets the process status enums.
	 * 
	 * @return the process status enums
	 */
	public List<ProcessStatusEnum> getProcessStatusEnums()
	{
		return processStatusEnums;
	}

	/**
	 * Sets the process status enums.
	 * 
	 * @param processStatusEnums the new process status enums
	 */
	public void setProcessStatusEnums(List<ProcessStatusEnum> processStatusEnums)
	{
		this.processStatusEnums = processStatusEnums;
	}

	/**
	 * Gets the process item history status enums.
	 * 
	 * @return the process item history status enums
	 */
	public List<ProcessItemHistoryStatusEnum> getProcessItemHistoryStatusEnums()
	{
		return processItemHistoryStatusEnums;
	}

	/**
	 * Sets the process item history status enums.
	 * 
	 * @param processItemHistoryStatusEnums the new process item history status enums
	 */
	public void setProcessItemHistoryStatusEnums(List<ProcessItemHistoryStatusEnum> processItemHistoryStatusEnums)
	{
		this.processItemHistoryStatusEnums = processItemHistoryStatusEnums;
	}

	/**
	 * Gets the search type.
	 * 
	 * @return the search type
	 */
	public SearchTypeEnum getSearchType()
	{
		return searchType;
	}

	/**
	 * Sets the search type.
	 * 
	 * @param searchType the new search type
	 */
	public void setSearchType(SearchTypeEnum searchType)
	{
		this.searchType = searchType;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.BaseSearch#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessSearch [getStartDateInt()=" + getStartDateInt() + ", getEndDateInt()=" + getEndDateInt()
				+ ", getProcessStatusEnums()=" + getProcessStatusEnums() + ", getProcessItemHistoryStatusEnums()="
				+ getProcessItemHistoryStatusEnums() + ", getSearchType()=" + getSearchType() + ", toString()="
				+ super.toString() + "]";
	}

}
