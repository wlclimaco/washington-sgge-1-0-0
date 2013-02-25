package com.sensus.mlc.base.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class LightingControlInquiryRequest.
 */
public class LightingControlInquiryRequest extends LightSelectionRequest
{

	/** The starting row for the request. */
	private Integer startRow = 0;

	/** The ending row for the request. */
	private Integer endRow = 0;

	/** The page size. */
	private Integer pageSize = 0;

	/** The start page. */
	private Integer startPage = 0;

	/** The sort expressions. */
	private List<SortExpression> sortExpressions = new ArrayList<SortExpression>();

	/** The sort expressions string. */
	private String sortExpressionsString;

	/** A pre-query may be performed in order to determine the row count prior to executing the actual query. * */
	/** This pre-query would in turn enable the response to indicate the total number of available rows that exist. **/
	private Boolean preQueryCount = false;

	/** If the maxPreQueryCount is exceeded then the query will fail. * */
	private Integer maxPreQueryCount = 0;

	/** The list column. */
	private List<Column> listColumn;

	/**
	 * Instantiates a new lighting control inquiry request.
	 */
	public LightingControlInquiryRequest()
	{
	}

	/**
	 * Instantiates a new lighting control inquiry request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public LightingControlInquiryRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Instantiates a new lighting control inquiry request.
	 * 
	 * @param userContext the user context
	 */
	public LightingControlInquiryRequest(UserContext userContext)
	{
		super(userContext);
	}

	// [end] Provider properties

	// [start] Public properties

	/**
	 * Gets the starting row at which the results set should start.
	 * 
	 * @return The starting row
	 */
	public Integer getStartRow()
	{
		return this.startRow;
	}

	/**
	 * Sets the starting row at which the results set should start.
	 * 
	 * @param startRow the start row
	 */
	public void setStartRow(Integer startRow)
	{
		this.startRow = startRow;
	}

	/**
	 * Get the ending row number at which the results set should stop.
	 * 
	 * @return The ending row number
	 */
	public Integer getEndRow()
	{
		return this.endRow;
	}

	/**
	 * Sets the ending row number at which the results set should stop.
	 * 
	 * @param endRow the end row
	 */
	public void setEndRow(Integer endRow)
	{
		this.endRow = endRow;
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	public Integer getPageSize()
	{
		return this.pageSize;
	}

	/**
	 * Gets the start page.
	 * 
	 * @return the start page
	 */
	public Integer getStartPage()
	{
		return this.startPage;
	}

	/**
	 * Sets the page size. Default is 20.
	 * 
	 * @param pageSize the new page size
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * Sets the start page.
	 * 
	 * @param startPage the new start page
	 */
	public void setStartPage(Integer startPage)
	{
		this.startPage = startPage;
	}

	/**
	 * Gets the collection of sort expressions for this request. See the {@link #getSortExpression} method to retrieve
	 * the full sort expression for use in SQL.
	 * 
	 * @return the sort expressions
	 */
	public List<SortExpression> getSortExpressions()
	{
		return this.sortExpressions;
	}

	/**
	 * Gets the entire sort expression rendered for use within a SQL statement <b>without</b> the ORDER BY prefix.<br/>
	 * The expressions are sort based on priority prior to being rendered.
	 * 
	 * @return the sort expression
	 */
	public String getSortExpression()
	{
		return this.sortExpressionsString;
	}

	/**
	 * Sets the sort expression.
	 * 
	 * @param sortExpression the new sort expression
	 */
	public void setSortExpression(String sortExpression)
	{
		this.sortExpressionsString = sortExpression;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IInquiryRequest#addSortExpressions(com.sensus.common.model.SortExpression)
	 */
	public void addSortExpressions(SortExpression sortExpression)
	{
		this.sortExpressions.add(sortExpression);
	}

	/**
	 * Returns the indicator of the preQuery property.
	 * 
	 * @return the preQueryCount indicator.
	 */
	public Boolean isPreQueryCount()
	{
		return this.preQueryCount;
	}

	/**
	 * The preQueryCount indicator is used to inform the operation provider if in additional query should be performed
	 * in order to set the totalRowsAvailable property on the Response object. Depending on the consumer this may be
	 * appropriate or not. If the consumer is a UI with a grid and we need to show the total number of available rows or
	 * pages then this extra query will be required.
	 * 
	 * @param preQueryCount the boolean value.
	 */
	public void setPreQueryCount(Boolean preQueryCount)
	{
		this.preQueryCount = preQueryCount;
	}

	/**
	 * Related to the preQueryCount indicator this number is used to indicate if the pre-query count exceeded this
	 * number then the query will fail. This will help prevent large queries from running out of control.
	 * 
	 * @return The current max preQuery count value.
	 */
	public Integer getMaxPreQueryCount()
	{
		return this.maxPreQueryCount;
	}

	/**
	 * Sets the max pre query count.
	 * 
	 * @param maxPreQueryCount the new max pre query count
	 */
	public void setMaxPreQueryCount(Integer maxPreQueryCount)
	{
		this.maxPreQueryCount = maxPreQueryCount;
	}

	/**
	 * Gets the list column.
	 * 
	 * @return the list column
	 */
	public List<Column> getListColumn()
	{
		return this.listColumn;
	}

	/**
	 * Sets the list column.
	 * 
	 * @param listColumn the new list column
	 */
	public void setListColumn(List<Column> listColumn)
	{
		this.listColumn = listColumn;
	}

	@Override
	public String toString()
	{
		return "LightingControlInquiryRequest [getStartRow()=" + this.getStartRow() + ", getEndRow()="
				+ this.getEndRow()
				+ ", getPageSize()=" + this.getPageSize() + ", getStartPage()=" + this.getStartPage()
				+ ", getSortExpressions()="
				+ this.getSortExpressions() + ", getSortExpression()=" + this.getSortExpression()
				+ ", isPreQueryCount()="
				+ this.isPreQueryCount() + ", getMaxPreQueryCount()=" + this.getMaxPreQueryCount()
				+ ", getListColumn()="
				+ this.getListColumn() + "]";
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
}
