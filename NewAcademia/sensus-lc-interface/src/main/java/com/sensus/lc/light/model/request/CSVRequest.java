package com.sensus.lc.light.model.request;

import com.sensus.common.model.request.Request;

/**
 * Used to query CSV data for lights.
 */
public class CSVRequest extends Request

{
	private Integer processId;

	private String fileName;

	/** The timezone. */
	private String timezone;

	/**
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * @return the processId
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the timezone.
	 *
	 * @return the timezone
	 */
	public String getTimezone()
	{
		return timezone;
	}

	/**
	 * Sets the timezone.
	 *
	 * @param timezone the new timezone
	 */
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}


}