package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class represents a status of a batch of {@link MoneyTransfer}'s.
 */
@SuppressWarnings("serial")
public class MoneyTransferBatchStatus extends QATModel
{
	/** The SendSolv id for the money transfer batch status. */
	private Integer id;

	/** The SendSolv id for the money transfer batch. */
	private Integer moneyTransferBatchId;

	/** The action due date for the money transfer batch status. */
	private Long actionDueDate;

	/** The status of the money transfer batch. */
	private MoneyTransferBatchStatusEnum status;

	/**
	 * Default constructor.
	 */
	public MoneyTransferBatchStatus()
	{
		super();
	}

	/**
	 * Get the SendSolv id for the money transfer batch status.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the money transfer batch status.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the SendSolv id for the money transfer batch.
	 *
	 * @return The id.
	 */
	public Integer getMoneyTransferBatchId()
	{
		return moneyTransferBatchId;
	}

	/**
	 * Set the SendSolv id for the money transfer batch.
	 *
	 * @param moneyTransferBatchId The id to set.
	 */
	public void setMoneyTransferBatchId(Integer moneyTransferBatchId)
	{
		this.moneyTransferBatchId = moneyTransferBatchId;
	}

	/**
	 * Get the action due date for the money transfer batch status. If this date has a time portion, it will be removed.
	 * This attribute is only a date.
	 *
	 * @return The due date.
	 */
	public Long getActionDueDate()
	{
		return actionDueDate;
	}

	/**
	 * Set the action due date for the money transfer batch status.
	 *
	 * @param actionDueDate The due date to set.
	 */
	public void setActionDueDate(Long actionDueDate)
	{
		this.actionDueDate = actionDueDate;
	}

	/**
	 * Get the status of the money transfer batch.
	 *
	 * @return The status.
	 */
	public MoneyTransferBatchStatusEnum getStatus()
	{
		return status;
	}

	/**
	 * Get the enumeration value for the status of the money transfer batch.
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
	 * Set the status of the money transfer batch.
	 *
	 * @param status The status to set.
	 */
	public void setStatus(MoneyTransferBatchStatusEnum status)
	{
		this.status = status;
	}

	/**
	 * Set the enumeration by the value of the status of the money transfer batch.
	 *
	 * @param statusValue The value of the status enumeration to set.
	 */
	public void setStatusValue(Integer statusValue)
	{
		setStatus(MoneyTransferBatchStatusEnum.enumForValue(statusValue));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchStatus [getId()=" + getId() + ", getMoneyTransferBatchId()="
				+ getMoneyTransferBatchId() + ", getActionDueDate()=" + getActionDueDate() + ", getStatus()="
				+ getStatus() + ", getStatusValue()=" + getStatusValue() + ", toString()=" + super.toString() + "]";
	}
}
