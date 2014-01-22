package com.sensus.lc.light.model.response;

import com.sensus.common.model.response.InternalResponse;

public class CSVInternalResponse extends InternalResponse
{
	private String fileName;

	/**
	 * Instantiates a new cSV internal response.
	 *
	 * @param fileNameParam the file name param
	 */
	public CSVInternalResponse(String fileNameParam)
	{
		super();
		fileName = fileNameParam;
	}

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
