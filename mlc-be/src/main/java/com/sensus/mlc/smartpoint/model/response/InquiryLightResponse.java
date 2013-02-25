package com.sensus.mlc.smartpoint.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;

/**
 * The Class InquiryLightResponse.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class InquiryLightResponse extends InquiryResponse
{

	/** The lights. */
	private List<Light> lights;

	/** The String. */
	private String fileName;

	/** The light history. */
	private List<LightHistory> lightHistory;

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
	 * @param lights the new lights
	 */
	public void setLights(List<Light> lights)
	{
		this.lights = lights;
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
	 * Gets the light history.
	 * 
	 * @return the light history
	 */
	public List<LightHistory> getLightHistory()
	{
		return this.lightHistory;
	}

	/**
	 * Sets the light history.
	 * 
	 * @param lightHistory the new light history
	 */
	public void setLightHistory(List<LightHistory> lightHistory)
	{
		this.lightHistory = lightHistory;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		if (ValidationUtil.isNullOrEmpty(new ArrayList(coll)))
		{
			return;
		}

		Class<?> elementClass = coll.iterator().next().getClass();

		if (Light.class.isAssignableFrom(elementClass))
		{
			setLights(new ArrayList<Light>(coll));
			return;
		}

		if (LightHistory.class.isAssignableFrom(elementClass))
		{
			setLightHistory(new ArrayList<LightHistory>(coll));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryLightResponse [getLights()=" + getLights() + ", getFileName()=" + getFileName()
				+ ", getLightHistory()=" + getLightHistory() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}
