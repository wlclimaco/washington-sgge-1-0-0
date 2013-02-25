package com.sensus.mlc.wui.base.model;

import java.util.Arrays;
import java.util.Date;

/**
 * Base class for Json results for callbacks by the JQuery datatable. This widget expects the data to be returned in a
 * 2-dimensional string array named aaData.
 * 
 * @author Anke Doerfel-Parker.
 * 
 */
@SuppressWarnings("serial")
public class SearchJsonResult extends JsonResult
{

	/** The force light status lrp id. */
	private Integer forceLightStatusLRPId;

	/** The result value. */
	private String resultValue;

	/**
	 * Gets the result value.
	 * 
	 * @return the result value
	 */
	public String getResultValue()
	{
		return resultValue;
	}

	/**
	 * Sets the result value.
	 * 
	 * @param resultValue the new result value
	 */
	public void setResultValue(String resultValue)
	{
		this.resultValue = resultValue;
	}

	/**
	 * Gets the force light status lrp id.
	 * 
	 * @return the force light status lrp id
	 */
	public Integer getForceLightStatusLRPId()
	{
		return forceLightStatusLRPId;
	}

	/**
	 * Sets the force light status lrp id.
	 * 
	 * @param forceLightStatusLRPId the new force light status lrp id
	 */
	public void setForceLightStatusLRPId(Integer forceLightStatusLRPId)
	{
		this.forceLightStatusLRPId = forceLightStatusLRPId;
	}

	/** The date refresh. */
	private String dateRefresh;

	/** The i total display records. */
	private Integer iTotalDisplayRecords;

	/**
	 * The records to populate the table with.
	 */
	private String[][] aaData = new String[][] {};

	/**
	 * Set the table data records.
	 * 
	 * @return the table data records
	 */
	public String[][] getAaData()
	{
		return aaData;
	}

	/**
	 * Get the table data records.
	 * 
	 * @param aaDataIn the table data records.
	 */
	public void setAaData(String[][] aaDataIn)
	{
		aaData = aaDataIn;
	}

	/**
	 * Sets the i total display records.
	 * 
	 * @param iTotalDisplayRecordsValue the new i total display records
	 */
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecordsValue)
	{
		iTotalDisplayRecords = iTotalDisplayRecordsValue;
	}

	/**
	 * Gets the i total display records.
	 * 
	 * @return the i total display records
	 */
	public Integer getiTotalDisplayRecords()
	{
		return iTotalDisplayRecords;
	}

	/**
	 * @return the dateRefresh
	 */
	public String getDateRefresh()
	{
		Date dateNow = new Date();
		return dateNow.toString();
	}

	/**
	 * @param dateRefresh the dateRefresh to set
	 */
	public void setDateRefresh(String dateRefresh)
	{
		this.dateRefresh = dateRefresh;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SearchJsonResult [getResultValue()=" + getResultValue()
				+ ", getForceLightStatusLRPId()=" + getForceLightStatusLRPId()
				+ ", getAaData()=" + Arrays.toString(getAaData())
				+ ", getiTotalDisplayRecords()=" + getiTotalDisplayRecords()
				+ ", getDateRefresh()=" + getDateRefresh() + "]";
	}

}
