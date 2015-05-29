package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.user.model.User;
import com.prosperitasglobal.sendsolv.user.model.request.InquiryUserRequest;
import com.prosperitasglobal.sendsolv.user.model.request.UserRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IUserDAC.
 */
/**
 * @author QATEmployee
 *
 */
public interface IUserDAC
{

	/**
	 * Fetch all users.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> fetchAllUsers(InquiryUserRequest inquiryPaginationRequest);

	/**
	 * Insert user.
	 *
	 * @param userRequest the user request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> insertUser(UserRequest userRequest);

	/**
	 * Insert groups to user.
	 *
	 * @param userRequest the user request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> insertGroupsToUser(UserRequest userRequest);

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
	 * Delete groups by user.
	 *
	 * @param userRequest the user request
	 * @return the internal response
	 */
	InternalResponse deleteGroupsByUser(UserRequest userRequest);

	/**
	 * Delete roles by user.
	 *
	 * @param userRequest the user request
	 * @return the internal response
	 */
	InternalResponse deleteRolesByUser(UserRequest userRequest);

	/**
	 * Insert roles to user.
	 *
	 * @param userRequest the user request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> insertRolesToUser(UserRequest userRequest);

	/**
	 * Fetch user by name.
	 *
	 * @param userRequest the user request
	 * @return the internal results response
	 */
	InternalResultsResponse<User> fetchUserByName(UserRequest userRequest);
}
