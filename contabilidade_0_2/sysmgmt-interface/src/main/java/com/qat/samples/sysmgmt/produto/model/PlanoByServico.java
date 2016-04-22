package com.qat.samples.sysmgmt.produto.model;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class PlanoByServico extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer planoId;

	private Integer servicoId;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getPlanoId()
	{
		return planoId;
	}

	public void setPlanoId(Integer planoId)
	{
		this.planoId = planoId;
	}

	public Integer getServicoId()
	{
		return servicoId;
	}

	public void setServicoId(Integer servicoId)
	{
		this.servicoId = servicoId;
	}

	@Override
	public String toString()
	{
		return "PlanoByServico [getId()=" + getId() + ", getPlanoId()=" + getPlanoId() + ", getServicoId()="
				+ getServicoId() + ", toString()=" + super.toString() + "]";
	}

}
