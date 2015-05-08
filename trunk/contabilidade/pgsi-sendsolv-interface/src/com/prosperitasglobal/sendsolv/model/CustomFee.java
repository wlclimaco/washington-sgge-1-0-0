package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.qat.framework.model.QATModelOL;

/**
 * The Class represents the different custom fees associated with a money transfer.
 */
@SuppressWarnings("serial")
public class CustomFee extends QATModelOL
{
	/** The date the custom fee is no longer valid. */
	private Long effectiveEndDate;

	/** The date that the custom fee becomes effective. */
	private Long effectiveStartDate;

	/** The SendSolv id for the custom fee. */
	private Integer id;

	/** The status of the customer fee. */
	private StatusEnum status;

	/** The SendSolv id of the transfer setting associated with this customer fee. */
	private Integer transferSettingId;

	/** The custom fee value. */
	private BigDecimal value;

	/**
	 * Default constructor.
	 */
	public CustomFee()
	{
		super();
	}

	/**
	 * Get the date the custom fee is no longer valid. If this date has a time portion, it will be removed. This
	 * attribute is only a date.
	 *
	 * @return The date.
	 */
	public Long getEffectiveEndDate()
	{
		return effectiveEndDate;
	}

	/**
	 * Set the date the custom fee is no longer valid.
	 *
	 * @param effectiveEndDate The date to set.
	 */
	public void setEffectiveEndDate(Long effectiveEndDate)
	{
		this.effectiveEndDate = effectiveEndDate;
	}

	/**
	 * Get the date that the custom fee becomes effective. If this date has a time portion, it will be removed. This
	 * attribute is only a date.
	 *
	 * @return The date.
	 */
	public Long getEffectiveStartDate()
	{
		return effectiveStartDate;
	}

	/**
	 * Set the date that the custom fee becomes effective.
	 *
	 * @param effectiveStartDate The date to set
	 */
	public void setEffectiveStartDate(Long effectiveStartDate)
	{
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Get the SendSolv id for the custom fee.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the custom fee.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the status of the customer fee.
	 *
	 * @return The status.
	 */
	public StatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Get the enumeration value for the status.
	 *
	 * @return The value for the status.
	 */
	public Integer getStatusValue()
	{
		if (getStatus() == null)
		{
			return null;
		}

		return getStatus().getValue();
	}

	/**
	 * Set the status of the customer fee.
	 *
	 * @param status The status to set.
	 */
	public void setStatus(StatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Set the enumeration by the value of the status.
	 *
	 * @param statusValue The value of the status enumeration to set.
	 */
	public void setStatusValue(Integer statusValue)
	{
		setStatus(StatusEnum.enumForValue(statusValue));
	}

	/**
	 * Get the SendSolv id of the transfer setting associated with this customer fee.
	 *
	 * @return The id.
	 */
	public Integer getTransferSettingId()
	{
		return transferSettingId;
	}

	/**
	 * Set the SendSolv id of the transfer setting associated with this customer fee.
	 *
	 * @param transferSettingId The id to set.
	 */
	public void setTransferSettingId(Integer transferSettingId)
	{
		this.transferSettingId = transferSettingId;
	}

	/**
	 * Get the value.
	 *
	 * @return the value
	 */
	public BigDecimal getValue()
	{
		return value;
	}

	/**
	 * Set the value.
	 *
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value)
	{
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CustomFee [getEffectiveEndDate()=" + getEffectiveEndDate() + ", getEffectiveStartDate()="
				+ getEffectiveStartDate() + ", getId()=" + getId() + ", getStatus()=" + getStatus()
				+ ", getStatusValue()=" + getStatusValue() + ", getTransferSettingId()=" + getTransferSettingId()
				+ ", getValue()=" + getValue() + ", toString()=" + super.toString() + "]";
	}
}
