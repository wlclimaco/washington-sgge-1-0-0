package com.sensus.mlc.wui.base.model;

import java.util.List;
import java.util.Map;

import com.sensus.mlc.wui.analytics.model.ViewAnalyticsGroupColumns;

/**
 * Base class for Json results for callbacks by the JQuery highchart.
 * 
 * @author Raphael Constantino
 * 
 */

@SuppressWarnings("serial")
public class GraphicJsonResult extends JsonResult
{

	/** The columns types. */
	private Map<String, List<ViewAnalyticsGroupColumns>> columnsTypes;

	/** The dates. */
	private String[] dates;

	/**
	 * Gets the columns types.
	 * 
	 * @return the columns types
	 */
	public final Map<String, List<ViewAnalyticsGroupColumns>> getColumnsTypes()
	{
		return columnsTypes;
	}

	/**
	 * Sets the columns types.
	 * 
	 * @param columnsTypes the columns types
	 */
	public final void setColumnsTypes(Map<String, List<ViewAnalyticsGroupColumns>> columnsTypes)
	{
		this.columnsTypes = columnsTypes;
	}

	/**
	 * Gets the dates.
	 * 
	 * @return the dates
	 */
	public String[] getDates()
	{
		return dates;
	}

	/**
	 * Sets the dates.
	 * 
	 * @param dates the new dates
	 */
	public void setDates(String[] dates)
	{
		this.dates = dates;
	}

}
