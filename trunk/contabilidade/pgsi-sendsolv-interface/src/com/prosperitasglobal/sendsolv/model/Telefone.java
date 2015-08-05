package com.prosperitasglobal.sendsolv.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Telefone extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String ddd;

	/** The description. */
	private String numero;

	private TelefoneTypeEnum telefoneTypeEnum;

	/**
	 * Default constructor.
	 */
	public Telefone()
	{
		super();
	}

	public Integer getTelefoneTypeEnumValue()
	{
		if (telefoneTypeEnum != null)
		{
			return telefoneTypeEnum.getValue();
		}
		return null;
	}

	public void setTelefoneTypeEnumValue(Integer acaoTypeValue)
	{
		telefoneTypeEnum = TelefoneTypeEnum.enumForValue(acaoTypeValue);
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
	 * @return the ddd
	 */
	public String getDdd()
	{
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(String ddd)
	{
		this.ddd = ddd;
	}

	/**
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * @return the telefoneTypeEnum
	 */
	public TelefoneTypeEnum getTelefoneTypeEnum()
	{
		return telefoneTypeEnum;
	}

	/**
	 * @param telefoneTypeEnum the telefoneTypeEnum to set
	 */
	public void setTelefoneTypeEnum(TelefoneTypeEnum telefoneTypeEnum)
	{
		this.telefoneTypeEnum = telefoneTypeEnum;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Telefone [getTelefoneTypeEnumValue()=" + getTelefoneTypeEnumValue() + ", getId()=" + getId()
				+ ", getDdd()=" + getDdd() + ", getNumero()=" + getNumero() + ", getTelefoneTypeEnum()="
				+ getTelefoneTypeEnum() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()=" + getUserId()
				+ ", getNotes()=" + getNotes() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
