package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Email extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String email;

	/** The description. */
	private EmailTypeEnum emailType;

	/**
	 * Default constructor.
	 */
	public Email()
	{
		super();
	}

	public Integer getEmailTypeEnumValue()
	{
		if (emailType != null)
		{
			return emailType.getValue();
		}
		return null;
	}

	public void setEmailTypeEnumValue(Integer acaoTypeValue)
	{
		emailType = EmailTypeEnum.enumForValue(acaoTypeValue);
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the emailType
	 */
	public EmailTypeEnum getEmailType()
	{
		return emailType;
	}

	/**
	 * @param emailType the emailType to set
	 */
	public void setEmailType(EmailTypeEnum emailType)
	{
		this.emailType = emailType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Email [getEmailTypeEnumValue()=" + getEmailTypeEnumValue() + ", getId()=" + getId() + ", getEmail()="
				+ getEmail() + ", getEmailType()=" + getEmailType() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()="
				+ getUserId() + ", getNotes()=" + getNotes() + ", toString()=" + super.toString()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
