package com.sensus.mlc.wui.group.unittest.action;

import java.util.ArrayList;
import java.util.Date;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.ResultsSetInfo;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

/**
 * Test the functionality of GroupAjaxAction.
 * 
 * For some basic use examples of the Struts Test case, see
 * https://cwiki.apache.org/WW/struts-2-junit-plugin-tutorial.html.
 * 
 * @author Anke Doerfel-Parker
 * 
 */
public class GroupBCFMockImpl extends BaseMockImpl implements IGroupBCF
{

	/** The Constant GROUP_COUNT. */
	public static final Integer GROUP_COUNT = 20;

	/** The Constant GROUP_FILTER_COUNT. */
	public static final Integer GROUP_FILTER_COUNT = 19;

	/** The Constant GROUP_NAME. */
	public static final String GROUP_NAME = "Group %d";

	/** The Constant SMARTPOINT_NAME. */
	public static final String SMARTPOINT_NAME = "SmartPoint %d";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#deleteGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		// Invalid inputs cover Failure scenario
		if (groupRequest.getSelectionPaginationIds() == null)
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#deleteSmartPointFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		// Invalid inputs cover Failure scenario
		if ((ValidationUtil.isNull(groupRequest.getGroup()))
				|| (ValidationUtil.isNull(groupRequest.getGroup().getId()))
				|| (groupRequest.getGroup().getId() < 1))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchAllGroups(com.sensus.mlc.group.model.request.InquiryGroupRequest)
	 */
	@Override
	public InquiryGroupResponse fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InquiryGroupResponse response = new InquiryGroupResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setGroups(new ArrayList<Group>());
			Integer pageSize = inquiryPaginationRequest.getPageSize();
			if (!(pageSize > 0))
			{
				pageSize = 20;
			}
			for (int i = 1; i <= pageSize; i++)
			{
				Group group = new Group();
				group.setId(i);
				group.setName(String.format(GROUP_NAME, i));
				group.setDescription(String.format(GROUP_NAME, i));
				group.setCreateDate(new Date());
				group.setLights(new ArrayList<Light>());
				for (int ii = 1; ii <= i; ii++)
				{

					Light sp = new Light();
					sp.setId(i);
					sp.setRniId(i);
					group.getLights().add(sp);
				}
				group.setSmartPointCount(group.getLights().size());
				response.getGroups().add(group);
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(response.getGroups().size());
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupById(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupById(GroupRequest groupRequest)
	{

		GroupResponse response = new GroupResponse();
		if ((groupRequest.getGroup() == null) || (groupRequest.getGroup().getId() == null))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setGroups(new ArrayList<Group>());
			Group group = new Group();
			group.setId(1);
			group.setName(String.format(SMARTPOINT_NAME, 1));
			group.setDescription(String.format(SMARTPOINT_NAME, 1));
			group.setLights(new ArrayList<Light>());
			for (int i = 1; i <= 3; i++)
			{
				Light light = new Light();
				light.setId(i);
				group.getLights().add(light);
			}
			response.getGroups().add(group);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#insertGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNull(groupRequest.getGroup())
				|| ValidationUtil.isNullOrEmpty(groupRequest.getGroup().getName()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setGroups(new ArrayList<Group>());
			response.setOperationSuccess(true);

			response.getGroups().add(groupRequest.getGroup());
			response.getGroups().get(0).setId(1);
			response.getGroups().get(0).setModelAction(PersistanceActionEnum.INSERT);

			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#insertSmartpointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertSmartpointToGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();

		// Invalid inputs cover Failure scenario
		if ((ValidationUtil.isNull(groupRequest.getGroup()))
				|| (ValidationUtil.isNull(groupRequest.getGroup().getId()))
				|| (groupRequest.getGroup().getId() < 1))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * Not currently used.
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#updateGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#updateGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateGroup(GroupRequest groupRequest)
	{

		GroupResponse response = new GroupResponse();
		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNull(groupRequest.getGroup())
				|| ValidationUtil.isNullOrEmpty(groupRequest.getGroup().getName()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setGroups(new ArrayList<Group>());
			response.setOperationSuccess(true);
			response.getGroups().add(groupRequest.getGroup());
			response.getGroups().get(0).setId(1);
			response.getGroups().get(0).setModelAction(PersistanceActionEnum.UPDATE);
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#updateLightIntensityForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightIntensityForGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		// Invalid inputs cover Failure scenario
		if (groupRequest.getPercentage() == 120)
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public GroupResponse fetchGroupsByLight(LightRequest lightRequest)
	{
		GroupResponse response = new GroupResponse();
		if ((lightRequest.getFirstLight() == null))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setGroups(new ArrayList<Group>());
			Group group = new Group();
			group.setId(1);
			group.setName(String.format(GROUP_NAME, 1));
			group.setLights(new ArrayList<Light>());
			for (int i = 1; i <= 3; i++)
			{
				Light sp = new Light();
				sp.setId(i);
				sp.setRniId(i);
				group.getLights().add(sp);
			}
			group.setSmartPointCount(GROUP_COUNT);
			response.getGroups().add(group);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupByName(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupByName(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		if ((groupRequest.getGroup() == null) || (groupRequest.getGroup().getName() == null))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setGroups(new ArrayList<Group>());
			if (groupRequest.getGroup().getName().equals("Tag 1"))
			{
				Group group = new Group();
				group.setId(1);
				group.setName("Tag 1");
				group.setDescription("Description test");
				group.setLights(new ArrayList<Light>());
				for (int i = 1; i <= 3; i++)
				{
					Light sp = new Light();
					sp.setId(i);
					sp.setRniId(i);
					group.getLights().add(sp);
				}
				group.setSmartPointCount(GROUP_COUNT);
				response.getGroups().add(group);
			}
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#updateLightProtectedForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightProtectedForGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		if (ValidationUtil.isNullOrEmpty(groupRequest.getSelectionPaginationIds())
				|| ValidationUtil.isNull(groupRequest.getLightProtected()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public GroupResponse fetchGroupsBySmartPoint(LightRequest lightRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryLightResponse fetchSmartpointByGroupToMap(GroupRequest groupRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();

		if (ValidationUtil.isNull(groupRequest))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public GroupResponse fetchGroupsByIdList(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InquiryLightResponse fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		return new InquiryLightResponse();
	}
}
