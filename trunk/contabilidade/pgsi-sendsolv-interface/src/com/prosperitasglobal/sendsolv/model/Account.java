package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModel;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Account extends QATModel
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The account number. */
	private Integer number;

	/** The type of an account. */
	private AccountTypeEnum type;

	/**
	 * Default constructor.
	 */
	public Account()
	{
		super();
	}

	/**
	 * Get the SendSolv id for the account.
	 *
	 * @return The id.
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Set the SendSolv id for the account.
	 *
	 * @param id The id to set.
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Get the account number.
	 *
	 * @return The number.
	 */
	public Integer getNumber()
	{
		return number;
	}

	/**
	 * Set the account number.
	 *
	 * @param number The account number to set.
	 */
	public void setNumber(Integer number)
	{
		this.number = number;
	}

	/**
	 * Get the account type.
	 *
	 * @return The type.
	 */
	public AccountTypeEnum getType()
	{
		return type;
	}

	/**
	 * Get the enumeration value for the account type.
	 *
	 * @return The value for the type.
	 */
	public Integer getTypeValue()
	{
		if (getType() == null)
		{
			return null;
		}

		return getType().getValue();
	}

	/**
	 * Set the account type.
	 *
	 * @param type The type to set.
	 */
	public void setType(AccountTypeEnum type)
	{
		this.type = type;
	}

	/**
	 * Set the enumeration by the value of the account type.
	 *
	 * @param typeValue The value of the type enumeration to set.
	 */
	public void setTypeValue(Integer typeValue)
	{
		setType(AccountTypeEnum.enumForValue(typeValue));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Account [getId()=" + getId() + ", getNumber()=" + getNumber() + ", getType()=" + getType()
				+ ", getTypeValue()=" + getTypeValue() + ", toString()=" + super.toString() + "]";
	}
}
