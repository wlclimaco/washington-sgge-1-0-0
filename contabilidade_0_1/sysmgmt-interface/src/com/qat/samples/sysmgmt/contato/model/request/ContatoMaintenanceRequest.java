package com.qat.samples.sysmgmt.contato.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.contato.Contato;

public class ContatoMaintenanceRequest extends MaintenanceRequest
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ContatoMaintenanceRequest [getContato()=" + getContato() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}