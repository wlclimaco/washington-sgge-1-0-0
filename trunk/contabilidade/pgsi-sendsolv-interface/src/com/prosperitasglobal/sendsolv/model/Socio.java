package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Socio extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	/** The description. */
	private String cota;

	/** The description. */
	private String porcentagem;

	/**
	 * Default constructor.
	 */
	public Socio()
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
	 * Gets the cota.
	 *
	 * @return the cota
	 */
	public String getCota()
	{
		return cota;
	}

	/**
	 * Sets the cota.
	 *
	 * @param cota the cota to set
	 */
	public void setCota(String cota)
	{
		this.cota = cota;
	}

	/**
	 * Gets the porcentagem.
	 *
	 * @return the porcentagem
	 */
	public String getPorcentagem()
	{
		return porcentagem;
	}

	/**
	 * Sets the porcentagem.
	 *
	 * @param porcentagem the porcentagem to set
	 */
	public void setPorcentagem(String porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Socio [getId()=" + getId() + ", getNome()=" + getNome() + ", getCota()=" + getCota()
				+ ", getPorcentagem()=" + getPorcentagem() + ", toString()=" + super.toString() + "]";
	}

}
