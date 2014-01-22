package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.user.bcf.IUserBCF;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.response.InquiryUserResponse;

/**
 * The Class FilterUsers.
 */
/**
 * @author QAT
 * 
 */
@Component
public class FilterUsers extends AbstractFilterBase
{

	/** The Constant FULLNAME. */
	private static final String FULLNAME = "full_name";

	/** The Constant USERS. */
	private static final String USERS = "USERS";

	/** The user bcf. */
	private IUserBCF userBCF;

	/**
	 * Gets the user bcf.
	 * 
	 * @return the userBCF
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * Sets the user bcf.
	 * 
	 * @param userBCF the userBCF to set
	 */
	@Resource
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return USERS.equals(filter);
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

		InquiryUserRequest userRequest = new InquiryUserRequest(userContext);
		userRequest.setPageSize(0);
		userRequest.addSortExpressions(new SortExpression(FULLNAME, Direction.Ascending));
		InquiryUserResponse userResponse = getUserBCF().fetchAllUsers(userRequest);
		if (!userResponse.isOperationSuccess() || ValidationUtil.isNullOrEmpty(userResponse.getUsers()))
		{
			filtersResponse.setUsers(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
			return;
		}

		for (User user : userResponse.getUsers())
		{
			records.put(user.getId().toString(), user.getFullName());
		}

		filtersResponse.setUsers(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
