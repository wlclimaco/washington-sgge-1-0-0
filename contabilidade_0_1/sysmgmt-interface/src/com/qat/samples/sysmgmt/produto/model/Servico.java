package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Servico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer entidadeId;

	/** The type of an account. */
	private String nome;

	private String descricao;

	private Integer tipoPreco;

	private Double valor;

	/**
	 * Default constructor.
	 */
	public Servico()
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

	public Integer getEntidadeId()
	{
		return entidadeId;
	}

	public void setEntidadeId(Integer entidadeId)
	{
		this.entidadeId = entidadeId;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public Integer getTipoPreco()
	{
		return tipoPreco;
	}

	public void setTipoPreco(Integer tipoPreco)
	{
		this.tipoPreco = tipoPreco;
	}

	public Double getValor()
	{
		return valor;
	}

	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	@Override
	public String toString()
	{
		return "Servico [getId()=" + getId() + ", getEntidadeId()=" + getEntidadeId() + ", getNome()=" + getNome()
				+ ", getDescricao()=" + getDescricao() + ", getTipoPreco()=" + getTipoPreco() + ", getValor()="
				+ getValor() + ", toString()=" + super.toString() + "]";
	}

}
