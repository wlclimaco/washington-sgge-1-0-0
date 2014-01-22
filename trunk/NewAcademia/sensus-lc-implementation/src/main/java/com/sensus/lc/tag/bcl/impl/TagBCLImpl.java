package com.sensus.lc.tag.bcl.impl;

import static com.sensus.lc.process.util.ProcessUtil.createProcessItemWithFailure;
import static com.sensus.lc.process.util.ProcessUtil.createProcessRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusAppContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.group.bcl.IGroupBCL;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.SearchParameter;
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
import com.sensus.lc.tag.bcl.ITagBCL;
import com.sensus.lc.tag.dac.ITagDAC;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.request.TagRequest;

/**
 * The Class TagBCLImpl.
 */
public class TagBCLImpl implements ITagBCL
{
	/** The tag dac. */
	private ITagDAC tagDAC; // injected by Spring through setter

	/** The group bcl. */
	private IGroupBCL groupBCL; // injected by Application Context Spring through setter

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The light bcl. */
	private ILightBCL lightBCL; // injected by Spring through setter

	/** The Constant SENSUS_MLC_TAGVALIDATOR_ALREADY_EXIST. */
	private static final String SENSUS_MLC_TAGVALIDATOR_ALREADY_EXIST = "sensus.mlc.tagvalidator.already.exist";

	/** The Constant LIGH_ALREADY_IN_THE_GROUP. */
	private static final String LIGHT_ALREADY_IN_TAG = "sensus.mlc.tagvalidator.light.already.exist";

	/** The Constant GROUP_BCL_BEAN. */
	private static final String GROUP_BCL_BEAN = "groupBCLTarget";

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
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#insertLightToTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse insertLightToTag(TagRequest tagRequest)
	{
		List<Tag> tags = tagRequest.getTags();
		UserContext userContext = tagRequest.getUserContext();

		if (!ValidationUtil.isNull(tagRequest.getLightRequest()))
		{
			tagRequest.getLightRequest().setUserContext(userContext);
		}

		InternalResultsResponse<Light> lightResponse = getLightBCL().fetchAllByRequest(tagRequest.getLightRequest());
		InternalResponse internalResponse = new InternalResponse();

		for (Tag tag : tags)
		{
			// Fetch tag data
			tagRequest.setTag(tag);
			InternalResultsResponse<Tag> response = fetchTagToInsertLight(tagRequest);
			if (response.isInError())
			{
				return response;
			}

			tag = response.getFirstResult();

			LCAction action = new LCAction(LCActionTypeEnum.ADD_LIGHT_TO_TAG);
			action.addActionParameter(new LCActionParameter(PropertyEnum.TAG_ID));
			action.addActionParameter(new LCActionParameter(PropertyEnum.TAG_NAME, tag.getName()));
			Process process = ProcessUtil.generateProcess(false, action, lightResponse.getResultsList().size());

			tagRequest.setTag(tag);
			List<ProcessItem> processItems = new ArrayList<ProcessItem>();

			for (Light light : lightResponse.getResultsList())
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

				tagRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));

				// Fetch lights that already is on the tag
				List<Light> lightsBelongGroup = getTagDAC().fetchLightsBelongTag(tagRequest).getResultsList();

				if (!ValidationUtil.isNullOrZero(lightsBelongGroup.size()))
				{
					internalResponse.addMessage(LIGHT_ALREADY_IN_TAG,
							Message.MessageSeverity.Error,
							Message.MessageLevel.FieldValidation,
							new Object[] {lightsBelongGroup.size(), tag.getName()});

					internalResponse.setStatus(Status.OperationSuccess);

					ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.MLCFAILURE);
					processItem.setLight(light);
					processItems.add(processItem);
					continue;

				}

				// Apply auto group
				InternalResponse autoGroupResponse = insertTagAutoGroup(tagRequest);

				if (autoGroupResponse.isInError())
				{
					internalResponse.addMessages(autoGroupResponse.getMessageInfoList());
					continue;
				}

				InternalResponse insertResponse = getTagDAC().insertLightToTag(tagRequest);

				internalResponse.setStatus(insertResponse.getStatus());
				internalResponse.addMessages(insertResponse.getMessageInfoList());

				ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.SUCCESS);
				processItem.setLight(light);
				processItems.add(processItem);

			}
			// Insert process to lights reseted
			process.setProcessItems(processItems);
			process.setIsProcessComplete(true);
			insertProcess(userContext, internalResponse, process);

		}
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#insertTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = fetchTagByName(tagRequest);
		if (response.hasResults())
		{
			// Tag is unique by tenant
			response.setStatus(Status.PersistenceError);
			response.addMessage(
					SENSUS_MLC_TAGVALIDATOR_ALREADY_EXIST,
					MessageSeverity.Error,
					MessageLevel.FieldValidation,
					new Object[] {tagRequest.getTag().getName()});
			return response;
		}

		response = getTagDAC().insertTag(tagRequest);

		if (response.isInError())
		{
			return response;
		}

		Tag tag = response.getFirstResult();
		tagRequest.setTag(tag);

		SearchParameter tagParameter = new SearchParameter(PropertyEnum.TAG_ID, String.valueOf(tag.getId()));
		tagRequest.getSearchLight().addSearchParameter(tagParameter);
		InternalResultsResponse<Process> processResponse = insertProcess(tagRequest, LCActionTypeEnum.INSERT_TAG, null);
		tagRequest.getSearchLight().getSearchParameters().remove(tagParameter);

		response.setStatus(processResponse.getStatus());
		response.addMessages(processResponse.getMessageInfoList());
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#fetchTagsByLight(com.sensus.mlc.light.model.request.LightRequest)
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
		if (tag.isAutoGroup() && tagRequest.getIncludeLightsToGroup())
		{
			Boolean autoGroup = tag.isAutoGroup();
			InternalResultsResponse<Tag> tagResponse = getTagDAC().fetchTagById(tagRequest);
			tag = tagResponse.getFirstResult();
			tag.setAutoGroup(autoGroup);
			tagRequest.setTag(tag);

			Group group = new Group();
			group.setName(tag.getName());
			GroupRequest groupRequest = new GroupRequest(tagRequest.getUserContext());
			groupRequest.setGroup(group);
			group = getGroupBCL().fetchGroupByName(groupRequest).getFirstResult();
			groupRequest.setGroup(group);

			groupRequest.setSelectionPaginationIds(LCPropertiesExtractorUtil.extractLightId(tagResponse
					.getFirstResult().getLights()));

			SearchParameter param = new SearchParameter();
			param.setPropertyEnum(PropertyEnum.TAG_ID);
			param.setValue(String.valueOf(tag.getId()));

			SearchLight searchLight = new SearchLight();
			searchLight.getSearchParameters().add(param);
			groupRequest.setSearchLight(searchLight);
			groupResponse = getGroupBCL().insertLightToGroup(groupRequest);
		}

		InternalResultsResponse<Tag> internalResultsResponse = getTagDAC().updateAutoGroupTag(tagRequest);
		internalResultsResponse.addMessages(groupResponse.getMessageInfoList());
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tag.bcl.ITagBCL#deleteLightFromTag(com.sensus.mlc.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteLightFromTag(TagRequest tagRequest)
	{
		UserContext userContext = tagRequest.getUserContext();

		if (!ValidationUtil.isNull(tagRequest.getLightRequest()))
		{
			tagRequest.getLightRequest().setUserContext(userContext);
		}

		InternalResultsResponse<Light> response = getLightBCL().fetchAllByRequest(tagRequest.getLightRequest());

		LCAction action = new LCAction(LCActionTypeEnum.DEL_LIGHT_FROM_TAG);
		action.addActionParameter(new LCActionParameter(PropertyEnum.TAG_ID));
		Process process = ProcessUtil.generateProcess(false, action, response.getResultsList().size());

		// Verify if exist some light to remove
		if (response.isInError() || !response.hasResults())
		{
			insertProcess(userContext, response, process);
			return response;
		}

		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		InternalResponse internalResponse = new InternalResponse();
		for (Tag tag : tagRequest.getTags())
		{

			tagRequest.setTag(tag);
			InternalResultsResponse<Tag> tagResponse = getTagDAC().fetchTagById(tagRequest);

			action = new LCAction(LCActionTypeEnum.DEL_LIGHT_FROM_TAG);
			action.addActionParameter(new LCActionParameter(PropertyEnum.TAG_ID));
			action.addActionParameter(new LCActionParameter(PropertyEnum.TAG_NAME, tagResponse.getFirstResult()
					.getName()));
			process = ProcessUtil.generateProcess(false, action, response.getResultsList().size());

			tagRequest.setTag(tagResponse.getFirstResult());

			for (Light light : response.getResultsList())
			{
				tagRequest.setSelectionPaginationIds(Arrays.asList(light.getId()));
				InternalResponse deleteResponse = getTagDAC().deleteLightFromTag(tagRequest);

				internalResponse.setStatus(deleteResponse.getStatus());
				internalResponse.addMessages(deleteResponse.getMessageInfoList());

				if (deleteResponse.getStatus() == Status.NoRowsUpdatedError)
				{
					internalResponse.setStatus(Status.OperationSuccess);
				}

				ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.SUCCESS);
				processItem.setLight(light);
				processItems.add(processItem);
			}
			// Insert process to lights reseted
			process.setProcessItems(processItems);
			process.setIsProcessComplete(true);
			insertProcess(userContext, internalResponse, process);
		}

		return internalResponse;
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
		if (ValidationUtil.isNull(tagRequest.getTag()))
		{
			InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		Tag tag = tagRequest.getTag();

		List<LCActionParameter> actionParameters = new ArrayList<LCActionParameter>();
		actionParameters.add(new LCActionParameter(com.sensus.lc.light.model.PropertyEnum.TAG_ID, String.valueOf(tag
				.getId())));
		actionParameters.add(new LCActionParameter(com.sensus.lc.light.model.PropertyEnum.TAG_NAME, tag.getName()));

		LightRequest lightRequest = SearchTranslation.setLightRequestCriterias(tagRequest);
		lightRequest.setUserContext(tagRequest.getUserContext());
		Integer lightAmount = getLightBCL().countAllByRequest(lightRequest).getFirstResult();

		LCAction action = new LCAction(lcActionType);
		action.setActionParameters(actionParameters);
		Process process = ProcessUtil.generateProcess(false, action, lightAmount);
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
	private InternalResultsResponse<Tag> fetchTagToInsertLight(TagRequest tagRequest)
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
		return getGroupBCL().insertLightToGroup(groupRequest);
	}

}
