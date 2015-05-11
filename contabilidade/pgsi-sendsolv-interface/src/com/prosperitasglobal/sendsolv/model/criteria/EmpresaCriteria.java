package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class EmpresaCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The business id. */
	private Integer empresa;

	/** The recipient id. */
	private Integer Id;

	/** The location name. */
	private String cidade;

	/** The organization name. */
	private String status;

	/** The primary phone number. */
	private String regime;

	/**
	 * The Constructor.
	 */
	public EmpresaCriteria()
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EmpresaCriteria [getNome()=" + getNome() + ", getEmpresa()=" + getEmpresa() + ", getCidade()="
				+ getCidade() + ", getStatus()=" + getStatus() + ", getId()=" + getId() + ", getRegime()="
				+ getRegime() + ", toString()=" + super.toString() + "]";
	}

}
