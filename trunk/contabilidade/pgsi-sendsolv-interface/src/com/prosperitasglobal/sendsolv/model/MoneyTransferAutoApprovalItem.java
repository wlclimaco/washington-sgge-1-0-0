package com.prosperitasglobal.sendsolv.model;

public class MoneyTransferAutoApprovalItem
{
	/** The Money Transfer Id. */
	private Integer Id;

	/** This transaction has the same amount as the previous transaction ?. */
	private Boolean isSameAmountPreviousTransaction;

	/** This transaction has the Net Pay's member greater than transfer value ?. */
	private Boolean isNetPayGreaterTransValue;

	/** Is previous transaction approved ?. */
	private Boolean isPreviousTransactionApproved;

	public MoneyTransferAutoApprovalItem()
	{
		Id = 0;
		isNetPayGreaterTransValue = Boolean.FALSE;
		isSameAmountPreviousTransaction = Boolean.FALSE;
		isPreviousTransactionApproved = Boolean.FALSE;
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		Id = id;
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
		return "MoneyTransferAutoApprovalItem [getId()=" + getId() + ", getIsSameAmountPreviousTransaction()="
				+ getIsSameAmountPreviousTransaction() + ", getIsNetPayGreaterTransValue()="
				+ getIsNetPayGreaterTransValue() + ", getIsPreviousTransactionApproved()="
				+ getIsPreviousTransactionApproved() + "]";
	}

}
