package com.prosperitasglobal.cbof.model.request;

import com.prosperitasglobal.cbof.model.RangeEnum;

/**
 * The Class RangeRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:32:55 AM
 */
public class RangeRequest extends FetchByIdRequest
{

	/** Attributes. */
	private RangeEnum rangeType;

	/**
	 * The Constructor.
	 */
	public RangeRequest()
	{

	}

	/**
	 * Gets the range type.
	 *
	 * @return the range type
	 */
	public RangeEnum getRangeType()
	{
		return rangeType;
	}

	/**
	 * Sets the range type.
	 *
	 * @param rangeType the range type
	 */
	public void setRangeType(RangeEnum rangeType)
	{
		this.rangeType = rangeType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RangeRequest [getRangeType()=" + getRangeType() + ", toString()=" + super.toString() + "]";
	}

}