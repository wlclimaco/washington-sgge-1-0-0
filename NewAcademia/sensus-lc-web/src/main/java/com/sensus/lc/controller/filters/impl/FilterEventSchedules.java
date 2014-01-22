package com.sensus.lc.controller.filters.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.model.FiltersModel;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.schedule.bcf.IScheduleBCF;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;

/**
 * The Class FilterEventSchedules.
 */
@Component
public class FilterEventSchedules extends AbstractFilterBase
{
	/** The Constant EVENT_SCHEDULE. */
	private static final String EVENT_SCHEDULE = "EVENT_SCHEDULE";

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the scheduleBCF
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the scheduleBCF to set
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return EVENT_SCHEDULE.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		Map<String, Object> records = new HashMap<String, Object>();

		InquiryScheduleRequest scheduleRequest = new InquiryScheduleRequest(userContext);
		scheduleRequest.setPageSize(0);
		scheduleRequest.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(scheduleRequest);
		if (!ValidationUtil.isNullOrEmpty(response.getSchedules()))
		{
			for (Schedule schedule : response.getSchedules())
			{
				records.put(schedule.getId().toString(), schedule.getName());
			}
		}

		filtersResponse.setEvent_schedule(new FiltersModel(FILTER_TYPE_CHECKBOX, records));
	}

}
