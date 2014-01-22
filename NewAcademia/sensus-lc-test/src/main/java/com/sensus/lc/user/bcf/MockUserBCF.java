package com.sensus.lc.user.bcf;

import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.request.UserRequest;
import com.sensus.lc.user.model.response.InquiryUserResponse;
import com.sensus.lc.user.model.response.UserResponse;

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
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			UserResponse response = new UserResponse();
			response.setOperationSuccess(Boolean.FALSE);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

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
