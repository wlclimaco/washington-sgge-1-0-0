package com.sensus.lc.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.group.bcf.IGroupBCF;
import com.sensus.lc.group.model.response.InquiryGroupResponse;
import com.sensus.lc.user.bcf.IUserBCF;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.request.UserRequest;
import com.sensus.lc.user.model.response.InquiryUserResponse;
import com.sensus.lc.user.model.response.UserResponse;

/**
 * The Class UserVIewController.
 * 
 * @author Rodolfo Alves
 */
@Controller
@RequestMapping("/user")
public class UserViewController extends BaseViewController
{
	/*
	 * URLs Mapping
	 */
	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant FETCH_UPDATE. */
	public static final String FETCH_UPDATE = "/update";

	/** The Constant VIEW_USER_MAIN. */
	private static final String VIEW_USER_MAIN = "/user/user_main";

	/** The Constant VIEW_USER_UPDATE. */
	private static final String VIEW_USER_UPDATE = "/user/user_create";

	/** The Constant USER_ID. */
	private static final String USER_ID = "userId";

	/** The Constant INQUIRY_ACTION_TABLE. */
	private static final String INQUIRY_ACTION_TABLE = "table";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "USER_NAME";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String GROUP_RESPONSE = "group_response";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "UserViewController";

	/** The user bcf. */
	private IUserBCF userBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

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
	@Resource
	public void setUserBCF(IUserBCF userBCF)
	{
		this.userBCF = userBCF;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * To get all groups
	 * 
	 * @return the group bcf
	 */

	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Fetch list.
	 * 
	 * @param request the request
	 * @return the model (inquiry User response) and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_USER_MAIN);
		InquiryUserRequest inquiryUserRequest = new InquiryUserRequest(getUserContext(request));
		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		setUserContext(inquiryUserRequest, request);
		// Check for page size
		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			inquiryUserRequest.setPageSize(pageSize);
		}
		inquiryUserRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));
		inquiryUserRequest.setAction(INQUIRY_ACTION_TABLE);

		InquiryUserResponse inquiryUserResponse = getUserBCF().fetchAllUsers(inquiryUserRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryUserResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Fetch.
	 * 
	 * @param userId the user id
	 * @param request the request
	 * @return the response
	 */

	@RequestMapping(value = FETCH_UPDATE, method = RequestMethod.GET)
	public ModelAndView update(@RequestParam(value = USER_ID, required = false) Integer userId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_USER_UPDATE);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		UserResponse userResponse = new UserResponse();

		// Check for update or create
		if (!ValidationUtil.isNullOrZero(userId))
		{

			User user = new User();
			user.setId(userId);

			UserRequest userRequest = new UserRequest(getUserContext(request));
			userRequest.setUser(user);

			userResponse = getUserBCF().fetchUserById(userRequest);
		}

		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest(getUserContext(request));
		inquiryPaginationRequest.setAction(INQUIRY_ACTION_TABLE);
		InquiryGroupResponse inquiryGroupResponse = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);

		try
		{
			modelAndView.addObject(GROUP_RESPONSE, getMapper().writeValueAsString(inquiryGroupResponse));
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(userResponse));
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, userResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return modelAndView;

	}

}
