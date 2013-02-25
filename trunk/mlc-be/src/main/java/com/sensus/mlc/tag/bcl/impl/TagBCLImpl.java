package com.sensus.mlc.tag.bcl.impl;

import static com.sensus.mlc.base.util.LCActionUtil.createMessageInfoNone;
import static com.sensus.mlc.base.util.LCActionUtil.createMessageWarningOther;
import static com.sensus.mlc.base.util.LCHelp.createInquiryLightRequest;
import static com.sensus.mlc.base.util.LCHelp.createProcessItemWithFailure;
import static com.sensus.mlc.base.util.LCHelp.createProcessRequest;
import static com.sensus.mlc.base.util.LCPropertiesExtractorUtil.extractLightId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.base.util.LCHelp;
import com.sensus.mlc.group.bcl.IGroupBCL;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.LCAction;
import com.sensus.mlc.process.model.LCActionParameter;
import com.sensus.mlc.process.model.LCActionTypeEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.ProcessItemStatusEnum;
import com.sensus.mlc.process.model.ProcessStatusReasonEnum;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tag.bcl.ITagBCL;
import com.sensus.mlc.tag.dac.ITagDAC;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;

/**
 * The Class TagBCLImpl.
 */
public class TagBCLImpl implements ITagBCL
{

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = " - ";

	/** The tag dac. */
	private ITagDAC tagDAC; // injected by Spring through setter

	/** The group bcl. */
	private IGroupBCL groupBCL; // injected by Application Context Spring through setter

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL; // injected by Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The lc help. */
	private LCHelp lcHelp;

	/** The Constant LIGH_ALREADY_IN_THE_GROUP. */
	private static final String LIGHT_ALREADY_IN_TAG = "sensus.mlc.tagvalidator.light.already.exist";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG = "sensus.mlc.mlc_action.add_smp_to_tag";

	/** The Constant DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG_BY_POLE_ID. */
	private static final String DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG_BY_POLE_ID =
			"sensus.mlc.mlc_action.add_smp_to_tag_by_poleid";

	/** The Constant SMARTPOINT_BCL_BEAN. */
	private static final String SMARTPOINT_ACCESSOR_BCL_BEAN = "smartPointAccessorBCLTarget";

	/** The Constant GROUP_BCL_BEAN. */
	private static final String GROUP_BCL_BEAN = "groupBCLTarget";

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
	 * Spring Sets the tag dac.
	 *
	 * @param iTagDAC the new tag dac
	 */
	public void setTagDAC(ITagDAC iTagDAC)
	{
		tagDAC = iTagDAC;
	}

	/**
	 * Gets the tag dac.
	 *
	 * @return the tag dac
	 */
	public ITagDAC getTagDAC()
	{
		return tagDAC;
	}

	/**
	 * Gets the smart point accessor bcl.
	 *
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		if (smartPointAccessorBCL == null)
		{
			smartPointAccessorBCL =
					(ISmartPointAccessorBCL)SensusAppContext.getApplicationContext().getBean(
							SMARTPOINT_ACCESSOR_BCL_BEAN);
		}

		return smartPointAccessorBCL;
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
	 * Gets the group bcl.
	 *
	 * @return the group bcl
	 */
	public IGroupBCL getGroupBCL()
	{
		if (groupBCL == null)
		{
			groupBCL = (IGroupBCL)SensusAppContext.getApplicationContext().getBean(GROUP_BCL_BEAN);
		}

		return groupBCL;
	}

	/**
	 * Sets the group bcl.
	 *
	 * @param groupBCL the new group bcl
	 */
	public void setGroupBCL(IGroupBCL groupBCL)
	{
		this.groupBCL = groupBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchAllTags(InquiryTagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		return getTagDAC().fetchAllTags(inquiryTagRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagByName(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagByName(TagRequest tagRequest)
	{
		return getTagDAC().fetchTagByName(tagRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagById(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagById(TagRequest tagRequest)
	{
		return getTagDAC().fetchTagById(tagRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#insertSmartPointToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse insertSmartPointToTag(TagRequest tagRequest)
	{
		InternalResponse groupResponse = null;
		InternalResponse tagResponse = new InternalResponse();
		List<Tag> tags = tagRequest.getTags();
		SearchLight searchLight = tagRequest.getSearchLight();
		List<Integer> unselectionPaginationIds = new ArrayList<Integer>();

		for (Tag tag : tags)
		{
			// Fetch tag data
			tagRequest.setTag(tag);
			InternalResultsResponse<Tag> response = fetchTagToInsertSmartPoint(tagRequest);
			if (response.isInError())
			{
				return response;
			}

			tag = response.getFirstResult();
			tagRequest.setTag(tag);

			// Apply auto group
			groupResponse = insertTagAutoGroup(tagRequest);

			// Fetch total lights by parameters
			InquiryLightRequest lightRequest = createInquiryLightRequest(tagRequest);
			Integer lightsTotal = getSmartPointAccessorBCL().countLights(lightRequest).getFirstResult();

			// Fetch lights deactivated
			List<LightStatusEnum> statusList = new ArrayList<LightStatusEnum>(searchLight.getStatusList());
			searchLight.setStatusList(Arrays.asList(LightStatusEnum.DEACTIVATED));
			List<Light> lightsDeactivated =
					getSmartPointAccessorBCL().fetchAllLights(lightRequest).getResultsList();
			Integer lightsDeactivatedAmount = lightsDeactivated.size();
			searchLight.setStatusList(statusList);

			// Fetch lights belong tag
			SearchParameter tagParameter = new SearchParameter(PropertyEnum.TAG_ID, String.valueOf(tag.getId()));
			searchLight.addSearchParameter(tagParameter);
			List<Light> lightsBelongTag = getTagDAC().fetchLightsBelongTag(tagRequest).getResultsList();
			Integer lightsBelongTagAmount = lightsBelongTag.size();

			// This is necessary to do not disturb fetch on the moment of insertion
			searchLight.getSearchParameters().remove(tagParameter);

			// Evaluates if all lights that already is on the tag
			if ((lightsTotal - lightsBelongTagAmount) == 0)
			{
				tagResponse.addMessage(
						LIGHT_ALREADY_IN_TAG,
						MessageSeverity.Info,
						MessageLevel.None,
						new Object[] {lightsTotal, tag.getName()});
				tagResponse.setStatus(Status.ExceptionError);

				continue;
			}

			// Remove invalid lights by tag
			tagRequest.setUnselectionPaginationIds(extractLightId(lightsBelongTag));
			tagRequest.setUnselectionPaginationIds(extractLightId(lightsDeactivated));

			// Remove invalid lights to all tags
			unselectionPaginationIds.addAll(tagRequest.getUnselectionPaginationIds());

			// insert the process
			String processDescription = createProcessDescription(
					tagRequest,
					groupResponse,
					lightsTotal,
					lightsBelongTagAmount,
					lightsDeactivatedAmount);

			InternalResultsResponse<Process> processResponse = this.insertProcess(
					tagRequest,
					LCActionTypeEnum.ADD_SMP_TO_TAG,
					processDescription,
					lightsDeactivated);

			tagResponse.addMessages(processResponse.getMessageInfoList());

			// Reset request data
			tagRequest.getUnselectionPaginationIds().clear();
		}

		if (tagResponse.isInError() || ValidationUtil.isNullOrEmpty(tags))
		{
			return tagResponse;
		}

		tagRequest.setUnselectionPaginationIds(unselectionPaginationIds);
		return getTagDAC().insertSmartPointToTag(tagRequest);
	}

	/**
	 * Creates the process description.
	 *
	 * @param tagRequest the tag request
	 * @param groupResponse the group response
	 * @param lightsTotal the lights total
	 * @param lightsBelongTagAmount the lights belong tag amount
	 * @param lightsDeactivatedAmount the lights deactivated amount
	 * @return the string
	 */
	private String createProcessDescription(
			TagRequest tagRequest,
			InternalResponse groupResponse,
			Integer lightsTotal,
			Integer lightsBelongTagAmount,
			Integer lightsDeactivatedAmount)
	{
		String processDescription = "";
		String separator = "";
		String tagName = tagRequest.getTag().getName();

		if (lightsTotal > 1)
		{
			Message message = createMessageWarningOther(DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG, lightsTotal, tagName);
			processDescription += message.getText();
			separator = SEPARATOR;
		}

		if (lightsTotal == 1)
		{
			Message message = createMessageWarningOther(DESCRIPTION_LABEL_KEY_ADD_SMP_TO_TAG_BY_POLE_ID, tagName);
			processDescription = message.getText();
			separator = SEPARATOR;
		}

		if (lightsBelongTagAmount > 0)
		{
			Message message = createMessageInfoNone(LIGHT_ALREADY_IN_TAG, lightsBelongTagAmount, tagName);
			processDescription += separator + message.getText();
		}

		if (!ValidationUtil.isNull(groupResponse))
		{
			StringBuilder messages = new StringBuilder("");
			for (MessageInfo messageInfo : groupResponse.getMessageInfoList())
			{
				Message message = createMessageInfoNone(messageInfo.getCode(), messageInfo.getArguments());
				messages.append(separator).append(message.getText());
			}

			processDescription += messages;
		}

		return processDescription;
	}

	/**
	 * Insert tag auto group.
	 *
	 * @param tagRequest the tag request
	 * @return the internal response
	 */
	private InternalResponse insertTagAutoGroup(TagRequest tagRequest)
	{
		InternalResponse groupResponse = new InternalResponse();
		if (ValidationUtil.isNull(tagRequest.getTag()) || !tagRequest.getTag().isAutoGroup())
		{
			return groupResponse;
		}

		InternalResultsResponse<Group> groupResultsResponse = fetchGroup(tagRequest);
		if (groupResultsResponse.isInError())
		{
			groupResponse.setStatus(groupResultsResponse.getStatus());
			groupResponse.addMessages(groupResultsResponse.getMessageInfoList());
			return groupResponse;
		}

		GroupRequest groupRequest = prepareGroupRequest(tagRequest, groupResultsResponse.getFirstResult());
		return getGroupBCL().insertSmartPointToGroup(groupRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#insertTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{

		InternalResultsResponse<Tag> response = getTagDAC().insertTag(tagRequest);

		if (!response.isInError())
		{
			Tag tag = response.getFirstResult();
			tagRequest.setTag(tag);

			SearchParameter tagParameter = new SearchParameter(PropertyEnum.TAG_ID, String.valueOf(tag.getId()));
			tagRequest.getSearchLight().addSearchParameter(tagParameter);
			InternalResultsResponse<Process> processResponse =
					this.insertProcess(tagRequest, LCActionTypeEnum.INSERT_TAG, null);
			tagRequest.getSearchLight().getSearchParameters().remove(tagParameter);

			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsBySmartPoint(LightRequest lightRequest)
	{
		return getTagDAC().fetchTagsBySmartPoint(lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchTagsByLight(LightRequest lightRequest)
	{
		return getTagDAC().fetchTagsByLight(lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#deleteTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteTag(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> tagResponse = getTagDAC().fetchTagById(tagRequest);
		InternalResponse response = new InternalResponse();

		if (tagResponse.isInError())
		{
			response.setStatus(tagResponse.getStatus());
			response.addMessages(tagResponse.getMessageInfoList());
			return response;
		}

		response = getTagDAC().deleteTag(tagRequest);

		if (response.isInError())
		{
			return response;
		}

		Tag tag = tagResponse.getFirstResult();
		tagRequest.setTag(tag);

		SearchParameter tagParameter = new SearchParameter(PropertyEnum.TAG_ID, String.valueOf(tag.getId()));
		tagRequest.getSearchLight().addSearchParameter(tagParameter);

		InternalResultsResponse<Process> processResponse =
				this.insertProcess(tagRequest, LCActionTypeEnum.DELETE_TAG, null);
		tagRequest.getSearchLight().getSearchParameters().remove(tagParameter);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#updateAutoGroupTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> updateAutoGroupTag(TagRequest tagRequest)
	{
		Tag tag = tagRequest.getTag();
		InternalResponse groupResponse = new InternalResponse();
		if (tag.isAutoGroup() && tagRequest.getIncludeSmartPointsToGroup())
		{
			Boolean autoGroup = tag.isAutoGroup();
			tag = getTagDAC().fetchTagById(tagRequest).getFirstResult();
			tag.setAutoGroup(autoGroup);
			tagRequest.setTag(tag);

			Group group = new Group();
			group.setName(tag.getName());
			GroupRequest groupRequest = new GroupRequest(tagRequest.getUserContext());
			groupRequest.setGroup(group);
			group = getGroupBCL().fetchGroupByName(groupRequest).getFirstResult();
			groupRequest.setGroup(group);

			InquiryLightRequest inquiryLightRequest = new InquiryLightRequest(tagRequest.getUserContext());
			inquiryLightRequest.setSortExpression(new MLCSortExpression());
			inquiryLightRequest.getMlcSortExpression().setField(LightOrderByEnum.LIGHT_ID.getValue());
			inquiryLightRequest.getMlcSortExpression().setIsProperty(false);

			SearchParameter param = new SearchParameter();
			param.setPropertyEnum(PropertyEnum.TAG_ID);
			param.setValue(String.valueOf(tag.getId()));

			SearchLight searchLight = new SearchLight();
			searchLight.getSearchParameters().add(param);

			inquiryLightRequest.setSearch(searchLight);
			group.setLights(getSmartPointAccessorBCL().fetchAllLights(inquiryLightRequest).getResultsList());
			groupRequest.setTagId(tag.getId());
			groupResponse = getGroupBCL().insertSmartPointToAutoGroup(groupRequest);
		}

		InternalResultsResponse<Tag> internalResultsResponse = getTagDAC().updateAutoGroupTag(tagRequest);
		internalResultsResponse.addMessages(groupResponse.getMessageInfoList());
		return internalResultsResponse;
	}

	/**
	 * Prepare group request.
	 *
	 * @param tagRequest the tag request
	 * @param group the group
	 * @return the group request
	 */
	private GroupRequest prepareGroupRequest(TagRequest tagRequest, Group group)
	{
		GroupRequest groupRequest = new GroupRequest(tagRequest.getUserContext());
		groupRequest.setGroup(group);
		groupRequest.setSearchLight(tagRequest.getSearchLight());
		groupRequest.setSelectionPaginationIds(tagRequest.getSelectionPaginationIds());
		groupRequest.setPaginationAllSelected(tagRequest.getPaginationAllSelected());

		return groupRequest;
	}

	/**
	 * Fetch tag to insert smart point.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	private InternalResultsResponse<Tag> fetchTagToInsertSmartPoint(TagRequest tagRequest)
	{
		// Case tag is new then insert it
		if (!ValidationUtil.isNull(tagRequest.getTag()) && ValidationUtil.isNull(tagRequest.getTag().getId()))
		{
			return insertTag(tagRequest);
		}

		return getTagDAC().fetchTagNameById(tagRequest);
	}

	/**
	 * Fetch group.
	 *
	 * @param tagRequest the tag request
	 * @return the internal results response
	 */
	private InternalResultsResponse<Group> fetchGroup(TagRequest tagRequest)
	{
		Tag tag = tagRequest.getTag();
		Group group = new Group();
		group.setName(tag.getName());

		GroupRequest groupRequest = new GroupRequest(tagRequest.getUserContext());
		groupRequest.setGroup(group);

		InternalResultsResponse<Group> resultResponse = getGroupBCL().fetchGroupByName(groupRequest);
		if (resultResponse.hasResults())
		{
			return resultResponse;
		}

		// Case not exist then create new group
		return getGroupBCL().insertGroup(groupRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#deleteSmartPointFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteSmartPointFromTag(TagRequest tagRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Tag tag : tagRequest.getTags())
		{
			tagRequest.setTag(tag);
			InternalResultsResponse<Tag> tagResponse = getTagDAC().fetchTagById(tagRequest);
			tagRequest.setTag(tagResponse.getFirstResult());

			// insert process
			InternalResultsResponse<Process> processResponse =
					insertProcess(tagRequest, LCActionTypeEnum.DEL_SMP_FROM_TAG, null);
			if (processResponse.isInError())
			{
				response.setStatus(processResponse.getStatus());
				response.addMessages(processResponse.getMessageInfoList());
				return response;
			}
		}
		response = getTagDAC().deleteSmartPointFromTag(tagRequest);
		if (response.getStatus() == Status.NoRowsUpdatedError)
		{
			response.setStatus(Status.OperationSuccess);
		}

		return response;
	}

	/**
	 * Insert process.
	 *
	 * @param tagRequest the tag request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TagRequest tagRequest, LCActionTypeEnum lcActionType,
			String processDescription)
			{
		return insertProcess(tagRequest, lcActionType, processDescription, null);
			}

	/**
	 * Insert process.
	 *
	 * @param tagRequest the tag request
	 * @param lcActionType the lc action type
	 * @param processDescription the process description
	 * @param deactivatedLights the deactivated lights
	 * @return the internal results response
	 */
	private InternalResultsResponse<Process> insertProcess(TagRequest tagRequest, LCActionTypeEnum lcActionType,
			String processDescription, List<Light> deactivatedLights)
	{
		if(ValidationUtil.isNull(tagRequest.getTag()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Tag tag = tagRequest.getTag();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_ID, String.valueOf(tag.getId())));
		actionParameters.add(new LCActionParameter(PropertyEnum.TAG_NAME, tag.getName()));

		InquiryLightRequest lightRequest = createInquiryLightRequest(tagRequest);
		Integer lightAmount = getSmartPointAccessorBCL().countLights(lightRequest).getFirstResult();

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = LCHelp.generateProcess(false, action, lightAmount);
		process.setIsProcessComplete(true);
		process.setEndTime(LCDateUtil.getNewDateUTC());

		if (!ValidationUtil.isNullOrEmpty(processDescription))
		{
			process.setDescription(processDescription);
		}

		ProcessRequest processRequest = createProcessRequest(tagRequest, process);
		processRequest.setProcessItemFailureList(
				createProcessItemWithFailure(
						deactivatedLights,
						ProcessItemStatusEnum.MLCFAILURE,
						ProcessStatusReasonEnum.LIGHT_DEACTIVATED));

		return getProcessBCL().insertProcess(processRequest);
	}
}
