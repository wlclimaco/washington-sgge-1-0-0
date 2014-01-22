package com.sensus.lc.light.model.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to query CSV data for lights.
 * Includes support for paging and sorting using properties from super class.<br>
 * Using this object you can filter the results of the query or in effect "select all".<br>
 */
public class LightCSVRequest extends CSVRequest
{

	/** The columns to export. */
	private List<String> csvColumns;

	/**
	 * Criteria attributes.
	 * Add more as needed.
	 */
	private LightRequest lightRequest;

	/**
	 * Instantiates a new light csv request.
	 */
	public LightCSVRequest()
	{
	}

	/**
	 * Instantiates a new light csv request.
	 *
	 * @param request the request
	 */
	public LightCSVRequest(LightRequest request)
	{
		setLightRequest(request);
	}

	/**
	 * Gets the csv columns.
	 *
	 * @return the csvColumns
	 */
	public List<String> getCsvColumns()
	{
		if (csvColumns == null)
		{
			csvColumns = new ArrayList<String>();
		}

		return csvColumns;
	}

	/**
	 * Sets the csv columns.
	 *
	 * @param csvColumns the csvColumns to set
	 */
	public void setCsvColumns(List<String> csvColumns)
	{
		this.csvColumns = csvColumns;
	}

	/**
	 * Gets the light request.
	 *
	 * @return the lightRequest
	 */
	public LightRequest getLightRequest()
	{
		return lightRequest;
	}

	/**
	 * Sets the light request.
	 *
	 * @param lightRequest the lightRequest to set
	 */
	public void setLightRequest(LightRequest lightRequest)
	{
		this.lightRequest = lightRequest;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightCSVRequest [getCsvColumns()=" + getCsvColumns() + ", getLightRequest()=" + getLightRequest()
				+ ", toString()=" + super.toString() + "]";
	}
}
