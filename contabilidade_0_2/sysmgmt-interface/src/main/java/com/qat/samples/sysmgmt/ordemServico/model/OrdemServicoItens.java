package com.qat.samples.sysmgmt.ordemServico.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class OrdemServicoItens extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Integer idOrdemServico;

	/** The estado. */
	private Long data;

	/** The cep. */
	private String texto;

	/**
	 * Default constructor.
	 */
	public OrdemServicoItens()
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
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the new data
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * Gets the texto.
	 * 
	 * @return the texto
	 */
	public String getTexto()
	{
		return texto;
	}

	/**
	 * Sets the texto.
	 * 
	 * @param texto the new texto
	 */
	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	/**
	 * @return the idOrdemServico
	 */
	public Integer getIdOrdemServico()
	{
		return idOrdemServico;
	}

	/**
	 * @param idOrdemServico the idOrdemServico to set
	 */
	public void setIdOrdemServico(Integer idOrdemServico)
	{
		this.idOrdemServico = idOrdemServico;
	}

	@Override
	public String toString()
	{
		return "OrdemServicoItens [getId()=" + getId() + ", getData()=" + getData() + ", getTexto()=" + getTexto()
				+ ", getIdOrdemServico()=" + getIdOrdemServico() + ", toString()=" + super.toString() + "]";
	}

}
