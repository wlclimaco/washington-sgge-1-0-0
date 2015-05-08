package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.QATModel;

/**
 * The Class Country.
 */
@SuppressWarnings("serial")
public class Currency extends QATModel
{
	/** The code for the currency. */
	private String code;

	/** The name of the currency. */
	private String name;

	/**
	 * Default constructor.
	 */
	public Currency()
	{
		super();
	}

	/**
	 * Constructor that accepts the code for a currency.
	 *
	 * @param code The code for the currency.
	 */
	public Currency(String code)
	{
		this();
		setCode(code);
	}

	/**
	 * Constructor that accepts the code and name for a currency.
	 *
	 * @param code The code for the currency.
	 * @param name The name of the currency.
	 */
	public Currency(String code, String name)
	{
		this(code);
		setName(name);
	}

	/**
	 * Get the code of the currency.
	 *
	 * @return The code.
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Set the code of the currency.
	 *
	 * @param code The code to set.
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Get the name of the currency.
	 *
	 * @return The name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the name of the currency.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Currency [getCode()=" + getCode() + ", getName()=" + getName() + ", toString()=" + super.toString()
				+ "]";
	}
}
