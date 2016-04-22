package com.qat.samples.sysmgmt.clinica.model.request;

import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class MedicoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "MedicoMaintenanceRequest [getMedico()=" + getMedico() + ", toString()=" + super.toString() + "]";
	}

}