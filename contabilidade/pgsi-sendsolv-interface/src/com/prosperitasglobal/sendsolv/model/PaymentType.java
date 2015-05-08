package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.cbof.model.Currency;
import com.qat.framework.model.QATModel;

/**
 * The class represents a type of payment. It contains the type {@link PaymentTypeEnum} as well as the {@link Currency}.
 */
@SuppressWarnings("serial")
public class PaymentType extends QATModel
{
	/** The SendSolv currency used with the payment type. */
	private Currency currency;

	/** The SendSolv payment type. */
	private PaymentTypeEnum type;

	/**
	 * Default constructor.
	 */
	public PaymentType()
	{
		super();
	}

	/**
	 * Constructor that takes a {@link PaymentTypeEnum} and {@link Currency} as parameters.
	 *
	 * @param currency The {@link Currency}.
	 * @param paymentTypeEnum The {@link PaymentTypeEnum}.
	 */
	public PaymentType(Currency currency, PaymentTypeEnum paymentTypeEnum)
	{
		this();
		setCurrency(currency);
		setType(paymentTypeEnum);
	}

	/**
	 * Get the SendSolv currency.
	 *
	 * @return The currency.
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * Set the SendSolv currency.
	 *
	 * @param currency The currency to set.
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the SendSolv payment type.
	 *
	 * @return The payment type.
	 */
	public PaymentTypeEnum getType()
	{
		return type;
	}

	/**
	 * Get the SendSolv payment type value.
	 *
	 * @return The payment type value.
	 */
	public Integer getTypeValue()
	{
		if (getType() == null)
		{
			return null;
		}

		return type.getValue();
	}

	/**
	 * Set the SendSolv payment type.
	 *
	 * @param type The payment type to set.
	 */
	public void setType(PaymentTypeEnum type)
	{
		this.type = type;
	}

	/**
	 * Set the SendSolv payment type by value.
	 *
	 * @param typeValue The payment type value to set.
	 */
	public void setTypeValue(Integer typeValue)
	{
		type = PaymentTypeEnum.enumForValue(typeValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PaymentType [getCurrency()=" + getCurrency() + ", getType()=" + getType() + ", getTypeValue()="
				+ getTypeValue() + ", toString()=" + super.toString() + "]";
	}
}
