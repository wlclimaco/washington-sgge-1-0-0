package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Beneficios;
import com.qat.framework.model.request.MaintenanceRequest;

public class BeneficiosMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BeneficiosMaintenanceRequest [getBeneficios()=" + getBeneficios() + ", getUserContext()="
				+ getUserContext()
				+ "]";
	}

}