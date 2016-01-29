package com.qat.samples.sysmgmt.entidade.model.criteria;

import java.io.Serializable;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class FilialCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The business id. */
	private Integer emprId;

	/** The recipient id. */
	private Integer Id;

	/** The location name. */
	private String cidade;

	private Integer entidadeEnum;

	/** The organization name. */
	private String status;

	/** The primary phone number. */
	private String regime;

	/**
	 * The Constructor.
	 */
	public FilialCriteria()
	{
		super();
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
	 * @return the emprId
	 */
	public Integer getEmprId()
	{
		return emprId;
	}

	/**
	 * @param emprId the emprId to set
	 */
	public void setEmprId(Integer emprId)
	{
		this.emprId = emprId;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		Id = id;
	}

	/**
	 * @return the regime
	 */
	public String getRegime()
	{
		return regime;
	}

	/**
	 * @param regime the regime to set
	 */
	public void setRegime(String regime)
	{
		this.regime = regime;
	}

	/**
	 * @return the entidadeEnum
	 */
	public Integer getEntidadeEnum()
	{
		return entidadeEnum;
	}

	/**
	 * @param entidadeEnum the entidadeEnum to set
	 */
	public void setEntidadeEnum(Integer entidadeEnum)
	{
		this.entidadeEnum = entidadeEnum;
	}

	@Override
	public String toString()
	{
		return "FilialCriteria [getNome()=" + getNome() + ", getEmprId()=" + getEmprId() + ", getCidade()="
				+ getCidade() + ", getStatus()=" + getStatus() + ", getId()=" + getId() + ", getRegime()="
				+ getRegime() + ", getEntidadeEnum()=" + getEntidadeEnum() + ", toString()=" + super.toString() + "]";
	}

}
