package com.sensus.mlc.user.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.user.model.response.UserResponse;

public class MockUserBCF extends AbstractMockBase implements IUserBCF
{

	@Override
	public InquiryUserResponse fetchAllUsers(InquiryUserRequest inquiryUserRequest)
	{
		return new InquiryUserResponse();
	}

	@Override
	public UserResponse insertUser(UserRequest userRequest)
	{
		return new UserResponse();
	}

	@Override
	public UserResponse deleteUser(UserRequest userRequest)
	{
		return new UserResponse();
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest)
	{
		return new UserResponse();
	}

	@Override
	public UserResponse changePassword(UserRequest userRequest)
	{
		return new UserResponse();
	}

	@Override
	public UserResponse fetchUserById(UserRequest userRequest)
	{
		return new UserResponse();
	}

	@Override
	public UserResponse fetchUserByName(UserRequest userRequest)
	{
		return new UserResponse();
	}

}
