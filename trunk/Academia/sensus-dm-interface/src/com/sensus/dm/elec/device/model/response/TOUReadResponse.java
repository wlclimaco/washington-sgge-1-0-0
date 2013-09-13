package com.sensus.dm.elec.device.model.response;

import java.util.Arrays;

import com.sensus.common.model.response.InquiryResponse;

/**
 * The Class TOUReadResponse.
 * 
 * @author QAT Global
 */
public class TOUReadResponse extends InquiryResponse
{

	/** The tou read. */
	private String[][] touRead;

	/**
	 * Instantiates a new tOU read response.
	 */
	public TOUReadResponse()
	{
	}

	/**
	 * Instantiates a new tOU read response.
	 * 
	 * @param touReadsParam the tou read
	 */
	public TOUReadResponse(String[][] touReadsParam)
	{
		setTouRead(touReadsParam);
	}

	/**
	 * Gets the tou read.
	 * 
	 * @return the tou read
	 */
	public String[][] getTouRead()
	{
		return touRead;
	}

	/**
	 * Sets the tou read.
	 * 
	 * @param touRead the new tou read
	 */
	public void setTouRead(String[][] touRead)
	{
		this.touRead = touRead;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TOUReadResponse [touRead=" + Arrays.toString(touRead) + "]";
	}

}
