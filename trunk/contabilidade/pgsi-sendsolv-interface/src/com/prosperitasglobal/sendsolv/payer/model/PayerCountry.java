package com.prosperitasglobal.sendsolv.payer.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModel;
import com.qat.framework.validation.ValidationUtil;

/**
 * Represents a payer country in the SendSolv system.
 */
@SuppressWarnings("serial")
public class PayerCountry extends QATModel
{
	/** Attributes. */

	/** The Payer Country Code */
	private String code;

	/** The Country Name */
	private String name;

	/** The Payer State/Province/Region list associated with the Payer Country */
	private List<PayerStateProvinceRegion> payerStateProvinceRegionList;

	/** The Payer Code list associated with the Payer Country */
	private List<Integer> payerIdList;

	/**
	 * Get the Payer Country Code
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Set the Payer Country Code
	 *
	 * @param code the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Get the payer country full name
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the payer country full name
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the Payer State/Province/Region List
	 *
	 * @return the payerStateProvinceRegionList
	 */
	public List<PayerStateProvinceRegion> getPayerStateProvinceRegionList()
	{
		if (ValidationUtil.isNull(payerStateProvinceRegionList))
		{
			setPayerStateProvinceRegionList(new ArrayList<PayerStateProvinceRegion>());
		}

		return payerStateProvinceRegionList;
	}

	/**
	 * Set the Payer State/Province/Region List
	 *
	 * @param payerStateProvinceRegionList the payerStateProvinceRegionList to set
	 */
	public void setPayerStateProvinceRegionList(List<PayerStateProvinceRegion> payerStateProvinceRegionList)
	{
		this.payerStateProvinceRegionList = payerStateProvinceRegionList;
	}

	/**
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
		return "PayerCountry [getCode()=" + getCode() + ", getName()=" + getName()
				+ ", getPayerStateProvinceRegionList()=" + getPayerStateProvinceRegionList() + ", getPayerIdList()="
				+ getPayerIdList() + ", toString()=" + super.toString() + "]";
	}
}
