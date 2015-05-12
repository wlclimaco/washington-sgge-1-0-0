package com.prosperitasglobal.sendsolv.model;


// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class NFe extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String type;

	/** The description. */
	private String description;

	/** The numero. */
	private String tamanho;

	/** The nome. */
	private boolean obrigatorio;

	/** The left. */
	private boolean chavePrimaria;

	/** The top. */
	private boolean chaveSecundaria;

	/** The width. */
	private Tabela tabelaSecundaria;

	/** The height. */
	private String nome;

	/**
	 * Default constructor.
	 */
	public NFe()
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Gets the tamanho.
	 *
	 * @return the tamanho
	 */
	public String getTamanho()
	{
		return tamanho;
	}

	/**
	 * Sets the tamanho.
	 *
	 * @param tamanho the new tamanho
	 */
	public void setTamanho(String tamanho)
	{
		this.tamanho = tamanho;
	}

	/**
	 * Checks if is obrigatorio.
	 *
	 * @return true, if is obrigatorio
	 */
	public boolean isObrigatorio()
	{
		return obrigatorio;
	}

	/**
	 * Sets the obrigatorio.
	 *
	 * @param obrigatorio the new obrigatorio
	 */
	public void setObrigatorio(boolean obrigatorio)
	{
		this.obrigatorio = obrigatorio;
	}

	/**
	 * Checks if is chave primaria.
	 *
	 * @return true, if is chave primaria
	 */
	public boolean isChavePrimaria()
	{
		return chavePrimaria;
	}

	/**
	 * Sets the chave primaria.
	 *
	 * @param chavePrimaria the new chave primaria
	 */
	public void setChavePrimaria(boolean chavePrimaria)
	{
		this.chavePrimaria = chavePrimaria;
	}

	/**
	 * Checks if is chave secundaria.
	 *
	 * @return true, if is chave secundaria
	 */
	public boolean isChaveSecundaria()
	{
		return chaveSecundaria;
	}

	/**
	 * Sets the chave secundaria.
	 *
	 * @param chaveSecundaria the new chave secundaria
	 */
	public void setChaveSecundaria(boolean chaveSecundaria)
	{
		this.chaveSecundaria = chaveSecundaria;
	}

	/**
	 * Gets the tabela secundaria.
	 *
	 * @return the tabela secundaria
	 */
	public Tabela getTabelaSecundaria()
	{
		return tabelaSecundaria;
	}

	/**
	 * Sets the tabela secundaria.
	 *
	 * @param tabelaSecundaria the new tabela secundaria
	 */
	public void setTabelaSecundaria(Tabela tabelaSecundaria)
	{
		this.tabelaSecundaria = tabelaSecundaria;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "NFe [getId()=" + getId() + ", getType()=" + getType() + ", getDescription()=" + getDescription()
				+ ", getTamanho()=" + getTamanho() + ", isObrigatorio()=" + isObrigatorio() + ", isChavePrimaria()="
				+ isChavePrimaria() + ", isChaveSecundaria()=" + isChaveSecundaria() + ", getTabelaSecundaria()="
				+ getTabelaSecundaria() + ", getNome()=" + getNome() + ", toString()=" + super.toString() + "]";
	}

}
