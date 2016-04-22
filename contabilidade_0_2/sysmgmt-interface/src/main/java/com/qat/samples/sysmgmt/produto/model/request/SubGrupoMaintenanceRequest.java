package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class SubGrupoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private SubGrupo plano;

	/**
	 * The Constructor.
	 */
	public SubGrupoMaintenanceRequest()
	{

	}

	/**
	 * Gets the plano.
	 *
	 * @return the plano
	 */
	public SubGrupo getSubGrupo()
	{
		return plano;
	}

	/**
	 * Sets the plano.
	 *
	 * @param plano the plano
	 */
	public void setSubGrupo(SubGrupo plano)
	{
		this.plano = plano;
	}

	@Override
	public String toString() {
		return "SubGrupoMaintenanceRequest [getSubGrupo()=" + getSubGrupo() + ", toString()=" + super.toString() + "]";
	}
}