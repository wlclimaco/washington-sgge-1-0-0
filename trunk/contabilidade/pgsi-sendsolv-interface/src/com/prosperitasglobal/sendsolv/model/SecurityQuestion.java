package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModelOL;

/**
 * The Class SecurityQuestion.
 */
@SuppressWarnings("serial")
public class SecurityQuestion extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The security question key. */
	private String securityQuestionKey;

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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the security question key.
	 *
	 * @return the securityQuestionKey
	 */
	public String getSecurityQuestionKey()
	{
		return securityQuestionKey;
	}

	/**
	 * Sets the security question key.
	 *
	 * @param securityQuestionKey the securityQuestionKey to set
	 */
	public void setSecurityQuestionKey(String securityQuestionKey)
	{
		this.securityQuestionKey = securityQuestionKey;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SecurityQuestion [getId()=" + getId() + ", getSecurityQuestionKey()=" + getSecurityQuestionKey()
				+ ", toString()=" + super.toString() + "]";
	}

}
