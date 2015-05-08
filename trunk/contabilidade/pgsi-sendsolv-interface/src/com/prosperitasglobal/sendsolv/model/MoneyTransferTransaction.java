package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of a Money Transfer Transaction that was sent to an automated clearing house.
 */
@SuppressWarnings("serial")
public class MoneyTransferTransaction extends QATModel
{
	/** The SendSolv id for the money transfer transaction. */
	private Integer id;

	/** The SendSolv key for the money transfer. */
	private String key;

	/** The SendSolv id for the money transfer status associated with the transaction. */
	private Integer moneyTransferStatusId;

	/** The external number from an external automated clearing house. */
	private String confirmationNumber;

	/** The sender of the money transfer. */
	private MoneyTransferParticipant sender;

	/** The receiver of the money transfer. */
	private MoneyTransferParticipant recipient;

	/** The amount that will be transferred from the origin. */
	private MoneyTransferAmount originAmount;

	/** The amount that will be transferred to the destination. */
	private MoneyTransferAmount destinationAmount;

	/** The customer foreign exchange rate used for the transfer. */
	private BigDecimal foreignExchangeRateCustomer;

	/** The automated clearing house payee code. */
	private String achPayeeCode;

	/** The payment type of the money transfer. */
	private PaymentTypeEnum paymentType;

	/** The user that approved the transfer. */
	private String releaseUser;

	/** The SendSolv origin flat fee. */
	private BigDecimal originFlatFee;

	/**
	 * Get the SendSolv id for the money transfer transaction.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the money transfer transaction.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the SendSolv key for the money transfer.
	 *
	 * @return The key.
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * Set the SendSolv key for the money transfer.
	 *
	 * @param key The key to set.
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * Get the SendSolv id for the money transfer status associated with the transaction.
	 *
	 * @return The id.
	 */
	public Integer getMoneyTransferStatusId()
	{
		return moneyTransferStatusId;
	}

	/**
	 * Set the SendSolv id for the money transfer status associated with the transaction.
	 *
	 * @param moneyTransferStatusId The money transfer status id to set.
	 */
	public void setMoneyTransferStatusId(Integer moneyTransferStatusId)
	{
		this.moneyTransferStatusId = moneyTransferStatusId;
	}

	/**
	 * Get the external number from an external automated clearing house.
	 *
	 * @return The confirmation number.
	 */
	public String getConfirmationNumber()
	{
		return confirmationNumber;
	}

	/**
	 * Set the external number from an external automated clearing house.
	 *
	 * @param confirmationNumber The confirmation number to set.
	 */
	public void setConfirmationNumber(String confirmationNumber)
	{
		this.confirmationNumber = confirmationNumber;
	}

	/**
	 * Get the sender of the money transfer.
	 *
	 * @return The sender.
	 */
	public MoneyTransferParticipant getSender()
	{
		return sender;
	}

	/**
	 * Set the sender of the money transfer.
	 *
	 * @param sender The sender to set.
	 */
	public void setSender(MoneyTransferParticipant sender)
	{
		this.sender = sender;
	}

	/**
	 * Get the recipient of the money transfer.
	 *
	 * @return The recipient.
	 */
	public MoneyTransferParticipant getRecipient()
	{
		return recipient;
	}

	/**
	 * Set the recipient of the money transfer.
	 *
	 * @param recipient The recipient to set.
	 */
	public void setRecipient(MoneyTransferParticipant recipient)
	{
		this.recipient = recipient;
	}

	/**
	 * Get the origin amount of the money transfer.
	 *
	 * @return The origin amount.
	 */
	public MoneyTransferAmount getOriginAmount()
	{
		return originAmount;
	}

	/**
	 * Set the origin amount of the money transfer.
	 *
	 * @param originAmount The origin amount to set.
	 */
	public void setOriginAmount(MoneyTransferAmount originAmount)
	{
		this.originAmount = originAmount;
	}

	/**
	 * Get the destination amount of the money transfer.
	 *
	 * @return The destination amount.
	 */
	public MoneyTransferAmount getDestinationAmount()
	{
		return destinationAmount;
	}

	/**
	 * Set the destination amount of the money transfer.
	 *
	 * @param destinationAmount The destination amount to set.
	 */
	public void setDestinationAmount(MoneyTransferAmount destinationAmount)
	{
		this.destinationAmount = destinationAmount;
	}

	/**
	 * Get the customer foreign exchange rate used for the transfer.
	 *
	 * @return The rate.
	 */
	public BigDecimal getForeignExchangeRateCustomer()
	{
		return foreignExchangeRateCustomer;
	}

	/**
	 * Set the customer foreign exchange rate used for the transfer.
	 *
	 * @param foreignExchangeRateCustomer The rate to set.
	 */
	public void setForeignExchangeRateCustomer(BigDecimal foreignExchangeRateCustomer)
	{
		this.foreignExchangeRateCustomer = foreignExchangeRateCustomer;
	}

	/**
	 * Get the automated clearing house payee code.
	 *
	 * @return The automated clearing house payee code.
	 */
	public String getAchPayeeCode()
	{
		return achPayeeCode;
	}

	/**
	 * Set the automated clearing house payee code.
	 *
	 * @param achPayeeCode The automated clearing house payee code to set.
	 */
	public void setAchPayeeCode(String achPayeeCode)
	{
		this.achPayeeCode = achPayeeCode;
	}

	/**
	 * Get the payment type.
	 *
	 * @return The payment type.
	 */
	public PaymentTypeEnum getPaymentType()
	{
		return paymentType;
	}

	/**
	 * Get the payment type value.
	 *
	 * @return The payment type value.
	 */
	public Integer getPaymentTypeValue()
	{
		if (getPaymentType() == null)
		{
			return null;
		}

		return paymentType.getValue();
	}

	/**
	 * Set the payment type.
	 *
	 * @param paymentType The payment type to set.
	 */
	public void setPaymentType(PaymentTypeEnum paymentType)
	{
		this.paymentType = paymentType;
	}

	/**
	 * Set the payment type by value.
	 *
	 * @param paymentTypeValue The payment type value to set.
	 */
	public void setPaymentTypeValue(Integer paymentTypeValue)
	{
		paymentType = PaymentTypeEnum.enumForValue(paymentTypeValue);
	}

	/**
	 * Get the release user.
	 *
	 * @return The release user.
	 */
	public String getReleaseUser()
	{
		return releaseUser;
	}

	/**
	 * Set the release user.
	 *
	 * @param releaseUser The release user.
	 */
	public void setReleaseUser(String releaseUser)
	{
		this.releaseUser = releaseUser;
	}

	/**
	 * Get the SendSolv origin flat fee.
	 *
	 * @return The fee.
	 */
	public BigDecimal getOriginFlatFee()
	{
		return originFlatFee;
	}

	/**
	 * Set the SendSolv origin flat fee.
	 *
	 * @param originflatFee The fee to set.
	 */
	public void setOriginFlatFee(BigDecimal originflatFee)
	{
		originFlatFee = originflatFee;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferTransaction [getId()=" + getId() + ", getKey()=" + getKey()
				+ ", getMoneyTransferStatusId()=" + getMoneyTransferStatusId() + ", getConfirmationNumber()="
				+ getConfirmationNumber() + ", getSender()=" + getSender() + ", getRecipient()=" + getRecipient()
				+ ", getOriginAmount()=" + getOriginAmount() + ", getDestinationAmount()=" + getDestinationAmount()
				+ ", getForeignExchangeRateCustomer()=" + getForeignExchangeRateCustomer() + ", getAchPayeeCode()="
				+ getAchPayeeCode() + ", getPaymentType()=" + getPaymentType() + ", getPaymentTypeValue()="
				+ getPaymentTypeValue() + ", getReleaseUser()=" + getReleaseUser() + ", getOriginFlatFee()="
				+ getOriginFlatFee() + ", toString()=" + super.toString() + "]";
	}
}
