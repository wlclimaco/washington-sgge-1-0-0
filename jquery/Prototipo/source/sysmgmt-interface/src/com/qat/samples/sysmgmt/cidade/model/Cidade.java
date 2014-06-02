package com.qat.samples.sysmgmt.cidade.model;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Cidade", propOrder = {"id", "estado", "cidade"})
public class Cidade extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private String estado;

	/** The description. */
	private String cidade;

	/**
	 * Instantiates a new cidade.
	 */
	public Cidade()
	{

	}

	/**
	 * Instantiates a new cidade.
	 * 
	 * @param id the id
	 * @param estado the estado
	 * @param cidade the cidade
	 */
	public Cidade(Integer id, String estado, String cidade)
	{
		super();
		this.id = id;
		this.estado = estado;
		this.cidade = cidade;
	}

	/**
	 * Instantiates a new cidade.
	 * 
	 * @param estado the estado
	 * @param cidade the cidade
	 */
	public Cidade(String estado, String cidade)
	{
		super();
		this.estado = estado;
		this.cidade = cidade;
	}

	/**
	 * Instantiates a new cidade.
	 * 
	 * @param id the id
	 */
	public Cidade(Integer id)
	{
		super();
		this.id = id;
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
	 * Gets the estado.
	 * 
	 * @return the estado
	 */
	public String getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado.
	 * 
	 * @param estado the estado to set
	 */
	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the cidade.
	 * 
	 * @return the cidade
	 */
	public String getCidade()
	{
		return cidade;
	}

	/**
	 * Sets the cidade.
	 * 
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Cidade [getId()=" + getId() + ", getEstado()=" + getEstado() + ", getCidade()=" + getCidade()
				+ ", toString()=" + super.toString() + "]";
	}

}
