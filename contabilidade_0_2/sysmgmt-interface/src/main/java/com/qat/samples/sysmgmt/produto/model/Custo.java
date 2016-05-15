package com.qat.samples.sysmgmt.produto.model;

import java.util.List;

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

	private Double valor;

	/** The type of an account. */
	private List<CustoItens> custoItens;



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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<CustoItens> getCustoItens() {
		return custoItens;
	}

	public void setCustoItens(List<CustoItens> custoItens) {
		this.custoItens = custoItens;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Custo [getId()=" + getId() + ", getValor()=" + getValor() + ", getCustoItens()=" + getCustoItens()
				+ ", toString()=" + super.toString() + "]";
	}



}
