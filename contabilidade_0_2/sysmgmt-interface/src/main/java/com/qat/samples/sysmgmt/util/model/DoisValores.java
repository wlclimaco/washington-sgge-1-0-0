package com.qat.samples.sysmgmt.util.model;

import com.qat.samples.sysmgmt.entidade.model.Pagina;

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

	private String value;

	/** The description. */
	private String descricao;


	private Pagina pagina;

	/** The atributos. */
	private DoisValorType doisValorType;

	/**
	 * Default constructor.
	 */
	public DoisValores()
	{
		super();
	}



	public DoisValores(Integer id) {
		super();
		this.id = id;
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



	public DoisValorType getDoisValorType() {
		return doisValorType;
	}

	public void setDoisValorType(DoisValorType doisValorType) {
		this.doisValorType = doisValorType;
	}

	public Pagina getPagina() {
		return pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DoisValores [getId()=" + getId() + ", getNome()=" + getNome() + ", getDescricao()=" + getDescricao()
				+ ", getDoisValorType()=" + getDoisValorType() + ", getPagina()=" + getPagina() + ", getValue()="
				+ getValue() + ", toString()=" + super.toString() + "]";
	}

}
