package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.qat.framework.model.QATModel;

/**
 * This class is a representation of the money in a Money Transfer.
 */
@SuppressWarnings("serial")
public class MoneyTransferAmount extends QATModel
{
	/** The country of the money transfer. */
	private Country country;

	/** The currency of the money transfer. */
	private Currency currency;

	/** The state/province/region of the money transfer. */
	private StateProvinceRegion stateProvinceRegion;

	/** The amount of the money transfer. */
	private BigDecimal amount;

	/**
	 * Default constructor.
	 */
	public MoneyTransferAmount()
	{
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param amount The amount.
	 * @param country The country.
	 * @param currency The currency.
	 */
	public MoneyTransferAmount(BigDecimal amount, Country country, Currency currency)
	{
		this();
		setAmount(amount);
		setCountry(country);
		setCurrency(currency);
	}

	/**
	 * Get the {@link Country} of the money transfer.
	 *
	 * @return The {@link Country}.
	 */
	public Country getCountry()
	{
		return country;
	}

	/**
	 * Set the {@link Country} of the money transfer.
	 *
	 * @param country The {@link Country} to set.
	 */
	public void setCountry(Country country)
	{
		this.country = country;
	}

	/**
	 * Get the {@link Currency} of the money transfer.
	 *
	 * @return The {@link Currency}.
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * Set the {@link Currency} of the money transfer.
	 *
	 * @param currency The {@link Currency} to set.
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the {@link StateProvinceRegion} of the money transfer.
	 *
	 * @return The {@link StateProvinceRegion}.
	 */
	public StateProvinceRegion getStateProvinceRegion()
	{
		return stateProvinceRegion;
	}

	/**
	 * Set the {@link StateProvinceRegion} of the money transfer.
	 *
	 * @param stateProvinceRegion The {@link StateProvinceRegion} to set.
	 */
	public void setStateProvinceRegion(StateProvinceRegion stateProvinceRegion)
	{
		this.stateProvinceRegion = stateProvinceRegion;
	}

	/**
	 * Get the amount of the money transfer.
	 *
	 * @return The amount.
	 */
	public BigDecimal getAmount()
	{
		return amount;
	}

	/**
	 * Set the amount of the money transfer.
	 *
	 * @param amount The amount to set.
	 */
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferAmount [getCountry()=" + getCountry() + ", getCurrency()=" + getCurrency()
				+ ", getStateProvinceRegion()=" + getStateProvinceRegion() + ", getAmount()=" + getAmount()
				+ ", toString()=" + super.toString() + "]";
	}
}
