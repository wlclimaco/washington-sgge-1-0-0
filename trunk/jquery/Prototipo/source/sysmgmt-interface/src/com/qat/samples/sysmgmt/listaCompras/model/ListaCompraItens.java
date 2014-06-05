package com.qat.samples.sysmgmt.listaCompras.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;
import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Cidade", propOrder = {"id", "estado", "cidade"})
public class ListaCompraItens extends Util
{

	/** The id. */
	private Integer id;

	/** The produtos. */
	private Produto produto;

	/** The supermercado. */
	private Supermercado supermercado;

	/**
	 * Instantiates a new cidade.
	 */
	public ListaCompraItens()
	{

	}

	/**
	 * Instantiates a new lista compra itens.
	 * 
	 * @param id the id
	 * @param produto the produto
	 * @param supermercado the supermercado
	 */
	public ListaCompraItens(Integer id, Produto produto, Supermercado supermercado)
	{
		super();
		this.id = id;
		this.produto = produto;
		this.supermercado = supermercado;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#getId()
	 */
	@Override
	public Integer getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#setId(java.lang.Integer)
	 */
	@Override
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the produtos.
	 * 
	 * @return the produtos
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * Sets the produtos.
	 * 
	 * @param produto the new produtos
	 */
	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	/**
	 * Gets the supermercado.
	 * 
	 * @return the supermercado
	 */
	public Supermercado getSupermercado()
	{
		return supermercado;
	}

	/**
	 * Sets the supermercado.
	 * 
	 * @param supermercado the new supermercado
	 */
	public void setSupermercado(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#toString()
	 */
	@Override
	public String toString()
	{
		return "ListaCompraItens [getId()=" + getId() + ", getProduto()=" + getProduto() + ", getSupermercado()="
				+ getSupermercado() + ", toString()=" + super.toString() + "]";
	}

}
