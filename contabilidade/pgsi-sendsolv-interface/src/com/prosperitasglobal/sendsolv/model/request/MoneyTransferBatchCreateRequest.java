package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventCalendar;
import com.prosperitasglobal.sendsolv.model.Location;
import com.qat.framework.model.request.Request;

/**
 * This request is used when {@link com.prosperitasglobal.sendsolv.model.MoneyTransferBatch}es are to be created from
 * {@link Location}'s. Specify the {@link Location} that will be used to create the {@link MoneyTransferBatch} objects,
 * and also specify the {@link FrequencyBasedEventCalendar} object that triggered the request.
 */
public class MoneyTransferBatchCreateRequest extends Request
{
	/** The location which will have a Money Transfer Batch created. */
	private Location location;

	/** The payment preparation frequency based event calendar which triggered the batch creations. */
	private FrequencyBasedEventCalendar paymentPreparationDate;

	/** The pay date frequency based event calendar for the payment preparation date. */
	private FrequencyBasedEventCalendar payDate;

	/**
	 * The Constructor.
	 */
	public MoneyTransferBatchCreateRequest()
	{
		super();
	}

	/**
	 * Gets the pay day frequency based event calendar which triggered the batch creations.
	 *
	 * @return The calendar.
	 */
	public FrequencyBasedEventCalendar getPayDate()
	{
		return payDate;
	}

	/**
	 * Sets the pay day frequency based event calendar which triggered the batch creations.
	 *
	 * @param payDate The calendar to set.
	 */
	public void setPayDate(FrequencyBasedEventCalendar payDate)
	{
		this.payDate = payDate;
	}

	/**
	 * Gets the payment preparation frequency based event calendar which triggered the batch creations.
	 *
	 * @return The calendar.
	 */
	public FrequencyBasedEventCalendar getPaymentPreparationDate()
	{
		return paymentPreparationDate;
	}

	/**
	 * Sets the payment preparation frequency based event calendar which triggered the batch creations.
	 *
	 * @param paymentPreparationDate The calendar to set.
	 */
	public void setPaymentPreparationDate(FrequencyBasedEventCalendar paymentPreparationDate)
	{
		this.paymentPreparationDate = paymentPreparationDate;
	}

	/**
	 * Gets the location which will have a Money Transfer Batch created.
	 *
	 * @return The location.
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * Sets the location which will have a Money Transfer Batch created.
	 *
	 * @param location The location.
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferBatchCreateRequest [getPayDate()=" + getPayDate() + ", getPaymentPreparationDate()="
				+ getPaymentPreparationDate() + ", getLocation()=" + getLocation() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}