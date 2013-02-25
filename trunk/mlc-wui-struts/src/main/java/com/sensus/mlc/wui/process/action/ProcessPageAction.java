/*
 * 
 */
package com.sensus.mlc.wui.process.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.process.model.LCActionCategoryEnum;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;

/**
 * The Class LongRunningProcessPageAction.
 * 
 * @author Alex Tiveron
 */

@SuppressWarnings("serial")
public class ProcessPageAction extends LayoutBase
{

	/** CONSTANTS *. */

	/**
	 * The default "empty" value for droplists.
	 */
	private static final String DEFAULT_VALUE = "0";

	/**
	 * The key for the default "loading error" droplist prompt.
	 */
	public static final String DEFAULT_ERROR_KEY = "widgets.combobox.errorprompt2";

	/** The Constant GROUP_LIST_ERROR. */
	private static final String USER_LIST_ERROR = "Error loading Tag List";

	/** The user bcf. */
	private IUserBCF userBCF;

	/** The actual date. */
	private String actualDate;

	/**
	 * Gets the user bcf.
	 * 
	 * @return the user bcf
	 */
	public IUserBCF getUserBCF()
	{
		return userBCF;
	}

	/**
	 * Sets the user bcf.
	 * 
	 * @param userBCF the new user bcf
	 */
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * Sets the actual date.
	 * 
	 * @param actualDate the new actual date
	 */
	public void setActualDate(String actualDate)
	{
		this.actualDate = actualDate;
	}

	/**
	 * Gets the actual date.
	 * 
	 * @return the actual date
	 */
	public String getActualDate()
	{
		return actualDate;
	}

	/**
	 * Gets the filter user list.
	 * 
	 * @return the filter user list
	 */
	public List<IdValuePair> getFilterUserList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryUserRequest request = new InquiryUserRequest(getUserContext());
			InquiryUserResponse response = getUserBCF().fetchAllUsers(request);

			if (!response.isOperationSuccess())
			{
				if (LOG.isErrorEnabled())
				{
					LOG.error(USER_LIST_ERROR);
				}

				return list;
			}

			if (!ValidationUtil.isNullOrEmpty(response.getUsers()))
			{
				for (User user : response.getUsers())
				{
					list.add(new IdValuePair(user.getId(), user.getId()));
				}
				Collections.sort(list, new IdValuePair());
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(USER_LIST_ERROR, e);
			}
		}
		return list;
	}

	/**
	 * Gets the process type list.
	 * 
	 * @return the process type list
	 */
	public List<IdValuePair> getProcessTypeList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();

		list.add(new IdValuePair(LCActionCategoryEnum.CONTROL_LIGHTS.getValue(),
				getText("process.page.filter.type.controllights")));
		list.add(new IdValuePair(LCActionCategoryEnum.MANAGE_GROUPS.getValue(),
				getText("process.page.filter.type.managegroups")));
		list.add(new IdValuePair(LCActionCategoryEnum.MANAGE_LIGHTS.getValue(),
				getText("process.page.filter.type.managelights")));
		list.add(new IdValuePair(LCActionCategoryEnum.MANAGE_SCHEDULES.getValue(),
				getText("process.page.filter.type.manageschedules")));
		list.add(new IdValuePair(LCActionCategoryEnum.MANAGE_TAGS.getValue(),
				getText("process.page.filter.type.managetags")));

		return list;
	}

}