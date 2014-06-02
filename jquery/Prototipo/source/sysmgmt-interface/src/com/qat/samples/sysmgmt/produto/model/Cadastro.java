package com.qat.samples.sysmgmt.produto.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Bundle", propOrder = {"id", "code", "description", "price"})
public class Cadastro extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private CadastroTypeEnum type;

	/** The description. */
	private String nome;

	/** The price. */
	private String descricao;

	/**
	 * Instantiates a new bundle.
	 */
	public Cadastro()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Cadastro(Integer id)
	{
		this.id = id;
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public CadastroTypeEnum getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type the type to set
	 */
	public void setType(CadastroTypeEnum type)
	{
		this.type = type;
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
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * Gets the descricao.
	 * 
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * Sets the descricao.
	 * 
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	/**
	 * Instantiates a new cadastro.
	 * 
	 * @param id the id
	 * @param type the type
	 */
	public Cadastro(Integer id, CadastroTypeEnum type)
	{
		super();
		this.id = id;
		this.type = type;
	}

	/**
	 * Instantiates a new cadastro.
	 * 
	 * @param id the id
	 * @param type the type
	 * @param nome the nome
	 */
	public Cadastro(Integer id, CadastroTypeEnum type, String nome)
	{
		super();
		this.id = id;
		this.type = type;
		this.nome = nome;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Cadastro [getId()=" + getId() + ", getType()=" + getType() + ", getNome()=" + getNome()
				+ ", getDescricao()=" + getDescricao() + ", toString()=" + super.toString() + "]";
	}

}
