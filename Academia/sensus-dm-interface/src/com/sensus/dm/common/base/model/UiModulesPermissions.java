package com.sensus.dm.common.base.model;

import java.util.List;

/**
 * The Class UiModules.
 * 
 * @author QAT Global.
 */
public class UiModulesPermissions
{
	/** The tabs. */
	private List<String> tabs;

	/** The summary data. */
	private List<String> summaryDatas;

	/** The content. */
	private List<String> contents;

	/**
	 * Gets the tabs.
	 * 
	 * @return the tabs
	 */
	public List<String> getTabs()
	{
		return tabs;
	}

	/**
	 * Sets the tabs.
	 * 
	 * @param param the new tabs
	 */
	public void setTabs(List<String> param)
	{
		tabs = param;
	}

	/**
	 * Gets the summary datas.
	 * 
	 * @return the summary datas
	 */
	public List<String> getSummaryDatas()
	{
		return summaryDatas;
	}

	/**
	 * Sets the summary datas.
	 * 
	 * @param param the new summary datas
	 */
	public void setSummaryDatas(List<String> param)
	{
		summaryDatas = param;
	}

	/**
	 * Gets the contents.
	 * 
	 * @return the contents
	 */
	public List<String> getContents()
	{
		return contents;
	}

	/**
	 * Sets the contents.
	 * 
	 * @param param the new contents
	 */
	public void setContents(List<String> param)
	{
		contents = param;
	}

	/**
	 * Instantiates a new ui modules permissions.
	 */
	public UiModulesPermissions()
	{

	}

	/**
	 * Instantiates a new ui modules permissions.
	 * 
	 * @param tabsParam the tabs
	 * @param summaryDatasParam the summary datas
	 * @param contentsParam the contents
	 */
	public UiModulesPermissions(List<String> tabsParam, List<String> summaryDatasParam, List<String> contentsParam)
	{
		setTabs(tabsParam);
		setSummaryDatas(summaryDatasParam);
		setContents(contentsParam);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UiModulesPermissions [getTabs()=" + getTabs() + ", getSummaryDatas()=" + getSummaryDatas()
				+ ", getContents()=" + getContents() + "]";
	}
}
