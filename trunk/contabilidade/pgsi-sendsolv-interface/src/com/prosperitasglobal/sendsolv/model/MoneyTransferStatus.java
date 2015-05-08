package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.sendsolv.model.response.MoneyTransferProcessingResponse;
import com.qat.framework.model.QATModel;
import com.qat.framework.validation.ValidationUtil;

/**
 * This class is a representation of a Money Transfer status.
 */
@SuppressWarnings("serial")
public class MoneyTransferStatus extends QATModel
{
	/** The SendSolv id for the money transfer transaction status. */
	private Integer id;

	/** The SendSolv id for the money transfer. */
	private Integer moneyTransferId;

	/** The status of the money transfer. */
	private MoneyTransferStatusEnum status;

	/** The response from processing a money transfer. */
	private MoneyTransferProcessingResponse response;

	/** The {@link MoneyTransferTransaction} sent to automated clearing house. */
	private MoneyTransferTransaction moneyTransferTransaction;

	/**
	 * Default constructor.
	 */
	public MoneyTransferStatus()
	{
		super();
	}

	/**
	 * Get the SendSolv id for the money transfer status.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the money transfer status.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the SendSolv id for the money transfer.
	 *
	 * @return The id.
	 */
	public Integer getMoneyTransferId()
	{
		return moneyTransferId;
	}

	/**
	 * Set the SendSolv id for the money transfer.
	 *
	 * @param moneyTransferId The id to set.
	 */
	public void setMoneyTransferId(Integer moneyTransferId)
	{
		this.moneyTransferId = moneyTransferId;
	}

	/**
	 * Get the {@link MoneyTransferTransaction} sent to automated clearing house.
	 *
	 * @return The {@link MoneyTransferTransaction}.
	 */
	public MoneyTransferTransaction getMoneyTransferTransaction()
	{
		return moneyTransferTransaction;
	}

	/**
	 * Set the {@link MoneyTransferTransaction} sent to automated clearing house.
	 *
	 * @param moneyTransferTransaction The {@link MoneyTransferTransaction} to set.
	 */
	public void setMoneyTransferTransaction(MoneyTransferTransaction moneyTransferTransaction)
	{
		this.moneyTransferTransaction = moneyTransferTransaction;
	}

	/**
	 * Get the response for processing the money transfer.
	 *
	 * @return The response.
	 */
	public MoneyTransferProcessingResponse getResponse()
	{
		return response;
	}

	/**
	 * Get the response for processing the money transfer.
	 *
	 * @return The response.
	 */
	public String getResponseString()
	{
		if (ValidationUtil.isNull(getResponse()))
		{
			return null;
		}

		return getResponse().toJsonString();
	}

	/**
	 * Set the response for processing the money transfer.
	 *
	 * @param response The response to set.
	 */
	public void setResponse(MoneyTransferProcessingResponse response)
	{
		this.response = response;
	}

	/**
	 * Set the response for processing the money transfer.
	 *
	 * @param response The response to set.
	 */
	public void setResponseString(String response)
	{
		if (ValidationUtil.isNull(response))
		{
			setResponse(null);
		}

		setResponse(MoneyTransferProcessingResponse.fromJsonString(response));
	}

	/**
	 * Get the status of the money transfer.
	 *
	 * @return The status.
	 */
	public MoneyTransferStatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Get the enumeration value for the status of the money transfer.
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
	 * Set the status of the money transfer.
	 *
	 * @param status The status to set.
	 */
	public void setStatus(MoneyTransferStatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Set the enumeration by the value of the status of the money transfer.
	 *
	 * @param statusValue The value of the status enumeration to set.
	 */
	public void setStatusValue(Integer statusValue)
	{
		setStatus(MoneyTransferStatusEnum.enumForValue(statusValue));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferStatus [getId()=" + getId() + ", getMoneyTransferId()=" + getMoneyTransferId()
				+ ", getMoneyTransferTransaction()=" + getMoneyTransferTransaction() + ", getResponse()="
				+ getResponse() + ", getResponseString()=" + getResponseString() + ", getStatus()=" + getStatus()
				+ ", getStatusValue()=" + getStatusValue() + ", toString()=" + super.toString() + "]";
	}
}
