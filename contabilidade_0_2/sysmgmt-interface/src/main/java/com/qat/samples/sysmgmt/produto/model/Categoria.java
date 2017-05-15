package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Categoria extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String categoria;

	private Double margem;



	/**
	 * Default constructor.
	 */
	public Categoria()
	{
		super();
	}

	public Categoria(int i, String string) {
		// TODO Auto-generated constructor stub
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
		return margem;
	}

	public void setValor(Double valor) {
		this.margem = valor;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getMargem() {
		return margem;
	}

	public void setMargem(Double margem) {
		this.margem = margem;
	}

	@Override
	public String toString() {
		return "Categoria [getId()=" + getId() + ", getValor()=" + getValor() + ", getCategoria()=" + getCategoria()
				+ ", getMargem()=" + getMargem() + ", toString()=" + super.toString() + "]";
	}
}
