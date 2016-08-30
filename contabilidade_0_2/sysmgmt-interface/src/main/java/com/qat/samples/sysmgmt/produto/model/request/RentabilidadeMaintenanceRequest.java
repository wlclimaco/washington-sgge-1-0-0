package com.qat.samples.sysmgmt.produto.model.request;

import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class RentabilidadeMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes. */
	private Rentabilidade rentabilidade;

	/**
	 * The Constructor.
	 */
	public RentabilidadeMaintenanceRequest()
	{

	}

	public Rentabilidade getRentabilidade() {
		return rentabilidade;
	}

	public void setRentabilidade(Rentabilidade rentabilidade) {
		this.rentabilidade = rentabilidade;
	}

	@Override
	public String toString() {
		return "RentabilidadeMaintenanceRequest [getRentabilidade()=" + getRentabilidade() + ", toString()="
				+ super.toString() + "]";
	}


}