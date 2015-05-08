package com.prosperitasglobal.sendsolv.model;

import java.util.ArrayList;
import java.util.List;

import com.qat.framework.model.QATModelOL;
import com.qat.framework.validation.ValidationUtil;

/**
 * This Class represents an Automated Clearing House of money transfers.
 */
@SuppressWarnings("serial")
public class AutomatedClearingHouse extends QATModelOL
{
	/** The SendSolv id for the automated clearing house. */
	private Integer id;

	/** The name of the automated clearing house. */
	private String name;

	/** The list of payers that the automated clearing house does business with. */
	private List<Payer> payerList;

	/**
	 * Default constructor.
	 */
	public AutomatedClearingHouse()
	{
		super();
	}

	/**
	 * Get the SendSolv id for the automated clearing house.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the automated clearing house.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get name of the automated clearing house.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set name of the automated clearing house.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get the list of payers that the automated clearing house does business with.
	 *
	 * @return The list of payers.
	 */
	public List<Payer> getPayerList()
	{
		if (ValidationUtil.isNull(payerList))
		{
			setPayerList(new ArrayList<Payer>());
		}

		return payerList;
	}

	/**
	 * Set the list of payers that the automated clearing house does business with.
	 *
	 * @param payerList The list of payers to set.
	 */
	public void setPayerList(List<Payer> payerList)
	{
		this.payerList = payerList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AutomatedClearingHouse [getId()=" + getId() + ", getName()=" + getName() + ", getPayerList()="
				+ getPayerList() + ", toString()=" + super.toString() + "]";
	}
}
