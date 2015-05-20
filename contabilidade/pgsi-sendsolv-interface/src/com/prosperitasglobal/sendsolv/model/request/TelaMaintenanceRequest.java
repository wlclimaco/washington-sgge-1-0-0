package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Tela;

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