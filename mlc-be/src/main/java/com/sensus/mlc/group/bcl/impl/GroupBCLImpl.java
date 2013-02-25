package com.sensus.mlc.group.bcl.impl;

import static com.sensus.mlc.base.util.LCActionUtil.createMessageInfoNone;
import static com.sensus.mlc.base.util.LCActionUtil.createMessageWarningOther;
import static com.sensus.mlc.base.util.LCActionUtil.generateDescription;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;
import static com.sensus.mlc.base.util.LCHelp.generateProcess;
import static com.sensus.mlc.base.util.LCPropertiesExtractorUtil.extractLightId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.group.bcl.IGroupBCL;
import com.sensus.mlc.group.dac.IGroupDAC;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.mlcserver.client.MlcServerClient;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.bcl.ITagBCL;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class GroupBCLImpl.
 * 
 * @author Gustavo Aragao - QAT.
 * 
 */
public class GroupBCLImpl implements IGroupBCL
{

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The group dac. */
	private IGroupDAC groupDAC; // injected by Spring through setter

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The Constant MAX_GROUPS_PER_LIGHT. */
	private static final Integer MAX_GROUPS_PER_LIGHT = 5;

	/** The Constant MAX_GROUPS_FAILURE. */
	private static final String MAX_GROUPS_FAILURE = "sensus.mlc.mlc_action.add_smp_to_grp.already_in_max_groups";

	/** The Constant MAX_GROUPS_FAILURE. */
	private static final String LIGHT_DEACTIVATED = "sensus.mlc.mlc_action.add_smp_to_grp.light_deactivated";

	/** The Constant LIGHT_ALREADY_IN_GROUP. */
	private static final String LIGHT_ALREADY_IN_GROUP = "sensus.mlc.mlc_action.add_smp_to_grp.already.exist";

	/** The Constant AUTO_GROUP_REMOVED. */
	private static final String AUTO_GROUP_REMOVED = "sensus.mlc.tagvalidator.autogroup.removed";

	/** The Constant GROUP_REMOVED. */
	private static final String GROUP_REMOVED = "sensus.mlc.groupbcfimpl.mlcgroupdeleted";

	/** The Constant LIGHT_NOT_IN_THE_GROUP. */
	private static final String LIGHT_NOT_IN_THE_GROUP = "sensus.mlc.mlc_action.add_smp_to_grp.not_in_group";

	/** The Constant PROCESS_RUNNING. */
	private static final String PROCESS_RUNNING = "sensus.mlc.groupbcfimpl.processrunning";

	/** The Constant ALREADY_IN_GROUP. */
	private static final String ALREADY_IN_GROUP = "alreadInGroup";

	/** The Constant MAX_GROUPS. */
	private static final String MAX_GROUPS = "maxGroups";

	/** The Constant NO_LIGHTS_IN_GROUPS. */
	private static final String NO_LIGHTS_IN_GROUPS = "sensus.mlc.mlc_action.light_status.no.lights_in_groups";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP = "sensus.mlc.mlc_action.add_smp_to_grp";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP_BY_POLE_ID =
			"sensus.mlc.mlc_action.add_smp_to_grp_by_poleid";

	/** The mlc gateway ws. */
	private MlcServerClient mlcGatewayWs;

	/** The Constant SMARTPOINT_BCL_BEAN. */
	private static final String SMARTPOINT_ACCESSOR_BCL_BEAN = "smartPointAccessorBCLTarget";

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return this.lcHelp;
	}

	/**
	 * Sets the lc help.
	 * 
	 * @param lcHelp the new lc help
	 */
	public void setLcHelp(LCHelp lcHelp)
	{
		this.lcHelp = lcHelp;
	}

	/**
	 * Gets the tag bcl.
	 * 
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return this.tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 * 
	 * @param tagBCL the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * Sets the group dac.
	 * 
	 * @param groupDACParam the new group dac
	 */
	public void setGroupDAC(IGroupDAC groupDACParam)
	{
		this.groupDAC = groupDACParam;
	}

	/**
	 * Gets the group dac.
	 * 
	 * @return the group dac
	 */
	public IGroupDAC getGroupDAC()
	{
		return this.groupDAC;
	}

	/**
	 * Gets the smart point accessor bcl.
	 * 
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		if (this.smartPointAccessorBCL == null)
		{
			this.smartPointAccessorBCL =
					(ISmartPointAccessorBCL)SensusAppContext.getApplicationContext().getBean(
							SMARTPOINT_ACCESSOR_BCL_BEAN);
		}

		return this.smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 * 
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
	}

	/**
	 * Gets the smart point updater bcl.
	 * 
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		return this.smartPointUpdaterBCL;
	}

	/**
	 * Sets the smart point updater bcl.
	 * 
	 * @param smartPointUpdaterBCL the new smart point updater bcl
	 */
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)
	{
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return this.processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Sets the mlc gateway ws.
	 * 
	 * @param mlcGatewayWs the new mlc gateway ws
	 */
	public void setMlcGatewayWs(MlcServerClient mlcGatewayWs)
	{
		this.mlcGatewayWs = mlcGatewayWs;
	}

	/**
	 * Gets the mlc gateway ws.
	 * 
	 * @return the mlc gateway ws
	 */
	public MlcServerClient getMlcGatewayWs()
	{
		return this.mlcGatewayWs;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#deleteGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		// build a list of groups based on the user selection - Select All requirement
		List<Group> groupList = fetchSelectedGroups(groupRequest);
		GroupRequest request = new GroupRequest(groupRequest.getUserContext());
		InternalResponse response = new InternalResponse();

		for (Group group : groupList)
		{
			request.setGroup(group);
			if (!getGroupDAC().fetchCanDelete(request))
			{
				// There are Processes running, return with an error
				response.setStatus(Status.ValidationError);
				response.addMessage(PROCESS_RUNNING, MessageSeverity.Info, MessageLevel.None,
						new Object[] {group.getName()});
				continue;
			}
			groupRequest.setGroup(group);

			response = getGroupDAC().deleteGroup(groupRequest);
			if (response.getStatus().equals(Status.OperationSuccess))
			{
				response.addMessage(GROUP_REMOVED, MessageSeverity.Info, MessageLevel.None,
						new Object[] {group.getName()});

				removeTagFromGroup(groupRequest.getUserContext(), response, group.getName());
			}

			// create a Process for this action group
			InternalResponse groupProcess = insertProcess(groupRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(groupProcess.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#insertGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> response = getGroupDAC().insertGroup(groupRequest);

		if (!response.isInError())
		{
			// create a Process for the created group
			InternalResponse processResponse = insertProcess(groupRequest, LCActionTypeEnum.INSERT_GROUP);
			response.addMessages(processResponse.getMessageInfoList());
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
		InternalResponse response = getGroupDAC().updateGroup(groupRequest);

		if (!response.isInError())
		{
			InternalResponse processResponse = insertProcess(groupRequest, LCActionTypeEnum.UPDATE_GROUP);

			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#deleteSmartPointFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		InquiryLightRequest lightRequest = createInquiryLightRequest(groupRequest);
		Integer lightTotal = getSmartPointAccessorBCL().countLights(lightRequest).getFirstResult();

		Group group = groupRequest.getGroup();
		SearchLight searchLight = groupRequest.getSearchLight();
		SearchParameter groupParameter = new SearchParameter(PropertyEnum.GROUP_ID, String.valueOf(group.getId()));
		searchLight.addSearchParameter(groupParameter);

		Integer lightsBelongGroup = getSmartPointAccessorBCL().countLights(lightRequest).getFirstResult();
		InternalResponse processResponse = insertProcess(
				groupRequest,
				LCActionTypeEnum.DEL_SMP_FROM_GRP,
				lightsBelongGroup,
				new ArrayList<Light>(),
				new ArrayList<Light>(),
				new ArrayList<Light>());

		InternalResponse response = getGroupDAC().deleteSmartPointFromGroup(groupRequest);
		if (response.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		Integer lightsNotBelongGroup = lightTotal - lightsBelongGroup;
		if (lightsNotBelongGroup > 0)
		{
			response.addMessage(LIGHT_NOT_IN_THE_GROUP, MessageSeverity.Info, MessageLevel.None,
					new Object[] {lightsNotBelongGroup});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchAllGroups(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return getGroupDAC().fetchAllGroups(inquiryPaginationRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupById(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupById(GroupRequest groupRequest)
	{
		return getGroupDAC().fetchGroupById(groupRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupsByIdList(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByIdList(GroupRequest groupRequest)
	{
		return getGroupDAC().fetchGroupsByIdList(groupRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#insertSmartPointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToGroup(GroupRequest groupRequest)
	{
		SearchLight searchLight = groupRequest.getSearchLight();

		// Fetch light total by conditions
		InquiryLightRequest lightRequest = createInquiryLightRequest(groupRequest);
		Integer lightsTotal = getSmartPointAccessorBCL().countLights(lightRequest).getFirstResult();

		// Fetch lights that contains the maximum number of groups allowed
		groupRequest.setMaxSmartpointCount(MAX_GROUPS_PER_LIGHT);
		List<Light> lightsWithGroupsMaxAllowed =
				getGroupDAC().fetchLightsWithGroupsMaxAllowed(groupRequest).getResultsList();
		Integer lightsWithGroupsMaxAmount = lightsWithGroupsMaxAllowed.size();
		// Add restriction lights that do not need add on the group
		groupRequest.setUnselectionPaginationIds(extractLightId(lightsWithGroupsMaxAllowed));

		// Fetch lights that already is on the group
		Group group = groupRequest.getGroup();
		SearchParameter groupParameter = new SearchParameter(PropertyEnum.GROUP_ID, String.valueOf(group.getId()));
		searchLight.addSearchParameter(groupParameter);
		List<Light> lightsBelongGroup = getGroupDAC().fetchLightsBelongGroup(groupRequest).getResultsList();
		Integer lightsBelongGroupAmount = lightsBelongGroup.size();
		searchLight.getSearchParameters().remove(groupParameter);
		// Add restriction lights that do not need add on the group
		groupRequest.setUnselectionPaginationIds(extractLightId(lightsBelongGroup));

		// Fetch lights deactivated
		List<LightStatusEnum> statusList = new ArrayList<LightStatusEnum>(searchLight.getStatusList());
		searchLight.setStatusList(Arrays.asList(LightStatusEnum.DEACTIVATED));
		List<Light> lightsDeactivated = getSmartPointAccessorBCL().fetchAllLights(lightRequest).getResultsList();
		Integer lightsDeactivatedAmount = lightsDeactivated.size();
		searchLight.setStatusList(statusList);
		// Add restriction lights that do not need add on the group
		groupRequest.setUnselectionPaginationIds(extractLightId(lightsDeactivated));

		// Evaluates if left some light to add on the group
		InternalResponse response = new InternalResponse();
		if ((lightsTotal - (lightsWithGroupsMaxAmount + lightsBelongGroupAmount + lightsDeactivatedAmount)) > 0)
		{
			// insert anothers lights on the group
			response = getGroupDAC().insertSmartPointToGroup(groupRequest);
			if (response.isInError())
			{
				return response;
			}

			getGroupDAC().updateSmartPointToGroup(groupRequest);
		}

		// Insert process
		InternalResultsResponse<Process> processResponse = insertProcess(
				groupRequest,
				LCActionTypeEnum.ADD_SMP_TO_GRP,
				lightsTotal,
				lightsBelongGroup,
				lightsWithGroupsMaxAllowed,
				lightsDeactivated);

		response.setStatus(processResponse.getStatus());
		response.addMessages(processResponse.getMessageInfoList());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#insertSmartPointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToAutoGroup(GroupRequest groupRequest)
	{
		// build a list of lights based on the user selection
		List<Light> lightList = groupRequest.getGroup().getLights();

		// Loop lightLists and check if the light is at 5 groups already.
		InternalResultsResponse<Group> groupResponse = fetchGroupById(groupRequest);
		String groupName = groupResponse.getFirstResult().getName();

		InternalResultsResponse<HashMap<String, Integer>> autoGroupResponse =
				getGroupDAC().insertSmartPointToAutoGroup(groupRequest);

		int countAlreadyInGroup = 0;

		if (!ValidationUtil.isNull(autoGroupResponse.getFirstResult())
				&& !ValidationUtil.isNull(autoGroupResponse.getFirstResult().get(ALREADY_IN_GROUP)))
		{
			countAlreadyInGroup = autoGroupResponse.getFirstResult().get(ALREADY_IN_GROUP);
		}

		int countMaxPerLight = 0;

		if (!ValidationUtil.isNull(autoGroupResponse.getFirstResult())
				&& !ValidationUtil.isNull(autoGroupResponse.getFirstResult().get(MAX_GROUPS)))
		{
			countMaxPerLight = autoGroupResponse.getFirstResult().get(MAX_GROUPS);
		}

		if (lightList.size() == (countAlreadyInGroup + countMaxPerLight))
		{
			setResponseMessageFailure(groupResponse, groupName, countAlreadyInGroup, countMaxPerLight);
			return groupResponse;
		}

		InternalResponse processResponse =
				insertGroupProcess(groupResponse.getFirstResult(), groupRequest.getUserContext(),
						groupRequest.getTenant(), LCActionTypeEnum.AUTO_GROUP, countAlreadyInGroup,
						countMaxPerLight, lightList);
		groupResponse.addMessages(processResponse.getMessageInfoList());

		return groupResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#updateLightIntensityForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateLightIntensityForGroup(GroupRequest groupRequest)
	{
		// Instatiate a response
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		// build a list of groups based on the user selection - Select All requirement
		List<Group> groupList = fetchSelectedGroups(groupRequest);
		Integer countGroupWithoutLight = 0;

		// Filter by group id
		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.GROUP_ID);

		for (Group group : groupList)
		{
			String groupId = String.valueOf(group.getId());
			param.setValue(groupId);
			SearchLight search = groupRequest.getSearchLight();
			search.getSearchParameters().add(param);

			InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(groupRequest);
			Integer lighsAmount = getSmartPointAccessorBCL().countLights(inquiryLightRequest).getFirstResult();

			if (ValidationUtil.isNullOrZero(lighsAmount))
			{
				countGroupWithoutLight++;
			}

			// create a Process for each group
			List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
			actionParameters.add(new LCActionParameter(PropertyEnum.GROUP_ID, groupId));
			actionParameters.add(new LCActionParameter(PropertyEnum.LIGHT_INTENSITY, String.valueOf(groupRequest
					.getPercentage())));

			LCAction action = new LCAction(LCActionTypeEnum.SET_INTENSITY_BY_GRP);
			action.setActionParameters(actionParameters);

			ProcessRequest processRequest = createProcessRequest(groupRequest, ProcessItemStatusEnum.PENDING);
			InternalResultsResponse<Process> processResponse = getProcessBCL().submitProcess(processRequest, action);

			response.addMessages(processResponse.getMessageInfoList());
			if (processResponse.isInError())
			{
				response.setStatus(Status.ExceptionError);
			}
		}

		if (countGroupWithoutLight > 0)
		{
			response.addMessage(NO_LIGHTS_IN_GROUPS, MessageSeverity.Info, MessageLevel.None,
					new Object[] {countGroupWithoutLight});
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByLight(LightRequest lightRequest)
	{
		return getGroupDAC().fetchGroupsByLight(lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsBySmartPoint(LightRequest lightRequest)
	{
		return getGroupDAC().fetchGroupsBySmartPoint(lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupByName(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupByName(GroupRequest groupRequest)
	{
		return getGroupDAC().fetchGroupByName(groupRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#updateLightProtectedForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse updateLightProtectedForGroup(GroupRequest groupRequest)
	{
		// Instatiate a response
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		// build a list of groups based on the user selection - Select All requirement
		List<Group> groupList = fetchSelectedGroups(groupRequest);
		for (Group group : groupList)
		{
			// getting all lights from this group
			List<Light> lightList = fetchLightsByGroup(groupRequest, group);

			// the process is created using at smartpoint BCL in updateLightProtected.
			LightRequest lightRequest = new LightRequest(groupRequest.getUserContext());
			lightRequest.setLights(lightList);
			lightRequest.setProtect(groupRequest.getLightProtected());
			lightRequest.setTenant(groupRequest.getTenant());
			InternalResultsResponse<Light> internalResultsResponse =
					getSmartPointUpdaterBCL().updateLightProtected(lightRequest);
			response.addMessages(internalResultsResponse.getMessageInfoList());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		return getGroupDAC().fetchCountLightsFromGroups(groupRequest);
	}

	private InternalResultsResponse<Process> insertProcess(GroupRequest groupRequest, LCActionTypeEnum lcActionType)
	{
		return insertProcess(groupRequest,
				lcActionType,
				NumberUtils.INTEGER_ZERO,
				new ArrayList<Light>(),
				new ArrayList<Light>(),
				new ArrayList<Light>());
	}

	/**
	 * Insert process.
	 * 
	 * @param groupRequest the group request
	 * @param lcActionType the lc action type
	 * @param lightAmount the light amount
	 * @param lightsAlreadyInGroup the count already in group
	 * @param lightsWithMaxGroupAllowed the count max per light
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(GroupRequest groupRequest, LCActionTypeEnum lcActionType,
			Integer lightAmount, List<Light> lightsAlreadyInGroup, List<Light> lightsWithMaxGroupAllowed,
			List<Light> deactivatedLights)
	{
		Group group = groupRequest.getGroup();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.GROUP_ID, String.valueOf(group.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.GROUP_NAME, group.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(
				process,
				group.getName(),
				lightAmount,
				lightsAlreadyInGroup.size(),
				lightsWithMaxGroupAllowed.size(),
				deactivatedLights.size(),
				processResponse);

		ProcessRequest processRequest = createProcessRequest(groupRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		processRequest.getProcessItemFailureList().addAll(
				createProcessItemWithFailure(
						lightsAlreadyInGroup,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_BELONG_GROUP));

		processRequest.getProcessItemFailureList().addAll(
				createProcessItemWithFailure(
						lightsWithMaxGroupAllowed,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_WITH_MAX_GROUP_ALLOWED));

		InternalResultsResponse<Process> internalResponse = new InternalResultsResponse<Process>();
		internalResponse = getProcessBCL().insertProcess(processRequest);
		internalResponse.setStatus(processResponse.getStatus());
		internalResponse.addMessages(processResponse.getMessageInfoList());
		return internalResponse;
	}

	/**
	 * Removes the tag from group.
	 * 
	 * @param userContext the user context
	 * @param response the response
	 * @param groupName the group name
	 */
	private void removeTagFromGroup(UserContext userContext, InternalResponse response, String groupName)
	{
		TagRequest tagRequest = new TagRequest(userContext);
		tagRequest.setTag(new Tag(groupName));
		InternalResultsResponse<Tag> tagResponse = getTagBCL().fetchTagByName(tagRequest);
		if (!ValidationUtil.isNull(tagResponse.getFirstResult())
				&& tagResponse.getFirstResult().isAutoGroup())
		{
			tagRequest.setTag(tagResponse.getFirstResult());
			tagRequest.getTag().setAutoGroup(false);
			tagResponse = getTagBCL().updateAutoGroupTag(tagRequest);

			if (tagResponse.getStatus().equals(Status.OperationSuccess))
			{
				response.addMessage(AUTO_GROUP_REMOVED, MessageSeverity.Info, MessageLevel.None,
						new Object[] {tagRequest.getTag().getName()});
			}
		}
	}

	/**
	 * Fetch selected groups. return a list of groups based on the user selection - Select All requirement
	 * 
	 * @param groupRequest the group request
	 * @return the list
	 */
	private List<Group> fetchSelectedGroups(GroupRequest groupRequest)
	{
		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest(groupRequest.getUserContext());
		inquiryPaginationRequest.setPageSize(0);
		inquiryPaginationRequest.setSelectionPaginationIds(groupRequest.getSelectionPaginationIds());
		inquiryPaginationRequest.setPaginationAllSelected(groupRequest.getPaginationAllSelected());
		inquiryPaginationRequest.setTenant(groupRequest.getTenant());

		return getGroupDAC().fetchAllGroups(inquiryPaginationRequest).getResultsList();
	}

	/**
	 * Insert group process.
	 * 
	 * @param group the group
	 * @param userContext the user context
	 * @param tenant the tenant
	 * @param actionGroup the action group
	 * @param countAlreadyInGroup the count already in group (in order to give a specific error message according
	 *            Business Requirement)
	 * @param countMaxPerLight the count max per light (in order to give a specific error message according Business
	 *            Requirement)
	 * @param lightList the light list
	 * @return the internal response
	 */
	private InternalResponse insertGroupProcess(Group group, UserContext userContext, Tenant tenant,
			LCActionTypeEnum actionGroup, Integer countAlreadyInGroup, Integer countMaxPerLight, List<Light> lightList)
	{
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		LCActionParameter actionParameterGroupId =
				new LCActionParameter(PropertyEnum.GROUP_ID, String.valueOf(group.getId()));
		LCActionParameter actionParameterGroupName =
				new LCActionParameter(PropertyEnum.GROUP_NAME, group.getName());
		LCAction action = new LCAction(actionGroup);

		actionParameters.add(actionParameterGroupId);
		actionParameters.add(actionParameterGroupName);
		action.setActionParameters(actionParameters);

		Process process = getLcHelp().generateProcess(null, action, lightList);
		process.setIsProcessComplete(true);
		process.setIsMonitoredInstance(false);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		ProcessRequest processRequest = new ProcessRequest(userContext);
		processRequest.setProcess(process);
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.SUCCESS);

		Integer lightAmount = 0;
		if (!ValidationUtil.isNullOrEmpty(lightList))
		{
			lightAmount = lightList.size();
		}

		setProcessDescription(process, group.getName(), lightAmount, countAlreadyInGroup, countMaxPerLight);
		return getProcessBCL().insertProcess(processRequest);
	}

	/**
	 * Sets the process description.
	 * 
	 * @param process the process
	 * @param groupName the group name
	 * @param lightAmount the light amount
	 * @param countAlreadyInGroup the count already in group
	 * @param countMaxPerLight the count max per light
	 */
	private void setProcessDescription(
			Process process,
			String groupName,
			Integer lightAmount,
			int countAlreadyInGroup,
			int countMaxPerLight)
	{
		setProcessDescription(
				process,
				groupName,
				lightAmount,
				countAlreadyInGroup,
				countMaxPerLight,
				NumberUtils.INTEGER_ZERO,
				new InternalResultsResponse<Process>());
	}

	/**
	 * Sets the process description.
	 * 
	 * @param process the process
	 * @param groupName the group name
	 * @param lightAmount the light amount
	 * @param countAlreadyInGroup the count already in group
	 * @param countMaxPerLight the count max per light
	 * @param processResponse the process response
	 */
	private void setProcessDescription(
			Process process,
			String groupName,
			Integer lightAmount,
			int countAlreadyInGroup,
			int countMaxPerLight,
			int countDeactivated,
			InternalResultsResponse<Process> processResponse)
	{

		if (ValidationUtil.isNull(process))
		{
			return;
		}

		if (process.getLcAction().getActionType() != LCActionTypeEnum.ADD_SMP_TO_GRP)
		{
			process.setDescription(generateDescription(process, lightAmount));
			return;
		}

		// create process description based on the max count per light
		if (lightAmount > 1)
		{
			process.setDescription(
					createMessageWarningOther(
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP,
							lightAmount,
							groupName).getText());
		}

		if ((lightAmount == 1) && (countAlreadyInGroup == 0) && (countMaxPerLight == 0) && (countDeactivated == 0))
		{
			process.setDescription(
					createMessageWarningOther(
							DESCRIPTION_LABEL_KEY_ADD_SMP_TO_GROUP_BY_POLE_ID,
							groupName).getText());
		}

		if (countAlreadyInGroup > 0)
		{
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_GROUP, groupName);
			process.setDescription(process.getDescription() + " - [" + countAlreadyInGroup + "] " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_ALREADY_IN_GROUP, groupName);
		}

		if (countMaxPerLight > 0)
		{
			Message message = createMessageInfoNone(MAX_GROUPS_FAILURE, countMaxPerLight);
			process.setDescription(process.getDescription() + " - " + message.getText());
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, MAX_GROUPS_FAILURE, countMaxPerLight);
		}

		if (countDeactivated > 0)
		{
			addMessageInfoIntoProcessResponse(lightAmount, processResponse, LIGHT_DEACTIVATED);
		}
	}

	/**
	 * Adds the message info into process response.
	 * 
	 * @param lightAmount the light amount
	 * @param processResponse the process response
	 * @param message the message
	 * @param args the args
	 */
	public void addMessageInfoIntoProcessResponse(
			Integer lightAmount,
			InternalResultsResponse<Process> processResponse,
			String message,
			Object... args)
	{
		if (ValidationUtil.isNullOrZero(lightAmount) || (lightAmount > 1))
		{
			return;
		}

		processResponse.addMessage(message, MessageSeverity.None, MessageLevel.None, args);
		processResponse.setStatus(Status.ExceptionError);
	}

	/**
	 * Fetch lights by group. build a light list that belongs a group
	 * 
	 * @param groupRequest the group request
	 * @param group the group
	 * @return the list
	 */
	private List<Light> fetchLightsByGroup(GroupRequest groupRequest, Group group)
	{
		InquiryLightRequest inquiryLightRequest = new InquiryLightRequest(groupRequest.getUserContext());
		inquiryLightRequest.setSortExpression(new MLCSortExpression());
		inquiryLightRequest.getMlcSortExpression().setField(LightOrderByEnum.LIGHT_ID.getValue());
		inquiryLightRequest.getMlcSortExpression().setIsProperty(false);

		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.GROUP_ID);
		param.setValue(group.getId().toString());

		SearchLight search = new SearchLight();
		search.getSearchParameters().add(param);

		inquiryLightRequest.setSearch(search);
		inquiryLightRequest.setPageSize(0);

		return getSmartPointAccessorBCL().fetchAllLights(inquiryLightRequest).getResultsList();
	}

	/**
	 * Sets the response message failure.
	 * 
	 * @param response the response
	 * @param groupName the group name
	 * @param countAlreadyInGroup the count already in group
	 * @param countMaxPerLight the count max per light
	 */
	private void setResponseMessageFailure(InternalResponse response, String groupName, int countAlreadyInGroup,
			int countMaxPerLight)
	{
		if (countMaxPerLight > 0)
		{
			response.addMessage(MAX_GROUPS_FAILURE, MessageSeverity.Info, MessageLevel.None,
					new Object[] {countMaxPerLight});
		}
		if (countAlreadyInGroup > 0)
		{
			response.addMessage(LIGHT_ALREADY_IN_GROUP, MessageSeverity.Info, MessageLevel.None,
					new Object[] {groupName});
		}
		response.setStatus(Status.ExceptionError);
	}
}
