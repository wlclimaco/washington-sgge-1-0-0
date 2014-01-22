package com.sensus.lc.controller.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.user.bcf.IUserBCF;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.request.UserRequest;
import com.sensus.lc.user.model.response.InquiryUserResponse;
import com.sensus.lc.user.model.response.UserResponse;

/**
 * The Class UserAPIController.
 *
 * @author Wsilva
 */
@Controller
@RequestMapping("/api/user")
public class UserAPIController extends BaseController
{
	/*
	 * URLs Mapping
	 */
	private static final String MAP_FETCH = "/fetch";
	private static final String MAP_FETCH_ALL = "/fetchall";
	private static final String MAP_DELETE = "/delete";
	private static final String MAP_INSERT = "/insert";
	private static final String MAP_UPDATE = "/update";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant SELECTION_PAGINATION_IDS. */
	private static final String SELECTION_PAGINATION_IDS = "selectionPaginationIds";

	private static final String PAGINATION_ALL_SELECTED = "paginationAllSelected";

	/** The user bcf. */

	private IUserBCF userBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "UserAPIController";

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
	 * Fetch.
	 *
	 * @param mapRequest the map request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, Object> mapRequest, HttpServletRequest request)
	{

		UserResponse userResponse = new UserResponse();
		try
		{

			if (!ValidationUtil.isNull(mapRequest.get(ID)))
			{
				User user = new User();
				user.setId(Integer.valueOf(mapRequest.get(ID).toString()));

				UserRequest userRequest = new UserRequest();
				// This is user object for the bcf request
				userRequest.setUser(user);
				// UserContext object from session
				setUserContext(userRequest, request);

				userResponse = getUserBCF().fetchUserById(userRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, userResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return userResponse;

	}

	/**
	 * Fetch all.
	 *
	 * @param inquiryUserRequest the inquiry user request
	 * @param request the request
	 * @return the inquiry user response
	 */
	@RequestMapping(value = MAP_FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public InquiryUserResponse fetchAll(@RequestBody InquiryUserRequest inquiryUserRequest, HttpServletRequest request)
	{

		InquiryUserResponse inquiryUserResponse = new InquiryUserResponse();

		try
		{
			// UserContext object from session
			setUserContext(inquiryUserRequest, request);
			inquiryUserResponse = this.getUserBCF().fetchAllUsers(inquiryUserRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryUserResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryUserResponse;

	}

	/**
	 * Delete.
	 *
	 * @param mapRequest the map request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(@RequestBody Map<String, Object> mapRequest, HttpServletRequest request)
	{

		UserResponse userResponse = new UserResponse();
		try
		{
			UserRequest userRequest = new UserRequest();
			setUserContext(userRequest, request);

			if (!ValidationUtil.isNull(mapRequest.get(PAGINATION_ALL_SELECTED)))
			{
				userRequest.setPaginationAllSelected((Boolean)mapRequest.get(PAGINATION_ALL_SELECTED));
			}

			List<Integer> ids = (List<Integer>)mapRequest.get(SELECTION_PAGINATION_IDS);
			if (!ValidationUtil.isNullOrEmpty(ids))
			{
				userRequest.setSelectionPaginationIds(ids);
			}

			userResponse = getUserBCF().deleteUser(userRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, userResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return userResponse;

	}

	/**
	 * Insert.
	 *
	 * @param userRequest the user request
	 * @param request the request
	 * @return the user response
	 */
	@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST)
	@ResponseBody
	public UserResponse insert(@RequestBody UserRequest userRequest, HttpServletRequest request)
	{

		UserResponse userResponse = new UserResponse();

		try
		{
			setUserContext(userRequest, request);
			userResponse = this.getUserBCF().insertUser(userRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, userResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return userResponse;

	}

	/**
	 * Update.
	 *
	 * @param userRequest the user request
	 * @param request the request
	 * @return the user response
	 */
	@RequestMapping(value = MAP_UPDATE, method = RequestMethod.POST)
	@ResponseBody
	public UserResponse update(@RequestBody UserRequest userRequest, HttpServletRequest request)
	{

		UserResponse userResponse = new UserResponse();

		try
		{
			setUserContext(userRequest, request);
			userResponse = getUserBCF().updateUser(userRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, userResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return userResponse;

	}

}
