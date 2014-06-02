package com.qat.samples.sysmgmt.endereco.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Bundle", propOrder = {"id", "code", "description", "price"})
public class Endereco extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private String logradouro;

	/** The bairro. */
	private String bairro;

	/** The estado. */
	private String estado;

	/** The cidade. */
	private String cidade;

	/** The numero. */
	private String numero;

	/** The description. */
	private String cep;

	/** The price. */
	private String nome;

	/** The complemento. */
	private String complemento;

	/**
	 * Instantiates a new bundle.
	 */
	public Endereco()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Endereco(Integer id)
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
	 * Gets the logradouro.
	 * 
	 * @return the logradouro
	 */
	public String getLogradouro()
	{
		return logradouro;
	}

	/**
	 * Sets the logradouro.
	 * 
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}

	/**
	 * Gets the bairro.
	 * 
	 * @return the bairro
	 */
	public String getBairro()
	{
		return bairro;
	}

	/**
	 * Sets the bairro.
	 * 
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	/**
	 * Gets the estado.
	 * 
	 * @return the estado
	 */
	public String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado.
	 * 
	 * @param estado the estado to set
	 */
	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the cidade.
	 * 
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * Sets the cidade.
	 * 
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/**
	 * Gets the numero.
	 * 
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero.
	 * 
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the cep.
	 * 
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * Sets the cep.
	 * 
	 * @param cep the cep to set
	 */
	public void setCep(String cep)
	{
		this.cep = cep;
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
	 * Gets the complemento.
	 * 
	 * @return the complemento
	 */
	public String getComplemento()
	{
		return complemento;
	}

	/**
	 * Sets the complemento.
	 * 
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Endereco [getId()=" + getId() + ", getLogradouro()=" + getLogradouro() + ", getBairro()=" + getBairro()
				+ ", getEstado()=" + getEstado() + ", getCidade()=" + getCidade() + ", getNumero()=" + getNumero()
				+ ", getCep()=" + getCep() + ", getNome()=" + getNome() + ", getComplemento()=" + getComplemento()
				+ ", toString()=" + super.toString() + "]";
	}

}
