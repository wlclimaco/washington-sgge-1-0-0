package com.qat.samples.sysmgmt.util.model;

import java.util.Date;

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

	public Telefone(Integer id, String ddd, String numero, TelefoneTypeEnum telefoneTypeEnum,
			PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
		this.telefoneTypeEnum = telefoneTypeEnum;
		setModelAction(modelAction);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
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

	@Override
	public String toString()
	{
		return "Telefone [getTelefoneTypeEnumValue()=" + getTelefoneTypeEnumValue() + ", getId()=" + getId()
				+ ", getDdd()=" + getDdd() + ", getNumero()=" + getNumero() + ", getTelefoneTypeEnum()="
				+ getTelefoneTypeEnum() + ", toString()=" + super.toString() + "]";
	}

}
