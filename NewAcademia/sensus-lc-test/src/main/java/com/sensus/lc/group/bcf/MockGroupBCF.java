package com.sensus.lc.group.bcf;

import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.group.model.response.GroupResponse;
import com.sensus.lc.group.model.response.InquiryGroupResponse;
import com.sensus.lc.light.model.request.LightRequest;

public class MockGroupBCF extends AbstractMockBase implements IGroupBCF
{

	@Override
	public GroupResponse insertLightToGroup(GroupRequest groupRequest)
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
	public GroupResponse deleteLightFromGroup(GroupRequest groupRequest)
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
	public GroupResponse fetchGroupByName(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

	@Override
	public GroupResponse fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		return new GroupResponse();
	}

}
