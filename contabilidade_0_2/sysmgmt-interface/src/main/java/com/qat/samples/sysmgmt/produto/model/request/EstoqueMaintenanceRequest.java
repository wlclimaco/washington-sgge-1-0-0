package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class EstoqueMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Estoque estoque;

	/**
	 * The Constructor.
	 */
	public EstoqueMaintenanceRequest()
	{

	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "EstoqueMaintenanceRequest [getEstoque()=" + getEstoque() + ", toString()=" + super.toString() + "]";
	}

	
}