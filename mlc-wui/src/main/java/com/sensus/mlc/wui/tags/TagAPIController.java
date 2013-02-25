package com.sensus.mlc.wui.tags;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("/api/tag")
public class TagAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant MAP_DELETE. */
	private static final String MAP_DELETE = "/delete";

	/** The Constant MAP_INSERT. */
	private static final String MAP_INSERT = "/insert";

	/** The Constant MAP_VERIFY_GROUP. */
	private static final String MAP_VERIFY_GROUP = "/verifyautogroup";

	/** The Constant MAP_UPDATE_AUTO_GROUP. */
	private static final String MAP_UPDATE_AUTO_GROUP = "/updateautogroup";

	/** The Constant MAP_UPDATE_AUTO_GROUP. */
	private static final String MAP_SMART_POINT_TO_TAG = "/insertlights";

	/** The Constant MAP_UPDATE_AUTO_GROUP. */
	private static final String MAP_REMOVE_SMART_POINT_TO_TAG = "/deletelights";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant AUTO_GROUP. */
	private static final String AUTO_GROUP = "autoGroup";

	/** The Constant TAG_NAME. */
	private static final String TAG_NAME = "tagName";

	/** The Constant INCLUDE_SMARTPOINTS_TO_GROUP. */
	private static final String INCLUDE_SMARTPOINTS_TO_GROUP = "includeSmartpointsToGroup";

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "TagAPIController";

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
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the new tag bcf
	 */
	@Resource
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

	/**
	 * @return the groupBCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * @param groupBCF the groupBCF to set
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Fetch.
	 * 
	 * @param inquiryTagRequest the inquiry tag request
	 * @param request the request
	 * @return the inquiry tag response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody InquiryTagRequest inquiryTagRequest, HttpServletRequest request)
	{

		InquiryTagResponse inquiryTagResponse = new InquiryTagResponse();
		try
		{
			setUserContext(inquiryTagRequest, request);

			inquiryTagResponse = getTagBCF().fetchAllTags((InquiryTagRequest)inquiryTagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryTagResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return inquiryTagResponse;

	}

	/**
	 * Delete tag.
	 * 
	 * @param tagRequest the tag request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response deleteTag(@RequestBody TagRequest tagRequest, HttpServletRequest request)
	{

		TagResponse tagResponse = new TagResponse();
		try
		{
			setUserContext(tagRequest, request);

			tagResponse = getTagBCF().deleteTag(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, tagResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return tagResponse;
	}

	/**
	 * Adds the tag.
	 * 
	 * @param tagRequest the tag request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response addTag(@RequestBody TagRequest tagRequest, HttpServletRequest request)
	{
		TagResponse tagResponse = new TagResponse();
		try
		{
			setUserContext(tagRequest, request);

			tagResponse = getTagBCF().insertTag(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, tagResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return tagResponse;
	}

	/**
	 * Adds the tag.
	 * 
	 * @param tagRequest the tag request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_SMART_POINT_TO_TAG, method = RequestMethod.POST)
	@ResponseBody
	public Response insertSmartpointToTag(@RequestBody TagRequest tagRequest, HttpServletRequest request)
	{
		TagResponse tagResponse = new TagResponse();
		try
		{
			setUserContext(tagRequest, request);
			Light light = new Light();
			light.setId(tagRequest.getSelectionPaginationIds().get(0));
			tagRequest.getSearchLight().setLight(light);

			tagResponse = getTagBCF().insertSmartPointToTag(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, tagResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return tagResponse;
	}

	@RequestMapping(value = MAP_REMOVE_SMART_POINT_TO_TAG, method = RequestMethod.POST)
	@ResponseBody
	public Response removeSmartpointFromTag(@RequestBody TagRequest tagRequest, HttpServletRequest request)
	{
		TagResponse tagResponse = new TagResponse();
		try
		{
			setUserContext(tagRequest, request);
			Light light = new Light();
			light.setId(tagRequest.getSelectionPaginationIds().get(0));
			tagRequest.getSearchLight().setLight(light);

			tagResponse = getTagBCF().deleteSmartPointFromTag(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, tagResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});

		}

		return tagResponse;
	}

	/**
	 * Verify group with tag name.
	 * 
	 * @param groupRequest the group request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_VERIFY_GROUP, method = RequestMethod.POST)
	@ResponseBody
	public Response verifyGroupWithTagName(@RequestBody Map<String, Object> jsonRequest, HttpServletRequest request)
	{
		GroupResponse groupResponse = new GroupResponse();
		try
		{
			GroupRequest groupRequest = new GroupRequest();

			Group group = new Group();

			if (!ValidationUtil.isNull(jsonRequest.get(TAG_NAME)))
			{
				group.setName(jsonRequest.get(TAG_NAME).toString());
			}

			groupRequest.setGroup(group);

			setUserContext(groupRequest, request);

			groupResponse = getGroupBCF().fetchGroupByName(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupResponse;
	}

	/**
	 * Update auto group.
	 * 
	 * @param tagRequest the tag request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_UPDATE_AUTO_GROUP, method = RequestMethod.POST)
	@ResponseBody
	public Response updateAutoGroup(@RequestBody Map<String, Object> jsonRequest, HttpServletRequest request)
	{
		TagResponse tagResponse = new TagResponse();
		try
		{
			TagRequest tagRequest = new TagRequest();

			Tag tag = new Tag();

			setUserContext(tagRequest, request);

			if (!ValidationUtil.isNull((jsonRequest).get(ID)))
			{
				tag.setId(Integer.parseInt((jsonRequest).get(ID).toString()));
			}

			if (!ValidationUtil.isNull((jsonRequest).get(AUTO_GROUP)))
			{
				tag.setAutoGroup(new Boolean((jsonRequest).get(ID).toString()));
			}

			if (!ValidationUtil.isNull((jsonRequest).get(INCLUDE_SMARTPOINTS_TO_GROUP)))
			{
				tagRequest.setIncludeSmartPointsToGroup(new Boolean((jsonRequest).get(ID).toString()));
			}

			tagRequest.setTag(tag);

			tagResponse = getTagBCF().updateAutoGroupTag(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, tagResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return tagResponse;
	}

}
