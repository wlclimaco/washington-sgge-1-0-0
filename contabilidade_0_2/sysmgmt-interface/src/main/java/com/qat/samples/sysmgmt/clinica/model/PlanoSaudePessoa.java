package com.qat.samples.sysmgmt.clinica.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PlanoSaudePessoa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private PlanoSaude planoId;

	private Integer dataValidade;

	private Integer numeroCartao;

	private String plano;

	public PlanoSaudePessoa()
	{

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

	public PlanoSaude getPlanoId()
	{
		return planoId;
	}

	public void setPlanoId(PlanoSaude planoId)
	{
		this.planoId = planoId;
	}

	public Integer getNumeroCartao()
	{
		return numeroCartao;
	}

	public void setNumeroCartao(Integer numeroCartao)
	{
		this.numeroCartao = numeroCartao;
	}

	public Integer getDataValidade()
	{
		return dataValidade;
	}

	public void setDataValidade(Integer dataValidade)
	{
		this.dataValidade = dataValidade;
	}

	public String getPlano()
	{
		return plano;
	}

	public void setPlano(String plano)
	{
		this.plano = plano;
	}

	@Override
	public String toString()
	{
		return "PlanoSaudePessoa [getId()=" + getId() + ", getPlanoId()=" + getPlanoId() + ", getNumeroCartao()="
				+ getNumeroCartao() + ", getDataValidade()=" + getDataValidade() + ", getPlano()=" + getPlano()
				+ ", toString()=" + super.toString() + "]";
	}

}
