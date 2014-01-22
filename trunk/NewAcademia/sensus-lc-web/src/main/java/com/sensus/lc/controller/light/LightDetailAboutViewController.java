package com.sensus.lc.controller.light;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.model.LightDetailResponse;
import com.sensus.lc.group.model.response.InquiryGroupResponse;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.schedule.bcf.IScheduleBCF;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.response.InquiryTagResponse;

/**
 * The Class LightDetailAboutViewController.
 */
@Controller
@RequestMapping({"/lightDetail",
		"/lightDetail/about",
		"/lightDetail/ecoMode",
		"/lightDetail/history"})
public class LightDetailAboutViewController extends BaseLightController
{

	/** The Constant FILL_DETAIL. */
	public static final String FILL_DETAIL = "";

	/** The Constant TAG_RESPONSE. */
	public static final String TAG_RESPONSE = "tagResponse";

	/** The Constant GROUP_RESPONSE. */
	public static final String GROUP_RESPONSE = "groupResponse";

	/** The Constant SCHEDULE_RESPONSE. */
	public static final String SCHEDULE_RESPONSE = "scheduleResponse";

	/** The Constant VIEW_DETAIL_ABOUT_MAIN. */
	private static final String VIEW_LIGHT_DETAIL_MAIN = "/light/light_detail_main";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightDetailAboutViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightDetailAboutViewController";

	/** The Constant LIGHT_ID. */
	public static final String LIGHT_ID = "id";

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/**
	 * Fill detail about.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FILL_DETAIL, method = RequestMethod.GET)
	public ModelAndView fillDetailAbout(@RequestParam(value = LIGHT_ID, required = true) Integer lightId,
			HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_LIGHT_DETAIL_MAIN);

		LightRequest lightRequest = new LightRequest(getUserContext(request));
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setLightIdList(Arrays.asList(lightId));
		lightRequest.setLightCriteria(lightCriteria);

		InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest(getUserContext(request));
		InquiryGroupResponse groupResponse = getGroupBCF().fetchAllGroups(inquiryPaginationRequest);

		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest(getUserContext(request));
		InquiryTagResponse tagResponse = getTagBCF().fetchAllTags(inquiryTagRequest);

		InquiryScheduleRequest inquiryScheduleRequest = new InquiryScheduleRequest(getUserContext(request));
		inquiryScheduleRequest.setPageSize(0);
		InquiryScheduleResponse scheduleResponse = getScheduleBCF().fetchAllSchedules(inquiryScheduleRequest);

		FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(lightId);
		setUserContext(fetchByIdRequest, request);

		LightDetailResponse lightDetailResponse = new LightDetailResponse();
		if (fetchLight(lightDetailResponse, fetchByIdRequest))
		{
			fetchGroups(lightDetailResponse, lightRequest);
			fetchTags(lightDetailResponse, lightRequest);
		}

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(lightDetailResponse));
			modelAndView.addObject(GROUP_RESPONSE, getMapper().writeValueAsString(groupResponse));
			modelAndView.addObject(TAG_RESPONSE, getMapper().writeValueAsString(tagResponse));
			modelAndView.addObject(SCHEDULE_RESPONSE, getMapper().writeValueAsString(scheduleResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
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
}