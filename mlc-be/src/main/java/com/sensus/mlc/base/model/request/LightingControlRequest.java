package com.sensus.mlc.base.model.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.Authority;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.Request;
import com.sensus.mlc.base.util.LCPropertiesExtractorUtil;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class LightingControlRequest.
 */
public class LightingControlRequest extends Request
{

	/** The timezone. */
	private String timezone;

	/** The date pattern. */
	private String datePattern;

	/** The action. */
	private String action;

	/**
	 * Instantiates a new lighting control request.
	 */
	public LightingControlRequest()
	{
		setUserContext(new UserContext());
	}

	/**
	 * Instantiates a new lighting control request.
	 */
	public LightingControlRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new lighting control request.
	 * 
	 * @param tenantParam the tenant param
	 */
	public LightingControlRequest(UserContext userContext, Tenant tenantParam)
	{
		userContext.setTenant(tenantParam);
		setUserContext(userContext);
	}

	/**
	 * Sets the tenant.
	 * 
	 * @param tenant the new tenant
	 */
	public void setTenant(Tenant tenant)
	{
		if (checkUserContext())
		{
			getUserContext().setTenant(tenant);
		}
	}

	/**
	 * Gets the tenant.
	 * 
	 * @return the tenant
	 */
	public Tenant getTenant()
	{
		if (checkUserContext() && (getUserContext().getTenant() != null))
		{
			if (getUserContext().<Tenant> getTenant() instanceof Tenant)
			{
				return getUserContext().<Tenant> getTenant();
			}

			return new Tenant(getUserContext().getTenant().getId());
		}

		return new Tenant();
	}

	/**
	 * Gets the allowed group id list.
	 * 
	 * @return the allowed group id list
	 */
	public List<Integer> getAllowedGroupIdList()
	{
		if (checkUserContext())
		{
			return LCPropertiesExtractorUtil.extractAuthorityId(getUserContext().getAuthorities());
		}

		return new ArrayList<Integer>();
	}

	/**
	 * Gets the string allowed groups.
	 * 
	 * @return the string allowed groups
	 */
	public String getStringAllowedGroups()
	{
		String groups = StringUtils.join(getAllowedGroupIdList(), ",");
		if (StringUtils.isBlank(groups))
		{
			return null;
		}
		return groups;
	}

	/**
	 * Sets the allowed group id list.
	 * 
	 * @param groupIdList the new allowed group id list
	 */
	public void setAllowedGroupIdList(List<Integer> groupIdList)
	{
		if (!checkUserContext())
		{
			return;
		}

		if ((groupIdList != null) && !groupIdList.isEmpty())
		{
			for (Integer groupId : groupIdList)
			{
				addAllowedGroupId(groupId);
			}
		}
	}

	/**
	 * Adds the allowed group id.
	 * 
	 * @param groupId the group id
	 */
	public void addAllowedGroupId(Integer groupId)
	{
		if (!checkUserContext())
		{
			return;
		}

		Authority authority = new Authority(groupId, "Group");

		if ((getUserContext().getAuthorities() != null) && !getUserContext().getAuthorities().isEmpty())
		{
			getUserContext().getAuthorities().add(authority);
			return;
		}

		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		getUserContext().setAuthorities(authorities);
	}

	/**
	 * Sets the allowed groups.
	 * 
	 * @param groups the new allowed groups
	 */
	public void setAllowedGroups(List<Group> groups)
	{
		if (!checkUserContext())
		{
			return;
		}

		if ((groups != null) && !groups.isEmpty())
		{
			for (Group group : groups)
			{
				addAllowedGroupId(group.getId());
			}
		}
	}

	/**
	 * Gets the timezone.
	 * 
	 * @return the timezone
	 */
	public String getTimezone()
	{
		return timezone;
	}

	/**
	 * Sets the timezone.
	 * 
	 * @param timezone the new timezone
	 */
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}

	/**
	 * Gets the date pattern.
	 * 
	 * @return the date pattern
	 */
	public String getDatePattern()
	{
		return datePattern;
	}

	/**
	 * Sets the date pattern.
	 * 
	 * @param datePattern the new date pattern
	 */
	public void setDatePattern(String datePattern)
	{
		this.datePattern = datePattern;
	}

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * Check user context.
	 * 
	 * @return true, if successful
	 */
	private boolean checkUserContext()
	{
		return getUserContext() != null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightingControlRequest [getTenant()=" + getTenant() + ", getUserContext()="
				+ getUserContext() + ", getAction()=" + getAction() + "]";
	}
}
