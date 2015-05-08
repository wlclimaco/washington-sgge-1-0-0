package com.prosperitasglobal.cbof.model.response;

import java.util.Collection;
import java.util.List;

import com.prosperitasglobal.cbof.model.Country;
import com.qat.framework.model.response.InquiryResponse;

/**
 * The Class CountryResponse.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:30:31 AM
 */
public class CountryResponse extends InquiryResponse
{

	/** Attributes. */
	private List<Country> countryList;

	/**
	 * The Constructor.
	 */
	public CountryResponse()
	{

	}

	/**
	 * Gets the country list.
	 *
	 * @return the country list
	 */
	public List<Country> getCountryList()
	{
		return countryList;
	}

	/**
	 * Sets the country list.
	 *
	 * @param countryList the country list
	 */
	public void setCountryList(List<Country> countryList)
	{
		this.countryList = countryList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void addResults(Collection coll)
	{
		setCountryList((List<Country>)coll);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CountryResponse [getCountryList()=" + getCountryList() + ", getResultsSetInfo()=" + getResultsSetInfo()
				+ ", getMessageIterator()=" + getMessageIterator() + ", getMessageList()=" + getMessageList()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", isOperationSuccess()=" + isOperationSuccess()
				+ "]";
	}

}