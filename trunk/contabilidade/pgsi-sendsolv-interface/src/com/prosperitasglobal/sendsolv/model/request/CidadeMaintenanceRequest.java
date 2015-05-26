package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Cidade;
import com.qat.framework.model.request.MaintenanceRequest;

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