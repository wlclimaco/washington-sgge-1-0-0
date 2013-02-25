package com.sensus.mlc.smartpoint.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.sensus.common.model.SensusModel;

/**
 * The Class CurrentAlarmWarningMessage.
 * 
 * @author QATEmployee
 */
@SuppressWarnings("serial")
public class CurrentAlarmWarningMessage extends SensusModel
{
	/** The light id. */
	private Integer lightId;

	/** The status message id. */
	private Integer statusMessageId;

	/** The status message type. */
	private StatusMessageCategoryEnum statusMessageType;

	/** The status message type. */
	private LightStatusEnum statusMessage;

	/** The status message sub-type id. */
	private StatusExceptionTypeEnum statusMessageSubtype;

	/** The message date. */
	private Date messageDate;

	/** The tenant id. */
	private Integer tenantId;

	/**
	 * Instantiates a new current alarm warning message.
	 */
	public CurrentAlarmWarningMessage()
	{
	}

	/**
	 * Instantiates a new current alarm warning message.
	 * 
	 * @param id the id
	 * @param lightId the light id
	 * @param statusMessageId the status message id
	 * @param statusMessageType the status message type
	 * @param statusMessage the status message
	 * @param statusMessageSubtype the status message subtype
	 * @param messageDate the message date
	 * @param tenantId the tenant id
	 */
	public CurrentAlarmWarningMessage(Integer lightIdValue, Integer statusMessageIdValue,
			StatusMessageCategoryEnum statusMessageTypeValue, LightStatusEnum statusMessageValue,
			StatusExceptionTypeEnum statusMessageSubtypeValue, Date messageDateValue, Integer tenantIdValue)
	{
		setLightId(lightIdValue);
		setStatusMessageId(statusMessageIdValue);
		setStatusMessageType(statusMessageTypeValue);
		setStatusMessage(statusMessageValue);
		setStatusMessageSubtype(statusMessageSubtypeValue);
		setMessageDate(messageDateValue);
		setTenantId(tenantIdValue);
	}

	/**
	 * Gets the light id.
	 * 
	 * @return the light id
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 * 
	 * @param lightId the new light id
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * Gets the status message id.
	 * 
	 * @return the status message id
	 */
	public Integer getStatusMessageId()
	{
		return statusMessageId;
	}

	/**
	 * Sets the status message id.
	 * 
	 * @param statusMessageId the new status message id
	 */
	public void setStatusMessageId(Integer statusMessageId)
	{
		this.statusMessageId = statusMessageId;
	}

	/**
	 * Gets the status message type.
	 * 
	 * @return the status message type
	 */
	public StatusMessageCategoryEnum getStatusMessageType()
	{
		return statusMessageType;
	}

	/**
	 * Sets the status message type.
	 * 
	 * @param statusMessageType the new status message type
	 */
	public void setStatusMessageType(StatusMessageCategoryEnum statusMessageType)
	{
		this.statusMessageType = statusMessageType;
	}

	/**
	 * Gets the status message type value.
	 * 
	 * @return the status message type value
	 */
	public Integer getStatusMessageTypeValue()
	{
		if (getStatusMessageType() != null)
		{
			return getStatusMessageType().getValue();
		}

		return null;
	}

	/**
	 * Sets the status message type value.
	 * 
	 * @param statusMessageTypeValue the new status message type value
	 */
	public void setStatusMessageTypeValue(Integer statusMessageTypeValue)
	{
		statusMessageType = StatusMessageCategoryEnum.enumForValue(statusMessageTypeValue);
	}

	/**
	 * Gets the status message.
	 * 
	 * @return the status message
	 */
	public LightStatusEnum getStatusMessage()
	{
		return statusMessage;
	}

	/**
	 * Sets the status message.
	 * 
	 * @param statusMessage the new status message
	 */
	public void setStatusMessage(LightStatusEnum statusMessage)
	{
		this.statusMessage = statusMessage;
	}

	/**
	 * Gets the status message value.
	 * 
	 * @return the status message value
	 */
	public Integer getStatusMessageValue()
	{
		if (getStatusMessage() != null)
		{
			return getStatusMessage().getValue();
		}

		return null;
	}

	/**
	 * Sets the status message value.
	 * 
	 * @param statusMessageValue the new status message value
	 */
	public void setStatusMessageValue(Integer statusMessageValue)
	{
		statusMessage = LightStatusEnum.enumForValue(statusMessageValue);
	}

	/**
	 * Gets the status message subtype.
	 * 
	 * @return the status message subtype
	 */
	public StatusExceptionTypeEnum getStatusMessageSubtype()
	{
		return statusMessageSubtype;
	}

	/**
	 * Sets the status message subtype.
	 * 
	 * @param statusMessageSubtype the new status message subtype
	 */
	public void setStatusMessageSubtype(StatusExceptionTypeEnum statusMessageSubtype)
	{
		this.statusMessageSubtype = statusMessageSubtype;
	}

	/**
	 * Gets the status message subtype value.
	 * 
	 * @return the status message subtype value
	 */
	public Integer getStatusMessageSubtypeValue()
	{
		if (getStatusMessageSubtype() != null)
		{
			return getStatusMessageSubtype().getValue();
		}

		return null;
	}

	/**
	 * Sets the status message subtype value.
	 * 
	 * @param statusMessageSubtypeValue the new status message subtype value
	 */
	public void setStatusMessageSubtypeValue(Integer statusMessageSubtypeValue)
	{
		statusMessageSubtype = StatusExceptionTypeEnum.enumForValue(statusMessageSubtypeValue);
	}

	/**
	 * Gets the message date.
	 * 
	 * @return the message date
	 */
	@JSON(format = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	public Date getMessageDate()
	{
		return messageDate;
	}

	/**
	 * Sets the message date.
	 * 
	 * @param messageDate the new message date
	 */
	public void setMessageDate(Date messageDate)
	{
		this.messageDate = messageDate;
	}

	/**
	 * Gets the tenant id.
	 * 
	 * @return the tenant id
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 * 
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

	@Override
	public String toString()
	{
		return "CurrentAlarmWarningMessage [getLightId()=" + getLightId() + ", getStatusMessageId()="
				+ getStatusMessageId() + ", getStatusMessageType()=" + getStatusMessageType()
				+ ", getStatusMessageTypeValue()=" + getStatusMessageTypeValue() + ", getStatusMessage()="
				+ getStatusMessage() + ", getStatusMessageValue()=" + getStatusMessageValue()
				+ ", getStatusMessageSubtype()=" + getStatusMessageSubtype() + ", getStatusMessageSubtypeValue()="
				+ getStatusMessageSubtypeValue() + ", getMessageDate()=" + getMessageDate() + ", getTenantId()="
				+ getTenantId() + "]";
	}

}
