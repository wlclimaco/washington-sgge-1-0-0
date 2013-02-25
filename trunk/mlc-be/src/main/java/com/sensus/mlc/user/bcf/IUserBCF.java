package com.sensus.mlc.user.bcf;

import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.user.model.response.UserResponse;

/**
 * The Interface ITagBCF.
 *
 * @author - Igor Mendes - QAT
 */
public interface IUserBCF
{

	/**
	 * Fetch all users.
	 *
	 * @param inquiryUserRequest the inquiry user request
	 * @return the inquiry user response
	 */
	InquiryUserResponse fetchAllUsers(InquiryUserRequest inquiryUserRequest);

	/**
	 * Insert user.
	 *
	 * @param userRequest the user request
	 * @return the user response
	 */
	UserResponse insertUser(UserRequest userRequest);

	/**
	 * Delete user.
	 *
	 * @param userRequest the user request
	 * @return the user response
	 */
	UserResponse deleteUser(UserRequest userRequest);

	/**
	 * Update user.
	 *
	 * @param userRequest the user request
	 * @return the user response
	 */
	UserResponse updateUser(UserRequest userRequest);

	/**
	 * Change password.
	 *
	 * @param userRequest the user request
	 * @return the user response
	 */
	UserResponse changePassword(UserRequest userRequest);

	/**
	 * Fetch user by id.
	 *
	 * @param userRequest the user request
	 * @return the user response
	 */
	UserResponse fetchUserById(UserRequest userRequest);

	/**
	 * Fetch user by name.
	 *
	 * @param userRequest the user request
	 * @return the user response
	 */
	UserResponse fetchUserByName(UserRequest userRequest);

}
