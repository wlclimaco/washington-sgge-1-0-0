package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;

/**
 * The Class MoneyTransferBatchMaintenanceRequest.
 */
public class MoneyTransferBatchMaintenanceRequest extends MaintenanceRequest
{
	/** The money transfer batch. */
	private MoneyTransferBatch moneyTransferBatch;

	/**
	 * Get the money transfer batch.
	 *
	 * @return The money transfer batch.
	 */
	public MoneyTransferBatch getMoneyTransferBatch()
	{
		return moneyTransferBatch;
	}

	/**
	 * Set the money transfer batch.
	 *
	 * @param moneyTransferBatch The money transfer batch to set.
	 */
	public void setMoneyTransferBatch(MoneyTransferBatch moneyTransferBatch)
	{
		this.moneyTransferBatch = moneyTransferBatch;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchMaintenanceRequest [getMoneyTransferBatch()=" + getMoneyTransferBatch()
				+ ", getUserContext()=" + getUserContext() + "]";
	}
}
