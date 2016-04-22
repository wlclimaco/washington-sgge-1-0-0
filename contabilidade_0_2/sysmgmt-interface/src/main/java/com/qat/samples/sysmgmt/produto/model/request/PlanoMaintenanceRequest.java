package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.framework.model.request.Request;
import com.qat.samples.sysmgmt.contabilidade.model.Plano;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class PlanoMaintenanceRequest extends UtilMaintenanceRequest
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

	@Override
	public String toString() {
		return "PlanoMaintenanceRequest [getPlano()=" + getPlano() + ", toString()=" + super.toString() + "]";
	}
}