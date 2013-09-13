package com.sensus.dm.controller.tag;

import java.math.BigInteger;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.tag.bcf.ITagBCF;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.common.tag.model.response.TagResponse;
import com.sensus.dm.controller.base.BaseController;

/**
 * The Class TagAPIController.
 */
@Controller
@RequestMapping("/api/tag")
public class TagAPIController extends BaseController
{

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(TagAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TagAPIController";

	/** The Constant NAME. */
	private static final String NAME = "name";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant FETCHALL. */
	private static final String FETCHALL = "/fetchAll";

	/** The Constant DELETE. */
	private static final String DELETE = "/delete";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/** The Constant INSERT_DEVICE. */
	private static final String INSERT_DEVICE = "/insertDevice";

	/** The Constant DELETE_DEVICE. */
	private static final String DELETE_DEVICE = "/deleteDevice";

	/** The tag bcf. */
	private ITagBCF tagBCF;

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
	 * Fetch.
	 * 
	 * @param tagRequest the tag request
	 * @return the response
	 */
	@RequestMapping(value = FETCHALL, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchall(@RequestBody InquiryTagRequest tagRequest)
	{

		InquiryTagResponse response = new InquiryTagResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(tagRequest);

			tagRequest.setPreQueryCount(true);

			response = getTagBCF().fetchAllTags(tagRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, String> request)
	{
		TagResponse response = new TagResponse();
		try
		{
			InquiryTagRequest tagRequest = new InquiryTagRequest();

			Tag tag = new Tag();

			tag.addDevice(new Device(new Radio(new BigInteger(request.get(ID)))));

			tagRequest.setTag(tag);
			addUserContextToRequest(tagRequest);
			return getTagBCF().fetchTagsByDevice(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	/**
	 * Adds the tag.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(@RequestBody Map<String, String> request)
	{
		TagResponse response = new TagResponse();
		try
		{
			TagRequest tagRequest = new TagRequest();
			// ADD user context to request
			addUserContextToRequest(tagRequest);

			tagRequest.addTag(new Tag(request.get(NAME)));
			response = getTagBCF().insertTag(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Delete tag.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(@RequestBody Map<String, Object> request)
	{

		TagResponse response = new TagResponse();
		try
		{
			TagRequest tagRequest = new TagRequest();
			// ADD user context to request
			addUserContextToRequest(tagRequest);

			// new tag with id and name
			tagRequest.addTag(new Tag(((Integer)request.get(ID)), ((String)request.get("tagName"))));
			response = getTagBCF().deleteTag(tagRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Insert list smartpoint to tag.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_DEVICE, method = RequestMethod.POST)
	@ResponseBody
	public Response insertDevice(@RequestBody InquiryDeviceRequest inquiryDeviceRequest)
	{
		TagResponse tagResponse = new TagResponse();

		try
		{

			// ADD user context to request
			addUserContextToRequest(inquiryDeviceRequest);

			tagResponse = getTagBCF().insertDeviceToTag(inquiryDeviceRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, tagResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return tagResponse;
	}

	/**
	 * Removes the list smartpoint to tag.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_DEVICE, method = RequestMethod.POST)
	@ResponseBody
	public Response deleteDevice(@RequestBody InquiryDeviceRequest deviceRequest)
	{
		TagResponse response = new TagResponse();
		try
		{
			// ADD user context to request
			addUserContextToRequest(deviceRequest);

			response = getTagBCF().deleteDeviceFromTag(deviceRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

}
