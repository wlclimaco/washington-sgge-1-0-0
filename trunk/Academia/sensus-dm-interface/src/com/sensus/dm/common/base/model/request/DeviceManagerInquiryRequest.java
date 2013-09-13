package com.sensus.dm.common.base.model.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.UserContext;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class DeviceManagerInquiryRequest.
 *
 * @author QAT Brazil.
 */
public class DeviceManagerInquiryRequest extends TenantRequest
{
	/** The starting row for the request. */
	private Integer startRow = 0;

	/** The ending row for the request. */
	private Integer endRow = 0;

	/** The page size. */
	private Integer pageSize;

	/** The start page. */
	private Integer startPage;

	/** The sort expressions. */
	private final List<SortExpression> sortExpressions = new ArrayList<SortExpression>();

	/** A pre-query may be performed in order to determine the row count prior to executing the actual query. * */
	/** This pre-query would in turn enable the response to indicate the total number of available rows that exist. **/
	private Boolean preQueryCount = false;

	/** If the maxPreQueryCount is exceeded then the query will fail. * */
	private Integer maxPreQueryCount = 0;


	/**
	 * Instantiates a new endPoint manager inquiry request.
	 *
	 * @param userContext the user context
	 */
	public DeviceManagerInquiryRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new endPoint manager inquiry request.
	 */
	public DeviceManagerInquiryRequest()
	{

	}

	/**
	 * Gets the starting row at which the results set should start.
	 *
	 * @return The starting row
	 */
	public Integer getStartRow()
	{
		return startRow;
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
		return endRow;
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
		return pageSize;
	}

	/**
	 * Gets the start page.
	 *
	 * @return the start page
	 */
	public Integer getStartPage()
	{
		return startPage;
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
		return sortExpressions;
	}

	/**
	 * Gets the entire sort expression rendered for use within a SQL statement <b>without</b> the ORDER BY prefix.<br/>
	 * The expressions are sort based on priority prior to being rendered.
	 *
	 * @return the sort expression
	 */
	public String getSortExpression()
	{

		if (getSortExpressions() != null && !getSortExpressions().isEmpty())
		{
			Collections.sort(getSortExpressions(), new Comparator<SortExpression>()
			{
				@Override
				public int compare(SortExpression exp1, SortExpression exp2)
				{
					if (exp1.getPriority() != null && exp2.getPriority() != null)
					{
						return exp1.getPriority().compareTo(exp2.getPriority());
					}
					else if (exp1.getPriority() == null && exp2.getPriority() == null)
					{
						return 0;
					}
					else
					{
						if (exp1.getPriority() == null)
						{
							return 1;
						}
						else
						{
							return -1;
						}
					}
				}
			});

		}

		return StringUtils.join(getSortExpressions(), ", ");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.IInquiryRequest#addSortExpressions(com.sensus.common.model.SortExpression)
	 */
	public void addSortExpressions(SortExpression sortExpression)
	{
		sortExpressions.add(sortExpression);
	}

	/**
	 * Returns the indicator of the preQuery property.
	 *
	 * @return the preQueryCount indicator.
	 */
	public Boolean isPreQueryCount()
	{
		return preQueryCount;
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
		return maxPreQueryCount;
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

	@Override
	public String toString()
	{
		return "DeviceManagerInquiryRequest [getStartRow()=" + getStartRow() + ", getEndRow()=" + getEndRow()
				+ ", getPageSize()=" + getPageSize() + ", getStartPage()=" + getStartPage() + ", getSortExpressions()="
				+ getSortExpressions() + ", getSortExpression()=" + getSortExpression() + ", isPreQueryCount()="
				+ isPreQueryCount() + ", getMaxPreQueryCount()=" + getMaxPreQueryCount() + ", toString()="
				+ super.toString() + "]";
	}


}
