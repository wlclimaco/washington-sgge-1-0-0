package com.prosperitasglobal.sendsolv.model;

import java.util.List;

import com.prosperitasglobal.cbof.model.Note;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Pessoa extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String nome;

	private String nomePai;

	private String nomeMae;

	private String nomeConjugue;

	private Integer estadoCivil;

	private Integer datanasc;

	private String foto;

	/** The type of an account. */
	private PessoaTypeEnum type;

	/** The sexo. */
	private Integer sexo;

	/** The enderecos. */
	private List<Endereco> enderecos;

	/** The documentos. */
	private List<Documento> documentos;

	/** The emails. */
	private List<Email> emails;

	/** The Telefones. */
	private List<Telefone> Telefones;

	private List<Note> notes;

	private List<Banco> bancos;

	/**
	 * Default constructor.
	 */
	public Pessoa()
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
		return "Cliente [getId()=" + getId() + ", getType()=" + getType() + ", getDescription()=" + getDescription()
				+ ", getTamanho()=" + getTamanho() + ", isObrigatorio()=" + isObrigatorio() + ", isChavePrimaria()="
				+ isChavePrimaria() + ", isChaveSecundaria()=" + isChaveSecundaria() + ", getTabelaSecundaria()="
				+ getTabelaSecundaria() + ", getNome()=" + getNome() + ", toString()=" + super.toString() + "]";
	}

}
