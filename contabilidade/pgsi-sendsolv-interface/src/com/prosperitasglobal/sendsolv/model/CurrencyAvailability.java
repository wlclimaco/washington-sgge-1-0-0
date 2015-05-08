package com.prosperitasglobal.sendsolv.model;

import java.math.BigDecimal;

import com.prosperitasglobal.cbof.model.Currency;
import com.qat.framework.model.QATModelOL;

/**
 * This class is a representation of a Currency Availability of a Purchased Currency on the open exchange.
 */
@SuppressWarnings("serial")
public class CurrencyAvailability extends QATModelOL
{
	/** The SendSolv id of the currency availability. */
	private Integer id;

	/** The SendSolv id of the payer. */
	private Integer payerId;

	/** Amount of currency purchased */
	private BigDecimal currencyCreditCumulative;

	/** Amount of currency used */
	private BigDecimal currencyDebitCumulative;

	/** Currency of the credit and debit. */
	private Currency currency;

	/** The effective foreign exchange rate. */
	private BigDecimal effectiveForeignExchangeRate;

	/**
	 * Get the SendSolv id of the currency availability.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id of the currency availability.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * Returns the accumulated amount of currency purchases for the payer and currency.
	 *
	 * @return
	 */
	public BigDecimal getCurrencyCreditCumulative()
	{
		return currencyCreditCumulative;
	}

	/**
	 * Used to set the accumulated amount of currency purchases for the payer and currency.
	 *
	 * @param currencyCreditCumulative
	 */
	public void setCurrencyCreditCumulative(BigDecimal currencyCreditCumulative)
	{
		this.currencyCreditCumulative = currencyCreditCumulative;
	}

	/**
	 * Convenience method to enable adding an amount to the cumulative credit.
	 *
	 * @param creditAmount to add
	 * @return - true if the currencyCreditCumulative changed
	 */
	public Boolean addToCurrencyCreditCumulative(BigDecimal creditAmount)
	{
		Boolean result = false;

		if (creditAmount != null)
		{
			if (currencyCreditCumulative == null)
			{
				setCurrencyCreditCumulative(creditAmount);
			}
			else
			{
				setCurrencyCreditCumulative(currencyCreditCumulative.add(creditAmount));
			}

			if (!creditAmount.equals(BigDecimal.ZERO))
			{
				result = true;
			}
		}

		return result;
	}

	/**
	 * Returns the accumulated amount of currency used for the payer and currency
	 *
	 * @return
	 */
	public BigDecimal getCurrencyDebitCumulative()
	{
		return currencyDebitCumulative;
	}

	/**
	 * Sets the accumulated amount of currency used for the payer and currency
	 *
	 * @param currencyDebitCumulative
	 */
	public void setCurrencyDebitCumulative(BigDecimal currencyDebitCumulative)
	{
		this.currencyDebitCumulative = currencyDebitCumulative;
	}

	/**
	 * Convenience method to enable adding an amount to the cumulative debit.
	 *
	 * @param debitAmount to add
	 * @return - true if the currencyDebitCumulative changed
	 */
	public Boolean addToCurrencyDebitCumulative(BigDecimal debitAmount)
	{
		Boolean result = false;

		if (debitAmount != null)
		{
			if (currencyDebitCumulative == null)
			{
				setCurrencyDebitCumulative(debitAmount);
			}
			else
			{
				setCurrencyDebitCumulative(currencyDebitCumulative.add(debitAmount));
			}

			if (!debitAmount.equals(BigDecimal.ZERO))
			{
				result = true;
			}
		}

		return result;
	}

	/**
	 * Get the currency of the credit and debit amounts.
	 *
	 * @return The currency.
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * Set the currency of the credit and debit amounts.
	 *
	 * @param currency The currency to set.
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the effective foreign exchange rate.
	 *
	 * @return The rate.
	 */
	public BigDecimal getEffectiveForeignExchangeRate()
	{
		return effectiveForeignExchangeRate;
	}

	/**
	 * Set the effective foreign exchange rate.
	 *
	 * @param effectiveForeignExchangeRate The rate to set.
	 */
	public void setEffectiveForeignExchangeRate(BigDecimal effectiveForeignExchangeRate)
	{
		this.effectiveForeignExchangeRate = effectiveForeignExchangeRate;
	}

	@Override
	public String toString()
	{
		return "CurrencyAvailability [getId()=" + getId() + ", getPayerId()=" + getPayerId()
				+ ", getCurrencyCreditCumulative()=" + getCurrencyCreditCumulative()
				+ ", getCurrencyDebitCumulative()=" + getCurrencyDebitCumulative() + ", getCurrency()=" + getCurrency()
				+ ", getEffectiveForeignExchangeRate()=" + getEffectiveForeignExchangeRate() + ", toString()="
				+ super.toString() + "]";
	}

}
