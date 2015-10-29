package com.qat.samples.sysmgmt.financeiro;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Financeiro extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String logradouro;

	/** The description. */
	private String cidade;

	/** The estado. */
	private String estado;

	/** The bairro. */
	private String bairro;

	/** The numero. */
	private String numero;

	/** The cep. */
	private String cep;

	/** The tipo endereco. */
	private String tipoEndereco;

	/**
	 * Default constructor.
	 */
	public Financeiro()
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
	 * Gets the tipo endereco.
	 * 
	 * @return the tipoEndereco
	 */
	public String getTipoEndereco()
	{
		return tipoEndereco;
	}

	/**
	 * Sets the tipo endereco.
	 * 
	 * @param tipoEndereco the tipoEndereco to set
	 */
	public void setTipoEndereco(String tipoEndereco)
	{
		this.tipoEndereco = tipoEndereco;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Endereco [getId()=" + getId() + ", getLogradouro()=" + getLogradouro() + ", getCidade()=" + getCidade()
				+ ", getEstado()=" + getEstado() + ", getBairro()=" + getBairro() + ", getNumero()=" + getNumero()
				+ ", getCep()=" + getCep() + ", getTipoEndereco()=" + getTipoEndereco() + ", toString()="
				+ super.toString() + "]";
	}

}
