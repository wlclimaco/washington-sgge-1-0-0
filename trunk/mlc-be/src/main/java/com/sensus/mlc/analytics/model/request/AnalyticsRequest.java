package com.sensus.mlc.analytics.model.request;

import java.util.Date;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.DashboardViewTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class AnalyticsRequest.
 * 
 * @author - Guilherme Vicentine - QAT Brazil
 */
public class AnalyticsRequest extends LightingControlRequest
{

	/** The analytics group. */
	private AnalyticsGroup group;

	/** The initial date. */
	private Date initialDate;

	/** The end date. */
	private Date endDate;

	/** The analytics type enum. */
	private AnalyticsTypeEnum analyticsTypeEnum;

	/** The analytics date type enum. */
	private AnalyticsDateTypeEnum analyticsDateTypeEnum;

	/** The alarm warning sub type. */
	private StatusExceptionTypeEnum statusExceptionTypeEnum;

	/** The view type enum. */
	private DashboardViewTypeEnum dashBoardViewTypeEnum;

	/** The file name. */
	private String fileName;

	/** The is monitored. */
	private Boolean monitored;

	/**
	 * Instantiates a new analytics request.
	 */
	public AnalyticsRequest()
	{
	}

	/**
	 * Instantiates a new analytics request.
	 * 
	 * @param userContext the user context
	 */
	public AnalyticsRequest(UserContext userContext)
	{
		super(userContext);
	}

	/**
	 * Instantiates a new analytics request.
	 * 
	 * @param userContext the user context
	 * @param tenant the tenant
	 */
	public AnalyticsRequest(UserContext userContext, Tenant tenant)
	{
		super(userContext, tenant);
	}

	/**
	 * Gets the initial date.
	 * 
	 * @return the initial date
	 */
	public Date getInitialDate()
	{
		return this.initialDate;
	}

	/**
	 * Gets the alarm warning sub type.
	 * 
	 * @return the alarm warning sub type
	 */
	public StatusExceptionTypeEnum getStatusExceptionTypeEnum()
	{
		return this.statusExceptionTypeEnum;
	}

	/**
	 * Sets the alarm warning sub type.
	 * 
	 * @param statusExceptionTypeEnum the new status exception type enum
	 */
	public void setStatusExceptionTypeEnum(StatusExceptionTypeEnum statusExceptionTypeEnum)
	{
		this.statusExceptionTypeEnum = statusExceptionTypeEnum;
	}

	/**
	 * Sets the initial date.
	 * 
	 * @param initialDate the new initial date
	 */
	public void setInitialDate(Date initialDate)
	{
		this.initialDate = initialDate;
	}

	/**
	 * Gets the group.
	 * 
	 * @return the group
	 */
	public AnalyticsGroup getGroup()
	{
		return this.group;
	}

	/**
	 * Sets the group.
	 * 
	 * @param group the new group
	 */
	public void setGroup(AnalyticsGroup group)
	{
		this.group = group;
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
	 * Gets the analytics date type enum.
	 * 
	 * @return the analytics date type enum
	 */
	public AnalyticsDateTypeEnum getAnalyticsDateTypeEnum()
	{
		return this.analyticsDateTypeEnum;
	}

	/**
	 * Sets the analytics date type enum.
	 * 
	 * @param analyticsDateTypeEnum the new analytics date type enum
	 */
	public void setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum analyticsDateTypeEnum)
	{
		this.analyticsDateTypeEnum = analyticsDateTypeEnum;
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
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
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
	 * Gets the checks if is monitored.
	 * 
	 * @return the checks if is monitored
	 */
	public Boolean isMonitored()
	{
		return this.monitored;
	}

	/**
	 * Sets the checks if is monitored.
	 * 
	 * @param isMonitored the new checks if is monitored
	 */
	public void setIsMonitored(Boolean isMonitored)
	{
		this.monitored = isMonitored;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.base.model.request.LightingControlRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyticsRequest [getInitialDate()=" + getInitialDate() + ", getStatusExceptionTypeEnum()="
				+ getStatusExceptionTypeEnum() + ", getGroup()=" + getGroup() + ", getAnalyticsTypeEnum()="
				+ getAnalyticsTypeEnum() + ", getAnalyticsDateTypeEnum()=" + getAnalyticsDateTypeEnum()
				+ ", getEndDate()=" + getEndDate() + ", getDashboardViewTypeEnum()=" + getDashboardViewTypeEnum()
				+ ", getFileName()=" + getFileName() + ", getIsMonitored()=" + isMonitored() + "]";
	}

}