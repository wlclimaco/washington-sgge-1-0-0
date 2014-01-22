package com.sensus.lc.ecomode.model.request;

import java.util.List;

import com.sensus.lc.light.model.request.CSVRequest;

/**
 * Used to query CSV data for ecoMode.
 * Using this object you can filter the results of the query or in effect "select all".<br>
 */
public class EcoModeCSVRequest extends CSVRequest
{
	/** The csv columns. */
	private List<String> csvColumns;

	/** The inquiry eco mode request. */
	private InquiryEcoModeRequest inquiryEcoModeRequest;

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
	 * Gets the inquiry eco mode request.
	 * 
	 * @return the inquiry eco mode request
	 */
	public InquiryEcoModeRequest getInquiryEcoModeRequest()
	{
		return inquiryEcoModeRequest;
	}

	/**
	 * Sets the inquiry eco mode request.
	 * 
	 * @param inquiryEcoModeRequest the new inquiry eco mode request
	 */
	public void setInquiryEcoModeRequest(InquiryEcoModeRequest inquiryEcoModeRequest)
	{
		this.inquiryEcoModeRequest = inquiryEcoModeRequest;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EcoModeCSVRequest [getCsvColumns()=" + getCsvColumns() + ", getInquiryEcoModeRequest()="
				+ getInquiryEcoModeRequest() + ", toString()=" + super.toString() + "]";
	}
}
