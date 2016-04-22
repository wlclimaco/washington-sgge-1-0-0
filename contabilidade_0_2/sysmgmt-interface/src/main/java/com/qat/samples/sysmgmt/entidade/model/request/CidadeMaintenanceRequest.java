package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CidadeMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "CidadeMaintenanceRequest [getCidade()=" + getCidade() + ", toString()=" + super.toString() + "]";
	}

}