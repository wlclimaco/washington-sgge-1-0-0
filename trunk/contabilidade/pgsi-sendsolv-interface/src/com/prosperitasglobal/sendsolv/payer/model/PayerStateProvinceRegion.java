package com.prosperitasglobal.sendsolv.payer.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModel;
import com.qat.framework.validation.ValidationUtil;

/**
 * Represents a payer state/province/region in the SendSolv system.
 */
@SuppressWarnings("serial")
public class PayerStateProvinceRegion extends QATModel
{
	/** The SendSolv id for the payer state/province/region */
	private Integer id;

	/** The state/province/region code */
	private String code;

	/** The name of the state/province/region */
	private String name;

	/** The full name of the state/province/region */
	private String longName;

	/** The parent key country code for the payer state/province/region */
	private String countryCode;

	/** The list of cities associated with the state/province/region */
	private List<PayerCity> payerCityList;

	/** The payerId of the associated payer */
	private List<Integer> payerIdList;

	/**
	 * Get the SendSolv id for the payer state/province/region
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the payer state/province/region
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the code for the payer state/province/region
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Set the code for the payer state/province/region
	 *
	 * @param code the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Get the full name of the payer state/province/region
	 *
	 * @return the longName
	 */
	public String getLongName()
	{
		return longName;
	}

	/**
	 * Set the full name of the payer state/province/region
	 *
	 * @param longName the longName to set
	 */
	public void setLongName(String longName)
	{
		this.longName = longName;
	}

	/**
	 * Get the name of the payer state/province/region
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the payer state/province/region
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the country code for the payer state/province/region
	 *
	 * @return the countryCode
	 */
	public String getCountryCode()
	{
		return countryCode;
	}

	/**
	 * Set the country code for the payer state/province/region
	 *
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	/**
	 * Get the list of associated cities
	 *
	 * @return the payerCityList
	 */
	public List<PayerCity> getPayerCityList()
	{
		if (ValidationUtil.isNull(payerCityList))
		{
			setPayerCityList(new ArrayList<PayerCity>());
		}

		return payerCityList;
	}

	/**
	 * Set the list of associated cities
	 *
	 * @param payerCityList the payerCityList to set
	 */
	public void setPayerCityList(List<PayerCity> payerCityList)
	{
		this.payerCityList = payerCityList;
	}

	/**
	 * Gets the payerIdList
	 *
	 * @return the payerIdList
	 */
	public List<Integer> getPayerIdList()
	{
		if (ValidationUtil.isNull(payerIdList))
		{
			setPayerIdList(new ArrayList<Integer>());
		}

		return payerIdList;
	}

	/**
	 * Sets the payerIdList
	 *
	 * @param payerIdList the payerIdList to set
	 */
	public void setPayerIdList(List<Integer> payerIdList)
	{
		this.payerIdList = payerIdList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerStateProvinceRegion [getId()=" + getId() + ", getCode()=" + getCode()
				+ ", getName()=" + getName() + ", getLongName()=" + getLongName() + ", getCountryCode()="
				+ getCountryCode() + ", getPayerCityList()=" + getPayerCityList() + ", getPayerIdList()="
				+ getPayerIdList() + ", toString()=" + super.toString() + "]";
	}
}
