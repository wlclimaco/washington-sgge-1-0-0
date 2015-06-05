package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Contato extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Long dataContato;

	private List<ContatoItens> contatoItensList;

	/**
	 * Default constructor.
	 */
	public Contato()
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the dataContato
	 */
	public Long getDataContato()
	{
		return dataContato;
	}

	/**
	 * @param dataContato the dataContato to set
	 */
	public void setDataContato(Long dataContato)
	{
		this.dataContato = dataContato;
	}

	/**
	 * @return the contatoItensList
	 */
	public List<ContatoItens> getContatoItensList()
	{
		return contatoItensList;
	}

	/**
	 * @param contatoItensList the contatoItensList to set
	 */
	public void setContatoItensList(List<ContatoItens> contatoItensList)
	{
		this.contatoItensList = contatoItensList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Contato [getId()=" + getId() + ", getDataContato()=" + getDataContato() + ", getContatoItensList()="
				+ getContatoItensList() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
