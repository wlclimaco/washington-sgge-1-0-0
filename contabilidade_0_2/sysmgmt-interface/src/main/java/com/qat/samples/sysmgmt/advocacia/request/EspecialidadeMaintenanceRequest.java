package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.clinica.model.Especialidade;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class EspecialidadeMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Especialidade especialidade;

	/**
	 * The Constructor.
	 */
	public EspecialidadeMaintenanceRequest()
	{

	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return "EspecialidadeMaintenanceRequest [getEspecialidade()=" + getEspecialidade() + ", toString()=" + super.toString() + "]";
	}





}