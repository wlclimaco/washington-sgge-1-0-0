package com.sensus.mlc.smartpoint.model.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class LightResponse.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class LightResponse extends Response
{

	/** The lights. */
	private List<Light> lights = new ArrayList<Light>();

	/** The file name. */
	private String fileName;

	/** The light history header. */
	private HashMap<String, Integer> lightHistoryHeader;

	/**
	 * Gets the first light.
	 * 
	 * @return the first light
	 */
	public Light getFirstLight()
	{
		if ((this.getLights() == null) || (this.getLights().size() == 0))
		{
			return null;
		}
		return this.getLights().get(0);
	}

	/**
	 * Gets the lights.
	 * 
	 * @return the lights
	 */
	public List<Light> getLights()
	{

		return this.lights;
	}

	/**
	 * Sets the lights.
	 * 
	 * @param lightList the new lights
	 */
	public void setLights(List<Light> lightList)
	{
		this.lights = lightList;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return this.fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the light history header.
	 * 
	 * @return the light history header
	 */
	public HashMap<String, Integer> getLightHistoryHeader()
	{
		return this.lightHistoryHeader;
	}

	/**
	 * Sets the light history header.
	 * 
	 * @param lightHistoryHeader the light history header
	 */
	public void setLightHistoryHeader(HashMap<String, Integer> lightHistoryHeader)
	{
		this.lightHistoryHeader = lightHistoryHeader;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.Response#getResponseTime()
	 */
	@Override
	public Date getResponseTime()
	{
		Date utcDate = LCDateUtil.convertDateToDefaultUTC(super.getResponseTime());
		return utcDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightResponse [getFirstLight()=" + this.getFirstLight() + ", getLights()=" + this.getLights()
				+ ", getFileName()=" + this.getFileName() + ", getLightHistoryHeader()=" + this.getLightHistoryHeader()
				+ ", getMessageIterator()=" + this.getMessageIterator() + ", getMessageList()=" + this.getMessageList()
				+ ", getMessageInfoList()=" + this.getMessageInfoList() + ", isOperationSuccess()="
				+ this.isOperationSuccess()
				+ "]";
	}

}