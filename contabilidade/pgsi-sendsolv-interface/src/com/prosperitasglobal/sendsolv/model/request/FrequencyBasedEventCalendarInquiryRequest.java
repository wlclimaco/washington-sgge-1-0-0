package com.prosperitasglobal.sendsolv.model.request;

public class FrequencyBasedEventCalendarInquiryRequest extends PagedInquiryRequest
{

	/** The start date. */
	private Long startDate;

	/** The end date. */
	private Long endDate;

	/**
	 * @return the startDate
	 */
	public Long getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Long startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Long getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Long endDate)
	{
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FrequencyBasedEventCalendarInquiryRequest [getStartDate()=" + getStartDate() + ", getEndDate()="
				+ getEndDate() + "]";
	}

}
