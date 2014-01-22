package com.sensus.lc.process.model.request;

import java.util.List;

import com.sensus.lc.light.model.request.CSVRequest;

/**
 * Used to query CSV data for process.
 * Using this object you can filter the results of the query or in effect "select all".<br>
 */
public class ProcessCSVRequest extends CSVRequest
{

	/** The csv columns. */
	private List<String> csvColumns;

	/** The inquiry process request. */
	private InquiryProcessRequest inquiryProcessRequest;

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
	 * Gets the inquiry process request.
	 * 
	 * @return the inquiry process request
	 */
	public InquiryProcessRequest getInquiryProcessRequest()
	{
		return inquiryProcessRequest;
	}

	/**
	 * Sets the inquiry process request.
	 * 
	 * @param inquiryProcessRequest the new inquiry process request
	 */
	public void setInquiryProcessRequest(InquiryProcessRequest inquiryProcessRequest)
	{
		this.inquiryProcessRequest = inquiryProcessRequest;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProcessCSVRequest [getCsvColumns()=" + getCsvColumns() + ", getInquiryProcessRequest()="
				+ getInquiryProcessRequest() + ", toString()=" + super.toString() + "]";
	}

}
