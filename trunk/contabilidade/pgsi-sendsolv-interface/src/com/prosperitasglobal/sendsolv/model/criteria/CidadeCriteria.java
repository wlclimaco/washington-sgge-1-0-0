package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;
import java.util.List;

import com.prosperitasglobal.sendsolv.model.CdStatusTypeEnum;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class CidadeCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The recipient id. */
	private Integer id;

	/** The location name. */
	private String cidade;

	/** The organization name. */
	private List<CdStatusTypeEnum> status;

	/**
	 * The Constructor.
	 */
	public CidadeCriteria()
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
	public List<CdStatusTypeEnum> getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(List<CdStatusTypeEnum> status)
	{
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "CidadeCriteria [getNome()=" + getNome() + ", getCidade()=" + getCidade() + ", getStatus()="
				+ getStatus() + ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
