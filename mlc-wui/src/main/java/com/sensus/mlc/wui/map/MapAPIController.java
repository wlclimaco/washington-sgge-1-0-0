package com.sensus.mlc.wui.map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

@Controller
@RequestMapping("/api/map")
public class MapAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	private static final String MAP_FETCH = "/fetch";

	private IGroupBCF groupBCF;

	private IScheduleBCF scheduleBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "MapAPIController";

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
	 * @return the scheduleBCF
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * @param scheduleBCF the scheduleBCF to set
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	@Override
	public void setMessageSource(MessageSource arg0)
	{
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public InquiryLightResponse fetch(
			@RequestBody LightSelectionRequest lightSelectionRequest,
			HttpServletRequest request)
	{

		InquiryLightResponse response = null;

		try
		{
			setUserContext(lightSelectionRequest, request);

			/*
			 * if ("schedule".equals(lightSelectionRequest.getAction()))
			 * {
			 * try
			 * {
			 * response = getScheduleBCF().fetchSmartpointByScheduleToMap((ScheduleRequest)lightSelectionRequest);
			 * }
			 * catch (Exception e)
			 * {
			 * // if (LOG.isErrorEnabled())
			 * // {
			 * // LOG.error("Error getting lights by group to map", e);
			 * // }
			 * }
			 * }
			 * else if ("group".equals(lightSelectionRequest.getAction()))
			 * {
			 * try
			 * {
			 * response = getGroupBCF().fetchSmartpointByGroupToMap((GroupRequest)lightSelectionRequest);
			 * }
			 * catch (Exception e)
			 * {
			 * // if (LOG.isErrorEnabled())
			 * // {
			 * // LOG.error("Error getting lights by group to map", e);
			 * // }
			 * }
			 * }
			 */
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

}
