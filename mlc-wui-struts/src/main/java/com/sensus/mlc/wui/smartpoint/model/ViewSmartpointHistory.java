package com.sensus.mlc.wui.smartpoint.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * The Class ViewSmartpointHistory is view helper class.
 * 
 * @author Alexandre Tiveron
 * 
 */

@SuppressWarnings("serial")
public class ViewSmartpointHistory implements Serializable, Comparator<ViewSmartpointHistory>
{

	/** The date. */
	private String date;

	/** The Smartpoint history list. */
	private List<SmartpointHistory> smartpointHistoryList;

	/**
	 * Gets the smartpoint history list.
	 * 
	 * @return the smartpoint history list
	 */
	public List<SmartpointHistory> getSmartpointHistoryList()
	{
		return smartpointHistoryList;
	}

	/**
	 * Sets the smartpoint history list.
	 * 
	 * @param smartpointHistoryList the new smartpoint history list
	 */
	public void setSmartpointHistoryList(List<SmartpointHistory> smartpointHistoryList)
	{
		this.smartpointHistoryList = smartpointHistoryList;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ViewSmartpointHistory o1, ViewSmartpointHistory o2)
	{
		return o2.getDate().compareTo(o1.getDate());
	}
}
