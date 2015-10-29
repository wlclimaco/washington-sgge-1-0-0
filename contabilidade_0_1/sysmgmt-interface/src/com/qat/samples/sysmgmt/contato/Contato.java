package com.qat.samples.sysmgmt.contato;

import java.util.List;

import com.qat.samples.sysmgmt.util.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Contato extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Long dataContato;

	private ContatoTypeEnum motivo;

	public Integer getMotivoValue()
	{
		if (motivo != null)
		{
			return motivo.getValue();
		}
		return null;
	}

	public void setMotivoValue(Integer acaoTypeValue)
	{
		motivo = ContatoTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * @return the motivo
	 */
	public ContatoTypeEnum getMotivo()
	{
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(ContatoTypeEnum motivo)
	{
		this.motivo = motivo;
	}

	private List<ContatoItens> contatoItensList;

	/**
	 * Default constructor.
	 */
	public Contato()
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the dataContato
	 */
	public Long getDataContato()
	{
		return dataContato;
	}

	/**
	 * @param dataContato the dataContato to set
	 */
	public void setDataContato(Long dataContato)
	{
		this.dataContato = dataContato;
	}

	/**
	 * @return the contatoItensList
	 */
	public List<ContatoItens> getContatoItensList()
	{
		return contatoItensList;
	}

	/**
	 * @param contatoItensList the contatoItensList to set
	 */
	public void setContatoItensList(List<ContatoItens> contatoItensList)
	{
		this.contatoItensList = contatoItensList;
	}

	@Override
	public String toString()
	{
		return "Contato [getMotivoValue()=" + getMotivoValue() + ", getMotivo()=" + getMotivo() + ", getId()="
				+ getId() + ", getDataContato()=" + getDataContato() + ", getContatoItensList()="
				+ getContatoItensList() + ", toString()=" + super.toString() + "]";
	}

}
