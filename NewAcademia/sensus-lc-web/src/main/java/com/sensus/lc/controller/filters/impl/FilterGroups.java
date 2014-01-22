package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.group.bcf.IGroupBCF;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.response.InquiryGroupResponse;

/**
 * The Class FilterGroups.
 */
@Component
public class FilterGroups extends AbstractFilterBase
{
	/** The Constant GROUPS. */
	private static final String GROUPS = "GROUPS";

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/**
	 * Gets the group bcf.
	 * 
	 * @return the groupBCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the groupBCF to set
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return GROUPS.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		Map<String, Object> records = new HashMap<String, Object>();

		InquiryPaginationRequest groupRequest = new InquiryPaginationRequest(userContext);
		groupRequest.setPageSize(0);

		InquiryGroupResponse groupsResponse = getGroupBCF().fetchAllGroups(groupRequest);
		if (groupsResponse.isOperationSuccess())
		{
			if (!ValidationUtil.isNullOrEmpty(groupsResponse.getGroups()))
			{
				for (Group group : groupsResponse.getGroups())
				{
					records.put(group.getId().toString(), group.getName());
				}
			}
		}

		filtersResponse.setGroups(new FiltersModel(FILTER_TYPE_CHECKBOX, records));

	}

}
