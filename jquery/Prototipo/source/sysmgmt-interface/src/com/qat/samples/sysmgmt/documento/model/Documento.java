package com.qat.samples.sysmgmt.documento.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Documento", propOrder = {"id", "indentificacaos", "dateNascimento"})
public class Documento extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private List<Indentificacao> indentificacaos;

	/** The description. */
	private Date dateNascimento;

	/**
	 * Instantiates a new documento.
	 */
	public Documento()
	{
		super();
	}

	/**
	 * Instantiates a new documento.
	 * 
	 * @param id the id
	 */
	public Documento(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * Instantiates a new documento.
	 * 
	 * @param id the id
	 * @param indentificacaos the indentificacaos
	 */
	public Documento(Integer id, List<Indentificacao> indentificacaos)
	{
		super();
		this.id = id;
		this.indentificacaos = indentificacaos;
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
	 * Gets the indentificacaos.
	 * 
	 * @return the indentificacaos
	 */
	public List<Indentificacao> getIndentificacaos()
	{
		return indentificacaos;
	}

	/**
	 * Sets the indentificacaos.
	 * 
	 * @param indentificacaos the indentificacaos to set
	 */
	public void setIndentificacaos(List<Indentificacao> indentificacaos)
	{
		this.indentificacaos = indentificacaos;
	}

	/**
	 * Gets the date nascimento.
	 * 
	 * @return the dateNascimento
	 */
	public Date getDateNascimento()
	{
		return dateNascimento;
	}

	/**
	 * Sets the date nascimento.
	 * 
	 * @param dateNascimento the dateNascimento to set
	 */
	public void setDateNascimento(Date dateNascimento)
	{
		this.dateNascimento = dateNascimento;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Documento [getId()=" + getId() + ", getIndentificacaos()=" + getIndentificacaos()
				+ ", getDateNascimento()=" + getDateNascimento() + ", toString()=" + super.toString() + "]";
	}

}
