package com.qat.samples.sysmgmt.pessoa.model.request;

import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Socio;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class SocioMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Socio socio;

	/**
	 * The Constructor.
	 */
	public SocioMaintenanceRequest()
	{

	}



	public Socio getSocio() {
		return socio;
	}



	public void setSocio(Socio socio) {
		this.socio = socio;
	}



	@Override
	public String toString() {
		return "SocioMaintenanceRequest [getSocio()=" + getSocio() + ", toString()=" + super.toString() + "]";
	}

}