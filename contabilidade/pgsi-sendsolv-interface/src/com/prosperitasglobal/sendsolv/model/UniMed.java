package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class UniMed extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String unimed;

	/** The description. */
	private String sigla;

	/**
	 * Default constructor.
	 */
	public UniMed()
	{
		super();
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
	 * @return the unimed
	 */
	public String getUnimed()
	{
		return unimed;
	}

	/**
	 * @param unimed the unimed to set
	 */
	public void setUnimed(String unimed)
	{
		this.unimed = unimed;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla()
	{
		return sigla;
	}

	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "UniMed [getId()=" + getId() + ", getUnimed()=" + getUnimed() + ", getSigla()=" + getSigla()
				+ ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue()
				+ ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()="
				+ getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum()
				+ ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite()
				+ ", toString()=" + super.toString() + ", getModelAction()=" + getModelAction() + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()="
				+ getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
