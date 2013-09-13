package com.sensus.dm.common.schedule.model.request;

import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;

/**
 * The Class InquiryScheduleRequest.
 * 
 * @author QAT Global
 */
public class InquiryScheduleRequest extends InquiryPaginationRequest
{

	/** The base search. */
	private BaseSearch baseSearch;

	/** The schedule status enums. */
	private List<ScheduleStatusEnum> scheduleStatusEnums;

	/** The frequencies. */
	private List<FrequencyTypeEnum> frequencies;

	/**
	 * Instantiates a new inquiry schedule request.
	 */
	public InquiryScheduleRequest()
	{
	}

	/**
	 * Instantiates a new inquiry schedule request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryScheduleRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Gets the schedule status enums.
	 * 
	 * @return the schedule status enums
	 */
	public List<ScheduleStatusEnum> getScheduleStatusEnums()
	{
		return scheduleStatusEnums;
	}

	/**
	 * Sets the schedule status enums.
	 * 
	 * @param scheduleStatusEnums the new schedule status enums
	 */
	public void setScheduleStatusEnums(List<ScheduleStatusEnum> scheduleStatusEnums)
	{
		this.scheduleStatusEnums = scheduleStatusEnums;
	}

	/**
	 * Gets the frequencies.
	 * 
	 * @return the frequencies
	 */
	public List<FrequencyTypeEnum> getFrequencies()
	{
		return frequencies;
	}

	/**
	 * Sets the frequencies.
	 * 
	 * @param frequencies the new frequencies
	 */
	public void setFrequencies(List<FrequencyTypeEnum> frequencies)
	{
		this.frequencies = frequencies;
	}

	/**
	 * Gets the base search.
	 * 
	 * @return the base search
	 */
	public BaseSearch getBaseSearch()
	{
		return baseSearch;
	}

	/**
	 * Sets the base search.
	 * 
	 * @param baseSearch the new base search
	 */
	public void setBaseSearch(BaseSearch baseSearch)
	{
		this.baseSearch = baseSearch;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.base.model.request.InquiryPaginationRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryScheduleRequest [getBaseSearch()=" + getBaseSearch() + ", getScheduleStatusEnums()="
				+ ", getFrequencies()=" + getFrequencies()
				+ getScheduleStatusEnums() + ", toString()=" + super.toString() + "]";
	}

}
