package com.qat.samples.sysmgmt.financeiro.model.criteria;

import java.io.Serializable;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class FinanceiroCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The business id. */
	private Integer emprId;

	private Integer id;

	/** The recipient id. */
	private Integer type;

	/** The location name. */
	private String assunto;

	/** The organization name. */
	private String status;

	/**
	 * The Constructor.
	 */
	public FinanceiroCriteria()
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

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ProdutoCriteria [getNome()=" + getNome() + ", getEmprId()=" + getEmprId() + ", getType()="
				+ getType() + ", getAssunto()=" + getAssunto() + ", getStatus()=" + getStatus() + ", toString()="
				+ super.toString() + "]";
	}

}
