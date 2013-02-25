package com.sensus.mlc.analytics.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class InquiryAnalyticsResponse.
 */
public class InquiryAnalyticsResponse extends InquiryResponse
{

	/** The groups. */
	private List<AnalyticsGroup> groups;

	/** The file name. */
	private String fileName;

	/** The lights. */
	private List<Light> lights;

	/**
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<AnalyticsGroup> getGroups()
	{
		return this.groups;
	}

	/**
	 * Sets the groups.
	 * 
	 * @param groups the new groups
	 */
	public void setGroups(List<AnalyticsGroup> groups)
	{
		this.groups = groups;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setGroups(new ArrayList<AnalyticsGroup>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryAnalyticsResponse [getGroups()=" + getGroups() + ", getFileName()=" + getFileName()
				+ "getLights()=" + getLights()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}

}
