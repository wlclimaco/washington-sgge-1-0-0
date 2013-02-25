/**
 *
 */
package com.sensus.mlc.analytics.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;

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

	@Override
	public String toString()
	{
		return "InquiryAnalyticsRequest [getGroup()=" + getGroup() + ", getAnalyticsTypeEnum()="
				+ getAnalyticsTypeEnum() + ", getAnalyticsDateTypeEnum()=" + getAnalyticsDateTypeEnum()
				+ ", getGroups()=" + getGroups() + ", super:" + super.toString() + "]";
	}

}
