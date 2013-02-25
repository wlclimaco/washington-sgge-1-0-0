package com.sensus.mlc.settings.model;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.smartpoint.model.PropertyEnum;

/**
 * The Model Object Settings.
 *
 * @author - Igor Henrique - QAT Brazil
 */
@SuppressWarnings("serial")
public class Setting extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The propertyEnum. */
	private PropertyEnum propertyEnum;

	/** The property_value. */
	private String propertyValue;

	/**
	 * Attributes.
	 */
	private String language;

	private String timeZone;

	private String dateFormat;

	private String monitorRequest;

	private String convertEnergyUnit;

	private Integer pageSize;

	private Integer pageSizeShowDialog;

	private String showDialogPolygon;

	/**
	 * Instantiates a new setting.
	 */
	public Setting()
	{
	}

	/**
	 * Instantiates a new setting.
	 *
	 * @param propertyEnum the property enum
	 * @param propertyValue the property value
	 */
	public Setting(PropertyEnum propertyEnumInternal, String propertyValueInternal)
	{
		propertyEnum = propertyEnumInternal;
		propertyValue = propertyValueInternal;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the propertyValue.
	 *
	 * @return the propertyValue
	 */
	public String getPropertyValue()
	{
		return propertyValue;
	}

	/**
	 * Gets the property enum.
	 *
	 * @return the property enum
	 */
	public PropertyEnum getPropertyEnum()
	{
		return propertyEnum;
	}

	/**
	 * Sets the property enum.
	 *
	 * @param propertyEnum the new property enum
	 */
	public void setPropertyEnum(PropertyEnum propertyEnum)
	{
		this.propertyEnum = propertyEnum;
	}

	/**
	 * Sets the propertyValue.
	 *
	 * @param propertyValueParam the new property value
	 */
	public void setPropertyValue(String propertyValueParam)
	{
		propertyValue = propertyValueParam;
	}

	/**
	 * Sets the property enum value.
	 *
	 * @param valueParam the new property enum value
	 */
	public void setPropertyEnumValue(Integer valueParam)
	{
		propertyEnum = PropertyEnum.enumForValue(valueParam);
	}

	/**
	 * Gets the property enum value.
	 *
	 * @return the property enum value
	 */
	public Integer getPropertyEnumValue()
	{
		return propertyEnum.getValue();
	}



	/**
	 * @return the language
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone()
	{
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone)
	{
		this.timeZone = timeZone;
	}

	/**
	 * @return the dateFormat
	 */
	public String getDateFormat()
	{
		return dateFormat;
	}

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(String dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	/**
	 * @return the monitorRequest
	 */
	public String getMonitorRequest()
	{
		return monitorRequest;
	}

	/**
	 * @param monitorRequest the monitorRequest to set
	 */
	public void setMonitorRequest(String monitorRequest)
	{
		this.monitorRequest = monitorRequest;
	}

	/**
	 * @return the convertEnergyUnit
	 */
	public String getConvertEnergyUnit()
	{
		return convertEnergyUnit;
	}

	/**
	 * @param convertEnergyUnit the convertEnergyUnit to set
	 */
	public void setConvertEnergyUnit(String convertEnergyUnit)
	{
		this.convertEnergyUnit = convertEnergyUnit;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageSizeShowDialog
	 */
	public Integer getPageSizeShowDialog()
	{
		return pageSizeShowDialog;
	}

	/**
	 * @param pageSizeShowDialog the pageSizeShowDialog to set
	 */
	public void setPageSizeShowDialog(Integer pageSizeShowDialog)
	{
		this.pageSizeShowDialog = pageSizeShowDialog;
	}

	/**
	 * @return the showDialogPolygon
	 */
	public String getShowDialogPolygon()
	{
		return showDialogPolygon;
	}

	/**
	 * @param showDialogPolygon the showDialogPolygon to set
	 */
	public void setShowDialogPolygon(String showDialogPolygon)
	{
		this.showDialogPolygon = showDialogPolygon;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Setting [id=" + id + ", propertyEnum=" + propertyEnum + ", propertyValue=" + propertyValue
				+ ", language=" + language + ", timeZone=" + timeZone + ", dateFormat=" + dateFormat
				+ ", monitorRequest=" + monitorRequest + ", convertEnergyUnit=" + convertEnergyUnit + ", pageSize="
				+ pageSize + ", pageSizeShowDialog=" + pageSizeShowDialog + ", showDialogPolygon=" + showDialogPolygon
				+ "]";
	}



}
