package com.prosperitasglobal.sendsolv.ach.model;

import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse;
import com.qat.framework.model.QATModel;

/**
 * The AchStatus class.
 */
@SuppressWarnings("serial")
public class AchStatus extends QATModel
{
	/** The identifier used by the automated clearing house. */
	private String achIdentifier;

	/** The order number used by the automated clearing house. */
	private String achOrderNumber;

	/** The notification id of the automated clearing house. */
	private String achNotificationId;

	/** The operation code of the automated clearing house. */
	private String achOperationCode;

	/** The SendSolv money transfer status. */
	private MoneyTransferStatusEnum moneyTransferStatus;

	/** The SendSolv response. */
	private MoneyTransferProcessingResponse achResponse;

	/**
	 * Get the order number used by the automated clearing house.
	 *
	 * @return The order number.
	 */
	public String getAchOrderNumber()
	{
		return achOrderNumber;
	}

	/**
	 * Set the order number used by the automated clearing house.
	 *
	 * @param achOrderNumber The order number to set.
	 */
	public void setAchOrderNumber(String achOrderNumber)
	{
		this.achOrderNumber = achOrderNumber;
	}

	/**
	 * Get the notification id of the automated clearing house.
	 *
	 * @return The id.
	 */
	public String getAchNotificationId()
	{
		return achNotificationId;
	}

	/**
	 * Set the notification id of the automated clearing house.
	 *
	 * @param achNotificationId The id to set.
	 */
	public void setAchNotificationId(String achNotificationId)
	{
		this.achNotificationId = achNotificationId;
	}

	/**
	 * Get the operation code of the automated clearing house.
	 *
	 * @return The code.
	 */
	public String getAchOperationCode()
	{
		return achOperationCode;
	}

	/**
	 * Set the operation code of the automated clearing house.
	 *
	 * @param achOperationCode The code to set.
	 */
	public void setAchOperationCode(String achOperationCode)
	{
		this.achOperationCode = achOperationCode;
	}

	/**
	 * Get the SendSolv money transfer status.
	 *
	 * @return The status.
	 */
	public MoneyTransferStatusEnum getMoneyTransferStatus()
	{
		return moneyTransferStatus;
	}

	/**
	 * Set the SendSolv money transfer status.
	 *
	 * @param moneyTransferStatus The status to set.
	 */
	public void setMoneyTransferStatus(MoneyTransferStatusEnum moneyTransferStatus)
	{
		this.moneyTransferStatus = moneyTransferStatus;
	}

	/**
	 * Get the identifier used by the automated clearing house.
	 *
	 * @return The identifier.
	 */
	public String getAchIdentifier()
	{
		return achIdentifier;
	}

	/**
	 * Set the identifier used by the automated clearing house.
	 *
	 * @param achIdentifier The identifier to set.
	 */
	public void setAchIdentifier(String achIdentifier)
	{
		this.achIdentifier = achIdentifier;
	}

	/**
	 * Get the SendSolv response.
	 *
	 * @return The response
	 */
	public MoneyTransferProcessingResponse getAchResponse()
	{
		return achResponse;
	}

	/**
	 * Set the SendSolv response.
	 *
	 * @param achResponse The response to set.
	 */
	public void setAchResponse(MoneyTransferProcessingResponse achResponse)
	{
		this.achResponse = achResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AchStatus [getAchOrderNumber()=" + getAchOrderNumber() + ", getAchNotificationId()="
				+ getAchNotificationId() + ", getAchOperationCode()=" + getAchOperationCode()
				+ ", getMoneyTransferStatus()=" + getMoneyTransferStatus() + ", getAchIdentifier()="
				+ getAchIdentifier() + ", getAchResponse()=" + getAchResponse() + ", toString()=" + super.toString()
				+ "]";
	}

}
