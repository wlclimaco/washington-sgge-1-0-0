package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class CustoItem extends ModelCosmeDamiao
{

	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String custo;

	private Integer custoDesp;

	public CustoItem(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * Default constructor.
	 */
	public CustoItem()
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
	public String getCusto()
	{
		return custo;
	}

	/**
	 * @param custo the custo to set
	 */
	public void setCusto(String custo)
	{
		this.custo = custo;
	}

	/**
	 * @return the custoDesp
	 */
	public Integer getCustoDesp()
	{
		return custoDesp;
	}

	/**
	 * @param custoDesp the custoDesp to set
	 */
	public void setCustoDesp(Integer custoDesp)
	{
		this.custoDesp = custoDesp;
	}

	@Override
	public String toString()
	{
		return "CustoItem [getId()=" + getId() + ", getCusto()=" + getCusto() + ", getCustoDesp()=" + getCustoDesp()
				+ ", toString()=" + super.toString() + "]";
	}

}
