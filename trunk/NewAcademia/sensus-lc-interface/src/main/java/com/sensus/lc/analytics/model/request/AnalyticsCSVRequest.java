package com.sensus.lc.analytics.model.request;

import java.util.List;

import com.sensus.lc.light.model.request.CSVRequest;

/**
 * Used to query CSV data for Analytics
 * Using this object you can filter the results of the query or in effect "select all".<br>
 */
public class AnalyticsCSVRequest extends CSVRequest
{

	/** The csv columns. */
	private List<String> csvColumns;

	/** The inquiry analytics request. */
	private InquiryAnalyticsRequest inquiryAnalyticsRequest;

	/**
	 * Gets the csv columns.
	 * 
	 * @return the csv columns
	 */
	public List<String> getCsvColumns()
	{
		return csvColumns;
	}

	/**
	 * Sets the csv columns.
	 * 
	 * @param csvColumns the new csv columns
	 */
	public void setCsvColumns(List<String> csvColumns)
	{
		this.csvColumns = csvColumns;
	}

	/**
	 * Gets the inquiry analytics request.
	 * 
	 * @return the inquiry analytics request
	 */
	public InquiryAnalyticsRequest getInquiryAnalyticsRequest()
	{
		return inquiryAnalyticsRequest;
	}

	/**
	 * Sets the inquiry analytics request.
	 * 
	 * @param inquiryAnalyticsRequest the new inquiry analytics request
	 */
	public void setInquiryAnalyticsRequest(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		this.inquiryAnalyticsRequest = inquiryAnalyticsRequest;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsCSVRequest [getCsvColumns()=" + getCsvColumns() + ", getInquiryAnalyticsRequest()="
				+ getInquiryAnalyticsRequest() + ", toString()=" + super.toString() + "]";
	}

}
