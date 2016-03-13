package com.qat.samples.sysmgmt.clinica.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.Paciente;

public class PacienteMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PacienteMaintenanceRequest [getPaciente()=" + getPaciente() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}