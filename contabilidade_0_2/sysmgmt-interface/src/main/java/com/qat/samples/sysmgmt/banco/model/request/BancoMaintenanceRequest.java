package com.qat.samples.sysmgmt.banco.model.request;

import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class BancoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "BancoMaintenanceRequest [getBanco()=" + getBanco() + ", toString()=" + super.toString() + "]";
	}

}