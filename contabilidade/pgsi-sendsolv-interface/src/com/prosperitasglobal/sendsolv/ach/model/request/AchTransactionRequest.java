package com.prosperitasglobal.sendsolv.ach.model.request;

import com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction;
import com.qat.framework.model.request.Request;

/**
 * The class AchTransactionRequest.
 */
public class AchTransactionRequest extends Request
{
	/** The money transfer transaction. */
	private MoneyTransferTransaction moneyTransferTransaction;

	/**
	 * Get the money transfer transaction.
	 *
	 * @return The money transfer transaction.
	 */
	public MoneyTransferTransaction getMoneyTransferTransaction()
	{
		return moneyTransferTransaction;
	}

	/**
	 * Set the money transfer transaction.
	 *
	 * @param moneyTransferTransaction The money transfer transaction.
	 */
	public void setMoneyTransferTransaction(MoneyTransferTransaction moneyTransferTransaction)
	{
		this.moneyTransferTransaction = moneyTransferTransaction;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AchTransactionRequest [getMoneyTransferTransaction()=" + getMoneyTransferTransaction()
				+ ", getUserContext()=" + getUserContext() + "]";
	}

}
