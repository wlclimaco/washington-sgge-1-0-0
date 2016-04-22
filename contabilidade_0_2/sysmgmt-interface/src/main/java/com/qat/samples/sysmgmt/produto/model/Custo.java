package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Custo extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer entidadeId;

	/** The type of an account. */
	private CustoItem custo;

	private Double valor;

	/**
	 * Default constructor.
	 */
	public Custo()
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the custo
	 */
	public CustoItem getCusto()
	{
		return custo;
	}

	/**
	 * @param custo the custo to set
	 */
	public void setCusto(CustoItem custo)
	{
		this.custo = custo;
	}

	/**
	 * @return the valor
	 */
	public Double getValor()
	{
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	/**
	 * @return the entidadeId
	 */
	public Integer getEntidadeId()
	{
		return entidadeId;
	}

	/**
	 * @param entidadeId the entidadeId to set
	 */
	public void setEntidadeId(Integer entidadeId)
	{
		this.entidadeId = entidadeId;
	}

	@Override
	public String toString()
	{
		return "Custo [getId()=" + getId() + ", getCusto()=" + getCusto() + ", getValor()=" + getValor()
				+ ", getEntidadeId()=" + getEntidadeId() + ", toString()=" + super.toString() + "]";
	}

}
