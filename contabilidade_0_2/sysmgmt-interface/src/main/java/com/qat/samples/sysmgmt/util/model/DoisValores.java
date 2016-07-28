package com.qat.samples.sysmgmt.util.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class DoisValores extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String nome;

	/** The description. */
	private String descricao;

	/** The atributos. */
	private List<Atributos> atributos;

	/**
	 * Default constructor.
	 */
	public DoisValores()
	{
		super();
	}

	public DoisValores(int i, String string) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Atributos> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<Atributos> atributos) {
		this.atributos = atributos;
	}

	@Override
	public String toString() {
		return "DoisValores [getId()=" + getId() + ", getNome()=" + getNome() + ", getDescricao()=" + getDescricao()
				+ ", getAtributos()=" + getAtributos() + ", toString()=" + super.toString() + "]";
	}

	

}
