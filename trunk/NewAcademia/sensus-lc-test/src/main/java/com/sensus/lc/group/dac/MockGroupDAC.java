package com.sensus.lc.group.dac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Class MockGroupDAC.
 */
public class MockGroupDAC extends AbstractMockBase implements IGroupDAC
{

	/** The Constant SIX. */
	private static final int SIX = 6;

	/** The Constant ARBITRARY_GROUP_ID_1. */
	private static final Integer ARBITRARY_GROUP_ID_1 = 1;

	/** The Constant ARBITRARY_GROUP_ID_2. */
	private static final Integer ARBITRARY_GROUP_ID_2 = 2;

	/** The Constant ARBITRARY_GROUP_ID_3. */
	private static final Integer ARBITRARY_GROUP_ID_3 = 3;

	/**
	 * Instantiates a new mock group dac.
	 */
	public MockGroupDAC()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.group.dac.IGroupDAC#fetchAllGroups(com.sensus.lc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#fetchGroupById(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupById(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#fetchGroupByName(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupByName(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#fetchGroupsByLight(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByLight(LightRequest request)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#insertGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#insertLightToGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertLightToGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#deleteGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#updateGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#deleteLightFromGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteLightFromGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#fetchCanDelete(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public Boolean fetchCanDelete(GroupRequest groupRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return true;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return false;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#updateLightToGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateLightToGroup(GroupRequest groupRequest)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#fetchGroupsByIdList(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByIdList(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<>();
		response.addResults(Arrays.asList(new Group(ARBITRARY_GROUP_ID_1), new Group(ARBITRARY_GROUP_ID_2), new Group(
				ARBITRARY_GROUP_ID_3)));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.group.dac.IGroupDAC#fetchLightsWithGroupsMaxAllowed(com.sensus.lc.group.model.request.GroupRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Light> fetchLightsWithGroupsMaxAllowed(GroupRequest groupRequest)
	{
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			if (getTestControl() == "MAX_GROUPS_PER_LIGHT")
			{
				InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
				response.addResult(TestBaseUtil.createLight());

				return response;
			}

			return new InternalResultsResponse<Light>();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			List<Light> lightList = new ArrayList<Light>();
			for (int i = 0; i <= SIX; i++)
			{
				lightList.add(TestBaseUtil.createLight());
			}

			internalResultsResponse.addResults(lightList);
			return internalResultsResponse;
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.dac.IGroupDAC#fetchLightsBelongGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Light> fetchLightsBelongGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			if (getTestControl() == "LIGHT_BELONG_GROUP")
			{
				InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
				response.addResult(TestBaseUtil.createLight());

				return response;
			}

			return new InternalResultsResponse<Light>();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}
		if (getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			Light light = TestBaseUtil.createLight();
			internalResultsResponse.addResult(light);
			return internalResultsResponse;
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.group.dac.IGroupDAC#fetchCountLightsFromGroups(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		response.getResultsList().add(TestBaseUtil.createGroup());
		return response;
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
			return getGroupResponse();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
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
			return getResponseResultsError();
		}

		return internalResponse;
	}
}