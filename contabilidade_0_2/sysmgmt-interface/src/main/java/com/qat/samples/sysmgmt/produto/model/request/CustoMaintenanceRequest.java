package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class CustoMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Custo custo;

	/**
	 * The Constructor.
	 */
	public CustoMaintenanceRequest()
	{

	}

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	@Override
	public String toString() {
		return "CustoMaintenanceRequest [getCusto()=" + getCusto() + ", toString()=" + super.toString() + "]";
	}

	
}