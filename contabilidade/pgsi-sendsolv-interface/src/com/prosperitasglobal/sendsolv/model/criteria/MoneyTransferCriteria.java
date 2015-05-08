package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;

/**
 * Criteria member used for doing a lookup on a money transfer in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class MoneyTransferCriteria implements Serializable
{
	/** The SendSolv id of the member associated with the transfer. */
	private Member member;

	/** The location name. */
	private String locationName;

	/** The organization name. */
	private String organizationName;

	/** The transaction id. */
	private String transactionId;

	/** The confirmation number. */
	private String confirmationNumber;

	/** The currency code. */
	private String currencyCode;

	/** The SendSolv id of the payer. */
	private Integer payerId;

	/** The money transfer batch id. */
	private Integer moneyTransferBatchId;

	/** The status list. */
	private List<MoneyTransferStatusEnum> statusList;

	/** The primary phone number. */
	private String primaryPhoneNumber;

	/** The recipient id. */
	private Integer recipientId;

	/** The create date. */
	private Long createDateUTC;

	/** The transfer setting id. */
	private Integer transferSettingId;

	/**
	 * Default constructor.
	 */
	public MoneyTransferCriteria()
	{
		super();
	}

	/**
	 * Gets the member.
	 *
	 * @return the member
	 */
	public Member getMember()
	{
		return member;
	}

	/**
	 * Sets the member.
	 *
	 * @param member the member to set
	 */
	public void setMember(Member member)
	{
		this.member = member;
	}

	/**
	 * Gets the location name.
	 *
	 * @return the locationName
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * Sets the location name.
	 *
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

	/**
	 * Gets the organization name.
	 *
	 * @return the organizationName
	 */
	public String getOrganizationName()
	{
		return organizationName;
	}

	/**
	 * Sets the organization name.
	 *
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName)
	{
		this.organizationName = organizationName;
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return the transactionId
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	/**
	 * Gets the confirmation number.
	 *
	 * @return the confirmationNumber
	 */
	public String getConfirmationNumber()
	{
		return confirmationNumber;
	}

	/**
	 * Sets the confirmation number.
	 *
	 * @param confirmationNumber the confirmationNumber to set
	 */
	public void setConfirmationNumber(String confirmationNumber)
	{
		this.confirmationNumber = confirmationNumber;
	}

	/**
	 * Gets the currency code.
	 *
	 * @return the currencyCode
	 */
	public String getCurrencyCode()
	{
		return currencyCode;
	}

	/**
	 * Sets the currency code.
	 *
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode)
	{
		this.currencyCode = currencyCode;
	}

	/**
	 * Get the SendSolv id of the payer.
	 *
	 * @return The id.
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * Set the SendSolv id of the payer.
	 *
	 * @param payerId The id to set.
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/**
	 * Gets the status list.
	 *
	 * @return the statusList
	 */
	public List<MoneyTransferStatusEnum> getStatusList()
	{
		return statusList;
	}

	/**
	 * Sets the status list.
	 *
	 * @param statusList the statusList to set
	 */
	public void setStatusList(List<MoneyTransferStatusEnum> statusList)
	{
		this.statusList = statusList;
	}

	/**
	 * Gets the money transfer batch id.
	 *
	 * @return the moneyTransferBatchId
	 */
	public Integer getMoneyTransferBatchId()
	{
		return moneyTransferBatchId;
	}

	/**
	 * Sets the money transfer batch id.
	 *
	 * @param moneyTransferBatchId the moneyTransferBatchId to set
	 */
	public void setMoneyTransferBatchId(Integer moneyTransferBatchId)
	{
		this.moneyTransferBatchId = moneyTransferBatchId;
	}

	/**
	 * Gets the primary phone number.
	 *
	 * @return the primaryPhoneNumber
	 */
	public String getPrimaryPhoneNumber()
	{
		return primaryPhoneNumber;
	}

	/**
	 * Sets the primary phone number.
	 *
	 * @param primaryPhoneNumber the primaryPhoneNumber to set
	 */
	public void setPrimaryPhoneNumber(String primaryPhoneNumber)
	{
		this.primaryPhoneNumber = primaryPhoneNumber;
	}

	/**
	 * @return the recipientId
	 */
	public Integer getRecipientId()
	{
		return recipientId;
	}

	/**
	 * @param recipientId the recipientId to set
	 */
	public void setRecipientId(Integer recipientId)
	{
		this.recipientId = recipientId;
	}

	/**
	 * @return the createDateUTC
	 */
	public Long getCreateDateUTC()
	{
		return createDateUTC;
	}

	/**
	 * @param createDateUTC the createDateUTC to set
	 */
	public void setCreateDateUTC(Long createDateUTC)
	{
		this.createDateUTC = createDateUTC;
	}

	/**
	 * @return the transferSettingId
	 */
	public Integer getTransferSettingId()
	{
		return transferSettingId;
	}

	/**
	 * @param transferSettingId the transferSettingId to set
	 */
	public void setTransferSettingId(Integer transferSettingId)
	{
		this.transferSettingId = transferSettingId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferCriteria [getMember()=" + getMember() + ", getLocationName()=" + getLocationName()
				+ ", getOrganizationName()=" + getOrganizationName() + ", getTransactionId()=" + getTransactionId()
				+ ", getConfirmationNumber()=" + getConfirmationNumber() + ", getCurrencyCode()=" + getCurrencyCode()
				+ ", getPayerId()=" + getPayerId() + ", getStatusList()=" + getStatusList()
				+ ", getMoneyTransferBatchId()=" + getMoneyTransferBatchId() + ", getPrimaryPhoneNumber()="
				+ getPrimaryPhoneNumber() + ", getRecipientId()=" + getRecipientId() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getTransferSettingId()=" + getTransferSettingId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
