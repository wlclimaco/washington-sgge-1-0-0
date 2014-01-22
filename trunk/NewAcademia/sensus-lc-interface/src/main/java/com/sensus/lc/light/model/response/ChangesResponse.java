package com.sensus.lc.light.model.response;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;

/**
 * The Class ChangesResponse.
 */
public class ChangesResponse extends InquiryResponse
{
	private List<BigInteger> flexnetList = new ArrayList<BigInteger>();

	/*
	 * Override this method so framework can work with this class.
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addResults(@SuppressWarnings("rawtypes") Collection coll)
	{
		getFlexnetList().addAll(coll);
	}

	/**
	 * Gets the flexnet list.
	 * 
	 * @return the flexnet list
	 */
	public List<BigInteger> getFlexnetList()
	{
		return flexnetList;
	}

	/**
	 * Sets the flexnet list.
	 * 
	 * @param flexnetList the new flexnet list
	 */
	public void setFlexnetList(List<BigInteger> flexnetList)
	{
		this.flexnetList = flexnetList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ChangesResponse [getFlexnetList()=" + getFlexnetList() + ", toString()=" + super.toString() + "]";
	}

}