package com.sensus.mlc.group.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;

public class MockGroupBCF extends AbstractMockBase implements IGroupBCF
{

	@Override
	public GroupResponse insertSmartpointToGroup(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse updateLightProtectedForGroup(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse insertGroup(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse updateGroup(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse deleteGroup(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public InquiryGroupResponse fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return new InquiryGroupResponse();
	}

	@Override
	public GroupResponse fetchGroupById(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse fetchGroupsByIdList(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse updateLightIntensityForGroup(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse fetchGroupsByLight(LightRequest lightRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse fetchGroupsBySmartPoint(LightRequest lightRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse fetchGroupByName(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public InquiryLightResponse fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		return new InquiryLightResponse();
	}

}
