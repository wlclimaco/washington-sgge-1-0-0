package com.prosperitasglobal.sendsolv.model;

import com.qat.framework.model.QATModelOL;

/**
 * The Class PersonSecurityAnswer.
 */
@SuppressWarnings("serial")
public class PersonSecurityAnswer extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The answer text. */
	private String answerText;

	/** The security question. */
	private SecurityQuestion securityQuestion;

	/** The parent key. */
	private Integer parentKey = -1;

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
	 * Gets the answer text.
	 *
	 * @return the answerText
	 */
	public String getAnswerText()
	{
		return answerText;
	}

	/**
	 * Sets the answer text.
	 *
	 * @param answerText the answerText to set
	 */
	public void setAnswerText(String answerText)
	{
		this.answerText = answerText;
	}

	/**
	 * Gets the security question.
	 *
	 * @return the securityQuestion
	 */
	public SecurityQuestion getSecurityQuestion()
	{
		return securityQuestion;
	}

	/**
	 * Sets the security question.
	 *
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(SecurityQuestion securityQuestion)
	{
		this.securityQuestion = securityQuestion;
	}

	/**
	 * Gets the parent key.
	 *
	 * @return the parentKey
	 */
	public Integer getParentKey()
	{
		return parentKey;
	}

	/**
	 * Sets the parent key.
	 *
	 * @param parentKey the parentKey to set
	 */
	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PersonSecurityAnswer [getId()=" + getId() + ", getAnswerText()=" + getAnswerText()
				+ ", getSecurityQuestion()=" + getSecurityQuestion() + ", getParentKey()=" + getParentKey()
				+ ", toString()=" + super.toString() + "]";
	}

}
