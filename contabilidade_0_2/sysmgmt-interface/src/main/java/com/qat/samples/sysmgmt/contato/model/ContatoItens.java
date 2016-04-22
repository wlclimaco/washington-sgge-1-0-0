package com.qat.samples.sysmgmt.contato.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class ContatoItens extends ModelCosmeDamiao
{

	/** The SendSolv id for the account. */
	private Integer id;

	/** The data alt. */
	private Long dataAlt;

	/** The note. */
	private String texto;

	/** The titulo. */
	private String titulo;

	/**
	 * Default constructor.
	 */
	public ContatoItens()
	{
	}

	public ContatoItens(Integer id, Long dataAlt, String texto, String titulo, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.dataAlt = dataAlt;
		this.texto = texto;
		this.titulo = titulo;
		setModelAction(modelAction);
	}

	/**
	 * Gets the SendSolv id for the account.
	 *
	 * @return the SendSolv id for the account
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the SendSolv id for the account.
	 *
	 * @param id the new SendSolv id for the account
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the data alt.
	 *
	 * @return the data alt
	 */
	public Long getDataAlt()
	{
		return dataAlt;
	}

	/**
	 * Sets the data alt.
	 *
	 * @param dataAlt the new data alt
	 */
	public void setDataAlt(Long dataAlt)
	{
		this.dataAlt = dataAlt;
	}

	public String getTexto()
	{
		return texto;
	}

	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo()
	{
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	@Override
	public String toString()
	{
		return "ContatoItens [getId()=" + getId() + ", getDataAlt()=" + getDataAlt() + ", getTexto()=" + getTexto()
				+ ", getTitulo()=" + getTitulo() + ", toString()=" + super.toString() + "]";
	}

}
