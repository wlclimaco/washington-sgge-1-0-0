package com.prosperitasglobal.sendsolv.payer.model;

import com.qat.framework.model.QATModel;

/**
 * Represents a payer branch in the SendSolv system.
 */
@SuppressWarnings("serial")
public class PayerBranch extends QATModel
{
	/** The SendSolv id for the branch */
	private Integer id;

	/** The parent key payer address id associated with the branch. */
	private Integer payerAddressId;

	/** The payer branch code from BTS */
	private String achPayerBranchCode;

	/** The name of the branch */
	private String name;

	/** The payerId of the associated payer */
	private Integer payerId;

	/**
	 * Get the SendSolv branch id
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv branch id
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the payer address id
	 *
	 * @return the payerAddressId
	 */
	public Integer getPayerAddressId()
	{
		return payerAddressId;
	}

	/**
	 * Set the payer address id
	 *
	 * @param payerAddressId the payerAddressId to set
	 */
	public void setPayerAddressId(Integer payerAddressId)
	{
		this.payerAddressId = payerAddressId;
	}

	/**
	 * Get the Automated Clearing House payer branch code
	 *
	 * @return the achPayerBranchCode
	 */
	public String getAchPayerBranchCode()
	{
		return achPayerBranchCode;
	}

	/**
	 * Set the Automated Clearing House payer branch code
	 *
	 * @param achPayerBranchCode the achPayerBranchCode to set
	 */
	public void setAchPayerBranchCode(String achPayerBranchCode)
	{
		this.achPayerBranchCode = achPayerBranchCode;
	}

	/**
	 * Get the branch name
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the branch name
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the payerId
	 */
	public Integer getPayerId()
	{
		return payerId;
	}

	/**
	 * @param payerId the payerId to set
	 */
	public void setPayerId(Integer payerId)
	{
		this.payerId = payerId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PayerBranch [getId()=" + getId() + ", getPayerAddressId()=" + getPayerAddressId()
				+ ", getAchPayerBranchCode()=" + getAchPayerBranchCode() + ", getName()=" + getName()
				+ ", getPayerId()=" + getPayerId() + ", toString()=" + super.toString() + "]";
	}

}
