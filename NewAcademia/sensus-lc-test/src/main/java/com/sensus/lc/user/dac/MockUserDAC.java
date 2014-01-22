package com.sensus.lc.user.dac;

import static com.sensus.lc.base.TestBaseUtil.createUser;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.user.model.User;
import com.sensus.lc.user.model.request.UserRequest;

public class MockUserDAC extends AbstractMockBase implements IUserDAC
{
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<User> getInternalResultsResponseUserBySituation()
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		if (this.getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(createUser());
			return response;
		}
		if (this.getSituationsEnum() == SituationsEnum.ERROR)
		{
			return this.getResponseResultsError();
		}

		return response;
	}

	@Override
	public InternalResultsResponse<User> fetchAllUsers(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResultsResponse<User> insertUser(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResultsResponse<User> insertGroupsToUser(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResultsResponse<User> fetchUserById(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResponse updateUser(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResponse deleteUser(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResponse deleteGroupsByUser(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResultsResponse<User> insertRolesToUser(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResultsResponse<User> fetchUserByName(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}

	@Override
	public InternalResponse deleteRolesByUser(UserRequest userRequest)
	{
		return this.getInternalResultsResponseUserBySituation();
	}
}