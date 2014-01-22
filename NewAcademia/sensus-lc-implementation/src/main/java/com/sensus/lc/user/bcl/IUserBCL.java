package com.sensus.lc.user.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.UserRequest;

/**
 * The Interface IUserBCL.
 *
 * @author QATEmployee
 */
public interface IUserBCL
{
	/**
	 * Fetch all users.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> fetchAllUsers(InquiryPaginationRequest inquiryPaginationRequest);

	/**
	 * Insert user.
	 *
	 * @param userRequest the user request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> insertUser(UserRequest userRequest);

	/**
	 * Fetch user by id.
	 *
	 * @param userRequest the user request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> fetchUserById(UserRequest userRequest);

	/**
	 * Update user.
	 *
	 * @param userRequest the user request
	 * @return the internal response
	 */
	InternalResponse updateUser(UserRequest userRequest);

	/**
	 * Delete user.
	 *
	 * @param userRequest the user request
	 * @return the internal response
	 */
	InternalResponse deleteUser(UserRequest userRequest);

	/**
	 * Fetch user by name.
	 *
	 * @param userRequest the user request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> fetchUserByName(UserRequest userRequest);

}
