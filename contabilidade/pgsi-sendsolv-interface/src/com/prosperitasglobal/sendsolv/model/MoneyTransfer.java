package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.Note;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * This class is a representation of a Money Transfer.
 */
@SuppressWarnings("serial")
public class MoneyTransfer extends QATModelOL
{
	/** The SendSolv id for the money transfer. */
	private Integer id;

	/** The SendSolv key for the money transfer. */
	private String key;

	/** The sender account to use for the money transfer. */
	private Account senderAccount;

	/** The recipient account to use for the money transfer. */
	private Account recipientAccount;

	/** The amount that will be transferred to the destination. */
	private MoneyTransferAmount destinationAmount;

	/** The SendSolv discount amount. */
	private BigDecimal discountAmount;

	/** The SendSolv origin flat fee. */
	private BigDecimal originFlatFee;

	/** The SendSolv origin automated clearing house fee. */
	private BigDecimal originAutomatedClearingHouseFee;

	/** The SendSolv origin call credit amount. */
	private BigDecimal originCallCreditAmount;

	/** The effective foreign exchange rate used for the transfer. */
	private BigDecimal foreignExchangeRate;

	/** The customer foreign exchange rate used for the transfer. */
	private BigDecimal foreignExchangeRateCustomer;

	/** The SendSolv id of the money transfer batch that this transfer is associated with. */
	private Integer moneyTransferBatchId;

	/** The associated objects that will be used to create the money transfer. */
	private MoneyTransferDetail moneyTransferDetail;

	/** The list of notes associated with the money transfer. */
	private List<Note> noteList;

	/** The amount that will be transferred from the origin. */
	private MoneyTransferAmount originAmount;

	/** The payment type of the money transfer. */
	private PaymentTypeEnum paymentType;

	/** The spread percentage. */
	private BigDecimal spreadPercentage;

	/** The historical list of statuses for the money transfer. */
	private List<MoneyTransferStatus> statusList;

	/** The transfer setting used to create the money transfer. */
	private TransferSetting transferSetting;

	/** The external number from an external automated clearing house. */
	private String confirmationNumber;

	/** The date the money transfer was funded. */
	private Long fundingDate;

	/** The user that approved the transfer. */
	private String releaseUser;

	/**
	 * Add a MoneyTransferStatus to the list of MoneyTransferStatuses
	 *
	 * @param status The status to add.
	 */
	public void addMoneyTransferStatus(MoneyTransferStatus status)
	{
		if (getStatusList() == null)
		{
			setStatusList(new ArrayList<MoneyTransferStatus>());
		}
		getStatusList().add(status);
	}

	/**
	 * Add a Note to the list of Notes
	 *
	 * @param note The note to add.
	 */
	public void addNote(Note note)
	{
		if (getNoteList() == null)
		{
			setNoteList(new ArrayList<Note>());
		}
		getNoteList().add(note);
	}

	/**
	 * Get the SendSolv id for the money transfer.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the money transfer.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * Get the date the money transfer was funded.
	 *
	 * @return The date.
	 */
	public Long getFundingDate()
	{
		return fundingDate;
	}

	/**
	 * Set the date the money transfer was funded.
	 *
	 * @param fundingDate The date to set.
	 */
	public void setFundingDate(Long fundingDate)
	{
		this.fundingDate = fundingDate;
	}

	/**
	 * Get the current status of the money transfer. Convenience method for getting the most current status out of the
	 * status list.
	 *
	 * @return The current status.
	 */
	public MoneyTransferStatus getCurrentStatus()
	{
		if (ValidationUtil.isNullOrEmpty(getStatusList()))
		{
			return null;
		}

		MoneyTransferStatus currentStatus = null;

		for (MoneyTransferStatus moneyTransferStatus : getStatusList())
		{
			if (ValidationUtil.isNull(currentStatus)
					|| (moneyTransferStatus.getCreateDateUTC() >= currentStatus.getCreateDateUTC()))
			{
				currentStatus = moneyTransferStatus;
			}
		}

		return currentStatus;
	}

	/**
	 * Get the sender account to use for the transfer.
	 *
	 * @return The account.
	 */
	public Account getSenderAccount()
	{
		return senderAccount;
	}

	/**
	 * Set the account to use for the transfer.
	 *
	 * @param senderAccount The account to set.
	 */
	public void setSenderAccount(Account senderAccount)
	{
		this.senderAccount = senderAccount;
	}

	/**
	 * Get the recipient account to use for the transfer.
	 *
	 * @return The account.
	 */
	public Account getRecipientAccount()
	{
		return recipientAccount;
	}

	/**
	 * Set the recipient account to use for the transfer.
	 *
	 * @param recipientAccount The account to set.
	 */
	public void setRecipientAccount(Account recipientAccount)
	{
		this.recipientAccount = recipientAccount;
	}

	/**
	 * Get the amount that will be transferred to the destination.
	 *
	 * @return The amount.
	 */
	public MoneyTransferAmount getDestinationAmount()
	{
		return destinationAmount;
	}

	/**
	 * Set the amount that will be transferred to the destination.
	 *
	 * @param destinationAmount The amount to set.
	 */
	public void setDestinationAmount(MoneyTransferAmount destinationAmount)
	{
		this.destinationAmount = destinationAmount;
	}

	/**
	 * Get the SendSolv discount amount.
	 *
	 * @return The amount.
	 */
	public BigDecimal getDiscountAmount()
	{
		return discountAmount;
	}

	/**
	 * Set the SendSolv discount amount.
	 *
	 * @param discountAmount The amount to set.
	 */
	public void setDiscountAmount(BigDecimal discountAmount)
	{
		this.discountAmount = discountAmount;
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

	/**
	 * Get the SendSolv origin automated clearing house fee.
	 *
	 * @return The fee.
	 */
	public BigDecimal getOriginAutomatedClearingHouseFee()
	{
		return originAutomatedClearingHouseFee;
	}

	/**
	 * Set the SendSolv origin automated clearing house fee.
	 *
	 * @param originAutomatedClearingHouseFee The fee to set.
	 */
	public void setOriginAutomatedClearingHouseFee(BigDecimal originAutomatedClearingHouseFee)
	{
		this.originAutomatedClearingHouseFee = originAutomatedClearingHouseFee;
	}

	/**
	 * Get the SendSolv origin call credit amount.
	 *
	 * @return The amount.
	 */
	public BigDecimal getOriginCallCreditAmount()
	{
		return originCallCreditAmount;
	}

	/**
	 * Set the SendSolv origin call credit amount.
	 *
	 * @param originCallCreditAmount The amount to set.
	 */
	public void setOriginCallCreditAmount(BigDecimal originCallCreditAmount)
	{
		this.originCallCreditAmount = originCallCreditAmount;
	}

	/**
	 * Get the effective foreign exchange rate used for the transfer.
	 *
	 * @return The rate.
	 */
	public BigDecimal getForeignExchangeRate()
	{
		return foreignExchangeRate;
	}

	/**
	 * Set the effective foreign exchange rate used for the transfer.
	 *
	 * @param foreignExchangeRate The rate to set.
	 */
	public void setForeignExchangeRate(BigDecimal foreignExchangeRate)
	{
		this.foreignExchangeRate = foreignExchangeRate;
	}

	/**
	 * Get the customer effective foreign exchange rate used for the transfer.
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
	 * Get the SendSolv id of the money transfer batch that this transfer is associated with.
	 *
	 * @return The id.
	 */
	public Integer getMoneyTransferBatchId()
	{
		return moneyTransferBatchId;
	}

	/**
	 * Set the SendSolv id of the money transfer batch that this transfer is associated with.
	 *
	 * @param moneyTransferBatchId The id to set.
	 */
	public void setMoneyTransferBatchId(Integer moneyTransferBatchId)
	{
		this.moneyTransferBatchId = moneyTransferBatchId;
	}

	/**
	 * Get the associated objects that will be used to create the money transfer.
	 *
	 * @return The money transfer details.
	 */
	public MoneyTransferDetail getMoneyTransferDetail()
	{
		return moneyTransferDetail;
	}

	/**
	 * Set the associated objects that will be used to create the money transfer.
	 *
	 * @param moneyTransferDetail The money transfer detail to set.
	 */
	public void setMoneyTransferDetail(MoneyTransferDetail moneyTransferDetail)
	{
		this.moneyTransferDetail = moneyTransferDetail;
	}

	/**
	 * Get the list of {@link Note}'s.
	 *
	 * @return The list.
	 */
	public List<Note> getNoteList()
	{
		if (ValidationUtil.isNull(noteList))
		{
			setNoteList(new ArrayList<Note>());
		}

		return noteList;
	}

	/**
	 * Set the list of {@link Note}'s.
	 *
	 * @param noteList The list to set.
	 */
	public void setNoteList(List<Note> noteList)
	{
		this.noteList = noteList;
	}

	/**
	 * Get the amount that will be transferred from the origin.
	 *
	 * @return The amount.
	 */
	public MoneyTransferAmount getOriginAmount()
	{
		return originAmount;
	}

	/**
	 * Set the amount that will be transferred from the origin.
	 *
	 * @param originAmount The amount to set.
	 */
	public void setOriginAmount(MoneyTransferAmount originAmount)
	{
		this.originAmount = originAmount;
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
	 * Get the spread percentage.
	 *
	 * @return The percentage.
	 */
	public BigDecimal getSpreadPercentage()
	{
		return spreadPercentage;
	}

	/**
	 * Set the spread percentage.
	 *
	 * @param spreadPercentage The percentage to set.
	 */
	public void setSpreadPercentage(BigDecimal spreadPercentage)
	{
		this.spreadPercentage = spreadPercentage;
	}

	/**
	 * Get the list of {@link MoneyTransferStatus}.
	 *
	 * @return The list.
	 */
	public List<MoneyTransferStatus> getStatusList()
	{
		if (ValidationUtil.isNull(statusList))
		{
			setStatusList(new ArrayList<MoneyTransferStatus>());
		}

		return statusList;
	}

	/**
	 * Set the list of {@link MoneyTransferStatus}.
	 *
	 * @param statusList The list to set.
	 */
	public void setStatusList(List<MoneyTransferStatus> statusList)
	{
		this.statusList = statusList;
	}

	/**
	 * Get the transfer setting used to create the money transfer.
	 *
	 * @return The transfer setting.
	 */
	public TransferSetting getTransferSetting()
	{
		return transferSetting;
	}

	/**
	 * Set Get the transfer setting used to create the money transfer.
	 *
	 * @param transferSetting The transfer setting to set.
	 */
	public void setTransferSetting(TransferSetting transferSetting)
	{
		this.transferSetting = transferSetting;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransfer [getId()=" + getId() + ", getConfirmationNumber()=" + getConfirmationNumber()
				+ ", getFundingDate()=" + getFundingDate() + ", getCurrentStatus()=" + getCurrentStatus()
				+ ", getSenderAccount()=" + getSenderAccount() + ", getRecipientAccount()=" + getRecipientAccount()
				+ ", getDestinationAmount()=" + getDestinationAmount() + ", getDiscountAmount()=" + getDiscountAmount()
				+ ", getKey()=" + getKey() + ", getOriginFlatFee()=" + getOriginFlatFee()
				+ ", getOriginAutomatedClearingHouseFee()=" + getOriginAutomatedClearingHouseFee()
				+ ", getOriginCallCreditAmount()=" + getOriginCallCreditAmount() + ", getForeignExchangeRate()="
				+ getForeignExchangeRate() + ", getForeignExchangeRateCustomer()=" + getForeignExchangeRateCustomer()
				+ ", getMoneyTransferBatchId()=" + getMoneyTransferBatchId() + ", getMoneyTransferDetail()="
				+ getMoneyTransferDetail() + ", getNoteList()=" + getNoteList() + ", getOriginAmount()="
				+ getOriginAmount() + ", getPaymentType()=" + getPaymentType() + ", getPaymentTypeValue()="
				+ getPaymentTypeValue() + ", getReleaseUser()=" + getReleaseUser() + ", getSpreadPercentage()="
				+ getSpreadPercentage() + ", getStatusList()=" + getStatusList() + ", getTransferSetting()="
				+ getTransferSetting() + ", toString()=" + super.toString() + "]";
	}
}
