package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.samples.sysmgmt.entidade.model.Boleto;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class BoletoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Boleto boleto;

	/**
	 * The Constructor.
	 */
	public BoletoMaintenanceRequest()
	{

	}

	/**
	 * @return the boleto
	 */
	public Boleto getBoleto()
	{
		return boleto;
	}

	/**
	 * @param boleto the boleto to set
	 */
	public void setBoleto(Boleto boleto)
	{
		this.boleto = boleto;
	}

	@Override
	public String toString() {
		return "BoletoMaintenanceRequest [getBoleto()=" + getBoleto() + ", toString()=" + super.toString() + "]";
	}

}