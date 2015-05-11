package com.prosperitasglobal.sendsolv.model.criteria;

import java.io.Serializable;

/**
 * The Class MemberCriteria.
 */
@SuppressWarnings("serial")
public class TelaCriteria implements Serializable
{

	/** The member. */
	private String nome;

	/** The business id. */
	private Integer empresa;

	/** The recipient id. */
	private Integer aba;

	/** The location name. */
	private String field;

	/** The organization name. */
	private String tipo;

	/**
	 * The Constructor.
	 */
	public TelaCriteria()
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

	public Integer getAba()
	{
		return aba;
	}

	public void setAba(Integer aba)
	{
		this.aba = aba;
	}

	public String getField()
	{
		return field;
	}

	public void setField(String field)
	{
		this.field = field;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TelaCriteria [getNome()=" + getNome() + ", getEmpresa()=" + getEmpresa() + ", getAba()=" + getAba()
				+ ", getField()=" + getField() + ", getTipo()=" + getTipo() + ", toString()=" + super.toString() + "]";
	}

}
