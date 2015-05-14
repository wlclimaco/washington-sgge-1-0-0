package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Banco;

public class BancoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Banco banco;

	/**
	 * The Constructor.
	 */
	public BancoMaintenanceRequest()
	{

	}

	/**
	 * @return the banco
	 */
	public Banco getBanco()
	{
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(Banco banco)
	{
		this.banco = banco;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BancoMaintenanceRequest [getBanco()=" + getBanco() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}