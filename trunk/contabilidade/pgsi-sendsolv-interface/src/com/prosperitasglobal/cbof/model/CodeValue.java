package com.prosperitasglobal.cbof.model;

import com.qat.framework.model.QATModel;

/**
 * The Class CodeValue.
 *
 * @author abarros
 * @version 1.0
 * @created 21-Jul-2014 9:59:53 AM
 */
@SuppressWarnings("serial")
public class CodeValue extends QATModel
{

	/** The id. */
	private Integer id;

	/** The code. */
	private String code;

	/** The value. */
	private String value;

	/**
	 * The Constructor.
	 */
	public CodeValue()
	{

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CodeValue [getId()=" + getId() + ", getCode()=" + getCode() + ", getValue()=" + getValue()
				+ ", toString()=" + super.toString() + "]";
	}

}