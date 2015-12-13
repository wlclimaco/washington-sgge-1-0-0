package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.MaintenanceRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;

public class PlanoMaintenanceRequest extends MaintenanceRequest
{

	/** Attributes. */
	private Plano plano;

	/**
	 * The Constructor.
	 */
	public PlanoMaintenanceRequest()
	{

	}

	/**
	 * Gets the plano.
	 * 
	 * @return the plano
	 */
	public Plano getPlano()
	{
		return plano;
	}

	/**
	 * Sets the plano.
	 * 
	 * @param plano the plano
	 */
	public void setPlano(Plano plano)
	{
		this.plano = plano;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "PlanoMaintenanceRequest [getPlano()=" + getPlano() + ", getUserContext()="
				+ getUserContext() + "]";
	}
}