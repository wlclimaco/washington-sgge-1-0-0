package com.prosperitasglobal.sendsolv.model.request;

import com.prosperitasglobal.sendsolv.model.Deposito;
import com.qat.framework.model.request.MaintenanceRequest;

public class DepositoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes */
	private Deposito deposito;

	/**
	 * The Constructor.
	 */
	public DepositoMaintenanceRequest()
	{

	}

	/**
	 * @return the deposito
	 */
	public Deposito getDeposito()
	{
		return deposito;
	}

	/**
	 * @param deposito the deposito to set
	 */
	public void setDeposito(Deposito deposito)
	{
		this.deposito = deposito;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "DepositoMaintenanceRequest [getDeposito()=" + getDeposito() + ", getUserContext()=" + getUserContext()
				+ "]";
	}

}