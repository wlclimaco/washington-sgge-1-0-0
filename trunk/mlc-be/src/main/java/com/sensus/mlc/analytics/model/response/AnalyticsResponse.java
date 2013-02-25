package com.sensus.mlc.analytics.model.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class AnalyticsResponse.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 * 
 */
public class AnalyticsResponse extends Response
{

	/** The lights. */
	private List<Light> lights;

	/** The alert values. */
	private Map<String, Integer> alertsByType;

	/** The columns. */
	private List<AnalyticsGroupColumns> columns = new ArrayList<AnalyticsGroupColumns>();

	/** The file name. */
	private String fileName;

	/** The analyticsGroups. */
	private List<AnalyticsGroup> analyticsGroups;

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
	 * Gets the alerts by type.
	 * 
	 * @return the alerts by type
	 */
	public Map<String, Integer> getAlertsByType()
	{
		return this.alertsByType;
	}

	/**
	 * Sets the alerts by type.
	 * 
	 * @param alertsByTypes the alerts by type
	 */
	public void setAlertsByType(Map<String, Integer> alertsByTypes)
	{
		this.alertsByType = alertsByTypes;
	}

	/**
	 * Gets the columns.
	 * 
	 * @return the columns
	 */
	public List<AnalyticsGroupColumns> getColumns()
	{
		return this.columns;
	}

	/**
	 * Sets the columns.
	 * 
	 * @param columns the new columns
	 */
	public void setColumns(List<AnalyticsGroupColumns> columns)
	{
		this.columns = columns;
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
	 * Sets the analytics groups.
	 * 
	 * @param analyticsGroups the new analytics groups
	 */
	public void setAnalyticsGroups(List<AnalyticsGroup> analyticsGroups)
	{
		this.analyticsGroups = analyticsGroups;
	}

	/**
	 * Gets the analytics groups.
	 * 
	 * @return the analytics groups
	 */
	public List<AnalyticsGroup> getAnalyticsGroups()
	{
		return this.analyticsGroups;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsResponse [getLights()=" + this.getLights() + ", getAlertsByType()=" + this.getAlertsByType()
				+ ", getColumns()=" + this.getColumns() + ", getFileName()=" + this.getFileName()
				+ ", getAnalyticsGroups()="
				+ this.getAnalyticsGroups() + ", getMessageIterator()=" + this.getMessageIterator()
				+ ", getMessageList()="
				+ this.getMessageList() + ", getMessageInfoList()=" + this.getMessageInfoList()
				+ ", isOperationSuccess()="
				+ this.isOperationSuccess() + "]";
	}

}