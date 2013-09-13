package com.sensus.dm.common.schedule.model.request;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class ScheduleRequest.
 * 
 * @author QAT Brazil.
 */
public class ScheduleRequest extends TenantRequest
{
	/** The Constant TIME_OFFSET. */
	private static final Integer TIME_OFFSET = 10;

	/** The schedule. */
	private DMSchedule schedule;

	/** The ids file. */
	private File idsFile;

	/** The id file type. */
	private PropertyEnum idFileType;

	/** The upload ids. */
	private String uploadIds;

	/** The end date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/**
	 * Instantiates a new schedule request.
	 */
	public ScheduleRequest()
	{
	}

	/**
	 * Instantiates a new schedule request.
	 * 
	 * @param scheduleParam the schedule
	 */
	public ScheduleRequest(DMSchedule scheduleParam)
	{
		setSchedule(scheduleParam);
	}

	/**
	 * Instantiates a new schedule request.
	 * 
	 * @param scheduleParam the schedule param
	 * @param tenantParam the tenant param
	 */
	public ScheduleRequest(DMSchedule scheduleParam, DMTenant tenantParam)
	{
		setSchedule(scheduleParam);
		setTenant(tenantParam);
	}

	/**
	 * Instantiates a new schedule request.
	 * 
	 * @param scheduleParam the schedule
	 * @param userContextParam the user context
	 */
	public ScheduleRequest(DMSchedule scheduleParam, UserContext userContextParam)
	{
		this(scheduleParam);
		setUserContext(userContextParam);
	}

	/**
	 * Instantiates a new schedule request.
	 * 
	 * @param scheduleParam the schedule param
	 * @param userContextParam the user context param
	 * @param tenantParam the tenant param
	 * @param serviceTypeParam the service type param
	 */
	public ScheduleRequest(DMSchedule scheduleParam, UserContext userContextParam, DMTenant tenantParam,
			ServiceTypeEnum serviceTypeParam)
	{
		this(scheduleParam);
		setUserContext(userContextParam);
		setTenant(tenantParam);
		setServiceTypeEnum(serviceTypeParam);
	}

	/**
	 * Instantiates a new schedule request.
	 * 
	 * @param startDateParam the start date
	 * @param endDateParam the end date
	 */
	public ScheduleRequest(Date startDateParam, Date endDateParam)
	{
		setStartDate(startDateParam);
		setEndDate(endDateParam);
	}

	/**
	 * Gets the schedule.
	 * 
	 * @return the schedule
	 */
	public DMSchedule getSchedule()
	{
		return schedule;
	}

	/**
	 * Sets the schedule.
	 * 
	 * @param schedule the new schedule
	 */
	public void setSchedule(DMSchedule schedule)
	{
		this.schedule = schedule;
	}

	/**
	 * Gets the ids file.
	 * 
	 * @return the idsFile
	 */
	public File getIdsFile()
	{
		return idsFile;
	}

	/**
	 * Sets the ids file.
	 * 
	 * @param idsFile the idsFile to set
	 */
	public void setIdsFile(File idsFile)
	{
		this.idsFile = idsFile;
	}

	/**
	 * Gets the id file type.
	 * 
	 * @return the idFileType
	 */
	@Override
	public PropertyEnum getIdFileType()
	{
		return idFileType;
	}

	/**
	 * Sets the id file type.
	 * 
	 * @param idFileType the idFileType to set
	 */
	@Override
	public void setIdFileType(PropertyEnum idFileType)
	{
		this.idFileType = idFileType;
	}

	/**
	 * Gets the upload ids.
	 * 
	 * @return the uploadIds
	 */
	@Override
	public String getUploadIds()
	{
		return uploadIds;
	}

	/**
	 * Sets the upload ids.
	 * 
	 * @param uploadIds the uploadIds to set
	 */
	@Override
	public void setUploadIds(String uploadIds)
	{
		this.uploadIds = uploadIds;
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the start date int.
	 * 
	 * @return the start date int
	 */
	public Integer getStartDateInt()
	{
		if (getStartDate() == null)
		{
			return null;
		}

		return convertDateToInteger(getStartDate());
	}

	/**
	 * Gets the end date int.
	 * 
	 * @return the end date int
	 */
	public Integer getEndDateInt()
	{
		if (getEndDate() == null)
		{
			return null;
		}

		return convertDateToInteger(getEndDate());
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.model.request.TenantRequest#toString()
	 */
	@Override
	public String toString()
	{
		return "ScheduleRequest [getSchedule()=" + getSchedule() + ", getIdsFile()=" + getIdsFile()
				+ ", getIdFileType()=" + getIdFileType() + ", getUploadIds()=" + getUploadIds() + ", getStartDate()="
				+ getStartDate() + ", getEndDate()=" + getEndDate() + ", toString()="
				+ super.toString() + "]";
	}

	/**
	 * Convert date to integer.
	 * 
	 * @param date the date
	 * @return the integer
	 */
	private Integer convertDateToInteger(Date date)
	{
		String timeStamp =
				String.valueOf(date.getTime()
						+ TimeZone.getTimeZone(Calendar.getInstance().getTimeZone().getID()).getOffset(
								date.getTime()));

		return Integer.parseInt(StringUtils.mid(timeStamp, 0, TIME_OFFSET));
	}

}
