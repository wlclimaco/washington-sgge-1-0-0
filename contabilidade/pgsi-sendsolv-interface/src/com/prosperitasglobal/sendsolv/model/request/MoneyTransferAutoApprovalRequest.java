package com.prosperitasglobal.sendsolv.model.request;


public class MoneyTransferAutoApprovalRequest extends Request
{
	/** The money transfer batch id. */
	private Integer moneyTransferBatchId;

	/** This transaction has the same amount as the previous transaction ?. */
	private Boolean isSameAmountPreviousTransaction;

	/** This transaction has the Net Pay's member greater than transfer value ?. */
	private Boolean isNetPayGreaterTransValue;

	/** Is previous transaction approved ?. */
	private Boolean isPreviousTransactionApproved;

	/**
	 * @return the moneyTransferBatchId
	 */
	public Integer getMoneyTransferBatchId()
	{
		return moneyTransferBatchId;
	}

	/**
	 * @param moneyTransferBatchId the moneyTransferBatchId to set
	 */
	public void setMoneyTransferBatchId(Integer moneyTransferBatchId)
	{
		this.moneyTransferBatchId = moneyTransferBatchId;
	}

	/**
	 * @return the isSameAmountPreviousTransaction
	 */
	public Boolean getIsSameAmountPreviousTransaction()
	{
		return isSameAmountPreviousTransaction;
	}

	/**
	 * @param isSameAmountPreviousTransaction the isSameAmountPreviousTransaction to set
	 */
	public void setIsSameAmountPreviousTransaction(Boolean isSameAmountPreviousTransaction)
	{
		this.isSameAmountPreviousTransaction = isSameAmountPreviousTransaction;
	}

	/**
	 * @return the isNetPayGreaterTransValue
	 */
	public Boolean getIsNetPayGreaterTransValue()
	{
		return isNetPayGreaterTransValue;
	}

	/**
	 * @param isNetPayGreaterTransValue the isNetPayGreaterTransValue to set
	 */
	public void setIsNetPayGreaterTransValue(Boolean isNetPayGreaterTransValue)
	{
		this.isNetPayGreaterTransValue = isNetPayGreaterTransValue;
	}

	/**
	 * @return the isPreviousTransactionApproved
	 */
	public Boolean getIsPreviousTransactionApproved()
	{
		return isPreviousTransactionApproved;
	}

	/**
	 * @param isPreviousTransactionApproved the isPreviousTransactionApproved to set
	 */
	public void setIsPreviousTransactionApproved(Boolean isPreviousTransactionApproved)
	{
		this.isPreviousTransactionApproved = isPreviousTransactionApproved;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferAutoApprovalRequest [getMoneyTransferBatchId()=" + getMoneyTransferBatchId()
				+ ", getIsSameAmountPreviousTransaction()=" + getIsSameAmountPreviousTransaction()
				+ ", getIsNetPayGreaterTransValue()=" + getIsNetPayGreaterTransValue()
				+ ", getIsPreviousTransactionApproved()=" + getIsPreviousTransactionApproved() + ", getUserContext()="
				+ getUserContext() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
