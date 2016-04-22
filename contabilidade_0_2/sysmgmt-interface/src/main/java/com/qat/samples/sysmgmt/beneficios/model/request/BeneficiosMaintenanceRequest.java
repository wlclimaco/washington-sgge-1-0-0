package com.qat.samples.sysmgmt.beneficios.model.request;

import com.qat.samples.sysmgmt.beneficios.model.Beneficios;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class BeneficiosMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Beneficios beneficios;

	/**
	 * The Constructor.
	 */
	public BeneficiosMaintenanceRequest()
	{

	}

	/**
	 * @return the beneficios
	 */
	public Beneficios getBeneficios()
	{
		return beneficios;
	}

	/**
	 * @param beneficios the beneficios to set
	 */
	public void setBeneficios(Beneficios beneficios)
	{
		this.beneficios = beneficios;
	}

	@Override
	public String toString() {
		return "BeneficiosMaintenanceRequest [getBeneficios()=" + getBeneficios() + ", toString()=" + super.toString()
				+ "]";
	}

}