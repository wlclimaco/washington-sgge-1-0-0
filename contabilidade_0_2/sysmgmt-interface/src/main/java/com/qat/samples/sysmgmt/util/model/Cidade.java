package com.qat.samples.sysmgmt.util.model;

import com.qat.samples.sysmgmt.estado.model.Estado;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Cidade extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String codigo;

	/** The type of an account. */
	private String nome;

	private String cdIBGE;

	private Estado estado;

	private String cep;

	private String municipio;

	/**
	 * Default constructor.
	 */
	public Cidade()
	{
		super();
	}

	public Cidade(Integer id)
	{
		super();
		this.id = id;
	}

	public Cidade(int i, String string) {
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
	 * @return the cdIBGE
	 */
	public String getCdIBGE()
	{
		return cdIBGE;
	}

	/**
	 * @param cdIBGE the cdIBGE to set
	 */
	public void setCdIBGE(String cdIBGE)
	{
		this.cdIBGE = cdIBGE;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado()
	{
		return estado;
	}

	/**
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep)
	{
		this.cep = cep;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio()
	{
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio)
	{
		this.municipio = municipio;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	@Override
	public String toString()
	{
		return "Cidade [getId()=" + getId() + ", getNome()=" + getNome() + ", getCdIBGE()=" + getCdIBGE()
				+ ", getEstado()=" + getEstado() + ", getCep()=" + getCep() + ", getMunicipio()=" + getMunicipio()
				+ ", getCodigo()=" + getCodigo() + ", toString()=" + super.toString() + "]";
	}

}
