package com.sensus.lc.light.model.request;

import java.math.BigInteger;

import com.sensus.common.model.request.Request;

/**
 * Used to return 0 to 1 <b>fully inflated</b> Light objects. <br>
 * The properties used within this request must be unique and when applied to a query must result in a single row being
 * returned.<br>
 * Since there are multiple properties in this request only one of them should be populated, the others should be null.<br>
 * The processing order/hierarchy is as follows:
 * <ol>
 * <li>LightId - If not null will be used.
 * <li>RniId - If LightId is null then RniId will be used.
 * <li>If all the properties are a null a validation error will be set.
 * </ol>
 */
public class FetchByIdRequest extends Request
{

	/** The light id. */
	private Integer lightId;

	/** The rni id. */
	private BigInteger rniId;

	/**
	 * Instantiates a new fetch by id request.
	 * 
	 * @param id the id
	 */
	public FetchByIdRequest(Integer id)
	{
		lightId = id;
	}

	/**
	 * Instantiates a new fetch by id request.
	 * 
	 * @param id the id
	 * @param radioId the radio id
	 */
	public FetchByIdRequest(Integer id, BigInteger radioId)
	{
		lightId = id;
		rniId = radioId;
	}

	/**
	 * Gets the light id.
	 * 
	 * @return the light id
	 */
	public Integer getLightId()
	{
		return lightId;
	}

	/**
	 * Sets the light id.
	 * 
	 * @param lightId the new light id
	 */
	public void setLightId(Integer lightId)
	{
		this.lightId = lightId;
	}

	/**
	 * @return the rniId
	 */
	public BigInteger getRniId()
	{
		return rniId;
	}

	/**
	 * @param rniId the rniId to set
	 */
	public void setRniId(BigInteger rniId)
	{
		this.rniId = rniId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FetchByIdRequest [getLightId()=" + getLightId() + ", getRniId()=" + getRniId() + ", toString()="
				+ super.toString() + "]";
	}
}