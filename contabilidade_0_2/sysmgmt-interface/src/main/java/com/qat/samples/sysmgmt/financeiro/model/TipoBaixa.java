package com.qat.samples.sysmgmt.financeiro.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class TipoBaixa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String descricao;

	/**
	 * Default constructor.
	 */
	public TipoBaixa()
	{
		super();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	@Override
	public String toString()
	{
		return "TipoBaixa [getId()=" + getId() + ", getDescricao()=" + getDescricao() + ", toString()="
				+ super.toString() + "]";
	}

}
