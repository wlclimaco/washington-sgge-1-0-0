package com.sensus.lc.analytics.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.lc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsRangeDateEnum;
import com.sensus.lc.analytics.model.AnalyticsTypeEnum;
import com.sensus.lc.analytics.model.DashboardViewTypeEnum;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.NotificationHistory;

/**
 * The Class AnalyticsRequest.
 * 
 * @author - QAT Brazil
 */
public class AnalyticsRequest extends InquiryPaginationRequest
{
	public enum OperatorEnum
	{
		PLUS("+"),
		MINUS("-");

		private String operatorValue;

		private OperatorEnum(String value)
		{
			operatorValue = value;
		}

		public String getValue()
		{
			return operatorValue;
		}
	}

	/**
	 * Attributes.
	 */
	private AnalyticsGroup group;
	private AnalyticsTypeEnum analyticsTypeEnum;
	private AnalyticsDateTypeEnum analyticsDateTypeEnum;
	private DashboardViewTypeEnum dashBoardViewTypeEnum;
	private AnalyticsRangeDateEnum analyticsRangeDateEnum;
	private NotificationHistory notificationHistory;
	private AlertSubTypeEnum alertSubtype;
	private OperatorEnum operator;
	private String fileName;

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
	 * Gets the group.
	 * 
	 * @return the group
	 */
	public AnalyticsGroup getGroup()
	{
		return group;
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
		return analyticsTypeEnum;
	}

	/**
	 * Gets the analytics date type enum.
	 * 
	 * @return the analytics date type enum
	 */
	public AnalyticsDateTypeEnum getAnalyticsDateTypeEnum()
	{
		return analyticsDateTypeEnum;
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
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	@Override
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	@Override
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the dashboard view type enum.
	 * 
	 * @return the dashboard view type enum
	 */
	public DashboardViewTypeEnum getDashboardViewTypeEnum()
	{
		return dashBoardViewTypeEnum;
	}

	/**
	 * Sets the dashboard view type enum.
	 * 
	 * @param dashBoardViewType the new dashboard view type enum
	 */
	public void setDashboardViewTypeEnum(DashboardViewTypeEnum dashBoardViewType)
	{
		dashBoardViewTypeEnum = dashBoardViewType;
	}

	/**
	 * Sets the view type enum value.
	 * 
	 * @param valueValue the new view type enum value
	 */
	public void setDashboardViewTypeEnumValue(String valueValue)
	{
		dashBoardViewTypeEnum = DashboardViewTypeEnum.enumForValue(valueValue);
	}

	/**
	 * Gets the alert subtype.
	 * 
	 * @return the alert subtype
	 */
	public AlertSubTypeEnum getAlertSubtype()
	{
		return alertSubtype;
	}

	/**
	 * Gets the alert subtype value.
	 * 
	 * @return the alert subtype value
	 */
	public Integer getAlertSubtypeValue()
	{
		if (getAlertSubtype() == null)
		{
			return null;
		}
		return getAlertSubtype().getValue();
	}

	/**
	 * Sets the alert subtype.
	 * 
	 * @param alertSubtype the new alert subtype
	 */
	public void setAlertSubtype(AlertSubTypeEnum alertSubtype)
	{
		this.alertSubtype = alertSubtype;
	}

	/**
	 * Gets the notification history.
	 * 
	 * @return the notification history
	 */
	public NotificationHistory getNotificationHistory()
	{
		return notificationHistory;
	}

	/**
	 * Sets the notification history.
	 * 
	 * @param notificationHistory the new notification history
	 */
	public void setNotificationHistory(NotificationHistory notificationHistory)
	{
		this.notificationHistory = notificationHistory;
	}

	/**
	 * Gets the operator.
	 * 
	 * @return the operator
	 */
	public OperatorEnum getOperator()
	{
		return operator;
	}

	/**
	 * Sets the operator.
	 * 
	 * @param operator the new operator
	 */
	public void setOperator(OperatorEnum operator)
	{
		this.operator = operator;
	}

	/**
	 * Gets the dash board view type enum.
	 * 
	 * @return the dash board view type enum
	 */
	public DashboardViewTypeEnum getDashBoardViewTypeEnum()
	{
		return dashBoardViewTypeEnum;
	}

	/**
	 * Sets the dash board view type enum.
	 * 
	 * @param dashBoardViewTypeEnum the new dash board view type enum
	 */
	public void setDashBoardViewTypeEnum(DashboardViewTypeEnum dashBoardViewTypeEnum)
	{
		this.dashBoardViewTypeEnum = dashBoardViewTypeEnum;
	}

	/**
	 * Gets the analytics range date enum.
	 * 
	 * @return the analytics range date enum
	 */
	public AnalyticsRangeDateEnum getAnalyticsRangeDateEnum()
	{
		return analyticsRangeDateEnum;
	}

	/**
	 * Sets the analytics range date enum.
	 * 
	 * @param analyticsRangeDateEnum the new analytics range date enum
	 */
	public void setAnalyticsRangeDateEnum(AnalyticsRangeDateEnum analyticsRangeDateEnum)
	{
		this.analyticsRangeDateEnum = analyticsRangeDateEnum;
	}

}