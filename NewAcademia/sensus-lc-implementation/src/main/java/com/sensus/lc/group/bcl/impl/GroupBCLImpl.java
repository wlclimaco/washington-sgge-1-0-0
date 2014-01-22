package com.sensus.lc.group.bcl.impl;

import static com.sensus.lc.base.util.LCActionUtil.generateDescription;
import static com.sensus.lc.base.util.LCHelp.createProcessRequest;
import static com.sensus.lc.process.util.ProcessUtil.createProcessItemWithFailure;
import static com.sensus.lc.process.util.ProcessUtil.generateProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.bcl.IAnalyticsBCL;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.group.bcl.IGroupBCL;
import com.sensus.lc.group.dac.IGroupDAC;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.util.SearchTranslation;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.ProcessStatusReasonEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.util.ProcessUtil;
import com.sensus.lc.server.client.MlcServerClient;
import com.sensus.lc.tag.bcl.ITagBCL;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.TagRequest;

/**
 * The Class GroupBCLImpl.
 * 
 * @author Gustavo Aragao - QAT.
 * 
 */
public class GroupBCLImpl implements IGroupBCL
{

	/** The Constant ALL_GROUPS_REMOVED. */
	private static final String ALL_GROUPS_REMOVED = "sensus.mlc.groupbcfimpl.groups.deleted";

	/** The lc help. */
	private LCHelp lcHelp; // injected by Spring through setter

	/** The group dac. */
	private IGroupDAC groupDAC; // injected by Spring through setter

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The light bcl. */
	private ILightBCL lightBCL; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The analytics bcl. */
	private IAnalyticsBCL analyticsBCL; // injected by Spring through setter

	/** The Constant MAX_GROUPS_PER_LIGHT. */
	private static final Integer MAX_GROUPS_PER_LIGHT = 5;

	/** The Constant AUTO_GROUP_REMOVED. */
	private static final String AUTO_GROUP_REMOVED = "sensus.mlc.tagvalidator.autogroup.removed";

	/** The Constant GROUP_REMOVED. */
	private static final String GROUP_REMOVED = "sensus.mlc.groupbcfimpl.mlcgroupdeleted";

	/** The Constant PROCESS_RUNNING. */
	private static final String PROCESS_RUNNING = "sensus.mlc.groupbcfimpl.processrunning";

	/** The Constant NO_LIGHTS_IN_GROUPS. */
	private static final String NO_LIGHTS_IN_GROUPS = "sensus.mlc.mlc_action.light_status.no.lights_in_groups";

	/** The mlc gateway ws. */
	private MlcServerClient mlcGatewayWs;

	/**
	 * Gets the lc help.
	 * 
	 * @return the lc help
	 */
	public LCHelp getLcHelp()
	{
		return lcHelp;
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
		return tagBCL;
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
		groupDAC = groupDACParam;
	}

	/**
	 * Gets the group dac.
	 * 
	 * @return the group dac
	 */
	public IGroupDAC getGroupDAC()
	{
		return groupDAC;
	}

	/**
	 * Gets the light bcl.
	 * 
	 * @return the light bcl
	 */
	public ILightBCL getLightBCL()
	{
		return lightBCL;
	}

	/**
	 * Sets the light bcl.
	 * 
	 * @param lightBCL the new light bcl
	 */
	@Resource
	public void setLightBCL(ILightBCL lightBCL)
	{
		this.lightBCL = lightBCL;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
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
		return mlcGatewayWs;
	}

	/**
	 * Gets the analytics bcl.
	 * 
	 * @return the analytics bcl
	 */
	public IAnalyticsBCL getAnalyticsBCL()
	{
		return analyticsBCL;
	}

	/**
	 * Sets the analytics bcl.
	 * 
	 * @param analyticsBCL the new analytics bcl
	 */
	public void setAnalyticsBCL(IAnalyticsBCL analyticsBCL)
	{
		this.analyticsBCL = analyticsBCL;
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

		Boolean deletedSuccess = true;

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
			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				deletedSuccess = false;
			}
			removeTagFromGroup(groupRequest.getUserContext(), response, group.getName());

			// create a Process for this action group
			InternalResponse groupProcess = insertProcess(groupRequest, LCActionTypeEnum.DEL_GRP);
			response.addMessages(groupProcess.getMessageInfoList());
		}

		if (deletedSuccess && groupList.size() == 1)
		{
			response.addMessage(GROUP_REMOVED, MessageSeverity.Info, MessageLevel.None,
					new Object[] {groupList.get(0).getName()});
			return response;
		}
		if (deletedSuccess)
		{
			response.addMessage(ALL_GROUPS_REMOVED, MessageSeverity.Info, MessageLevel.None);
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

			// insert default values to analytics summarized tables.
			AnalyticsRequest analyticsRequest = new AnalyticsRequest();
			analyticsRequest.setGroup(new AnalyticsGroup(response.getFirstResult().getId()));
			analyticsRequest.setUserContext(groupRequest.getUserContext());

			InternalResponse internalResponse = getAnalyticsBCL().insertAnalyticsSummarized(analyticsRequest);

			if (internalResponse.isInError())
			{
				response.addMessages(response.getMessageInfoList());
			}
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
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#deleteLightFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteLightFromGroup(GroupRequest groupRequest)
	{
		UserContext userContext = groupRequest.getUserContext();

		if (!ValidationUtil.isNull(groupRequest.getLightRequest()))
		{
			groupRequest.getLightRequest().setUserContext(userContext);
		}

		InternalResultsResponse<Light> response = getLightBCL().fetchAllByRequest(groupRequest.getLightRequest());

		LCAction action = new LCAction(LCActionTypeEnum.DEL_LIGHT_FROM_GRP);
		action.addActionParameter(new LCActionParameter(PropertyEnum.GROUP_ID));
		action.addActionParameter(new LCActionParameter(PropertyEnum.GROUP_NAME, groupRequest.getGroup().getName()));
		Process process = ProcessUtil.generateProcess(false, action, response.getResultsList().size());

		// Verify if exist some light to remove
		if (response.isInError() || !response.hasResults())
		{
			insertProcess(userContext, response, process);
			return response;
		}

		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		InternalResponse internalResponse = new InternalResponse();
		for (Light light : response.getResultsList())
		{
			groupRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
			InternalResponse deleteResponse = getGroupDAC().deleteLightFromGroup(groupRequest);

			if (deleteResponse.isInError())
			{
				internalResponse.setStatus(deleteResponse.getStatus());
				internalResponse.addMessages(deleteResponse.getMessageInfoList());
			}

			ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.SUCCESS);
			processItem.setLight(light);
			processItems.add(processItem);
		}

		// Insert process to lights reseted
		process.setProcessItems(processItems);
		process.setIsProcessComplete(true);
		insertProcess(userContext, internalResponse, process);
		return internalResponse;
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
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#insertLightToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse insertLightToGroup(GroupRequest groupRequest)
	{
		UserContext userContext = groupRequest.getUserContext();

		if (!ValidationUtil.isNull(groupRequest.getLightRequest()))
		{
			groupRequest.getLightRequest().setUserContext(userContext);
		}

		InternalResultsResponse<Light> response = getLightBCL().fetchAllByRequest(groupRequest.getLightRequest());

		LCAction action = new LCAction(LCActionTypeEnum.ADD_LIGHT_TO_GRP);
		action.addActionParameter(new LCActionParameter(PropertyEnum.GROUP_ID));
		action.addActionParameter(new LCActionParameter(PropertyEnum.GROUP_NAME, groupRequest.getGroup().getName()));
		Process process = ProcessUtil.generateProcess(false, action, response.getResultsList().size());

		// Verify if exist some light.
		if (response.isInError() || !response.hasResults())
		{
			insertProcess(userContext, response, process);
			return response;
		}

		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		InternalResponse internalResponse = new InternalResponse();

		for (Light light : response.getResultsList())
		{
			// Verify if the light is Deactivated.
			if (LifeCycleStateEnum.DEACTIVATED.equals(light.getLifeCycleState()))
			{

				ProcessItem processItem =
						new ProcessItem(light, ProcessItemStatusEnum.MLCFAILURE,
								ProcessStatusReasonEnum.LIGHT_DEACTIVATED);
				processItem.setLight(light);
				processItems.add(processItem);
				internalResponse.setStatus(Status.OperationSuccess);
				continue;
			}

			// Verify if the light contain the maximum number of groups allowed
			groupRequest.setMaxLightCount(MAX_GROUPS_PER_LIGHT);
			groupRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
			List<Light> lightsWithGroupsMaxAllowed =
					getGroupDAC().fetchLightsWithGroupsMaxAllowed(groupRequest).getResultsList();

			if (!ValidationUtil.isNullOrEmpty(lightsWithGroupsMaxAllowed))
			{
				ProcessItem processItem =
						new ProcessItem(light, ProcessItemStatusEnum.MLCFAILURE,
								ProcessStatusReasonEnum.LIGHT_WITH_MAX_GROUP_ALLOWED);
				processItem.setLight(light);
				processItems.add(processItem);
				internalResponse.setStatus(Status.OperationSuccess);
				continue;
			}

			// Fetch lights that already is on the group
			List<Light> lightsBelongGroup = getGroupDAC().fetchLightsBelongGroup(groupRequest).getResultsList();

			if (!ValidationUtil.isNullOrZero(lightsBelongGroup.size()))
			{

				ProcessItem processItem =
						new ProcessItem(light, ProcessItemStatusEnum.MLCFAILURE,
								ProcessStatusReasonEnum.LIGHT_BELONG_GROUP);
				processItem.setLight(light);
				processItems.add(processItem);
				internalResponse.setStatus(Status.OperationSuccess);
				continue;
			}

			InternalResponse insertResponse = getGroupDAC().insertLightToGroup(groupRequest);
			internalResponse.setStatus(insertResponse.getStatus());
			internalResponse.addMessages(insertResponse.getMessageInfoList());

			getGroupDAC().updateLightToGroup(groupRequest);

			ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.SUCCESS);
			processItem.setLight(light);
			processItems.add(processItem);

		}

		// Insert process to lights reseted
		process.setProcessItems(processItems);
		process.setIsProcessComplete(true);
		insertProcess(userContext, internalResponse, process);
		return internalResponse;
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

		for (Group group : groupList)
		{
			LightRequest lightRequest = SearchTranslation.setLightRequestCriterias(groupRequest);
			Integer lighsAmount = getLightBCL().countAllByRequest(lightRequest).getFirstResult();

			if (ValidationUtil.isNullOrZero(lighsAmount))
			{
				countGroupWithoutLight++;
			}

			// create a Process for each group
			List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();

			actionParameters.add(new LCActionParameter(PropertyEnum.GROUP_ID, String.valueOf(group.getId())));
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
	 * @see com.sensus.mlc.group.bcl.IGroupBCL#fetchGroupsByLight(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByLight(LightRequest lightRequest)
	{
		return getGroupDAC().fetchGroupsByLight(lightRequest);
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
			List<Light> lightList = fetchLightsByGroup(groupRequest);
			LightMaintenanceRequest lightMaintenanceRequest = null;

			for (Light light : lightList)
			{
				lightMaintenanceRequest = new LightMaintenanceRequest(light);
				InternalResponse updateResponse = getLightBCL().updateLightBase(lightMaintenanceRequest);
				response.addMessages(updateResponse.getMessageInfoList());
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcl.IGroupBCL#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		return getGroupDAC().fetchCountLightsFromGroups(groupRequest);
	}

	/**
	 * Insert process.
	 * 
	 * @param groupRequest the group request
	 * @param lcActionType the lc action type
	 * @return the internal results response
	 */
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
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(GroupRequest groupRequest, LCActionTypeEnum lcActionType,
			Integer lightAmount, List<Light> lightsAlreadyInGroup, List<Light> lightsWithMaxGroupAllowed,
			List<Light> deactivatedLights)
	{
		Group group = groupRequest.getGroup();
		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(com.sensus.lc.light.model.PropertyEnum.GROUP_ID, String
				.valueOf(group.getId())));
		actionParameters
				.add(new LCActionParameter(com.sensus.lc.light.model.PropertyEnum.GROUP_NAME, group.getName()));

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());
		InternalResultsResponse<Process> processResponse = new InternalResultsResponse<Process>();
		setProcessDescription(
				process,
				lightAmount);
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
		inquiryPaginationRequest.getUserContext().setTenant(groupRequest.getTenant());

		return getGroupDAC().fetchAllGroups(inquiryPaginationRequest).getResultsList();
	}

	/**
	 * Sets the process description.
	 * 
	 * @param process the process
	 * @param lightAmount the light amount
	 */
	private void setProcessDescription(
			Process process,
			Integer lightAmount)
	{

		if (ValidationUtil.isNull(process))
		{
			return;
		}

		process.setDescription(generateDescription(process, lightAmount));
		return;

	}

	/**
	 * Fetch lights by group. build a light list that belongs a group
	 * 
	 * @param groupRequest the group request
	 * @param group the group
	 * @return the list
	 */
	private List<Light> fetchLightsByGroup(GroupRequest groupRequest)
	{
		return getLightBCL().fetchAllByRequest(groupRequest.getLightRequest()).getResultsList();
	}

	/**
	 * Insert process.
	 * 
	 * @param userContext the user context
	 * @param response the response
	 * @param process the process
	 */
	private void insertProcess(UserContext userContext, InternalResponse response, Process process)
	{
		ProcessRequest processRequest = new ProcessRequest(userContext);
		processRequest.setProcess(process);
		InternalResponse processResponse = getProcessBCL().insertProcess(processRequest);
		if (processResponse.isInError())
		{
			response.addMessages(processResponse.getMessageInfoList());
			response.setStatus(processResponse.getStatus());
		}
	}
}
