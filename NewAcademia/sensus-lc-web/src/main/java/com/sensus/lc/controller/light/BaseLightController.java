package com.sensus.lc.controller.light;

import javax.annotation.Resource;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.controller.model.LightDetailResponse;
import com.sensus.lc.group.bcf.IGroupBCF;
import com.sensus.lc.group.model.response.GroupResponse;
import com.sensus.lc.light.bcf.ILightBCF;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.LightResponse;
import com.sensus.lc.tag.bcf.ITagBCF;
import com.sensus.lc.tag.model.response.TagResponse;

/**
 * The Class BaseLightController.
 */
public class BaseLightController extends BaseViewController
{

	/** The light bcf. */
	private ILightBCF lightBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/**
	 * Fetch the groups for this light. If none found then copy any messages and keep going.
	 * 
	 * @param response the response
	 * @param lightRequest the light request
	 * @return true, if successful
	 */
	protected void fetchGroups(LightDetailResponse response, LightRequest lightRequest)
	{
		GroupResponse groupResponse = getGroupBCF().fetchGroupsByLight(lightRequest);

		// If no groups found then copy the messages and keep going.
		if (!groupResponse.isOperationSuccess())
		{
			response.merge(groupResponse);
		}

		response.setGroups(groupResponse.getGroups());
	}

	/**
	 * Fetch the light for this ID. If not found then copy messages and return false.
	 * 
	 * @param response the response
	 * @param lightId the light id
	 * @return true, if successful
	 */
	protected boolean fetchLight(LightDetailResponse response, FetchByIdRequest fetchByIdRequest)
	{
		LightResponse lightResponse = getLightBCF().fetchById(fetchByIdRequest);

		// If the light is not found then copy the messages and return false.
		if (ValidationUtil.isNull(lightResponse.getLight()))
		{
			response.merge(lightResponse);
			return false;
		}

		response.setLight(lightResponse.getLight());

		return true;
	}

	/**
	 * Fetch the tags for this light. If none found then copy any messages and keep going.
	 * 
	 * @param response the response
	 * @param lightRequest the light request
	 * @return true, if successful
	 */
	protected void fetchTags(LightDetailResponse response, LightRequest lightRequest)
	{
		TagResponse tagResponse = getTagBCF().fetchTagsByLight(lightRequest);

		// If no tags found then copy the messages and keep going.
		if (!tagResponse.isOperationSuccess())
		{
			response.merge(tagResponse);
		}

		response.setTags(tagResponse.getTags());
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Gets the light bcf.
	 * 
	 * @return the light bcf
	 */
	public ILightBCF getLightBCF()
	{
		return lightBCF;
	}

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tag bcf
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Sets the light bcf.
	 * 
	 * @param lightBCF the new light bcf
	 */
	@Resource
	public void setLightBCF(ILightBCF lightBCF)
	{
		this.lightBCF = lightBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the new tag bcf
	 */
	@Resource
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

}
