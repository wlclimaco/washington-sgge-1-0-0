package com.sensus.mlc.base.model;

import java.text.DateFormat;

import com.sensus.common.model.SensusModel;

/**
 * The Class UserPreferences.
 */
@SuppressWarnings("serial")
public class UserPreferences extends SensusModel
{
	/** The date format. */
	private DateFormat dateFormat;

	/** The Language. */
	private String language;

	/** The time zone info. */
	private TimeZoneInfo timeZoneInfo;

	/** The monitor request status. */
	private MonitorRequestStatusEnum monitorRequestStatusEnum;

	/** The convert unit. */
	private String convertUnit;

	/** The page size. */
	private Integer pageSize;

	/** The page size list. */
	private String pageSizeList;

	/** The page size dialog enum. */
	private PageSizeDialogEnum pageSizeDialogEnum;

	/** The polygon dialog. */
	private String polygonDialog;

	/**
	 * Gets the page size list.
	 * 
	 * @return the page size list
	 */
	public String getPageSizeList()
	{
		return this.pageSizeList;
	}

	/**
	 * Sets the page size list.
	 * 
	 * @param pageSizeList the new page size list
	 */
	public void setPageSizeList(String pageSizeList)
	{
		this.pageSizeList = pageSizeList;
	}

	/**
	 * Gets the date format.
	 * 
	 * @return the date format
	 */
	public DateFormat getDateFormat()
	{
		return this.dateFormat;
	}

	/**
	 * Sets the date format.
	 * 
	 * @param dateFormat the new date format
	 */
	public void setDateFormat(DateFormat dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	/**
	 * Gets the language.
	 * 
	 * @return the language
	 */
	public String getLanguage()
	{
		return this.language;
	}

	/**
	 * Sets the language.
	 * 
	 * @param language the new language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * Gets the time zone info.
	 * 
	 * @return the time zone info
	 */
	public TimeZoneInfo getTimeZoneInfo()
	{
		return this.timeZoneInfo;
	}

	/**
	 * Sets the time zone info.
	 * 
	 * @param timeZoneInfo the new time zone info
	 */
	public void setTimeZoneInfo(TimeZoneInfo timeZoneInfo)
	{
		this.timeZoneInfo = timeZoneInfo;
	}

	/**
	 * Gets the monitor request status enum.
	 * 
	 * @return the monitor request status enum
	 */
	public MonitorRequestStatusEnum getMonitorRequestStatusEnum()
	{
		return this.monitorRequestStatusEnum;
	}

	/**
	 * Sets the monitor request status enum.
	 * 
	 * @param monitorRequestStatusEnum the new monitor request status enum
	 */
	public void setMonitorRequestStatusEnum(MonitorRequestStatusEnum monitorRequestStatusEnum)
	{
		this.monitorRequestStatusEnum = monitorRequestStatusEnum;
	}

	/**
	 * Sets the convert unit.
	 * 
	 * @param convertUnit the new convert unit
	 */
	public void setConvertUnit(String convertUnit)
	{
		this.convertUnit = convertUnit;
	}

	/**
	 * Gets the convert unit.
	 * 
	 * @return the convert unit
	 */
	public String getConvertUnit()
	{
		return this.convertUnit;
	}

	/**
	 * Gets the page size.
	 * 
	 * @return the pageSize
	 */
	public Integer getPageSize()
	{
		return this.pageSize;
	}

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * Gets the page size dialog enum.
	 * 
	 * @return the page size dialog enum
	 */
	public PageSizeDialogEnum getPageSizeDialogEnum()
	{
		return this.pageSizeDialogEnum;
	}

	/**
	 * Sets the page size dialog enum.
	 * 
	 * @param pageSizeDialogEnum the new page size dialog enum
	 */
	public void setPageSizeDialogEnum(PageSizeDialogEnum pageSizeDialogEnum)
	{
		this.pageSizeDialogEnum = pageSizeDialogEnum;
	}

	/**
	 * @return the polygonDialog
	 */
	public String getPolygonDialog()
	{
		return this.polygonDialog;
	}

	/**
	 * @param polygonDialog the polygonDialog to set
	 */
	public void setPolygonDialog(String polygonDialog)
	{
		this.polygonDialog = polygonDialog;
	}

	@Override
	public String toString()
	{
		return "UserPreferences [getPageSizeList()=" + getPageSizeList() + ", getDateFormat()=" + getDateFormat()
				+ ", getLanguage()=" + getLanguage() + ", getTimeZoneInfo()=" + getTimeZoneInfo()
				+ ", getMonitorRequestStatusEnum()=" + getMonitorRequestStatusEnum() + ", getConvertUnit()="
				+ getConvertUnit() + ", getPageSize()=" + getPageSize() + ", getPageSizeDialogEnum()="
				+ getPageSizeDialogEnum() + ", getPolygonDialog()=" + getPolygonDialog() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + "]";
	}

}
