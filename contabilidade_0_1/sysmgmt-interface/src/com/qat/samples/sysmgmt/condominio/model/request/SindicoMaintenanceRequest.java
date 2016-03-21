package com.qat.samples.sysmgmt.advocacia.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.model.Advogado;

public class AdvogadoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Sindico Sindico;

	/**
	 * The Constructor.
	 */
	public SindicoMaintenanceRequest()
	{

	}

	/**
	 * @return the Sindico
	 */
	public Sindico getSindico()
	{
		return Sindico;
	}

	/**
	 * @param Sindico the Sindico to set
	 */
	public void setSindico(Sindico Sindico)
	{
		this.Sindico = Sindico;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SindicoMaintenanceRequest [getSindico()=" + getSindico() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}