package com.qat.samples.sysmgmt.model;

import com.qat.framework.model.BizEvent;

/**
 * The Class SampleBE is used to show how a BizEvent could be used publish an event to a JMS topic.
 */
@SuppressWarnings("serial")
public class SampleBE extends BizEvent
{

	private String procCode;

	private String priceChg;

	/**
	 * Instantiates a new SampleBE whose attributes will be initialized to null.
	 */
	public SampleBE()
	{
	}

	/**
	 * Instantiates a new SampleBE. The procCode is the unique identifier of the object related to the priceChg.
	 *
	 * @param argProcCode the procCode identifier of the object the price change is for.
	 * @param argPriceChg the price change
	 */
	public SampleBE(String argProcCode, String argPriceChg)
	{
		procCode = argProcCode;
		priceChg = argPriceChg;
	}

	/**
	 * Instantiates a new sample be.
	 *
	 * @param corrid the corrid
	 * @param bizevt the bizevt
	 * @param argProcCode the procCode identifier of the object the price change is for.
	 * @param argPriceChg the price change
	 */
	public SampleBE(String corrid, String bizevt, String argProcCode, String argPriceChg)
	{
		setBizCorrID(corrid);
		setBizEventName(bizevt);
		procCode = argProcCode;
		priceChg = argPriceChg;
	}

	/**
	 * Gets the ProcCode of the object whose price is changing..
	 *
	 * @return the String proceCode
	 */
	public String getProcCode()
	{
		return procCode;
	}

	/**
	 * Sets the procCode.
	 *
	 * @param procCode the new String procCode
	 */
	public void setProcCode(String procCode)
	{
		this.procCode = procCode;
	}

	/**
	 * Gets the price change which is the reason for this event.
	 *
	 * @return the price change
	 */
	public String getPriceChg()
	{
		return priceChg;
	}

	/**
	 * Sets the price change.
	 *
	 * @param priceChg the new price change
	 */
	public void setPriceChg(String priceChg)
	{
		this.priceChg = priceChg;
	}

	/**
	 * @return String representation of the SampleBE object.
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SampleBE [getBizEventName()=");
		builder.append(getBizEventName());
		builder.append(", getBizCorrID()=");
		builder.append(getBizCorrID());
		builder.append(", getProcCode()=");
		builder.append(getProcCode());
		builder.append(", getPriceChg()=");
		builder.append(getPriceChg());
		builder.append("]");
		return builder.toString();
	}

}
