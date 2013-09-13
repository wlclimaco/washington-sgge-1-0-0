package com.sensus.common.model.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is used to hold various fields related to a results set.<br/>
 * These fields are encapsulated in this field so they can re-used within various Response object classes.
 */
@XmlRootElement
public class ResultsSetInfo
{
	/** The start row. */
	private int startRow = 0;

	/** The end row. */
	private int endRow = 0;

	/** The more rows available. */
	private boolean moreRowsAvailable = false;

	/** The total rows available. */
	private int totalRowsAvailable = 0;

	/**
	 * Instantiates a new empty results set info.
	 */
	public ResultsSetInfo()
	{
	}

	public ResultsSetInfo(int startRow, int endRow, boolean moreRowsAvailable)
	{
		this.startRow = startRow;
		this.endRow = endRow;
		this.moreRowsAvailable = moreRowsAvailable;
	}

	public ResultsSetInfo(int startRow, int endRow, boolean moreRowsAvailable, int totalRowsAvailable)
	{
		this.startRow = startRow;
		this.endRow = endRow;
		this.moreRowsAvailable = moreRowsAvailable;
		this.totalRowsAvailable = totalRowsAvailable;
	}

	/**
	 * Gets the start row. Base = 1
	 * 
	 * @return the start row
	 */
	public int getStartRow()
	{
		return startRow;
	}

	/**
	 * Sets the start row.
	 * 
	 * @param startRow the new start row
	 */
	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}

	/**
	 * Gets the end row.
	 * 
	 * @return the end row
	 */
	public int getEndRow()
	{
		return endRow;
	}

	/**
	 * Sets the end row.
	 * 
	 * @param endRow the new end row
	 */
	public void setEndRow(int endRow)
	{
		this.endRow = endRow;
	}

	/**
	 * Checks to see if there are more rows available.
	 * 
	 * @return true, if there is more rows available
	 */
	public boolean isMoreRowsAvailable()
	{
		return moreRowsAvailable;
	}

	/**
	 * Sets the more rows available.
	 * 
	 * @param moreRowsAvailable the new more rows available
	 */
	public void setMoreRowsAvailable(boolean moreRowsAvailable)
	{
		this.moreRowsAvailable = moreRowsAvailable;
	}

	/**
	 * Gets the total rows available.
	 * 
	 * @return the total rows available
	 */
	public int getTotalRowsAvailable()
	{
		return totalRowsAvailable;
	}

	/**
	 * Sets the total rows available.
	 * 
	 * @param totalRowsAvailable the new total rows available
	 */
	public void setTotalRowsAvailable(int totalRowsAvailable)
	{
		this.totalRowsAvailable = totalRowsAvailable;
	}

	@Override
	public String toString()
	{
		return "ResultsSetInfo [getStartRow()=" + getStartRow() + ", getEndRow()=" + getEndRow() + ", isMoreRowsAvailable()=" + isMoreRowsAvailable()
				+ ", getTotalRowsAvailable()=" + getTotalRowsAvailable() + "]";
	}

}
