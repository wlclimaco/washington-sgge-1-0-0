package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of a foreign exchange rate.
 */
@SuppressWarnings("serial")
public class DailyCurrencyRateDetail extends QATModel
{
	/** The SendSolv id for the daily currency rate detail. */
	private Integer id;

	/** The SendSolv id for the daily currency rate. */
	private Integer dailyCurrencyRateId;

	/** The exchange rate. */
	private BigDecimal exchangeRate;

	/**
	 * Default constructor.
	 */
	public DailyCurrencyRateDetail()
	{
		super();
	}

	/**
	 * Get the SendSolv id for the money transfer batch.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the money transfer batch.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the SendSolv id for the daily currency rate.
	 *
	 * @return The id.
	 */
	public Integer getDailyCurrencyRateId()
	{
		return dailyCurrencyRateId;
	}

	/**
	 * Set the SendSolv id for the daily currency rate.
	 *
	 * @param dailyCurrencyRateId The id to set.
	 */
	public void setDailyCurrencyRateId(Integer dailyCurrencyRateId)
	{
		this.dailyCurrencyRateId = dailyCurrencyRateId;
	}

	/**
	 * Get the exchange rate.
	 *
	 * @return The rate.
	 */
	public BigDecimal getExchangeRate()
	{
		return exchangeRate;
	}

	/**
	 * Set the exchange rate.
	 *
	 * @param exchangeRate The rate to set.
	 */
	public void setExchangeRate(BigDecimal exchangeRate)
	{
		this.exchangeRate = exchangeRate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DailyCurrencyRateDetail [getId()=" + getId() + ", getDailyCurrencyRateId()=" + getDailyCurrencyRateId()
				+ ", getExchangeRate()=" + getExchangeRate() + ", toString()=" + super.toString() + "]";
	}
}
