package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.prosperitasglobal.cbof.model.Currency;
import com.qat.framework.model.QATModel;

/**
 * This class is a representation of a Currency Purchase on the open exchange.
 */
@SuppressWarnings("serial")
public class CurrencyPurchase extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The amount of the currency purchased. */
	private BigDecimal amountPurchased;

	/** The amount of the currency unallocated to money transfers. */
	private BigDecimal amountNotAllocated;

	/** The currency purchased. */
	private Currency currency;

	/** The exchange rate of the purchase. */
	private BigDecimal foreignExchangeRate;

	/** The id of the Payer that the purchase was made. */
	private Integer payerId;

	/** The date of the purchase. */
	private Long purchaseDate;

	/** The amount of US Dollars spent to purchase the currency. */
	private BigDecimal usDollarsSpent;

	/**
	 * Default constructor.
	 */
	public CurrencyPurchase()
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
	 * Get the amount of the currency purchased.
	 *
	 * @return The amount.
	 */
	public BigDecimal getAmountPurchased()
	{
		return amountPurchased;
	}

	/**
	 * Set the amount of the currency purchased.
	 *
	 * @param amountPurchased The amount to set.
	 */
	public void setAmountPurchased(BigDecimal amountPurchased)
	{
		this.amountPurchased = amountPurchased;
	}

	/**
	 * Get the amount of the currency unallocated.
	 *
	 * @return The amount.
	 */
	public BigDecimal getAmountNotAllocated()
	{
		return amountNotAllocated;
	}

	/**
	 * Set the amount of the currency unallocated.
	 *
	 * @param amountNotAllocated The amount to set.
	 */
	public void setAmountNotAllocated(BigDecimal amountNotAllocated)
	{
		this.amountNotAllocated = amountNotAllocated;
	}

	/**
	 * Get the currency purchased.
	 *
	 * @return The currency.
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * Set the currency purchased.
	 *
	 * @param currency The currency to set.
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the exchange rate of the purchase.
	 *
	 * @return The exchange rate.
	 */
	public BigDecimal getForeignExchangeRate()
	{
		return foreignExchangeRate;
	}

	/**
	 * Set the exchange rate of the purchase.
	 *
	 * @param foreignExchangeRate The exchange rate to set.
	 */
	public void setForeignExchangeRate(BigDecimal foreignExchangeRate)
	{
		this.foreignExchangeRate = foreignExchangeRate;
	}

	/**
	 * Get the SendSolv payer id for which the purchase was made.
	 *
	 * @return The payer id.
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * Set the SendSolv payer id for which the purchase was made.
	 *
	 * @param payerId The payer id to set.
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/**
	 * Get the currency purchased date.
	 *
	 * @return The currency purchased date.
	 */
	public Long getPurchaseDate()
	{
		return purchaseDate;
	}

	/**
	 * Set the currency purchased date.
	 *
	 * @param purchaseDate The date of purchase to set.
	 */
	public void setPurchaseDate(Long purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Get the amount of US Dollars spent to purchase the currency.
	 *
	 * @return The amount.
	 */
	public BigDecimal getUsDollarsSpent()
	{
		return usDollarsSpent;
	}

	/**
	 * Set the amount of US Dollars spent to purchase the currency.
	 *
	 * @param usDollarsSpent The amount to set.
	 */
	public void setUsDollarsSpent(BigDecimal usDollarsSpent)
	{
		this.usDollarsSpent = usDollarsSpent;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CurrencyPurchase [getId()=" + getId() + ", getAmountPurchased()=" + getAmountPurchased()
				+ ", getAmountNotAllocated()=" + getAmountNotAllocated() + ", getCurrency()=" + getCurrency()
				+ ", getForeignExchangeRate()=" + getForeignExchangeRate() + ", getPayerId()=" + getPayerId()
				+ ", getPurchaseDate()=" + getPurchaseDate() + ", getUsDollarsSpent()=" + getUsDollarsSpent()
				+ ", toString()=" + super.toString() + "]";
	}
}
