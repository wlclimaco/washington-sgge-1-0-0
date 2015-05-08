package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.cbof.model.Currency;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * This class is a representation of the daily foreign exchange rate for a Payer.
 */
@SuppressWarnings("serial")
public class DailyCurrencyRate extends QATModelOL
{
	/** The SendSolv id for the daily currency rate. */
	private Integer id;

	/** The validForDate which the rate is valid. */
	private Long validForDate;

	/** The currency. */
	private Currency currency;

	/** The SendSolv id of the payer. */
	private Integer payerId;

	/** The SendSolv id for the current rate. Most current rate should be the first entry in the list. */
	private List<DailyCurrencyRateDetail> dailyCurrencyRateDetailList;

	/**
	 * Default constructor.
	 */
	public DailyCurrencyRate()
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
	 * Get the validForDate which the rate is valid. If this validForDate has a time portion, it will be removed. This
	 * attribute is only
	 * a validForDate.
	 *
	 * @return The validForDate.
	 */
	public Long getValidForDate()
	{
		return validForDate;
	}

	/**
	 * Set the validForDate which the rate is valid.
	 *
	 * @param validForDate The validForDate to set.
	 */
	public void setValidForDate(Long validForDate)
	{
		this.validForDate = validForDate;
	}

	/**
	 * Get the currency.
	 *
	 * @return The currency.
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * Set the currency.
	 *
	 * @param currency The currency to set.
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the SendSolv id of the payer.
	 *
	 * @return The payer id.
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * Set the SendSolv id of the payer.
	 *
	 * @param payerId The payer id to set.
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/**
	 * Get the list of {@link DailyCurrencyRateDetail}.
	 *
	 * @return The list.
	 */
	public List<DailyCurrencyRateDetail> getDailyCurrencyRateDetailList()
	{
		if (ValidationUtil.isNull(dailyCurrencyRateDetailList))
		{
			setDailyCurrencyRateDetailList(new ArrayList<DailyCurrencyRateDetail>());
		}

		return dailyCurrencyRateDetailList;
	}

	/**
	 * Set the list of {@link DailyCurrencyRateDetail}.
	 *
	 * @param dailyCurrencyRateDetailList The list to set.
	 */
	public void setDailyCurrencyRateDetailList(List<DailyCurrencyRateDetail> dailyCurrencyRateDetailList)
	{
		this.dailyCurrencyRateDetailList = dailyCurrencyRateDetailList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DailyCurrencyRate [getId()=" + getId() + ", getValidForDate()=" + getValidForDate()
				+ ", getCurrency()=" + getCurrency() + ", getPayerId()=" + getPayerId()
				+ ", getDailyCurrencyRateDetailList()=" + getDailyCurrencyRateDetailList() + ", toString()="
				+ super.toString() + "]";
	}
}
