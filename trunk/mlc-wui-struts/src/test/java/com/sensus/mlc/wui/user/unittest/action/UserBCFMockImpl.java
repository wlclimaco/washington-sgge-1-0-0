package com.sensus.mlc.wui.user.unittest.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.user.bcf.IUserBCF;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.InquiryUserRequest;
import com.sensus.mlc.user.model.request.UserRequest;
import com.sensus.mlc.user.model.response.InquiryUserResponse;
import com.sensus.mlc.user.model.response.UserResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class UserBCFMockImpl extends BaseMockImpl implements IUserBCF
{
	public static final Integer USER_COUNT = 3;

	public static final Integer ALL_USER = 15;

	@Override
	public InquiryUserResponse fetchAllUsers(InquiryUserRequest inquiryUserRequest)
	{
		InquiryUserResponse response = new InquiryUserResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setUsers(new ArrayList<User>());
			Integer pageSize = inquiryUserRequest.getPageSize();

			if (!(pageSize > 0))
			{
				pageSize = ALL_USER;
			}
			for (Integer i = 1; i <= pageSize; i++)
			{
				User user = new User();
				user.setId(i);
				user.setUserName("UserName" + i);
				user.setFirstName("Name " + i);
				user.setLastName("Last Name");
				user.setEmail("user" + i + "@smallvile.com");
				user.setRole("Admin");
				user.setCreateUser("Admin");
				if ((i % 2) == 0)
				{
					user.setAllLightsAuth(false);
					List<Group> groups = new ArrayList<Group>();
					Group group;
					for (int x = 0; x <= 6; x++)
					{
						group = new Group();
						int n = i * x;
						group.setName("Group " + n);
						group.setId(x);
						groups.add(group);
					}
					user.setGroups(groups);
				}
				else
				{
					user.setAllLightsAuth(true);
				}

				user.setLatitude(new BigDecimal(41.25816656));
				user.setLongitude(new BigDecimal(-95.93688238));
				user.setTotalLights(350);
				user.setCreateDate(new Date());
				response.getUsers().add(user);

				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(response.getUsers().size());
				response.setResultsSetInfo(resultsSetInfo);
			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public UserResponse insertUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();

		// Invalid inputs cover Failure scenario
		if ((ValidationUtil.isNull(userRequest)) || (ValidationUtil.isNull(userRequest.getUser())))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public UserResponse deleteUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();

		// Invalid inputs cover Failure scenario
		if ((ValidationUtil.isNull(userRequest)) || (ValidationUtil.isNull(userRequest.getUser())))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();

		// Invalid inputs cover Failure scenario
		if ((ValidationUtil.isNull(userRequest)) || (ValidationUtil.isNull(userRequest.getUser())))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public UserResponse fetchUserById(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{

			if (ValidationUtil.isNullOrZero(userRequest.getUser().getId())
					|| ValidationUtil.isNull(userRequest.getUserContext()))
			{
				response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
				response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
				response.setOperationSuccess(false);
				return response;
			}

			User user = new User();
			user.setId(userRequest.getUser().getId());
			user.setUserName("UserName");
			user.setFirstName("Name");
			user.setLastName("Last Name");
			user.setEmail("user@smallvile.com");

			user.setRole("Admin");
			user.setCreateUser("Admin");
			user.setLatitude(new BigDecimal(41.25816656));
			user.setLongitude(new BigDecimal(-95.93688238));
			user.setCreateDate(new Date());

			List<Group> groups = new ArrayList<Group>();
			Group group;

			for (int x = 5; x <= 8; x++)
			{
				group = new Group();
				group.setName("Group " + x);
				group.setId(x);
				groups.add(group);
			}

			user.setGroups(groups);

			response.setUser(user);
			response.setOperationSuccess(true);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	@Override
	public UserResponse fetchUserByName(UserRequest userRequest)
	{
		UserResponse response = new UserResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			User user = new User();
			user.setId(1);
			user.setUserName("UserName");
			user.setFirstName("Name");
			user.setLastName("Last Name");
			user.setEmail("user@smallvile.com");
			user.setPassword("koala");

			user.setRole("Admin");
			user.setCreateUser("Admin");
			user.setLatitude(new BigDecimal(41.25816656));
			user.setLongitude(new BigDecimal(-95.93688238));
			user.setCreateDate(new Date());
			List<Group> groups = new ArrayList<Group>();
			Group group;
			for (int x = 0; x <= 3; x++)
			{
				group = new Group();
				group.setName("Group " + x);
				group.setId(x);
				groups.add(group);
			}
			user.setGroups(groups);
			response.setUser(user);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

		}

		return response;
	}

	@Override
	public UserResponse changePassword(UserRequest userRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
