package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.util.Cidade;

public class CidadeMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Cidade cidade;

	/**
	 * The Constructor.
	 */
	public CidadeMaintenanceRequest()
	{

	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(Cidade cidade)
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
		return "CidadeMaintenanceRequest [getCidade()=" + getCidade() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}