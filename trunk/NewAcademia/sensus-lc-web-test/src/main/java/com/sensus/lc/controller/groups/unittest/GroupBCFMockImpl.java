package com.sensus.lc.controller.groups.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.group.bcf.IGroupBCF;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.group.model.response.GroupResponse;
import com.sensus.lc.group.model.response.InquiryGroupResponse;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Class GroupBCFMockImpl.
 */
public class GroupBCFMockImpl extends BaseMockImpl implements IGroupBCF
{

	private String groupName = "Group Test";

	private Integer groupId = 1;

	private List<Group> generateOneGroup()
	{
		List<Group> groups = new ArrayList<Group>();

		Group group = new Group();
		group.setName(groupName);
		group.setId(10);
		groups.add(group);

		return groups;
	}

	private List<Group> generateGroupList(Integer size)
	{
		List<Group> groups = new ArrayList<Group>();

		for (int i = 1; i <= size; i++)
		{
			Group group = new Group();
			group.setName(groupName + " - " + i);
			group.setId(i);
			groups.add(group);
		}

		return groups;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.bcf.IGroupBCF#deleteGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	@Override
	public GroupResponse deleteLightFromGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.group.bcf.IGroupBCF#fetchAllGroups(com.sensus.lc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InquiryGroupResponse fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InquiryGroupResponse response = new InquiryGroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setGroups(generateGroupList(10));
			return response;
		}

		return (InquiryGroupResponse)testOtherDefaultModes(response);
	}

	@Override
	public GroupResponse fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.bcf.IGroupBCF#fetchGroupById(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupById(GroupRequest groupRequest)
	{
		groupId = groupRequest.getGroup().getId();
		return getGroupResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.bcf.IGroupBCF#fetchGroupByName(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupByName(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.bcf.IGroupBCF#fetchGroupsByIdList(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupsByIdList(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.bcf.IGroupBCF#fetchGroupsByLight(com.sensus.lc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public GroupResponse fetchGroupsByLight(LightRequest lightRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setGroups(generateOneGroup());
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
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
			response.setGroups(generateOneGroup());
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.bcf.IGroupBCF#insertGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertGroup(GroupRequest groupRequest)
	{
		groupName = groupRequest.getGroup().getName();
		return getGroupResponseDefault();
	}

	@Override
	public GroupResponse insertLightToGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.group.bcf.IGroupBCF#updateGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateGroup(GroupRequest groupRequest)
	{
		groupId = groupRequest.getGroup().getId();
		return getGroupResponseDefault();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.group.bcf.IGroupBCF#updateLightIntensityForGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightIntensityForGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.group.bcf.IGroupBCF#updateLightProtectedForGroup(com.sensus.lc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightProtectedForGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (GroupResponse)testOtherDefaultModes(response);
	}

}
