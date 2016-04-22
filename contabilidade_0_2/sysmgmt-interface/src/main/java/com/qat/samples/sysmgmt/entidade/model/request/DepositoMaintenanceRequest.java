package com.qat.samples.sysmgmt.entidade.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class DepositoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "DepositoMaintenanceRequest [getDeposito()=" + getDeposito() + ", toString()=" + super.toString() + "]";
	}

}