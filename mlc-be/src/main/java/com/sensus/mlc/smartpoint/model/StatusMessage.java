package com.sensus.mlc.smartpoint.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.sensus.common.model.SensusModel;

/**
 * * The Model Object Status Message.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
@SuppressWarnings("serial")
public class StatusMessage extends SensusModel
{
	/** The id. */
	private Integer id;

	/** The date. */
	private Date date;

	/** The status exceptions (status_subtype table). */
	private List<StatusException> statusExceptions = new ArrayList<StatusException>();

	/** The Overall status of the light (status table). ACTIVE, ALARM, WARNING, MAINTENANCE, UNKNOWN. */
	private LightStatusEnum lightStatusEnum;

	/** The Category of the message. ALARM, BINDING, FORCED_STATUS, SETUP, UNSOLICITED_STATUS, CLEAR_ALARM */
	private StatusMessageCategoryEnum statusMessageCategoryEnum;

	/** The operational data. */
	private List<OperationalData> operationalData;

	/** The Override ***. */
	private OverrideEnum overrideEnum;

	/**
	 * Instantiates a new status message.
	 */
	public StatusMessage()
	{
	}

	/**
	 * Instantiates a new status message.
	 * 
	 * @param statusMessageId the status message id
	 * @param dateParam the date param
	 * @param statusExceptionsParam the status exceptions param
	 */
	public StatusMessage(Integer statusMessageId, Date dateParam, List<StatusException> statusExceptionsParam)
	{
		setId(statusMessageId);
		setDate(dateParam);
		setStatusExceptions(statusExceptionsParam);
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
	 * @param statusMessageId the new id
	 */
	public void setId(Integer statusMessageId)
	{
		id = statusMessageId;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getDate()
	{
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param dateDate the new date
	 */
	public void setDate(Date dateDate)
	{
		date = dateDate;
	}

	/**
	 * Gets the status exceptions.
	 * 
	 * @return the status exceptions
	 */
	public List<StatusException> getStatusExceptions()
	{
		return statusExceptions;
	}

	/**
	 * Sets the status exceptions.
	 * 
	 * @param statusExceptions the new status exceptions
	 */
	public void setStatusExceptions(List<StatusException> statusExceptions)
	{
		this.statusExceptions = statusExceptions;
	}

	/**
	 * Adds the status exception.
	 * 
	 * @param statusException the status exception
	 */
	public void addStatusException(StatusException statusException)
	{
		getStatusExceptions().add(statusException);
	}

	/**
	 * Gets the status exception type enum.
	 * 
	 * @return the status exception type enum
	 */
	public LightStatusEnum getLightStatusEnum()
	{
		return lightStatusEnum;
	}

	/**
	 * Sets the status exception type enum.
	 * 
	 * @param lightStatusEnum the new light status enum
	 */
	public void setLightStatusEnum(LightStatusEnum lightStatusEnum)
	{
		this.lightStatusEnum = lightStatusEnum;
	}

	/**
	 * Sets the light status enum value.
	 * 
	 * @param lightStatusEnumValue the new light status enum value
	 */
	public void setLightStatusEnumValue(Integer lightStatusEnumValue)
	{
		lightStatusEnum = LightStatusEnum.enumForValue(lightStatusEnumValue);
	}

	/**
	 * Gets the status message category enum value.
	 * 
	 * @return the status message category enum value
	 */
	public Integer getLightStatusEnumValue()
	{
		if (getLightStatusEnum() == null)
		{
			return null;
		}

		return getLightStatusEnum().getValue();
	}

	/**
	 * Gets the status message category enum.
	 * 
	 * @return the status message category enum
	 */
	public StatusMessageCategoryEnum getStatusMessageCategoryEnum()
	{
		return statusMessageCategoryEnum;
	}

	/**
	 * Sets the status message category enum.
	 * 
	 * @param statusMessageCategoryEnum the new status message category enum
	 */
	public void setStatusMessageCategoryEnum(StatusMessageCategoryEnum statusMessageCategoryEnum)
	{
		this.statusMessageCategoryEnum = statusMessageCategoryEnum;
	}

	/**
	 * Sets the status category type enum value.
	 * 
	 * @param statusMessageCategoryEnumValue the new status category type enum value
	 */
	public void setStatusMessageCategoryEnumValue(Integer statusMessageCategoryEnumValue)
	{
		statusMessageCategoryEnum = StatusMessageCategoryEnum.enumForValue(statusMessageCategoryEnumValue);
	}

	/**
	 * Gets the status message category enum value.
	 * 
	 * @return the status message category enum value
	 */
	public Integer getStatusMessageCategoryEnumValue()
	{
		if (getStatusMessageCategoryEnum() != null)
		{
			return getStatusMessageCategoryEnum().getValue();
		}

		return null;
	}

	/**
	 * Sets the operational data.
	 * 
	 * @param operationalData the new operational data
	 */
	public void setOperationalData(List<OperationalData> operationalData)
	{
		this.operationalData = operationalData;
	}

	/**
	 * Gets the operational data.
	 * 
	 * @return the operational data
	 */
	public List<OperationalData> getOperationalData()
	{
		return operationalData;
	}

	/**
	 * Gets the override enum.
	 * 
	 * @return the override enum
	 */
	public OverrideEnum getOverrideEnum()
	{
		return overrideEnum;
	}

	/**
	 * Sets the override enum.
	 * 
	 * @param overrideEnum the new override enum
	 */
	public void setOverrideEnum(OverrideEnum overrideEnum)
	{
		this.overrideEnum = overrideEnum;
	}

	/**
	 * Sets the override enum value.
	 * 
	 * @param override the new override enum value
	 */
	public void setOverrideEnumValue(Integer override)
	{
		overrideEnum = OverrideEnum.enumForValue(override);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "StatusMessage [getId()=" + getId() + ", getDate()=" + getDate() + ", getStatusExceptions()="
				+ getStatusExceptions() + ", getLightStatusEnum()=" + getLightStatusEnum()
				+ ", getLightStatusEnumValue()=" + getLightStatusEnumValue() + ", getStatusMessageCategoryEnum()="
				+ getStatusMessageCategoryEnum() + ", getStatusMessageCategoryEnumValue()="
				+ getStatusMessageCategoryEnumValue() + ", getOperationalData()=" + getOperationalData()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDate()=" + getModifyDate() + "]";
	}
}
