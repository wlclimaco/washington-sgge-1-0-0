package com.sensus.mlc.smartpoint.model;

import com.sensus.common.validation.ValidationUtil;

/**
 * * The Class StatusException.
 * 
 * @author - Gustavo Aragao - QAT Brazil
 */
public class StatusException
{
	/** The id. */
	private Integer id;

	/** The label key. */
	private String labelKey;

	/** The status exception type enum (status_subtype table). */
	private StatusExceptionTypeEnum statusExceptionTypeEnum;

	/**
	 * Instantiates a new status exception.
	 */
	public StatusException()
	{
	}

	/**
	 * Instantiates a new status exception.
	 * 
	 * @param statusExceptionTypeEnumParam the status exception type enum param
	 */
	public StatusException(StatusExceptionTypeEnum statusExceptionTypeEnumParam)
	{
		setStatusExceptionTypeEnum(statusExceptionTypeEnumParam);
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
	 * Gets the label key.
	 * 
	 * @return the label key
	 */
	public String getLabelKey()
	{
		return labelKey;
	}

	/**
	 * Sets the label key.
	 * 
	 * @param labelKey the new label key
	 */
	public void setLabelKey(String labelKey)
	{
		this.labelKey = labelKey;
	}

	/**
	 * Gets the status exception type enum.
	 * 
	 * @return the status exception type enum
	 */
	public StatusExceptionTypeEnum getStatusExceptionTypeEnum()
	{
		return statusExceptionTypeEnum;
	}

	/**
	 * Sets the status exception type enum.
	 * 
	 * @param statusExceptionTypeEnum the new status exception type enum
	 */
	public void setStatusExceptionTypeEnum(StatusExceptionTypeEnum statusExceptionTypeEnum)
	{
		this.statusExceptionTypeEnum = statusExceptionTypeEnum;
	}

	/**
	 * Gets the status exception type enum.
	 * 
	 * @return the status exception type enum
	 */
	public Integer getStatusExceptionTypeEnumValue()
	{
		if (ValidationUtil.isNull(statusExceptionTypeEnum))
		{
			return null;
		}

		return statusExceptionTypeEnum.getValue();
	}

	/**
	 * Sets the status exception type enum.
	 * 
	 * @param statusExceptionTypeEnumValue the new status exception type enum value
	 */
	public void setStatusExceptionTypeEnumValue(Integer statusExceptionTypeEnumValue)
	{
		statusExceptionTypeEnum = StatusExceptionTypeEnum.enumForValue(statusExceptionTypeEnumValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "StatusException [getId()=" + getId() + ", getLabelKey()=" + getLabelKey()
				+ ", getStatusExceptionTypeEnum()=" + getStatusExceptionTypeEnum()
				+ ", getStatusExceptionTypeEnumValue()=" + getStatusExceptionTypeEnumValue() + ", hashCode()="
				+ hashCode() + "]";
	}
}
