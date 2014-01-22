/**
 *
 */
package com.sensus.lc.analytics.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsRangeDateEnum;
import com.sensus.lc.analytics.model.AnalyticsTypeEnum;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;

/**
 * The Class InquiryAnalyticsRequest.
 * 
 * @author Igor Mendes - QAT Brazil
 */
public class InquiryAnalyticsRequest extends InquiryPaginationRequest
{
	/** The analytics group. */
	private AnalyticsGroup group;

	/** The analytics type enum. */
	private AnalyticsTypeEnum analyticsTypeEnum;

	/** The analytics date type enum. */
	private AnalyticsDateTypeEnum analyticsDateTypeEnum;

	/** The analytics range date enum. */
	private AnalyticsRangeDateEnum analyticsRangeDateEnum;

	/** The groups. */
	private List<AnalyticsGroup> groups;

	/**
	 * Instantiates a new inquiry analytics request.
	 */
	public InquiryAnalyticsRequest()
	{
	}

	/**
	 * Instantiates a new inquiry analytics request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryAnalyticsRequest(UserContext userContext)
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
	 * Gets the groups.
	 * 
	 * @return the groups
	 */
	public List<AnalyticsGroup> getGroups()
	{
		return groups;
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
	 * Gets the analytics range date enum
	 * 
	 * @return the analytics range date enum
	 */
	public AnalyticsRangeDateEnum getAnalyticsRangeDateEnum()
	{
		return analyticsRangeDateEnum;
	}

	/**
	 * Sets the analytics range date enum enum.
	 * 
	 * @param analyticsRangeDateEnumEnum the new analytics range date enum enum
	 */
	public void setAnalyticsRangeDateEnumEnum(AnalyticsRangeDateEnum analyticsRangeDateEnum)
	{
		this.analyticsRangeDateEnum = analyticsRangeDateEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryAnalyticsRequest [getGroup()=" + getGroup() + ", getAnalyticsTypeEnum()="
				+ getAnalyticsTypeEnum() + ", getAnalyticsDateTypeEnum()=" + getAnalyticsDateTypeEnum()
				+ ", getGroups()=" + getGroups() + ", getAnalyticsRangeDateEnum()="
				+ getAnalyticsRangeDateEnum() + ", toString()=" + super.toString() + "]";
	}

}
