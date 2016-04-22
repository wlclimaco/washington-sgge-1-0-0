package com.qat.samples.sysmgmt.util.model;

import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Configuracao extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String marca;

	/** The description. */
	private String fabricante;

	/** The estado. */
	private List<Email> emailList;

	/** The bairro. */
	private List<Endereco> enderecoList;

	/** The numero. */
	private List<Telefone> telefoneList;

	/**
	 * Default constructor.
	 */
	public Configuracao()
	{
		super();
	}

	public Configuracao(Integer id)
	{
		super();
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the marca
	 */
	public String getMarca()
	{
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	/**
	 * @return the fabricante
	 */
	public String getFabricante()
	{
		return fabricante;
	}

	/**
	 * @param fabricante the fabricante to set
	 */
	public void setFabricante(String fabricante)
	{
		this.fabricante = fabricante;
	}

	/**
	 * @return the emailList
	 */
	public List<Email> getEmailList()
	{
		return emailList;
	}

	/**
	 * @param emailList the emailList to set
	 */
	public void setEmailList(List<Email> emailList)
	{
		this.emailList = emailList;
	}

	/**
	 * @return the enderecoList
	 */
	public List<Endereco> getEnderecoList()
	{
		return enderecoList;
	}

	/**
	 * @param enderecoList the enderecoList to set
	 */
	public void setEnderecoList(List<Endereco> enderecoList)
	{
		this.enderecoList = enderecoList;
	}

	/**
	 * @return the telefoneList
	 */
	public List<Telefone> getTelefoneList()
	{
		return telefoneList;
	}

	/**
	 * @param telefoneList the telefoneList to set
	 */
	public void setTelefoneList(List<Telefone> telefoneList)
	{
		this.telefoneList = telefoneList;
	}

	@Override
	public String toString()
	{
		return "Configuracao [getId()=" + getId() + ", getMarca()=" + getMarca() + ", getFabricante()="
				+ getFabricante() + ", getEmailList()=" + getEmailList() + ", getEnderecoList()=" + getEnderecoList()
				+ ", getTelefoneList()=" + getTelefoneList() + ", toString()=" + super.toString() + "]";
	}

}
