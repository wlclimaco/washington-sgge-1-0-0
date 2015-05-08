package com.prosperitasglobal.sendsolv.model;

import com.prosperitasglobal.cbof.model.Currency;
import com.qat.framework.model.QATModelOL;

/**
 * Class that associates a {@link Payer} and a {@link PaymentTypeEnum}.
 */
@SuppressWarnings("serial")
public class ProductPlanApplicability extends QATModelOL
{
	/** The currency. */
	private Currency currency;

	/** The id of the product plan applicability. */
	private Integer id;

	/** The id of the product plan. */
	private Integer productPlanId;

	/** The payer. */
	private Payer payer;

	/** the payment type of the payer. */
	private PaymentTypeEnum paymentType;

	/**
	 * Default Constructor.
	 */
	public ProductPlanApplicability()
	{
		super();
	}

	/**
	 * Get the currency of the product plan applicability.
	 *
	 * @return The id.
	 */
	public Currency getCurrency()
	{
		return currency;
	}

	/**
	 * Set the currency of the product plan applicability.
	 *
	 * @param currency The currency to set.
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	/**
	 * Get the id of the product plan applicability.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id of the product plan applicability.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the payer.
	 *
	 * @return The payer.
	 */
	public Payer getPayer()
	{
		return payer;
	}

	/**
	 * Set the payer.
	 *
	 * @param payer The payer to set.
	 */
	public void setPayer(Payer payer)
	{
		this.payer = payer;
	}

	/**
	 * Get the payment type.
	 *
	 * @return The payment type.
	 */
	public PaymentTypeEnum getPaymentType()
	{
		return paymentType;
	}

	/**
	 * Get the payment type value.
	 *
	 * @return The payment type value.
	 */
	public Integer getPaymentTypeValue()
	{
		if (getPaymentType() == null)
		{
			return null;
		}

		return paymentType.getValue();
	}

	/**
	 * Set the payment type.
	 *
	 * @param paymentType The payment type to set.
	 */
	public void setPaymentType(PaymentTypeEnum paymentType)
	{
		this.paymentType = paymentType;
	}

	/**
	 * Set the payment type by value.
	 *
	 * @param paymentTypeValue The payment type value to set.
	 */
	public void setPaymentTypeValue(Integer paymentTypeValue)
	{
		paymentType = PaymentTypeEnum.enumForValue(paymentTypeValue);
	}

	/**
	 * Get the id of the product plan.
	 *
	 * @return The product plan id.
	 */
	public Integer getProductPlanId()
	{
		return productPlanId;
	}

	/**
	 * Set the id of the product plan.
	 *
	 * @param productPlanId The id of the product plan to set.
	 */
	public void setProductPlanId(Integer productPlanId)
	{
		this.productPlanId = productPlanId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProductPlanApplicability [getCurrency()=" + getCurrency() + ", getId()=" + getId() + ", getPayer()="
				+ getPayer() + ", getPaymentType()=" + getPaymentType() + ", getPaymentTypeValue()="
				+ getPaymentTypeValue() + ", getProductPlanId()=" + getProductPlanId() + ", toString()="
				+ super.toString() + "]";
	}
}
