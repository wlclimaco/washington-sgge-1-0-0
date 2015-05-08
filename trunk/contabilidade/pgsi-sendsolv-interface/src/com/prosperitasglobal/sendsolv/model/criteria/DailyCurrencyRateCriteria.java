package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

import com.prosperitasglobal.cbof.model.Currency;

/**
 * Criteria member used for doing a lookup on a daily currency rate in the SendSolv system.
 * <p>
 * The properties specified in this class are used when fetching rows from the data store. If a property value is
 * <code>null</code>, then it won't be including in the matching logic. Only properties that have a value will be used.
 */
@SuppressWarnings("serial")
public class DailyCurrencyRateCriteria implements Serializable
{
	/** The SendSolv id of the payer associated with the rate. */
	private Integer payerId;

	/** The currency of the rate. */
	private Currency currency;

	/**
	 * The date of the foreign exchange rate. Any date on the database that is greater than or equal to this date will
	 * be returned.
	 */
	private Long date;

	/**
	 * Default constructor.
	 */
	public DailyCurrencyRateCriteria()
	{
		super();
	}

	/**
	 * Get the SendSolv id of the payer.
	 *
	 * @return The id.
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * Set the SendSolv id of the payer.
	 *
	 * @param payerId The id to set.
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/**
	 * Get the currency of the rate.
	 *
	 * @return The currency.
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * Set the currency of the rate.
	 *
	 * @param currency The currency to set.
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the date of the rate. This date will be returned without a time value. This attribute just captures the date
	 * and not the time. Any date on the database that is greater than or equal to this date will be returned.
	 *
	 * @return The date.
	 */
	public Long getDate()
	{
		return date;
	}

	/**
	 * Set the date of the rate. This date will have the time portion removed if one is passed in. This is meant to be a
	 * date, and not a date/time attribute.
	 *
	 * @param date The date to set.
	 */
	public void setDate(Long date)
	{
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DailyCurrencyRateCriteria [getPayerId()=" + getPayerId() + ", getCurrency()=" + getCurrency()
				+ ", getDate()=" + getDate() + "]";
	}
}
