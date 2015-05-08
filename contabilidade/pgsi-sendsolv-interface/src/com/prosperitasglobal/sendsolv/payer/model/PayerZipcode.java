package com.prosperitasglobal.sendsolv.payer.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModel;
import com.qat.framework.validation.ValidationUtil;

/**
 * Represents a payer zipcode in the SendSolv system.
 */
@SuppressWarnings("serial")
public class PayerZipcode extends QATModel
{
	/** The SendSolv id for the zipcode */
	private Integer id;

	/** The payer zipcode */
	private String zipcode;

	/** The parent key city id associated with the zipcode. */
	private Integer cityId;

	/** The payerIdList of the associated payer */
	private List<Integer> payerIdList;

	private List<PayerAddress> payerAddressList;

	/**
	 * Get the id
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the id
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the zipcode
	 *
	 * @return the zipcode
	 */
	public String getZipcode()
	{
		return zipcode;
	}

	/**
	 * Set the zipcode
	 *
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	/**
	 * Get the city id
	 *
	 * @return the cityId
	 */
	public Integer getCityId()
	{
		return cityId;
	}

	/**
	 * Get the city id
	 *
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId)
	{
		this.cityId = cityId;
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

	/**
	 * Gets the payer address list associated with this zipcode
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
	 * Sets the payer address list associated with this zipcode
	 *
	 * @param payerAddressList the payerAddressList to set
	 */
	public void setPayerAddressList(List<PayerAddress> payerAddressList)
	{
		this.payerAddressList = payerAddressList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerZipcode [getId()=" + getId() + "getZipcode()=" + getZipcode()
				+ ", getCityId()=" + getCityId() + ", getPayerIdList()=" + getPayerIdList()
				+ ", getPayerAddressList()=" + getPayerAddressList() + ", toString()=" + super.toString() + "]";
	}
}
