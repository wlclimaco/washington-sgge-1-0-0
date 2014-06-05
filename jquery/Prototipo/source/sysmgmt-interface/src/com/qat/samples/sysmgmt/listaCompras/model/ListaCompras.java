package com.qat.samples.sysmgmt.listaCompras.model;

import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.samples.sysmgmt.util.Util;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Cidade", propOrder = {"id", "estado", "cidade"})
public class ListaCompras extends Util
{

	/** The id. */
	private Integer id;

	/** The code. */
	private Date dataLista;

	/** The description. */
	private String descricao;

	/** The nome. */
	private String nome;

	/** The produtos. */
	private List<ListaCompraItens> produtos;

	/**
	 * Instantiates a new cidade.
	 */
	public ListaCompras()
	{

	}

	/**
	 * Instantiates a new lista compras.
	 * 
	 * @param id the id
	 * @param dataLista the data lista
	 * @param descricao the descricao
	 * @param nome the nome
	 */
	public ListaCompras(Integer id, Date dataLista, String descricao, String nome)
	{
		super();
		this.id = id;
		this.dataLista = dataLista;
		this.descricao = descricao;
		this.nome = nome;
	}

	/**
	 * Instantiates a new lista compras.
	 * 
	 * @param id the id
	 * @param dataLista the data lista
	 * @param descricao the descricao
	 * @param nome the nome
	 * @param produtos the produtos
	 */
	public ListaCompras(Integer id, Date dataLista, String descricao, String nome, List<ListaCompraItens> produtos)
	{
		super();
		this.id = id;
		this.dataLista = dataLista;
		this.descricao = descricao;
		this.nome = nome;
		this.produtos = produtos;
	}

	/**
	 * Instantiates a new lista compras.
	 * 
	 * @param id the id
	 */
	public ListaCompras(Integer id)
	{
		super();
		this.id = id;
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
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public Date getDataLista()
	{
		return dataLista;
	}

	/**
	 * Sets the code.
	 * 
	 * @param dataLista the new code
	 */
	public void setDataLista(Date dataLista)
	{
		this.dataLista = dataLista;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * Sets the description.
	 * 
	 * @param descricao the new description
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	/**
	 * Gets the nome.
	 * 
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * Sets the nome.
	 * 
	 * @param nome the new nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the produtos.
	 * 
	 * @return the produtos
	 */
	public List<ListaCompraItens> getProdutos()
	{
		return produtos;
	}

	/**
	 * Sets the produtos.
	 * 
	 * @param produtos the new produtos
	 */
	public void setProdutos(List<ListaCompraItens> produtos)
	{
		this.produtos = produtos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.samples.sysmgmt.util.Util#toString()
	 */
	@Override
	public String toString()
	{
		return "ListaCompras [getId()=" + getId() + ", getDataLista()=" + getDataLista() + ", getDescricao()="
				+ getDescricao() + ", getNome()=" + getNome() + ", getProdutos()=" + getProdutos() + ", toString()="
				+ super.toString() + "]";
	}

}
