package com.qat.samples.sysmgmt.clinica.model.request;

import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class PacienteMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Paciente paciente;

	/**
	 * The Constructor.
	 */
	public PacienteMaintenanceRequest()
	{

	}

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente()
	{
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(Paciente paciente)
	{
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		return "PacienteMaintenanceRequest [getPaciente()=" + getPaciente() + ", toString()=" + super.toString() + "]";
	}

}