package com.sensus.mlc.group.dac;

import java.util.Arrays;
import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Class MockGroupDAC.
 */
public class MockGroupDAC extends AbstractMockBase implements IGroupDAC
{
	/** The Constant ARBITRARY_GROUP_ID_1. */
	private static final Integer ARBITRARY_GROUP_ID_1 = 1;

	/** The Constant ARBITRARY_GROUP_ID_2. */
	private static final Integer ARBITRARY_GROUP_ID_2 = 2;

	/** The Constant ARBITRARY_GROUP_ID_3. */
	private static final Integer ARBITRARY_GROUP_ID_3 = 3;

	/** The Constant ARBITRARY_COUNT_LIGHT_FROM_GROUP_5. */
	private static final Integer ARBITRARY_COUNT_LIGHT_FROM_GROUP_5 = 5;

	/** The Constant ARBITRARY_COUNT_LIGHT_GROUP_3. */
	private static final Integer ARBITRARY_COUNT_LIGHT_GROUP_3 = 3;

	/** The Constant ARBITRARY_COUNT_LIGHT_GROUP_6. */
	private static final Integer ARBITRARY_COUNT_LIGHT_GROUP_6 = 6;

	/** The Constant ALREAD_IN_GROUP. */
	private static final String ALREAD_IN_GROUP = "alreadInGroup";

	/**
	 * Instantiates a new mock group dac.
	 */
	public MockGroupDAC()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchAllGroups(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupById(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupById(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupByName(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupByName(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByLight(LightRequest request)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#insertGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#insertSmartPointToAutoGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> insertSmartPointToAutoGroup(GroupRequest groupRequest)
	{

		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			map.put(ALREAD_IN_GROUP, 1);
			response.getResultsList().add(map);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			map.put(ALREAD_IN_GROUP, 1);
			map.put("maxGroups", 1);
			response.getResultsList().add(map);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#insertSmartPointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#deleteGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#updateGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#countLightGroups(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public Integer countLightGroups(LightRequest lightRequest)
	{
		if (lightRequest.getFirstLight().getRniId() == 2)
		{
			return ARBITRARY_COUNT_LIGHT_GROUP_6;
		}
		else
		{
			return ARBITRARY_COUNT_LIGHT_GROUP_3;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#deleteSmartPointFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		return testSituationsGroupResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchCanDelete(com.sensus.mlc.group.model.request.GroupRequest)
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
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchGroupsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsBySmartPoint(LightRequest request)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
		response.getResultsList().add(TestBaseUtil.createGroup());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#updateSmartPointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateSmartPointToGroup(GroupRequest groupRequest)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchGroupsByIdList(com.sensus.mlc.group.model.request.GroupRequest)
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
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchLightsWithGroupsMaxAllowed(com.sensus.mlc.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightsWithGroupsMaxAllowed(GroupRequest groupRequest)
	{
		return new InternalResultsResponse<>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.dac.IGroupDAC#fetchLightsBelongGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchLightsBelongGroup(GroupRequest groupRequest)
	{
		return new InternalResultsResponse<>();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.dac.IGroupDAC#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		response.getResultsList().add(ARBITRARY_COUNT_LIGHT_FROM_GROUP_5);
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