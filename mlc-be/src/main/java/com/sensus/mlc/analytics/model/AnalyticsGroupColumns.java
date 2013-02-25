/**
 *
 */
package com.sensus.mlc.analytics.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;


/**
 * The Class AnalyticsGroupColumns.
 * 
 * @author Igor Mendes - QAT Brazil
 */
public class AnalyticsGroupColumns
{
	/** The description. */
	private String description;

	/** The value. */
	private Double value;

	/** The date. */
	private Date date;

	/** The average. */
	private Double average;

	/** The trend. */
	private String trends;

	/** The change. */
	private Double change;

	/** The analytics type enum. */
	private AnalyticsTypeEnum analyticsTypeEnum;

	/** The view type enum. */
	private DashboardViewTypeEnum dashBoardViewTypeEnum;

	/**
	 * Instantiates a new analytics group columns.
	 */
	public AnalyticsGroupColumns()
	{
	}

	/**
	 * Instantiates a new analytics group columns.
	 * 
	 * @param analyticsDescription the description
	 * @param analyticsValue the value
	 */
	public AnalyticsGroupColumns(String analyticsDescription, Double analyticsValue)
	{
		setDescription(analyticsDescription);
		setValue(analyticsValue);
	}

	/**
	 * Instantiates a new analytics group columns.
	 * 
	 * @param descriptionValue the description value
	 * @param valueValue the value value
	 * @param dateValue the date value
	 */
	public AnalyticsGroupColumns(String descriptionValue, Double valueValue, Date dateValue)
	{
		setDescription(descriptionValue);
		setValue(valueValue);
		setDate(dateValue);
	}

	/**
	 * Instantiates a new analytics group columns.
	 * 
	 * @param analyticsTypeEnumValue the analytics type enum value
	 * @param valueValue the value value
	 * @param changeValue the change value
	 * @param averageValue the average value
	 */
	public AnalyticsGroupColumns(AnalyticsTypeEnum analyticsTypeEnumValue, Double valueValue, Double changeValue,
			Double averageValue)
	{
		setAnalyticsTypeEnum(analyticsTypeEnumValue);
		setValue(valueValue);
		setChange(changeValue);
		setAverage(averageValue);
	}

	/**
	 * Instantiates a new analytics group columns.
	 * 
	 * @param analyticsTypeEnumValue the analytics type enum value
	 * @param valueValue the value value
	 * @param changeValue the change value
	 * @param averageValue the average value
	 * @param dashBoardViewType the dash board view type enum
	 */
	public AnalyticsGroupColumns(AnalyticsTypeEnum analyticsTypeEnumValue, Double valueValue, Double changeValue,
			Double averageValue, DashboardViewTypeEnum dashBoardViewType)
	{
		setAnalyticsTypeEnum(analyticsTypeEnumValue);
		setValue(valueValue);
		setChange(changeValue);
		setAverage(averageValue);
		setDashboardViewTypeEnum(dashBoardViewType);
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the new description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Double getValue()
	{
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value the new value
	 */
	public void setValue(Double value)
	{
		this.value = value;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getDate()
	{
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date the new date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * Gets the average.
	 * 
	 * @return the average
	 */
	public Double getAverage()
	{
		return this.average;
	}

	/**
	 * Sets the average.
	 * 
	 * @param average the new average
	 */
	public void setAverage(Double average)
	{
		this.average = average;
	}

	/**
	 * Gets the change.
	 * 
	 * @return the change
	 */
	public Double getChange()
	{
		return this.change;
	}

	/**
	 * Sets the change.
	 * 
	 * @param change the new change
	 */
	public void setChange(Double change)
	{
		this.change = change;
	}

	/**
	 * Gets the analytics type enum.
	 * 
	 * @return the analytics type enum
	 */
	public AnalyticsTypeEnum getAnalyticsTypeEnum()
	{
		return this.analyticsTypeEnum;
	}

	/**
	 * Sets the analytics type enum.
	 * 
	 * @param analyticsTypeEnum the new analytics type enum
	 */
	public void setAnalyticsTypeEnum(AnalyticsTypeEnum analyticsTypeEnum)
	{
		this.analyticsTypeEnum = analyticsTypeEnum;
	}

	/**
	 * Sets the analytics type enum value.
	 * 
	 * @param valueValue the new analytics type enum value
	 */
	public void setAnalyticsTypeEnumValue(String valueValue)
	{
		this.analyticsTypeEnum = AnalyticsTypeEnum.enumForValue(valueValue);
	}

	/**
	 * Gets the analytics type enum value.
	 * 
	 * @return the analytics type enum value
	 */
	public String getAnalyticsTypeEnumValue()
	{
		if (this.analyticsTypeEnum == null)
		{
			return null;
		}

		return this.analyticsTypeEnum.getValue();
	}

	/**
	 * Gets the dashboard view type enum.
	 * 
	 * @return the dashboard view type enum
	 */
	public DashboardViewTypeEnum getDashboardViewTypeEnum()
	{
		return this.dashBoardViewTypeEnum;
	}

	/**
	 * Sets the dashboard view type enum.
	 * 
	 * @param dashBoardViewType the new dashboard view type enum
	 */
	public void setDashboardViewTypeEnum(DashboardViewTypeEnum dashBoardViewType)
	{
		this.dashBoardViewTypeEnum = dashBoardViewType;
	}

	/**
	 * Sets the view type enum value.
	 * 
	 * @param valueValue the new view type enum value
	 */
	public void setDashboardViewTypeEnumValue(String valueValue)
	{
		this.dashBoardViewTypeEnum = DashboardViewTypeEnum.enumForValue(valueValue);
	}

	/**
	 * Gets the trends.
	 * 
	 * @return the trends
	 */
	public String getTrends()
	{
		return this.trends;
	}

	/**
	 * Sets the trends.
	 * 
	 * @param trends the new trends
	 */
	public void setTrends(String trends)
	{
		this.trends = trends;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsGroupColumns [getDescription()=" + getDescription() + ", getDashboardViewTypeEnum()="
				+ getDashboardViewTypeEnum() + ", getValue()=" + getValue()
				+ ", getDate()=" + getDate() + ", getAverage()=" + getAverage() + ", getChange()=" + getChange()
				+ ", getAnalyticsTypeEnum()=" + getAnalyticsTypeEnum() + ", getAnalyticsTypeEnumValue()="
				+ getAnalyticsTypeEnumValue() + ", getTrends()=" + getTrends() + "]";
	}
}