package com.prosperitasglobal.sendsolv.payer.model;

import java.util.ArrayList;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.Payer;
import com.qat.framework.model.QATModel;
import com.qat.framework.validation.ValidationUtil;

/**
 * Represents a payer address in the SendSolv system.
 */
@SuppressWarnings("serial")
public class PayerAddress extends QATModel
{
	/** The SendSolv id for the address */
	private Integer id;

	/** The address */
	private String address;

	/** The city id associated with the address. */
	private Integer cityId;

	/** The zipcode id associated with the address. */
	private Integer zipcodeId;

	/** The list of branches for the address */
	private List<PayerBranch> payerBranchList;

	private Payer payer;

	/**
	 * Get the SendSolv address id
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv address id
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * Set the city id
	 *
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId)
	{
		this.cityId = cityId;
	}

	/**
	 * Get the zipcode id
	 *
	 * @return the zipcode
	 */
	public Integer getZipcodeId()
	{
		return zipcodeId;
	}

	/**
	 * Set the zipcode id
	 *
	 * @param zipcode the zipcode to set
	 */
	public void setZipcodeId(Integer zipcodeId)
	{
		this.zipcodeId = zipcodeId;
	}

	/**
	 * Get the address
	 *
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Set the address
	 *
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Gets the payer branch list
	 *
	 * @return the payerBranchList
	 */
	public List<PayerBranch> getPayerBranchList()
	{
		if (ValidationUtil.isNull(payerBranchList))
		{
			setPayerBranchList(new ArrayList<PayerBranch>());
		}

		return payerBranchList;
	}

	/**
	 * Sets the payer branch list
	 *
	 * @param payerBranchList the payerBranchList to set
	 */
	public void setPayerBranchList(List<PayerBranch> payerBranchList)
	{
		this.payerBranchList = payerBranchList;
	}

	/**
	 * @return the payer
	 */
	public Payer getPayer()
	{
		return payer;
	}

	/**
	 * @param payer the payer to set
	 */
	public void setPayer(Payer payer)
	{
		this.payer = payer;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerAddress [getId()=" + getId() + ", getCityId()=" + getCityId() + ", getZipcodeId()="
				+ getZipcodeId() + ", getAddress()=" + getAddress() + ", getPayerBranchList()=" + getPayerBranchList()
				+ ", getPayer()=" + getPayer() + "]";
	}

}
