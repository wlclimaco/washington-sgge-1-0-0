package com.sensus.dm.common.schedule.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.schedule.model.DMSchedule;

/**
 * The Class InquiryScheduleResponse.
 */
public class InquiryScheduleResponse extends InquiryResponse
{
	/** The schedules. */
	private List<DMSchedule> schedules;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the schedules.
	 * 
	 * @return the schedules
	 */
	public List<DMSchedule> getSchedules()
	{
		return schedules;
	}

	/**
	 * Sets the schedules.
	 * 
	 * @param schedules the new schedules
	 */
	public void setSchedules(List<DMSchedule> schedules)
	{
		this.schedules = schedules;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void addResults(Collection coll)
	{
		setSchedules(new ArrayList<DMSchedule>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryScheduleResponse [getSchedules()=" + getSchedules() + ", getFileName()=" + getFileName()
				+ ", getResultsSetInfo()=" + getResultsSetInfo() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
