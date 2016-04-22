package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class AdvogadoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "AdvogadoMaintenanceRequest [getAdvogado()=" + getAdvogado() + ", toString()=" + super.toString() + "]";
	}




}