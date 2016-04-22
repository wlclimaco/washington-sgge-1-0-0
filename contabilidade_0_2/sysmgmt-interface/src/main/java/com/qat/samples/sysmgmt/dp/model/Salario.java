package com.qat.samples.sysmgmt.dp.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Salario extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Profissao profissao;

	/** The type of an account. */
	private Long data;

	/** The description. */
	private double valor;

	/**
	 * Default constructor.
	 */
	public Salario()
	{
		super();
	}

	public Salario(Integer id, Long data, double valor)
	{
		super();
		this.id = id;
		this.data = data;
		this.valor = valor;
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * @return the valor
	 */
	public double getValor()
	{
		return valor;
	}

	public Profissao getProfissao()
	{
		return profissao;
	}

	public void setProfissao(Profissao profissao)
	{
		this.profissao = profissao;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor)
	{
		this.valor = valor;
	}

	@Override
	public String toString()
	{
		return "Salario [getId()=" + getId() + ", getData()=" + getData() + ", getValor()=" + getValor()
				+ ", getProfissao()=" + getProfissao() + ", toString()=" + super.toString() + "]";
	}

}
