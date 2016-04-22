package com.qat.samples.sysmgmt.contato.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ContatoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Contato contato;

	/**
	 * The Constructor.
	 */
	public ContatoMaintenanceRequest()
	{

	}

	/**
	 * @return the contato
	 */
	public Contato getContato()
	{
		return contato;
	}

	/**
	 * @param contato the contato to set
	 */
	public void setContato(Contato contato)
	{
		this.contato = contato;
	}

	@Override
	public String toString() {
		return "ContatoMaintenanceRequest [getContato()=" + getContato() + ", toString()=" + super.toString() + "]";
	}


}