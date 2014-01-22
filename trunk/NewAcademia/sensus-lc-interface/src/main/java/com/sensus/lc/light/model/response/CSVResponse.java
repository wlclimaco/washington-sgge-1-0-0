package com.sensus.lc.light.model.response;

import com.sensus.common.model.response.Response;

public class CSVResponse extends Response
{
	private String fileName;

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
}
