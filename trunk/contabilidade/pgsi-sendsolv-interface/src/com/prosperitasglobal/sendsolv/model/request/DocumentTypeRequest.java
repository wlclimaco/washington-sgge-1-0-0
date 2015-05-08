package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;

/**
 * The Class DocumentTypeRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:32:55 AM
 */
public class DocumentTypeRequest extends FetchByIdRequest
{

	/** Attributes. */
	private BusinessTypeEnum businessType;

	/** The country code. */
	private String countryCode;

	/**
	 * The Constructor.
	 */
	public DocumentTypeRequest()
	{

	}

	/**
	 * The Constructor.
	 *
	 * @param businessTypeParam the business type param
	 */
	public DocumentTypeRequest(BusinessTypeEnum businessTypeParam)
	{
		businessType = businessTypeParam;
	}

	/**
	 * Gets the business type.
	 *
	 * @return the business type
	 */
	public BusinessTypeEnum getBusinessType()
	{
		return businessType;
	}

	/**
	 * Sets the business type.
	 *
	 * @param businessTypeParam the business type
	 */
	public void setBusinessType(BusinessTypeEnum businessTypeParam)
	{
		businessType = businessTypeParam;
	}

	/**
	 * Gets the country code.
	 *
	 * @return the country code
	 */
	public String getCountryCode()
	{
		return countryCode;
	}

	/**
	 * Sets the country code.
	 *
	 * @param countryCode the country code
	 */
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	/**
	 * Gets the business type value.
	 *
	 * @return the business type value
	 */
	public Integer getBusinessTypeValue()
	{
		if (businessType == null)
		{
			return null;
		}

		return businessType.getValue();
	}

	/**
	 * Sets the business type value.
	 *
	 * @param businessTypeValue the business type value
	 */
	public void setBusinessTypeValue(Integer businessTypeValue)
	{
		businessType = BusinessTypeEnum.enumForValue(businessTypeValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DocumentTypeRequest [getBusinessType()=" + getBusinessType() + ", getCountryCode()=" + getCountryCode()
				+ ", getBusinessTypeValue()=" + getBusinessTypeValue() + ", toString()=" + super.toString() + "]";
	}

}