package com.qat.samples.sysmgmt.banco;

import java.util.List;

import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Banco extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private String nome;

	private List<Agencia> agenciaList;

	public Banco(Integer id)
	{
		super();
		this.id = id;
	}

	/**
	 * The Constructor.
	 */
	public Banco()
	{

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
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}

	/**
	 * @return the agenciaList
	 */
	public List<Agencia> getAgenciaList()
	{
		return agenciaList;
	}

	/**
	 * @param agenciaList the agenciaList to set
	 */
	public void setAgenciaList(List<Agencia> agenciaList)
	{
		this.agenciaList = agenciaList;
	}

	@Override
	public String toString()
	{
		return "Banco [getId()=" + getId() + ", getNome()=" + getNome() + ", getAgenciaList()=" + getAgenciaList()
				+ ", toString()=" + super.toString() + "]";
	}

}