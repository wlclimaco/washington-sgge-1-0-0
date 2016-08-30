package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class PorcaoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Porcao porcao;

	/**
	 * The Constructor.
	 */
	public PorcaoMaintenanceRequest()
	{

	}

	public Porcao getPorcao() {
		return porcao;
	}

	public void setPorcao(Porcao porcao) {
		this.porcao = porcao;
	}

	@Override
	public String toString() {
		return "PorcaoMaintenanceRequest [getPorcao()=" + getPorcao() + ", toString()=" + super.toString() + "]";
	}

}