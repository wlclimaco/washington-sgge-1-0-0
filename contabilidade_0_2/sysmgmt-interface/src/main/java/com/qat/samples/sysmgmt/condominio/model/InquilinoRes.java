package com.qat.samples.sysmgmt.condominio.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class InquilinoRes extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private String bloco;
	private String apartamento;

	public InquilinoRes()
	{
		super();

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getBloco()
	{
		return bloco;
	}

	public void setBloco(String bloco)
	{
		this.bloco = bloco;
	}

	public String getApartamento()
	{
		return apartamento;
	}

	public void setApartamento(String apartamento)
	{
		this.apartamento = apartamento;
	}

	@Override
	public String toString()
	{
		return "InquilinoRes [getId()=" + getId() + ", getBloco()=" + getBloco() + ", getApartamento()="
				+ getApartamento() + ", toString()=" + super.toString() + "]";
	}

}
