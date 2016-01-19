package com.qat.samples.sysmgmt.contato;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

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

	/** The contato status. */
	private ContatoStatusEnum contatoStatus;

	/** The note. */
	private String texto;

	/** The titulo. */
	private String titulo;

	/**
	 * Default constructor.
	 */
	public ContatoItens()
	{
		super();
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

	/**
	 * Gets the contato status.
	 * 
	 * @return the contato status
	 */
	public ContatoStatusEnum getContatoStatus()
	{
		return contatoStatus;
	}

	/**
	 * Sets the contato status.
	 * 
	 * @param contatoStatus the new contato status
	 */
	public void setContatoStatus(ContatoStatusEnum contatoStatus)
	{
		this.contatoStatus = contatoStatus;
	}

	/**
	 * Gets the contato status value.
	 * 
	 * @return the contato status value
	 */
	public Integer getContatoStatusValue()
	{
		if (contatoStatus != null)
		{
			return contatoStatus.getValue();
		}
		return null;
	}

	/**
	 * Sets the motivo value.
	 * 
	 * @param acaoTypeValue the new motivo value
	 */
	public void setContatoStatusValue(Integer acaoTypeValue)
	{
		contatoStatus = ContatoStatusEnum.enumForValue(acaoTypeValue);
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
		return "ContatoItens [getId()=" + getId() + ", getDataAlt()=" + getDataAlt() + ", getContatoStatus()="
				+ getContatoStatus() + ", getContatoStatusValue()=" + getContatoStatusValue() + ", getTexto()="
				+ getTexto() + ", getTitulo()=" + getTitulo() + ", toString()=" + super.toString() + "]";
	}

}
