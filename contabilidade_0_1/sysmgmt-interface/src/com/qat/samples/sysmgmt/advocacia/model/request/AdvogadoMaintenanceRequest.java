package com.qat.samples.sysmgmt.advocacia.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.advocacia.model.Advogado;

public class AdvogadoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Advogado Advogado;

	/**
	 * The Constructor.
	 */
	public AdvogadoMaintenanceRequest()
	{

	}

	/**
	 * @return the Advogado
	 */
	public Advogado getAdvogado()
	{
		return Advogado;
	}

	/**
	 * @param Advogado the Advogado to set
	 */
	public void setAdvogado(Advogado Advogado)
	{
		this.Advogado = Advogado;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AdvogadoMaintenanceRequest [getAdvogado()=" + getAdvogado() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}