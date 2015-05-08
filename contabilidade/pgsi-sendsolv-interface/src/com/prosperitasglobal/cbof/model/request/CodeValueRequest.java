package com.prosperitasglobal.cbof.model.request;

import com.prosperitasglobal.cbof.model.CodeValueEnum;

/**
 * The Class CodeValueRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 2:15:09 PM
 */
public class CodeValueRequest extends FetchByCodeRequest
{

	/** Attributes. */
	private CodeValueEnum codeValueType;

	/**
	 * The Constructor.
	 */
	public CodeValueRequest()
	{

	}

	/**
	 * Gets the code value type.
	 *
	 * @return the code value type
	 */
	public CodeValueEnum getCodeValueType()
	{
		return codeValueType;
	}

	/**
	 * Sets the code value type.
	 *
	 * @param codeValueType the code value type
	 */
	public void setCodeValueType(CodeValueEnum codeValueType)
	{
		this.codeValueType = codeValueType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CodeValueRequest [getCodeValueType()=" + getCodeValueType() + ", toString()=" + super.toString() + "]";
	}

}