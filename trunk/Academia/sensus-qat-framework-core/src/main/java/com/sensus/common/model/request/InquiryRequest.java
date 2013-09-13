package com.sensus.common.model.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sensus.common.model.SortExpression;

/**
 * This InquiryRequest object is used to encapsulate various properties and parameters used by a BAS/BAI provider.<br/>
 * It is used in the context of a consumer "requesting" an inquiry operation upon a "provider" where there are many
 * properties that need to be passed in as parameters. The Request object contains various properties that are common to
 * every request. Like:
 * <ol>
 * <li>Paging data
 * <li>Results criteria
 * <li>Sort expressions
 * <li>Other indicators
 * </ol>
 * Components should extend this class and add criteria properties related to the operation.<br/>
 * A note about paging: Two techniques for paging are supported within this object. Page based or rows based paging is
 * supported. Depending on the consumer of this request the appropriate properties should be set and used.
 */
public class InquiryRequest extends Request // implements IInquiryRequest
{
	// [start] Private properties

	/** The starting row for the request. */
	private Integer startRow = 0;

	/** The ending row for the request. */
	private Integer endRow = 0;

	/** The page size. */
	private Integer pageSize = 20;

	/** The start page. */
	private Integer startPage = 0;

	/** The sort expressions. */
	private final List<SortExpression> sortExpressions = new ArrayList<SortExpression>();

	/** A pre-query may be performed in order to determine the row count prior to executing the actual query. * */
	/** This pre-query would in turn enable the response to indicate the total number of available rows that exist. **/
	private Boolean preQueryCount = false;

	/** If the maxPreQueryCount is exceeded then the query will fail. * */
	private Integer maxPreQueryCount = 0;

	// [end] Provider properties

	// [start] Public properties

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
	 * Gets the entire sort expression rendered for use within a SQL statement <b>without<b/> the ORDER BY prefix.<br/>
	 * The expressions are sort based on priority prior to being rendered.
	 * 
	 * @return the sort expression
	 */
	public String getSortExpression()
	{
		String prefix = "";
		StringBuilder sb = new StringBuilder();

		if ((getSortExpressions() != null) && (getSortExpressions().size() > 0))
		{
			Collections.sort(getSortExpressions(), new Comparator<SortExpression>()
			{
				public int compare(SortExpression exp1, SortExpression exp2)
				{
					if ((exp1.getPriority() != null) && (exp2.getPriority() != null))
					{
						return exp1.getPriority().compareTo(exp2.getPriority());
					}
					else if ((exp1.getPriority() == null) && (exp2.getPriority() == null))
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

			for (SortExpression exp : getSortExpressions())
			{
				sb.append(prefix + exp.toString());
				prefix = ", ";
			}
		}
		if (sb.length() == 0)
		{
			return null;
		}

		return sb.toString();
	}

	/**
	 * Sets the sort expression to be applied.
	 * 
	 * @param sortExpression the sort expression
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

	// [end] Public properties

}
