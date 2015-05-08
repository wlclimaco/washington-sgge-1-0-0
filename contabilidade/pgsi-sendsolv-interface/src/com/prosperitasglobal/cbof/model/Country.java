package com.prosperitasglobal.cbof.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModel;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class Country.
 */
@SuppressWarnings("serial")
public class Country extends QATModel
{
	/** The code. */
	private String code;

	/** The list of currency. */
	private List<Currency> currencyList;

	/** The description. */
	private String description;

	/** The phone code. */
	private String phoneCode;

	/**
	 * The Constructor.
	 */
	public Country()
	{
	}

	/**
	 * The Constructor.
	 *
	 * @param code the code
	 */
	public Country(String code)
	{
		this.code = code;
	}

	/**
	 * The Constructor.
	 *
	 * @param code the code
	 * @param description the description
	 */
	public Country(String code, String description)
	{
		this.code = code;
		this.description = description;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Gets the list of currency.
	 *
	 * @return The list of currency.
	 */
	public List<Currency> getCurrencyList()
	{
		if (ValidationUtil.isNull(currencyList))
		{
			setCurrencyList(new ArrayList<Currency>());
		}

		return currencyList;
	}

	/**
	 * Sets the list of currency.
	 *
	 * @param currencyList The list of currency.
	 */
	public void setCurrencyList(List<Currency> currencyList)
	{
		this.currencyList = currencyList;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the phone code.
	 *
	 * @return the phone code
	 */
	public String getPhoneCode()
	{
		return phoneCode;
	}

	/**
	 * Sets the phone code.
	 *
	 * @param phoneCode the phone code
	 */
	public void setPhoneCode(String phoneCode)
	{
		this.phoneCode = phoneCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Country [getCode()=" + getCode() + ", getCurrencyList()=" + getCurrencyList() + ", getDescription()="
				+ getDescription() + ", getPhoneCode()=" + getPhoneCode() + ", toString()=" + super.toString() + "]";
	}
}
