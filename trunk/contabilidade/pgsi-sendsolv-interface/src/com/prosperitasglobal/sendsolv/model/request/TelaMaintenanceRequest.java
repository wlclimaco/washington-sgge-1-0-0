package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Tela;

// TODO: Auto-generated Javadoc
/**
 * The Class LocationMaintenanceRequest.
 *
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:26:49 AM
 */
public class TelaMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Tela tela;

	/**
	 * The Constructor.
	 */
	public TelaMaintenanceRequest()
	{

	}

	/**
	 * Gets the tela.
	 *
	 * @return the tela
	 */
	public Tela getTela()
	{
		return tela;
	}

	/**
	 * Sets the tela.
	 *
	 * @param tela the tela to set
	 */
	public void setTela(Tela tela)
	{
		this.tela = tela;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TelaMaintenanceRequest [getTela()=" + getTela() + ", getUserContext()="
				+ getUserContext() + "]";
	}

}