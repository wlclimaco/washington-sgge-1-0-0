package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class MarcaMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Marca plano;

	/**
	 * The Constructor.
	 */
	public MarcaMaintenanceRequest()
	{

	}

	/**
	 * Gets the plano.
	 *
	 * @return the plano
	 */
	public Marca getMarca()
	{
		return plano;
	}

	/**
	 * Sets the plano.
	 *
	 * @param plano the plano
	 */
	public void setMarca(Marca plano)
	{
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "MarcaMaintenanceRequest [getMarca()=" + getMarca() + ", toString()=" + super.toString() + "]";
	}
}