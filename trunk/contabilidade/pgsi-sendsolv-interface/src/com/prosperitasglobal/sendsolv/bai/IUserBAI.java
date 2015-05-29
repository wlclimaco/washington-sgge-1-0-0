package com.prosperitasglobal.sendsolv.bai;

import com.prosperitasglobal.sendsolv.user.model.request.InquiryUserRequest;
import com.prosperitasglobal.sendsolv.user.model.request.UserRequest;
import com.prosperitasglobal.sendsolv.user.model.response.InquiryUserResponse;
import com.prosperitasglobal.sendsolv.user.model.response.UserResponse;

public interface IUserBAI
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
