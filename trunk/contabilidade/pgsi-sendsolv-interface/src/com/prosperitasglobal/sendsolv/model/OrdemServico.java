package com.prosperitasglobal.sendsolv.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class OrdemServico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The description. */
	private String nome;

	/** The estado. */
	private Long data;

	/** The bairro. */
	private OrdemServicoTypes typeId;

	/** The numero. */
	private String assunto;

	/** The tipo endereco. */
	private List<OrdemServicoItens> ordemStatusList;

	/**
	 * Default constructor.
	 */
	public OrdemServico()
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
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * @return the typeId
	 */
	public OrdemServicoTypes getTypeId()
	{
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(OrdemServicoTypes typeId)
	{
		this.typeId = typeId;
	}

	/**
	 * @return the assunto
	 */
	public String getAssunto()
	{
		return assunto;
	}

	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	/**
	 * @return the ordemStatusList
	 */
	public List<OrdemServicoItens> getOrdemStatusList()
	{
		return ordemStatusList;
	}

	/**
	 * @param ordemStatusList the ordemStatusList to set
	 */
	public void setOrdemStatusList(List<OrdemServicoItens> ordemStatusList)
	{
		this.ordemStatusList = ordemStatusList;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "OrdemServico [getId()=" + getId() + ", getNome()=" + getNome() + ", getData()=" + getData()
				+ ", getTypeId()=" + getTypeId() + ", getAssunto()=" + getAssunto() + ", getOrdemStatusList()="
				+ getOrdemStatusList() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
