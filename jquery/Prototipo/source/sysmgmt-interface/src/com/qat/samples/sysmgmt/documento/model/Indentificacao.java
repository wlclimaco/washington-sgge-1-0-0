package com.qat.samples.sysmgmt.documento.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Indentiicacao", propOrder = {"id", "tipo"})
public class Indentificacao extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private DocumentoTypeEnum tipo;

	/**
	 * Instantiates a new bundle.
	 */
	public Indentificacao()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Indentificacao(Integer id)
	{
		this.id = id;
	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 * @param tipo the tipo
	 */
	public Indentificacao(Integer id, DocumentoTypeEnum tipo)
	{
		this.id = id;
		this.tipo = tipo;
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the tipo.
	 * 
	 * @return the tipo
	 */
	public DocumentoTypeEnum getTipo()
	{
		return tipo;
	}

	/**
	 * Sets the tipo.
	 * 
	 * @param tipo the tipo to set
	 */
	public void setTipo(DocumentoTypeEnum tipo)
	{
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "Indentificacao [getId()=" + getId() + ", getTipo()=" + getTipo() + ", toString()=" + super.toString()
				+ "]";
	}

}
