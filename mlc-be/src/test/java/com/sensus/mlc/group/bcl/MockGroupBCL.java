package com.sensus.mlc.group.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class MockGroupBCL.
 */
public class MockGroupBCL extends AbstractMockBase implements IGroupBCL
{
	/** The Constant ARBITRARY_GROUP_ID_1000. */
	private static final int ARBITRARY_GROUP_ID_1000 = 1000;

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchAllGroups(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupById(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupById(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#insertGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		// Simulate a persistence error
		if (!ValidationUtil.isNull(groupRequest.getGroup().getId()))
		{
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#updateGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateGroup(GroupRequest groupRequest)
	{
		InternalResponse internalResponse = new InternalResponse();
		internalResponse.setStatus(Status.OperationSuccess);
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#deleteGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#insertSmartPointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#deleteSmartPointFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		// Generate a persistence error
		if (ValidationUtil.isNullOrZero(groupRequest.getGroup().getId().compareTo(ARBITRARY_GROUP_ID_1000)))
		{
			response.setStatus(Status.ExceptionError);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#updateLightIntensityForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateLightIntensityForGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByLight(LightRequest lightRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupByName(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupByName(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#insertSmartPointToAutoGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToAutoGroup(GroupRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();
		// Generate a persistence error
		if (ValidationUtil.isNullOrZero(groupRequest.getGroup().getId().compareTo(ARBITRARY_GROUP_ID_1000)))
		{
			response.setStatus(Status.ExceptionError);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#updateLightProtectedForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateLightProtectedForGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsBySmartPoint(LightRequest lightRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupsByIdList(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByIdList(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.setStatus(Status.OperationSuccess);
			response.getResultsList().add(1);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			response.getResultsList().add(1);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException();
		}
		return response;
	}

	/**
	 * Gets the group response default.
	 * 
	 * @return the group response default
	 */
	private InternalResultsResponse<Group> getGroupResponseDefault()
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		response.addResult(TestBaseUtil.createGroup());
		return response;
	}

	/**
	 * Test situations group results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Group> testSituationsGroupResultsResponse()
	{
		InternalResultsResponse<Group> internalResultsResponse = new InternalResultsResponse<Group>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getGroupResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/**
	 * Test situations group response.
	 * 
	 * @return the internal response
	 */
	private InternalResponse testSituationsGroupResponse()
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getGroupResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResponse;
	}

	/**
	 * Gets the group response.
	 * 
	 * @return the group response
	 */
	private InternalResultsResponse<Group> getGroupResponse()
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		response.addResult(TestBaseUtil.createGroup());
		return response;
	}

}