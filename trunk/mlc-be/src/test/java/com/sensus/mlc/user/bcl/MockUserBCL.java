package com.sensus.mlc.user.bcl;

import static com.sensus.mlc.base.TestBaseUtil.createUser;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.security.util.LCSecurityHandler;
import com.sensus.mlc.user.model.User;
import com.sensus.mlc.user.model.request.UserRequest;

/**
 * The Class MockUserBCL.
 */
public class MockUserBCL extends AbstractMockBase implements IUserBCL
{

	/* (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#fetchAllUsers(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<User> fetchAllUsers(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(createUser());
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#insertUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<User> insertUser(UserRequest userRequest)
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#fetchUserById(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<User> fetchUserById(UserRequest userRequest)
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(createUser());
			response.setStatus(Status.OperationSuccess);
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#updateUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse updateUser(UserRequest userRequest)
	{
		InternalResponse response = new InternalResponse();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#deleteUser(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@Override
	public InternalResponse deleteUser(UserRequest userRequest)
	{

		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.user.bcl.IUserBCL#fetchUserByName(com.sensus.mlc.user.model.request.UserRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<User> fetchUserByName(UserRequest userRequest)
	{
		InternalResultsResponse<User> response = new InternalResultsResponse<User>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.getResultsList().add(createUser());
			response.getFirstResult().setUserName("Test1");
			response.getFirstResult().setPassword(LCSecurityHandler.encryptPassword("Sensus123$", "Test"));
			response.setStatus(Status.OperationSuccess);
			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		return response;
	}

}