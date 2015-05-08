package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.qat.framework.model.QATModelOL;

/**
 * This class is a representation of a Currency Allocation of a Purchased Currency on the open exchange.
 *
 * @deprecated Not needed for the SendSolv implementation.
 */
@Deprecated
@SuppressWarnings("serial")
public class CurrencyAllocation extends QATModelOL
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The amount allocated toward a currency purchase. */
	private BigDecimal allocatedAmount;

	/** The SendSolv id for the currency purchase associated with the allocation. */
	private Integer currencyPurchaseId;

	/** The SendSolv id for the money transfer associated with the allocation. */
	private Integer moneyTransferId;

	/**
	 * Default constructor.
	 */
	public CurrencyAllocation()
	{
		super();
	}

	/**
	 * Get the SendSolv id for the account.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the account.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the amount allocated toward a currency purchase.
	 *
	 * @return The amount.
	 */
	public BigDecimal getAllocatedAmount()
	{
		return allocatedAmount;
	}

	/**
	 * Set the amount allocated toward a currency purchase.
	 *
	 * @param allocatedAmount The amount allocated toward a purchase.
	 */
	public void setAllocatedAmount(BigDecimal allocatedAmount)
	{
		this.allocatedAmount = allocatedAmount;
	}

	/**
	 * Get the SendSolv id for the currency purchase associated with the allocation.
	 *
	 * @return The id.
	 */
	public Integer getCurrencyPurchaseId()
	{
		return currencyPurchaseId;
	}

	/**
	 * Set the SendSolv id for the currency purchase associated with the allocation.
	 *
	 * @param currencyPurchaseId The currency purchase id to set.
	 */
	public void setCurrencyPurchaseId(Integer currencyPurchaseId)
	{
		this.currencyPurchaseId = currencyPurchaseId;
	}

	/**
	 * Get the SendSolv id for the money transfer associated with the allocation.
	 *
	 * @return The id.
	 */
	public Integer getMoneyTransferId()
	{
		return moneyTransferId;
	}

	/**
	 * Set the SendSolv id for the money transfer associated with the allocation.
	 *
	 * @param moneyTransferId The money transfer id to set.
	 */
	public void setMoneyTransferId(Integer moneyTransferId)
	{
		this.moneyTransferId = moneyTransferId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CurrencyAllocation [getId()=" + getId() + ", getAllocatedAmount()=" + getAllocatedAmount()
				+ ", getCurrencyPurchaseId()=" + getCurrencyPurchaseId() + ", getMoneyTransferId()="
				+ getMoneyTransferId() + ", toString()=" + super.toString() + "]";
	}
}
