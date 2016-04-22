package com.qat.samples.sysmgmt.dp.model.criteria;

import java.io.Serializable;

import com.qat.samples.sysmgmt.beneficios.model.Beneficios;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class FuncionarioCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The business id. */
	private Integer emprId;

	/** The recipient id. */
	private Integer funcionarioId;

	/** The location name. */
	private String cidade;

	/** The organization name. */
	private String status;

	/** The primary phone number. */
	private Beneficios beneficios;

	/**
	 * The Constructor.
	 */
	public FuncionarioCriteria()
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
	 * @return the funcionarioId
	 */
	public Integer getFuncionarioId()
	{
		return funcionarioId;
	}

	/**
	 * @param funcionarioId the funcionarioId to set
	 */
	public void setFuncionarioId(Integer funcionarioId)
	{
		this.funcionarioId = funcionarioId;
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
	 * @return the beneficios
	 */
	public Beneficios getBeneficios()
	{
		return beneficios;
	}

	/**
	 * @param beneficios the beneficios to set
	 */
	public void setBeneficios(Beneficios beneficios)
	{
		this.beneficios = beneficios;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "FuncionarioCriteria [getNome()=" + getNome() + ", getEmprId()=" + getEmprId()
				+ ", getFuncionarioId()=" + getFuncionarioId() + ", getCidade()=" + getCidade() + ", getStatus()="
				+ getStatus() + ", getBeneficios()=" + getBeneficios() + ", toString()=" + super.toString() + "]";
	}

}
