package com.sensus.lc.schedule.model.request;

import com.sensus.common.model.UserContext;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;

/**
 * The Class InquiryScheduleRequest.
 */
public class InquiryScheduleRequest extends InquiryPaginationRequest
{

	/** The schedule type enum. */
	private ScheduleTypeEnum scheduleTypeEnum;

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
		super(userContext);
	}

	/**
	 * Sets the schedule type enum.
	 * 
	 * @param scheduleTypeEnum the new schedule type enum
	 */
	public void setScheduleTypeEnum(ScheduleTypeEnum scheduleTypeEnum)
	{
		this.scheduleTypeEnum = scheduleTypeEnum;
	}

	/**
	 * Gets the schedule type enum.
	 * 
	 * @return the schedule type enum
	 */
	public ScheduleTypeEnum getScheduleTypeEnum()
	{
		return scheduleTypeEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryScheduleRequest [getScheduleTypeEnum()=" + getScheduleTypeEnum() + ", toString()="
				+ super.toString() + "]";
	}

}
