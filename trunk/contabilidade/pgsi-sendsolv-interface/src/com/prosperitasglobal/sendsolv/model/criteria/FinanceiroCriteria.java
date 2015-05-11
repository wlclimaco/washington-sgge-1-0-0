package com.prosperitasglobal.sendsolv.model.criteria;

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
	private Integer empresa;

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
	 * @return the empresa
	 */
	public Integer getEmpresa()
	{
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Integer empresa)
	{
		this.empresa = empresa;
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
		return "ProdutoCriteria [getNome()=" + getNome() + ", getEmpresa()=" + getEmpresa() + ", getType()="
				+ getType() + ", getAssunto()=" + getAssunto() + ", getStatus()=" + getStatus() + ", toString()="
				+ super.toString() + "]";
	}

}
