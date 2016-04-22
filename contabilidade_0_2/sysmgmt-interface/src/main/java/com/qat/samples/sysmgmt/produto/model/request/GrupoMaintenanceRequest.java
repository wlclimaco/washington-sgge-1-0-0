package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class GrupoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Grupo plano;

	/**
	 * The Constructor.
	 */
	public GrupoMaintenanceRequest()
	{

	}

	/**
	 * Gets the plano.
	 *
	 * @return the plano
	 */
	public Grupo getGrupo()
	{
		return plano;
	}

	/**
	 * Sets the plano.
	 *
	 * @param plano the plano
	 */
	public void setGrupo(Grupo plano)
	{
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "GrupoMaintenanceRequest [getGrupo()=" + getGrupo() + ", toString()=" + super.toString() + "]";
	}
}