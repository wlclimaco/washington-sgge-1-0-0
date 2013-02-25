package com.sensus.mlc.wui.groups.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.util.ModeEnum;

/**
 * The Class GroupBCFMockImpl.
 */
public class GroupBCFMockImpl extends BaseMockImpl implements IGroupBCF
{

	private String groupName = "Group ";

	private Integer groupId = 1;

	/**
	 * Creates the analytics group list.
	 *
	 * @param listSize the list size
	 * @param firstId the first id
	 * @return the list
	 */
	private List<Group> createAnalyticsGroupList(Integer listSize)
	{

		List<Group> groups = new ArrayList<Group>();

		for (int i = 0; i < listSize; i++)
		{
			Group group = new Group();
			group.setName(this.groupName + i);
			group.setId(i + this.groupId);
			group.setDescription("Description of group " + i);
			group.setSmartPointCount(30);
			groups.add(group);
		}

		return groups;
	}

	/**
	 * Gets the group response default.
	 *
	 * @param groupId the group id
	 * @return the group response default
	 */
	private GroupResponse getGroupResponseDefault()
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setGroups(createAnalyticsGroupList(1));
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#insertSmartpointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertSmartpointToGroup(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#updateLightProtectedForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightProtectedForGroup(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#insertGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertGroup(GroupRequest groupRequest)
	{
		this.groupName = groupRequest.getGroup().getName();
		return getGroupResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#updateGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateGroup(GroupRequest groupRequest)
	{
		this.groupId = groupRequest.getGroup().getId();
		return getGroupResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#deleteGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteGroup(GroupRequest groupRequest)
	{
		this.groupId = groupRequest.getSelectionPaginationIds().get(0);
		return getGroupResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#fetchAllGroups(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InquiryGroupResponse fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InquiryGroupResponse response = new InquiryGroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setGroups(createAnalyticsGroupList(inquiryPaginationRequest.getPageSize()));
			return response;
		}

		return (InquiryGroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupById(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupById(GroupRequest groupRequest)
	{
		this.groupId = groupRequest.getGroup().getId();
		return getGroupResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupsByIdList(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupsByIdList(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#deleteSmartPointFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#updateLightIntensityForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightIntensityForGroup(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public GroupResponse fetchGroupsByLight(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public GroupResponse fetchGroupsBySmartPoint(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupByName(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupByName(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InquiryLightResponse fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
