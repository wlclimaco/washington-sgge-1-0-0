package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * Represents an association between a payer, payment type, and currency.
 */
@SuppressWarnings("serial")
public class PayerPaymentTypeCurrency extends QATModel
{
	/** The SendSolv currency code. */
	private String currencyCode;

	/** The SendSolv payer id. */
	private Integer payerId;

	/** The SendSolv payment type code. */
	private Integer paymentTypeCode;

	/**
	 * Get the SendSolv currency code.
	 *
	 * @return The code.
	 */
	public String getCurrencyCode()
	{
		return currencyCode;
	}

	/**
	 * Set the SendSolv currency code.
	 *
	 * @param currencyCode The currency code to set.
	 */
	public void setCurrencyCode(String currencyCode)
	{
		this.currencyCode = currencyCode;
	}

	/**
	 * Get the SendSolv payer id.
	 *
	 * @return The id.
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * Set the SendSolv payer id.
	 *
	 * @param payerId The payer id to set.
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/**
	 * Get the SendSolv payment type code.
	 *
	 * @return The payment type code.
	 */
	public Integer getPaymentTypeCode()
	{
		return paymentTypeCode;
	}

	/**
	 * Set the SendSolv payment type code.
	 *
	 * @param paymentTypeCode The payment type code to set.
	 */
	public void setPaymentTypeCode(Integer paymentTypeCode)
	{
		this.paymentTypeCode = paymentTypeCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerPaymentTypeCurrency [getCurrencyCode()=" + getCurrencyCode() + ", getPayerId()=" + getPayerId()
				+ ", getPaymentTypeCode()=" + getPaymentTypeCode() + ", toString()=" + super.toString() + "]";
	}
}
