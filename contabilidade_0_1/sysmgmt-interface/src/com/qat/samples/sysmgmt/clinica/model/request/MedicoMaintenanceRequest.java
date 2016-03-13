package com.qat.samples.sysmgmt.clinica.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.Medico;

public class MedicoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Medico medico;

	/**
	 * The Constructor.
	 */
	public MedicoMaintenanceRequest()
	{

	}

	/**
	 * @return the medico
	 */
	public Medico getMedico()
	{
		return medico;
	}

	/**
	 * @param medico the medico to set
	 */
	public void setMedico(Medico medico)
	{
		this.medico = medico;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MedicoMaintenanceRequest [getMedico()=" + getMedico() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}