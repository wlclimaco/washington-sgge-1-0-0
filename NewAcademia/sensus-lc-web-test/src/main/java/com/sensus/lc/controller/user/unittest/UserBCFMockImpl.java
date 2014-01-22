package com.sensus.lc.controller.user.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.user.bcf.IUserBCF;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.InquiryUserRequest;
import com.sensus.lc.user.model.request.UserRequest;
import com.sensus.lc.user.model.response.InquiryUserResponse;
import com.sensus.lc.user.model.response.UserResponse;

public class UserBCFMockImpl extends BaseMockImpl implements IUserBCF
{

	private static final String USER = "user";
	private static final String DOMAIN = "@mock.com";
	private static final String PASSWORD = "pass";

	private User createUser(Integer id)
	{

		User user = new User();

		user.setCreateDate(new Date());
		user.setEmail(USER + id + DOMAIN);
		user.setFirstName(USER + id);
		user.setLastName(USER);
		user.setFullName(USER + id + USER);
		user.setId(id);
		user.setPassword(PASSWORD + id);

		return user;
	}

	private List<User> createUserList(Integer listSize)
	{

		InquiryUserResponse inquiryUserResponse = new InquiryUserResponse();

		// Create User List
		List<User> userList = new ArrayList<User>();

		// Add User to userList
		inquiryUserResponse.setUsers(userList);

		// Create Users to set at list
		for (int i = 0; i < listSize; i++)
		{
			User user = new User();

			user.setCreateDate(new Date());
			user.setEmail(USER + i + DOMAIN);
			user.setFirstName(USER + i);
			user.setLastName(USER);
			user.setFullName(USER + i + USER);
			user.setId(i);
			user.setPassword(PASSWORD + i);

			userList.add(user);
		}

		return userList;
	}

	@Override
	public UserResponse fetchUserById(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(userRequest.getUser().getId()))
			{
				response.setUser(createUser(userRequest.getUser().getId()));
				return response;
			}
		}

		return (UserResponse)testOtherDefaultModes(response);

	}

	@Override
	public UserResponse changePassword(UserRequest arg0)
	{
		UserResponse response = new UserResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (UserResponse)testOtherDefaultModes(response);
	}

	@Override
	public UserResponse deleteUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNullOrEmpty(userRequest.getSelectionPaginationIds()))
			{
				response.setOperationSuccess(true);
				return response;
			}
		}

		return (UserResponse)testOtherDefaultModes(response);
	}

	@Override
	public InquiryUserResponse fetchAllUsers(InquiryUserRequest request)
	{

		InquiryUserResponse response = new InquiryUserResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setUsers(createUserList(request.getPageSize()));
			return response;
		}

		return (InquiryUserResponse)testOtherDefaultModes(response);

	}

	@Override
	public UserResponse fetchUserByName(UserRequest arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponse insertUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (UserResponse)testOtherDefaultModes(response);
	}

	@Override
	public UserResponse updateUser(UserRequest arg0)
	{
		UserResponse response = new UserResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (UserResponse)testOtherDefaultModes(response);
	}
}
