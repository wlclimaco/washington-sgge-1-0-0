package com.prosperitasglobal.sendsolv.payer.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModel;
import com.qat.framework.validation.ValidationUtil;

/**
 * Represents a payer city in the SendSolv system.
 */
@SuppressWarnings("serial")
public class PayerCity extends QATModel
{
	/** The SendSolv id for the city */
	private Integer id;

	/** The parent key state/province/region id associated with the city. */
	private Integer stateProvinceRegionId;

	/** The name of the city */
	private String name;

	/** The list of addresses associated with the city */
	private List<PayerAddress> payerAddressList;

	/** The list of zipcodes associated with the city */
	private List<PayerZipcode> payerZipcodeList;

	/** The payerIdList of the associated payer */
	private List<Integer> payerIdList;

	/**
	 * Get the SendSolv city id
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv city id
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the state/province/region id
	 *
	 * @return the stateProvinceRegionId
	 */
	public Integer getStateProvinceRegionId()
	{
		return stateProvinceRegionId;
	}

	/**
	 * Set the state/province/region id
	 *
	 * @param stateProvinceRegionId the stateProvinceRegionId to set
	 */
	public void setStateProvinceRegionId(Integer stateProvinceRegionId)
	{
		this.stateProvinceRegionId = stateProvinceRegionId;
	}

	/**
	 * Get the city name
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the city name
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the payer address list
	 *
	 * @return the payerAddressList
	 */
	public List<PayerAddress> getPayerAddressList()
	{
		if (ValidationUtil.isNull(payerAddressList))
		{
			setPayerAddressList(new ArrayList<PayerAddress>());
		}

		return payerAddressList;
	}

	/**
	 * Set the payer address list
	 *
	 * @param payerAddressList the payerAddressList to set
	 */
	public void setPayerAddressList(List<PayerAddress> payerAddressList)
	{
		this.payerAddressList = payerAddressList;
	}

	/**
	 * Get the payer zipcode list
	 *
	 * @return the payerAddressList
	 */
	public List<PayerZipcode> getPayerZipcodeList()
	{
		if (ValidationUtil.isNull(payerZipcodeList))
		{
			setPayerZipcodeList(new ArrayList<PayerZipcode>());
		}

		return payerZipcodeList;
	}

	/**
	 * Set the payer zipcode list
	 *
	 * @param payerZipcodeList the payerZipcodeList to set
	 */
	public void setPayerZipcodeList(List<PayerZipcode> payerZipcodeList)
	{
		this.payerZipcodeList = payerZipcodeList;
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
		return "PayerCity [getId()=" + getId() + ", getStateProvinceRegionId()=" + getStateProvinceRegionId()
				+ ", getName()=" + getName() + ", getPayerAddressList()=" + getPayerAddressList()
				+ ", getPayerZipcodeList()=" + getPayerZipcodeList() + ", getPayerIdList()=" + getPayerIdList()
				+ ", toString()=" + super.toString() + "]";
	}
}
